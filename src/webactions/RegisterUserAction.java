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

public class RegisterUserAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		UserDTO user = new UserDTO();
		
		// TODO - parameterise these set's according to the request
		user.setActivated(true);
		user.setAvatar("path to pic/" + user.getUid());
		user.setBanned(false);
		user.setEmail(req.getParameter("email"));
		user.setFirstName(req.getParameter("firstname"));
		user.setLastName(req.getParameter("lastname"));
		user.setNickname(req.getParameter("nickname"));
		user.setPassword(req.getParameter("password"));
		user.setUsername(req.getParameter("email"));
		user.setYearOfBirth(new Date(2010, 7, 22));
		
		UserDAO userdao = new UserDAOImpl();
		try {
			userdao.addUser(user);
			
			// TODO - MAIL fix this up so it works
			MailSenderService mailsender = MailSenderServiceFactory.getMailSenderService();
			mailsender.sendMail("Our email", 
								user.getEmail(), 
								"Welcome" + user.getFirstName(), 
								(new StringBuffer()).append("Welcome " + user.getFirstName() + " " + user.getLastName() + ",\n" +
															"Click on the following link to activate your account:\n" +
														    "<a href=\"http://localhost:8080/webapps/activate.jsp?activation=" + DBUtils.calculateMD5(user.getEmail()) + "\">Activation link</a>"));
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO - sort out handling of this exception
			return "error.jsp";
		} catch (ServiceLocatorException e) {
			// TODO what to do if cant send email???
			e.printStackTrace();
		}
		
		// TODO - return the next page
		return "login.jsp";
	}

}
