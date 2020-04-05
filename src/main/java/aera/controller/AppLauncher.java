package aera.controller;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import aera.utility.DateUtil;

/**
 * @author Arul Christo C T
 *
 */
public class AppLauncher {

	public WebDriver driver;
	public WebDriverWait wait;
	public DateUtil utility;

	/**
	 * This method is used to initialize driver properties and launch the
	 * application which is under test. And this method will be invoked one time
	 * before @test method
	 */
	@BeforeTest
	public void initApp() {
		utility = new DateUtil();
		System.setProperty("webdriver.chrome.driver", "C:\\Work\\New folder\\AutoEval\\driver\\chromedriver.exe");
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();
		driver.get("https://www.hotwire.com/");// all the strings can be moved to properties files
	}

	/**
	 * This method is used to close the open browsers and other resources and this
	 * will be invoked after @Test method for one time.
	 */
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
