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

public class LeagueAttendMemberInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("pList0={");
sb.append("\r\n");
List pList0614 = new ArrayList<>();
if (null!=context.get("pList0")){
if (context.get("pList0") instanceof List) pList0614=(List<?>)context.get("pList0");
else if (context.get("pList0").getClass().isArray()) pList0614=Stream.of((Object[])context.get("pList0")).collect(Collectors.toList());
}
pList0614.forEach(p->{
try{
sb.append("{");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"playerId"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"name"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("pList1={");
sb.append("\r\n");
List pList1855 = new ArrayList<>();
if (null!=context.get("pList1")){
if (context.get("pList1") instanceof List) pList1855=(List<?>)context.get("pList1");
else if (context.get("pList1").getClass().isArray()) pList1855=Stream.of((Object[])context.get("pList1")).collect(Collectors.toList());
}
pList1855.forEach(p->{
try{
sb.append("{");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"playerId"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"name"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("pList2={");
sb.append("\r\n");
List pList2613 = new ArrayList<>();
if (null!=context.get("pList2")){
if (context.get("pList2") instanceof List) pList2613=(List<?>)context.get("pList2");
else if (context.get("pList2").getClass().isArray()) pList2613=Stream.of((Object[])context.get("pList2")).collect(Collectors.toList());
}
pList2613.forEach(p->{
try{
sb.append("{");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"playerId"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"name"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("pList3={");
sb.append("\r\n");
List pList3975 = new ArrayList<>();
if (null!=context.get("pList3")){
if (context.get("pList3") instanceof List) pList3975=(List<?>)context.get("pList3");
else if (context.get("pList3").getClass().isArray()) pList3975=Stream.of((Object[])context.get("pList3")).collect(Collectors.toList());
}
pList3975.forEach(p->{
try{
sb.append("{");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"playerId"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"name"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("pList4={");
sb.append("\r\n");
List pList4876 = new ArrayList<>();
if (null!=context.get("pList4")){
if (context.get("pList4") instanceof List) pList4876=(List<?>)context.get("pList4");
else if (context.get("pList4").getClass().isArray()) pList4876=Stream.of((Object[])context.get("pList4")).collect(Collectors.toList());
}
pList4876.forEach(p->{
try{
sb.append("{");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"playerId"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"name"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("pList5={");
sb.append("\r\n");
List pList553 = new ArrayList<>();
if (null!=context.get("pList5")){
if (context.get("pList5") instanceof List) pList553=(List<?>)context.get("pList5");
else if (context.get("pList5").getClass().isArray()) pList553=Stream.of((Object[])context.get("pList5")).collect(Collectors.toList());
}
pList553.forEach(p->{
try{
sb.append("{");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"playerId"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"name"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}