package com.pearl.o2o.manager;


import org.apache.log4j.Logger;

//import com.danga.MemCached.MemCachedClient;
//import com.danga.MemCached.SockIOPool;


public class MemCachedManager
{
/*    private static final Logger log = Logger.getLogger(MemCachedManager.class);
    
    private static MemCachedClient mcc;

    public static MemCachedClient getClient(){
    	if(mcc == null){
    		return getClientInstance();
    	}
    	
    	return mcc;
    }
    
	protected synchronized static MemCachedClient getClientInstance(){
		if(mcc == null){
			// server list and weights
			String[] servers ={
					"192.168.1.111:11211",
					"192.168.1.16:11211" 
			};
		
			Integer[] weights = { 5, 20 };
		
			// grab an instance of our connection pool
			SockIOPool pool = SockIOPool.getInstance();
		
			// set the servers and the weights
			pool.setServers( servers );
			pool.setWeights( weights );
		
			// set some basic pool settings
			// 5 initial, 5 min, and 250 max conns
			// and set the max idle time for a conn
			// to 6 hours
			pool.setInitConn( 5 );
			pool.setMinConn( 5 );
			pool.setMaxConn( 100 );
			pool.setMaxIdle( 1000 * 60 * 60 * 6 );
		
			// set the sleep for the maint thread
			// it will wake up every x seconds and
			// maintain the pool size
			pool.setMaintSleep( 30 );
		
			// set some TCP settings
			// disable nagle
			// set the read timeout to 3 secs
			// and do not set a connect timeout
			pool.setNagle( false );
			pool.setSocketTO( 3000 );
			pool.setSocketConnectTO( 0 );
			
			
			//set CONSISTENT_HASH for dynamic adding/removing servers
			pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
			pool.setFailover(true);
			pool.setFailback(true);
			pool.setAliveCheck(false);
		
			// initialize the connection pool
			pool.initialize();
	
		// lets set some compression on for the client
		// compress anything larger than 64k
		//	mcc.setCompressEnable( true );
		//	mcc.setCompressThreshold( 64 * 1024 );
			
			mcc = new MemCachedClient();
		}				
		
		return mcc;
	}*/
}
