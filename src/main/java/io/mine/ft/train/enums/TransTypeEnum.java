package io.mine.ft.train.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum TransTypeEnum {
	MANUAL_OPERATION(1, "手动"),
	ACTIVITY(2, "活动"),
	CONSUME(3, "消费"),
	;
	private Integer code;

	private String msg;

	private TransTypeEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public static Map<Integer, TransTypeEnum> getAll() {
		Map<Integer, TransTypeEnum> map = new HashMap<Integer, TransTypeEnum>();
		for (TransTypeEnum columnEnum : TransTypeEnum.values()) {
			map.put(columnEnum.getCode(), columnEnum);
		}
		return map;
	}

	public List<String> getAllName() {
		List<String> list = new ArrayList<String>();
		for (TransTypeEnum columnEnum : TransTypeEnum.values()) {
			list.add(columnEnum.getMsg());
		}
		return list;
	}
	public static String getMsg(Integer code){
		for (TransTypeEnum columnEnum : TransTypeEnum.values()) {
			if(columnEnum.getCode() == code){
				return columnEnum.getMsg();
			} 
		}
		return null;
	}
}
