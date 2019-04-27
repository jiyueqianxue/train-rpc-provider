package io.mine.ft.train.exception;

import io.mine.ft.train.enums.ResultCodeEnum;

/**
 * 服务异常，继承RuntimeException，是unchecked异常
 * 用户服务出现无法兼容的异常
 *
 * @author machao
 * @date 2018-08-13.
 */
public class ServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String code = ResultCodeEnum.SYSTEM_INNER_ERROR.getCode();

    public ServiceException() {
        super(ResultCodeEnum.SYSTEM_INNER_ERROR.getMsg());
    }

    public ServiceException(String message) {
        super(message);
        this.code = ResultCodeEnum.SYSTEM_INNER_ERROR.getCode();
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
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
