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

public class GrowthGift implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("info = { ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mi"),"money"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mi"),"exp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mi"),"vipmoney"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mi"),"vipexp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mi"),"upgrade"));
sb.append(" ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
Context includeContextVar107=new Context();
includeContextVar107.set("awardItemVos",O2oUtil.getSmarty4jProperty(context.get("mi"),"awardItemVos"));
sb.append(new MissionItemAward().get(includeContextVar107));
return sb.toString();
}

}