package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exceptions.ServiceLocatorException;

public class AuctionDAOImpl implements AuctionDAO {

	@Override
	public void addAuction(AuctionDTO auction) {
		// TODO finish adding auction
		Connection con;
		try {
			con = DBConnectionFactory.getConnection();
			// TODO - im assuming here the id is automatically generated on insert,
			//      - if not, add an extra parameter at the start
			
			PreparedStatement updateAuc = con.prepareStatement("INSERT into " + DBUtils.AUCTION_TABLE + 
															   " (auction_owner_uid,auction_title,auction_category,"
															   + "auction_image,auction_description,"
															   + "auction_postage_details,auction_reserve_price,"
															   + "bidding_start_price,bidding_increment,"
															   + "auction_start_time,auction_close_time,auction_halt)" +
															   " values(?,?,?,?,?,?,?,?,?,?,?,?)");
			
			updateAuc.setInt(1,auction.getAuctionOwnerId());
			updateAuc.setString(2,auction.getAuctionTitle());
			updateAuc.setString(3,auction.getAuctionCategory());
			updateAuc.setString(4,auction.getAuctionImage());
			updateAuc.setString(5,auction.getAuctionDescription());
			updateAuc.setString(6,auction.getAuctionPostageDetails());
			updateAuc.setDouble(7,auction.getAuctionReservePrice());
			updateAuc.setDouble(8,auction.getBiddingStartPrice());
			updateAuc.setDouble(9,auction.getBiddingIncrement());
			updateAuc.setTimestamp(10,auction.getAuctionStartTime());
			updateAuc.setTimestamp(11,auction.getAuctionCloseTime());
			updateAuc.setBoolean(12,auction.getAuctionHalt());
			
			updateAuc.executeUpdate();      
			
			con.close();
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		}
	}

	@Override
	public AuctionDTO getAuctionById(int auctionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuctionDTO getAuctionByItemType(String itemType) {
		// TODO Auto-generated method stub
		return null;
	}

}
