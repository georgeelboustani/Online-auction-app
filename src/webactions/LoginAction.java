package webactions;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.DBConnectionFactory;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import model.ForLogin;

import com.google.gson.Gson;

import exceptions.ServiceLocatorException;

public class LoginAction implements WebActionAjax {

	@Override
	public Map<String, Object> executeAjax(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		// TODO - we need to verify the user
		logger.info("WebAction => Doing Authentication");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String jsonData = req.getParameter("data");
		Gson gson = new Gson();
		ForLogin loginData = gson.fromJson(jsonData, ForLogin.class);
	    
	    UserDAO userDAOImpl = new UserDAOImpl();
	    int resultUID = -1;
	    
	    try {
	    	resultUID = userDAOImpl.authenticateLogin(loginData.getUserName(), loginData.getPassword());
	    } catch (SQLException sqle) {
			sqle.printStackTrace();
			resultMap.put("error", true);
			resultMap.put("message", "An SQLException has occured...");
			resultMap.put("redirect", "error.jsp");
			
			return resultMap;
		}
	    
	    if(resultUID > -1){
	    	//start user session
	    	HttpSession sess = req.getSession(true);
	    	sess.setAttribute("user_uid", resultUID);
	    	
	    	//success message
	    	resultMap.put("success", true);
			resultMap.put("message", "login successful");
			resultMap.put("redirect", "index.jsp"); //login sucessful, go to index home page
			return resultMap; 
	    }else{
	    	resultMap.put("success", false);
	    	resultMap.put("message", "login unsuccessful");
			resultMap.put("redirect", "login.jsp"); //login sucessful, go to index home page
	    	return resultMap; //login unsuccessful, go back to login page
	    }
	    
	}

	
	
}
