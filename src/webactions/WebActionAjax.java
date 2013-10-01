package webactions;

import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebActionAjax {
	
	Map<String, Object> executeAjax(HttpServletRequest req, HttpServletResponse res, Logger logger);
	
}
