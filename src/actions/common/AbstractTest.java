package common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.Reporter;

import common.BrowserManagement;

import org.json.simple.JSONObject;

public class AbstractTest extends BrowserManagement {

	/**
	 * verify True
	 * 
	 * @param condition
	 * @param halt
	 * @return passed/failed
	 */
	protected boolean verifyTrue(boolean condition, boolean halt) {
		boolean pass = true;
		if (halt == false) {
			try {
				if(condition==true) log.info("===PASSED===");
				else log.info("===FAILED===");
				Assert.assertTrue(condition);
			} catch (Throwable e) {
				pass = false;
				VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);				
				String path = CommonActions.captureScreenshot(getClass().toString().replace("class ", ""), "./Screenshot");
				String script = CommonActions.convertImageToURI(path);
				Reporter.log(script);
			}
		} else {
			Assert.assertTrue(condition);
		}
		return pass;
	}

	/**
	 * verify true
	 * 
	 * @param condition
	 * @return true/false
	 */
	protected boolean verifyTrue(boolean condition) {
		return verifyTrue(condition, false);
	}

	/**
	 * verify false
	 * 
	 * @param condition
	 * @param halt
	 * @return passed/failed
	 */
	protected boolean verifyFalse(boolean condition, boolean halt) {
		boolean pass = true;
		if (halt == false) {
			try {
				if(condition==false) log.info("===PASSED===");
				else log.info("===FAILED===");
				Assert.assertFalse(condition);
			} catch (Throwable e) {
				pass = false;
				VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
				String path = CommonActions.captureScreenshot(getClass().toString().replace("class ", ""), "./Screenshot");
				String script = CommonActions.convertImageToURI(path);
				Reporter.log(script);
			}
		} else {
			Assert.assertFalse(condition);
		}
		return pass;

	}

	/**
	 * verify false
	 * 
	 * @param condition
	 * @return true/false
	 */
	protected boolean verifyFalse(boolean condition) {
		return verifyFalse(condition, false);
	}

	/**
	 * verify equal
	 * 
	 * @param actual
	 * @param expected
	 * @param halt
	 * @return passed/failed
	 */
	protected boolean verifyEquals(Object actual, Object expected, boolean halt) {
		boolean pass = true;
		if (halt == false) {
			try {
				if(actual.equals(expected)) log.info("===PASSED===");
				else log.info("===FAILED===");
				Assert.assertEquals(actual, expected);
			} catch (Throwable e) {
				pass = false;
				VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
				String path = CommonActions.captureScreenshot(getClass().toString().replace("class ", ""), "./Screenshot");
				String script = CommonActions.convertImageToURI(path);
				Reporter.log(script);
			}
		} else {
			Assert.assertEquals(actual, expected);
		}
		return pass;
	}

	/**
	 * verify equal
	 * 
	 * @param actual
	 * @param expected
	 * @return true/false
	 */
	protected boolean verifyEquals(Object actual, Object expected) {
		return verifyEquals(actual, expected, false);
	}

	public Log log = LogFactory.getLog(getClass());

	/**
	 * get Data from json file
	 * 
	 * @param field
	 * @return
	 */
	public String getDataset(String field) {
		JSONObject object = (JSONObject) DataActions.readJsonData(Constants.DATA_FILE_PATH, getClass().getSimpleName());
		return (String) object.get(field);
	}
}
