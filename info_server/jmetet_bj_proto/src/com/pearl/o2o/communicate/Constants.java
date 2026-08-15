/**
 * 
 */
package com.pearl.o2o.communicate;

import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

/**
 * @author lifengyang
 * 
 */
public class Constants {
	public static final int MAXFRAMELENGTH = 65535;
	public static final int LENGTHFIELDOFFSET = 2;
	public static final int LENGTHFIELDLENGTH = 2;
	public static final int LENGTHADJUSTMENT = -4;
	public static final int INITIALBYTESTOSTRIP = 0;

	public static LengthFieldBasedFrameDecoder getNewLengthFieldBasedFrameDecoder() {
		return new LengthFieldBasedFrameDecoder(MAXFRAMELENGTH, LENGTHFIELDOFFSET, LENGTHFIELDLENGTH, LENGTHADJUSTMENT, INITIALBYTESTOSTRIP);
	}

}
