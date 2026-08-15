/**
 * 
 */
package com.pearl.o2o;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lifengyang
 * 
 */
public class MockPorxyServer {
	public static void main(String[] args) {
		final ApplicationContext context = new ClassPathXmlApplicationContext("mock-proxyserver.xml");
		context.hashCode();
	}
}
