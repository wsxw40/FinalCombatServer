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

public class PlayerCharacterList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("data ={ ");
sb.append("\r\n");
List list50 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list50=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list50=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list50.forEach(ch->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		cid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(ch,"sysCharacter.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(ch,"sysCharacter.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		is_open=");
sb.append(O2oUtil.getSmarty4jPropertyNil(ch,"isOpen"));
sb.append(", ");
sb.append("\r\n");
sb.append("		resource=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(ch,"headResource"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		resourceName=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(ch,"sysCharacter.resourceName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(ch,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("select={");
sb.append(context.get("selectIds"));
sb.append(",} ");
sb.append("\r\n");
sb.append("size=");
sb.append(context.get("size"));
sb.append(" ");
sb.append("\r\n");
sb.append("openSize=");
sb.append(context.get("openSize"));
sb.append(" ");
sb.append("\r\n");
sb.append("bioChardata={ ");
sb.append("\r\n");
List bioCharList248 = new ArrayList<>();
if (null!=context.get("bioCharList")){
if (context.get("bioCharList") instanceof List) bioCharList248=(List<?>)context.get("bioCharList");
else if (context.get("bioCharList").getClass().isArray()) bioCharList248=Stream.of((Object[])context.get("bioCharList")).collect(Collectors.toList());
}
bioCharList248.forEach(bio->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		cid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(bio,"sysCharacter.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(bio,"sysCharacter.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		state=");
sb.append(O2oUtil.getSmarty4jPropertyNil(bio,"stateInCharList"));
sb.append(", ");
sb.append("\r\n");
sb.append("		resourceName=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(bio,"sysCharacter.resourceName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		leftSeconds=");
sb.append(O2oUtil.getSmarty4jPropertyNil(bio,"leftSeconds"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("bioItemdata={ ");
sb.append("\r\n");
List bioItemList747 = new ArrayList<>();
if (null!=context.get("bioItemList")){
if (context.get("bioItemList") instanceof List) bioItemList747=(List<?>)context.get("bioItemList");
else if (context.get("bioItemList").getClass().isArray()) bioItemList747=Stream.of((Object[])context.get("bioItemList")).collect(Collectors.toList());
}
bioItemList747.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("	itemID=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List theItemgpPricesList932 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof List) theItemgpPricesList932=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList").getClass().isArray()) theItemgpPricesList932=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
}
theItemgpPricesList932.forEach(pay->{
try{
sb.append("		{id=");
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
sb.append("}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List theItemcrPricesList196 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof List) theItemcrPricesList196=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList").getClass().isArray()) theItemcrPricesList196=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
}
theItemcrPricesList196.forEach(pay->{
try{
sb.append("		{id=");
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
sb.append("}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List theItemvoucherPricesList303 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof List) theItemvoucherPricesList303=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList").getClass().isArray()) theItemvoucherPricesList303=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
}
theItemvoucherPricesList303.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
List theItemresources566 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources566=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources566=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources566.forEach(resource->{
try{
sb.append("			\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}