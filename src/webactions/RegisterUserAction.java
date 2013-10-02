package webactions;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import exceptions.ServiceLocatorException;
import mail.MailSenderService;
import mail.MailSenderServiceFactory;
import model.ForLogin;
import model.ForRegistration;
import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.DBUtils;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;


public class RegisterUserAction implements WebActionAjax {
	
	@Override
	public Map<String, Object> executeAjax(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		logger.info("WebAction => Doing User Registration");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String jsonData = req.getParameter("data");
		Gson gson = new Gson();
		ForRegistration regoData = gson.fromJson(jsonData, ForRegistration.class);
		
		if(!validateRegistration(regoData, resultMap)){
			
			return resultMap;
		}
		

		UserDTO user = new UserDTO();
		
		// TODO - parameterise these set's according to the request
		user.setActivated(false);
//		user.setAvatar("path to pic/" + user.getUid()); // No image yet
		user.setBanned(false);
		user.setEmail(regoData.getEmail());
		user.setFirstName(regoData.getFirstName());
		user.setLastName(regoData.getLastName());
		user.setNickname(regoData.getNickName());
		user.setPassword(regoData.getPassword());
		user.setUsername(regoData.getUsername());
		
		user.setYearOfBirth(new Date(2010, 7, 22));
		
		UserDAO userdao = new UserDAOImpl();
		try {
			userdao.addUser(user);
			
			// TODO - MAIL fix this up so it works
			System.out.println(req.getLocalAddr());
			
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
			return resultMap;
		} catch (ServiceLocatorException e) {
			// TODO what to do if cant send email???
			e.printStackTrace();
		}
		
		// TODO - return the next page
		return resultMap;
	}
	
	private boolean validateRegistration(ForRegistration regoData, Map<String, Object> resultMap){
		boolean pass = true;
//		String msg = "";
//		Pattern wordPattern = Pattern.compile("^[a-zA-Z_0-9]*$");
//		
//		if(regoData.getUsername().contains(" ") || regoData.getUsername().equals("") 
//				|| wordPattern.matcher(regoData.getUsername()).find()){
//			pass = false;
//		}else{
//			
//		}
//		
//		
//		
//		resultMap.put("success", false);
//		resultMap.put("message", "An SQLException has occured...");
//		resultMap.put("redirect", "error.jsp");
		
		return false;
	}

}
