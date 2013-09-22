package webactions;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements WebAction {

	@Override
	public RequestDispatcher execute(HttpServletRequest req, HttpServletResponse res) {
		// TODO - we need to verify the user
		return req.getRequestDispatcher("index.jsp");		
	}
	
}
