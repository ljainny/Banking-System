package com.capgemini.bank.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.capgemini.bank.exception.BankException;

public class DBUtil {
	private static Logger myLogger;
	private static Connection connection;
	static {

		PropertyConfigurator.configure("resources/log4j.properties");
		myLogger = Logger.getLogger("DBUtil.class");
	}

	public static Connection getConnection() throws BankException {
		String url, username, password;
		try {
			// creating properties and load the properties
			Properties prop = DBUtil.loadProp();
			// get properties from file
			// driver = prop.getProperty("classname");
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			// loading and registering the driver
			// Class.forName(driver);
			// getConnection
			connection = DriverManager.getConnection(url, username, password);
			if (connection != null)
				myLogger.info("connection Obtained! : " + connection);
			// System.out.println("connection Obtained!");
			else {
				myLogger.error("connection Failed!  ");
				throw new Exception("sorry!!! "
						+ "Something went wrong	with the connection");
			}
		} catch (Exception e) {
			myLogger.error("connection Failed!  " + e);
			e.printStackTrace();
			throw new BankException(e.getMessage());
		}
		return connection;
	}// end of method
		// method for loading property file

	private static Properties loadProp() throws BankException {
		Properties myProp = new Properties();
		try (FileInputStream fis = new FileInputStream(
				"resources/jdbc.properties");) {
			myProp.load(fis);
			myLogger.info("Property File loaded : ");
		} catch (Exception e) {
			myLogger.error("Property File Not loaded");
			throw new BankException(e.getMessage());
		}
		return myProp;
	}

	// method for closing the connection
	public static Connection closeConnection() throws BankException {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
				myLogger.info("Closing Connection");
			} else
				myLogger.error("Connection already closed");
		} catch (Exception e) {
			myLogger.error("Connection already closed");
			throw new BankException(e.getMessage());

		}
		return connection;
	}
	/*
	 * public static void main(String[] args) throws BankException { new
	 * DBUtil().getConnection(); }
	 */
}
