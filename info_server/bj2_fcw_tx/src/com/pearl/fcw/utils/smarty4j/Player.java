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

public class Player implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("pid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"id"));
sb.append(" ");
sb.append("\r\n");
sb.append("level=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"rank"));
sb.append(" ");
sb.append("\r\n");
sb.append("exp=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"exp"));
sb.append(" ");
sb.append("\r\n");
sb.append("expnum=");
sb.append(context.get("exp"));
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}