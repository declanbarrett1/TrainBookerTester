package com.declan.functionalcode;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Test2 {
	public static void main(String[] args) throws IOException {
		getDestData();
		getDateData();
		getTimeData();
		getTestNumData();
	}
	
	public static String [][] getDestData() throws IOException{
		StoringExcelData excelData = new StoringExcelData("/Users/declanbarrett/Documents/Eclipse Projects/Selenium/Train Booking Tester/TrainBooking/src/test/resources/data/data.xlsx");
		excelData.collectingData();
		String data[][] = excelData.DestinationData();
		System.out.println(data[0][0]);
		return data;
	}
	public static String [][] getDateData() throws IOException{
		StoringExcelData excelData = new StoringExcelData("/Users/declanbarrett/Documents/Eclipse Projects/Selenium/Train Booking Tester/TrainBooking/src/test/resources/data/data.xlsx");
		excelData.collectingData();
		String data[][] = excelData.DateData();
		System.out.println(data[0][0]);
		return data;
	}
	public static String [][] getTimeData() throws IOException{
		StoringExcelData excelData = new StoringExcelData("/Users/declanbarrett/Documents/Eclipse Projects/Selenium/Train Booking Tester/TrainBooking/src/test/resources/data/data.xlsx");
		excelData.collectingData();
		String data[][] = excelData.TimeData();
		System.out.println(data[0][0]);
		return data;
	}

	public static String [][] getTestNumData() throws IOException{
		StoringExcelData excelData = new StoringExcelData("/Users/declanbarrett/Documents/Eclipse Projects/Selenium/Train Booking Tester/TrainBooking/src/test/resources/data/data.xlsx");
		excelData.collectingData();
		String data[][] = excelData.TestNumData();
		System.out.println(data[0][0]);
		return data;
}
}
