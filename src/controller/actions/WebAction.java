package controller.actions;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebAction {
	
	RequestDispatcher execute(HttpServletRequest req, HttpServletResponse res);

}
