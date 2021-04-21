package com.globant.WilsonAngelFinalExam.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import net.bytebuddy.asm.Advice.FieldValue;
/**
 *  Main class to map the elements, methods to campare, select lists and buttons and call the base page to init web elements
 * @author wilson.angel
 *
 */
public class MainPage extends BasePage {


	@FindBy(css="a#global-user-trigger")
	private WebElement menuOptionIcon;

	@FindBy(css= "ul.tools.focused ul li a[data-affiliatename='espn']")
	private WebElement loginLink;
	
	@FindBy(css= "ul.tools.focused ul li a.small")
	private WebElement logOutLink;

	@FindBy(css="iframe#disneyid-iframe")
	private WebElement iFrameLogin;

	@FindBy(css="span input[type='email']")
	private WebElement iFrameTxtEmail;

	@FindBy(css="span input[type='password']")
	private WebElement iFrameTxtPassword;

	@FindBy(css="button.btn.btn-primary.btn-submit.ng-isolate-scope")
	private WebElement iFrameBtnLogIn;

	@FindBy(css="div.block.block-service-error-message p.ng-isolate-scope")
	private WebElement iFrameErrorMessage;

	@FindBy(css="div.container li.display-user span")
	private WebElement logedUser;

	//---
	@FindBy(id="global-header")
	private WebElement header;

	//----- CANCEL SELECTORS

	@FindBy(css="#global-header ul.account-management li:nth-child(5) a")
	private WebElement espnProfile;

	@FindBy(css="a#cancel-account")
	private WebElement deleteAccountLink;

	@FindBy(css="section.section-divider.section-usage-statement p.ng-isolate-scope a")
	private WebElement privacyPolicy;


	@FindBy(css="button.btn.btn-primary.ng-isolate-scope")
	private WebElement btndeleteAccountConfirmation;

	//-iframe

	@FindBy(css="div.promo-banner-container")
	private WebElement bannerPromo;

	@FindBy(css="a.action.ng-isolate-scope[title='Edit your display name.']")
	private WebElement editOptionPrueba;

	@FindBy(css="div#did-ui button#close")
	private WebElement closeIframe;
	




	/**
	 * Main class to map the elements and call the base page to init web elements
	 * 
	 * @author wilson.angel
	 * @param pDriver
	 */
	public MainPage(WebDriver pDriver) {
		super(pDriver);

	}

	public void clickMenuOption() {
		
		waitForMenuLoad();

		if(iFrameLogin.isDisplayed()) {
			getDriver().switchTo().defaultContent();
		}
		menuOptionIcon.click();

	}

