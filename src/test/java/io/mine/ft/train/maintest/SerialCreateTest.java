package io.mine.ft.train.maintest;

import io.mine.ft.train.utils.CreateSerialUtil;

public class SerialCreateTest {

	public static void main(String[] args) {

		String serial = CreateSerialUtil.createFlowSerial("DBD", 4);
		System.out.println(serial);
	}

}
