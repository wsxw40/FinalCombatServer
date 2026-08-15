/**
 * 
 */
package com.pearl.o2o.protocal;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/**
 * @author lifengyang
 * 
 */
public abstract class PJMessage {
	public static final Charset DEFAULT_CHARSET = Charset.forName("GBK");
	public static final int HEADTIP = 21314;
	
	protected int datalength;

	public final ChannelBuffer toChannelBuffer() {
		ChannelBuffer message = ChannelBuffers.dynamicBuffer(ByteOrder.LITTLE_ENDIAN, 2048);
		message.writeShort(HEADTIP);
		message.writeShort(4);
		fillDataBody(message);
		message.setShort(2, message.readableBytes());
		return message;
	}

	protected abstract void fillDataBody(ChannelBuffer message);

	public final void mergeFrom(ChannelBuffer message) {
		datalength = message.readableBytes();
		mergeDatabody(message);
	}

	protected abstract void mergeDatabody(ChannelBuffer message);

	public abstract String getMessageStringFace();

	protected final void fillStringByLengthField(ChannelBuffer message, String str) {
		ByteBuffer encode = DEFAULT_CHARSET.encode(str);
		message.writeInt(encode.remaining());
		message.writeBytes(encode);
	}

	protected final void fillStringDirect(ChannelBuffer message, String str) {
		ByteBuffer encode = DEFAULT_CHARSET.encode(str);
		message.writeInt(encode.remaining());
		message.writeBytes(encode);
	}
	
	protected final void fillByteArrayByLengthField(ChannelBuffer message, byte[] bytes) {
		message.writeInt(bytes.length);
		message.writeBytes(bytes);
	}
	
	protected final byte[] readByteArrayByLengthField(ChannelBuffer buffer) {
		byte[] value = new byte[buffer.readInt()];
		buffer.readBytes(value);
		return value;
	}
	
	protected final String readStringByLengthField(ChannelBuffer buffer) {
		int strDataLength = buffer.readInt();
		return readStringByLength(buffer, strDataLength);
	}

	protected final String readStringToEnd(ChannelBuffer buffer) {
		return readStringByLength(buffer, buffer.readableBytes());
	}

	protected final String readStringByLength(ChannelBuffer buffer, int length) {
		String string = buffer.toString(buffer.readerIndex(), length, DEFAULT_CHARSET);
		buffer.skipBytes(length);
		return string;
	}
}
