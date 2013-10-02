package jdbc;

import java.sql.Date;
import javax.validation.constraints.NotNull;

public class UserDTO {
	
	@NotNull
	private int uid;
	
	private String username;
	private String nickname;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Date yearOfBirth;
	private String avatar;
	private boolean activated;
	private boolean banned;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(Date yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public boolean getActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public boolean getBanned() {
		return banned;
	}
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
}
