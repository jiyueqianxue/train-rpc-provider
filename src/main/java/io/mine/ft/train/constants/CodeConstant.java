package io.mine.ft.train.constants;

/**
 * 信息码
 *
 * @author machao
 * @date 2018-08-15.
 */
public class CodeConstant {

	/**
	 * 成功
	 */
	public static final String SUCCESS = "0";
	/**
	 * 服务内部异常. 用于dubbo内部出现的各种异常
	 */
	public static final String SYSTEM_INNER_ERROR = "1001";
	/**
	 * 调用服务化接口错误. 调用其他dubbo接口出错
	 */
	public static final String CALL_SERVER_INTERFACE_FAIL = "2001";
	/**
	 * 调用第三方接口出错 调用其他公司或平台接口出错
	 */
	public static final String CALL_OPEN_INTERFACE_FAIL = "3001";
	/**
	 * 请求参数不合法
	 */
	public static final String PARAMS_INAVAILABLE = "4001";
	/**
	 * 没有可用的数据 调用接口的参数找不到对应的合法数据，例如:通过用户id找不到用户信息
	 */
	public static final String NO_AVALIABLE_DATA = "5001";
	/**
	 * 没有权限
	 */
	public static final String PERMISSION_DENY = "6001";
}
