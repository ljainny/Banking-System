package com.capgemini.bank.bean;

import java.time.LocalDate;

public class Account {
	private int accountId;
	private String accountType;
	private int accountBalance;
	private LocalDate openDate;

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int accountId, String accountType, int accountBalance,
			LocalDate openDate) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
		this.openDate = openDate;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	@Override
	public String toString() {
		return "AccountMaster [accountId=" + accountId + ", accountType="
				+ accountType + ", accountBalance=" + accountBalance
				+ ", openDate=" + openDate + "]";
	}

}
