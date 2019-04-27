package io.mine.ft.train.conf.websocket;
/**
 * 服务器返回给浏览器的消息由这个类来承载
 * @author chao.ma
 *
 */
public class ResponseMessage {

	private String responseMessage;

	public ResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

}
