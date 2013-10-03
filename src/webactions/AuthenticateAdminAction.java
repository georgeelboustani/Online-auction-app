package webactions;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;
import model.ForLogin;

import com.google.gson.Gson;

public class AuthenticateAdminAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		logger.info("WebAction => Doing Admin Authentication");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
			    
	    UserDAO userDAOImpl = new UserDAOImpl();
	    int resultUID = -1;
	    
	    try {
	    	UserDTO user = userDAOImpl.getUserByUserName(username);
	    	if (user == null || !user.getIsAdmin()) {
	    		return "login.jsp";
	    	}
	    	
	    	resultUID = userDAOImpl.authenticateLogin(username,password);
	    	
	    	if(resultUID > -1){
		    	//start user session
		    	HttpSession sess = req.getSession(true);
		    	sess.setAttribute("user_uid", resultUID);

		    	return "index.jsp"; 
		    }else{
		    	return "login.jsp"; //login unsuccessful, go back to login page
		    }
	    	
	    } catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	    
	    return "index.jsp";
	}

}
