package io.mine.ft.train.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class POIUtil {
	private final static String xls = "xls";
	private final static String xlsx = "xlsx";

	/**
	 * 读入excel文件，解析后返回
	 * 
	 * @param file
	 * @param skipCount
	 *            开头跳过几行
	 * @param skipEnd
	 *            过滤结尾行数
	 * @param lastCellNum
	 *            每行列数
	 * @throws IOException
	 */
	public static List<String[]> readExcel(MultipartFile file, int sheetNum, int skipCount, int skipEnd,
			int lastCellNum) throws IOException {
		// 检查文件
		checkFile(file);
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null) {
			// for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets();
			// sheetNum++) {
			// 获得当前sheet工作表
			Sheet sheet = workbook.getSheetAt(sheetNum);
			if (sheet == null) {
				return null;
			}
			// 获得当前sheet的开始行
			int firstRowNum = sheet.getFirstRowNum();
			// 获得当前sheet的结束行
			int lastRowNum = sheet.getLastRowNum();
			// 循环除了前几行的所有行
			for (int rowNum = firstRowNum + skipCount; rowNum <= lastRowNum - skipEnd; rowNum++) {
				// 获得当前行
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				// 获得当前行的开始列
				int firstCellNum = row.getFirstCellNum();
				// 获得当前行的列数
				// int lastCellNum = row.getPhysicalNumberOfCells();
				// 由于excel格式问题会导致取最后一列的数据有差异，此处当前行的列数由参数传人
				String[] cells = new String[lastCellNum];
				if (StringUtils.isEmpty(getCellValue(row.getCell(0)))) {
					continue;
				}
				// 循环当前行
				for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
					Cell cell = row.getCell(cellNum);
					cells[cellNum] = getCellValue(cell);
				}
				list.add(cells);
			}
			// }
			closeInputStream(file.getInputStream());
		}
		return list;
	}

	public static void checkFile(MultipartFile file) throws IOException {
		// 判断文件是否存在
		if (null == file) {
			log.error("文件不存在！");
			throw new FileNotFoundException("文件不存在！");
		}
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 判断文件是否是excel文件
		if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
			log.error(fileName + "不是excel文件");
			throw new IOException(fileName + "不是excel文件");
		}
	}

	public static Workbook getWorkBook(MultipartFile file) {
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			InputStream is = file.getInputStream();
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith(xls)) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith(xlsx)) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		return workbook;
	}

	private static void closeInputStream(InputStream in) {
		close(in, null);
	}

	private static void close(InputStream in, OutputStream out) {
		try {
			if (null != in) {
				in.close();
			}
			if (null != out) {
				out.close();
			}
		} catch (Exception e) {
			log.error("关闭流失败。。" + e.getMessage(), e);
		}
	}

	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}

	/**
	 * @author lzj
	 * @param ins
	 *            InputStream
	 * @param startRowNum
	 *            有效内容其实行号，从0开始
	 * @param columnNum
	 *            列数
	 * @return
	 */
	public static List<String[]> getExcelContent(InputStream ins, int startRowNum, int columnNum) {
		if (null == ins) {
			return null;
		}
		List<String[]> list = new ArrayList<String[]>();
		try {
			Workbook wb = WorkbookFactory.create(ins);
			Sheet sheet = wb.getSheetAt(0);
			if (null != sheet && sheet.getPhysicalNumberOfRows() > 0) {
				for (int r = startRowNum; r < sheet.getPhysicalNumberOfRows(); r++) {
					Row row = sheet.getRow(r);
					int c_length = row.getPhysicalNumberOfCells();
					if (c_length < 1) {
						continue;
					}
					String[] c_array = new String[columnNum];
					for (int c = 0; c < columnNum; c++) {
						c_array[c] = getCellValue(row.getCell(c));

					}
					list.add(c_array);
				}
			}
		} catch (IOException e) {
			log.info("读取文件流异常{}", e);
		} catch (InvalidFormatException e) {
			log.info("读取xls/xlsx文件流异常,InvalidFormatException={}", e);
		} finally {
			close(ins, null);
		}
		return list;
	}

	/**
	 * @author machao
	 * @param ins
	 *            InputStream
	 * @param startRowNum
	 *            有效内容其实行号，从0开始
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static List<String[]> getExcelContent(InputStream ins, int startRowNum)
			throws InvalidFormatException, IOException {
		if (null == ins) {
			return null;
		}
		List<String[]> list = new ArrayList<String[]>();

		Workbook wb = WorkbookFactory.create(ins);
		Sheet sheet = wb.getSheetAt(0);
		if (null != sheet && sheet.getPhysicalNumberOfRows() > 0) {
			for (int r = startRowNum; r < sheet.getPhysicalNumberOfRows(); r++) {
				Row row = sheet.getRow(r);
				int c_length = row.getPhysicalNumberOfCells();
				if (c_length < 1) {
					continue;
				}
				String[] c_array = new String[c_length];
				for (int c = 0; c < c_length; c++) {
					c_array[c] = getCellValue(row.getCell(c));
				}
				list.add(c_array);
			}
		}
		close(ins, null);
		return list;
	}

	// 大标题的样式
	public static CellStyle bigTitle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD); // 字体加粗
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中
		return style;
	}

	// 小标题的样式
	public static CellStyle title(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中
		return style;
	}

	// 文字的样式
	public static CellStyle text(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Times New Roman");
		font.setFontHeightInPoints((short) 10);
		style.setFont(font);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
		style.setAlignment(CellStyle.ALIGN_CENTER); // 横向居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 纵向居中
		return style;
	}
}
