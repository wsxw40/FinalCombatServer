/**
 * 
 */
package com.pearl.o2o;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;

import com.pearl.o2o.jmeter.protocol.tcp.sampler.CM_RequestBinaryRPCClientImpl;
import com.pearl.o2o.jmeter.protocol.tcp.sampler.CM_RequestTextRPCClientImpl;
import com.pearl.o2o.jmeter.protocol.tcp.sampler.PJRPCMessageClientImpl;

/**
 * @author lifengyang
 * 
 */
public class Test {

	/**
	 * @param args
	 * @throws IOException
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws Exception {
		// AtomicInteger counter = new AtomicInteger();
		// System.out.println(counter.incrementAndGet());
		// counter.set(Integer.MAX_VALUE);
		// System.out.println(counter.intValue());
		// counter.incrementAndGet();
		// System.out.println(counter.intValue());
//		String host = "localhost";
//		String host = "125.39.150.138";
		String host = "125.39.150.141";
//		String host = "125.39.150.159";
		int port = 9090;
		Socket client = new Socket(host, port);
		client.setSoTimeout(5000);
		InputStream is = client.getInputStream();
		OutputStream os = client.getOutputStream();
		PJRPCMessageClientImpl binaryclinetImpl = new CM_RequestBinaryRPCClientImpl();
		PJRPCMessageClientImpl textClientImpl = new CM_RequestTextRPCClientImpl();
		{
			String str = "{'url':'s_character_online','uid':40447,'userName':'we3434f42le0','ip':'121.73.109.165','version':'*','isXunleiVip':'1','internetCafe':'0'}";
			binaryclinetImpl.write(os, str);
			String read = binaryclinetImpl.read(is);
			System.out.println(read);
		}
		Thread.sleep(1000);
		{
			String name = UUID.randomUUID().toString().substring(0, 6);
			String str = "{'url':'s_player_online','uid':40447,'userName':'" + name + "','name':'" + name + "','ip':'121.73.109.165','isXunleiVip':'0','internetCafe':'1'}";
			binaryclinetImpl.write(os, str);
			String read = binaryclinetImpl.read(is);
			System.out.println(read);
		}
//		for (int i = 0; i < 10; i++) {
//			Thread.sleep(1000);
//			String str = "{ \"url\":\"c_get_growth_list\", \"paramMap\":{ \"pid\":\"40447\"} }";
//			textClientImpl.write(os, str);
//			String read = textClientImpl.read(is);
////			System.out.println(read);
//		}
	}

}
