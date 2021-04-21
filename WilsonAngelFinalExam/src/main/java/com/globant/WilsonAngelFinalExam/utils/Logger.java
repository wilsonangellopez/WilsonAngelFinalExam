package com.globant.WilsonAngelFinalExam.utils;

import java.util.Date;

import org.testng.Reporter;

/**
 * 
 * @author angel.hojsgaard | Globant
 *Testng Reporterlog implementation
 */
public class Logger {

	private static final String DATEPATTERN = "MM-dd-yyyy HH:mm:ss ZZ";
	private static final String LOGTEMPLATE = "[%s %s] %s";
	
	
	/**
	 * Print formatted info on Console and testng reporter 
	 * @param message
	 * @author angel.hojsgaard | Globant
	 */
	
	public static void printInfo(String message){
		
		Reporter.log("message:" + message);	
	}
	
	/**
	 * Print formatted debug info on Console and testng reporter 
	 * @param message
	 * @author angel.hojsgaard | Globant
	 */	

	public static void printDebug(String message){
	//	String logMessage = String.format(LOGTEMPLATE,"DEBUG",DateTime.now().toString(DATEPATTERN),message);
		Reporter.log("message:" + message);	
	}
	
	/**
	 * Print formatted error info on Console and testng reporter 
	 * @param message
	 * @author angel.hojsgaard | Globant
	 */

	public static void printError(String message) {
		Reporter.log("message:" + message);	
		
	}
	
	/**
	 * Print formatter warning info on Console and testng reporter
	 * @param string
	 * @author angel.hojsgaard | Globant
	 */

	public static void printWarning(String message) {
		Reporter.log("message:" + message);	
	}
}
