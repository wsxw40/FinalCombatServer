package com.pearl.fcw.utils;

import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Smarty4jUtil {
private static Logger logger = LoggerFactory.getLogger(Smarty4jUtil.class);

public static String get(String template, Context context) throws Exception{
logger.info(template);
try {
String className = template.substring(0, template.indexOf("."));
Class<?> clazz = Class.forName("com.pearl.fcw.utils.smarty4j." + className);
String str = ((Ctx)clazz.newInstance()).get(context);
return str;
} catch (Exception e) {
throw e;
}
}
public  interface Ctx{
String get(Context context) throws Exception;
}

}