package webactions;

import java.util.HashMap;
import java.util.logging.Logger;

import jdbc.DBConnectionFactory;

public class WebActionFactory {
	static Logger logger = Logger.getLogger(DBConnectionFactory.class.getName());
	
	private static WebActionFactory factory = null;
	private static HashMap<String,WebAction> actionMap;
	
	private WebActionFactory() {
		actionMap = new HashMap<String, WebAction>();
		actionMap.put("login", new LoginAction());
		actionMap.put("addAuction", new AddAuctionAction());
		actionMap.put("addUser", new RegisterUserAction());
		actionMap.put("updateUser", new UpdateUserAction());
		actionMap.put("placeBid", new UpdateUserAction());
		// TODO - add more action mappings
	}
	
	public static WebAction getAction(String action) {
		if(factory==null) {
			factory = new WebActionFactory();
		}
		
		return actionMap.get(action);
	}
}
