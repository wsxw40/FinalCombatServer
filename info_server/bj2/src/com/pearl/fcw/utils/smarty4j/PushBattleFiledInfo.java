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

public class PushBattleFiledInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("{ ");
sb.append("\r\n");
sb.append("	cmd = \"battlefield_infopush\", ");
sb.append("\r\n");
sb.append("	canRobRes=");
sb.append(context.get("canRobRes"));
sb.append(", ");
sb.append("\r\n");
sb.append("	dtname=\"");
sb.append(context.get("dtname"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	dtsplevel=");
sb.append(context.get("dtsplevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	dtconfig=\"");
sb.append(context.get("dtconfig"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	restype=");
sb.append(context.get("restype"));
sb.append(" ");
sb.append("\r\n");
sb.append("}	 ");
sb.append("\r\n");
return sb.toString();
}

}