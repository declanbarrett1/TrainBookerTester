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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.declan.functionalcode.ReadingExcelFile;


public class quicktest {

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
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test(priority=1, dataProvider="SearchData")
	public void Test001_ClickCookie(String from, String to, String date, String hour, String minute, String testnum) throws InterruptedException, IOException {
		driver.get(BaseURL);
		
		List<WebElement> cookieAccept = driver.findElements(By.id("onetrust-accept-btn-handler"));
		if(cookieAccept.size() !=0) {
			
			cookieAccept.get(0).click();
		}
		
		//Initialising all elements
		WebElement fromStationInput = driver.findElement(By.xpath("//input[@placeholder=\"Enter origin station...\"]"));
		WebElement toStationInput = driver.findElement(By.xpath("//input[@placeholder='Enter destination station...']"));
		
		fromStationInput.sendKeys(from);
		toStationInput.sendKeys(to);
		toStationInput.sendKeys(Keys.TAB);
		Thread.sleep(2000);
		
		WebElement fromDateInput = driver.findElement(By.xpath("//input[@value=\"19-Jul-22\"]"));
		
		
		fromDateInput.sendKeys(Keys.CLEAR);
		Thread.sleep(2000);
		
		fromDateInput.sendKeys(date);
		fromDateInput.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
	
	
			Select hours =new Select(driver.findElement(By.name("hours")));
			Select minutes =new Select(driver.findElement(By.name("minutes")));
			hours.selectByVisibleText(hour);
			minutes.selectByVisibleText(minute);
			
			Thread.sleep(3000);
	
	
		WebElement search = driver.findElement(By.xpath("//*[@id=\"app\"]/div/div[2]/main/div[2]/div[4]/div/div/div[1]/section/form/div[5]/button"));
		search.click();
		Thread.sleep(5000);
	

		String fileWithPath = "/Users/declanbarrett/Documents/Eclipse Projects/Selenium"
				+ "/Train Booking Tester/TrainBooking/src/test/resources/screenshots/screenshot"+testnum+".png";
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	
		WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
		submitButton.click();
	}
	
	
	@DataProvider(name="SearchData")
	public String [][] getData() throws IOException{
		
		// Get Data from excel
		String path = "/Users/declanbarrett/Documents/Eclipse Projects/Selenium/Train Booking Tester/TrainBooking/src/test/resources/data/data.xlsx";
		ReadingExcelFile excelFile = new ReadingExcelFile(path);
		int numOfRows = excelFile.getRowCount(0);
		
		int numOfColumns = excelFile.getCellCount("Sheet1", numOfRows-1);
	
		String [][] searchData = new String [numOfRows][numOfColumns];
		
		for(int row = 1; row <= numOfRows; row++) {
			
			for(int col=0; col < numOfColumns;col++) {
				searchData[row-1][col] = excelFile.getData("Sheet1", row, col);
			}
		}

		
		
		return searchData;
		
	}

	
}
