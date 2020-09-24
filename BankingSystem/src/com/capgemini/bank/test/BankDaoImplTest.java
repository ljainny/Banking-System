package com.capgemini.bank.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Customer;
import com.capgemini.bank.bean.FundTransfer;
import com.capgemini.bank.bean.ServiceTracker;
import com.capgemini.bank.bean.Transactions;
import com.capgemini.bank.bean.User;
import com.capgemini.bank.dao.BankDaoImpl;
import com.capgemini.bank.exception.BankException;

public class BankDaoImplTest {
	private static BankDaoImpl bankDao;
	int id = 1000;
	int count = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		bankDao = new BankDaoImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		bankDao = null;
	}

	@Ignore
	@Test
	public void testApplyService() {
		try {

			ServiceTracker serviceTracker = new ServiceTracker(1000,
					"Cheque Book", 1001, LocalDate.now(), "requested");
			id = bankDao.applyService(serviceTracker);
			assertTrue("Service not applied !", id >= 1000);
			if (id >= 1000) {
				System.out.println("Your Unique Service Id : " + id
						+ "\n this Id will be used to track the request");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testGetServiceStatus() {

		try {
			String str = bankDao.getServiceStatus(1000, 1001);
			System.out.println(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testAddDetails() {
		Account account = new Account(1000, "Savings", 5000, LocalDate.now());
		Customer customer = new Customer(1000, "Nitin", "nitin007@gmail.com",
				"South Mumbai", "DTGQW9812M");
		User user = new User(1000, "asdfgh", "black", "open");

		try {
			int x = bankDao.addDetails(account, customer, user);
			if (x == 0) {
				System.out.println("PanCard Details already exists");
			} else {
				System.out.println(x);
			}
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testTransferFund() {
		try {

			FundTransfer fundTransfer = new FundTransfer(1000, 1000, 1001,
					LocalDate.now(), 200);
			Transactions transactions = new Transactions(1000, "Money",
					LocalDate.now(), "Net Banking", 200, 1000, 1001, 5000);

			int x = bankDao.transferFund(fundTransfer, transactions);
			System.out.println(x);

		} catch (BankException e) {

			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testShowMiniStatement() throws BankException {
		// int id=bankDao.getUniqueTransactionId();
		List<Transactions> tranList = bankDao.showMiniStatement(1001);
		assertNotNull("Transaction Records Not Found", tranList);
		System.out.println("*********List of Latest Transaction*******");
		for (Transactions t : tranList)
			System.out.println(t);
	}

	@Ignore
	@Test
	public void testShowAllTransaction() {
		try {

			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("dd-MM-yyyy");

			String date1 = "21-09-2017";
			String date2 = "23-09-2017";
			LocalDate localDate1 = LocalDate.parse(date1, formatter);
			LocalDate localDate2 = LocalDate.parse(date2, formatter);

			List<Transactions> transList = bankDao.showAllTransactions(
					localDate1, localDate2, 1001);

			assertNotNull("No Transactions Found : ", transList);
			if (transList != null) {
				System.out
						.println("**********************Detailed Transactions**********************");
				for (Transactions tran : transList)
					System.out.println(tran);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testShowReport() {
		try {

			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("dd-MM-yyyy");

			String date1 = "21-09-2017";
			String date2 = "23-09-2017";
			LocalDate localDate1 = LocalDate.parse(date1, formatter);
			LocalDate localDate2 = LocalDate.parse(date2, formatter);

			List<Transactions> transList = bankDao.showReport(localDate1,
					localDate2);

			assertNotNull("No Transactions Found : ", transList);
			if (transList != null) {
				System.out
						.println("**********************Detailed Transactions**********************");
				for (Transactions tran : transList)
					System.out.println(tran);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Ignore
	@Test
	public void testLogin() throws BankException {

		for (int i = 0; i < 3; i++) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Account Id");
			int accountId = sc.nextInt();
			System.out.println("Enter password");
			String password = sc.next();
			boolean flag = bankDao.loginCheck(accountId, password);
			// System.out.println(flag);
			if (flag) {
				System.out.println("Login Successful");
				testGetBalance();
				break;
			} else {
				count++;
				if (count == 3) {
					{
						System.out.println("Locked");
					}
				}
			}

		}

	}

	@Ignore
	@Test
	public void testGetBalance() {
		int balance = bankDao.returnBalance(1000);
		System.out.println(balance);
	}

	@Ignore
	@Test
	public void testChangeDetails() throws BankException {
		Customer customer = new Customer();
		customer.setAddress("Mumbai Central");
		customer.setEmail("bond@gmail.com");
		customer.setAccountId(1000);

		int x = bankDao.changeDetails(customer);
		if (x > 0) {
			System.out.println("Successful");
		}
	}

	@Ignore
	@Test
	public void testAdminLogin() throws BankException {
		boolean result = bankDao.adminLogin("admin", "admin");
		// if (result) {
		// System.out.println("Login Successful");
		// }
		assertTrue(result);
		System.out.println("Login Successful");

	}

	@Ignore
	@Test
	public void testResetPassword() throws BankException {

		int rows = bankDao.resetPassword("asdfgh", 1002);
		assertNotNull(rows);
		System.out.println(rows);
		if (rows > 0) {
			System.out.println("change password");
		} else {
			System.out.println("Not change");
		}

	}

	@Ignore
	@Test
	public void testForgotPass() throws BankException {
		int res = bankDao.forgotPassword("abcdef", 1001, "black");
		assertNotNull(res);
		System.out.println(res);
		if (res > 0) {
			System.out.println("updated ");
		} else {
			System.out.println("Not updated");
		}

	}

}