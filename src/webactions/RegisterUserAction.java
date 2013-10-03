package webactions;

import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import model.ForRegistration;
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
		
		UserDTO user = new UserDTO();
		
		user.setActivated(false);
		user.setBanned(false);
		user.setEmail(regoData.getEmail());
		user.setFirstName(regoData.getFirstName());
		user.setLastName(regoData.getLastName());
		user.setNickname(regoData.getNickName());
		user.setPassword(regoData.getPassword());
		user.setUsername(regoData.getUsername());
		try{
			SimpleDateFormat DateFormat = new SimpleDateFormat("dd/mm/yyyy");
			Date parsed = DateFormat.parse(regoData.getBirthDate());
			java.sql.Date sql = new java.sql.Date(parsed.getTime());
			user.setYearOfBirth(sql);
			
		}catch(ParseException pe){
			pe.printStackTrace();
		}
		
		user.setCreditCardNum(regoData.getCreditCardNum());
		
		String checksum = DBUtils.calculateMD5(user.getEmail());
		user.setCheckSum(checksum);
		
		
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<UserDTO>> violations = validator.validate(user);
		
		
			//TODO: Set customValidations if emails & usernames are to be unique 
			if(customValidations(regoData)){
				resultMap.put("success", false);
				resultMap.put("message", "Username already taken.");
				resultMap.put("redirect", "controller?action=login");
			
			}else if(violations.size() > 0){
				//Returns error message for violations detected
				StringBuffer message = new StringBuffer();
				message.append("Could not process registration, you had invalid inputs of: ");
				Iterator<ConstraintViolation<UserDTO>> it = violations.iterator();
				while(it.hasNext()){
					message.append("<br/>" + it.next().getMessage());
				}
				resultMap.put("success", false);
				resultMap.put("message", message.toString());
				resultMap.put("redirect", "controller?action=login");
				
			}else{
				UserDAO userdao = new UserDAOImpl();
				try {
					userdao.addUser(user);
					
					//TODO: remove debug script
					System.out.println("inside email activation link:\n"+
							"http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath() +
							    "/controller?action=activation&username="+user.getUsername()+"&checksum=" + user.getCheckSum());
					
					
					MailSenderService mailsender = MailSenderServiceFactory.getMailSenderService();
					
					mailsender.sendMail(MailUtils.OUR_EMAIL, 
										user.getEmail(), 
										"Welcome" + user.getFirstName(), 
										(new StringBuffer()).append("Welcome " + user.getFirstName() + " " + user.getLastName() + ",\n" +
																	"Click on the following link to activate your account:\n" +
																    "<a href=\"http://"+req.getServerName()+":"+req.getServerPort()+req.getContextPath() +
																    "/controller?action=activation&username="+user.getUsername()+"&checksum=" + user.getCheckSum() + "\">Activation link</a>"));
				
				} catch (ServiceLocatorException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				resultMap.put("success", true);
				resultMap.put("message", "Confirmation Email has been sent to you, please activate and re-login");
				resultMap.put("redirect", "controller?action=login");
			}
		
		
		// TODO - return the next page
		return resultMap;
	}
	
	private boolean customValidations(ForRegistration regoData){
		boolean invalid = false;
		UserDAO userdao = new UserDAOImpl();
		
		try {
			int result = userdao.getNumUserByUserName(regoData.getUsername());
			if(result > 0){
				invalid = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return invalid;
	}

}
