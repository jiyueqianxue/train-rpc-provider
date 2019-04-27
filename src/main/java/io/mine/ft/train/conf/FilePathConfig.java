package io.mine.ft.train.conf;

import java.io.Serializable;

public class FilePathConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2338987436589814359L;

	public static final String PAGE_SOURCEFILE_TEMP_PATH = "pageupload/sourcefile/local/temp/"; // 页面上传文件本地临时存放原始文件目录（需要拼接一个时间戳，以免文件互相影响）
	public static final String PAGE_SOURCEFILE_OSS_PREFIX = "xcollection/pageupload/sourcefile/oss/prefix/"; // 页面上传文件的数据源存放oss的前缀

}
