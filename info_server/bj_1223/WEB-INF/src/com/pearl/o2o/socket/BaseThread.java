package com.pearl.o2o.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.jboss.netty.buffer.ChannelBuffers;
import org.springframework.beans.factory.BeanFactory;

import com.pearl.o2o.enumuration.ClientMessage;
import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.enumuration.StatusCode;
import com.pearl.o2o.servlet.client.BaseClientServlet;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
/** handler request*/
public class BaseThread implements Runnable{
    private static final BeanFactory beanFactory = ConfigurationUtil.beanFactory;
    private static final byte[] SUCCESS_BYTE_ARRAY = BinaryUtil.toByta(StatusCode.SUCCESS.ordinal());
	private static Logger log = LoggerFactory.getLogger(BaseThread.class.getName());
	private static Logger performanceLog = LoggerFactory.getLogger("request");

	private LinkedBlockingQueue<byte[]> responseQueue;
	private ThreadPoolExecutor threadPool;
    private Request	request;

	public BaseThread(Request request, LinkedBlockingQueue<byte[]> responseQueue, ThreadPoolExecutor threadPool){
		this.request = request;
		this.responseQueue = responseQueue;
		this.threadPool = threadPool;
	}

	@Override
	public void run(){
		List<Response> result = null;
		long start = System.currentTimeMillis();
		long end = start;
		ServerMessage	responseType = null;
		HttpServlet servlet = null;
		
		//sendError时需要发送期望请求大小，所以这里要在TRY块外先计算一次
		int expectedRequestCount = getExpectedRequestCount(threadPool);
		
		try{
			if(request.getType() == ClientMessage.CM_RequestTextRPC.ordinal()){
				responseType = ServerMessage.SM_ResponseTextRPC;
				servlet = (BaseClientServlet)beanFactory.getBean(request.getUrl());
				result = ((BaseClientServlet) servlet).execute(request.getParamMap());
			}
			else if(request.getType() == ClientMessage.CM_RequestBinaryRPC.ordinal()){
				responseType = ServerMessage.SM_ResponseBinaryRPC;
				servlet = (BaseServerServlet)beanFactory.getBean(request.getUrl());
				//use 'wrappedBuffer' will just wrap the byte array, any modification on array will reflect to the buffer
				result = ((BaseServerServlet) servlet).execute((new BinaryChannelBuffer(ChannelBuffers.wrappedBuffer((request.getParamArray())))));
				//result = servlet.execute(new BinaryChannelBuffer(ChannelBuffers.copiedBuffer(request.getParamArray())));
			}else{
				log.warn("Unknown request type :" + request.getType());
			}

			end = System.currentTimeMillis();

			expectedRequestCount = getExpectedRequestCount(threadPool);
			
			byte[] responseArray = getResponse(result, request.getQueueId(), expectedRequestCount);

//			log.info("request.getUrl()"+request.getUrl()+"    responseArray length ="+responseArray.length);
			if(responseArray.length >= 0 && responseArray.length < 65535){
				if(responseArray.length>62000){
					log.warn("responseArray length ="+responseArray.length);
				}
				sendResponse(responseArray);
			}
			else{
				String errorMsg="exception happen in send response to game sever,responseType="+responseType+" url="+request.getUrl()+" request type="+request.getType();
				if(request.getType() == ClientMessage.CM_RequestTextRPC.ordinal()){
					errorMsg+=" params:";
					for(Map.Entry<String, String> entry: request.getParamMap().entrySet()) {
						errorMsg+=entry.getKey()+"-"+entry.getValue()+" ";
					}
				}
				log.error(errorMsg);
				sendError(responseType, request.getQueueId(),  expectedRequestCount);
			}
			
			long threadCosume = end - start;
    		performanceLog.debug(request.getUrl() + " " + threadCosume + " ms");
			
//			//performance
			if(ConfigurationUtil.SWITCH_PERFORMANCE_MONITOR.getIsOn() &&
					(request.getType() == ClientMessage.CM_RequestTextRPC.ordinal()|| request.getType() == ClientMessage.CM_RequestBinaryRPC.ordinal())){
	    		try{
	    			//ConfigurationUtil.performanceDatas.addInvokeRecord(request.getUrl(), (int) threadCosume);
	    			ConfigurationUtil.performanceDatas.addInvokeRecord(servlet.getClass().getName(), (int) threadCosume);
	    		}catch(Exception e){
	    			performanceLog.error("error happened in ",e);
	    		}
			}
//			for(Response rp:result){
//				log.error(rp.getText());
//			}
//			log.debug("Service Completed: " + request.getUrl());
		}
		catch(Exception e){
			log.warn("Exception in baseThread run():request="+request.getUrl()+" request type="+request.getType(), e);
			sendError(responseType, request.getQueueId(), expectedRequestCount);
			return;
		}
	}
	
