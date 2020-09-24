package com.capgemini.bank.client;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.bank.bean.Account;
import com.capgemini.bank.bean.Customer;
import com.capgemini.bank.bean.FundTransfer;
import com.capgemini.bank.bean.ServiceTracker;
import com.capgemini.bank.bean.Transactions;
import com.capgemini.bank.bean.User;
import com.capgemini.bank.exception.BankException;
import com.capgemini.bank.service.BankServiceImpl;
import com.capgemini.bank.service.IBankService;
import com.capgemini.bank.util.DBUtil;

public class BankClient {
	static Logger myLogger;
	// creating object of service class
	static IBankService bankService;
	static Scanner sc;
	static int adminCount = 0;
	static int userCount = 0;
	static int accountId = 0;
	static int accountBalance = 5000;
	static {
		try {
			myLogger = Logger.getLogger(BankClient.class.getName());
			PropertyConfigurator.configure("resources/log4j.properties");
			bankService = new BankServiceImpl();
			sc = new Scanner(System.in);
		} catch (BankException e) {
			myLogger.error("Error :" + e.getMessage());
		}
	}

	public static void main(String[] args) {
		int choice = 0;
		try {
			outer: while (true) {
				try {
					// displaying data of screen
					displayMenu();
					choice = sc.nextInt();
					switch (choice) {
					case 1:
						userLogin();
						break;
					case 2:
						adminLogin();
						break;
					case 0:
						// before exit close the connection
						DBUtil.closeConnection();
						exit();

					default:
						// if user select choice other than 1,2,0 this following
						// msg will be print
						System.out
								.println("Please select correct choice...(0-2) only");
					}
				} // end of inner try
				catch (InputMismatchException e) {
					myLogger.error("ERRROR " + e);
					sc.nextLine();
					System.err
							.println("Please enter a numeric value, try again");
					continue outer;
				}
			}// end of while
		}// end of outer try
		catch (BankException e) {
			System.err.println("Customer Exception in client !" + e);
			myLogger.error("Customer Exception in client !" + e);
		}

	}

