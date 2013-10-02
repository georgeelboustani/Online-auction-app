package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import exceptions.ServiceLocatorException;

public class AuctionDAOImpl implements AuctionDAO {

	@Override
	public void addAuction(AuctionDTO auction) throws SQLException {
		// TODO finish adding auction
		Connection con = null;
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
			
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
	
	// TODO - test this
	@Override
	public AuctionDTO getAuctionById(int auctionId) throws SQLException {
		AuctionDTO auction = new AuctionDTO();
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.AUCTION_TABLE
															 + " WHERE ?=?");
			userQuery.setString(1,DBUtils.AUC_ID);
			userQuery.setInt(2,auctionId);
			
			ResultSet rs = userQuery.executeQuery();
			rs.next();
			
			auction = generateAuctionDTO(rs);
			
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
		return auction;
	}
	
	// TODO - test this
	@Override
	public List<AuctionDTO> getActiveAuctionByCategory(String auctionCategory) throws SQLException {
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.AUCTION_TABLE
															 + " WHERE " + DBUtils.AUC_CATEGORY + "=? AND auction_halt=?");
			userQuery.setString(1,auctionCategory);
			userQuery.setBoolean(2,false);
			
			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				auctions.add(generateAuctionDTO(rs));
			}
			
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
		return auctions;
	}

	// TODO - test this
	@Override
	public List<AuctionDTO> getAllAuctions() throws SQLException {
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.AUCTION_TABLE);
			
			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				auctions.add(generateAuctionDTO(rs));
			}
			
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
		return auctions;
	}
	
	private AuctionDTO generateAuctionDTO(ResultSet rs) throws SQLException {
		AuctionDTO auction = new AuctionDTO();
		
		auction.setAid(rs.getInt(DBUtils.AUC_ID));
		auction.setAuctionOwnerId(rs.getInt(DBUtils.AUC_OWNER_ID));
		auction.setAuctionTitle(rs.getString(DBUtils.AUC_TITLE));
		auction.setAuctionCategory(rs.getString(DBUtils.AUC_CATEGORY));
		auction.setAuctionImage(rs.getString(DBUtils.AUC_IMAGE));
		auction.setAuctionDescription(rs.getString(DBUtils.AUC_DESC));
		auction.setAuctionPostageDetails(rs.getString(DBUtils.AUC_POSTAGE));
		auction.setAuctionReservePrice(rs.getDouble(DBUtils.AUC_RESERVE));
		auction.setBiddingStartPrice(rs.getDouble(DBUtils.AUC_BIDDING_START));
		auction.setBiddingIncrement(rs.getDouble(DBUtils.AUC_BID_INCREMENT));
		auction.setAuctionStartTime(rs.getTimestamp(DBUtils.AUC_START));
		auction.setAuctionCloseTime(rs.getTimestamp(DBUtils.AUC_CLOSE));
		auction.setAuctionHalt(rs.getBoolean(DBUtils.AUC_HALT));
		
		return auction;
	}

	@Override
	public List<String> getAuctionCategories() throws SQLException {
		List<String> categories = new ArrayList<String>();
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT distinct(auction_category) FROM " + DBUtils.AUCTION_TABLE);
			
			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				categories.add(rs.getString(1));
			}
			
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
		return categories;
	}

	@Override
	public void closeAuction(int aucId) throws SQLException {
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement updateUser = con.prepareStatement("UPDATE auction_item "
															 + "SET auction_halt=?,"
															 + "WHERE aid = ?");
			updateUser.setBoolean(1,true);
			updateUser.setInt(2,aucId);
			
			updateUser.executeUpdate();      

		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	@Override
	public List<AuctionDTO> wordSearchAuctionsByDescription(String searchString, boolean ascending) throws SQLException {
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			List<String> searchWords = Arrays.asList(searchString.split(" "));
			
			StringBuffer queryString = new StringBuffer();
			queryString.append("SELECT * FROM " + DBUtils.AUCTION_TABLE + " WHERE auction_halt=false");
			for (String word: searchWords) {
				queryString.append(" AND " + DBUtils.AUC_DESC + " LIKE ?");
			}
			
			String order = "";
			if (!ascending) {
				order = "desc";
			}
			queryString.append(" ORDER BY " + DBUtils.AUC_CLOSE + " " + order);
			
			PreparedStatement userQuery = con.prepareStatement(queryString.toString());
			int i = 1;
			for (String word: searchWords) {
				userQuery.setString(i, "%"+word+"%");
				i++;
			}
			
			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				auctions.add(generateAuctionDTO(rs));
			}
			
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
		// This list is ordered in ascending order
		return auctions;
	}

	@Override
	public List<AuctionDTO> getActiveAuctionsWhereUserParticipated(int userId)
			throws SQLException {
		
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT distinct(ai.aid) FROM auction_item as ai, auction_bid as ab " + 
															   "WHERE ai.auction_halt=false and ab.aid=ai.aid and ab.uid=?");
			userQuery.setInt(1,userId);

			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				auctions.add(this.getAuctionById(rs.getInt(1)));
			}
			
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
		return auctions;
	}

	@Override
	public List<AuctionDTO> getUserActiveAuctions(int userId)
			throws SQLException {
		
		List<AuctionDTO> auctions = new ArrayList<AuctionDTO>();
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.AUCTION_TABLE + 
															   " WHERE " + DBUtils.AUC_OWNER_ID + "=?" +
															   " AND " + DBUtils.AUC_HALT + "=false");
			
			userQuery.setInt(1, userId);
			
			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				auctions.add(generateAuctionDTO(rs));
			}
			
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
		
		return auctions;
	}
	
}
