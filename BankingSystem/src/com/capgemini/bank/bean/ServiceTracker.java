package com.capgemini.bank.bean;

import java.time.LocalDate;

public class ServiceTracker {
	/*Service_ID NUMBER, 
	 * Service_Description VARCHAR2(100),
	 * Account_ID NUMBER, 
	 * Service_Raised_Date DATE ,
	 * Service_status VARCHAR2(20)*/
	
	private int serviceId;
	private String serviceDescription;
	private int accountId;
	private LocalDate serviceRaisedDate;
	private String serviceStatus;
	
	public ServiceTracker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceTracker(int serviceId, String serviceDescription,
			int accountId, LocalDate serviceRaisedDate, String serviceStatus) {
		super();
		this.serviceId = serviceId;
		this.serviceDescription = serviceDescription;
		this.accountId = accountId;
		this.serviceRaisedDate = serviceRaisedDate;
		this.serviceStatus = serviceStatus;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public LocalDate getServiceRaisedDate() {
		return serviceRaisedDate;
	}

	public void setServiceRaisedDate(LocalDate serviceRaisedDate) {
		this.serviceRaisedDate = serviceRaisedDate;
	}

	public String getServiceStatus() {
		return serviceStatus;
	}

	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}

	@Override
	public String toString() {
		return "ServiceTracker [serviceId=" + serviceId
				+ ", serviceDescription=" + serviceDescription + ", accountId="
				+ accountId + ", serviceRaisedDate=" + serviceRaisedDate
				+ ", serviceStatus=" + serviceStatus + "]";
	}
	
	
	
	
}
