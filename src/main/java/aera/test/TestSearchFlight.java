package aera.test;

import java.text.ParseException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import aera.controller.AppLauncher;

/**
 * @author Arul Christo C T
 *
 */
public class TestSearchFlight extends AppLauncher {

	/**
	 * This method is used to do the needed validations and this will be invoked
	 * after @BeforeTest from AppLauncher.
	 * 
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	@Test
	public void bookFlightTicket() throws ParseException {
		driver.findElement(By.linkText("Vacations")).click();// to click the Vacations link button
		wait = new WebDriverWait(driver, 5);// explicit wait
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='hw-btn hw-btn-check']"))).click();
		selectFlyingLocations();// method to select flying locations
		selectDepartAndReturnDate(1, 21); // method to set depart and return dates dynamically.This data can be moved to
											// excel and make it more dynamic
		selectDepartAndReturnTime(); // method to set depart and return times
		driver.findElement(By.xpath("//*[@id=\"farefinder-package-search-button\"]")).click(); // to click search button
		validateSearchResult(); // to validate the result based on input
	}

	/**
	 * This method is used to set the origin and destination locations
	 * 
	 * @throws InterruptedException
	 */
	private void selectFlyingLocations() {
		driver.findElement(By.xpath("//*[@id=\"farefinder-package-origin-location-input\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"farefinder-package-origin-location-input\"]")).sendKeys("SFO");
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@title='San Francisco, CA, United States of America (SFO - San Francisco Intl.)']")))
				.click();

		driver.findElement(By.xpath("//*[@id=\"farefinder-package-destination-location-input\"]")).click();
		driver.findElement(By.xpath("//*[@id=\"farefinder-package-destination-location-input\"]")).sendKeys("LAX");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title='Los Angeles, CA']"))).click();
	}

	/**
	 * This method is used to set the departure and return dates dynamically as per
	 * the test case.
	 * 
	 * @param dept
	 *            - no of days from current date
	 * @param ret
	 *            - no of days from current date
	 * @throws InterruptedException
	 * @throws ParseException
	 */
	private void selectDepartAndReturnDate(int dept, int ret) throws ParseException  {
		String currDate = driver.findElement(By.xpath("//*[@id=\"farefinder-package-startdate-input\"]"))
				.getAttribute("value");
		String newDeptDate = utility.dateCalculation(currDate, dept);
		driver.findElement(By.xpath("//*[@id=\"farefinder-package-startdate-input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"farefinder-package-startdate-input\"]")).sendKeys(newDeptDate);
		String newReturnDate = utility.dateCalculation(currDate, ret);
		driver.findElement(By.xpath("//*[@id=\"farefinder-package-enddate-input\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"farefinder-package-enddate-input\"]")).sendKeys(newReturnDate);
	}

	/**
	 * This method is used to select the departure and return times
	 */
	private void selectDepartAndReturnTime() {
		driver.findElement(By.xpath("//*[@id=\"farefinder-package-pickuptime-input\"]")).click();
		Select deptReturnTime = new Select(
				driver.findElement(By.xpath("//*[@id=\"farefinder-package-pickuptime-input\"]")));
		deptReturnTime.selectByVisibleText("Evening");

		driver.findElement(By.xpath("//*[@id=\"farefinder-package-dropofftime-input\"]")).click();
		deptReturnTime = new Select(driver.findElement(By.xpath("//*[@id=\"farefinder-package-dropofftime-input\"]")));
		deptReturnTime.selectByVisibleText("Morning");
	}

	/**
	 * This method is used to validate the search results
	 */
	private void validateSearchResult() {
		wait = new WebDriverWait(driver, 25);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@id=\"resultsContainer\"]/section/article")));
		List<WebElement> searchList = driver.findElements(By.xpath("//*[@id=\"resultsContainer\"]/section/article"));
		Assert.assertTrue(searchList.size() >= 1, "Testcase Passed");
	}
}
