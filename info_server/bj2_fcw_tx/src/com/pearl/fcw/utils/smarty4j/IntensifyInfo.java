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

public class IntensifyInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("res=");
sb.append(context.get("res"));
sb.append(" ");
sb.append("\r\n");
sb.append("outOfCost=");
sb.append(context.get("outOfCost"));
sb.append(" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("levelChange"),"==",1) ){
sb.append("	intensifyLevel = ");
sb.append(context.get("intensifyLevel"));
sb.append(" ");
sb.append("\r\n");
}
if( O2oUtil.compare(context.get("isUp"),"==",1) ){
sb.append("	itemLevel = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("newTeamItem"),"level"));
sb.append(" ");
sb.append("\r\n");
sb.append("	resources = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("newTeamItem"),"sysItem.name"));
sb.append("\" ");
sb.append("\r\n");
sb.append("	name = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("newTeamItem"),"sysItem.displayName"));
sb.append("\" ");
sb.append("\r\n");
}
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}