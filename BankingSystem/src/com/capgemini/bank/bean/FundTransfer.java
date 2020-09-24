package com.capgemini.bank.bean;

import java.time.LocalDate;

public class FundTransfer {
	private int fundTransferId;
	private int accountId;
	private int payeeAccountId;
	private LocalDate dateOfTransfer;
	private int transferAmount;

	public FundTransfer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FundTransfer(int fundTransferId, int accountId, int payeeAccountId,
			LocalDate dateOfTransfer, int transferAmount) {
		super();
		this.fundTransferId = fundTransferId;
		this.accountId = accountId;
		this.payeeAccountId = payeeAccountId;
		this.dateOfTransfer = dateOfTransfer;
		this.transferAmount = transferAmount;
	}

	public int getFundTransferId() {
		return fundTransferId;
	}

	public void setFundTransferId(int fundTransferId) {
		this.fundTransferId = fundTransferId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getPayeeAccountId() {
		return payeeAccountId;
	}

	public void setPayeeAccountId(int payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}

	public LocalDate getDateOfTransfer() {
		return dateOfTransfer;
	}

	public void setDateOfTransfer(LocalDate dateOfTransfer) {
		this.dateOfTransfer = dateOfTransfer;
	}

	public int getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(int transferAmount) {
		this.transferAmount = transferAmount;
	}

	@Override
	public String toString() {
		return "FundTransfer [fundTransferId=" + fundTransferId
				+ ", accountId=" + accountId + ", payeeAccountId="
				+ payeeAccountId + ", dateOfTransfer=" + dateOfTransfer
				+ ", transferAmount=" + transferAmount + "]";
	}

}
