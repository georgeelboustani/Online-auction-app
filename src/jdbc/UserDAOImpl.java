package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.ServiceLocatorException;

public class UserDAOImpl implements UserDAO {
	
	@Override
	public void addUser(UserDTO user) throws SQLException {
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement updateUser = con.prepareStatement("INSERT into " + DBUtils.SCHEMA_NAME + ".user "
															 + "(username,nickname,first_name,last_name,password,"
															 + "email,year_of_birth,avatar_img,activate,ban)"
															 + " values (?,?,?,?,?,?,?,?,?,?)");
			updateUser.setString(1,user.getUsername());
			updateUser.setString(2,user.getNickname());
			updateUser.setString(3,user.getFirstName());
			updateUser.setString(4,user.getLastName());
			updateUser.setString(5,DBUtils.calculateMD5(user.getPassword()));
			updateUser.setString(6,user.getEmail());
			updateUser.setDate(7,user.getYearOfBirth());
			updateUser.setString(8,user.getAvatar());
			updateUser.setBoolean(9,user.getActivated());
			updateUser.setBoolean(10,user.getBanned());
			
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
	
	// TODO - not tested yet
	@Override
	public UserDTO getUserById(int userId) throws SQLException {
		UserDTO user = new UserDTO();
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.SCHEMA_NAME + "." + DBUtils.USER_TABLE
															 + " WHERE uid=?");
			userQuery.setInt(1,userId);
			
			ResultSet rs = userQuery.executeQuery();
			rs.next();
			
			user = generateUserDTO(rs);
			
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
		
		return user;
	}

	// TODO - not tested yet
	@Override
	public List<UserDTO> getUserByUserName(String username) throws SQLException {
		List<UserDTO> users = new ArrayList<UserDTO>();
		
		Connection con = null;
		
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.SCHEMA_NAME + "." + DBUtils.USER_TABLE
															 + " WHERE ?=?");
			userQuery.setString(1,DBUtils.USER_NAME);
			userQuery.setString(2, username);
			
			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				users.add(generateUserDTO(rs));
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
		
		return users;
	}

	// TODO - not tested yet
	@Override
	public List<UserDTO> getAllUsers() throws SQLException {
		List<UserDTO> users = new ArrayList<UserDTO>();
		
		Connection con = null;
		
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement userQuery = con.prepareStatement("SELECT * FROM " + DBUtils.SCHEMA_NAME + "." + DBUtils.USER_TABLE);
			
			ResultSet rs = userQuery.executeQuery();
			while (rs.next()) {
				users.add(generateUserDTO(rs));
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
		
		return users;
	}
	
	// TODO - test this out
	@Override
	public int authenticateLogin(String username, String password) throws SQLException {
		int resultUid = -1;
		
		Connection con = null;
		
		try {
			con = DBConnectionFactory.getConnection();
			PreparedStatement userQuery = con.prepareStatement("SELECT uid FROM " + DBUtils.SCHEMA_NAME + "." + DBUtils.USER_TABLE 
															 + " WHERE "+DBUtils.USER_NAME+"=? AND "+DBUtils.USER_PASSWORD+"=? "
															 		+ "AND "+DBUtils.USER_ACTIVE+"=TRUE AND "+DBUtils.USER_BAN+"=FALSE;");
			// TODO - should make username unique in the database
			
			userQuery.setString(1, username);
			userQuery.setString(2, DBUtils.calculateMD5(password));
			
			// If anything is returned, then the login username and pass is a valid combination
			ResultSet rs = userQuery.executeQuery();
			if(rs.next()){
				resultUid = rs.getInt("uid");
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
		
		return resultUid;
	}
	
	private UserDTO generateUserDTO(ResultSet rs) throws SQLException {
		UserDTO user = new UserDTO();
		
		user.setUid(rs.getInt(DBUtils.USER_ID));
		user.setUsername(rs.getString(DBUtils.USER_NAME));
		user.setNickname(rs.getString(DBUtils.USER_NICKNAME));
		user.setFirstName(rs.getString(DBUtils.USER_FIRST_NAME));
		user.setLastName(rs.getString(DBUtils.USER_LAST_NAME));
		user.setPassword(rs.getString(DBUtils.USER_PASSWORD));
		user.setEmail(rs.getString(DBUtils.USER_EMAIL));
		user.setYearOfBirth(rs.getDate(DBUtils.USER_DOB));
		user.setAvatar(rs.getString(DBUtils.USER_AVATAR));
		user.setActivated(rs.getBoolean(DBUtils.USER_ACTIVE));
		user.setBanned(rs.getBoolean(DBUtils.USER_BAN));
		
		return user;
	}

	@Override
	public void updateUser(UserDTO user) throws SQLException {
		Connection con = null;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement updateUser = con.prepareStatement("UPDATE " + DBUtils.SCHEMA_NAME + ".user "
															 + "SET nickname=?,first_name=?,last_name=?,password=?,"
															 + "email=?,year_of_birth=?"
															 + "WHERE " + DBUtils.USER_ID + " = " + user.getUid());
			updateUser.setString(1,user.getNickname());
			updateUser.setString(2,user.getFirstName());
			updateUser.setString(3,user.getLastName());
			updateUser.setString(4,DBUtils.calculateMD5(user.getPassword()));
			updateUser.setString(5,user.getEmail());
			updateUser.setDate(6,user.getYearOfBirth());
			
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

}