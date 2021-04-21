package com.globant.WilsonAngelFinalExam.test;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.logging.Logger;

public class MyListener implements ITestListener {

	public void onTestStart(ITestResult result) {
		Logger logger = Logger.getLogger(ESPNTest.class.getCanonicalName());
		logger.info("Test Begin: " + result.getName()+ "\\n -Time: "+ result.getStartMillis());
			//System.out.println("El test inicio: " + result.getName());
			}

	public void onTestSuccess(ITestResult result) {
		Logger logger = Logger.getLogger(ESPNTest.class.getCanonicalName());
		logger.info("Test succes: " + result.getName()+ "\\n -Time "+ result.getEndMillis());
		
	}

	public void onTestFailure(ITestResult result) {
		
		Logger logger = Logger.getLogger(ESPNTest.class.getCanonicalName());
		logger.warning("Test FAIL: " + result.getName()+ " - "+ result.getTestName() + "\\n Failure code:" +result.FAILURE);
	
	}

	public void onTestSkipped(ITestResult result) {
		
		Logger logger = Logger.getLogger(ESPNTest.class.getCanonicalName());
		logger.info("Test Skipped: " + result.getName()+ "\\n -Time"+ result.getEndMillis());	
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		Logger logger = Logger.getLogger(ESPNTest.class.getCanonicalName());
		logger.info("Test Fail %: " + result.getName()+ "\\n -Test context"+ result.getTestContext() +"result.getInstanceName()"+ result.getInstanceName());
	}

	public void onStart(ITestContext context) {
		Logger logger = Logger.getLogger(ESPNTest.class.getCanonicalName());
		logger.info("Suite start:---------------------> " + context.getSuite()+"\\n getHost: " + context.getHost()+ "\\n -Methods to run: "+ context.getAllTestMethods().length+"\n Current xml test:"+ context.getCurrentXmlTest().getThreadCount());
		
	}

	public void onFinish(ITestContext context) {
		Logger logger = Logger.getLogger(ESPNTest.class.getName());
		logger.info("Suite Finished: ------------------> " + context.getSuite()+"\\n getHost: " + context.getHost()+ "\\n <-Methods to run:> "+ context.getAllTestMethods().length + "\\n   <Passed Test:>  "+ context.getPassedTests()+ "\nstart date: "+context.getStartDate()+ "\\n       < End date>    "+ context.getEndDate() + "\n atributes name"+ context.getAttributeNames());
		
	}
}
