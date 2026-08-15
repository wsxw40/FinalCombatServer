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

public class PlayerInfoEdit implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
if( (O2oUtil.compare(context.get("type"),"==",1))){
sb.append("	head={ ");
sb.append("\r\n");
sb.append("		totalPage = ");
sb.append(context.get("totalPage"));
sb.append(", ");
sb.append("\r\n");
sb.append("		current=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		current1=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"achievement"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		current2=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		data_list={ ");
sb.append("\r\n");
List iconList55 = new ArrayList<>();
if (null!=context.get("iconList")){
if (context.get("iconList") instanceof List) iconList55=(List<?>)context.get("iconList");
else if (context.get("iconList").getClass().isArray()) iconList55=Stream.of((Object[])context.get("iconList")).collect(Collectors.toList());
}
iconList55.forEach(icon->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(icon,"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(icon,"level"));
sb.append(", ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("	} ");
sb.append("\r\n");
}
if((O2oUtil.compare(context.get("type"),"==",2))){
sb.append("	title={ ");
sb.append("\r\n");
sb.append("		totalPage = ");
sb.append(context.get("totalPage"));
sb.append(", ");
sb.append("\r\n");
sb.append("		current=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		data_list={ ");
sb.append("\r\n");
List achievementList868 = new ArrayList<>();
if (null!=context.get("achievementList")){
if (context.get("achievementList") instanceof List) achievementList868=(List<?>)context.get("achievementList");
else if (context.get("achievementList").getClass().isArray()) achievementList868=Stream.of((Object[])context.get("achievementList")).collect(Collectors.toList());
}
achievementList868.forEach(t->{
try{
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(t,"achievement.title"),"!=",null) && O2oUtil.compare(O2oUtil.getSmarty4jProperty(t,"achievement.title"),"!=",""))){
sb.append("				{ ");
sb.append("\r\n");
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(t,"achievement.title"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(t,"status"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
}
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("	} ");
sb.append("\r\n");
}
if((O2oUtil.compare(context.get("type"),"==",3))){
sb.append("	achievement={ ");
sb.append("\r\n");
sb.append("		totalPage = ");
sb.append(context.get("totalPage"));
sb.append(", ");
sb.append("\r\n");
sb.append("		current=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"achievement"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		general_list={ ");
sb.append("\r\n");
List achievementList674 = new ArrayList<>();
if (null!=context.get("achievementList")){
if (context.get("achievementList") instanceof List) achievementList674=(List<?>)context.get("achievementList");
else if (context.get("achievementList").getClass().isArray()) achievementList674=Stream.of((Object[])context.get("achievementList")).collect(Collectors.toList());
}
achievementList674.forEach(a->{
try{
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(a,"achievement.type"),"==",1)){
sb.append("				{");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"status"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.name"));
sb.append("\",},  ");
sb.append("\r\n");
}
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("	} ");
sb.append("\r\n");
}
if((O2oUtil.compare(context.get("type"),"==",4))){
sb.append("	achievement={ ");
sb.append("\r\n");
sb.append("		totalPage = ");
sb.append(context.get("totalPage"));
sb.append(", ");
sb.append("\r\n");
sb.append("		current=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"achievement"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		character_list={ ");
sb.append("\r\n");
List achievementList585 = new ArrayList<>();
if (null!=context.get("achievementList")){
if (context.get("achievementList") instanceof List) achievementList585=(List<?>)context.get("achievementList");
else if (context.get("achievementList").getClass().isArray()) achievementList585=Stream.of((Object[])context.get("achievementList")).collect(Collectors.toList());
}
achievementList585.forEach(a->{
try{
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(a,"achievement.type"),"==",3)){
sb.append("				{");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"status"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(a,"achievement.name"));
sb.append("\",},  ");
sb.append("\r\n");
}
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("	} ");
sb.append("\r\n");
}
return sb.toString();
}

}