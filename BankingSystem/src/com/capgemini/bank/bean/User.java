package com.capgemini.bank.bean;

public class User {
	private int accountId;
	private String loginPassword;
	private String secretQuestion;
	private String lockStatus;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int accountId, String loginPassword, String secretQuestion,
			String lockStatus) {
		super();
		this.accountId = accountId;
		this.loginPassword = loginPassword;
		this.secretQuestion = secretQuestion;
		this.lockStatus = lockStatus;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	@Override
	public String toString() {
		return "User [accountId=" + accountId + ", loginPassword="
				+ loginPassword + ", secretQuestion=" + secretQuestion
				+ ", lockStatus=" + lockStatus + "]";
	}


			
}