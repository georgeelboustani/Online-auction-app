package webactions;

import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebAction {
	
	RequestDispatcher execute(HttpServletRequest req, HttpServletResponse res, Logger logger);

}
