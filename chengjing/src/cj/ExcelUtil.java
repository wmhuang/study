package cj;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	// 当前时间
	private static String currentDate;
	// 默认单元格内容为数字时格式
	private static DecimalFormat df = new DecimalFormat("0");
	// 默认单元格格式化日期字符串
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	// 格式化数字
	private static DecimalFormat nf = new DecimalFormat("0");

	public static ArrayList<ArrayList<Object>> readExcel(File file) {
		if (file == null) {
			return null;
		}
		if (file.getName().endsWith("xlsx")) {
			// 处理ecxel2007
			return readExcel2007(file);
		} else {
			// 处理ecxel2003
			return readExcel2003(file);
		}
	}

	/*
	 * @return 将返回结果存储在ArrayList内，存储结构与二位数组类似
	 * lists.get(0).get(0)表示过去Excel中0行0列单元格
	 */
	public static ArrayList<ArrayList<Object>> readExcel2003(File file) {
		try {
			ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> colList;
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row;
			HSSFCell cell;
			Object value;
			for (int i = sheet.getFirstRowNum(), rowCount = 0; rowCount < sheet
					.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				colList = new ArrayList<Object>();
				if (row == null) {
					// 当读取行为空时
					if (i != sheet.getPhysicalNumberOfRows()) {// 判断是否是最后一行
						rowList.add(colList);
					}
					continue;
				} else {
					rowCount++;
				}
				// 时间
				String date;
				// PC端引流关键词
				String pcComeKey;
				// 访客数
				int pcComeCount;
				// PC端成交关键字
				String pcSuccessKey;
				// 支付子订单
				int pcSuccessCount;
				// 无线端引流关键词
				String mobelComeKey;
				// 访客数
				int mobelComeCount;
				// 无线端成交关键词
				String mobelSuccessKey;
				// 支付子订单
				int mobelSuccessCount;

				// 设置默认时间
				HSSFCell cell0 = row.getCell(0);
				if (cell0 != null) {
					currentDate = cell0.getStringCellValue();
				}
				colList.add(currentDate);
				HSSFCell cell1 = row.getCell(1);
				pcComeKey = cell0.getStringCellValue();
				colList.add(pcComeKey);
				HSSFCell cell2 = row.getCell(2);
				pcComeCount = (int) cell2.getNumericCellValue();
				colList.add(pcComeKey);
				HSSFCell cell4 = row.getCell(4);
				pcSuccessKey = cell4.getStringCellValue();
				colList.add(pcSuccessKey);
				HSSFCell cell5 = row.getCell(5);
				pcSuccessCount = (int) cell5.getNumericCellValue();
				colList.add(pcSuccessCount);
				HSSFCell cell7 = row.getCell(7);
				mobelComeKey = cell7.getStringCellValue();
				colList.add(mobelComeKey);
				HSSFCell cell8 = row.getCell(8);
				mobelComeCount = (int) cell8.getNumericCellValue();
				colList.add(mobelComeCount);
				HSSFCell cell10 = row.getCell(10);
				mobelSuccessKey = cell10.getStringCellValue();
				colList.add(mobelSuccessKey);
				HSSFCell cell11 = row.getCell(11);
				mobelSuccessCount = (int) cell11.getNumericCellValue();
				colList.add(mobelSuccessCount);
				// for (int j = row.getFirstCellNum(); j <=
				// row.getLastCellNum(); j++) {
				// cell = row.getCell(j);
				// if (cell == null
				// || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
				// // 当该单元格为空
				// if (j != row.getLastCellNum()) {// 判断是否是该行中最后一个单元格
				// colList.add("");
				// }
				// continue;
				// }
				// switch (cell.getCellType()) {
				// case XSSFCell.CELL_TYPE_STRING:
				// // System.out.println(i + "行" + j +
				// // " 列 is String type");
				// value = cell.getStringCellValue();
				// break;
				// case XSSFCell.CELL_TYPE_NUMERIC:
				// if ("@".equals(cell.getCellStyle()
				// .getDataFormatString())) {
				// value = df.format(cell.getNumericCellValue());
				// } else if ("General".equals(cell.getCellStyle()
				// .getDataFormatString())) {
				// value = nf.format(cell.getNumericCellValue());
				// } else {
				// value = sdf.format(HSSFDateUtil.getJavaDate(cell
				// .getNumericCellValue()));
				// }
				// // System.out.println(i + "行" + j
				// // + " 列 is Number type ; DateFormt:"
				// // + value.toString());
				// break;
				// case XSSFCell.CELL_TYPE_BOOLEAN:
				// // System.out.println(i + "行" + j +
				// // " 列 is Boolean type");
				// value = Boolean.valueOf(cell.getBooleanCellValue());
				// break;
				// case XSSFCell.CELL_TYPE_BLANK:
				// // System.out.println(i + "行" + j + " 列 is Blank type");
				// value = "";
				// break;
				// default:
				// // System.out.println(i + "行" + j +
				// // " 列 is default type");
				// value = cell.toString();
				// }// end switch
				// colList.add(value);
				// }// end for j
				rowList.add(colList);
			}// end for i

			return rowList;
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<ArrayList<Object>> readExcel2007(File file) {
		int line =0;
		try {
			ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			ArrayList<Object> colList;
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Object value;
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				line=i;
				row = sheet.getRow(i);
				colList = new ArrayList<Object>();
				if (row == null) {
					continue;
				}

				// 时间
				String date = null;
				// PC端引流关键词
				String pcComeKey = null;
				// 访客数
				int pcComeCount = 0;
				// PC端成交关键字
				String pcSuccessKey = null;
				// 支付子订单
				int pcSuccessCount = 0;
				// 无线端引流关键词
				String mobelComeKey = null;
				// 访客数
				int mobelComeCount = 0;
				// 无线端成交关键词
				String mobelSuccessKey = null;
				// 支付子订单
				int mobelSuccessCount = 0;

				// 设置默认时间
				XSSFCell cell0 = row.getCell(0);
				if (cell0 != null) {
					currentDate = cell0.toString();
				}
				colList.add(currentDate);

				XSSFCell cell1 = row.getCell(1);
				if (cell1 != null) {
					cell1.setCellType(Cell.CELL_TYPE_STRING);
					pcComeKey = cell1.toString();
				} 
				colList.add(pcComeKey);

				XSSFCell cell2 = row.getCell(2);
				if (cell2 != null) {
					cell2.setCellType(Cell.CELL_TYPE_NUMERIC);
					pcComeCount = (int) cell2.getNumericCellValue();
				} else {
					pcComeCount = 0;
				}
				colList.add(pcComeCount);

				XSSFCell cell4 = row.getCell(4);
				if (cell4 != null) {
					pcSuccessKey = cell4.getStringCellValue();
				}
				colList.add(pcSuccessKey);

				XSSFCell cell5 = row.getCell(5);
				if (cell5 != null) {
					pcSuccessCount = (int) cell5.getNumericCellValue();
				} else {
					pcSuccessCount = 0;
				}
				colList.add(pcSuccessCount);

				XSSFCell cell7 = row.getCell(7);
				if (cell7 != null) {
					cell7.setCellType(Cell.CELL_TYPE_STRING);
					mobelComeKey = cell7.getStringCellValue();
				}
				colList.add(mobelComeKey);

				XSSFCell cell8 = row.getCell(8);
				if (cell8 != null) {
					mobelComeCount = (int) cell8.getNumericCellValue();
				} else {
					mobelComeCount = 0;
				}
				colList.add(mobelComeCount);

				XSSFCell cell10 = row.getCell(10);
				if (cell10 != null) {
					cell10.setCellType(Cell.CELL_TYPE_STRING);
					mobelSuccessKey = cell10.getStringCellValue();
				}
				colList.add(mobelSuccessKey);

				XSSFCell cell11 = row.getCell(11);
				if (cell11 != null) {
					mobelSuccessCount = (int) cell11.getNumericCellValue();
				} else {
					mobelSuccessCount = 0;
				}
				colList.add(mobelSuccessCount);

				// for (int j = row.getFirstCellNum(); j <=
				// row.getLastCellNum(); j++) {
				// cell = row.getCell(j);
				// if (cell == null
				// || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
				// // 当该单元格为空
				// if (j != row.getLastCellNum()) {// 判断是否是该行中最后一个单元格
				// colList.add("");
				// }
				// continue;
				// }
				// switch (cell.getCellType()) {
				// case XSSFCell.CELL_TYPE_STRING:
				// // System.out.println(i + "行" + j +
				// // " 列 is String type");
				// value = cell.getStringCellValue();
				// break;
				// case XSSFCell.CELL_TYPE_NUMERIC:
				// if ("@".equals(cell.getCellStyle()
				// .getDataFormatString())) {
				// value = df.format(cell.getNumericCellValue());
				// } else if ("General".equals(cell.getCellStyle()
				// .getDataFormatString())) {
				// value = nf.format(cell.getNumericCellValue());
				// } else {
				// value = sdf.format(HSSFDateUtil.getJavaDate(cell
				// .getNumericCellValue()));
				// }
				// // System.out.println(i + "行" + j
				// // + " 列 is Number type ; DateFormt:"
				// // + value.toString());
				// break;
				// case XSSFCell.CELL_TYPE_BOOLEAN:
				// // System.out.println(i + "行" + j +
				// // " 列 is Boolean type");
				// value = Boolean.valueOf(cell.getBooleanCellValue());
				// break;
				// case XSSFCell.CELL_TYPE_BLANK:
				// // System.out.println(i + "行" + j + " 列 is Blank type");
				// value = "";
				// break;
				// default:
				// // System.out.println(i + "行" + j +
				// // " 列 is default type");
				// value = cell.toString();
				// }// end switch
				// colList.add(value);
				// }// end for j
				// System.out.println(colList);
				rowList.add(colList);
			}// end for i

			return rowList;
		} catch (Exception e) {
			System.out.println(line);
			e.printStackTrace();
			return null;
		}
	}

	public static void writeExcel(ArrayList<ArrayList<Object>> result,
			String path) {
		if (result == null) {
			return;
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet1");
		for (int i = 0; i < result.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			if (result.get(i) != null) {
				for (int j = 0; j < result.get(i).size(); j++) {
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(result.get(i).get(j).toString());
				}
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		File file = new File(path);// Excel文件生成后存储的位置。
		OutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(content);
			os.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static DecimalFormat getDf() {
		return df;
	}

	public static void setDf(DecimalFormat df) {
		ExcelUtil.df = df;
	}

	public static SimpleDateFormat getSdf() {
		return sdf;
	}

	public static void setSdf(SimpleDateFormat sdf) {
		ExcelUtil.sdf = sdf;
	}

	public static DecimalFormat getNf() {
		return nf;
	}

	public static void setNf(DecimalFormat nf) {
		ExcelUtil.nf = nf;
	}

}