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

public class ResMagicBoxList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("pRes=");
sb.append(context.get("pRes"));
sb.append(" ");
sb.append("\r\n");
sb.append("cost=");
sb.append(context.get("cost"));
sb.append(" ");
sb.append("\r\n");
sb.append("ncost=");
sb.append(context.get("ncost"));
sb.append(" ");
sb.append("\r\n");
sb.append("count=");
sb.append(context.get("count"));
sb.append(" ");
sb.append("\r\n");
sb.append("iid=");
sb.append(context.get("iid"));
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}