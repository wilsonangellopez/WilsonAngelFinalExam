package com.globant.WilsonAngelFinalExam.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;
import com.globant.WilsonAngelFinalExam.utils.Logger;
import com.globant.WilsonAngelFinalExam.utils.TraceHelper;

import sun.security.util.Length;

public class BasePage {

	private WebDriver driver;
	private WebDriverWait wait;

	/**
	 * Base page with common methods
	 * @param pDriver
	 */

	public BasePage(WebDriver pDriver){

		PageFactory.initElements(pDriver, this);
		wait = new WebDriverWait(pDriver, 30);
		driver = pDriver;

	}

	/**
	 * Method to allow to selenium get explicits Waits
	 * @return: wait
	 */
	public WebDriverWait getWait(){return wait;}

	/**
	 * Get driver selenium
	 * @return: driver
	 */
	protected WebDriver getDriver() {return driver;}	

	/**
	 * @return
	 */
	public void moveToElement(WebElement element) {
		

		Actions actionProvider = new Actions(getDriver());
		getWait().until(ExpectedConditions.elementToBeClickable(element));
		actionProvider.moveToElement(element).build().perform();
	}

	public boolean switchToIframe(String option, WebElement element){
		
		
		if(option.equalsIgnoreCase("Login")) {
			
			getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.id("disneyid-iframe"))));
			getDriver().switchTo().frame("disneyid-iframe");
			
			if(getDriver().switchTo().frame("disneyid-iframe").getTitle()!=null){
				return true;
				
			}else return false;
			
		}
		return false;
	}
	
	public static boolean isElementPresent(WebDriver driver, final By byLocator, int seconds) {

		
		Logger.printInfo("Validating if element is present:"+ byLocator);
		try {
			ArrayList<Object> dataSet = new ArrayList<>();
			dataSet.add(0, driver);
			dataSet.add(1, byLocator);
			Boolean isPresent;
			Wait<ArrayList<Object>> control = new FluentWait<ArrayList<Object>>(dataSet)
					.withTimeout(seconds, TimeUnit.SECONDS).pollingEvery(1, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			isPresent = control.until(isElementPresent);
			if (!isPresent) {
				Logger.printDebug("The element By " + byLocator + " is not present after reaching the time out: "
						+ seconds + " seconds");
			} else {
				Logger.printDebug("The element By " + byLocator + " is present");
			}
			return isPresent;
		} catch (TimeoutException e) {
			Logger.printError(String.format("Timeout reached while validating element present: %s", byLocator));
			return false;
		}
	}
	
	private static Function<List<Object>, Boolean> isElementPresent = new Function<List<Object>, Boolean>() {
		@Override
		public Boolean apply(List<Object> dataSet) {
			WebDriver driver = (WebDriver) dataSet.get(0);
			By byLocator = (By) dataSet.get(1);
			try {
				driver.findElement(byLocator);
				return true;
			} catch (StaleElementReferenceException e) {
				Logger.printError("Error found in method (%s), called from: (%s), (%s). Error: (%s)"+TraceHelper.getMethodName(0)+ TraceHelper.getMethodName(1)+ TraceHelper.getMethodName(2) + e.getMessage());
				return false;
			}
		}
	};

	public void goToDefaultContent() {
		getDriver().switchTo().parentFrame();
	}
	

	public static void waitForPageToBeLoadedCompletely(WebDriver driver, int seconds) {

		waitForJQueryLoad(driver, seconds);
		waitUntilJSReady(driver, seconds);
		waitForAngularLoad(driver, seconds);
	}

	public static void waitForJQueryLoad(WebDriver driver, int seconds) {
		try {
			//Wait for jQuery to load
			ExpectedCondition<Boolean> jQueryLoad = webdriver -> ((Long) ((JavascriptExecutor) driver)
					.executeScript("return jQuery.active") == 0);

			//Get JQuery is Ready
			boolean jqueryReady = (boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active==0");

			//Wait JQuery until it is Ready!
			if(!jqueryReady) {
								Logger.printInfo("JQuery is NOT Ready!");
				//Wait for jQuery to load
				new WebDriverWait(driver, seconds).until(jQueryLoad);
			} else {
								Logger.printInfo("JQuery is Ready!");
			}
		}catch (Exception e) {
			//			Logger.printError(String.format("Error found in method (%s), called from: (%s), (%s). Error: (%s)",TraceHelper.getMethodName(0), TraceHelper.getMethodName(1), TraceHelper.getMethodName(2),e.getMessage()));
		}

	}

	//Wait for Angular Load
	public static void waitForAngularLoad(WebDriver driver, int seconds) {

		try {
			WebDriverWait wait = new WebDriverWait(driver,seconds);
			JavascriptExecutor jsExec = (JavascriptExecutor) driver;

			String angularReadyScript = "return angular.element(document).injector().get('$http').pendingRequests.length === 0";

			//Wait for ANGULAR to load
			ExpectedCondition<Boolean> angularLoad = webdriver -> Boolean.valueOf(((JavascriptExecutor) driver)
					.executeScript(angularReadyScript).toString());

			//Get Angular is Ready
			boolean angularReady = Boolean.valueOf(jsExec.executeScript(angularReadyScript).toString());

			//Wait ANGULAR until it is Ready!
			if(!angularReady) {
								Logger.printInfo("ANGULAR is NOT Ready!");
				//Wait for Angular to load
				wait.until(angularLoad);
			} else {
								Logger.printInfo("ANGULAR is Ready!");
			}
		}catch (Exception e) {
			//			Logger.printError(String.format("Error found in method (%s), called from: (%s), (%s). Error: (%s)",TraceHelper.getMethodName(0), TraceHelper.getMethodName(1), TraceHelper.getMethodName(2),e.getMessage()));
		}
	}

	//Wait Until JS Ready
	public static void waitUntilJSReady(WebDriver driver, int seconds) {
		try {
			WebDriverWait wait = new WebDriverWait(driver,seconds);
			JavascriptExecutor jsExec = (JavascriptExecutor) driver;

			//Wait for Javascript to load
			ExpectedCondition<Boolean> jsLoad = webdriver -> ((JavascriptExecutor) driver)
					.executeScript("return document.readyState").toString().equals("complete");

			//Get JS is Ready
			boolean jsReady =  jsExec.executeScript("return document.readyState").toString().equals("complete");

			//Wait Javascript until it is Ready!
			if(!jsReady) {
								Logger.printInfo("JS in NOT Ready!");
				//Wait for Javascript to load
				wait.until(jsLoad);
			} else {
								Logger.printInfo("JS is Ready!");
			}
		}catch (Exception e) {
			//			Logger.printError(String.format("Error found in method (%s), called from: (%s), (%s). Error: (%s)",TraceHelper.getMethodName(0), TraceHelper.getMethodName(1), TraceHelper.getMethodName(2),e.getMessage()));
		}
	}
	
	
	public static void scrollToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
	}
	
	public static void scrollToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public static void forceClick(WebDriver driver, WebElement element) {
		try {
			int i = 0;
			while (!( element.isDisplayed()) && i++ < 30) {
				Thread.sleep(500);
			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("var evt = document.createEvent('MouseEvents');"
					+ "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
					+ "arguments[0].dispatchEvent(evt);", element);
		} catch (InterruptedException e) {
			Logger.printError(String.format("Error found in method (%s), called from: (%s), (%s). Error: (%s)",TraceHelper.getMethodName(0), TraceHelper.getMethodName(1), TraceHelper.getMethodName(2),e.getMessage()));
		}
	}

	/**
	 * Method to wait a element to be visible
	 * @param element
	 */

	public  void findElementVisible(String element) {
		getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(element))));
	}

	/**
	 * Generic Method to get text from a web element
	 * @param element
	 * @return String
	 */

	public String getText(String element) {
		getWait().until(ExpectedConditions.visibilityOf(getDriver().findElement(By.cssSelector(element))));
		return  getDriver().findElement(By.cssSelector(element)).getText();
	}


	/**
	 * Generic Method to get text from a web element
	 * @param element
	 * @return String
	 */

	public String getText(WebElement element) {

		getWait().until(ExpectedConditions.visibilityOf(element));

		String[] arrSplit= element.getText().split(":");

		String str= "";
		str = arrSplit[arrSplit.length-1];
		str= str.replaceAll("\\s","");

		return str;
	}

	/**
	 * Generic Method to get text from a web element
	 * @param element
	 * @param option
	 * @return
	 */


	/**
	 * Generic Method to compare text
	 * @param obtain
	 * @param expected
	 * @return
	 */
	public boolean validateText(String obtain, String expected) {

		return obtain.equalsIgnoreCase(expected)?true:false;
	}

	/**
	 * Generic Method to select a list or multi select web element
	 * @param element
	 * @param state
	 */
	public void selectGenericList(WebElement element, String state) {

		getWait().until(ExpectedConditions.elementToBeClickable(element));

		Select multiselect = new Select(element);
		multiselect.selectByValue(state);

	}

	/**
	 * Kill driver
	 */
	public void dispose() {

		if(driver!=null) {
			driver.quit();
		}

	}
}
