/*
 * Copyright 2017 mine.com All right reserved. This software is the
 * confidential and proprietary information of mine.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with mine.com.
 */
package io.mine.ft.train.utils;

import org.springframework.util.StringUtils;

/**
 * 类DesensitiseStrUtil.java的实现描述：TODO 类实现描述 脱敏
 * 
 * @author za-fuyonghong 2017年10月23日 下午5:42:15
 */
public class DesensitiseStrUtil {
	private static String desensitisationRepalaceChar = "*";

	/**
	 * 银行账号 脱敏 保留前4 后4 中间*
	 * 
	 * @param valueStr
	 * @return
	 */
	public static String desensitiseStrForCardNo(String valueStr) {
		return concatDesensitisationStr(valueStr, 4, 4, desensitisationRepalaceChar);
	}

	/**
	 * 手机号 脱敏 保留前3 后4 中间*
	 * 
	 * @param valueStr
	 * @return
	 */
	public static String desensitiseStrForPhoneNo(String valueStr) {
		return concatDesensitisationStr(valueStr, 3, 4, desensitisationRepalaceChar);
	}

	/**
	 * 身份证号 脱敏 保留前4 后4 中间*
	 * 
	 * @param valueStr
	 * @return
	 */
	public static String desensitiseStrForId(String valueStr) {
		return concatDesensitisationStr(valueStr, 4, 4, desensitisationRepalaceChar);
	}

	/**
	 * 
	 * @param valueStr
	 * @param headCharNum
	 * @param tailCharNum
	 * @param middleReplaceChar
	 * @return
	 */
	public static String concatDesensitisationStr(String valueStr, int headCharNum, int tailCharNum,
			String middleReplaceChar) {
		if (StringUtils.isEmpty(valueStr) || headCharNum < 0 || tailCharNum < 0
				|| valueStr.length() < (headCharNum + tailCharNum))
			return valueStr;

		int replaceCharLength = valueStr.length() - headCharNum - tailCharNum;
		String minddleStr = "";
		for (int i = 0; i < replaceCharLength; i++) {
			minddleStr += middleReplaceChar;
		}
		String retStr = valueStr.substring(0, headCharNum) + minddleStr
				+ valueStr.substring(valueStr.length() - tailCharNum, valueStr.length());
		return retStr;
	}
}
