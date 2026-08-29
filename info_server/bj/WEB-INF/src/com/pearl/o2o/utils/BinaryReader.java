package com.pearl.o2o.utils;

import java.io.IOException;

public interface BinaryReader {

	public  byte readByte() throws IOException;


	public  short readShort() throws IOException;

	public  char readChar() throws IOException;

	public  int readInt() throws IOException;
	public String readString() throws IOException;
	
	public float readFloat() throws IOException;
}