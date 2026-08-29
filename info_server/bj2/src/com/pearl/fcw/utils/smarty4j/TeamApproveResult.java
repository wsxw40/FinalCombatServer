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

public class TeamApproveResult implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("success={  ");
sb.append("\r\n");
List successNameList173 = new ArrayList<>();
if (null!=context.get("successNameList")){
if (context.get("successNameList") instanceof List) successNameList173=(List<?>)context.get("successNameList");
else if (context.get("successNameList").getClass().isArray()) successNameList173=Stream.of((Object[])context.get("successNameList")).collect(Collectors.toList());
}
successNameList173.forEach(name->{
try{
sb.append("		\"");
sb.append(name);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("fail={ ");
sb.append("\r\n");
sb.append("	size={ ");
sb.append("\r\n");
List szieNameList802 = new ArrayList<>();
if (null!=context.get("szieNameList")){
if (context.get("szieNameList") instanceof List) szieNameList802=(List<?>)context.get("szieNameList");
else if (context.get("szieNameList").getClass().isArray()) szieNameList802=Stream.of((Object[])context.get("szieNameList")).collect(Collectors.toList());
}
szieNameList802.forEach(name->{
try{
sb.append("			\"");
sb.append(name);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	expire={ ");
sb.append("\r\n");
List expireNameList785 = new ArrayList<>();
if (null!=context.get("expireNameList")){
if (context.get("expireNameList") instanceof List) expireNameList785=(List<?>)context.get("expireNameList");
else if (context.get("expireNameList").getClass().isArray()) expireNameList785=Stream.of((Object[])context.get("expireNameList")).collect(Collectors.toList());
}
expireNameList785.forEach(name->{
try{
sb.append("			\"");
sb.append(name);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}