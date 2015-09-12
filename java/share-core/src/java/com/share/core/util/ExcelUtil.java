package com.share.core.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {
	private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	public void exportExcelWithIndex(String exportFileName, String[] cellTitle, String[] cellContentName, HttpServletResponse response, List<Map<String, Object>> list) {
		exportExcel(exportFileName, cellTitle, cellContentName, response, list, true);
	}

	public void exportExcelWithOutIndex(String exportFileName, String[] cellTitle, String[] cellContentName, HttpServletResponse response, List<Map<String, Object>> list) {
		exportExcel(exportFileName, cellTitle, cellContentName, response, list, false);
	}

	private void exportExcel(String exportFileName, String[] cellTitle, String[] cellContentName, HttpServletResponse response, List<Map<String, Object>> list, boolean needIndex) {
		XSSFWorkbook workBook = new XSSFWorkbook();
		buildSheet(workBook, "sheet1", cellTitle, cellContentName, list, needIndex);

		try {
			int index = exportFileName.indexOf(".xlsx");
			if (index <= 0)
				exportFileName = exportFileName + ".xlsx";
			// 表示以附件的形式把文件发送到客户端 
			response.setHeader("Content-Disposition", "attachment;filename=" + new String((exportFileName).getBytes(), "ISO-8859-1"));
			//设定输出文件头 
			response.setContentType("application/x-download;charset=UTF-8");
			//通过response的输出流把工作薄的流发送浏览器形成文件 
			OutputStream outStream = response.getOutputStream();
			workBook.write(outStream);
			outStream.flush();
			outStream.close();

		} catch (Exception e) {
			logger.warn("", e);
		}
	}

	private void buildSheet(XSSFWorkbook workBook, String sheetName, String[] cellTitle, String[] cellContentName, List<Map<String, Object>> list, boolean needIndex) {
		XSSFSheet sheet = workBook.createSheet();
		workBook.setSheetName(0, sheetName);// 工作簿名称
		XSSFFont font = workBook.createFont();
		font.setColor(XSSFFont.COLOR_NORMAL);
		font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		XSSFCellStyle titleCellStyle = workBook.createCellStyle();// 创建格式
		titleCellStyle.setFont(font);
		titleCellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		titleCellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		// 创建第一行标题
		XSSFRow titleRow = sheet.createRow((short) 0);// 第一行标题
		for (int i = 0, size = cellTitle.length; i < size; i++) {// 创建第1行标题单元格
			createStringCell(titleRow, i, titleCellStyle, cellTitle[i]);
		}

		if (list != null && !list.isEmpty()) {
			XSSFCellStyle contentStyle = workBook.createCellStyle();// 创建格式
			for (int i = 0, size = list.size(); i < size; i++) {
				Map<String, Object> entity = list.get(i);
				XSSFRow row = sheet.createRow((short) i + 1);
				for (int j = 0, length = cellTitle.length; j < length; j++) {
					//XSSFCell cell = row.createCell(j, 0);// 在上面行索引0的位置创建单元格
					if (needIndex) {
						if (j == 0) {// 第一个单元格是序号
							contentStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
							createNumCell(row, j, contentStyle, i + 1);
						} else {
							Object obj = entity.get(cellContentName[j - 1]);
							contentStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
							createStringCell(row, j, contentStyle, obj == null ? "" : obj.toString());
						}
					} else {
						Object obj = entity.get(cellContentName[j]);
						contentStyle.setAlignment(XSSFCellStyle.ALIGN_LEFT);
						createStringCell(row, j, contentStyle, obj == null ? "" : obj.toString());
					}
				}
			}
		}
	}

	private void createNumCell(XSSFRow row, int index, XSSFCellStyle cellStyle, int cellValue) {
		XSSFCell cell = row.createCell(index, 0);
		cell.setCellStyle(cellStyle);
		cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
		cell.setCellValue(cellValue);

	}

	private void createStringCell(XSSFRow row, int index, XSSFCellStyle cellStyle, String cellValue) {
		XSSFCell cell = row.createCell(index, 0);
		cell.setCellStyle(cellStyle);
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);
		cell.setCellValue(cellValue);
	}

	/**
	 * 读取excel文件
	 * @param inputStream 文件流
	 * @return sheetName => List<sheetContent>
	 */
	public final static Map<String, List<String>> readExcel(InputStream inputStream) {
		Map<String, List<String>> data = new HashMap<String, List<String>>();
		try {
			Workbook wb = WorkbookFactory.create(inputStream);
			int sheetsNum = wb.getNumberOfSheets();
			for (int i = 0; i < sheetsNum; i++) {
				Sheet sheet = wb.getSheetAt(i);
				String sheetName = sheet.getSheetName().trim();
				List<String> list = data.get(sheetName);
				if (list == null) {
					list = new ArrayList<String>();
					data.put(sheetName, list);
				}

				for (Row row : sheet) {
					Iterator<Cell> it = row.iterator();
					while (it.hasNext()) {
						Cell cell = it.next();
						list.add(cell.toString().trim());
					}
				}
			}
		} catch (Exception e) {
			logger.warn("", e);
			return data;
		}
		return data;
	}

	/**
	 * 读取excel文件
	 * @param filePath 文件路径
	 * @return sheetName => List<sheetContent>
	 */
	public final static Map<String, List<String>> readExcel(String filePath) {
		try {
			return readExcel(new FileInputStream(filePath));
		} catch (Exception e) {
			logger.warn("", e);
		}
		return null;
	}
}