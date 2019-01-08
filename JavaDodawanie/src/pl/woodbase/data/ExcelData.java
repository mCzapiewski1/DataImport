package pl.woodbase.data;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import pl.woodbase.dao.SqlConn;

public class ExcelData {
	
	private String id;
	private String path;
	private String stackTrace;
	private int records;
	private boolean error;
	private boolean exception;
	private SqlConn sqlConn = new SqlConn();
	public void readExcelFile(String fileName) {
		
		try {
			
			FileInputStream excelFile = new FileInputStream(new File(fileName));
			Workbook workbook = new XSSFWorkbook(excelFile);
			org.apache.poi.ss.usermodel.Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			
			while(iterator.hasNext()) {
				records++;
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				byte li = 0;
					while(cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						if(li==1) {
							path = currentCell.getStringCellValue();
						}else if(li==0) {
							id = currentCell.getStringCellValue();
						}else {
							error = true;
						}
						li++;
					}
					sqlConn.addRecord(id, path);
			}
			
		} catch (Exception e) {
			exception = true;
			e.printStackTrace();
			stackTrace = e.toString();
		}
		
	}
	
	public boolean getError() {
		return error;
	}
	public int getRecords() {
		return records;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public boolean getException() {
		return exception;
	}
	
}
