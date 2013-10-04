package webactions;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ServiceLocatorException;
import mail.MailSenderService;
import mail.MailSenderServiceFactory;
import mail.MailUtils;
import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;

public class BanUserAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req, HttpServletResponse res, Logger logger) {

		UserDAO userDao = new UserDAOImpl();
		
		String reasons = req.getParameter("reasons");		
		if (reasons == null) {
			reasons = "No reason";
		}
		
		int userId = Integer.parseInt(req.getParameter("ban_uid"));
		try {
			UserDTO user = userDao.getUserById(userId);
			
			userDao.banUser(userId);
			
			try {
				MailSenderService mailsender = MailSenderServiceFactory.getMailSenderService();
				StringBuffer text = new StringBuffer();
				text.append("Hi "+user.getFirstName()+",\nYou have been banned for the following actions:\n");
				text.append(reasons+"\nDont appeal");
				mailsender.sendMail(MailUtils.OUR_EMAIL, user.getEmail(), "You are Banned", text);
			} catch (ServiceLocatorException e) {
				e.printStackTrace();
			}
			
			// Halt all there auctions
			AuctionDAO auctionDao = new AuctionDAOImpl();
			List<AuctionDTO> auctions = auctionDao.getUserActiveAuctions(userId);
			for (AuctionDTO auction: auctions) {
				auctionDao.closeAuction(auction.getAid());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// TODO - where to go?? broo
		return "admin.jsp";
	}

}
