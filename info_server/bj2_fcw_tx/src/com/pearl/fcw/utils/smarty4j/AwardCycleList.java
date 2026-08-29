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

public class AwardCycleList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("info = {");
sb.append(context.get("cal"));
sb.append(",");
sb.append(context.get("exp"));
sb.append(",");
sb.append(context.get("vipCal"));
sb.append(",");
sb.append(context.get("vipExp"));
sb.append(",");
sb.append(context.get("upgrade"));
sb.append("} ");
sb.append("\r\n");
Context includeContextVar5313=new Context();
includeContextVar5313.set("awardItemVos",O2oUtil.getSmarty4jProperty(context.get("mi"),"awardItemVos"));
sb.append(new MissionItemAward().get(includeContextVar5313));
return sb.toString();
}

}