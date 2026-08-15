package com.pearl.o2o.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.enumuration.ClientMessage;
import com.pearl.o2o.enumuration.ServerMessage;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;


/** one dispatcher thread per connection*/
public class RequestDispatcher implements Runnable{
	private static final Logger logger = LoggerFactory.getLogger(RequestDispatcher.class);


	//private LinkedBlockingQueue<byte[]> requestQueue;
	private LinkedBlockingQueue<OrginalRPCRequest> requestQueue;
	private LinkedBlockingQueue<byte[]> responseQueue;

	private ThreadPoolExecutor loginExec  = (ThreadPoolExecutor) Executors.newFixedThreadPool(ConfigurationUtil.SOCKET_LOGIN_POOLSIZE);		//thread pool to serve login request only
	private ThreadPoolExecutor serverExec = (ThreadPoolExecutor) Executors.newFixedThreadPool(ConfigurationUtil.SOCKET_SERVER_POOLSIZE);	//thread pool to serve server requests
	private ThreadPoolExecutor clientExec = (ThreadPoolExecutor) Executors.newFixedThreadPool(ConfigurationUtil.SOCKET_CLIENT_POOLSIZE);	//thread pool to serve client requests
	private ThreadPoolExecutor gameExec = (ThreadPoolExecutor) Executors.newFixedThreadPool(ConfigurationUtil.SOCKET_GAME_POOLSIZE);	//thread pool to serve client requests
	private ThreadPoolExecutor playerExec = (ThreadPoolExecutor) Executors.newFixedThreadPool(ConfigurationUtil.SOCKET_PLAYER_POOLSIZE);	//thread pool to serve client requests

	
	private Map<Integer, ThreadPoolExecutor> poolMap = new HashMap<Integer, ThreadPoolExecutor>();
	
	private final  DefaultSocketHandler socketHandler;

	public RequestDispatcher(LinkedBlockingQueue<OrginalRPCRequest> requestQueue, LinkedBlockingQueue<byte[]> responseQueue, DefaultSocketHandler handler){
		this.requestQueue = requestQueue;
		this.responseQueue = responseQueue;
		this.socketHandler = handler;
		
		poolMap.put(0, clientExec);//4
		poolMap.put(1, playerExec);//8
		poolMap.put(2, loginExec); //8
		poolMap.put(3, serverExec); //4
		poolMap.put(4, gameExec); //8
		
	}

