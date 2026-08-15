package com.pearl.fcw.utils.smarty4j2;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.Smarty4jUtil2.Ctx;

public class CCharacterGet implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("enable = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"enable"));
sb.append(" ");
sb.append("\r\n");
sb.append("player={ ");
sb.append("\r\n");
sb.append("	name= \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	matchRank=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"matchRank"));
sb.append(", ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("fightnum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"fightnum"));
sb.append(" ");
sb.append("\r\n");
sb.append("items=	{ ");
sb.append("\r\n");
sb.append("	resourcename=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"items.resourcename"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"items.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	avatar={ ");
sb.append("\r\n");
List protoitemsavatarList202 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"items.avatarList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"items.avatarList") instanceof List) protoitemsavatarList202=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"items.avatarList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"items.avatarList").getClass().isArray()) protoitemsavatarList202=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"items.avatarList")).collect(Collectors.toList());
}
protoitemsavatarList202.forEach(res->{
try{
if( (O2oUtil.compare(res,"!=",null))){
sb.append("				\"");
sb.append(res);
sb.append("\", ");
sb.append("\r\n");
}
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("	putSuitId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"items.putSuitId"));
sb.append(", ");
sb.append("\r\n");
sb.append("	suitCount=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"items.suitCount"));
sb.append(", ");
sb.append("\r\n");
sb.append("	weapons = { ");
sb.append("\r\n");
List protoitemsweaponsList622 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList") instanceof List) protoitemsweaponsList622=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList").getClass().isArray()) protoitemsweaponsList622=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList")).collect(Collectors.toList());
}
protoitemsweaponsList622.forEach(playerItem->{
try{
sb.append("			{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar8338=new HashMap<>();
includeContextVar8338.put("playerItem",playerItem);
sb.append(new PlayerItem().get(includeContextVar8338));
sb.append("			}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	costume = { ");
sb.append("\r\n");
List protoitemsweaponsList948 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList") instanceof List) protoitemsweaponsList948=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList").getClass().isArray()) protoitemsweaponsList948=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"items.weaponsList")).collect(Collectors.toList());
}
protoitemsweaponsList948.forEach(playerItem->{
try{
sb.append("			{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar7388=new HashMap<>();
includeContextVar7388.put("playerItem",playerItem);
sb.append(new PlayerItem().get(includeContextVar7388));
sb.append("			}, ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}