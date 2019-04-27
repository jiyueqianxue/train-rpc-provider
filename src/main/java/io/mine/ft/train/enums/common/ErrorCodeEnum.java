package io.mine.ft.train.enums.common;

public enum ErrorCodeEnum {

	SUCCESS(0, "成功"), CAPTCHA_CODE_ERROR(999, "验证码验证失败"), ERROR(999, "失败");
	private int retCode;
	private String retMsg;

	private ErrorCodeEnum(int retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public int getRetCode() {
		return retCode;
	}

	public void setRetCode(int retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
}
