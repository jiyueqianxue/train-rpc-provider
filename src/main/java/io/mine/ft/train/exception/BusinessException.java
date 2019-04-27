package io.mine.ft.train.exception;

import io.mine.ft.train.enums.ResultCodeEnum;

/**
 * 业务异常类，继承Exception是checked异常
 * 用于需要上层捕获，做其他处理的操作
 *
 * @author machao
 * @date 2018-08-15.
 */
public class BusinessException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String code;
    
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
    
    public BusinessException(ResultCodeEnum resultCodeEnum) {
    	super(resultCodeEnum.getMsg());
		this.code = resultCodeEnum.getCode();
	}

    /**
     * 获取信息码
     *
     * @return 信息码
     */
    public String getCode() {
        return code;
    }
    
}
