package com.pearl.manager.utils;

import java.util.Random;

import net.rubyeye.xmemcached.GetsResponse;
import net.rubyeye.xmemcached.MemcachedClient;

public class MemcachedLockImpl implements InfoServerLock {
	 private static final String WRITE_LOCKED = "1";
	    private static final String UNLOCKED = "0";

	    private static final int KEY_EXPR = 60 * 10; //不能保证解锁百分百成功，但是如果设置了超时，能保证在一定时间后，该锁在内存中失效
	    private static final Random random = new Random(47);
	    private static final int SPIT_INTERVAL_MILLIS = 3; //自旋的间隔,每次争锁失败会睡眠此间隔指定时间,此参数若太大会影响性能
	    private static final int SPIT_INTERVAL_RANDOM_NANO = SPIT_INTERVAL_MILLIS * 1000/3;//自旋的随机间隔微秒，防止活锁
	    private static final int UNLOCK_RETRY = 3;//解锁的尝试次数

	    private MemcachedClient mcc=ServiceLocator.mcc;

	    private boolean tryLock(String key, long timeoutMillis, String lockType) {
	        final long start = System.currentTimeMillis();

	        GetsResponse<Integer> response = null;
	        boolean isLockSuccess = false;

	        while( !isLockSuccess && System.currentTimeMillis() - start < timeoutMillis ){//锁没争取成功，且用时比较低
	            try{
	                response = mcc.gets(key);
	                if (null == response) {
	                    mcc.add(key, KEY_EXPR, UNLOCKED);
	                    response = mcc.gets(key);
	                }


	                if ( UNLOCKED.equals(response.getValue()) ) {
	                    isLockSuccess = mcc.cas(key, KEY_EXPR, lockType, response.getCas());
	                    if (isLockSuccess){
	                        return true;
	                    }
	                }

	                //随机休眠
	                Thread.sleep(SPIT_INTERVAL_MILLIS, random.nextInt(SPIT_INTERVAL_RANDOM_NANO));
	            }catch(Exception e){
	                //just catch
	                e.printStackTrace();
	            }
	        }

	        return isLockSuccess;
	    }
	    
	    private boolean tryLockWithNoDelay(String key,String lockType){
		        GetsResponse<Integer> response = null;
		        boolean isLockSuccess = false;

		            try{
		                response = mcc.gets(key);
		                if (null == response) {
		                    mcc.add(key, KEY_EXPR, UNLOCKED);
		                    response = mcc.gets(key);
		                }


		                if ( UNLOCKED.equals(response.getValue()) ) {
		                    isLockSuccess = mcc.cas(key, KEY_EXPR, lockType, response.getCas());
		                    if (isLockSuccess){
		                        return true;
		                    }
		                }

		            }catch(Exception e){
		                //just catch
		                e.printStackTrace();
		            }

		        return isLockSuccess;
	    }


	    /**
	     * 对于解锁前，锁的状态就可能3种之一（locked,unlocked,null），最终结果希望是解锁状态
	     *
	     * 所以这里不用考虑同步问题，直接 将状态改为unlocked
	     */
	    @Override
	    public void unlock(String key) {
	        int retryTimes = -1;
	        while (retryTimes < UNLOCK_RETRY ) {
	            try{
	                mcc.set(key, KEY_EXPR, UNLOCKED);
	                return ;
	            }catch (Exception e){
	                e.printStackTrace();
	            }
	            //运行到这里说明出现异常
	            retryTimes ++ ;
	            //随机休眠
	            try{
	                Thread.sleep(SPIT_INTERVAL_MILLIS, random.nextInt(SPIT_INTERVAL_RANDOM_NANO));
	            }catch(Exception e){
	                //just catch ,do nothing
	            }
	        }
	    }


	    @Override
	    public boolean tryLock(String key, long timeoutMillis) {
	        return tryLock(key, timeoutMillis, WRITE_LOCKED);
	    }
	    public boolean tryLockWithNoDelay(String key){
	    	return tryLockWithNoDelay(key, WRITE_LOCKED);
	    }
	    public void setMcc(MemcachedClient mcc) {
	        this.mcc = mcc;
	    }
}