	@Override
	public void run() {
		try{
			next_request:
			while(!Thread.interrupted()){
				OrginalRPCRequest orgRequest = requestQueue.take();
				//if cancel
		    	if (orgRequest.isCancel()) {
		    		continue next_request;
		    	}
		    	
		    	if(orgRequest.isSyncRPC()){
		    		byte[] byteArray = getSyncRPCQueueResponse(orgRequest.getQueueId(), orgRequest.getQueueSize());
		    		sendSyncRPCQueueResponse(byteArray);
		    		continue next_request;
		    	}		    	

				Request request = new Request();

				byte [] requestArray = orgRequest.getRequestBytes();
		    	BinaryChannelBuffer buf = new BinaryChannelBuffer(requestArray);
		    	try{
		    	    byte type = buf.readByte();
		    		request.setType(type);
		    		//RPC 璇锋眰閮戒細鍙戞潵 QUEUE ID
		            if (type == ClientMessage.CM_CancelRPC.ordinal() || type == ClientMessage.CM_RequestBinaryRPC.ordinal() || type == ClientMessage.CM_RequestTextRPC.ordinal()|| type == ClientMessage.CM_ResponseOnlineInfo.ordinal()|| type == ClientMessage.CM_ResponseStatus.ordinal()) {
		                int queueId = buf.readInt();
		                request.setQueueId(queueId);
		            }

		    		request.setRpcId(buf.readInt());
		    		request.setUrl(buf.readString());

			    	byte[] userData  = new byte[buf.readInt()];
			    	buf.readBytes(userData);
			    	request.setUserData(userData);


			    	//If client request, set text parameter map
			    	if(request.getType() == ClientMessage.CM_RequestTextRPC.ordinal()){
				    	HashMap<String, String> paramMap = new HashMap<String, String>();
				    	while(buf.readable()){
				    		paramMap.put(buf.readString(), buf.readString());
				    	}
				    	request.setParamMap(paramMap);
			    	}

			    	//If server request, set binary parameter array
			    	else if(request.getType() == ClientMessage.CM_RequestBinaryRPC.ordinal()){
			    		byte[] paramArray = new byte[buf.readableBytes()];
			    		buf.readBytes(paramArray);
			    		request.setParamArray(paramArray);
			    	}
			    	//request for update server info
			    	else if(request.getType() == ClientMessage.CM_UpdateServerInfo.ordinal()){
			    		String useLessStr = buf.readString();//just discard
			    		short port = buf.readShort();

			    		this.socketHandler.setChannelAlias(port);
			    	}
			    	//if client response
			    	else if (request.getType() == ClientMessage.CM_ResponseStatus.ordinal() ||
			    				request.getType() == ClientMessage.CM_ResponseOnlineInfo.ordinal()){
			    		Task<byte[]> task =  socketHandler.getClientBinaryResponseQueue().get(request.getRpcId());
			    		if (task != null){
			    			byte[] paramArray = new byte[buf.readableBytes()];
				    		buf.readBytes(paramArray);
			    			task.singnal(paramArray);
			    		}
			    	}
			    	//特殊请求不处理
			    	if(request.getType() == ClientMessage.CM_UpdateServerInfo.ordinal() || request.getType() == ClientMessage.CM_ResponseOnlineInfo.ordinal()){
						//DO NOTHING ;
					}else{
						ThreadPoolExecutor exec = poolMap.get(request.getQueueId());
						if (exec != null){
						    Runnable process = new BaseThread(request, responseQueue, exec);
							exec.execute(process);
						}
					}
			    	
		    	}
		    	catch(Exception ioe){
		    		logger.error("RequestDispatcher error ",ioe);
		    	}
			}
		}
		catch(InterruptedException e){
			logger.error("RequestDispatcher InterruptedException error ",e);
		}
	}
	
	
	protected byte[] getSyncRPCQueueResponse (int queueId, int queueSize){
		try{
			ThreadPoolExecutor pool = poolMap.get(queueId);
			int incrSize = 0;
			int availSize = 0;
			int poolSize = 0;
			
			if(pool != null){
				if(ConfigurationUtil.SWITCH_INFO.getIsOn()){
					poolSize = pool.getCorePoolSize() * 2;
				}
				availSize = poolSize - pool.getActiveCount() - pool.getQueue().size();
			}			
			
			incrSize = availSize - queueSize;
			if(incrSize < -poolSize || incrSize > poolSize){
				logger.error("incrSize="+incrSize+" poolSize="+poolSize+" CorePoolSize: " + pool.getCorePoolSize() + ", Active: " + pool.getActiveCount() + ", Queue: " + pool.getQueue().size() + ", QueueSize: " + queueSize + ", MaxPoolSize" + pool.getMaximumPoolSize() + ", PoolSize: " + pool.getPoolSize() + ", LagestPoolSize:" + pool.getLargestPoolSize());
			}
			
        	ByteArrayOutputStream out = new ByteArrayOutputStream();
        	out.write(BinaryUtil.toByta((byte)ServerMessage.SM_SyncRPCQueue.ordinal()));
        	out.write(BinaryUtil.toByta(queueId));
        	out.write(BinaryUtil.toByta(incrSize));
        	out.write(BinaryUtil.toByta(availSize));
        	return out.toByteArray();
		}
		catch(IOException e){
			logger.error("IOException in sendSyncRPCQueueResponse():", e);
		}
		catch (Exception e) {
			logger.error("Exception in sendSyncRPCQueueResponse():", e);
		}
		return Constants.EMPTY_BYTE_ARRAY;
	}

	protected void sendSyncRPCQueueResponse(byte[] responseArray){
		try{
			responseQueue.put(responseArray);
		}
		catch(InterruptedException ire){
			logger.error("sendResponse() in RequestDispatch Interrupted: ", ire);
		}
	}	


	public ExecutorService getLoginExec() {
		return loginExec;
	}

	public ExecutorService getClientExec() {
		return clientExec;
	}


	public ExecutorService getServerExec() {
		return serverExec;
	}

	public ExecutorService getGameExec() {
		return gameExec;
	}

	public ExecutorService getPlayerExec() {
		return playerExec;
	}

}
