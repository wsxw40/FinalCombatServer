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

public class TeamContributionGet implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("info = { ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"level"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"exp"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"totalExp"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"contributionLimit"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerTeam"),"contribution"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	");
sb.append(context.get("burnt"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	");
sb.append(context.get("flag"));
sb.append(", ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}