package com.capgemini.bank.dao;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Customer;
import com.capgemini.bank.bean.FundTransfer;
import com.capgemini.bank.bean.ServiceTracker;
import com.capgemini.bank.bean.Transactions;
import com.capgemini.bank.bean.User;
import com.capgemini.bank.exception.BankException;

public interface IBankDao {
	public boolean loginCheck(int accountId, String password)
			throws BankException;

	public int applyService(ServiceTracker serviceTracker) throws BankException;

	public String getServiceStatus(int serviceId, int accountId)
			throws BankException;

	public int transferFund(FundTransfer fundTransfer, Transactions transactions)
			throws BankException;

	public int addDetails(Account account, Customer customer, User user)
			throws BankException;

	List<Transactions> showMiniStatement(int accountId) throws BankException;

	public List<Transactions> showAllTransactions(LocalDate from, LocalDate to,
			int accountId) throws BankException;

	public List<Transactions> showReport(LocalDate from, LocalDate to)
			throws BankException;

	public int resetPassword(String password, int accountId)
			throws BankException;

	public int forgotPassword(String password, int accountId,
			String secretQuestion) throws BankException;

	public int changeDetails(Customer customer) throws BankException;

	public boolean adminLogin(String adminName, String adminPassword)
			throws BankException;

	public int returnBalance(int accountId);
}
