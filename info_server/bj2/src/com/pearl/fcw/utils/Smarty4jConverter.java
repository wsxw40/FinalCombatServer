package com.pearl.fcw.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 将proto数据或者其他内容转为lua脚本格式的字符串
 */
public class Smarty4jConverter {
    private static Logger logger = LoggerFactory.getLogger(Smarty4jConverter.class);

    /**
	 * 错误信息
	 * @param msg
	 * @return
	 * @throws Exception
	 */
    public static String error(String msg) {
        Map<String, Object> map = new HashMap<>();
        map.put("msg", msg);
        try {
            return Smarty4jUtil2.get("Error.st", map);
        } catch (Exception e) {
            logger.error("", e);
        }
        return "";
    }

	/**
	 * 将proto数据转为lua字符串。注意proto的类名必须是ResponseXXX，lua脚本文件名对应为CXXX.st
	 * @param proto
	 * @return
	 * @throws Exception
	 */
	public static String proto2Lua(com.google.protobuf.GeneratedMessage proto) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("proto", proto);
		return Smarty4jUtil2.get(proto.getClass().getSimpleName().replaceFirst("Response", "C") + ".st", map);
	}
}
