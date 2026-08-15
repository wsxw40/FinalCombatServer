package com.pearl.o2o.socket;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task<T> {
	// wait time out
	private static int timeout = 2000;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private T result;
	
	
	public T await(int timeout) throws TimeoutException{
		lock.lock();
		try{
			// check if get result before starting wait
			if (result != null){
				return result;
			}
			boolean waitResult = condition.await(timeout,TimeUnit.MILLISECONDS);
			if (!waitResult && result == null) {// time out
				throw new TimeoutException();
			}
			return result;
		} catch (InterruptedException e) {
			return result;
		}finally{
			lock.unlock();
		}
	}
	
	public T await() throws TimeoutException{
		return await(timeout);
	}
	
	public void singnal(T result){
		lock.lock();
		try{
			this.result = result;
			condition.signal();
		}finally{
			lock.unlock();
		}
	}
	
	public T getResult() {
		return result;
	}


}
