package com.pearl.fcw.info.core.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.core.persistence.utils.BinaryChannelBuffer;
import com.pearl.fcw.info.core.persistence.utils.BinaryUtil;

public class PacketDecoder extends ByteToMessageDecoder {

	private static final Logger logger = LoggerFactory
			.getLogger(PacketDecoder.class);

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		
		if (in.readableBytes() < Packet.MIN_PACK_SIZE) {
			return;
		}

		int headIndex = 0;
		byte[] headtip = new byte[2]; // Head

		int lenIndex = 0;
		byte[] lengthBuffer = new byte[2];// BodyLength

		int readIndex = 0;
		byte[] readBuffer = null; // Body

		while (in.isReadable() && (headIndex<2 || lenIndex<2 || (readBuffer == null || readIndex < readBuffer.length && readBuffer.length > 0))) {
			if (headIndex < 2) {
				headtip[headIndex++] = in.readByte();
				continue;
			}

			if (BinaryUtil.toChar(headtip) != Packet.HEAD_PACK_SIZE) {
				// in.resetReaderIndex();
				ctx.close();
				break;
			}

			if (lenIndex < 2) {
				lengthBuffer[lenIndex++] = in.readByte();
				continue;
			}

			if (readIndex == 0) {
				readBuffer = new byte[BinaryUtil.toChar(lengthBuffer) - 4];
			}

			if (readIndex < readBuffer.length && readBuffer.length > 0) {
				readBuffer[readIndex++] = in.readByte();
				continue;
			}
		}

		if (readBuffer == null) {
			return;
		}

		BinaryChannelBuffer body = new BinaryChannelBuffer(readBuffer);

		byte type = body.readByte();

		Request request = new Request();
		request.setType(type);

		int queueId = 0;

		if (type != ClientMessage.CM_UpdateServerInfo.ordinal()) {
			queueId = body.readInt();
			logger.debug("Request queueId {}", queueId);
		}

		if (type == ClientMessage.CM_SyncRPCQueue.ordinal()) { // TODO is syn
																// rpc response
			ByteArrayOutputStream o = new ByteArrayOutputStream();
			o.write(BinaryUtil.toByta((byte) ServerMessage.SM_SyncRPCQueue
					.ordinal()));
			o.write(BinaryUtil.toByta(queueId)); // queueId
			//int queueSize = 
			body.readInt();		//TODO QUEUE SIZE 
			o.write(BinaryUtil.toByta(10)); // incrSize
			o.write(BinaryUtil.toByta(100)); // availSize

			byte[] byteArray = o.toByteArray();

			Packet packet = new Packet();
			packet.setBodyArrayTo(byteArray);
			packet.setRequest(request);
			out.add(packet);
			return;
		}

		int rpcId = body.readInt();
		if (type == ClientMessage.CM_CancelRPC.ordinal()) { // throw
			// break;
		} else {
			request.setQueueId(queueId);
			request.setRpcId(rpcId);
			request.setUrl(body.readString());

			byte[] data = new byte[body.readInt()];
			body.readBytes(data);

			request.setUserData(data);

			// If client request, set text parameter map
			if (type == ClientMessage.CM_RequestTextRPC.ordinal()) {
				HashMap<String, String> paramMap = new HashMap<String, String>();
				while (body.readable()) {
					paramMap.put(body.readString(), body.readString());
				}
				request.setParamMap(paramMap);
			}

			// If server request, set binary parameter array
			else if (type == ClientMessage.CM_RequestBinaryRPC.ordinal()) {
				byte[] paramArray = new byte[body.readableBytes()];
				body.readBytes(paramArray);
				request.setParamArray(paramArray);
			}
			
			// request for update server info
			else if (type == ClientMessage.CM_UpdateServerInfo.ordinal()) {
				body.readString();//just discard
				body.readShort(); //channel alias port
			}
			// if client response
			else if (type == ClientMessage.CM_ResponseStatus.ordinal()
					|| type == ClientMessage.CM_ResponseOnlineInfo.ordinal()) {
				byte[] paramArray = new byte[body.readableBytes()];
				body.readBytes(paramArray);

				Packet packet = new Packet();
				packet.setBodyArrayTo(paramArray);
				packet.setRequest(request);
				out.add(packet);
				return;
			}

			logger.debug("Request rpcId {} type {} , readDataLength {}", rpcId, type, readBuffer.length);

			// 特殊请求不处理
			if (type == ClientMessage.CM_UpdateServerInfo.ordinal()) {
				// DO NOTHING ;
			} else {
				Packet packet = new Packet();
				packet.setRequest(request);
				packet.setBody(body);
				out.add(packet);
			}

		}
	}
}
