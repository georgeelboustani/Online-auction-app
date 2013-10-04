package webactions;

import java.sql.SQLException;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;

public class UserViewProfileAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req,
			HttpServletResponse res, Logger logger) {
		
		int userID = LoginUtils.getUserId(req);
		UserDAO userDao = new UserDAOImpl();
		
		try{
			UserDTO user = userDao.getUserById(userID);
			if (user == null) {
				req.getSession().setAttribute("displayError", true);
				req.getSession().setAttribute("errorMessage", "Can't find the user with id: " + userID);
			} else {
				req.getSession().setAttribute("userDTO", user);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "profile.jsp";
	}

}
