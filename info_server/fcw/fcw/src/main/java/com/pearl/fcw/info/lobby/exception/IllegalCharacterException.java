package com.pearl.fcw.info.lobby.exception;

/**
 * 
 * @author WengJie
 *
 */
public class IllegalCharacterException extends BaseException {
	private static final long serialVersionUID = -6314040534788042725L;

	public IllegalCharacterException() {
		super("您输入的内容中包含非法字符");
	}

	public IllegalCharacterException(String s) {
		super(s);
	}

	public IllegalCharacterException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalCharacterException(Throwable cause) {
		super(cause);
	}
}