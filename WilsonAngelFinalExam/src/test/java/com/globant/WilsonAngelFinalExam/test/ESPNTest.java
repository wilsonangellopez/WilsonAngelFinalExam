package com.globant.WilsonAngelFinalExam.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.globant.WilsonAngelFinalExam.pages.MainPage;

/**
 * 
 * @author wilson.angel
 *
 */
public class ESPNTest extends BaseTest{

	MainPage mainPage;
	Assertion myAssert = new Assertion();

	/**
	 * Init base page
	 */

	@BeforeTest
	public void setUp() {
		mainPage= new MainPage(driver);
		
	}


	/**
	 * Test to login a espn user
	 * @param browser
	 * @param email
	 * @param password
	 * */
	 
	@Parameters({"browser"})
	@Test(dataProvider="data",priority=1)
	public void loginESPN(String email,
			String pass,
			String wilson,
			String login) {

		myAssert.assertTrue(mainPage.searchMenuOptionIcon("menuOption"),"The page doesn't display menu option");
		mainPage.clickMenuOption();
		myAssert.assertTrue(mainPage.searchMenuOptionIcon("loginLink"),"The page doesn't display login link");
		mainPage.clickLoginOption();
		
		
		myAssert.assertTrue(mainPage.swithToFrame(),"The system doesn't display the iFrame");
		
		mainPage.writeEmail(email);
		mainPage.writePassword(pass);
		mainPage.clickBtnLoginIFrame();
		mainPage.goToDefaultContent();
		
		mainPage.clickMenuOption();
		mainPage.moveToWebElement("logedUser");
		myAssert.assertTrue(mainPage.validateText(login,wilson),"The user is not logged");
		
	}
	

	
	
	@Parameters({"browser"})
	@Test(dataProvider="data", priority=2)
	public void deleteAccount(String email,
			String pass,
			String wilson,
			String login,
			String delete,
			String deleteMesagge,
			String espnProfile) {

		myAssert.assertTrue(mainPage.searchMenuOptionIcon("menuOption"),"The page doesn't display menu option");
		mainPage.clickMenuOption();
		myAssert.assertTrue(mainPage.searchMenuOptionIcon("loginLink"),"The page doesn't display login link");
		mainPage.clickLoginOption();
		myAssert.assertTrue(mainPage.swithToFrame(),"The system doesn't display the iFrame");
		
		mainPage.writeEmail(email);
		mainPage.writePassword(pass);
		mainPage.clickBtnLoginIFrame();
		mainPage.goToDefaultContent();
		
		mainPage.clickMenuOption();
		
		mainPage.moveToWebElement(espnProfile);
		myAssert.assertTrue(mainPage.elementIsPresent(),"The profile Option is not present in the web page");
		mainPage.clickEspnProfile();
		myAssert.assertTrue(mainPage.swithToFrame(),"The system doesn't display the iFrame");

		mainPage.scrollButtomPage();
		mainPage.scrollToElementInFrame();
		mainPage.waitFordeleteAccountink();
		mainPage.deleteAccountLinkClick();
		myAssert.assertTrue(mainPage.validateText(delete,deleteMesagge),"The user is not possible to delete");
		mainPage.clickBtnCloseIFrame();
		mainPage.goToDefaultContent();
		
		
	}

	@AfterTest()
	public void LogOut() {
		
		myAssert.assertTrue(mainPage.searchMenuOptionIcon("menuOption"),"The page doesn't display menu option");
		mainPage.clickMenuOption();
		mainPage.clickLogOut();
		
	}


	/**
	 * Data provider
	 * @return array
	 */

	 @DataProvider(name = "data")
	    public Object[][] testUsers(){
	        Object [][] users=new Object[1][7];
	        users[0][0]="some@gg.com";
	        users[0][1]="w123123";
	        users[0][2]="wilson!";
	        users[0][3]="Login";
	        users[0][4]="Delete";
	        users[0][5]="Yes, delete this account";
	        users[0][6]="espnProfile";
	        
	        return users;
	    }
	/**
	 * Close the driver when the test class finish
	 */


	@AfterClass
	public void afterClass() {
		//driver.close();
	}



}
