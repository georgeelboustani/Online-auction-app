package webactions;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements WebActionGP{

	@Override
	public String executeAction(HttpServletRequest req,
			HttpServletResponse res, Logger logger) {
		return "login.jsp";
	}

}
