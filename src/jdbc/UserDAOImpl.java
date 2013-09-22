package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import exceptions.ServiceLocatorException;

public class UserDAOImpl implements UserDAO {
	
	@Override
	public void addUser(UserDTO user) {
		// TODO - insert into public.user (username,nickname,first_name,last_name,password,email,year_of_birth,avatar_img,activate,ban) values ('george','g','george','elb','123','g@hotmail.com','20120618 10:34:09 AM','asd',false,false);

		Connection con;
		try {
			con = DBConnectionFactory.getConnection();
			
			PreparedStatement updateAuc = con.prepareStatement("INSERT into " + DBUtils.SCHEMA_NAME + ".user "
															 + "(username,nickname,first_name,last_name,password,"
															 + "email,year_of_birth,avatar_img,activate,ban)"
															 + " values (?,?,?,?,?,?,?,?,?,?)");
			updateAuc.setString(1,user.getUsername());
			updateAuc.setString(2,user.getNickname());
			updateAuc.setString(3,user.getFirstName());
			updateAuc.setString(4,user.getLastName());
			updateAuc.setString(5,user.getPassword());
			updateAuc.setString(6,user.getEmail());
			updateAuc.setDate(7,user.getYearOfBirth());
			updateAuc.setString(8,user.getAvatar());
			updateAuc.setBoolean(9,user.getActivated());
			updateAuc.setBoolean(10,user.getBanned());
			
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
	public UserDTO getUserById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getUserByFirstName(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

}
