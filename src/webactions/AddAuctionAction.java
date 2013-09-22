package webactions;

import java.sql.Timestamp;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;

public class AddAuctionAction implements WebAction {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		AuctionDTO auc = new AuctionDTO();
		
		// TODO - replace all the constant parameters with the values from the request parameters. hopefully there all there :s
		auc.setAuctionCategory("electronics");
		auc.setAuctionCloseTime(new Timestamp(2010, 5, 13, 4, 34, 2, 0));
		auc.setAuctionDescription("blah batteries");
		auc.setAuctionHalt(false);
		auc.setAuctionImage("blah");
		auc.setAuctionOwnerId(2);
		auc.setAuctionPostageDetails("somewhere rd");
		auc.setAuctionReservePrice(20);
		auc.setAuctionStartTime(new Timestamp(2010, 4, 13, 4, 34, 2, 0));
		auc.setAuctionTitle("batteries");
		auc.setBiddingIncrement(1);
		auc.setBiddingStartPrice(10);
		
		AuctionDAO aucdao = new AuctionDAOImpl();
		aucdao.addAuction(auc);
		
		// TODO - return the next page
		return "index.jsp";
	}

}
