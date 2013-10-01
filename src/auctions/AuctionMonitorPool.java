package auctions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import jdbc.AuctionBidDAO;
import jdbc.AuctionBidDAOImpl;
import jdbc.AuctionBidDTO;
import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;
import jdbc.DBUtils;
import jdbc.UserDAO;
import jdbc.UserDAOImpl;
import jdbc.UserDTO;
import exceptions.AuctionMonitorException;
import exceptions.ServiceLocatorException;
import mail.MailSender;
import mail.MailSenderService;
import mail.MailSenderServiceFactory;
import mail.MailUtils;
import exceptions.MailSenderException;

class AuctionTask implements Runnable{
	
	private String category;
	
	public AuctionTask(String category) {
		super();
		this.category = category;
	}

	@Override
	public void run() {
		// TODO - do the monitoring of the open auction's in this category
		Logger.getAnonymousLogger().info("Auction task run for category: " + this.category);
		
		try {
			AuctionDAO aucDao = new AuctionDAOImpl();
			List<AuctionDTO> auctions = aucDao.getActiveAuctionByCategory(category);
			
			for (AuctionDTO auc: auctions) {
				Logger.getAnonymousLogger().info(auc.toString());
				
				Timestamp closeTime = auc.getAuctionCloseTime();
				Timestamp currentTime = (new Timestamp(System.currentTimeMillis()));
				
				if (currentTime.after(closeTime)) {
					aucDao.closeAuction(auc.getAid());
					
					UserDAO userDao = new UserDAOImpl();
					AuctionBidDAO bidDao = new AuctionBidDAOImpl();
					
					UserDTO auctionOwner = userDao.getUserById(auc.getAuctionOwnerId());
					
					AuctionBidDTO highestBid = bidDao.getHighestBid(auc.getAid());
					UserDTO auctionWinner = null;
					if (highestBid != null) {
						auctionWinner = userDao.getUserById(highestBid.getUserId());
					}
					
					MailSenderService mailService = MailSenderServiceFactory.getMailSenderService();
					if (auctionWinner == null) {
						// Send email to auction owner that not successful
						String subject = "Unsuccessful auction: " + auc.getAuctionTitle();
						StringBuffer text = new StringBuffer();
						text.append("Hi " + auctionOwner.getFirstName() + " " + auctionOwner.getLastName() + ",\n");
						text.append("The following auction has just ended, and has no winner:\n");
						text.append(auc.getAid() + " " + auc.getAuctionTitle() + "\n\n");
						text.append("You may relist the item onto the website\n");
						text.append("Yours sincerely,\n");
						text.append("The ROLLBACK team");
						
						mailService.sendMail(MailUtils.OUR_EMAIL, auctionOwner.getEmail(), subject, text);						
					} else {
						double winningBid = highestBid.getBidAmount();
						double reserve = auc.getAuctionReservePrice();
						
						String ownerSubject = "Auction finished: " + auc.getAuctionTitle();
						StringBuffer ownerText = new StringBuffer();
						String winnerSubject = "Auction finished: " + auc.getAuctionTitle();
						StringBuffer winnerText = new StringBuffer();
						
						if (winningBid < reserve) {
							ownerText.append("Hi " + auctionOwner.getFirstName() + " " + auctionOwner.getLastName() + ",\n");
							ownerText.append("The following auction has just ended, but winning bid is less then your reserve:\n");
							ownerText.append(auc.getAid() + " " + auc.getAuctionTitle() + "\n\n");
							ownerText.append("You may choose to decline or accept the bid from your selling page\n");
							ownerText.append("Yours sincerely,\n");
							ownerText.append("The ROLLBACK team");
							
							winnerText.append("Hi " + auctionWinner.getFirstName() + " " + auctionWinner.getLastName() + ",\n");
							winnerText.append("The following auction has just ended, and you were the highest bidder:\n");
							winnerText.append(auc.getAid() + " " + auc.getAuctionTitle() + "\n\n");
							winnerText.append("Your bid was lower then the reserve, and so the auction owner will decide to accept or decline your bid\n");
							winnerText.append("Yours sincerely,\n");
							winnerText.append("The ROLLBACK team");
						} else {
							ownerText.append("Hi " + auctionOwner.getFirstName() + " " + auctionOwner.getLastName() + ",\n");
							ownerText.append("The following auction has just ended:\n");
							ownerText.append(auc.getAid() + " " + auc.getAuctionTitle() + "\n\n");
							ownerText.append("The winner was " + auctionWinner.getFirstName() + " " + auctionWinner.getLastName() + "\n");
							ownerText.append("You may contact the user in order to make arrangements\n");
							ownerText.append("Yours sincerely,\n");
							ownerText.append("The ROLLBACK team");
							
							winnerText.append("Hi " + auctionWinner.getFirstName() + " " + auctionWinner.getLastName() + ",\n");
							winnerText.append("The following auction has just ended, and you are the winner:\n");
							winnerText.append(auc.getAid() + " " + auc.getAuctionTitle() + "\n\n");
							winnerText.append("We will now deduct $" + winningBid + " from your account. The auction owner will send you the item\n");
							winnerText.append("Yours sincerely,\n");
							winnerText.append("The ROLLBACK team");
						}
						mailService.sendMail(MailUtils.OUR_EMAIL, auctionOwner.getEmail(), ownerSubject, ownerText);
						mailService.sendMail(MailUtils.OUR_EMAIL, auctionWinner.getEmail(), winnerSubject, winnerText);
					}
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

public class AuctionMonitorPool implements Runnable {
	
	static Logger logger = Logger.getLogger(AuctionMonitorPool.class.getName());
	
	private ThreadPoolExecutor executor;
	private boolean stopMonitoring = false;
	
	public AuctionMonitorPool() {
		BlockingQueue<Runnable> auctionQueue = new ArrayBlockingQueue<Runnable>(10);
		executor = new ThreadPoolExecutor(5, 20, 20, TimeUnit.SECONDS, auctionQueue);
	}
	
	private void monitorAuctions(String category){
		AuctionTask task = new AuctionTask(category);
		executor.execute(task);
	}

	@Override
	public void run() {
		logger.info("Auction monitor started succesfully");
		while (!stopMonitoring) {
			try {
				List<String> categories = (new AuctionDAOImpl()).getAuctionCategories();
				for (String category: categories) {
					monitorAuctions(category);
				}
			} catch (SQLException e1) {
				logger.severe("Could not connect to database and get categories");
			}

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stopMonitoring() {
		stopMonitoring = true;
	}
}
