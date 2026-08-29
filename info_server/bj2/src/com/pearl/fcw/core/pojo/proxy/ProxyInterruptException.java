package com.pearl.fcw.core.pojo.proxy;

/**
 * 为了中断事务最后对代理实例数据的修改而设置的抛出异常类，并非真正的异常信息
 */
public class ProxyInterruptException extends Exception{

	private static final long serialVersionUID = -5616824266470441244L;
	private String msg = "";

	public ProxyInterruptException() {

	}

	public ProxyInterruptException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
