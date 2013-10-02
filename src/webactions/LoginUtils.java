package webactions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginUtils {

	public static int getUserId(HttpServletRequest req) {
		return (Integer) req.getSession(false).getAttribute("user_uid");
	}
	
	public static void setUserId(HttpServletRequest req, int userId) {
		req.getSession(false).setAttribute("user_uid", userId);	
	}
	
	public static boolean isNewUserSession(HttpServletRequest req) {
		return (req.getSession(false).getAttribute("user_uid") != null);
	}
}
