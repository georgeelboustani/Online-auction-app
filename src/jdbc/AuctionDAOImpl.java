package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		AuctionDTO auction = new AuctionDTO();
		Connection con;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.AUCTION_TABLE
															 + " WHERE ?=?");
			userQuery.setString(1,DBUtils.AUC_ID);
			userQuery.setInt(2,auctionId);
			
			ResultSet rs = userQuery.executeQuery();
			rs.next();
			
			auction = generateAuctionDTO(rs);
			
			con.close();
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		}
		
		return auction;
	}

	@Override
	public List<AuctionDTO> getAuctionByItemType(String auctionCategory) {
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		Connection con;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.AUCTION_TABLE
															 + " WHERE ?=?");
			userQuery.setString(1,DBUtils.AUC_CATEGORY);
			userQuery.setString(2,auctionCategory);
			
			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				auctions.add(generateAuctionDTO(rs));
			}
			
			con.close();
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		}
		
		return auctions;
	}
	
	private AuctionDTO generateAuctionDTO(ResultSet rs) throws SQLException {
		AuctionDTO auction = new AuctionDTO();
		
		auction.setAuctionOwnerId(rs.getInt(DBUtils.AUC_OWNER_ID));
		auction.setAuctionTitle(rs.getString(DBUtils.AUC_TITLE));
		auction.setAuctionCategory(rs.getString(DBUtils.AUC_CATEGORY));
		auction.setAuctionImage(rs.getString(DBUtils.AUC_IMAGE));
		auction.setAuctionDescription(rs.getString(DBUtils.AUC_DESC));
		auction.setAuctionPostageDetails(rs.getString(DBUtils.AUC_POSTAGE));
		auction.setAuctionReservePrice(rs.getDouble(DBUtils.AUC_RESERVE));
		auction.setBiddingStartPrice(rs.getDouble(DBUtils.AUC_START));
		auction.setBiddingIncrement(rs.getDouble(DBUtils.AUC_BID_INCREMENT));
		auction.setAuctionStartTime(rs.getTimestamp(DBUtils.AUC_START));
		auction.setAuctionCloseTime(rs.getTimestamp(DBUtils.AUC_CLOSE));
		auction.setAuctionHalt(rs.getBoolean(DBUtils.AUC_HALT));
		
		return auction;
	}

}
