package webactions;

import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ServiceLocatorException;
import mail.MailSenderService;
import mail.MailSenderServiceFactory;
import mail.MailUtils;
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
		
		if (req.getParameter("bidAmount") != null && req.getParameter("auctionId") != null) {
			int aid = Integer.parseInt(req.getParameter("auctionId"));
			int uid = LoginUtils.getUserId(req);
			double newBid = Double.parseDouble(req.getParameter("bidAmount"));
			
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
							
						} catch (ServiceLocatorException e) {
							// To bad cant send mail
							e.printStackTrace();
						}
					}
				} else {
					// Auction doesnt exist
				}
			} catch (SQLException e) {
				// TODO- handle not being able to place the bid. Could be not unique?? or return not succesful ajax
				e.printStackTrace();
			}
		}	
		
		// TODO - figure out what you want to return
		return null;
	}

}
