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

public class PlayerInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("info =  ");
sb.append("{ ");
sb.append("\r\n");
sb.append("	mateRank=");
sb.append(context.get("mateRank"));
sb.append(", ");
sb.append("\r\n");
sb.append("	nickName = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	rank	 = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("	exp	 = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	isVip =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	vipExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	leftMinites=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"leftMinites"));
sb.append(", ");
sb.append("\r\n");
sb.append("	maxFightNum =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"maxFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("	internetCafe=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("	title =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	headIcon =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	winRate=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"winRate"));
sb.append(", ");
sb.append("\r\n");
sb.append("	runAwayRate=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"runAwayRate"));
sb.append(", ");
sb.append("\r\n");
sb.append("	top=");
sb.append(context.get("top"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gWin=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"gWin"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gLose=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"gLose"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gold=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"gold"));
sb.append(", ");
sb.append("\r\n");
sb.append("	silver=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"silver"));
sb.append(", ");
sb.append("\r\n");
sb.append("	bronze=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"bronze"));
sb.append(", ");
sb.append("\r\n");
sb.append("	total=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"total"));
sb.append(", ");
sb.append("\r\n");
sb.append("	achievement=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"achievement"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	buff_list = ");
sb.append("	{ ");
sb.append("\r\n");
List buffList673 = new ArrayList<>();
if (null!=context.get("buffList")){
if (context.get("buffList") instanceof List) buffList673=(List<?>)context.get("buffList");
else if (context.get("buffList").getClass().isArray()) buffList673=Stream.of((Object[])context.get("buffList")).collect(Collectors.toList());
}
buffList673.forEach(buff->{
try{
sb.append("			{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"timeLeft"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.unitType"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("newSimple={ ");
sb.append("\r\n");
List news608 = new ArrayList<>();
if (null!=context.get("news")){
if (context.get("news") instanceof List) news608=(List<?>)context.get("news");
else if (context.get("news").getClass().isArray()) news608=Stream.of((Object[])context.get("news")).collect(Collectors.toList());
}
news608.forEach(event->{
try{
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(event,"nosqlPlainStr"));
sb.append(" ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}