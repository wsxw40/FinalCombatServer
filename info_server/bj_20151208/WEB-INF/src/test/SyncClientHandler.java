package test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

public class SyncClientHandler extends SimpleChannelUpstreamHandler {

    private final Object observer;

    private final Map<Object, Response> responses = Collections.synchronizedMap(new HashMap<Object, Response>());

    public SyncClientHandler(Object observer) {
        this.observer = observer;
    }

    public boolean containsResponse(Object key) {
        return responses.containsKey(key);
    }

    public Response takeResponse(Object key) {
        return responses.remove(key);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Response response = (Response) e.getMessage();
        responses.put(response.getRpc(), response);
        synchronized (observer) {
            observer.notifyAll();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }

}
