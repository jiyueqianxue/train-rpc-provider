package io.mine.ft.train.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum ResultCodeEnum {
	SUCCESS("000000", "成功"),
	SYSTEM_INNER_ERROR("000001", "系统内部错误"),
	CALL_SERVER_INTERFACE_FAIL("000002", "调用服务化接口错误"),
	CALL_OPEN_INTERFACE_FAIL("000003", "调用第三方接口出错"),
	PARAMS_INAVAILABLE("000004", "请求参数不合法"),
	NO_AVALIABLE_DATA("000005", "没有可用的数据"),
	PERMISSION_DENY("000006", "没有权限"),
	USER_NOT_EXIST("100001", "该用户不存在"),
	USER_IS_EXIST("100002", "该用户已存在"),
	POINT_NOT_SUFFICIENT("100003", "减少积分失败，当前积分不足"),
	POINT_AMOUNT_SIGN_EXCEPTION("100004", "变更积分额度符号异常！"),
	UPDATE_POINT_ACC_EXCEPTION("100005", "更新账户积分余额异常！"),
	CREATE_POINT_DETAIL_EXCEPTION("100006", "生成积分明细异常！"),
	CREATE_POINT_ACC_EXCEPTION("100007", "创建积分账户异常！"),
	CREATE_POINT_DETAIL_DUPLICATE("100008", "创建积分明细重复！"),
	CREATE_POINT_FLOW_REQT_DUPLICATE("100009", "积分变更流水请求重复！"),
	CREATE_BUSINESS_SYS_EXCEPTION("100009", "生成积分明细异常！"),
	UPDATE_BUSINESS_SYS_EXCEPTION("100010", "更新账户积分余额异常！"),
	CREATE_POINT_BIZ_SYS_CODE_DUPLICATE("100011", "新增业务系统，业务系统编码重复！"),
	CREATE_POINT_BIZ_TYPE_CODE_DUPLICATE("100012", "新增业务类型，业务类型编码重复！"),
	;
 
	private String code;

	private String msg;
	
	private ResultCodeEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public static Map<String, ResultCodeEnum> getAll() {
		Map<String, ResultCodeEnum> map = new HashMap<String, ResultCodeEnum>();
		for (ResultCodeEnum columnEnum : ResultCodeEnum.values()) {
			map.put(columnEnum.getCode(), columnEnum);
		}
		return map;
	}

	public List<String> getAllName() {
		List<String> list = new ArrayList<String>();
		for (ResultCodeEnum columnEnum : ResultCodeEnum.values()) {
			list.add(columnEnum.getMsg());
		}
		return list;
	}
	public static String getMsg(String code){
		for (ResultCodeEnum columnEnum : ResultCodeEnum.values()) {
			if(columnEnum.getCode() == code){
				return columnEnum.getMsg();
			} 
		}
		return null;
	}
}
