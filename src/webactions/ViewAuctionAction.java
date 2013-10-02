package webactions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.AuctionBidDAO;
import jdbc.AuctionBidDAOImpl;
import jdbc.AuctionBidDTO;
import jdbc.AuctionDAO;
import jdbc.AuctionDAOImpl;
import jdbc.AuctionDTO;
import pagebeans.AuctionBean;
import pagebeans.SearchBean;

public class ViewAuctionAction implements WebActionGP {

	@Override
	public String executeAction(HttpServletRequest req,
			HttpServletResponse res, Logger logger) {
		
		if (req.getParameter("auctionId") == null) {
			// TODO - what can we do? error auction doesnt exists probs
		} else {
			AuctionDAO auctionDao = new AuctionDAOImpl();
			
			int auctionId = Integer.parseInt(req.getParameter("auctionId"));
			AuctionDTO auction;
			try {
				auction = auctionDao.getAuctionById(auctionId);

				AuctionBean auctionBean = new AuctionBean();
				
				if (auction == null) {
					auctionBean.setDisplayError(true);
					auctionBean.setErrorMessage("Can't find the auction with id: " + auctionId);
				} else {
					Timestamp closeTime = auction.getAuctionCloseTime();
					Timestamp currentTime = (new Timestamp(System.currentTimeMillis()));
					
					long timeLeft = 0;
					if (closeTime == null) {
						timeLeft = 0;
					} else {
						timeLeft = closeTime.getTime() - currentTime.getTime();
					}
					
					auctionBean.setTimeLeft(timeLeft/(1000*60));
					auctionBean.setDisplayError(false);
					auctionBean.setAuction(auction);
				}
				
				req.getSession().setAttribute("auctionBean", auctionBean);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return "auction.jsp";
	}

}
