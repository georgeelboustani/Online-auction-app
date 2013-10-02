package webactions;

import javax.servlet.http.HttpServletRequest;

public class LoginUtils {

	public static int getUserId(HttpServletRequest req) {
		// TODO - get the user's id from the session
		return 0;
	}
	
	public static void setUserId(HttpServletRequest req, int userId) {
		// TODO - save the users id in the session
	}
	
	public static boolean isNewUserSession(HttpServletRequest req) {
		// TODO - return appropriate
		return false;
	}
}
