package controller.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginAction implements WebAction {

	@Override
	public RequestDispatcher execute(HttpServletRequest req, HttpServletResponse res) {
		// TODO - fix this to do what needs to be done at login
		return req.getRequestDispatcher("index.jsp");		
	}
	
}
