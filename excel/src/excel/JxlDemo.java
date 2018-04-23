package excel;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
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
public class JxlDemo {
	public static void main(String[] args) throws BiffException, IOException,
			RowsExceededException, WriteException {
		File xlsFile = new File("D:/excelTest/excelFiles");
		if (xlsFile.isDirectory()) {
			File files[] = xlsFile.listFiles();
			System.out.println("该文件夹中一共" + files.length + "个excel文件。");
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				
				//创建汇总文件
				File writeFile = new File("D:/excelTest/"+file.getName()+"_汇总.xls");
				if (!writeFile.exists()) {
					writeFile.createNewFile();
				}
				WritableWorkbook workbook1 = Workbook.createWorkbook(writeFile);

				WritableSheet sheetWd = workbook1.createSheet("温度汇总", 0);
				WritableSheet sheetSd = workbook1.createSheet("湿度汇总", 1);
				

				System.out.println("正在合并第" + (i + 1) + "个文件:[" + file.getName()
						+ "]");

				// 获得工作簿对象
				Workbook workbook = Workbook.getWorkbook(file);
				// 获得所有工作表
				Sheet[] sheets = workbook.getSheets();
				int sheetSize = sheets.length;
				System.out.println("该文件总共" + sheetSize + "个sheet");

				// 遍历工作表
				if (sheets != null) {
					int currentSheet = 0;
					for (Sheet sheet : sheets) {
						String sheetName = sheet.getName();
						System.out.println("正在处理"+sheetName);
						// 获得行数
						int rows = sheet.getRows();
						// 读取数据

						// 读取第一个Sheet时添加前几列
						if (currentSheet == 0) {
							for (int row = 0; row < rows; row++) {
								for (int col = 0; col < 3; col++) {
									sheetWd.addCell(new Label(col, row, sheet
											.getCell(col, row).getContents()));
									sheetSd.addCell(new Label(col, row, sheet
											.getCell(col, row).getContents()));
								}
							}
						}

						for (int row = 0; row < rows; row++) {
							if (row == 0) {
								sheetWd.addCell(new Label(currentSheet + 3,
										row, sheetName));
								sheetSd.addCell(new Label(currentSheet + 3,
										row, sheetName));
							} else {
								sheetWd.addCell(new Label(currentSheet + 3,
										row, sheet.getCell(3, row)
												.getContents()));
								sheetSd.addCell(new Label(currentSheet + 3,
										row, sheet.getCell(4, row)
												.getContents()));
							}

						}
						currentSheet++;
					}
				}
				workbook.close();
				workbook1.write();
				workbook1.close();
			}
		}
		System.out.println("处理完成");
	}
}