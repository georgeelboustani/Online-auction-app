package mail;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import exceptions.ServiceLocatorException;
import mail.MailSender;
import exceptions.MailSenderException;

class MailTask implements Runnable{
	
	String fromAddress;
	String toAddress;
	String mailSubject; 
	StringBuffer text;
	MailSender sender;
	
	public MailTask(String fromAddress, String toAddress, String mailSubject,
			StringBuffer text, MailSender sender) {
		super();
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.mailSubject = mailSubject;
		this.text = text;
		this.sender = sender;
	}

	@Override
	public void run() {
		
		try {
			sender.sendMessage(fromAddress, toAddress, mailSubject, text);
		} catch (AddressException e) {
			Logger.getAnonymousLogger().severe("Sending mail from "+fromAddress+" to "+toAddress+" failed");
			Logger.getAnonymousLogger().severe(e.getLocalizedMessage());
		} catch (MessagingException e) {
			Logger.getAnonymousLogger().severe("Sending mail from "+fromAddress+" to "+toAddress+" failed");
			Logger.getAnonymousLogger().severe(e.getLocalizedMessage());
		}
		
	}
	
}

public class MailSenderService {
	
	static Logger logger = Logger.getLogger(MailSenderService.class.getName());
	
	private static MailSender mailSender;
	private ThreadPoolExecutor executor;
	
	public MailSenderService() throws MailSenderException, ServiceLocatorException{
		BlockingQueue<Runnable> mailQueue = new ArrayBlockingQueue<Runnable>(10);
		executor = new ThreadPoolExecutor(5, 20, 20, TimeUnit.SECONDS, mailQueue);
		mailSender = new MailSender();
	}
	
	public void sendMail(String fromAddress, String toAddress, String mailSubject,
			StringBuffer text){
		
		MailTask task = new MailTask(fromAddress, toAddress, mailSubject, text, mailSender);
		executor.execute(task);
		
	}

}
