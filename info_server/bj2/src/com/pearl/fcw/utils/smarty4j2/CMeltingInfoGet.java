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

public class CMeltingInfoGet implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("info = { ");
sb.append("\r\n");
sb.append("	meltingLevel = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("	slotNum = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"slotNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("	exp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	total_exp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"totalExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	meltingEnergy = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"num"));
sb.append(", ");
sb.append("\r\n");
sb.append("	remaind = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"remaindSecond"));
sb.append(", ");
sb.append("\r\n");
sb.append("	residual = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"remaindInt"));
sb.append(", ");
sb.append("\r\n");
sb.append("	recycle_ratio = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"recycleRatio"));
sb.append(" ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}