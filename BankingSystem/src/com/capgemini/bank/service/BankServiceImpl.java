package com.capgemini.bank.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Customer;
import com.capgemini.bank.bean.FundTransfer;
import com.capgemini.bank.bean.ServiceTracker;
import com.capgemini.bank.bean.Transactions;
import com.capgemini.bank.bean.User;
import com.capgemini.bank.dao.BankDaoImpl;
import com.capgemini.bank.dao.IBankDao;
import com.capgemini.bank.exception.BankException;

public class BankServiceImpl implements IBankService {

	private IBankDao bankDao;

	public BankServiceImpl() throws BankException {
		bankDao = new BankDaoImpl();
	}

	@Override
	public boolean loginCheck(int accountId, String password)
			throws BankException {
		// TODO Auto-generated method stub
		return bankDao.loginCheck(accountId, password);
	}

	@Override
	public int applyService(ServiceTracker serviceTracker) throws BankException {
		// TODO Auto-generated method stub
		return bankDao.applyService(serviceTracker);
	}

	@Override
	public String getServiceStatus(int serviceId, int accountId)
			throws BankException {
		// TODO Auto-generated method stub
		return bankDao.getServiceStatus(serviceId, accountId);
	}

	@Override
	public int transferFund(FundTransfer fundTransfer, Transactions transactions)
			throws BankException {
		// TODO Auto-generated method stub
		return bankDao.transferFund(fundTransfer, transactions);
	}

	@Override
	public int addDetails(Account account, Customer customer, User user)
			throws BankException {
		// TODO Auto-generated method stub
		return bankDao.addDetails(account, customer, user);
	}

	@Override
	public List<Transactions> showMiniStatement(int accountId)
			throws BankException {
		// TODO Auto-generated method stub
		return bankDao.showMiniStatement(accountId);
	}

	@Override
	public List<Transactions> showAllTransactions(LocalDate from, LocalDate to,
			int accountId) throws BankException {
		// TODO Auto-generated method stub
		return bankDao.showAllTransactions(from, to, accountId);
	}

	@Override
	public List<Transactions> showReport(LocalDate from, LocalDate to)
			throws BankException {
		// TODO Auto-generated method stub
		return bankDao.showReport(from, to);
	}

	@Override
	public int resetPassword(String password, int accountId)
			throws BankException {
		// TODO Auto-generated method stub
		return bankDao.resetPassword(password, accountId);
	}

	@Override
	public int forgotPassword(String password, int accountId,
			String secretQuestion) throws BankException {
		// TODO Auto-generated method stub
		return bankDao.forgotPassword(password, accountId, secretQuestion);
	}

	@Override
	public int changeDetails(Customer customer) throws BankException {
		// TODO Auto-generated method stub
		return bankDao.changeDetails(customer);
	}

	@Override
	public boolean adminLogin(String adminName, String adminPassword)
			throws BankException {

		return bankDao.adminLogin(adminName, adminPassword);
	}

	@Override
	public int returnBalance(int accountId) {
		// TODO Auto-generated method stub
		return bankDao.returnBalance(accountId);
	}

	@Override
	public boolean validatePancard(String pancard) throws BankException {
		Pattern panPattern = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]{1}$");
		Matcher panMatcher = panPattern.matcher(pancard);

		if (panMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean validateCustomerName(String customerName)
			throws BankException {
		Pattern usernamePattern = Pattern.compile("^[A-Z]{1}[a-z]{2,}$");

		Matcher userMatcher = usernamePattern.matcher(customerName);

		if (userMatcher.matches()) {
			return true;
		} else {
			return false;
		}

	}
	@Override
	public boolean validatePassword(String password) throws BankException {
		Pattern pwdPattern = Pattern.compile("^[A-Z]{1}[a-z]{2,}[0-9]{2,}$");
		Matcher pwdMatcher = pwdPattern.matcher(password);
		if (pwdMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean validateAmount(String amount) throws BankException {
		Pattern accIdPattern = Pattern.compile("^[0-9]{1,}$");
		Matcher accIdMatcher = accIdPattern.matcher(amount);

		if (accIdMatcher.matches()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean validateAccountId(String accountId) throws BankException {
		Pattern accIdPattern = Pattern.compile("^[0-9]{4,}$");
		Matcher accIdMatcher = accIdPattern.matcher(accountId);
		if (accIdMatcher.matches()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean validateAccountType(String accountType) throws BankException {
		boolean res = false;
		if (accountType.equals("Savings") || accountType.equals("Current")) {
			res = true;
		}
		return res;
	}

	@Override
	public boolean validateDate(String date) throws BankException {

		Date d1 = null;

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		try {
			d1 = formatter.parse(date);
			if (date.equals(formatter.format(d1))) {
				return true;
			}
		} catch (ParseException e) {
			return false;
		}

		return false;

	}

	@Override
	public boolean validateEmail(String email) throws BankException {
		String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		// System.out.println("validateEmail() service invoked....");
		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher emailMatcher = emailPattern.matcher(email);
		if (emailMatcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

}
