package jdbc;

public interface AuctionDAO {
	
	void addAuction(AuctionDTO auction);
	
	AuctionDTO getAuctionById(int auctionId);
	
	AuctionDTO getAuctionByItemType(String itemType);
	
	// TODO - Add more shit we need from the auction table
	
}
