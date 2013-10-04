package jdbc;

import java.sql.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


public class UserDTO {
	
	private int uid;
	private String username;
	private String nickname;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Date yearOfBirth;
	private boolean activated = false;
	private boolean banned = false;
	private String creditCardNum;
	private String activateChecksum;
	private boolean isAdmin = false;
	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Digits(integer=10, fraction=0)
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	@NotNull(message = "Username is compulsory")
	@NotBlank(message = "Username is compulsory")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull(message = "Nickname is compulsory")
	@NotBlank(message = "Nickname is compulsory")
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@NotNull(message = "Firstname is compulsory")
	@NotBlank(message = "Firstname is compulsory")
	@Pattern(regexp = "[a-z-A-Z]*", message = "Firstname has invalid characters")
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@NotNull(message = "Lastname is compulsory")
	@NotBlank(message = "Lastname is compulsory")
	@Pattern(regexp = "[a-z-A-Z]*", message = "Lastname has invalid characters")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@NotNull(message = "Password is compulsory")
	@NotBlank(message = "Password is compulsory")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@NotNull(message = "Email is compulsory")
	@NotBlank(message = "Email is compulsory")
	@Email(message = "Email Address is not a valid format")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotNull(message = "Year of birth is compulsory")
	public Date getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(Date yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
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
	
	@NotNull(message = "CreditCardNum is compulsory")
	@NotBlank(message = "CreditCardNum is compulsory")
	public String getCreditCardNum(){
		return this.creditCardNum;
	}
	public void setCreditCardNum(String creditCardNum){
		this.creditCardNum = creditCardNum;
	}
	
	
	public String getCheckSum(){
		return this.activateChecksum;
	}
	public void setCheckSum(String activateChecksum){
		this.activateChecksum = activateChecksum;
	}
	
}
