package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelfile
{
XSSFWorkbook wb;
public excelfile(String excelinputpath) throws Throwable
{
	FileInputStream fi=new FileInputStream(excelinputpath);
	wb=new XSSFWorkbook(fi);
	}
public int rowcount(String sheetname)
{
	int rowsize=wb.getSheet(sheetname).getLastRowNum();
	return rowsize;
}
public int rowcount(int sheetnumber)
{
	int rowsize=wb.getSheetAt(sheetnumber).getLastRowNum();
	return rowsize;
}
public int cellcount(String sheetname)
{
	int cellsize=wb.getSheet(sheetname).getRow(0).getLastCellNum();
			return cellsize;
}
public int cellcount(int sheetnumber)
{
	int cellsize=wb.getSheetAt(sheetnumber).getRow(0).getLastCellNum();
	return cellsize;
}
public String getcelldata(String sheetname,int rownum,int cellnum)
{
	String data="";
	if(wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata=(int)wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getNumericCellValue();
		data=String.valueOf(celldata);
	
	}
	else
	{
		data=wb.getSheet(sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
	
	}
	return data;
}
public String getcelldata(int sheetnumber,int rownum,int cellnum)
{
	String data="";
	if(wb.getSheetAt(sheetnumber).getRow(rownum).getCell(cellnum).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata=(int)wb.getSheetAt(sheetnumber).getRow(rownum).getCell(cellnum).getNumericCellValue();
		data=String.valueOf(celldata);
	
	}
	else
	{
		data=wb.getSheetAt(sheetnumber).getRow(cellnum).getCell(cellnum).getStringCellValue();
	
	}
	return data;
}
public void setcelldata(String sheetname,int rownum,int columnnum,String status,String xloutputpath) throws Throwable
{
	XSSFCell cell=wb.getSheet(sheetname).getRow(rownum).createCell(columnnum);
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("pass"))
		
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		wb.getSheet(sheetname).getRow(rownum).getCell(columnnum).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("fail"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		wb.getSheet(sheetname).getRow(rownum).getCell(columnnum).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("blocked"))
	{
		XSSFCellStyle style=wb.createCellStyle();
		XSSFFont font=wb.createFont();
		font.setColor(IndexedColors.LIGHT_BLUE.getIndex());
		font.setBold(true);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		wb.getSheet(sheetname).getRow(rownum).getCell(columnnum).setCellStyle(style);
	}
	FileOutputStream fo=new FileOutputStream(xloutputpath);
			wb.write(fo);
}
public static void main(String as[]) throws Throwable
{
	excelfile xl=new excelfile("D:/liveproject.xlsx");
	int r=xl.rowcount(0);
	System.out.println(r);
	int r1=xl.rowcount("teststeps");
	System.out.println(r1);
	String s=xl.getcelldata(0, 1,1);
	System.out.println(s);
	xl.setcelldata("testcase", 1, 3, "pass","D:/sumiyen.xlsx" );
}
}
