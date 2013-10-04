package webactions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pagebeans.SellingBean;
import jdbc.AuctionBidDAO;
import jdbc.AuctionBidDAOImpl;
import jdbc.AuctionBidDTO;
import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;

public class ViewSellingAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req, HttpServletResponse res, Logger logger) {
		
		AuctionDAO auctionDao = new AuctionDAOImpl();
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		int userId = LoginUtils.getUserId(req);
		try {
			auctions = auctionDao.getUserActiveAuctions(userId);
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
					bids.add(auc.getBiddingStartPrice());
				} else {
					bids.add(bid.getBidAmount());
				}
			} catch (SQLException e) {
				bids.add(auc.getBiddingStartPrice());
			}
		}
		
		SellingBean sellingBean = new SellingBean();
		if (auctions.size() == 0) {
			sellingBean.setDisplayError(true);
			sellingBean.setErrorMessage("Sorry we can't find any auctions owned by you");
		} else {
			sellingBean.setAuctions(auctions);
			sellingBean.setBids(bids);
			sellingBean.setTimes(times);
			sellingBean.setDisplayError(false);
		}	
		req.getSession().setAttribute("sellingBean", sellingBean);
		
		return "selling.jsp";
	}

}