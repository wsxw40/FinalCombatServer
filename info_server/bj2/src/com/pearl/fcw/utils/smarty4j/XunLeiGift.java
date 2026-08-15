package com.pearl.fcw.utils.smarty4j;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.Smarty4jUtil.Ctx;

public class XunLeiGift implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("{ ");
sb.append("\r\n");
sb.append("	cmd = \"xunlei_gift\", ");
sb.append("\r\n");
sb.append("	num = ");
sb.append(context.get("num"));
sb.append(", ");
sb.append("\r\n");
sb.append("	items = { ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("list"),"!=",null))){
List list532 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list532=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list532=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list532.forEach(sysItem->{
try{
sb.append("				{0 , \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"type"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"gunProperty.color"));
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}