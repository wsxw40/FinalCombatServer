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
List list933 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list933=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list933=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list933=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list933=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list933=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list933=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list933=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list933=Stream.of(context.get("list")).collect(Collectors.toList());
}
list933.forEach(ch->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		cid=");
sb.append(O2oUtil.getSmarty4jProperty(ch,"sysCharacter.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(ch,"sysCharacter.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		is_open=");
sb.append(O2oUtil.getSmarty4jProperty(ch,"isOpen"));
sb.append(", ");
sb.append("\r\n");
sb.append("		resource=\"");
sb.append(O2oUtil.getSmarty4jProperty(ch,"headResource"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		resourceName=\"");
sb.append(O2oUtil.getSmarty4jProperty(ch,"sysCharacter.resourceName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description=\"");
sb.append(O2oUtil.getSmarty4jProperty(ch,"description"));
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
List bioCharList929 = new ArrayList<>();
if (null!=context.get("bioCharList")){
if (context.get("bioCharList") instanceof List) bioCharList929=(List<?>)context.get("bioCharList");
else if (context.get("bioCharList") instanceof int[]) bioCharList929=Stream.of((int[])context.get("bioCharList")).collect(Collectors.toList());
else if (context.get("bioCharList") instanceof long[]) bioCharList929=Stream.of((long[])context.get("bioCharList")).collect(Collectors.toList());
else if (context.get("bioCharList") instanceof float[]) bioCharList929=Stream.of((float[])context.get("bioCharList")).collect(Collectors.toList());
else if (context.get("bioCharList") instanceof double[]) bioCharList929=Stream.of((double[])context.get("bioCharList")).collect(Collectors.toList());
else if (context.get("bioCharList") instanceof byte[]) bioCharList929=Stream.of((byte[])context.get("bioCharList")).collect(Collectors.toList());
else if (context.get("bioCharList") instanceof String[]) bioCharList929=Stream.of((String[])context.get("bioCharList")).collect(Collectors.toList());
else if (context.get("bioCharList").getClass().isArray()) bioCharList929=Stream.of(context.get("bioCharList")).collect(Collectors.toList());
}
bioCharList929.forEach(bio->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		cid=");
sb.append(O2oUtil.getSmarty4jProperty(bio,"sysCharacter.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(bio,"sysCharacter.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		state=");
sb.append(O2oUtil.getSmarty4jProperty(bio,"stateInCharList"));
sb.append(", ");
sb.append("\r\n");
sb.append("		resourceName=\"");
sb.append(O2oUtil.getSmarty4jProperty(bio,"sysCharacter.resourceName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		leftSeconds=");
sb.append(O2oUtil.getSmarty4jProperty(bio,"leftSeconds"));
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
List bioItemList962 = new ArrayList<>();
if (null!=context.get("bioItemList")){
if (context.get("bioItemList") instanceof List) bioItemList962=(List<?>)context.get("bioItemList");
else if (context.get("bioItemList") instanceof int[]) bioItemList962=Stream.of((int[])context.get("bioItemList")).collect(Collectors.toList());
else if (context.get("bioItemList") instanceof long[]) bioItemList962=Stream.of((long[])context.get("bioItemList")).collect(Collectors.toList());
else if (context.get("bioItemList") instanceof float[]) bioItemList962=Stream.of((float[])context.get("bioItemList")).collect(Collectors.toList());
else if (context.get("bioItemList") instanceof double[]) bioItemList962=Stream.of((double[])context.get("bioItemList")).collect(Collectors.toList());
else if (context.get("bioItemList") instanceof byte[]) bioItemList962=Stream.of((byte[])context.get("bioItemList")).collect(Collectors.toList());
else if (context.get("bioItemList") instanceof String[]) bioItemList962=Stream.of((String[])context.get("bioItemList")).collect(Collectors.toList());
else if (context.get("bioItemList").getClass().isArray()) bioItemList962=Stream.of(context.get("bioItemList")).collect(Collectors.toList());
}
bioItemList962.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("	itemID=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List theItemgpPricesList867 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof List) theItemgpPricesList867=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof int[]) theItemgpPricesList867=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof long[]) theItemgpPricesList867=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof float[]) theItemgpPricesList867=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof double[]) theItemgpPricesList867=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof byte[]) theItemgpPricesList867=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof String[]) theItemgpPricesList867=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList").getClass().isArray()) theItemgpPricesList867=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
}
theItemgpPricesList867.forEach(pay->{
try{
sb.append("		{id=");
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
sb.append("}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List theItemcrPricesList600 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof List) theItemcrPricesList600=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof int[]) theItemcrPricesList600=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof long[]) theItemcrPricesList600=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof float[]) theItemcrPricesList600=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof double[]) theItemcrPricesList600=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof byte[]) theItemcrPricesList600=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof String[]) theItemcrPricesList600=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList").getClass().isArray()) theItemcrPricesList600=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
}
theItemcrPricesList600.forEach(pay->{
try{
sb.append("		{id=");
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
sb.append("}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List theItemvoucherPricesList432 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof List) theItemvoucherPricesList432=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof int[]) theItemvoucherPricesList432=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof long[]) theItemvoucherPricesList432=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof float[]) theItemvoucherPricesList432=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof double[]) theItemvoucherPricesList432=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof byte[]) theItemvoucherPricesList432=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof String[]) theItemvoucherPricesList432=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList").getClass().isArray()) theItemvoucherPricesList432=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
}
theItemvoucherPricesList432.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItemresources886 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources886=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources886=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources886=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources886=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources886=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources886=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources886=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources886=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources886.forEach(resource->{
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