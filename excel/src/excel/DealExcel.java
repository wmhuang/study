package excel;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.DateCell;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * jxl读excel
 * 
 * @author weijing
 * 
 */
public class DealExcel {
	public static void main(String[] args) throws BiffException, IOException,
			RowsExceededException, WriteException, ParseException {

		File xlsFile = new File("D:/excelTest/excelFiles");
		if (xlsFile.isDirectory()) {
			File files[] = xlsFile.listFiles();
			System.out.println("该文件夹中一共" + files.length + "个excel文件。");
			for (int i = 0; i < files.length; i++) {
				File file = files[i];

				System.out.println("正在处理第" + (i + 1) + "个文件:[" + file.getName()
						+ "]");

				Workbook book = Workbook.getWorkbook(file);
				WritableWorkbook wbook = Workbook.createWorkbook(file, book);//

				// 获得所有工作表
				WritableSheet[] sheets = wbook.getSheets();

				// 遍历工作表
				if (sheets != null) {

					for (WritableSheet sheet : sheets) {
						String sheetName = sheet.getName();
						System.out.println("正在处理" + sheetName);
						// 获得行数
						int rows = sheet.getRows();
						System.out.println("共"+rows+"行数据");
						 int delRows=0;
						// 读取数据
						for (int a = 1; a < rows; a++) {
							if(!sheet.getCell(1,a).getContents().isEmpty()){
								DateCell date = (DateCell)sheet.getCell(1,a);
								Date b = date.getDate();
								Long mm = b.getTime();
								SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
							 
								Long startTime = formatDate.parse("2017/11/23 00:00:00").getTime();
								Long endTime = formatDate.parse("2017/11/28 00:00:00").getTime();
								if (mm < startTime || mm > endTime) {
									delRows++;
									sheet.removeRow(a);
								}
							}
						}
						System.out.println("删除"+delRows+"行数据");
					}
					wbook.write();
					wbook.close();
					book.close();
				}
			}
			System.out.println("处理完成");
		}
	}
}