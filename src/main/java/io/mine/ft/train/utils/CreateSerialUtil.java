package io.mine.ft.train.utils;

import java.util.Random;

public class CreateSerialUtil {

	final static String[] hybridCode = { "a", "1", "b", "2", "c", "3", "d", "4", "e", "5", "f", "6", "g", "7", "h", "8",
			"i", "9", "j", "0", "k", "1", "l", "2", "m", "3", "n", "4", "o", "5", "p", "6", "q", "7", "r", "8", "s",
			"9", "t", "0", "u", "1", "v", "2", "w", "3", "x", "4", "y", "z" };

	final static String[] number = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"

	};

	final static String[] English = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p",
			"q", "r", "s", "t", "u", "v", "w", "x", "y", "z"

	};

	public static String createFlowSerial(String prefix, int digit) {

		return prefix + randomSerial(digit, number)
				+ DateFormatUtil.longToFormatStr(System.currentTimeMillis(), "yyyyMMddHHmmss");
	}

	public static String createHybridSerial(String prefix, int digit) {

		return prefix + randomSerial(digit, hybridCode);
	}

	public static String randomSerial(int digit, String[] codeList) {

		StringBuilder builder = new StringBuilder();
		Random rand = new Random();

		for (int i = 0; i < digit; i++) {
			int index = rand.nextInt(codeList.length);
			builder.append(codeList[index]);
		}
		return builder.toString();
	}
}
