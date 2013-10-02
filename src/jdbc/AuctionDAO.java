package jdbc;

import java.sql.SQLException;
import java.util.List;

public interface AuctionDAO {
	
	void addAuction(AuctionDTO auction) throws SQLException;
	
	AuctionDTO getAuctionById(int auctionId) throws SQLException;
	
	List<AuctionDTO> getActiveAuctionByCategory(String category) throws SQLException;
	
	List<AuctionDTO> getAllAuctions() throws SQLException;
	
	List<String> getAuctionCategories() throws SQLException;
	
	void closeAuction(int aucId) throws SQLException;

	List<AuctionDTO> wordSearchAuctionsByDescription(String searchString, boolean ascending) throws SQLException;
	
	// TODO - Add more shit we need from the auction table
	
}
