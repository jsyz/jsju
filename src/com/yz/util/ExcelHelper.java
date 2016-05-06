/*package com.yz.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.yz.model.Usero;

*//**
 * Excel组件
 * 
 * @author Snowolf
 * @version 1.0
 * @since 1.0
 *//*
public abstract class ExcelHelper {

	*//**
	 * Excel 2003
	 *//*
	private final static String XLS = "xls";
	*//**
	 * Excel 2007
	 *//*
	private final static String XLSX = "xlsx";
	*//**
	 * 分隔符
	 *//*
	private final static String SEPARATOR = "|";

	*//**
	 * 由Excel文件的Sheet导出至List
	 * 
	 * @param file
	 * @param sheetNum
	 * @return
	 *//*
	public static List<Usero> exportListFromExcel(File file, int sheetNum)
			throws IOException {
		return exportListFromExcel(new FileInputStream(file), FilenameUtils
				.getExtension(file.getName()), sheetNum);
	}

	*//**
	 * 由Excel流的Sheet导出至List
	 * 
	 * @param is
	 * @param extensionName
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 *//*
	public static List<Usero> exportListFromExcel(InputStream is,
			String extensionName, int sheetNum) throws IOException {

		Workbook workbook = null;
		System.out.println(sheetNum);
		if (extensionName.toLowerCase().equals(XLS)) {
			workbook = new HSSFWorkbook(is);
		} else if (extensionName.toLowerCase().equals(XLSX)) {
			workbook = new XSSFWorkbook(is);
		}
		System.out.println(sheetNum);
		return exportListFromExcel(workbook, sheetNum);
	}

	*//**
	 * 由指定的Sheet导出至List
	 * 
	 * @param workbook
	 * @param sheetNum
	 * @return
	 * @throws IOException
	 *//*
	private static List<Usero> exportListFromExcel(Workbook workbook,
			int sheetNum) {

		Sheet sheet = workbook.getSheetAt(sheetNum);

		System.out.println(sheetNum);
		// 解析公式结果
		FormulaEvaluator evaluator = workbook.getCreationHelper()
				.createFormulaEvaluator();

		List<Usero> list = new ArrayList<Usero>();
		
		Usero usero = null;

		int minRowIx = sheet.getFirstRowNum();
		int maxRowIx = sheet.getLastRowNum();

		for (int rowIx = minRowIx; rowIx < maxRowIx; rowIx++) {
			Row row = sheet.getRow(rowIx);
				
			usero = new Usero();
			short minColIx = row.getFirstCellNum();
			short maxColIx = row.getLastCellNum();
			for (short colIx = minColIx; colIx < maxColIx; colIx++) {
				Cell cell = row.getCell(new Integer(colIx));
				CellValue cellValue = evaluator.evaluate(cell);

				if (cellValue == null) {
					continue;
				}
				
				// 经过公式解析，最后只存在Boolean、Numeric和String三种数据类型，此外就是Error了
				// 其余数据类型，根据官方文档，完全可以忽略http://poi.apache.org/spreadsheet/eval.html
				switch (cellValue.getCellType()) {
				case Cell.CELL_TYPE_BOOLEAN:
					break;
				case Cell.CELL_TYPE_NUMERIC:
					switch (colIx) {
					case 0:
						System.out.println("职位：" + cellValue.getStringValue());
						usero.setJobTitle(cellValue.getStringValue());
						break;
					case 1:
						System.out.println("名字：" + cellValue.getStringValue());
						usero.setRealname(cellValue.getStringValue());
						break;
					case 2:
						usero.setMobiletelphone(whatYourWant);
						break;
					default:
						break;
					}
					break;
				case Cell.CELL_TYPE_STRING:
					switch (colIx) {
					case 0:
						System.out.println("职位：" + cellValue.getStringValue());
						usero.setLochus(cellValue.getStringValue());
						break;
					case 1:
						System.out.println("名字：" + cellValue.getStringValue());
						usero.setName(cellValue.getStringValue());
						break;
					default:
						break;
					}
					break;
				case Cell.CELL_TYPE_FORMULA:
					break;
				case Cell.CELL_TYPE_BLANK:
					break;
				case Cell.CELL_TYPE_ERROR:
					break;
				default:
					break;
				}
			}
			list.add(usero);
		}
		return list;
	}
}*/