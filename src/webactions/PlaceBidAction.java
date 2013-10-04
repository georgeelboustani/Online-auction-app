package webactions;

import java.sql.SQLException;
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

import com.google.gson.Gson;

import exceptions.ServiceLocatorException;
import mail.MailSenderService;
import mail.MailSenderServiceFactory;
import mail.MailUtils;
import model.ForAuction;
import model.ForAuctionBid;
import jdbc.AuctionBidDAO;
import jdbc.AuctionBidDAOImpl;
import jdbc.AuctionBidDTO;
import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;

public class PlaceBidAction implements WebActionAjax {

	@Override
	public Map<String, Object> executeAjax(HttpServletRequest req,
			HttpServletResponse res, Logger logger) {
		logger.info("WebAction => Bidding Auction");
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String jsonData = req.getParameter("data");
		Gson gson = new Gson();
		ForAuctionBid bidData = gson.fromJson(jsonData, ForAuctionBid.class);
		
		int aid = Integer.parseInt(bidData.getAucID());
		int uid = LoginUtils.getUserId(req);
		double newBid = Double.parseDouble(bidData.getValue());
		
		System.out.println(aid + "  " + uid + " " + newBid);
		
		AuctionBidDAO bidDao = new AuctionBidDAOImpl();
		AuctionDAO aucDao = new AuctionDAOImpl();
		
		try {
			AuctionBidDTO highestBid = bidDao.getHighestBid(aid);
			double currentHighestBid = 0;
			if (highestBid != null) {
				currentHighestBid = highestBid.getBidAmount();
			}
			
			AuctionDTO auction = aucDao.getAuctionById(aid);
			
			if (auction != null) {
				if (newBid >= currentHighestBid + auction.getBiddingIncrement()) {
					AuctionBidDTO bidDto = new AuctionBidDTO();
					bidDto.setAuctionId(aid);
					bidDto.setBidAmount(newBid);
					bidDto.setUserId(uid);
					
					ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
					Validator validator = factory.getValidator();
					Set<ConstraintViolation<AuctionBidDTO>> violations = validator.validate(bidDto);
					
					if(violations.size() > 0){
						//Returns error message for violations detected
						StringBuffer message = new StringBuffer();
						message.append("Could not process auction, you had invalid inputs of: ");
						Iterator<ConstraintViolation<AuctionBidDTO>> it = violations.iterator();
						while(it.hasNext()){
							message.append("<br/>" + it.next().getMessage());
						}
						resultMap.put("success", false);
						resultMap.put("message", message.toString());
						resultMap.put("redirect", "controller?action=searchAuction");
						
					}else{
						bidDao.placeBid(bidDto);
						
						try {
							MailSenderService mailsender = MailSenderServiceFactory.getMailSenderService();
							UserDAO userDao = new UserDAOImpl();
							
							if (highestBid != null) {
								UserDTO prevWinner = userDao.getUserById(highestBid.getUserId());
								StringBuffer text = new StringBuffer("Woops "+prevWinner.getFirstName()+",\nYou have lost the lead in the following auction:\n");
								text.append(auction.getAuctionTitle()+"\nThe new highest bid is $"+newBid+"\nThanks\nRollback team");
								mailsender.sendMail(MailUtils.OUR_EMAIL, prevWinner.getEmail(), "Beaten at auction: "+auction.getAuctionTitle(), text);
							}
							
							UserDTO currentUser = userDao.getUserById(LoginUtils.getUserId(req));
							StringBuffer text = new StringBuffer("Congrats " + currentUser.getFirstName()+",\nYou have gained the lead in the following auction:\n");
							text.append(auction.getAuctionTitle()+"\nThe new highest bid is $"+newBid+"\nThanks\nRollback team");
							mailsender.sendMail(MailUtils.OUR_EMAIL, currentUser.getEmail(), "Taken the lead: "+auction.getAuctionTitle(), text);
							
							resultMap.put("success", true);
							resultMap.put("message", "Awesome, you gained the lead!");
							resultMap.put("redirect", "controller?action=searchAuction");
							
						} catch (ServiceLocatorException e) {
							// To bad cant send mail
							e.printStackTrace();
						}
					}
				}
			} else {
				resultMap.put("success", false);
				resultMap.put("message", "Sorry, auction does not exist");
				resultMap.put("redirect", "controller?action=searchAuction");
			}
		} catch (SQLException e) {
			// TODO- handle not being able to place the bid. Could be not unique?? or return not succesful ajax
			e.printStackTrace();
		}
		
		// TODO - figure out what you want to return
		return resultMap;
	}

}
