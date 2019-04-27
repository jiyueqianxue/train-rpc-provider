package io.mine.ft.train.constants;

/**
 * 通用字段定义
 * 
 */
public class CommonConstants {

	public static final String LOCAL_PATH = System.getProperty("user.home").concat("/");

	public static final String SEPARATOR_HORIZONAL = "-";

	public static final String SEPARATOR_UNDERLINE = "_";

	public static final String SUCCESS_DESC = "成功";

	public static final String FAIL_DESC = "失败";

	public static final String CODE = "code";

	public static final String MSG = "msg";
	/** 服务返回结果码-成功 */
	public static final String RESULT_CODE_SUCCESS = "000000";
	/** 服务返回结果码-失败 */
	public static final String RESULT_CODE_FAIL = "999999";

	public static final int KB = 1024;

	public static final int DEFAULT_BUFFER_SIZE = 8 * KB;

	public static final String SEPARATOR_TRANSFER_POINT = "\\.";

	public static final String SEPARATOR_POINT = ".";
	/** 删除标识：N-未删除 */
	public static final String IS_DELETED_N = "N";
	/** 删除标识：Y-已删除 */
	public static final String IS_DELETED_Y = "Y";

	/**
	 * excel文件后缀xls
	 */
	public static final String EXCEL_SUFFIX_XLS = "xls";

	/**
	 * excel文件后缀xlsx
	 */
	public static final String EXCEL_SUFFIX_XLSX = "xlsx";

	public static final String FILE_SUFFIX_PDF = "pdf";

	public static final String FILE_SUFFIX_ZIP = "zip";

	public static final String SEPARATE_POINT = ".";
	/** 收单版本号 */
	public static final String PAYACQ_VERSION = "1.0.0";
	/** 结算系统编号 */
	public static final String CLR_SYS_CODE = "SET_CLR";
}
