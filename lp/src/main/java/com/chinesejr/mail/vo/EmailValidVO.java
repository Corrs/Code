package com.chinesejr.mail.vo;

public class EmailValidVO {
	private String userEmail;
	private String validCode;

	public EmailValidVO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public EmailValidVO(String userEmail, String validCode) {
		super();
		this.userEmail = userEmail;
		this.validCode = validCode;
	}


	public String getUserEmail() {
		return userEmail;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

}
