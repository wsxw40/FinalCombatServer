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
List itemList566 = new ArrayList<>();
if (null!=context.get("itemList")){
if (context.get("itemList") instanceof List) itemList566=(List<?>)context.get("itemList");
else if (context.get("itemList") instanceof int[]) itemList566=Stream.of((int[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof long[]) itemList566=Stream.of((long[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof float[]) itemList566=Stream.of((float[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof double[]) itemList566=Stream.of((double[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof byte[]) itemList566=Stream.of((byte[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof String[]) itemList566=Stream.of((String[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList").getClass().isArray()) itemList566=Stream.of(context.get("itemList")).collect(Collectors.toList());
}
itemList566.forEach(playerItem->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					level=");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("					playerItemId = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					sysItemId = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					display = \"");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					name = \"");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					description = \"");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					value = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"quantityForZYZDZPersonal"));
sb.append(", ");
sb.append("\r\n");
sb.append("					buycd=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"buyCD"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					durable = 100, ");
sb.append("\r\n");
sb.append("					bullet = 100,	    				 ");
sb.append("\r\n");
sb.append("	    				resDisPrice={ ");
sb.append("\r\n");
sb.append("						id=");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList[0].id"));
sb.append(", ");
sb.append("\r\n");
sb.append("						unittype=");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList[0].unitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cost=");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList[0].cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("						unit=");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList[0].unit"));
sb.append(" ");
sb.append("\r\n");
sb.append("					},							 ");
sb.append("\r\n");
sb.append("	    				resDisPrices={ ");
sb.append("\r\n");
List playerItemsysItemresDisPricesList101 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")){
if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList") instanceof List) playerItemsysItemresDisPricesList101=(List<?>)O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList");
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList") instanceof int[]) playerItemsysItemresDisPricesList101=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList") instanceof long[]) playerItemsysItemresDisPricesList101=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList") instanceof float[]) playerItemsysItemresDisPricesList101=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList") instanceof double[]) playerItemsysItemresDisPricesList101=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList") instanceof byte[]) playerItemsysItemresDisPricesList101=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList") instanceof String[]) playerItemsysItemresDisPricesList101=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList").getClass().isArray()) playerItemsysItemresDisPricesList101=Stream.of(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resDisPricesList")).collect(Collectors.toList());
}
playerItemsysItemresDisPricesList101.forEach(pay->{
try{
sb.append("	    					{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property1.property"));
sb.append(", ");
sb.append("\r\n");
sb.append("							level = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property1.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("							value = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property1.value"));
sb.append(", ");
sb.append("\r\n");
sb.append("							curExp = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property1.curExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("							maxExp = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property1.maxExp"));
sb.append(",								 ");
sb.append("\r\n");
sb.append("						}, ");
sb.append("\r\n");
sb.append("						fireSpeed={ ");
sb.append("\r\n");
sb.append("							property = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property2.property"));
sb.append(", ");
sb.append("\r\n");
sb.append("							level = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property2.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("							value = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property2.value"));
sb.append(", ");
sb.append("\r\n");
sb.append("							curExp = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property2.curExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("							maxExp = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property2.maxExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("						}, ");
sb.append("\r\n");
sb.append("						hp={ ");
sb.append("\r\n");
sb.append("							property = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property3.property"));
sb.append(", ");
sb.append("\r\n");
sb.append("							level = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property3.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("							value = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property3.value"));
sb.append(", ");
sb.append("\r\n");
sb.append("							curExp = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property3.curExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("							maxExp = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property3.maxExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("						}, ");
sb.append("\r\n");
sb.append("						moveSpeed={ ");
sb.append("\r\n");
sb.append("							property = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property6.property"));
sb.append(", ");
sb.append("\r\n");
sb.append("							level = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property6.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("							value = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property6.value"));
sb.append(", ");
sb.append("\r\n");
sb.append("							curExp = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property6.curExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("							maxExp = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"property6.maxExp"));
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
List itemList158 = new ArrayList<>();
if (null!=context.get("itemList")){
if (context.get("itemList") instanceof List) itemList158=(List<?>)context.get("itemList");
else if (context.get("itemList") instanceof int[]) itemList158=Stream.of((int[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof long[]) itemList158=Stream.of((long[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof float[]) itemList158=Stream.of((float[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof double[]) itemList158=Stream.of((double[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof byte[]) itemList158=Stream.of((byte[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof String[]) itemList158=Stream.of((String[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList").getClass().isArray()) itemList158=Stream.of(context.get("itemList")).collect(Collectors.toList());
}
itemList158.forEach(playerItem->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					sysItemId = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					display = \"");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					name = \"");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					description = \"");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					value = ");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"quantityForZYZDZPersonal"));
sb.append(", ");
sb.append("\r\n");
sb.append("					lid=");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.iId"));
sb.append(", ");
sb.append("\r\n");
sb.append("					capacity=");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.wAccuracyTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("					time=");
sb.append(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.wAccuracyTimeModefier"));
sb.append(", ");
sb.append("\r\n");
sb.append("					resPrices={ ");
sb.append("\r\n");
List playerItemsysItemresPricesList386 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")){
if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList") instanceof List) playerItemsysItemresPricesList386=(List<?>)O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList");
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList") instanceof int[]) playerItemsysItemresPricesList386=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList") instanceof long[]) playerItemsysItemresPricesList386=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList") instanceof float[]) playerItemsysItemresPricesList386=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList") instanceof double[]) playerItemsysItemresPricesList386=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList") instanceof byte[]) playerItemsysItemresPricesList386=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList") instanceof String[]) playerItemsysItemresPricesList386=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList").getClass().isArray()) playerItemsysItemresPricesList386=Stream.of(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.resPricesList")).collect(Collectors.toList());
}
playerItemsysItemresPricesList386.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List playerItemsysItemcrPricesList99 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList") instanceof List) playerItemsysItemcrPricesList99=(List<?>)O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList") instanceof int[]) playerItemsysItemcrPricesList99=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList") instanceof long[]) playerItemsysItemcrPricesList99=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList") instanceof float[]) playerItemsysItemcrPricesList99=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList") instanceof double[]) playerItemsysItemcrPricesList99=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList") instanceof byte[]) playerItemsysItemcrPricesList99=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList") instanceof String[]) playerItemsysItemcrPricesList99=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList").getClass().isArray()) playerItemsysItemcrPricesList99=Stream.of(O2oUtil.getSmarty4jProperty(playerItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
playerItemsysItemcrPricesList99.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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