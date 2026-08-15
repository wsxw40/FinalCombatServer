package com.pearl.o2o.socket;

import java.net.SocketAddress;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.pearl.o2o.enumuration.ClientMessage;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.ConfigurationUtil;

/** one handler each connection*/
public class DefaultSocketHandler extends SimpleChannelUpstreamHandler {
    private static final Logger log = LoggerFactory.getLogger(DefaultSocketHandler.class.getName());

    private Channel channel;
    private int channelAlias;//this value will be set by client
    //used for unique rpcId
	private static AtomicInteger count = new AtomicInteger();

    private byte[] lengthBuffer = new byte[2];
    private byte   lengthIndex  = 0;
    private byte[] headtip=new byte[2];
    private byte[] readBuffer;
    private int    readIndex;
    private int index=0;
    //input queue
	//private LinkedBlockingQueue<byte[]> requestQueue = new LinkedBlockingQueue<byte[]>();
    private LinkedBlockingQueue<OrginalRPCRequest> requestQueue = new LinkedBlockingQueue<OrginalRPCRequest>();
	private LinkedBlockingQueue<byte[]> responseQueue = new LinkedBlockingQueue<byte[]>();

	//response map
	private final ConcurrentHashMap<Integer,Task<byte[]>> clientBinaryResponseQueue = new ConcurrentHashMap<Integer, Task<byte[]>>();

	private ExecutorService requestExec = Executors.newSingleThreadExecutor();
	private ExecutorService returnExec = Executors.newSingleThreadExecutor();

	private RequestDispatcher dispatcher = new RequestDispatcher(requestQueue, responseQueue,this);
	private ReturnConsumer consumer = new ReturnConsumer(this, responseQueue);


	@Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
    	ConfigurationUtil.CHANNEL_HANDLERS.registChannel(this);
    	log.info("channel " + e.getChannel().getRemoteAddress().toString() + "connected ");
    	//start thread
		requestExec.execute(dispatcher);
		returnExec.execute(consumer);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
    	ChannelBuffer input = (ChannelBuffer)e.getMessage();
    	channel = e.getChannel();
    	while(input.readable()){
    		if(index==2){
    			if(BinaryUtil.toChar(headtip)==21314){
    				if(lengthIndex == 2){
            			//2. If right size of length received, start reading content data
            			if(readIndex == 0){
            				readBuffer = new byte[BinaryUtil.toChar(lengthBuffer) -4];
            			}
            			//3. If read content size matches length, execute a command
        				if(readIndex == readBuffer.length && readBuffer.length != 0){
        					//requestQueue.put(readBuffer);
        					produceRequest(readBuffer);
        					lengthIndex = 0;
        					readIndex	= 0;
        					index=0;
        				}
        				else{
        					readBuffer[readIndex++] = input.readByte();
        				}
            		}
            		else{
            			//1. Read length data
            			lengthBuffer[lengthIndex++] = input.readByte();
            		}
    			}else{
    				log.warn("wrong package head   "+BinaryUtil.toChar(headtip)+"    "+channel.getRemoteAddress().toString());
    				channel.close();
    			}

    		}else{
    			headtip[index++]=input.readByte();
    		}

    	}
    	//4. Check if the final byte read completes a command, execute it
    	if(lengthIndex == 2 && readIndex == readBuffer.length && readBuffer.length != 0){
    		produceRequest(readBuffer);
			lengthIndex = 0;
			readIndex	= 0;
			index=0;
    	}
    }

    private void produceRequest(byte [] requestByteArray){
    	try {
			//special process cancel request
			BinaryChannelBuffer buf = new BinaryChannelBuffer(readBuffer);
			byte type = buf.readByte();

			OrginalRPCRequest req = new OrginalRPCRequest();
			
            if (type == ClientMessage.CM_SyncRPCQueue.ordinal()){
            	req.setQueueId(buf.readInt());
            	req.setQueueSize(buf.readInt());
            	req.setSyncRPC(true);
            	requestQueue.put(req);
            	return;
            }			
			
			//RPC 请求都会发来队列ID
            if (type == ClientMessage.CM_CancelRPC.ordinal()
                    || type == ClientMessage.CM_RequestBinaryRPC.ordinal()
                    || type == ClientMessage.CM_RequestTextRPC.ordinal()
                    || type == ClientMessage.CM_ResponseOnlineInfo.ordinal()
                    || type == ClientMessage.CM_ResponseStatus.ordinal()
                    ) {

			    int queueId = buf.readInt();
			    req.setQueueId(queueId);
//			    int queueSize=buf.readInt();
			    
			}

			int rpcId = buf.readInt();
    		if (type == ClientMessage.CM_CancelRPC.ordinal()) {
    			for (OrginalRPCRequest rq : requestQueue) {
    				if (rq != null && rq.getRpcId() == rpcId) {
    					rq.setCancel(true);
    					return ;
    				}
    			}
    		}else {
    			req.setRpcId(rpcId);
    			req.setRequestBytes(readBuffer);
    			requestQueue.put(req);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        // Close the connection when an exception is raised.
        log.warn("Unexpected exception from downstream.", e.getCause());
        channel.close();
    }

    public void push(byte[] output){
    	ChannelBuffer buff = ChannelBuffers.buffer(output.length+4);
    	buff.writeBytes((BinaryUtil.toByta((char)(21314))));
		buff.writeBytes((BinaryUtil.toByta((char)(output.length+4))));
    	buff.writeBytes(output);
    	channel.write(buff);
    }

    @Override
	public void channelDisconnected(ChannelHandlerContext ctx,
			ChannelStateEvent e) throws Exception {
    	ConfigurationUtil.CHANNEL_HANDLERS.removeChannel(this);
    	requestExec.shutdown();
    	returnExec.shutdown();
	}

    @Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
    	ConfigurationUtil.CHANNEL_HANDLERS.removeChannel(this);
    	requestExec.shutdown();
    	returnExec.shutdown();
	}

	public String getDisplayName(){
    	return  channel.getRemoteAddress().toString() + " port: " + this.getChannelAlias() + "  id:   " + channel.getId();
    }

	public  Integer generateId(){
		int current = count.getAndIncrement() -1;
		if (current >= Integer.MAX_VALUE) {
			synchronized (count){
				int nowValue = count.intValue();
				if (nowValue >= Integer.MAX_VALUE){
					count.set(0);
				}else{
					return count.get();
				}
			}
		}
		return current ;
	}

    public  ConcurrentHashMap<Integer, Task<byte[]>> getClientBinaryResponseQueue() {
		return clientBinaryResponseQueue;
	}

	public ExecutorService getLoginPool() {
		return dispatcher.getLoginExec();
	}

	public ExecutorService getServerPool() {
		return dispatcher.getServerExec();
	}

	public ExecutorService getClientPool() {
		return dispatcher.getClientExec();
	}

	public ExecutorService getGamePool() {
		return dispatcher.getGameExec();
	}
	public ExecutorService getPlayerPool() {
		return dispatcher.getPlayerExec();
	}

	public Channel getChannel() {
		return channel;
	}

	public int getChannelAlias() {
		return channelAlias;
	}

	public void setChannelAlias(int channelAlias) {
		this.channelAlias = channelAlias;
	}

	@SuppressWarnings("unchecked")
	public BlockingQueue getReqQueue(){
    	return requestQueue;
    }

    @SuppressWarnings("unchecked")
	public BlockingQueue getResQueue(){
    	return responseQueue;
    }
}