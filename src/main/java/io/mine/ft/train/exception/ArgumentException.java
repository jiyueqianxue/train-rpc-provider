package io.mine.ft.train.exception;

import io.mine.ft.train.enums.ResultCodeEnum;
/**
 * 参数异常
 *
 * @author machao
 * @date 2017-06-23.
 */
public class ArgumentException extends IllegalArgumentException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String code = ResultCodeEnum.PARAMS_INAVAILABLE.getCode();

    public ArgumentException() {
        super(ResultCodeEnum.PARAMS_INAVAILABLE.getMsg());
    }

    public ArgumentException(String message) {
        super(message);
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
