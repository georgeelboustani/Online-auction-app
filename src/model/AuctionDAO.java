package model;

public interface AuctionDAO {
	
	AuctionDTO getAuctionById(int auctionId);
	
	AuctionDTO getAuctionByItemType(String itemType);
	
	// Add more shit we need from the auction table
	
}