	private static void userLogin() {
		boolean flag = true;

		System.out
				.println("-------------------------------------------------------------------"
						+ "\n\t\t\t User Login"
						+ "\n-------------------------------------------------------------------");
		System.out.println("Enter AccountId");
		String accId = sc.next();

		while (true) {
			try {
				if (bankService.validateAccountId(accId)) {
					break;
				} else {
					System.err.println("\nEnter Valid Account Id");
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			accId = sc.next();
		}
		accountId = Integer.parseInt(accId);

		System.out
				.println("Enter Password (Enter 'forget' if you forget password)");
		String password = sc.next();
		if (password.equalsIgnoreCase("forget")) {
			System.out
					.println("What is your Favorite Color (Security Question)");
			String color = sc.next();
			System.out.println("Enter New Password");
			password = sc.next();
			while (true) {
				try {
					if (bankService.validatePassword(password)) {
						break;
					} else {
						System.err
								.println("\nEnter Password in Correct Format (Eg: Jam12)");
						password = sc.next();
					}
				} catch (BankException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			int x = 0;
			try {
				x = bankService.forgotPassword(password, accountId, color);
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (x == 0) {
				System.out.println("Not a valid user");
				exit();
			} else {
				System.out.println("Password Updated Successfully");
				userLogin();
			}
		}
		try {
			flag = bankService.loginCheck(accountId, password);
			if (flag == true) {
				displayUser();
			} else {
				userCount++;

				if (userCount < 3) {
					userLogin();
				} else {
					System.err
							.println("Invalid Credentials!! Forcefully Exited");
					System.exit(0);
				}

			}
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void displayUser() {

		int choice = 0;
		boolean flag = true;

		outer: while (flag) {
			try {
				// displaying data of screen
				displayUserMenu();
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					getMiniStatement();
					break;

				case 2:
					getDetailedStatement();
					break;

				case 3:
					changeAddressMobile();
					break;

				case 4:
					requestChequeBook();
					break;

				case 5:
					trackRequest();
					break;
				case 6:
					doFundTransfer();
					break;
				case 7:
					changePassword();
					break;

				case 0:
					// before exit close the connection
					flag = false;
					break;

				default:
					// if user select choice other than 1,2,0 this following
					// msg will be print
					System.out
							.println("Please select correct choice...(0-7) only");
				}
			} // end of inner try
			catch (InputMismatchException e) {
				myLogger.error("ERRROR " + e);
				sc.nextLine();
				System.err.println("Please enter a numeric value, try again");
				continue outer;
			}
		}// end of while

	}

	private static void doFundTransfer() {
		FundTransfer fundTransfer = new FundTransfer();
		Transactions transactions = new Transactions();
		System.out.println("Enter Payee Account Id : ");
		String payeeAccId = sc.next();
		while (true) {
			try {
				if (bankService.validateAccountId(payeeAccId)) {
					break;
				} else {
					System.err.println("\nEnter Valid Account Id");
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			payeeAccId = sc.next();
		}
		int payeeAccountId = Integer.parseInt(payeeAccId);

		System.out.println("Enter Amount to be Transfered");
		String amount = sc.next();
		while (true) {
			try {
				if (bankService.validateAmount(amount)) {
					break;
				} else {
					System.err.println("\nEnter Valid Amount");
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			amount = sc.next();
		}
		int transferAmount = Integer.parseInt(amount);

		System.out.println("Enter The Reason For Transaction");
		String tranDescription = sc.next();

		System.out.println("Enter Transaction Type (Debit,NetBanking,NEFT)");
		String transactionType = sc.next();

		fundTransfer.setAccountId(accountId);
		fundTransfer.setPayeeAccountId(payeeAccountId);
		fundTransfer.setDateOfTransfer(LocalDate.now());
		fundTransfer.setTransferAmount(transferAmount);

		transactions.setTranDescription(tranDescription);
		transactions.setDateOfTransaction(LocalDate.now());
		transactions.setTransactionType(transactionType);
		transactions.setTranAmount(transferAmount);

		int x = 0;
		try {
			x = bankService.transferFund(fundTransfer, transactions);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (x == 0) {
			System.out.println("Payee Account Id is Invalid");
		} else if (x == 2) {
			System.out.println("Insufficient Balance");
		} else {
			System.out.println("Transaction Successfull"
					+ "\nTransaction Id is" + x);
		}
	}

	private static void changePassword() {
		System.out.println("Enter New Password");
		String password = sc.next();
		while (true) {
			try {
				if (bankService.validatePassword(password)) {
					break;
				} else {
					System.err
							.println("\nEnter Password in Correct Format (Eg: Jam12)");
					password = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int x = 0;
		try {
			x = bankService.resetPassword(password, accountId);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (x > 0) {
			System.out.println("Password Updated");
		} else {
			System.out.println("Password Not Changed");
		}

	}

	private static void trackRequest() {
		System.out.println("Enter Service Id");
		String servId = sc.next();
		while (true) {
			try {
				if (bankService.validateAccountId(servId)) {
					break;
				} else {
					System.err.println("\nEnter Valid Account Id");
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			servId = sc.next();
		}
		int serviceId = Integer.parseInt(servId);
		String status = null;
		try {
			status = bankService.getServiceStatus(serviceId, accountId);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (status.equalsIgnoreCase("issued")) {
			System.out.println("Your Request has been Issued");
		} else if (status.equalsIgnoreCase("dipacthed")) {
			System.out.println("Your Cheque Book has been Dispatched");
		} else if (status.equalsIgnoreCase("delivered")) {
			System.out.println("Your Cheque Book has been Delivered");
		} else if (status.equalsIgnoreCase("returned")) {
			System.out
					.println("Your Cheque Book might have been returned to the Bank");
		} else if (status.equalsIgnoreCase("invalid")) {
			System.out.println("Invalid Service Id");
		} else {
			System.out.println("Your Request is to be Proccessed");
		}
	}

	private static void requestChequeBook() {
		ServiceTracker serviceTracker = new ServiceTracker();
		serviceTracker.setAccountId(accountId);
		serviceTracker.setServiceDescription("Cheque Book");
		serviceTracker.setServiceRaisedDate(LocalDate.now());
		serviceTracker.setServiceStatus("open");
		int serviceId = 0;
		try {
			serviceId = bankService.applyService(serviceTracker);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You have successfully requested for cheque book"
				+ "\n Your Service Id is : " + serviceId
				+ " this will be used to track your request");
	}

	private static void changeAddressMobile() {
		Customer customer = new Customer();
		System.out.println("Enter New Address");
		String address = sc.next();
		System.out.println("Enter Email Id");
		String email = sc.next();
		while (true) {
			try {
				if (bankService.validateEmail(email)) {
					break;
				} else {
					System.err.println("\nEnter Correct Email Id");
					email = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		customer.setAccountId(accountId);
		customer.setAddress(address);
		customer.setEmail(email);
		int x = 0;
		try {
			x = bankService.changeDetails(customer);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (x > 0) {
			System.out.println("Updated Successfully");
		} else {
			System.out.println("Not Updated");
		}

	}

	private static void getDetailedStatement() {
		System.out.println("Enter 'from' Date: (dd-MM-yyyy)");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date1 = sc.next();
		while (true) {
			try {
				if (bankService.validateDate(date1)) {
					break;
				} else {
					System.err
							.println("\nEnter Date in (dd-MM-yyyy) format (For Eg : 10-10-2010)");
					date1 = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Enter 'to' Date: (dd-MM-yyyy)");
		String date2 = sc.next();
		while (true) {
			try {
				if (bankService.validateDate(date2)) {
					break;
				} else {
					System.err
							.println("\nEnter Date in (dd-MM-yyyy) format (For Eg : 10-10-2010)");
					date2 = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		LocalDate localDate1 = LocalDate.parse(date1, formatter);
		LocalDate localDate2 = LocalDate.parse(date2, formatter);

		List<Transactions> transList = null;
		try {
			transList = bankService.showAllTransactions(localDate1, localDate2,
					accountId);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (transList.isEmpty()) {
			System.out.println("No transactions made between " + date1
					+ " and " + date2);
		} else {
			System.out
					.println("**********************Detailed Transactions**********************");
			for (Transactions tran : transList)
				System.out.println(tran);
		}

	}

	private static void getMiniStatement() {
		List<Transactions> tranList = null;
		try {
			tranList = bankService.showMiniStatement(accountId);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("*********List of Latest Transaction*******");
		for (Transactions t : tranList)
			System.out.println(t);
	}

	private static void displayUserMenu() {
		System.out.println("\n\n****** Effective Balance : "
				+ bankService.returnBalance(accountId) + " ******");
		System.out
				.println("-------------------------------------------------------------------"
						+ "\n\t\t\t User Portal"
						+ "\n-------------------------------------------------------------------"
						+ "\n\t\t\t 1.Mini Statement"
						+ "\n\t\t\t 2.Detailed Statement"
						+ "\n\t\t\t 3.Change in address and Email Id"
						+ "\n\t\t\t 4.Request for cheque book"
						+ "\n\t\t\t 5.Track Request"
						+ "\n\t\t\t 6.Fund Transfer"
						+ "\n\t\t\t 7.Change Password"
						+ "\n\t\t\t 0.Exit"
						+ "\n-------------------------------------------------------------------"
						+ "\n\t\t Please Enter a Choice"
						+ "\n-------------------------------------------------------------------");

	}

	private static void adminLogin() {
		boolean flag = true;

		System.out
				.println("-------------------------------------------------------------------"
						+ "\n\t\t\t Admin Login"
						+ "\n-------------------------------------------------------------------");
		System.out.println("Enter Username");
		String adminName = sc.next();
		System.out.println("Enter Password");
		String adminPassword = sc.next();
		try {
			flag = bankService.adminLogin(adminName, adminPassword);
			if (flag == true) {
				displayAdmin();
			} else {
				adminCount++;

				if (adminCount < 3) {
					adminLogin();
				} else {
					System.err
							.println("Invalid Credentials!! Forcefully Exited");
					System.exit(0);
				}

			}
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void displayAdmin() {

		int choice = 0;
		boolean flag = true;

		outer: while (flag) {
			try {
				// displaying data of screen
				displayAdminMenu();
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					addCustomer();
					break;
				case 2:
					viewReport();
					break;
				case 0:
					// before exit close the connection
					flag = false;
					break;

				default:
					// if user select choice other than 1,2,0 this following
					// msg will be print
					System.out
							.println("Please select correct choice...(0-2) only");
				}
			} // end of inner try
			catch (InputMismatchException e) {
				myLogger.error("ERRROR " + e);
				sc.nextLine();
				System.err.println("Please enter a numeric value, try again");
				continue outer;
			}
		}// end of while
	}// end of outer try

	private static void viewReport() {
		System.out.println("Enter 'from' Date");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date1 = sc.next();
		while (true) {
			try {
				if (bankService.validateDate(date1)) {
					break;
				} else {
					System.err
							.println("\nEnter Date in (dd-MM-yyyy) format (For Eg : 10-10-2010)");
					date1 = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Enter 'to' Date");
		String date2 = sc.next();
		while (true) {
			try {
				if (bankService.validateDate(date2)) {
					break;
				} else {
					System.err
							.println("\nEnter Date in (dd-MM-yyyy) format (For Eg : 10-10-2010)");
					date2 = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		LocalDate localDate1 = LocalDate.parse(date1, formatter);
		LocalDate localDate2 = LocalDate.parse(date2, formatter);

		List<Transactions> transList = null;
		try {
			transList = bankService.showReport(localDate1, localDate2);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (transList.isEmpty()) {
			System.out.println("No transactions made between " + date1
					+ " and " + date2);
		} else {
			System.out
					.println("**********************Detailed Transactions**********************");
			for (Transactions tran : transList)
				System.out.println(tran);
		}

	}

	private static void addCustomer() {

		Account account = new Account();
		Customer customer = new Customer();
		User user = new User();

		System.out.println("Enter Customer Name : (Name starting with Capital Letter (Eg : James)");
		String customerName = sc.next();
		while (true) {
			try {
				if (bankService.validateCustomerName(customerName)) {
					break;
				} else {
					System.err
							.println("\nEnter Name starting with Capital Letter (Eg : James)");
					customerName = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Enter Email Id: (Eg: abc@gmail.com)");
		String email = sc.next();
		while (true) {
			try {
				if (bankService.validateEmail(email)) {
					break;
				} else {
					System.err.println("\nEnter Correct Email Id");
					email = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Enter Address");
		String address = sc.next();
		
		System.out.println("Enter Pan Card No: (Eg: ABCDE1234F)");
		String pancard = sc.next();
		while (true) {
			try {
				if (bankService.validatePancard(pancard)) {
					break;
				} else {
					System.err.println("\nEnter Correct Pan Card No");
					pancard = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Enter Account Type (Savings or Current)");
		String accountType = sc.next();
		while (true) {
			try {
				if (bankService.validateAccountType(accountType)) {
					break;
				} else {
					System.err
							.println("\nEnter either Savings or Current (Eg : Savings)");
					accountType = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Enter Password");
		String password = sc.next();
		while (true) {
			try {
				if (bankService.validatePassword(password)) {
					break;
				} else {
					System.err
							.println("\nEnter Password in Correct Format (Eg: Jam12)");
					password = sc.next();
				}
			} catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Enter Your Favorite Color (Security Question)");
		String secretQuestion = sc.next();

		account.setAccountType(accountType);
		account.setAccountBalance(accountBalance);
		account.setOpenDate(LocalDate.now());
		user.setLoginPassword(password);
		user.setLockStatus("open");
		user.setSecretQuestion(secretQuestion);
		customer.setCustomerName(customerName);
		customer.setEmail(email);
		customer.setAddress(address);
		customer.setPancard(pancard);

		int accId = 0;
		try {
			accId = bankService.addDetails(account, customer, user);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (accId == 0) {
			System.out
					.println("This Pan Card number is already used by Another Account");
			addCustomer();
		}
		System.out.println("Your Account Id is " + accId);
		accountId = accId;
	}

	private static void displayAdminMenu() {
		System.out
				.println("-------------------------------------------------------------------"
						+ "\n\t\t\t Admin Portal"
						+ "\n-------------------------------------------------------------------"
						+ "\n\t\t\t 1.Add Customer"
						+ "\n\t\t\t 2.View Reports"
						+ "\n\t\t\t 0.Exit"
						+ "\n-------------------------------------------------------------------"
						+ "\n\t\t Please Enter a Choice"
						+ "\n-------------------------------------------------------------------");

	}

	private static void displayMenu() {
		System.out
				.println("-------------------------------------------------------------------"
						+ "\n\t\t Capgemini Banking Application"
						+ "\n-------------------------------------------------------------------"
						+ "\n\t\t\t 1.User"
						+ "\n\t\t\t 2.Admin"
						+ "\n\t\t\t 0.Exit"
						+ "\n-------------------------------------------------------------------"
						+ "\n\t\t Please Enter a Choice"
						+ "\n-------------------------------------------------------------------");
	}

	private static void exit() {
		// occur

		System.out
				.println("-----------------Thank You for using our Banking Application-----------------"
						+ "\n\t-----------------Do Visit Us Again!!-----------------"
						+ "\n\t--------xxxxxxx--------xxxxxxx--------xxxxxxx--------");
		System.exit(0);// exit the program
	}

}
