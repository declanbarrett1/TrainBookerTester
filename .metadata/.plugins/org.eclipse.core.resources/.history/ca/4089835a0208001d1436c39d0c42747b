package com.declan.functionalcode;

import java.io.IOException;

public class TestingReadingFile {

	public static void main(String[] args) throws IOException {
		
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
		
		for(int i=0; i< searchData.length; i++) {
			for (int j = 0; j<5;j++) {
				System.out.println(searchData[i][j]);
			}
			
		}
		

		
	}
}
