package jdbc;

import java.util.List;

public interface UserDAO {
	
	UserDTO getUserById(int userId);
	
	List<UserDTO> getUserByFirstName(String firstName);
	
	// Add more shit that we need to perform on user table
}
