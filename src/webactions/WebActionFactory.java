package webactions;

import java.util.HashMap;
import java.util.logging.Logger;

import jdbc.DBConnectionFactory;

public class WebActionFactory {
	static Logger logger = Logger.getLogger(DBConnectionFactory.class.getName());
	
	private static WebActionFactory factory = null;
	private static HashMap<String,WebActionGP> gpActionMap;
	private static HashMap<String,WebActionAjax> ajaxActionMap;
	
	private WebActionFactory() {
		// TODO - add more action mappings
		gpActionMap = new HashMap<String, WebActionGP>();
		gpActionMap.put("addAuction", new AddAuctionAction());
		gpActionMap.put("updateUser", new UpdateUserAction());
		gpActionMap.put("login", new LoginAction());
		gpActionMap.put("logout", new LogoutAction());
		gpActionMap.put("searchAuction", new SearchAction());
		gpActionMap.put("viewAuction", new ViewAuctionAction());
		gpActionMap.put("viewMyBid", new ViewMyBidAction());
		gpActionMap.put("viewSelling", new ViewSellingAction());
		gpActionMap.put("banUser", new BanUserAction());
		gpActionMap.put("haltAuction", new HaltAuctionAction());

		// TODO - add more action mappings
		ajaxActionMap = new HashMap<String, WebActionAjax>();
		ajaxActionMap.put("authenticate", new AuthenticateAction());
		ajaxActionMap.put("addUser", new RegisterUserAction());
		ajaxActionMap.put("placeBid", new PlaceBidAction());
	}
	
	// Get the Get or Post action
	public static WebActionGP getGPAction(String action) {
		if(factory==null) {
			factory = new WebActionFactory();
		}
		
		return gpActionMap.get(action);
	}
	
	public static WebActionAjax getAjaxAction(String action) {
		if(factory==null) {
			factory = new WebActionFactory();
		}
		
		return ajaxActionMap.get(action);
	}
}
