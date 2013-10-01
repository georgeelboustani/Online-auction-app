package webactions;

import java.util.HashMap;
import java.util.logging.Logger;

import jdbc.DBConnectionFactory;

public class WebActionFactory {
	static Logger logger = Logger.getLogger(DBConnectionFactory.class.getName());
	
	private static WebActionFactory factory = null;
	private static HashMap<String,WebActionPost> postActionMap;
	private static HashMap<String,WebActionAjax> ajaxActionMap;
	
	private WebActionFactory() {
		postActionMap = new HashMap<String, WebActionPost>();
		postActionMap.put("addAuction", new AddAuctionAction());
		postActionMap.put("addUser", new RegisterUserAction());
		postActionMap.put("updateUser", new UpdateUserAction());
		// TODO - add more action mappings
		
		ajaxActionMap = new HashMap<String, WebActionAjax>();
		ajaxActionMap.put("login", new LoginAction());
	}
	
	public static WebActionPost getPostAction(String action) {
		if(factory==null) {
			factory = new WebActionFactory();
		}
		
		return postActionMap.get(action);
	}
	
	public static WebActionAjax getAjaxAction(String action) {
		if(factory==null) {
			factory = new WebActionFactory();
		}
		
		return ajaxActionMap.get(action);
	}
}
