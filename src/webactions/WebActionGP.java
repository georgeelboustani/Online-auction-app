package webactions;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebActionGP {
	
	String executeAction(HttpServletRequest req, HttpServletResponse res, Logger logger);
	
}
