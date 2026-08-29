package com.pearl.fcw.info.core.network;

public class Response {
	private final ServerMessage type;
	private final String text;
	private final byte[] binary;

	private Response() {
		type = null;
		text = null;
		binary = null;
	}

	private Response(ServerMessage type, String text, byte[] binary) {
		this.type = type;
		this.text = text;
		this.binary = binary;
	}

	public static Response text(String text) {
		return new Response(ServerMessage.SM_ResponseTextRPC, text, null);
	}

	public static Response binary(byte[] binary) {
		return new Response(ServerMessage.SM_ResponseBinaryRPC, null, binary);
	}

	/**
	 * @return the type
	 */
	public ServerMessage getType() {
		return type;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @return the binary
	 */
	public byte[] getBinary() {
		return binary;
	}
}
