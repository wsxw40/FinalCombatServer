package com.pearl.o2o.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;

public class SocketClient {
	private static final Logger log = Logger.getLogger(SocketClient.class.getName());
	
	private static SocketClient client;
	private ClientBootstrap bootstrap;
	private Channel channel;
	
	private  String host;
	private  Integer port;
	private	 int retryTimes = Integer.MAX_VALUE;
	private  final int interval = 300;//ms
	private  int maxSleepTime = 5 * 1000;//ms
	private  volatile boolean isReStarting = false;//indicate whether there is a thread doing the restarting
	private  final Object lock = new Object();//inner lock for mutex
	
	private SocketClient(){
		
	}
	
	
	public static SocketClient getInstance(){
		if(client == null){
			client = new SocketClient();
		}
		
		return client;
	}

    public boolean start(){
    	if (host == null || port ==null){
    		log.error("host/ip of socket client is empty");
    		throw new RuntimeException("host/ip of socket client is empty");
    	}
    	
        // Configure the client.
        this.bootstrap = new ClientBootstrap(
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));
        
        final SocketClient socketClient = this;
        // Set up the pipeline factory.
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public ChannelPipeline getPipeline() throws Exception {
                return Channels.pipeline(new SocketClientHandler(socketClient));
            }
        });

        // Start the connection attempt.
        ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port));        
        if (!future.awaitUninterruptibly().isSuccess()) {
            System.out.println("--- CLIENT - Failed to connect to server at " + host);
            this.bootstrap.releaseExternalResources();
            return false;
        }        
	        
        this.channel = future.getChannel();
        return this.channel.isConnected();
    }
    
    public void stop(){
    	if(this.channel != null){
    		this.channel.close().awaitUninterruptibly();
    	}

        // Shut down thread pools to exit.
        this.bootstrap.releaseExternalResources();
    }
    
    
    /**
     * Method to send private message to a player
     * @param to: Receiver
     * @param msg: Message Body
     * @return boolean
     */
    public boolean message(String to, String msg) {
    	try{
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		out.write(BinaryUtil.toByta((byte)0));
    		out.write(BinaryUtil.toByta(to));
    		out.write(BinaryUtil.toByta(Constants.MSG_BROADCAST));
    		out.write(BinaryUtil.toByta(""));    		
    		out.write(BinaryUtil.toByta(msg));
			byte[] array = out.toByteArray();	
			log.error("channelListBroadCast发送信息："+msg+" to:"+to);
			return send(array);   
		}
		catch(IOException e){
			log.error("Exception in sending server message: ", e);
		}    	
		return false;
    }
    
    /**
     * Method to push msg to client
     * @param to: Receiver
     * @param msg: Message Body
     * @return boolean
     */
    public boolean pushCMD(String to, String msg) {
    	try{
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		out.write(BinaryUtil.toByta((byte)0));
    		out.write(BinaryUtil.toByta(to));
    		out.write(BinaryUtil.toByta(Constants.MSG_CMD));
    		out.write(BinaryUtil.toByta(""));    		
    		out.write(BinaryUtil.toByta(msg));
    		log.error("pushCMD发送信息："+msg+" to:"+to);
			byte[] array = out.toByteArray();					    		
			return send(array);   
		}
		catch(IOException e){
			log.error("Exception in sending server message: ", e);
		}    	
		return false;
    }
    public boolean messageXLB(String msg, int chanel) {
    	try{
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		out.write(BinaryUtil.toByta((byte)1));
//    		out.write(BinaryUtil.toByta(chanel));
    		out.write(BinaryUtil.toByta(Constants.MSG_XLB));
    		out.write(BinaryUtil.toByta(""));
    		out.write(BinaryUtil.toByta(msg));
			byte[] array = out.toByteArray();					    		
			return send(array);   
		}
		catch(IOException e){
			log.error("Exception in sending server message: ", e);
		}    	
		
		return false;
    }
    public boolean messageDLB(Set<Integer> chanelList,String msg) {
    	boolean isSuccess=false;
    	try{
    		
//    		for(int id:chanelList){
	    		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    		out.write(BinaryUtil.toByta((byte)1));
//	    		out.write(BinaryUtil.toByta(id));
	    		out.write(BinaryUtil.toByta(Constants.MSG_DLB));
	    		out.write(BinaryUtil.toByta(""));
	    		out.write(BinaryUtil.toByta(msg)); 
	    		log.error("messageDLB发送信息："+msg);
				byte[] array = out.toByteArray();					    		
				isSuccess= send(array);
//				if(!isSuccess){
//					return false;
//				}
//    		}
    		return isSuccess;
		}
		catch(IOException e){
			log.error("Exception in sending server message: ", e);
		}    	
		
		return false;
    } 
    
    /**
     * Method to broadcast message to server
     * @param msg: Message Body
     * @return boolen
     */
    public boolean serverBroadCast(String msg) {
    	try{
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		out.write(BinaryUtil.toByta((byte)1));
    		out.write(BinaryUtil.toByta(Constants.MSG_BROADCAST));
    		out.write(BinaryUtil.toByta(""));
    		out.write(BinaryUtil.toByta(msg));
			byte[] array = out.toByteArray();					    		
			return send(array);   
		}
		catch(IOException e){
			log.error("Exception in sending server message: ", e);
		}    	
		
		return false;
    }
    
    /**
     * Method to broadcast message to channel
     * @param id: Channel ID
     * @param msg: Message Body
     * @return boolean
     */
    public boolean channelBroadCast(List<Integer> chanelList, String msg) {
    	try{
    		boolean isSuccess=false;
    		for(int id:chanelList){
	    		ByteArrayOutputStream out = new ByteArrayOutputStream();
	    		out.write(BinaryUtil.toByta((byte)2));
	    		out.write(BinaryUtil.toByta(id));
	    		out.write(BinaryUtil.toByta(Constants.MSG_BROADCAST));
	    		out.write(BinaryUtil.toByta(""));
	    		out.write(BinaryUtil.toByta(msg));  
				byte[] array = out.toByteArray();					    		
				isSuccess= send(array);
				log.error("channelListBroadCast发送信息："+msg);
				if(!isSuccess){
					return false;
				}
    		}
    		return isSuccess;
		}
		catch(IOException e){
			log.error("Exception in sending channel message: ", e);
		}    	
		
		return false;
    }
    /**
     * Method to broadcast message to server
     * @param id: Channel ID
     * @param msg: Message Body
     * @return boolean
     */
    public boolean channelBroadCast(int id, String msg) {
    	try{
    		ByteArrayOutputStream out = new ByteArrayOutputStream();
    		out.write(BinaryUtil.toByta((byte)2));
    		out.write(BinaryUtil.toByta(id));
    		out.write(BinaryUtil.toByta(Constants.MSG_BROADCAST));
    		out.write(BinaryUtil.toByta(""));
    		out.write(BinaryUtil.toByta(msg));  
			byte[] array = out.toByteArray();
			log.error("channelBroadCast发送信息："+msg);
			return send(array);  
		}
		catch(IOException e){
			log.error("Exception in sending channel message: ", e);
		}    	
		
		return false;
    }
    
    
    private boolean send(byte[] array) {
    	if(this.channel.isConnected()){				    		
			ChannelBuffer buff = ChannelBuffers.buffer(array.length+2);
			buff.writeBytes(BinaryUtil.toByta((short)(array.length+2)));
			buff.writeBytes(array);    	
			channel.write(buff);  
	    	return true;
    	}else{// try reconnect
    		restart();
    	}
    	return false;    	
    }

    protected void restart(){
    	if (isReStarting){// ensure there will be only one thread do the restarting
    		return ;
    	}
    	
		synchronized(lock){//mutext area,  set the flag 'restarting'
			if (isReStarting){
				return;//double check
			}
			//current thread needs to do the restart
			isReStarting = true;//set the flag
		}
		
		log.warn("find server not connected, try restart");
		new Thread(new Runnable(){// currently, send method will call restart, so need to sparm a new thread doing this
			@Override
			public void run() {
				int sleepTime = 0;
		    	try{
		    		stop();
		    	}catch (Exception e){
		    		//do nothing
		    	}
		    	boolean isConnected = false;
		    	try{
		    		isConnected = start();
		    	}catch(Exception e){
		    		//do nothing
		    	}
				int tryTimes = 1;
				while(tryTimes < retryTimes && !isConnected){
					if ((sleepTime + interval) > maxSleepTime ) {
						sleepTime = maxSleepTime;
					}else {
						sleepTime += interval;
					}
					
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
					try{
						log.warn("reconnecting.. retry NO." + tryTimes );
						stop();
						isConnected = start();
					}catch (Exception e){
						//do nothing
					}finally{
						tryTimes++;
					}
				}
				log.warn("restart successfully");
				isReStarting = false;
			}
		}).start();
    }

	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public Integer getPort() {
		return port;
	}


	public void setPort(Integer port) {
		this.port = port;
	}
}

