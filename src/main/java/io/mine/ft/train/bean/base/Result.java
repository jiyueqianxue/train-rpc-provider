package io.mine.ft.train.bean.base;

import io.mine.ft.train.enums.ResultCodeEnum;

/**
 * 结果,提供化的dubbo接口返回<br/>
 * Result&lt;XXX&gt; result = Result.create();<br/>
 * ...<br/>
 * return result.success();<br/>
 * or <br/>
 * return result.success(data);<br/>
 * or <br/>
 * return result.fail("SomeErrorCode","SomeDescription")<br/>
 * or <br/>
 * return result.fail("SomeErrorCode") <br/>
 * or you can do chained callings like below:<br/>
 *
 * result.data(data).code("SomeCode").description("SomeDescription").success();<br/>
 * <br/>
 * on 2018/8/14.
 */
public class Result<T> extends BaseResult {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 返回数据，可为基本类型（包装类），可以为其它可序列化对象
	 */
	private T data;

	public static <T> Result<T> create() {
		Result<T> result = new Result<T>();
		result.setSuccess(false);
		return result;
	}

	public Result<T> success(){
		success(null);
		return this;
	}

	public Result<T> success(T data){
		this.setSuccess(true);
		this.setCode(ResultCodeEnum.SUCCESS.getCode());
		this.setMsg(ResultCodeEnum.SUCCESS.getMsg());
		this.data = data;
		return this;
	}

	public Result<T> fail(String code, String msg){
		this.setSuccess(false);
		this.setCode(code);
		this.setMsg(msg);
		return this;
	}

	public Result<T> fail(ResultCodeEnum enums){
		fail(enums.getCode(), enums.getMsg());
		return this;
	}

	public Result<T> code(String code){
		this.setCode(code);
		return this;
	}

	public Result<T> msg(String msg){
		this.setMsg(msg);
		return this;
	}

	public Result<T> data(T data){
		this.data = data;
		return this;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
