package webactions;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;
import mail.MailSenderService;
import mail.MailSenderServiceFactory;
import mail.MailUtils;
import exceptions.ServiceLocatorException;

public class HaltAuctionAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req,
			HttpServletResponse res, Logger logger) {

		UserDAO userDao = new UserDAOImpl();
		
		int auctionId = -1;
		try {
			auctionId = Integer.parseInt(req.getParameter("auctionId"));
		} catch (NumberFormatException e) {
			
		}
		
		if (auctionId > -1) {
			String reasons = req.getParameter("reasons");		
			if (reasons == null) {
				reasons = "No reason";
			}
			
			int userId = LoginUtils.getUserId(req);
			
			try {
				UserDTO user = userDao.getUserById(userId);
				
				AuctionDAO auctionDao = new AuctionDAOImpl();
				AuctionDTO auction = auctionDao.getAuctionById(auctionId);
				
				auctionDao.closeAuction(auctionId);
				
				try {
					MailSenderService mailsender = MailSenderServiceFactory.getMailSenderService();
					StringBuffer text = new StringBuffer();
					text.append("Hi "+user.getFirstName()+",\nYour auction ("+auction.getAuctionTitle()+") has been banned for the following reasons:\n");
					text.append(reasons);
					mailsender.sendMail(MailUtils.OUR_EMAIL, user.getEmail(), "Auction halted: " + auction.getAuctionTitle(), text);
				} catch (ServiceLocatorException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// TODO - where to go?? broo
		return "index.jsp";
	}

}
