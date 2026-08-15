package com.pearl.o2o.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class Receiver {
		private InputStream is;
		byte[] buffer = new byte[64];
		
		public Receiver(InputStream is, byte[] buffer) {
			this.is = is;
			this.buffer = buffer;
		}

		public int readInt() throws IOException{
			is.read(buffer, 0, 4);
			return BinaryUtil.toInt(buffer);
		}
		
		public byte readByte() throws IOException{
			return (byte) is.read();
		}
		
		public short readShort() throws IOException{
			is.read(buffer, 0, 2);
			return BinaryUtil.toShort(buffer);
		}
		
		public String readString() throws IOException{
			int length = readInt();
			byte[] strBytes = new byte[length];
			is.read(strBytes);
			try {
				return new String(strBytes,"GBK");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		}
	}