package com.declan.testscripts;

import java.io.File;
import java.io.IOException;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.declan.functionalcode.ReadingExcelFile;


public class MainGUITest {

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
	
	@Test(priority=2, dataProvider="SearchData")
	public void InputTrainStationDetails(String from, String to) throws InterruptedException {
		//Initialising all elements
		WebElement fromStationInput = driver.findElement(By.xpath("//input[@placeholder=\"Enter origin station...\"]"));
		WebElement toStationInput = driver.findElement(By.xpath("//input[@placeholder='Enter destination station...']"));
		
		fromStationInput.sendKeys(from);
		toStationInput.sendKeys(to);
		toStationInput.sendKeys(Keys.TAB);
		
		
		
	}
	
	@Test(priority=3, dataProvider="SearchData")
	public void changeDateofTrains(String date) throws InterruptedException {
	
		WebElement fromDateInput = driver.findElement(By.xpath("//input[@value=\"19-Jul-22\"]"));
		
		
		fromDateInput.sendKeys(Keys.CLEAR);
		
		fromDateInput.sendKeys(date);
		fromDateInput.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	}
	
	@Test(priority=4, dataProvider="SearchData")
	public void changeTimeOfTrain(String hour, String minute) throws InterruptedException {
			Select hours =new Select(driver.findElement(By.name("hours")));
			Select minutes =new Select(driver.findElement(By.name("minutes")));
			hours.selectByVisibleText(hour);
			minutes.selectByVisibleText(minute);
			
			Thread.sleep(3000);
	}
	
	
	@Test(priority=5)
	public void searchForTrains() throws InterruptedException {
		WebElement search = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/main/div[2]/div[4]/div/div/div[1]/section/form/div[5]/button"));
		search.click();
		Thread.sleep(5000);
	}
	
	@Test(priority=6)
	public void takeScreenShot() throws IOException {
		String fileWithPath = "/Users/declanbarrett/Documents/Eclipse Projects/Selenium"
				+ "/Train Booking Tester/TrainBooking/src/test/resources/screenshots/screenshot1.png";
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}
	
	@Test(priority=7)
	public void searchTickets() {
		WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
		submitButton.click();
	}
	@AfterTest
	public void quitBrowser() {
		driver.quit();
	}
	
	@DataProvider(name="SearchData")
	public String [][] getData() throws IOException{
		
		// Get Data from excel
		String path = "/Users/declanbarrett/Documents/Eclipse Projects/Selenium/Train Booking Tester/TrainBooking/src/test/resources/data/data.xlsx";
		ReadingExcelFile excelFile = new ReadingExcelFile(path);
		int numOfRows = excelFile.getRowCount(0);
		System.out.println(numOfRows);
		int numOfColumns = excelFile.getCellCount("Sheet1", numOfRows-1);
		System.out.println(numOfColumns);
		String [][] searchData = new String [numOfRows][numOfColumns];
		
		for(int row = 1; row <= numOfRows; row++) {
			
			for(int col=0; col < numOfColumns;col++) {
				searchData[row-1][col] = excelFile.getData("Sheet1", row, col);
			}
		}
		
		
		return searchData;
		
	}
	
}
