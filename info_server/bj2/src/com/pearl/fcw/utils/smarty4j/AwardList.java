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

public class AwardList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("info = {0,0,0,0,0} ");
sb.append("\r\n");
sb.append("items =  ");
sb.append("{ ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("pa"),"!=",null))){
Context includeContextVar9693=new Context();
includeContextVar9693.set("sysItem",O2oUtil.getSmarty4jProperty(context.get("pa"),"sysActivity.sysItem"));
includeContextVar9693.set("unit",O2oUtil.getSmarty4jProperty(context.get("pa"),"sysActivity.unit"));
includeContextVar9693.set("unitType",O2oUtil.getSmarty4jProperty(context.get("pa"),"sysActivity.unitType"));
includeContextVar9693.set("isVip",0);
sb.append(new MissionItemAwardEntity().get(includeContextVar9693));
}
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}