package com.declan.functionalcode;

import java.io.IOException;

public class StoringExcelData {

	String Path;
	int numOfRows;
	int numOfColumns;
	public String[][] searchData;
	ReadingExcelFile excelFile;
	public StoringExcelData(String path) throws IOException{
		this.Path = path;
		this.excelFile = new ReadingExcelFile(path);
		this.numOfRows = excelFile.getRowCount(0);
		this.numOfColumns = excelFile.getCellCount("Sheet1", numOfRows-1);
		
	}
	
	public void collectingData() {
		this.searchData = new String [numOfRows][numOfColumns];
		
		for(int row = 1; row <= numOfRows; row++) {
			
			for(int col=0; col < numOfColumns;col++) {
				this.searchData[row-1][col] = excelFile.getData("Sheet1", row, col);
			}
		
	}
	}
	public String[][] DestinationData() {
		String[][] destinationData = new String[numOfRows-1][2];
		
		for(int row=1;row<numOfRows;row++) {
			for(int col=0; col<2;col++) {
				destinationData[row-1][col] = searchData[row-1][col];
			}
		}
		return destinationData;
	}
	
	public String[][] DateData() {
		String[][] dateData = new String[numOfRows-1][1];
		
		for(int row=1;row<numOfRows;row++) {
			dateData[row-1][0] = searchData[row-1][2];
	}
		
		return dateData;
	}
	
	public String[][] TimeData() {
		String[][] timeData = new String[numOfRows-1][2];
		
		for(int row=1;row<numOfRows;row++) {
			for(int col=3; col<5;col++) {
				timeData[row-1][col-3] = searchData[row-1][col];
			}
		}
		return timeData;
	}
	public String[][] TestNumData() {
		String[][] testNumData = new String[numOfRows-1][1];
		
		for(int row=1;row<numOfRows;row++) {
			testNumData[row-1][0] = searchData[row-1][5];
	}

		return testNumData;
	}
	
	
}