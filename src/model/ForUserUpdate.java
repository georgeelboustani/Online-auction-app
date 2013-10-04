package model;

public class ForUserUpdate {
	
	private String nickName;
	private String firstName;
	private String lastName;
	private String birthdate;
	private String address;
	private String creditcardnum;
	private String password;
	private String passwordConfirm;
	private String email;
	
	
	public String getNickName(){
		return this.nickName;
	}
	
	public String getFirstName(){
		return this.firstName;
	}
	
	public String getLastName(){
		return this.lastName;
	}
	
	public String getBirthDate(){
		return this.birthdate;
	}
	
	public String getAddress(){
		return this.address;
	}
	
	public String getCreditCardNum(){
		return this.creditcardnum;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public String getPasswordConfirm(){
		return this.passwordConfirm;
	}
}
