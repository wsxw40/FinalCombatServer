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

public class GetChallengeInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("id=\"");
sb.append(context.get("id"));
sb.append("\" ");
sb.append("\r\n");
sb.append("name=\"");
sb.append(context.get("name"));
sb.append("\" ");
sb.append("\r\n");
sb.append("level=");
sb.append(context.get("level"));
sb.append(" ");
sb.append("\r\n");
sb.append("stones=");
sb.append(context.get("stones"));
sb.append(" ");
sb.append("\r\n");
sb.append("canRobStones=");
sb.append(context.get("canRobStones"));
sb.append(" ");
sb.append("\r\n");
sb.append("teamSpaceLevel=");
sb.append(context.get("teamSpaceLevel"));
sb.append(" ");
sb.append("\r\n");
sb.append("battleFieldId=");
sb.append(context.get("battleFieldId"));
sb.append(" ");
sb.append("\r\n");
sb.append("battleFieldConfig=\"");
sb.append(context.get("battleFieldConfig"));
sb.append("\" ");
sb.append("\r\n");
sb.append("formatedConfig=\"");
sb.append(context.get("formatedConfig"));
sb.append("\" ");
sb.append("\r\n");
sb.append("challengeCost=");
sb.append(context.get("challengeCost"));
sb.append(" ");
sb.append("\r\n");
sb.append("ableBeChallenge=");
sb.append(context.get("ableBeChallenge"));
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}