package com.pearl.fcw.info.core.network;

import java.util.Map;

import com.pearl.fcw.info.core.persistence.utils.BinaryChannelBuffer;

//@FunctionalInterface
public interface Executable {
	byte[] execute(Map<String, String> params) throws Exception;

	byte[] execute(BinaryChannelBuffer params) throws Exception;
}
