package pagebeans;

import java.sql.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class UpdateBean {
	
	private int uid;
	private String nickname;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private Date yearOfBirth;
	private String creditCardNum;
	private String address;
	
	public int getUid() {
		return this.uid;
	}
	public void setIsAdmin(int uid) {
		this.uid = uid;
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
	
	@NotNull(message = "CreditCardNum is compulsory")
	@NotBlank(message = "CreditCardNum is compulsory")
	public String getCreditCardNum(){
		return this.creditCardNum;
	}
	public void setCreditCardNum(String creditCardNum){
		this.creditCardNum = creditCardNum;
	}
	
	public String getSecureCreditCardNum(){
		return this.creditCardNum.substring(Math.max(this.creditCardNum.length() - 3, 0));
	}
	
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String address){
		this.address = address;
	}
}
