package com.pearl.o2o.jmeter.protocol.tcp.sampler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.jmeter.protocol.tcp.sampler.AbstractTCPClient;
import org.apache.jmeter.protocol.tcp.sampler.ReadException;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.google.gson.Gson;
import com.pearl.o2o.protocal.PJMessage;
import com.pearl.o2o.protocal.cm.CM;
import com.pearl.o2o.protocal.sm.SM;

/**
 * @author lifengyang
 * 此类为AbstractTCPClient针对大冲锋项目提过的采样器实现
 * 注意事项：
 * 1.count rpcId 为全局递增计数器
 * 2.接收数据会过滤掉无关信息及和请求数据类型不一致的数据
 * 2.
 */
public abstract class PJRPCMessageClientImpl extends AbstractTCPClient {
	private static final int BUFFERSIZE = 4096;

	private static final AtomicInteger count = new AtomicInteger();

	protected static final Logger log = LoggingManager.getLoggerForClass();
	protected static final Gson gson = new Gson();

	public final int generateRpcId() {
		return count.incrementAndGet();
	}

	@Override
	public final void write(OutputStream os, String s) {
		try {
			ChannelBuffer channelBuffer = converToCM(s).toChannelBuffer();
			int offset = channelBuffer.readerIndex() + channelBuffer.arrayOffset();
			os.write(channelBuffer.array(), offset, channelBuffer.readableBytes());
			os.flush();
		} catch (Exception e) {
			log.warn("Write error", e);
		}
		log.debug("Wrote: " + s);
	}
	@Override
	public void write(OutputStream os, InputStream is) throws IOException {
		// TODO Auto-generated method stub
	}
	protected abstract CM converToCM(String str);

	protected abstract SM buildSM(ChannelBuffer channelBuffer) throws ReadException;

	protected abstract String getStringFace(SM sm);

	protected abstract int getType();

	@Override
	public final String read(InputStream is) throws ReadException {
		//all recevie message String face
		String result = "";
		try {
			//mark recevie expect message already;
			boolean isReceive = false;
			ChannelBuffer channelBuffer = ChannelBuffers.dynamicBuffer(ByteOrder.LITTLE_ENDIAN, BUFFERSIZE);
			byte[] buffer = new byte[BUFFERSIZE];
			// read data byte length
			int x = 0;
			while ((x = is.read(buffer)) != -1) {//read
				channelBuffer.writeBytes(buffer, 0, x);
//				System.out.printf("x:%s;channelBuffer:%s;\n", x, channelBuffer.readableBytes());
				while (channelBuffer.readableBytes() > 4) {//handle (ps:contain head and datalength;)
					channelBuffer.markReaderIndex();

					int headtip = channelBuffer.readUnsignedShort();
					if (headtip != PJMessage.HEADTIP) {//message right
						throw new IOException("Read error:headtip not right");
					}

					int dataBodylength = channelBuffer.readUnsignedShort() - 4;
					
					if (channelBuffer.readableBytes() >= dataBodylength) {//message whole
						ChannelBuffer readBytes = channelBuffer.readBytes(dataBodylength);
						byte receiveType = readBytes.readByte();
						readBytes.skipBytes(-1);
//						System.out.printf("dataBodylength:%s;receiveType:%s;remain:%s;\n", dataBodylength, receiveType, channelBuffer.readableBytes());
						if (receiveType == getType()) {//expect message
							SM sm = buildSM(readBytes);
							isReceive = true;
							result += "\n" + getStringFace(sm);
						} else {//other message
							// reject
							// sm = SM.buildSM(readBytes,receiveType, 0, null);
						}

						if ((!channelBuffer.readable()) && isReceive) {//all message receive complete
							return result;
						}
					} else {//mesasge not whole;need read again;
						channelBuffer.resetReaderIndex();
						break;
					}
				}
			}

		} catch (IOException e) {
			throw new ReadException("", e, result);
		} 
//		catch (Exception e) {
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			PrintWriter pw = new PrintWriter(baos);
//			try {
//				e.printStackTrace(pw);
//				pw.flush();
//				log.warn("PJRPCMessageClientImpl Read error:", e);
//				return "PJRPCMessageClientImpl Read error!\nresult:" + result + "\nerror:" + new String(baos.toByteArray());
//			} finally{
//				pw.close();
//			}
//		}
		return result;
	}
}