	/**
	 * 
	 */
	public void waitForMenuLoad() {
		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(header, By.cssSelector("li.user")));
		getWait().until(ExpectedConditions.invisibilityOf(iFrameLogin));
		getWait().until(ExpectedConditions.elementToBeClickable(menuOptionIcon));
	}



	public void moveToWebElement(String option) {

		if(option.equalsIgnoreCase("logedUser")) {
			moveToElement(logedUser);
		}

			
		if(option.equalsIgnoreCase("espnProfile")) {//espnProfile
			moveToElement(espnProfile);
		}

	}


	/**
	 * 
	 */


	public boolean searchMenuOptionIcon(String option) {

		
		if(option.equalsIgnoreCase("menuOption")) {
		
			getWait().until(ExpectedConditions.visibilityOf(menuOptionIcon));
			return menuOptionIcon.isDisplayed();
		}
		
		if(option.equalsIgnoreCase("loginLink")) {
			
			getWait().until(ExpectedConditions.visibilityOf(loginLink));
			return loginLink.isDisplayed();

		}else return false;




	}

	//-------------------------
	public void clickLoginOption() {

		getWait().until(ExpectedConditions.elementToBeClickable(loginLink));
		moveToElement(loginLink);
		loginLink.click();
	}
	
	public void clickLogOut() {
		getWait().until(ExpectedConditions.elementToBeClickable(logOutLink));
		moveToElement(logOutLink);
		logOutLink.click();
	}

	public void deleteAccountLinkClick() {

		getWait().until(ExpectedConditions.elementToBeClickable(deleteAccountLink));
		forceClick(getDriver(), deleteAccountLink);
	}
	
	public void clickBtnCloseIFrame() {
		getWait().until(ExpectedConditions.elementToBeClickable(closeIframe));
		closeIframe.click();
	}
	 
	public void clickEspnProfile() {

		getWait().until(ExpectedConditions.visibilityOf(espnProfile));
		espnProfile.click();
	}

	public void clickBtnLoginIFrame() {

		getWait().until(ExpectedConditions.visibilityOf(iFrameBtnLogIn));
		iFrameBtnLogIn.click();
		getDriver().switchTo().defaultContent();
	}




	public void genericClick(String option) {

		if(option.equalsIgnoreCase("loginLink")) {

			moveToElement(loginLink);
			loginLink.click();
		}

		if(option.equalsIgnoreCase("Delete")) {

			getWait().until(ExpectedConditions.elementToBeClickable(deleteAccountLink));
			forceClick(getDriver(), deleteAccountLink);
		}

		if(option.equalsIgnoreCase("espnProfile")) {

			getWait().until(ExpectedConditions.visibilityOf(espnProfile));
			espnProfile.click();
		}

		if(option.equalsIgnoreCase("iFrameBtnLogIn")) {

			getWait().until(ExpectedConditions.visibilityOf(iFrameBtnLogIn));
			iFrameBtnLogIn.click();
			getDriver().switchTo().defaultContent();
		}

	}

	//---------

	public boolean swithToFrame() {

		getDriver().switchTo().frame(getDriver().findElement(By.name("disneyid-iframe")));
		return true;


	}

	public void writeInTextInput(String option) {


		if(option.equalsIgnoreCase("some@gg.com")) {

			getWait().until(ExpectedConditions.visibilityOf(iFrameTxtEmail));
			iFrameTxtEmail.sendKeys("some@gg.com");
		}
		if(option.equalsIgnoreCase("w123123")) {

			getWait().until(ExpectedConditions.visibilityOf(iFrameTxtPassword));
			iFrameTxtPassword.sendKeys("w123123");
		}


	}

	public void writeEmail(String email) {

		getWait().until(ExpectedConditions.visibilityOf(iFrameTxtEmail));
		iFrameTxtEmail.sendKeys(email);
	}

	public void writePassword(String pass) {

		getWait().until(ExpectedConditions.visibilityOf(iFrameTxtPassword));
		iFrameTxtPassword.sendKeys(pass);
	}



	public boolean validateErrorMessageIFrame() {

		return iFrameErrorMessage.isDisplayed()?true:false;
	}

	public boolean validateText(String option, String mesagge) {


		waitForPageToBeLoadedCompletely(getDriver(),60);

		if(option.equalsIgnoreCase("Login")) {
			moveToElement(logedUser);
			return logedUser.getText().equalsIgnoreCase(mesagge)?true:false;
			//return logedUser.getText().equalsIgnoreCase(option)?true:false;
			
		}
		
		//if(option.equalsIgnoreCase("Yes, delete this account")) {
		if(option.equalsIgnoreCase("Delete")) {
			
			moveToElement(btndeleteAccountConfirmation);
			return btndeleteAccountConfirmation.getText().equalsIgnoreCase(mesagge)?true:false;
			//return btndeleteAccountConfirmation.getText().equalsIgnoreCase(option)?true:false;
		}else return false;

	}

	public boolean elementIsPresent() {
		return isElementPresent(getDriver(), By.cssSelector("#global-header ul.account-management li:nth-child(5) a"), 30);
	}





	public void deleteConfirmation() {
		getWait().until(ExpectedConditions.visibilityOf(btndeleteAccountConfirmation));
		btndeleteAccountConfirmation.click();



	}

	public void scrollToElementInFrame() {

		getWait().until(ExpectedConditions.visibilityOf(privacyPolicy));
		scrollToElement(getDriver(), privacyPolicy);

	}

	public void scrollButtomPage() {

		scrollToBottomPage(getDriver());

	}

	public void waitFordeleteAccountink() {
		getWait().until(ExpectedConditions.presenceOfNestedElementLocatedBy(By.cssSelector("section.main div.field-group:nth-child(8)"), By.cssSelector("a#cancel-account")));

	}


	/**
	 * Generic method to select list and multi select elements
	 * @param option


	public void selectOneList(String option) {


		if(option.equalsIgnoreCase("Texas")) {
			selectGenericList(multiSelect, option);	

		}else {
			selectGenericList(selectListDay, option);
		}
	} */

	/**
	 * Method to select button from multiSelect web element 
	 * @return

	public boolean clickFirstSelectedBtn() {

		getWait().until(ExpectedConditions.presenceOfElementLocated(By.id("printMe")));

		if(firstBtnSelected.isDisplayed()&& firstBtnSelected.isEnabled()) {
			firstBtnSelected.click();
			return true;	
		}
		return false;

	}*/

	/**
	 * Generic Method to obtain and verify the text from select or multi select  
	 * @param option
	 * @param webElement
	 * @return boolean

	public boolean verifyText(String option) {


		if(option.equalsIgnoreCase("Texas")) {

			return validateText(getText(MultiSelectText,option), option);


		}else {

			return validateText(getText(selectedTextDay,option), option);
		}

	}*/

}
