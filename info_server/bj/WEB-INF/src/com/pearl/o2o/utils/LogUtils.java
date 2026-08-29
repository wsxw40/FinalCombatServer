/**
 * 
 */
package com.pearl.o2o.utils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * @author lifengyang
 *
 */
public class LogUtils {
	public static final Joiner JoinerByUnderScore = Joiner.on('_');
	public static final Joiner JoinerByColon = Joiner.on(':');
	public static final Joiner JoinerByTab = Joiner.on('\t');
	public static final Joiner JoinerByVertical = Joiner.on('|');
	public static final Splitter splitterByUnderScore = Splitter.on('_').trimResults();
	public static final Splitter splitterByColon = Splitter.on(':').trimResults();
	public static final Splitter splitterByTab = Splitter.on('\t').trimResults();
}
