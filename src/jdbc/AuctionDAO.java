package jdbc;

import java.util.List;

public interface AuctionDAO {
	
	void addAuction(AuctionDTO auction);
	
	AuctionDTO getAuctionById(int auctionId);
	
	List<AuctionDTO> getAuctionByItemType(String itemType);
	
	// TODO - Add more shit we need from the auction table
	
}
