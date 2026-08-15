package com.pearl.o2o.socket;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.AdaptiveReceiveBufferSizePredictor;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class PlayerCountSocketServer {   
	private static final Logger log = LoggerFactory.getLogger(PlayerCountSocketServer.class.getName());	
	private static PlayerCountSocketServer server;
	private ServerBootstrap bootstrap;
	private Channel channel;
	
	private Integer port;	
	
	
	public static void main(String[] args){
		if(args.length > 0){
			PlayerCountSocketServer.getInstance(Integer.valueOf(args[0])).start();
		}
		else{
			PlayerCountSocketServer.getInstance(9018).start();
		}
	}
	
	private PlayerCountSocketServer(){		
	}
	
	
	public static PlayerCountSocketServer getInstance(int port){
		if(server == null){
			server = new PlayerCountSocketServer();
			server.port = port;
		}
		
		return server;
	}		
  
    
    public void start() {   
        this.bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool(), 4));
        bootstrap.setPipelineFactory(new ServerPipelineFactory());  
        bootstrap.setOption("child.receiveBufferSizePredictor", new AdaptiveReceiveBufferSizePredictor(16*1024, 16*1024, 64*1024));
        
        // Start the connection attempt.
        this.channel = bootstrap.bind(new InetSocketAddress(port));  
        log.info("player count SocketServer listening on port: " + port);
    }   
    
    public void stop(){
    	if(this.channel != null){
    		this.channel.close().awaitUninterruptibly();
    	}

        // Shut down thread pools to exit.
        this.bootstrap.releaseExternalResources();
    }
    
    /**
     * each connection(channel) will create a handler 
     * each handler has its own thread pool -> each connection has its own pool
     */
    private class ServerPipelineFactory implements ChannelPipelineFactory {   
        public ChannelPipeline getPipeline() throws Exception {   
            ChannelPipeline pipeline = Channels.pipeline();   
            pipeline.addLast("handler", new PlayerCountSocketHandler());   
            return pipeline;   
        }   
    }
}  