/**
 * © Qio Technologies Ltd. 2016. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF QIO TECHNOLOGIES LTD.
 */
package io.qio.qa.lib.assertions;

import io.qio.qa.lib.exception.ServerResponse;
import static org.junit.Assert.*;
import org.apache.log4j.Logger;

/*
 * This class can be expanded with methods to add more generalized custom assertions.
 */
public class CustomAssertions {
	
	private static String DATE_FORMAT_REGEX = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01])T(00|0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])Z$";
	final static Logger logger = Logger.getRootLogger();

	public static void assertServerError(int expectedRespCode, String expectedExceptionMsg, String expectedMsg, ServerResponse serverResp) {
		assertServerError(expectedRespCode, expectedExceptionMsg, serverResp);
		assertTrue(serverResp.getMessage().equals(expectedMsg) || serverResp.getMessage().contains(expectedMsg));
	}

	public static void assertServerError(int expectedRespCode, String expectedExceptionMsg, ServerResponse serverResp) {
		assertEquals(expectedRespCode, serverResp.getStatus());
		assertEquals(expectedExceptionMsg, serverResp.getException());
	}

	public static void assertRequestAndResponseObj(int expectedRespCode, int actualRespCode, Object requestObj, Object responseObj) {
		logger.info("In assertRequestAndResponseObj with 4 args: int, int, Object, Object");
		assertEquals(expectedRespCode, actualRespCode);
		assertTrue(requestObj.equals(responseObj));
	}

	public static void assertRequestAndResponseObj(Object requestObj, Object responseObj) {
		// Here the expected and actual response codes do not matter, therefore
		// setting them both to 0, so as to ignore the assertion on them in the
		// called method.
		logger.info("In assertRequestAndResponseObj with 2 args: Object, Object");
		assertRequestAndResponseObj(0, 0, requestObj, responseObj);
	}

	public static void assertResponseCode(int expectedRespCode, int actualRespCode) {
		logger.info("In assertResponseCode");
		assertEquals(expectedRespCode, actualRespCode);
	}

	public static void assertRequestAndResponseObjForNullEqualityCheck(Object requestObj, Object responseObj) {
		assertEquals(requestObj, responseObj);
	}
	
	public static void assertDateFormat(String inputDate){
		logger.info("In assertDateFormat with 1 date arg");
		assertDateFormat(inputDate, DATE_FORMAT_REGEX);
	}
	
	public static void assertDateFormat(String inputDate, String dateFormatRegex){
		logger.info("In assertDateFormat with 1 date and 1 rexex arg");
		assertNotNull("Date field does not exist", inputDate);
		assertTrue("Incorrect date format", inputDate.matches(dateFormatRegex));
	}
	
	public static void assertEqualityCheckOnInputFields(String expectedValue, String actualValue){
		assertEquals(expectedValue, actualValue);
	}
	
	//ADDITIONAL ASSERTIONS
	//Ability to assert that system generated fields were created and ability to validate that they are null or not null
	//Ability to validate that system validated fields have a specific value
	//For example, in EHM tenants and insights get a referenceId field and is based on the value of counter;  
	//the counter value is incremented every time a new item collection is created - there is no API to get the counter value
	//
	//Also another example of system created fields are the createDate
	//Example of system updated fields: lastUpdateDate
}