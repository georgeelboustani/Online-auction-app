package webactions;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req, HttpServletResponse res,
			Logger logger) {
		
		System.out.println("ITS LOGGING OUT");
		HttpSession session = req.getSession(false);
		session.invalidate();
		
		return "login.jsp";
	}

}