	private int getExpectedRequestCount(ThreadPoolExecutor threadPool){
/*		int reaminActiveThreads = threadPool.getCorePoolSize() - threadPool.getActiveCount();
		int currentQueueSize = threadPool.getQueue().size();
		int queueThreshold = threadPool.getCorePoolSize() * 2;//队列阀值
		
		int expectedRequestCounts = 0;
		if ( reaminActiveThreads > 0 && currentQueueSize < threadPool.getCorePoolSize()){//如果有空闲的线程,且当前队列小于线程数大小，则期望返回剩余线程数及线程池大小的一半
			expectedRequestCounts = reaminActiveThreads + threadPool.getCorePoolSize()/2;
		}else{//无空闲
			if (currentQueueSize < queueThreshold) {//如果积压队列大小小于线程池2倍大小
				expectedRequestCounts = queueThreshold - currentQueueSize;
			}else{//否则返回0
				expectedRequestCounts = 0;
			}
		}*/
		
/*		int expectedRequestCounts = threadPool.getCorePoolSize()*2 - threadPool.getActiveCount();
		return expectedRequestCounts;*/
		if(!ConfigurationUtil.SWITCH_INFO.getIsOn()){
			return 0;
		}
		
		return threadPool.getActiveCount() + threadPool.getQueue().size();
	}
	
	

	private void sendError (ServerMessage responseType, int queueId,  int expectedRequestCount){
		BinaryChannelBuffer buffer = new BinaryChannelBuffer(BinaryUtil.getLength(
											(byte)responseType.ordinal())+
											BinaryUtil.getLength(queueId)+
											BinaryUtil.getLength(expectedRequestCount)+
											BinaryUtil.getLength(request.getRpcId())+
											BinaryUtil.getLength(StatusCode.FAIL.ordinal())+
											BinaryUtil.getLength(request.getUserData()));

		buffer.writeByte((byte)responseType.ordinal());
		buffer.writeInt(queueId);
		buffer.writeInt(expectedRequestCount);
		buffer.writeInt(request.getRpcId());
		buffer.writeInt(StatusCode.FAIL.ordinal());
		buffer.writeBytes(request.getUserData());

		try{
			responseQueue.put(buffer.array());
		}
		catch(InterruptedException ire){
			log.error("sendError() in BaseThread Interrupted: ", ire);
		}
	}

	protected byte[] getResponse (List<Response> result, int queueId, int queueCurrentSize){
		if(result == null){
			return Constants.EMPTY_BYTE_ARRAY;
		}
		try{
			for (Response response : result){

	        	ByteArrayOutputStream out = new ByteArrayOutputStream();
	        	out.write(BinaryUtil.toByta((byte)response.getType().ordinal()));

				if(response.getType() == ServerMessage.SM_ResponseTextRPC){
				    //写队列剩余大小
				    out.write(BinaryUtil.toByta(queueId));
				    out.write(BinaryUtil.toByta(queueCurrentSize));

		        	out.write(BinaryUtil.toByta(request.getRpcId()));
		        	out.write(SUCCESS_BYTE_ARRAY);
		        	out.write(request.getUserData());
		        	if(response.getText() != null){
						out.write(response.getText().getBytes(Charset.forName(Constants.ENCODING)));
		        	}
		        	return out.toByteArray();
				}
				else if(response.getType() == ServerMessage.SM_ResponseBinaryRPC){
				    out.write(BinaryUtil.toByta(queueId));
                    out.write(BinaryUtil.toByta(queueCurrentSize));

		        	out.write(BinaryUtil.toByta(request.getRpcId()));
		        	out.write(SUCCESS_BYTE_ARRAY);
		        	out.write(request.getUserData());
		        	if(response.getBinary() != null){
						out.write(response.getBinary());
		        	}
		        	return out.toByteArray();
				}
				else if(response.getType() == ServerMessage.SM_SendChat){
					out.write(response.getBinary());
					return out.toByteArray();
				}
			}
		}
		catch(IOException e){
			log.error("IOException in sendResponse():", e);
		}
		return Constants.EMPTY_BYTE_ARRAY;
	}

	protected void sendResponse(byte[] responseArray){
		try{
			responseQueue.put(responseArray);
		}
		catch(InterruptedException ire){
			log.error("sendResponse() in BaseThread Interrupted: ", ire);
		}
	}
}
