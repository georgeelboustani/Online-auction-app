package webactions;

import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements WebAction {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		// TODO - we need to verify the user
		logger.info("Doing Login");
		return "index.jsp";
	}
	
}
