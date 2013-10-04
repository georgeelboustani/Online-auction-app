package webactions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import model.ForAuction;

import com.google.gson.Gson;

import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;

public class AddAuctionAction implements WebActionAjax {

	@Override
	public Map<String, Object> executeAjax(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		logger.info("WebAction => Adding Auction");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String jsonData = req.getParameter("data");

		Gson gson = new Gson();
		ForAuction aucData = gson.fromJson(jsonData, ForAuction.class);
	    
		try {
			UserDAO userDao = new UserDAOImpl();
			int userId = LoginUtils.getUserId(req);
			UserDTO user = userDao.getUserById(userId);
			int bidDuration = Integer.parseInt(aucData.getDuration());
			double startPrice = Double.parseDouble(aucData.getStartPrice());
			double reservePrice = Double.parseDouble(aucData.getReservePrice());
			
			if(bidDuration < 3 && bidDuration > 60){
				resultMap.put("success", false);
				resultMap.put("message", "Duration is incorrect");
				resultMap.put("redirect", "");
			}else if (startPrice > reservePrice) {	
				resultMap.put("success", false);
				resultMap.put("message", "Start price is greater than reserve price");
				resultMap.put("redirect", "");
			}else if (user == null || user.getBanned()) {
				// Do nothing i guess?
				resultMap.put("success", false);
				resultMap.put("message", "Your already banned.. please excuse yourself");
				resultMap.put("redirect", "");
				
			} else {
				
				AuctionDTO auc = new AuctionDTO();
				auc.setAuctionOwnerId(userId);
				auc.setAuctionTitle(aucData.getTitle());
				auc.setAuctionCategory(aucData.getCategory());
				
				auc.setAuctionImage(aucData.getImage());
				
				auc.setAuctionDescription(aucData.getItemDesc());
				auc.setAuctionPostageDetails(aucData.getPostageDesc());
				auc.setBiddingStartPrice(Double.parseDouble(aucData.getStartPrice()));
				auc.setAuctionReservePrice(Double.parseDouble(aucData.getReservePrice()));
				auc.setBiddingIncrement(Double.parseDouble(aucData.getMinBid()));
				
				
				java.util.Date date= new java.util.Date();
				Timestamp startTime = new Timestamp(date.getTime());
				Calendar cal = Calendar.getInstance();
		        cal.setTimeInMillis(startTime.getTime());
		        cal.add(Calendar.MINUTE, bidDuration);
		        Timestamp endTime = new Timestamp(cal.getTime().getTime());
				
				auc.setAuctionStartTime(startTime);
				auc.setAuctionCloseTime(endTime);
				auc.setAuctionHalt(false);

				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				Validator validator = factory.getValidator();
				Set<ConstraintViolation<AuctionDTO>> violations = validator.validate(auc);
				
				if(violations.size() > 0){
					//Returns error message for violations detected
					StringBuffer message = new StringBuffer();
					message.append("Could not process auction, you had invalid inputs of: ");
					Iterator<ConstraintViolation<AuctionDTO>> it = violations.iterator();
					while(it.hasNext()){
						message.append("<br/>" + it.next().getMessage());
					}
					resultMap.put("success", false);
					resultMap.put("message", message.toString());
					resultMap.put("redirect", "controller?action=viewSelling");
				}else{
					AuctionDAO aucdao = new AuctionDAOImpl();
					try {
						aucdao.addAuction(auc);
					} catch (SQLException e) {
						e.printStackTrace();
						
						// TODO - sort out handling of this exception
						resultMap.put("success", false);
						resultMap.put("message", "SQL exception: "+e.getSQLState());
						resultMap.put("redirect", "");
					}
					
					resultMap.put("success", true);
					resultMap.put("message", "Your Auction has been Submitted!!");
					resultMap.put("redirect", "controller?action=viewSelling");
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return resultMap;
	}
}