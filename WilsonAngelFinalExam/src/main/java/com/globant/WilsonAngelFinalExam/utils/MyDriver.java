package com.globant.WilsonAngelFinalExam.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyDriver {

	private WebDriver driver;
	/**
	 * Constructor to set up chrome and firefox driver
	 * @param browser
	 */
	public MyDriver(String browser) {

		switch (browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			break;

		default:
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
			break;
		}
		driver.manage().window().maximize();

	}

	public WebDriver getDriver() {
		return this.driver;
	}
}





