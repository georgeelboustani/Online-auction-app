package auctions;

import java.util.logging.Logger;

import exceptions.AuctionMonitorException;
import exceptions.MailSenderException;
import exceptions.ServiceLocatorException;

public class AuctionMonitorPoolFactory {
	
	static Logger logger = Logger.getLogger(AuctionMonitorPoolFactory.class.getName());
	private static AuctionMonitorPoolFactory factory = null;
	private static AuctionMonitorPool auctionMonitor;
	
	private AuctionMonitorPoolFactory() {
			auctionMonitor = new AuctionMonitorPool();
			logger.info("Auction monitor found: " + auctionMonitor);
	}
	
	public static AuctionMonitorPool getAuctionMonitorService() {
		if(factory==null)
			factory = new AuctionMonitorPoolFactory();
		
		return auctionMonitor;
	}

}