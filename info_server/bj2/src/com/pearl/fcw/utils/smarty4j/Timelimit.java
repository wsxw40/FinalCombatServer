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

public class Timelimit implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("timelimit =  ");
if( O2oUtil.compare(context.get("unitType"),"==",0)){
sb.append(" \"<C440^0>\"  ");
sb.append("\r\n");
} else if ( O2oUtil.compare(context.get("unitType"),"==",1)){
sb.append(" \"<C438^1^");
sb.append(context.get("unit"));
sb.append(">\"  ");
sb.append("\r\n");
} else if (  O2oUtil.compare(context.get("unitType"),"==",2)){
sb.append(" \"<C439^1^");
sb.append(context.get("unit"));
sb.append(">\"  ");
sb.append("\r\n");
}
sb.append(" , ");
sb.append("\r\n");
return sb.toString();
}

}