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

public class GetMatchedTeam implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
if( O2oUtil.compare(context.get("outOfCount"),"!=",null)){
sb.append("	outOfCount=\"");
sb.append(context.get("outOfCount"));
sb.append("\" ");
sb.append("\r\n");
}
if( O2oUtil.compare(context.get("outOfStones"),"!=",null)){
sb.append("	outOfStones=\"");
sb.append(context.get("outOfStones"));
sb.append("\" ");
sb.append("\r\n");
}
sb.append(" ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("outOfCount"),"==",null)){
if( O2oUtil.compare(context.get("outOfStones"),"==",null)){
sb.append("		id=\"");
sb.append(context.get("id"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(context.get("name"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		level=");
sb.append(context.get("level"));
sb.append(" ");
sb.append("\r\n");
sb.append("		unusableResource=");
sb.append(context.get("unusableResource"));
sb.append(" ");
sb.append("\r\n");
sb.append("		canRobUnuseRes=");
sb.append(context.get("canRobUnuseRes"));
sb.append(" ");
sb.append("\r\n");
sb.append("		teamSpaceLevel=");
sb.append(context.get("teamSpaceLevel"));
sb.append(" ");
sb.append("\r\n");
sb.append("		battleFieldId=");
sb.append(context.get("battleFieldId"));
sb.append(" ");
sb.append("\r\n");
sb.append("		battleFieldConfig=\"");
sb.append(context.get("battleFieldConfig"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		formatedConfig=\"");
sb.append(context.get("formatedConfig"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		pDisResStone=");
sb.append(context.get("pDisResStone"));
sb.append(" ");
sb.append("\r\n");
}
}
return sb.toString();
}

}