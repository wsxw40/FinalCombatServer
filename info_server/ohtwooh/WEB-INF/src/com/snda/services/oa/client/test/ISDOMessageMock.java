package com.snda.services.oa.client.test;

import java.util.HashMap;
import java.util.Map;

import com.snda.services.oa.client.impl.ISDOMessage;

public class ISDOMessageMock extends ISDOMessage {
	private Map<String,String> innerMap = new HashMap<String,String>();
	
	@Override
	public String getValue(String key) {
		String value =  innerMap.get(key);
		if (value == null) {
			return "0";
		}else{
			return value;
		}
	}

	@Override
	public void setValue(String key, String value) {
		innerMap.put(key, value);
	}
	
}
