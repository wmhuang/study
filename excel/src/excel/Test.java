package excel;

import java.io.File;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  
  
import jxl.Cell;  
import jxl.Workbook;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook; 

public class Test {
	public static void main(String[] args) throws Exception {
		String filePath = "D:/excelTest/excelFiles/中药材-阴凉库.xls";
		File file  = new File(filePath);
	
		Workbook book = Workbook.getWorkbook(new File(filePath));
		WritableWorkbook wbook = Workbook.createWorkbook(new File(filePath),
				book);//

		WritableSheet sheet = (WritableSheet) wbook.getSheet(0);
		System.out.println(sheet.getName());
		 sheet.removeRow(1);
		wbook.write();
		wbook.close();
		book.close();
	}
}
