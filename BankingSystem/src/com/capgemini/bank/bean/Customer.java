package com.capgemini.bank.bean;

public class Customer {
	private int accountId;
	private String customerName;
	private String email;
	private String address;
	private String pancard;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int accountId, String customerName, String email,
			String address, String pancard) {
		super();
		this.accountId = accountId;
		this.customerName = customerName;
		this.email = email;
		this.address = address;
		this.pancard = pancard;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPancard() {
		return pancard;
	}

	public void setPancard(String pancard) {
		this.pancard = pancard;
	}

	@Override
	public String toString() {
		return "Customer [accountId=" + accountId + ", customerName="
				+ customerName + ", email=" + email + ", address=" + address
				+ ", pancard=" + pancard + "]";
	}
	
	

}
