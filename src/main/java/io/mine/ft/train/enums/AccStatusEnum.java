package io.mine.ft.train.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum AccStatusEnum {
	NORMAL(0, "正常"),
	FREEZE(1, "冻结"),
	CANCEL(2, "注销"),
	;
	private Integer code;

	private String msg;

	private AccStatusEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public static Map<Integer, AccStatusEnum> getAll() {
		Map<Integer, AccStatusEnum> map = new HashMap<Integer, AccStatusEnum>();
		for (AccStatusEnum columnEnum : AccStatusEnum.values()) {
			map.put(columnEnum.getCode(), columnEnum);
		}
		return map;
	}

	public List<String> getAllName() {
		List<String> list = new ArrayList<String>();
		for (AccStatusEnum columnEnum : AccStatusEnum.values()) {
			list.add(columnEnum.getMsg());
		}
		return list;
	}
	public static String getMsg(Integer code){
		for (AccStatusEnum columnEnum : AccStatusEnum.values()) {
			if(columnEnum.getCode() == code){
				return columnEnum.getMsg();
			} 
		}
		return null;
	}
}
