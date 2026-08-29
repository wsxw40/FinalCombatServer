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

public class CreateTeam implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("warning = { ");
sb.append("\r\n");
if((O2oUtil.compare(context.get("nameStr"),"!=",""))){
sb.append("		\"");
sb.append(context.get("nameStr"));
sb.append("\"  ");
sb.append("\r\n");
} else {
sb.append("		\"\"  ");
sb.append("\r\n");
}
sb.append(" , ");
sb.append("\r\n");
if((O2oUtil.compare(context.get("descriptionStr"),"!=",""))){
sb.append("		\"");
sb.append(context.get("descriptionStr"));
sb.append("\"  ");
sb.append("\r\n");
} else {
sb.append("		\"\"  ");
sb.append("\r\n");
}
sb.append(" , ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}