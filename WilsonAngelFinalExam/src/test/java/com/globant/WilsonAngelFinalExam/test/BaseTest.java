package com.globant.WilsonAngelFinalExam.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.globant.WilsonAngelFinalExam.utils.MyDriver;

public class BaseTest {
	

	
	protected MyDriver mydriver;
	protected WebDriver driver;
	
	@BeforeSuite(alwaysRun=true)
	@Parameters({"browser"})	
	public void beforeSuite(String browser) {

		mydriver= new MyDriver(browser);
		driver= mydriver.getDriver();
		driver.get("https://www.espnqa.com/?src=com&_adblock=true&espn=cloud");
	}
}
