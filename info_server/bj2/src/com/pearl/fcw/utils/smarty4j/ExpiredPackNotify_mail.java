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

public class ExpiredPackNotify_mail implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("<C447^1^ ");
sb.append("\r\n");
List list402 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list402=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list402=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list402.forEach(item->{
try{
if((O2oUtil.compare(O2oUtil.getSmarty4jProperty(item,"durable"),"<=",0))){
sb.append("		<C445^1^");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("> ");
sb.append("\r\n");
} else {
sb.append("		<C446^1^");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("> ");
sb.append("\r\n");
}
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("> ");
sb.append("\r\n");
return sb.toString();
}

}