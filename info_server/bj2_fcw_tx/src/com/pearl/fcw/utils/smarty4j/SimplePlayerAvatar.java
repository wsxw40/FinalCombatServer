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

public class SimplePlayerAvatar implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("avatar_info ={ ");
sb.append("\r\n");
sb.append("	\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"name"));
sb.append("\",	 ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"lastGameSide"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"gender"));
sb.append("\",	 ");
sb.append("\r\n");
sb.append("	{ ");
sb.append("\r\n");
List playercostumeT425 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")){
if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT") instanceof List) playercostumeT425=(List<?>)O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT");
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT") instanceof int[]) playercostumeT425=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT") instanceof long[]) playercostumeT425=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT") instanceof float[]) playercostumeT425=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT") instanceof double[]) playercostumeT425=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT") instanceof byte[]) playercostumeT425=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT") instanceof String[]) playercostumeT425=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT").getClass().isArray()) playercostumeT425=Stream.of(O2oUtil.getSmarty4jProperty(context.get("player"),"costumeT")).collect(Collectors.toList());
}
playercostumeT425.forEach(itemT->{
try{
if((O2oUtil.compare(itemT,"!=",""))){
sb.append("				\"");
sb.append(itemT);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	{ ");
sb.append("\r\n");
List playercostumeP245 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")){
if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP") instanceof List) playercostumeP245=(List<?>)O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP");
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP") instanceof int[]) playercostumeP245=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP") instanceof long[]) playercostumeP245=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP") instanceof float[]) playercostumeP245=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP") instanceof double[]) playercostumeP245=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP") instanceof byte[]) playercostumeP245=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP") instanceof String[]) playercostumeP245=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP").getClass().isArray()) playercostumeP245=Stream.of(O2oUtil.getSmarty4jProperty(context.get("player"),"costumeP")).collect(Collectors.toList());
}
playercostumeP245.forEach(itemP->{
try{
if((O2oUtil.compare(itemP,"!=",""))){
sb.append("				\"");
sb.append(itemP);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	{ ");
sb.append("\r\n");
sb.append("		type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"pi.wId"));
sb.append(",  ");
sb.append("\r\n");
List playerpiresource599 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource") instanceof List) playerpiresource599=(List<?>)O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource") instanceof int[]) playerpiresource599=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource") instanceof long[]) playerpiresource599=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource") instanceof float[]) playerpiresource599=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource") instanceof double[]) playerpiresource599=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource") instanceof byte[]) playerpiresource599=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource") instanceof String[]) playerpiresource599=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource").getClass().isArray()) playerpiresource599=Stream.of(O2oUtil.getSmarty4jProperty(context.get("player"),"pi.resource")).collect(Collectors.toList());
}
playerpiresource599.forEach(itemS->{
try{
if((O2oUtil.compare(itemS,"!=",""))){
sb.append("				\"");
sb.append(itemS);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	},	 ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}