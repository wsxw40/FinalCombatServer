package com.pearl.o2o.socket;

import java.util.concurrent.LinkedBlockingQueue;


public class ReturnConsumer implements Runnable {
	private DefaultSocketHandler handler;
	private LinkedBlockingQueue<byte[]> responseQueue;
	
	public ReturnConsumer(DefaultSocketHandler handler, LinkedBlockingQueue<byte[]> responseQueue){
		this.handler = handler;
		this.responseQueue = responseQueue;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				byte[] responseArray = responseQueue.take();
				if(responseArray.length>0){
					handler.push(responseArray);
				}
			}
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
