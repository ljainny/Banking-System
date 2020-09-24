/**
 * 
 */
package com.capgemini.bank.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.capgemini.bank.exception.BankException;
import com.capgemini.bank.util.DBUtil;

/**
 * @author Nitin
 *
 */
public class DBUtilTest {
	static Connection connection;
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("*******Set up Before Class**********");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("*******Tear down After Class**********");
		
	}

	/**
	 * Test method for {@link com.capgemini.ems.util.DBUtil#getConnection()}.
	 * @throws BankException 
	 */
	@Test
	public void testGetConnection() throws BankException {
		//fail("Not yet implemented");
		connection=DBUtil.getConnection();
		assertNotNull("Connection failed!!",connection);
	}

	/**
	 * Test method for {@link com.capgemini.ems.util.DBUtil#closeConnection()}.
	 * @throws BankException 
	 */
	@Test
	public void testCloseConnection() throws BankException {
		//fail("Not yet implemented");
		
		connection = DBUtil.closeConnection();
		assertNull("Connection not closed!!", connection);
	}

}
