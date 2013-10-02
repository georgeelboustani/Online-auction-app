package webactions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pagebeans.SearchBean;
import jdbc.AuctionBidDAO;
import jdbc.AuctionBidDAOImpl;
import jdbc.AuctionBidDTO;
import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;

public class SearchAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req, HttpServletResponse res,
			Logger logger) {
		
		// TODO - make sure this is the parameter name
		String searchString = req.getParameter("searchString");
		if (searchString == null) {
			searchString = "";
		}
		AuctionDAO auctionDao = new AuctionDAOImpl();
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		try {
			auctions = auctionDao.wordSearchAuctionsByDescription(searchString,true);
		} catch (SQLException e) {
			// TODO - make an error message somehow about unable to get auctions
			e.printStackTrace();
		}
		
		List<Long> times = new ArrayList<Long>();
		List<Double> bids = new ArrayList<Double>();
		
		for (AuctionDTO auc: auctions) {
			Timestamp closeTime = auc.getAuctionCloseTime();
			Timestamp currentTime = (new Timestamp(System.currentTimeMillis()));
			
			long timeLeft = 0;
			if (closeTime == null) {
				timeLeft = 0;
			} else {
				timeLeft = closeTime.getTime() - currentTime.getTime();
			}
			
			times.add(timeLeft/(1000*60));
			
			AuctionBidDAO bidDao = new AuctionBidDAOImpl();
			try {
				AuctionBidDTO bid = bidDao.getHighestBid(auc.getAid());
				if (bid == null) {
					bids.add((double) 0);
				} else {
					bids.add(bid.getBidAmount());
				}
			} catch (SQLException e) {
				bids.add((double) 0);
			}
		}
		
		SearchBean search = new SearchBean();
		if (auctions.size() == 0) {
			search.setDisplayError(true);
			search.setErrorMessage("No results found for the given search string: " + searchString);
		} else {
			search.setAuctions(auctions);
			search.setBids(bids);
			search.setTimes(times);
			search.setDisplayError(false);
		}	
		req.getSession().setAttribute("searchBean", search);
		
		return "index.jsp";
	}

}
