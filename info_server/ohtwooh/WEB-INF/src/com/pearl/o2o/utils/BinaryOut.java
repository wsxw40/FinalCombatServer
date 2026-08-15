package com.pearl.o2o.utils;

import java.io.IOException;
import java.io.OutputStream;

public class BinaryOut {
	private final OutputStream outputStream;
	
	public BinaryOut(OutputStream os) {
		this.outputStream = os;
	}

	public void writeByte(int data) throws IOException{
		outputStream.write(data);
	}
	
	public void writeString(String s) throws IOException{
		writeInt(s.length());
		outputStream.write(s.getBytes("GBK"));
	}
	
	public void writeInt(int data) throws IOException{
		outputStream.write(BinaryUtil.toByta(data));
	}
	
	public void writeFloat(float f) throws IOException{
		outputStream.write(BinaryUtil.toByta(f));
	}
	
	
	public void flush() throws IOException{
		outputStream.flush();
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}
	
}
