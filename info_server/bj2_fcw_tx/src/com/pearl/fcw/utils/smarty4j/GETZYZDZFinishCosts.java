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

public class GETZYZDZFinishCosts implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("PRICE={ ");
sb.append("\r\n");
sb.append("	type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("costs"),"[0]"));
sb.append(", ");
sb.append("\r\n");
sb.append("	buyCD=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("costs"),"[1]"));
sb.append(", ");
sb.append("\r\n");
sb.append("	fullCD=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("costs"),"[2]"));
sb.append(", ");
sb.append("\r\n");
sb.append("	fccost=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("costs"),"[3]"));
sb.append(" ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}