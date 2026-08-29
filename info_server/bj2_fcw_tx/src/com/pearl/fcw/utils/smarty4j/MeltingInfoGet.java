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

public class MeltingInfoGet implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("info = { ");
sb.append("\r\n");
sb.append("	meltingLevel = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerMelting"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("	slotNum = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerMelting"),"slotNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("	exp = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerMelting"),"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	total_exp = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerMelting"),"totalExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	meltingEnergy = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerMelting"),"num"));
sb.append(", ");
sb.append("\r\n");
sb.append("	remaind = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerMelting"),"remaindSecond"));
sb.append(", ");
sb.append("\r\n");
sb.append("	residual = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerMelting"),"remaindInt"));
sb.append(", ");
sb.append("\r\n");
sb.append("	rate = ");
sb.append(context.get("rate"));
sb.append(", ");
sb.append("\r\n");
sb.append("	price = ");
sb.append(context.get("price"));
sb.append(" ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}