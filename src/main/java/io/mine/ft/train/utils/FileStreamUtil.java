package io.mine.ft.train.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileStreamUtil {

	/**
	 * 服务器端excel文件删除
	 * 
	 * @param fileNames
	 * @param zipPath
	 */
	public static void deleteFile(List<String> fileNames, String zipPath) {
		String sPath = null;
		File file = null;
		// boolean flag = false;
		try {
			// 判断目录或文件是否存在
			for (int i = 0; i < fileNames.size(); i++) {
				sPath = fileNames.get(i);
				file = new File(sPath);
				if (file.exists()) {
					file.delete();
				}
			}
			file = new File(zipPath);
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			log.error("FileStreamUtil deleteFile error", e);
		}
	}

	/**
	 * 多个excel压缩
	 * 
	 * @param srcfile
	 *            文件名数组
	 * @param zipfile
	 *            压缩后文件
	 */
	public static void zipFiles(java.io.File[] srcfile, java.io.File zipfile) {
		byte[] buf = new byte[1024];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
		} catch (IOException e) {
			log.error("FileStreamUtil zipFiles error", e);
		}
	}

	/**
	 * 设置响应头
	 */
	public static void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			response.setContentType("application/octet-stream;charset=UTF-8");
			response.setHeader("Content-Disposition",
					"attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8") + ".zip");
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			log.error("e", ex);
		}
	}
}
