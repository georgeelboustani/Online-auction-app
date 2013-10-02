package webactions;

import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.UserDAO;
import jdbc.UserDAOImpl;

public class UserActivationAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req,
			HttpServletResponse res, Logger logger) {
		
		String username = req.getParameter("username");
		String checkSum = req.getParameter("checksum");
		
		UserDAO userdao = new UserDAOImpl();
		try {
			userdao.activateUser(username, checkSum);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return "index.jsp";
	}

}
