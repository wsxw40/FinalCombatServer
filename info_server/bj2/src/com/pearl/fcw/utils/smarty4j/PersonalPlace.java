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

public class PersonalPlace implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("teamResourceWar={ ");
sb.append("\r\n");
sb.append("	fc=");
sb.append(context.get("fc"));
sb.append(", ");
sb.append("\r\n");
sb.append("	resoucesNum=");
sb.append(context.get("resoucesNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("	teamStorage={ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("storageType"),"==",3)){
sb.append("			tank={ ");
sb.append("\r\n");
sb.append("				curPage=");
sb.append(context.get("curPage"));
sb.append(", ");
sb.append("\r\n");
sb.append("				pages=");
sb.append(context.get("pages"));
sb.append(", ");
sb.append("\r\n");
List itemList951 = new ArrayList<>();
if (null!=context.get("itemList")){
if (context.get("itemList") instanceof List) itemList951=(List<?>)context.get("itemList");
else if (context.get("itemList").getClass().isArray()) itemList951=Stream.of((Object[])context.get("itemList")).collect(Collectors.toList());
}
itemList951.forEach(playerItem->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					level=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("					playerItemId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					sysItemId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					display = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					name = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					description = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					value = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"quantityForZYZDZPersonal"));
sb.append(", ");
sb.append("\r\n");
sb.append("					buycd=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"buyCD"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					durable = 100, ");
sb.append("\r\n");
sb.append("					bullet = 100,	    				 ");
sb.append("\r\n");
sb.append("	    				resDisPrice={ ");
sb.append("\r\n");
sb.append("						id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.resDisPricesList[0].id"));
sb.append(", ");
sb.append("\r\n");
sb.append("						unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.resDisPricesList[0].unitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.resDisPricesList[0].cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("						unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.resDisPricesList[0].unit"));
sb.append(" ");
sb.append("\r\n");
sb.append("					},							 ");
sb.append("\r\n");
sb.append("	    				resDisPrices={ ");
sb.append("\r\n");
List playerItemsysItemresDisPricesList266 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")){
if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList") instanceof List) playerItemsysItemresDisPricesList266=(List<?>)O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList");
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList").getClass().isArray()) playerItemsysItemresDisPricesList266=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")).collect(Collectors.toList());
}
playerItemsysItemresDisPricesList266.forEach(pay->{
try{
sb.append("	    					{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					performance={ ");
sb.append("\r\n");
sb.append("						power={ ");
sb.append("\r\n");
sb.append("							property = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property1.property"));
sb.append(", ");
sb.append("\r\n");
sb.append("							level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property1.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("							value = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property1.value"));
sb.append(", ");
sb.append("\r\n");
sb.append("							curExp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property1.curExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("							maxExp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property1.maxExp"));
sb.append(",								 ");
sb.append("\r\n");
sb.append("						}, ");
sb.append("\r\n");
sb.append("						fireSpeed={ ");
sb.append("\r\n");
sb.append("							property = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property2.property"));
sb.append(", ");
sb.append("\r\n");
sb.append("							level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property2.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("							value = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property2.value"));
sb.append(", ");
sb.append("\r\n");
sb.append("							curExp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property2.curExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("							maxExp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property2.maxExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("						}, ");
sb.append("\r\n");
sb.append("						hp={ ");
sb.append("\r\n");
sb.append("							property = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property3.property"));
sb.append(", ");
sb.append("\r\n");
sb.append("							level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property3.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("							value = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property3.value"));
sb.append(", ");
sb.append("\r\n");
sb.append("							curExp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property3.curExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("							maxExp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property3.maxExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("						}, ");
sb.append("\r\n");
sb.append("						moveSpeed={ ");
sb.append("\r\n");
sb.append("							property = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property6.property"));
sb.append(", ");
sb.append("\r\n");
sb.append("							level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property6.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("							value = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property6.value"));
sb.append(", ");
sb.append("\r\n");
sb.append("							curExp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property6.curExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("							maxExp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"property6.maxExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("						}, ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("			},	 ");
sb.append("\r\n");
} else {
sb.append("			buff={ ");
sb.append("\r\n");
sb.append("				curPage=");
sb.append(context.get("curPage"));
sb.append(", ");
sb.append("\r\n");
sb.append("				pages=");
sb.append(context.get("pages"));
sb.append(", ");
sb.append("\r\n");
List itemList567 = new ArrayList<>();
if (null!=context.get("itemList")){
if (context.get("itemList") instanceof List) itemList567=(List<?>)context.get("itemList");
else if (context.get("itemList").getClass().isArray()) itemList567=Stream.of((Object[])context.get("itemList")).collect(Collectors.toList());
}
itemList567.forEach(playerItem->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					sysItemId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					display = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					name = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					description = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					value = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"quantityForZYZDZPersonal"));
sb.append(", ");
sb.append("\r\n");
sb.append("					lid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.iId"));
sb.append(", ");
sb.append("\r\n");
sb.append("					capacity=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.wAccuracyTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("					time=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerItem,"sysItem.wAccuracyTimeModefier"));
sb.append(", ");
sb.append("\r\n");
sb.append("					resPrices={ ");
sb.append("\r\n");
List playerItemsysItemresPricesList350 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")){
if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList") instanceof List) playerItemsysItemresPricesList350=(List<?>)O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList");
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList").getClass().isArray()) playerItemsysItemresPricesList350=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")).collect(Collectors.toList());
}
playerItemsysItemresPricesList350.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					crPrices={ ");
sb.append("\r\n");
List playerItemsysItemcrPricesList366 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList") instanceof List) playerItemsysItemcrPricesList366=(List<?>)O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList").getClass().isArray()) playerItemsysItemcrPricesList366=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
playerItemsysItemcrPricesList366.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}