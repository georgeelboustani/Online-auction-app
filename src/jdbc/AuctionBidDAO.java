package jdbc;

import java.sql.SQLException;
import java.util.List;

public interface AuctionBidDAO {
	
	List<AuctionBidDTO> getAuctionBids(int auctionId) throws SQLException;
	
	AuctionBidDTO getHighestBid(int auctionId) throws SQLException;
	
	// TODO - add more functions we need from the bid table
	
}
