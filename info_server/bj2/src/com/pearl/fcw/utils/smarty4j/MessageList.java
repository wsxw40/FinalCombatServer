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

public class MessageList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list = { ");
sb.append("\r\n");
List messageList796 = new ArrayList<>();
if (null!=context.get("messageList")){
if (context.get("messageList") instanceof List) messageList796=(List<?>)context.get("messageList");
else if (context.get("messageList").getClass().isArray()) messageList796=Stream.of((Object[])context.get("messageList")).collect(Collectors.toList());
}
messageList796.forEach(message->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(message,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(message,"subject"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(message,"createdDateStr"));
sb.append("\", ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("type"),"==",0)) ){
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(message,"sender"));
sb.append("\", ");
sb.append("\r\n");
} else {
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(message,"receiver"));
sb.append("\", ");
sb.append("\r\n");
}
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(message,"isAttached"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(message,"open"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(message,"haveAttached"));
sb.append("\" ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}