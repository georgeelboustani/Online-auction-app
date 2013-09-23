package jdbc;

import java.sql.SQLException;
import java.util.List;

public interface AuctionDAO {
	
	void addAuction(AuctionDTO auction) throws SQLException;
	
	AuctionDTO getAuctionById(int auctionId) throws SQLException;
	
	List<AuctionDTO> getAuctionByItemType(String itemType) throws SQLException;
	
	List<AuctionDTO> getAllAuctions() throws SQLException;
	
	// TODO - Add more shit we need from the auction table
	
}
