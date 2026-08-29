package com.pearl.fcw.core.pojo;

/**
 * 延迟信息<br/>
 */
public class DelayMessage {
	private String key;//多数情况下对应playerId
	private String uri;//当前线程使用的接口
	private Object data;//可以有关联延迟触发的信息

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
