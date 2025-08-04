package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelRead {
	static FileInputStream f;
	static XSSFWorkbook wb;
	static XSSFSheet sh;
	
	public static String getLoginData(String path, String sheetName, int row,int column) throws IOException // a represents row value and b represents column value
	{
		f=new FileInputStream(path);
		wb= new XSSFWorkbook(f);
		sh=wb.getSheet(sheetName);
		XSSFRow r =sh.getRow(row);
		XSSFCell c = r.getCell(column);
		return c.getStringCellValue();
	}
}