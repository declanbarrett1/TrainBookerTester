package com.declan.testscripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;

public class test {
	

	static WebDriver driver;
	static String BaseURL = "https://www.thetrainline.com/";
	Actions action;
	String chromeDriverLocation = "/Users/declanbarrett/Documents/Eclipse Projects/Selenium/Train Booking Tester/TrainBooking/src/test/resources/drivers/chromedriver";
	
	public static void main(String[] args) throws InterruptedException {
		changeTimeOfLeavingTrain();
	}
	public static void changeTimeOfLeavingTrain() throws InterruptedException {
	driver = new SafariDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(BaseURL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		List<WebElement> cookieAccept = driver.findElements(By.id("onetrust-accept-btn-handler"));
		if(cookieAccept.size() !=0) {
			
			cookieAccept.get(0).click();
		}
		Thread.sleep(4000);
		WebElement returnButton = driver.findElement(By.id("return"));
		returnButton.click();
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
			
			driver.quit();
	}
//	
}
