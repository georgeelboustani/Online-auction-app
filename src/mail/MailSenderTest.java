package mail;

import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.Session;
import javax.mail.URLName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import exceptions.ServiceLocatorException;
import exceptions.MailSenderException;

import junit.framework.TestCase;

public class MailSenderTest extends TestCase {

	static Logger logger = Logger.getLogger(MailSenderTest.class.getName());
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// To test JNDI outside Tomcat, we need to set these
		 // values manually ... (just for testing purposes)
		 System.setProperty(Context.INITIAL_CONTEXT_FACTORY,
            "org.apache.naming.java.javaURLContextFactory");
        System.setProperty(Context.URL_PKG_PREFIXES, 
            "org.apache.naming");            

        // Create InitialContext with java:/comp/env/mail
        InitialContext ic = new InitialContext();

        ic.createSubcontext("java:");
        ic.createSubcontext("java:comp");
        ic.createSubcontext("java:comp/env");
        ic.createSubcontext("java:comp/env/mail");
       
        //Construct a naming reference
        Reference ref = new Reference("javax.mail.Session", "org.apache.naming.factory.MailSessionFactory", null);
        ref.add(new StringRefAddr("auth", "Container"));
        //You can also use other smtp providers
        ref.add(new StringRefAddr("mail.smtp.host","smtp.gmail.com"));
        ref.add(new StringRefAddr("mail.smtp.auth","true"));
        //Replace as necessary
        ref.add(new StringRefAddr("mail.smtp.user","george_el_boustani@hotmail.com"));
        ref.add(new StringRefAddr("mail.smtp.password","PASSWORD"));
        //Turn this off to avoid username and password appearing in log files.
        ref.add(new StringRefAddr("mail.debug","false"));
        ref.add(new StringRefAddr("mail.smtp.starttls.enable","true"));
        
        ic.bind("java:comp/env/mail/Session", ref);
	}
	
	public void testSession(){
		MailSenderService sender = null;
		try {
			sender = MailSenderServiceFactory.getMailSenderService();
			assertNotNull(sender);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
			fail("Could not obtain session");
		}
	}
	
	public void testSendMessage(){
		try{
			//Replace as necessary
			String fromAddress = "george_el_boustani@hotmail.com";
			String toAddress = "george_el_boustani@hotmail.com";
			String subject = "test";
			StringBuffer mailBody = new StringBuffer();
			mailBody.append("Dear Mailer, Why you email me?!!");
			MailSenderServiceFactory.getMailSenderService().sendMail(fromAddress, toAddress, subject, mailBody);
			assertTrue(true);
 		}catch(Exception e){
			e.printStackTrace();
			fail("MailSender did not send message");
		}
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		InitialContext ic = new InitialContext();
		Context subContext = (Context) ic.lookup("java:comp");
		logger.info("Found sub-context");
		subContext.destroySubcontext("/env/mail");
		ic.destroySubcontext("java:comp");
		ic.destroySubcontext("java:");
	}

}