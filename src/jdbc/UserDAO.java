package jdbc;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
	
	void addUser(UserDTO user) throws SQLException;
	
	void updateUser(UserDTO user) throws SQLException;
	
	void banUser(int userId) throws SQLException;
	
	UserDTO getUserById(int userId) throws SQLException;
	
	List<UserDTO> getUserByUserName(String userName) throws SQLException;
	
	List<UserDTO> getAllUsers() throws SQLException;
	
	int authenticateLogin(String username, String password) throws SQLException;
	
	// Add more shit that we need to perform on user table
}
