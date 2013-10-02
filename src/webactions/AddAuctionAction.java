package webactions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;

public class AddAuctionAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		int userId = LoginUtils.getUserId(req);
		UserDAO userDao = new UserDAOImpl();
		try {
			UserDTO user = userDao.getUserById(userId);
			
			if (user == null || user.getBanned()) {
				// Do nothing i guess?
			} else {
				AuctionDTO auc = new AuctionDTO();
				
				// TODO - fix this shit up with parameter from request
				auc.setAuctionCategory("electronics");
				auc.setAuctionCloseTime(new Timestamp(2010, 5, 13, 4, 34, 2, 0));
				auc.setAuctionDescription("blah batteries");
				auc.setAuctionHalt(false);
				auc.setAuctionImage("blah");
				auc.setAuctionOwnerId(2);
				auc.setAuctionPostageDetails("somewhere rd");
				auc.setAuctionReservePrice(20);
				auc.setAuctionStartTime(new Timestamp(2010, 4, 13, 4, 34, 2, 0));
				auc.setAuctionTitle("batteries");
				auc.setBiddingIncrement(1);
				auc.setBiddingStartPrice(10);
				
				AuctionDAO aucdao = new AuctionDAOImpl();
				try {
					aucdao.addAuction(auc);
				} catch (SQLException e) {
					e.printStackTrace();
					// TODO - sort out handling of this exception
					return "error.jsp";
				}
				
				return "selling.jsp";
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return "index.jsp";
	}
}