package mail;

import java.util.logging.Logger;
import exceptions.MailSenderException;
import exceptions.ServiceLocatorException;

public class MailSenderServiceFactory {
	
	static Logger logger = Logger.getLogger(MailSenderServiceFactory.class.getName());
	private static MailSenderServiceFactory factory = null;
	private static MailSenderService mailsender;
	
	private MailSenderServiceFactory() throws ServiceLocatorException{
		try{
			mailsender = new MailSenderService();
			logger.info("Mail sender service found: " + mailsender);
		}catch(MailSenderException e){
			logger.severe("Cannot find mail sender, throwing exception"+e.getMessage());
			e.printStackTrace();
			throw new ServiceLocatorException();
		}
	}
	
	// For initialisation at web app startup. called in controller constructor
	public static void init() throws ServiceLocatorException {
		if(factory==null)
			factory = new MailSenderServiceFactory();
	}
	
	public static MailSenderService getMailSenderService() throws ServiceLocatorException {
		if(factory==null)
			factory = new MailSenderServiceFactory();
		
		return mailsender;
	}

}