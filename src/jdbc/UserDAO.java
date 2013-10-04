package jdbc;

import java.sql.SQLException;
import java.util.List;

import pagebeans.UpdateBean;

public interface UserDAO {
	
	void addUser(UserDTO user) throws SQLException;
	
	void updateUser(UpdateBean updateBean) throws SQLException;
	
	void banUser(int userId) throws SQLException;
	
	UserDTO getUserById(int userId) throws SQLException;
	
	UserDTO getUserByUserName(String userName) throws SQLException;
	
	List<UserDTO> getAllUsers() throws SQLException;
	
	int authenticateLogin(String username, String password) throws SQLException;

	int getNumUserByUserName(String username) throws SQLException;

	void activateUser(String username, String checksum) throws SQLException;
	// Add more shit that we need to perform on user table
}
