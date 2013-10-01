package webactions;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebActionPost {
	
	String executePost(HttpServletRequest req, HttpServletResponse res, Logger logger);
	
}
