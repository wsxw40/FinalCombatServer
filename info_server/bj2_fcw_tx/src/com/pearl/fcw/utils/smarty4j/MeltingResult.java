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

public class MeltingResult implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("result = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"result"));
sb.append(" ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"result"),"==",0)){
sb.append("	upgrade = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"playerMelting.upgrade"));
sb.append(" ");
sb.append("\r\n");
Context includeContextVar3100=new Context();
includeContextVar3100.set("playerMelting",O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"playerMelting"));
includeContextVar3100.set("rate",O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"rate"));
includeContextVar3100.set("price",O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"price"));
sb.append(new MeltingInfoGet().get(includeContextVar3100));
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"mt"),"==",32)){
Context includeContextVar4952=new Context();
includeContextVar4952.set("sysItem",O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"meltingAward.item"));
includeContextVar4952.set("unitType",O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"meltingAward.unitType"));
includeContextVar4952.set("unit",O2oUtil.getSmarty4jProperty(context.get("meltingReslut"),"meltingAward.unit"));
includeContextVar4952.set("isVip",0);
sb.append(new MeltingOutput().get(includeContextVar4952));
}
}
return sb.toString();
}

}