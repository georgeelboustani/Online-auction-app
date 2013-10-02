package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import exceptions.ServiceLocatorException;

public class AuctionBidDAOImpl implements AuctionBidDAO {

	@Override
	public List<AuctionBidDTO> getAuctionBids(int auctionId) throws SQLException {
		List<AuctionBidDTO> bids = new ArrayList<AuctionBidDTO>();
		Connection con = null;
		
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.AUCTION_BID_TABLE + " WHERE aid=?");
			userQuery.setInt(1,auctionId);
			
			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				bids.add(generateAuctionBidDTO(rs));
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
		
		return bids;
	}

	@Override
	public AuctionBidDTO getHighestBid(int auctionId) throws SQLException {
		AuctionBidDTO bid = null;
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.AUCTION_BID_TABLE +
															   " WHERE aid=? and bid=(select max(bid) from auction_bid where aid=?)");
			userQuery.setInt(1,auctionId);
			userQuery.setInt(2,auctionId);
			
			ResultSet rs = userQuery.executeQuery();
			if (rs.next()) {
				bid = generateAuctionBidDTO(rs);
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
		
		return bid;
	}
	
	private AuctionBidDTO generateAuctionBidDTO(ResultSet rs) throws SQLException {
		AuctionBidDTO bid = new AuctionBidDTO();
		
		bid.setAuctionId(rs.getInt(DBUtils.AUCBID_AUC_ID));
		bid.setBidAmount(rs.getDouble(DBUtils.AUC_BID));
		bid.setUserId(rs.getInt(DBUtils.AUCBID_USER_ID));
		
		return bid;
	}

	@Override
	public void placeBid(AuctionBidDTO bid) throws SQLException {
		// TODO finish adding auction
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();

			PreparedStatement updateAuc = con.prepareStatement("INSERT into " + DBUtils.AUCTION_BID_TABLE + 
															   " (uid,aid,bid)" +
															   " values(?,?,?)");
			
			updateAuc.setInt(1,bid.getUserId());
			updateAuc.setInt(2,bid.getAuctionId());
			updateAuc.setDouble(3,bid.getBidAmount());
			
			updateAuc.executeUpdate();      
			
		} catch (ServiceLocatorException e) {
			// TODO do some roll back probably
			e.printStackTrace();
		} catch (SQLIntegrityConstraintViolationException integrity) {
			throw integrity;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

}
