package com.pearl.o2o.manager;

import java.io.IOException;

import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.utils.AddrUtil;


public class XmemCachedManager
{
/*    
    private static XMemcachedClient mcc;

    public static XMemcachedClient getClient(){
    	try{
	    	if(mcc == null){
	    		return getClientInstance();
	    	}	    
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	
    	return mcc;    	
    }
    
	protected synchronized static XMemcachedClient getClientInstance() throws IOException{
		if(mcc == null){
			MemcachedClientBuilder builder = new XMemcachedClientBuilder(
                    AddrUtil.getAddresses("192.168.1.16:11211 192.168.1.111:11211"), new int[]{1, 1});
//			builder.setCommandFactory(new BinaryCommandFactory());
			mcc = (XMemcachedClient)builder.build();
		}				
		
		return mcc;
	}*/
}
