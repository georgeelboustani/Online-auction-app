package webactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.DBConnectionFactory;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import model.ForLogin;

import com.google.gson.Gson;

import exceptions.ServiceLocatorException;

public class LoginAction implements WebAction {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		// TODO - we need to verify the user
		logger.info("WebAction => Doing Authentication");
		
		String jsonData = req.getParameter("data");
		Gson gson = new Gson();
		ForLogin loginData = gson.fromJson(jsonData, ForLogin.class);
	    System.out.println(loginData.getUserName() + ", " + loginData.getPassword() + ", " + loginData.getRememberMe());
		
	    UserDAO userDAOImpl = new UserDAOImpl();
	    boolean result = false;
	    
	    try {
	    	result = userDAOImpl.authenticateLogin(loginData.getUserName(), loginData.getPassword());
	    } catch (SQLException sqle) {
			sqle.printStackTrace();
			return "error.jsp";
		}
	    
	    System.out.println("LOGIN RESULT: " + result);
	    
		return "index.jsp";
	}
	
}
