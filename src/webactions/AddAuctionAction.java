package webactions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
			// TODO - prices cant be negative, increment must be greater then 0, reserve not zero. - done
			// TODO - closing time > current time (> 3mins and < 60mins), auction starts when user clicks button - done
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
				
				//TODO: this image thing later
				auc.setAuctionImage("blahblah");
				
				java.util.Date date= new java.util.Date();
				Timestamp startTime = new Timestamp(date.getTime());
				Calendar cal = Calendar.getInstance();
		        cal.setTimeInMillis(startTime.getTime());
		        cal.add(Calendar.MINUTE, bidDuration);
		        Timestamp endTime = new Timestamp(cal.getTime().getTime());
				
				auc.setAuctionStartTime(startTime);
				auc.setAuctionCloseTime(endTime);
				auc.setAuctionHalt(false);
				
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
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return resultMap;
	}
}