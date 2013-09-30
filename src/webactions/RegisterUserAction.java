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
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;

public class RegisterUserAction implements WebAction {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		UserDTO user = new UserDTO();
		
		// TODO - parameterise these set's according to the request
		user.setActivated(true);
		user.setAvatar("asdas");
		user.setBanned(false);
		user.setEmail("blah@hotmail.com");
		user.setFirstName("tom");
		user.setLastName("smith");
		user.setNickname("t");
		user.setPassword("pass");
		user.setUsername("tsmith");
		user.setYearOfBirth(new Date(2010, 7, 22));
		
		UserDAO userdao = new UserDAOImpl();
		try {
			userdao.addUser(user);
			
			// TODO - MAIL fix this up so it works
			MailSenderService mailsender = MailSenderServiceFactory.getMailSenderService();
			mailsender.sendMail("Our email", 
								user.getEmail(), 
								"Welcome" + user.getFirstName(), 
								(new StringBuffer()).append("Welcome Message name etc" + "activation link"));
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
