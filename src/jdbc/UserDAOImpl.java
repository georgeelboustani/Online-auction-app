package jdbc;

import java.util.List;

public class UserDAOImpl implements UserDAO {
	
	@Override
	public void addUser(UserDTO user) {
		// TODO - insert into public.user (username,nickname,first_name,last_name,password,email,year_of_birth,avatar_img,activate,ban) values ('george','g','george','elb','123','g@hotmail.com','20120618 10:34:09 AM','asd',false,false);
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
