package com.capgemini.bank.bean;

import java.time.LocalDate;

public class Transactions {
	private int transactionId;
	private String tranDescription;
	private LocalDate dateOfTransaction;
	private String transactionType;
	private int tranAmount;
	private int accountId;
	private int payeeAccountId;
	private int balance;

	public Transactions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transactions(int transactionId, String tranDescription,
			LocalDate dateOfTransaction, String transactionType,
			int tranAmount, int accountId, int payeeAccountId, int balance) {
		super();
		this.transactionId = transactionId;
		this.tranDescription = tranDescription;
		this.dateOfTransaction = dateOfTransaction;
		this.transactionType = transactionType;
		this.tranAmount = tranAmount;
		this.accountId = accountId;
		this.payeeAccountId = payeeAccountId;
		this.balance = balance;
	}

	public int getPayeeAccountId() {
		return payeeAccountId;
	}

	public void setPayeeAccountId(int payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTranDescription() {
		return tranDescription;
	}

	public void setTranDescription(String tranDescription) {
		this.tranDescription = tranDescription;
	}

	public LocalDate getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDate dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTranAmount() {
		return tranAmount;
	}

	public void setTranAmount(int tranAmount) {
		this.tranAmount = tranAmount;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Transactions [transactionId=" + transactionId
				+ ", tranDescription=" + tranDescription
				+ ", dateOfTransaction=" + dateOfTransaction
				+ ", transactionType=" + transactionType + ", tranAmount="
				+ tranAmount + ", accountId=" + accountId + ", payeeAccountId="
				+ payeeAccountId + ", balance=" + balance + "]";
	}

	
	

}
