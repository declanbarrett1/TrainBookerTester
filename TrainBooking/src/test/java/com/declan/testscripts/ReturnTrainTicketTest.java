package com.declan.testscripts;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReturnTrainTicketTest {
	
		WebDriver driver;
		String BaseURL = "https://www.thetrainline.com/";
		Actions action;
		String chromeDriverLocation = "/Users/declanbarrett/Documents/Eclipse Projects/Selenium/Train Booking Tester/TrainBooking/src/test/resources/drivers/chromedriver";
		
		@BeforeTest
		public void openBrowser() {
			//System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
			//driver = new ChromeDriver();
			driver = new SafariDriver();
			
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.get(BaseURL);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		
		@Test(priority=1)
		public void Test001_ClickCookie() throws InterruptedException {
			WebElement cookieAccept = driver.findElement(By.id("onetrust-accept-btn-handler"));
			cookieAccept.click();
			
			
		}
		
		@Test(priority=2)
		public void InputTrainStationDetails() throws InterruptedException {
			//Initialising all elements
			WebElement fromStationInput = driver.findElement(By.xpath("//input[@placeholder=\"Enter origin station...\"]"));
			WebElement toStationInput = driver.findElement(By.xpath("//input[@placeholder='Enter destination station...']"));
			
			fromStationInput.sendKeys("bexley");
			toStationInput.sendKeys("sidcup");
			toStationInput.sendKeys(Keys.TAB);
			
			
			
		}
		
		@Test(priority=3)
		public void returnOrSingle() throws InterruptedException {
			WebElement returnButton = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/main/div[2]/div[4]/div/div/div[1]/section/form/fieldset/div/label[2]"));
			returnButton.click();
			if(returnButton.isSelected()) {
				System.out.println("Return");
			}
			
			
		}
		
		@Test(priority=4)
		public void changeDateofLeavingTrains() throws InterruptedException {
		
			WebElement fromDateInput = driver.findElement(By.xpath("//input[@value=\"20-Jul-22\"]"));
			
			
			fromDateInput.sendKeys(Keys.CLEAR);
			
			fromDateInput.sendKeys("22-Jul-22");
			fromDateInput.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
		}
		@Test(priority=5)
		public void changeDateofReturningTrains() throws InterruptedException {
		
			WebElement toDateInput = driver.findElement(By.name("page.journeySearchForm.inbound.title"));
			
			
			toDateInput.sendKeys(Keys.CLEAR);
			
			toDateInput.sendKeys("23-Jul-22");
			toDateInput.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			
		}
		
		
		@Test(priority=6)
		public void changeTimeOfLeavingTrain() throws InterruptedException {
			List<WebElement> hours = driver.findElements(By.name("hours"));
			List<WebElement> minutes = driver.findElements(By.name("minutes"));
			System.out.println(hours.size());
			WebElement hoursLeaving = hours.get(0);
			WebElement minutesLeaving =minutes.get(0);
			hoursLeaving.sendKeys("12");
			minutesLeaving.sendKeys("30");
				
			Thread.sleep(3000);
		
			WebElement hoursReturning = hours.get(1);
			WebElement minutesReturning = minutes.get(1);
			hoursReturning.sendKeys("14");
			minutesReturning.sendKeys("15");
				
			Thread.sleep(3000);
		}
		
		
		@Test(priority=6)
		public void searchForTrains() throws InterruptedException {
			WebElement search = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/main/div[2]/div[4]/div/div/div[1]/section/form/div[5]/button"));
			search.click();
			Thread.sleep(10000);
		}
		
		@Test(priority=7)
		public void takeScreenShot() throws IOException {
			String fileWithPath = "/Users/declanbarrett/Documents/Eclipse Projects/Selenium"
					+ "/Train Booking Tester/TrainBooking/src/test/resources/screenshots/screenshot1.png";
			TakesScreenshot scrShot =((TakesScreenshot)driver);
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(fileWithPath);
			FileUtils.copyFile(SrcFile, DestFile);
		}
		
		@Test(priority=8)
		public void searchTickets() {
			WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
			submitButton.click();
		}
		@AfterTest
		public void quitBrowser() {
			driver.quit();
		}
		

}
