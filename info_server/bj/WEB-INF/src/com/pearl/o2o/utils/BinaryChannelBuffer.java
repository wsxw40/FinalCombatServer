package com.pearl.o2o.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

public class BinaryChannelBuffer implements BinaryReader {
		private ChannelBuffer buffer;
		
		public BinaryChannelBuffer(ChannelBuffer buffer) {
			this.buffer = buffer;
		}
		
		public BinaryChannelBuffer(int capacity){
			this.buffer = ChannelBuffers.buffer(capacity);
		}
		
		public BinaryChannelBuffer(byte[] array){
			this.buffer = ChannelBuffers.copiedBuffer(array);
		}
		
		
		public boolean readable(){
			return buffer.readable();
		}
		
		public int readableBytes(){
			return buffer.readableBytes();
		}		

		/* (non-Javadoc)
		 * @see com.pearl.o2o.utils.BinaryReader#readByte()
		 */
		@Override
		public byte readByte() throws IOException{
			return buffer.readByte();
		}
		public void readBytes(byte[] dst) throws IOException{
			buffer.readBytes(dst);
		}
		
		public byte[] array(){
			return buffer.array();
		}

		/* (non-Javadoc)
		 * @see com.pearl.o2o.utils.BinaryReader#readShort()
		 */
		@Override
		public short readShort() throws IOException{
			byte[] array = new byte[2];
			buffer.readBytes(array);
			return BinaryUtil.toShort(array);
		}
		
		/* (non-Javadoc)
		 * @see com.pearl.o2o.utils.BinaryReader#readChar()
		 */
		@Override
		public char readChar() throws IOException{
			byte[] array = new byte[2];
			buffer.readBytes(array);			
			return BinaryUtil.toChar(array);
		}		
		
		/* (non-Javadoc)
		 * @see com.pearl.o2o.utils.BinaryReader#readInt()
		 */
		@Override
		public int readInt() throws IOException{
			byte[] array = new byte[4];
			buffer.readBytes(array);			
			return BinaryUtil.toInt(array);
		}				
		
		public String readString() throws IOException{
			byte[] strBytes = new byte[readInt()];
			buffer.readBytes(strBytes);

			try {
				return new String(strBytes,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		}		
		
		@Override
		public float readFloat() throws IOException {
			byte[] array = new byte[4];
			buffer.readBytes(array);
			return BinaryUtil.toFloat(array);
		}	
		
		
		public void writeByte(byte data){
			buffer.writeByte(data);
		}		
		
		public void writeBytes(byte[] data){
			buffer.writeBytes(data);
		}
		
		public void writeInt(int data){
			buffer.writeBytes(BinaryUtil.toByta(data));
		}

		public void writeString(String data){
			if (StringUtil.isEmptyString(data)) {
				writeInt(0);
			}else{
				buffer.writeBytes(BinaryUtil.toByta(data));
			}
		}
		
		public BinaryChannelBuffer clone(){
			return new BinaryChannelBuffer(this.buffer.copy());
		}
			
	}