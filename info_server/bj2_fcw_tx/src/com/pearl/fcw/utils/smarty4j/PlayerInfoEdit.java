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
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		current1=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"achievement"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		current2=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		data_list={ ");
sb.append("\r\n");
List iconList829 = new ArrayList<>();
if (null!=context.get("iconList")){
if (context.get("iconList") instanceof List) iconList829=(List<?>)context.get("iconList");
else if (context.get("iconList") instanceof int[]) iconList829=Stream.of((int[])context.get("iconList")).collect(Collectors.toList());
else if (context.get("iconList") instanceof long[]) iconList829=Stream.of((long[])context.get("iconList")).collect(Collectors.toList());
else if (context.get("iconList") instanceof float[]) iconList829=Stream.of((float[])context.get("iconList")).collect(Collectors.toList());
else if (context.get("iconList") instanceof double[]) iconList829=Stream.of((double[])context.get("iconList")).collect(Collectors.toList());
else if (context.get("iconList") instanceof byte[]) iconList829=Stream.of((byte[])context.get("iconList")).collect(Collectors.toList());
else if (context.get("iconList") instanceof String[]) iconList829=Stream.of((String[])context.get("iconList")).collect(Collectors.toList());
else if (context.get("iconList").getClass().isArray()) iconList829=Stream.of(context.get("iconList")).collect(Collectors.toList());
}
iconList829.forEach(icon->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(icon,"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(icon,"level"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		data_list={ ");
sb.append("\r\n");
List achievementList832 = new ArrayList<>();
if (null!=context.get("achievementList")){
if (context.get("achievementList") instanceof List) achievementList832=(List<?>)context.get("achievementList");
else if (context.get("achievementList") instanceof int[]) achievementList832=Stream.of((int[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof long[]) achievementList832=Stream.of((long[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof float[]) achievementList832=Stream.of((float[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof double[]) achievementList832=Stream.of((double[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof byte[]) achievementList832=Stream.of((byte[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof String[]) achievementList832=Stream.of((String[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList").getClass().isArray()) achievementList832=Stream.of(context.get("achievementList")).collect(Collectors.toList());
}
achievementList832.forEach(t->{
try{
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(t,"achievement.title"),"!=",null) && O2oUtil.compare(O2oUtil.getSmarty4jProperty(t,"achievement.title"),"!=",""))){
sb.append("				{ ");
sb.append("\r\n");
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(t,"achievement.title"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(t,"status"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"achievement"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		general_list={ ");
sb.append("\r\n");
List achievementList613 = new ArrayList<>();
if (null!=context.get("achievementList")){
if (context.get("achievementList") instanceof List) achievementList613=(List<?>)context.get("achievementList");
else if (context.get("achievementList") instanceof int[]) achievementList613=Stream.of((int[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof long[]) achievementList613=Stream.of((long[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof float[]) achievementList613=Stream.of((float[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof double[]) achievementList613=Stream.of((double[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof byte[]) achievementList613=Stream.of((byte[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof String[]) achievementList613=Stream.of((String[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList").getClass().isArray()) achievementList613=Stream.of(context.get("achievementList")).collect(Collectors.toList());
}
achievementList613.forEach(a->{
try{
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(a,"achievement.type"),"==",1)){
sb.append("				{");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(a,"status"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.name"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"achievement"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		character_list={ ");
sb.append("\r\n");
List achievementList626 = new ArrayList<>();
if (null!=context.get("achievementList")){
if (context.get("achievementList") instanceof List) achievementList626=(List<?>)context.get("achievementList");
else if (context.get("achievementList") instanceof int[]) achievementList626=Stream.of((int[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof long[]) achievementList626=Stream.of((long[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof float[]) achievementList626=Stream.of((float[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof double[]) achievementList626=Stream.of((double[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof byte[]) achievementList626=Stream.of((byte[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList") instanceof String[]) achievementList626=Stream.of((String[])context.get("achievementList")).collect(Collectors.toList());
else if (context.get("achievementList").getClass().isArray()) achievementList626=Stream.of(context.get("achievementList")).collect(Collectors.toList());
}
achievementList626.forEach(a->{
try{
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(a,"achievement.type"),"==",3)){
sb.append("				{");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(a,"status"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(a,"achievement.name"));
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