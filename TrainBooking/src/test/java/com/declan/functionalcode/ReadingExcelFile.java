package com.declan.functionalcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingExcelFile {

	XSSFWorkbook work_book;
	XSSFSheet sheet;
	String path = null;
	XSSFRow rowElement;
	public ReadingExcelFile(String excelfilePath) {
	try {
	File s = new File(excelfilePath);
	FileInputStream stream = new FileInputStream(s);
	work_book = new XSSFWorkbook(stream);
	}
	catch(Exception e) {
	System.out.println(e.getMessage());
	this.path = excelfilePath;
	
	}
	}
	
	
	public String getData(String sheetName, int row, int column){
		String data = null;
		sheet = work_book.getSheet(sheetName);
		XSSFRow currentRow = sheet.getRow(row);
		if (currentRow != null) {
			Cell cell = currentRow.getCell(column);
			cell.setCellType(CellType.STRING);
			data = cell.getStringCellValue();
				}
		
		return data;
	} 
	
	public int getCellCount(String sheetName, int rownum) throws IOException{
		sheet = work_book.getSheet(sheetName);
		rowElement = sheet.getRow(rownum);
		int cellCount = rowElement.getLastCellNum();
		return cellCount;
	}
	
	
	public int getRowCount(int sheetIndex){
	int row = work_book.getSheetAt(sheetIndex).getLastRowNum();
	row = row + 1;
	return row;
	}
}
