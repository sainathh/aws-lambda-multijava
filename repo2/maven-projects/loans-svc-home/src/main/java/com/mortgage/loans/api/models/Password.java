package com.mortgage.loans.api.models;

public class Password {

	private String oldPassword;
	private String newPassword;
	
	public Password() {
		super();
	}

	public Password(String oldPassword, String newPassword) {
		super();
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "Password [oldPassword=" + oldPassword + ", newPassword=" + newPassword + "]";
	}
}
