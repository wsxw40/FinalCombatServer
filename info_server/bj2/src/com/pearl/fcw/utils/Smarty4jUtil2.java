package com.pearl.fcw.utils;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Smarty4jUtil2 {
private static Logger logger = LoggerFactory.getLogger(Smarty4jUtil2.class);

public static <T> String get(String template, Map<String, T> context) throws Exception{
logger.info(template);
try {
String className = template.substring(0, template.indexOf("."));
Class<?> clazz = Class.forName("com.pearl.fcw.utils.smarty4j2." + className);
String str = ((Ctx)clazz.newInstance()).get(context);
return str;
} catch (Exception e) {
throw e;
}
}
public  interface Ctx{
<T> String get(Map<String, T> context) throws Exception;
}

}