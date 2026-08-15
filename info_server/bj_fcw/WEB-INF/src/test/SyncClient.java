package test;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.pearl.o2o.utils.StringUtil;

public class SyncClient {

    private String host = "";
//    	ConfigurationUtil.TEST_SOCKET_HOST;
    private int port= StringUtil.toInt("");
//    		ConfigurationUtil.TEST_SOCKET_PORT);

    private ClientBootstrap bootstrap = null;
    private ChannelFuture future = null;

    private Listener listener = Listener.DEFAULT_LISTENER;

    public SyncClient() {
    }

    public SyncClient(Listener listener) {
        this.listener = listener;
    }

    public SyncClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public SyncClient(String host, int port, Listener listener) {
        this.host = host;
        this.port = port;
        this.listener = listener;
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public void start() {
        ChannelFactory factory = new NioClientSocketChannelFactory(
                Executors.newCachedThreadPool(),
                Executors.newCachedThreadPool());
        bootstrap = new ClientBootstrap(factory);
        bootstrap.setPipeline(Channels.pipeline(new RequestEncoder(), new ResponseDecoder(),
                new SyncClientHandler(this)));
        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);
        future = bootstrap.connect(new InetSocketAddress(host, port));
        future.awaitUninterruptibly(5000);
        if (!future.isSuccess()) {
            throw new RuntimeException(future.getCause());
        }
    }

    // TODO fix me
    public void stop() {
        if (future != null) {
            future.awaitUninterruptibly();
            future.getChannel().getCloseFuture().awaitUninterruptibly();
        }
        if (bootstrap != null) {
            bootstrap.releaseExternalResources();
            bootstrap.getFactory().releaseExternalResources();
        }
    }

    public Object send(Request request) {
        request.setRpc((int) Thread.currentThread().getId());       // TODO fix me
       
        SyncClientHandler handler = future.getChannel().getPipeline().get(SyncClientHandler.class);
        Event<Request> event = new Event<Request>();

        event.setRequest(request);
        listener.onPreparedSend(event);

        event.setSendTime(new Date());
        synchronized (future.getChannel()) {
            future.getChannel().write(request);
        }
        listener.onSendComplete(event);

        synchronized (this) {
            while (!handler.containsResponse(request.getRpc())) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        event.setReceivedTime(new Date()); 
        Response	response=handler.takeResponse(request.getRpc());
   	 	event.setResponse(response);
        listener.onReceived(event);
        if(request.getType()==0){//text rpc
             try {
				return new String(response.getResponseBody(),"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
        }
        	
            return response.getResponseBody();
        
    }

    public Object send(String url, Map<String, String> parameters) {
        CRequest req = new CRequest(url);
        req.putParameters(parameters);
        return send(req);
    }

    public Object send(String url, Object... parameters) {
        SRequest req = new SRequest(url);
        req.addParameters(parameters);
        return send(req);
    }

}
