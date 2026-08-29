package com.pearl.o2o.socket;

import java.io.IOException;
import java.nio.charset.Charset;

import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryUtil;

public class Response {
	private final ServerMessage   type;
	private final String		  text;
	private final byte[]		  binary;
	
	private Response(){
		type 	= null;
		text 	= null;
		binary	= null;
	}
	
	private Response(ServerMessage type, String text, byte[] binary){
		this.type 	= type;
		this.text 	= text;
		this.binary = binary;
	}
	
	public static Response text(String text){
		return new Response(ServerMessage.SM_ResponseTextRPC, text, null);
	}
	
	public static Response binary(byte[] binary){
		return new Response(ServerMessage.SM_ResponseBinaryRPC, null, binary);
	}
	
	public static Response chat(String receiver, String to, String name, String msg) throws IOException{
		BinaryChannelBuffer buffer = new BinaryChannelBuffer(BinaryUtil.getLength(receiver, to, name, msg));
		buffer.writeString(receiver);
		buffer.writeString(to);
		buffer.writeString(name);
		buffer.writeString(msg);		
		return new Response(ServerMessage.SM_SendChat, null, buffer.array());
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

