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

public class QwInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("type=");
sb.append(context.get("type"));
sb.append(" ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("type"),"==",0))){
sb.append("	scoce=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[0]"));
sb.append(" ");
sb.append("\r\n");
sb.append("	kill=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[1]"));
sb.append(" ");
sb.append("\r\n");
sb.append("	dead=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[2]"));
sb.append(" ");
sb.append("\r\n");
sb.append("	assist=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[3]"));
sb.append(" ");
sb.append("\r\n");
sb.append("	win=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[4]"));
sb.append(" ");
sb.append("\r\n");
sb.append("	fail=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[5]"));
sb.append(" ");
sb.append("\r\n");
sb.append("	rank=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[6]"));
sb.append(" ");
sb.append("\r\n");
sb.append("	rankNum=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[7]"));
sb.append(" ");
sb.append("\r\n");
sb.append("	kValue=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[8]"));
sb.append(" ");
sb.append("\r\n");
sb.append("	killSum=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("qwInfo"),"[1]"));
sb.append("			 ");
sb.append("\r\n");
sb.append("	rate=");
sb.append(context.get("rate"));
sb.append("		 ");
sb.append("\r\n");
}
if( (O2oUtil.compare(context.get("type"),"==",1))){
sb.append("	page=");
sb.append(context.get("page"));
sb.append(" ");
sb.append("\r\n");
sb.append("	pageNum=");
sb.append(context.get("pageNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("	items ={ ");
sb.append("\r\n");
List days671 = new ArrayList<>();
if (null!=context.get("days")){
if (context.get("days") instanceof List) days671=(List<?>)context.get("days");
else if (context.get("days") instanceof int[]) days671=Stream.of((int[])context.get("days")).collect(Collectors.toList());
else if (context.get("days") instanceof long[]) days671=Stream.of((long[])context.get("days")).collect(Collectors.toList());
else if (context.get("days") instanceof float[]) days671=Stream.of((float[])context.get("days")).collect(Collectors.toList());
else if (context.get("days") instanceof double[]) days671=Stream.of((double[])context.get("days")).collect(Collectors.toList());
else if (context.get("days") instanceof byte[]) days671=Stream.of((byte[])context.get("days")).collect(Collectors.toList());
else if (context.get("days") instanceof String[]) days671=Stream.of((String[])context.get("days")).collect(Collectors.toList());
else if (context.get("days").getClass().isArray()) days671=Stream.of(context.get("days")).collect(Collectors.toList());
}
days671.forEach(day->{
try{
sb.append("		{s=");
sb.append(O2oUtil.getSmarty4jProperty(day,"[0]"));
sb.append(",k=");
sb.append(O2oUtil.getSmarty4jProperty(day,"[1]"));
sb.append(",d=");
sb.append(O2oUtil.getSmarty4jProperty(day,"[2]"));
sb.append(",a=");
sb.append(O2oUtil.getSmarty4jProperty(day,"[3]"));
sb.append(",w=");
sb.append(O2oUtil.getSmarty4jProperty(day,"[4]"));
sb.append(",f=");
sb.append(O2oUtil.getSmarty4jProperty(day,"[5]"));
sb.append(",r=");
sb.append(O2oUtil.getSmarty4jProperty(day,"[6]"));
sb.append(",ks=");
sb.append(O2oUtil.getSmarty4jProperty(day,"[9]"));
sb.append(",t=\"");
sb.append(O2oUtil.getSmarty4jProperty(day,"[10]"));
sb.append("\"}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("		} ");
sb.append("\r\n");
}
if( (O2oUtil.compare(context.get("type"),"==",2))){
sb.append("	page=");
sb.append(context.get("page"));
sb.append(" ");
sb.append("\r\n");
sb.append("	pageNum=");
sb.append(context.get("pageNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("	items ={ ");
sb.append("\r\n");
List sums338 = new ArrayList<>();
if (null!=context.get("sums")){
if (context.get("sums") instanceof List) sums338=(List<?>)context.get("sums");
else if (context.get("sums") instanceof int[]) sums338=Stream.of((int[])context.get("sums")).collect(Collectors.toList());
else if (context.get("sums") instanceof long[]) sums338=Stream.of((long[])context.get("sums")).collect(Collectors.toList());
else if (context.get("sums") instanceof float[]) sums338=Stream.of((float[])context.get("sums")).collect(Collectors.toList());
else if (context.get("sums") instanceof double[]) sums338=Stream.of((double[])context.get("sums")).collect(Collectors.toList());
else if (context.get("sums") instanceof byte[]) sums338=Stream.of((byte[])context.get("sums")).collect(Collectors.toList());
else if (context.get("sums") instanceof String[]) sums338=Stream.of((String[])context.get("sums")).collect(Collectors.toList());
else if (context.get("sums").getClass().isArray()) sums338=Stream.of(context.get("sums")).collect(Collectors.toList());
}
sums338.forEach(sum->{
try{
sb.append("	{s=");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[0]"));
sb.append(",k=");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[1]"));
sb.append(",d=");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[2]"));
sb.append(",w=");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[4]"));
sb.append(",f=");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[5]"));
sb.append(",r=");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[6]"));
sb.append(",rN=");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[7]"));
sb.append(",n=\"");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[9]"));
sb.append("\",pR=");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[10]"));
sb.append(",vR=");
sb.append(O2oUtil.getSmarty4jProperty(sum,"[11]"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
}
return sb.toString();
}

}