package webactions;

import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.ServiceLocatorException;
import mail.MailSenderService;
import mail.MailSenderServiceFactory;
import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.DBUtils;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;

public class UpdateUserAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		UserDTO user = new UserDTO();
		
		// TODO - fix these parameter names in case there wrong
		// TODO - VALIDATE this shit especially dates etc
		user.setActivated(true);
		user.setBanned(false);
		user.setEmail(req.getParameter("email"));
		user.setFirstName(req.getParameter("firstname"));
		user.setLastName(req.getParameter("lastname"));
		user.setNickname(req.getParameter("nickname"));
		user.setPassword(req.getParameter("password"));
		user.setUsername(req.getParameter("email"));
		user.setYearOfBirth(new Date(Integer.parseInt(req.getParameter("year")), 
									 Integer.parseInt(req.getParameter("month")), 
									 Integer.parseInt(req.getParameter("day"))));
		
		// TODO - some how set the user's id based on the session
		// user.setUserID(session.blahblah);
		
		UserDAO userdao = new UserDAOImpl();
		try {
			userdao.updateUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO - sort out handling of this exception
			return "error.jsp";
		}
		
		// TODO - return the next page
		return "login.jsp";
	}

}
