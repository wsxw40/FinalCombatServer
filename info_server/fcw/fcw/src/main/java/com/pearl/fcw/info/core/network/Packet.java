package com.pearl.fcw.info.core.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.Future;

import java.nio.charset.Charset;

import com.pearl.fcw.info.core.persistence.utils.BinaryChannelBuffer;
import com.pearl.fcw.info.core.persistence.utils.BinaryUtil;
import com.pearl.fcw.info.core.persistence.utils.StatusCode;
import com.pearl.fcw.info.lobby.utils.Constants;

public class Packet {
	private static final byte[] SUCCESS_BYTE_ARRAY = BinaryUtil
			.toByta(StatusCode.SUCCESS.ordinal());

	public static final int MIN_PACK_SIZE = 0; // 最小包长度
	public static final char HEAD_PACK_SIZE = 21314; // 最大包长度3mb
	public static final int HEADER_MAX_LENGTH = 0xFFFF; // 最大包头长度

	private Request request;
	private Response response;
	private BinaryChannelBuffer body;

	public Packet() {
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public <F extends Future<?>> void operationComplete(F future)
			throws Exception {
		releaseBody();
	}

	public void releaseBody() {
		if (body != null) {
			body.getByteBuf().release();
		}
	}

	public byte[] response() {
		BinaryChannelBuffer out = new BinaryChannelBuffer();
		Response response = getResponse();
		Request request = getRequest();
		int queueCurrentSize = 100; // FIXME ActionCount + QueueSize

		out.writeBytes(BinaryUtil.toByta((byte) response.getType().ordinal()));
		if (response.getType() == ServerMessage.SM_ResponseTextRPC) {
			// 写队列剩余大小
			out.writeBytes(BinaryUtil.toByta(request.getQueueId()));
			out.writeBytes(BinaryUtil.toByta(queueCurrentSize));

			out.writeBytes(BinaryUtil.toByta(request.getRpcId()));
			out.writeBytes(SUCCESS_BYTE_ARRAY);
			out.writeBytes(request.getUserData());
			if (response.getText() != null) {
				out.writeBytes(response.getText().getBytes(
						Charset.forName(Constants.ENCODING)));
			}
		} else if (response.getType() == ServerMessage.SM_ResponseBinaryRPC) {
			out.writeBytes(BinaryUtil.toByta(request.getQueueId()));
			out.writeBytes(BinaryUtil.toByta(queueCurrentSize));

			out.writeBytes(BinaryUtil.toByta(request.getRpcId()));
			out.writeBytes(SUCCESS_BYTE_ARRAY);
			out.writeBytes(request.getUserData());
			if (response.getBinary() != null) {
				out.writeBytes(response.getBinary());
			}
		} else if (response.getType() == ServerMessage.SM_SendChat) {
			out.writeBytes(response.getBinary());
		}

		byte[] output = out.array();
		ByteBuf buff = Unpooled.buffer(output.length + 4);
		buff.writeBytes(BinaryUtil.toByta((char) 21314));
		buff.writeBytes((BinaryUtil.toByta((char) (output.length + 4))));
		buff.writeBytes(output);
		return buff.array();
	}

	public void setBody(BinaryChannelBuffer body) {
		this.body = body;
	}

	public BinaryChannelBuffer getBody() {
		return body;
	}

	public void setBodyArrayTo(byte[] byteArray) {
		ByteBuf buff = Unpooled.buffer(byteArray.length + 4);
		buff.writeBytes(BinaryUtil.toByta(HEAD_PACK_SIZE));
		buff.writeBytes((BinaryUtil.toByta((char) (byteArray.length + 4))));
		buff.writeBytes(byteArray);
		setBody(new BinaryChannelBuffer(buff.array()));
	}
}
