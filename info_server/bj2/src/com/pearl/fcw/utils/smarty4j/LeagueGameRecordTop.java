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

public class LeagueGameRecordTop implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("myTeamIndex=");
sb.append(context.get("myTeamIndex"));
sb.append(" ");
sb.append("\r\n");
sb.append("top =  ");
sb.append("{");
sb.append("\r\n");
List gameRecords447 = new ArrayList<>();
if (null!=context.get("gameRecords")){
if (context.get("gameRecords") instanceof List) gameRecords447=(List<?>)context.get("gameRecords");
else if (context.get("gameRecords").getClass().isArray()) gameRecords447=Stream.of((Object[])context.get("gameRecords")).collect(Collectors.toList());
}
gameRecords447.forEach(gameRecord->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("	teamBname=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(gameRecord,"teambName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	myTeamScoce=");
sb.append(O2oUtil.getSmarty4jPropertyNil(gameRecord,"myTeamScoce"));
sb.append(", ");
sb.append("\r\n");
sb.append("	teambScoce=");
sb.append(O2oUtil.getSmarty4jPropertyNil(gameRecord,"teambScoce"));
sb.append(", ");
sb.append("\r\n");
sb.append("	{");
sb.append("\r\n");
List gameRecordlist788 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(gameRecord,"list")){
if (O2oUtil.getSmarty4jProperty(gameRecord,"list") instanceof List) gameRecordlist788=(List<?>)O2oUtil.getSmarty4jProperty(gameRecord,"list");
else if (O2oUtil.getSmarty4jProperty(gameRecord,"list").getClass().isArray()) gameRecordlist788=Stream.of((Object[])O2oUtil.getSmarty4jProperty(gameRecord,"list")).collect(Collectors.toList());
}
gameRecordlist788.forEach(entry->{
try{
sb.append("		{");
sb.append(entry);
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}