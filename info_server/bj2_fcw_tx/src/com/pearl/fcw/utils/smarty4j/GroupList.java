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

public class GroupList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("myGroup = { ");
sb.append("\r\n");
List myGroup387 = new ArrayList<>();
if (null!=context.get("myGroup")){
if (context.get("myGroup") instanceof List) myGroup387=(List<?>)context.get("myGroup");
else if (context.get("myGroup") instanceof int[]) myGroup387=Stream.of((int[])context.get("myGroup")).collect(Collectors.toList());
else if (context.get("myGroup") instanceof long[]) myGroup387=Stream.of((long[])context.get("myGroup")).collect(Collectors.toList());
else if (context.get("myGroup") instanceof float[]) myGroup387=Stream.of((float[])context.get("myGroup")).collect(Collectors.toList());
else if (context.get("myGroup") instanceof double[]) myGroup387=Stream.of((double[])context.get("myGroup")).collect(Collectors.toList());
else if (context.get("myGroup") instanceof byte[]) myGroup387=Stream.of((byte[])context.get("myGroup")).collect(Collectors.toList());
else if (context.get("myGroup") instanceof String[]) myGroup387=Stream.of((String[])context.get("myGroup")).collect(Collectors.toList());
else if (context.get("myGroup").getClass().isArray()) myGroup387=Stream.of(context.get("myGroup")).collect(Collectors.toList());
}
myGroup387.forEach(friend->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(friend,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(friend,"groupName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"card"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("addGroups = { ");
sb.append("\r\n");
List addGroups61 = new ArrayList<>();
if (null!=context.get("addGroups")){
if (context.get("addGroups") instanceof List) addGroups61=(List<?>)context.get("addGroups");
else if (context.get("addGroups") instanceof int[]) addGroups61=Stream.of((int[])context.get("addGroups")).collect(Collectors.toList());
else if (context.get("addGroups") instanceof long[]) addGroups61=Stream.of((long[])context.get("addGroups")).collect(Collectors.toList());
else if (context.get("addGroups") instanceof float[]) addGroups61=Stream.of((float[])context.get("addGroups")).collect(Collectors.toList());
else if (context.get("addGroups") instanceof double[]) addGroups61=Stream.of((double[])context.get("addGroups")).collect(Collectors.toList());
else if (context.get("addGroups") instanceof byte[]) addGroups61=Stream.of((byte[])context.get("addGroups")).collect(Collectors.toList());
else if (context.get("addGroups") instanceof String[]) addGroups61=Stream.of((String[])context.get("addGroups")).collect(Collectors.toList());
else if (context.get("addGroups").getClass().isArray()) addGroups61=Stream.of(context.get("addGroups")).collect(Collectors.toList());
}
addGroups61.forEach(group->{
try{
sb.append("	{ ");
sb.append("\r\n");
List group938 = new ArrayList<>();
if (null!=group){
if (group instanceof List) group938=(List<?>)group;
else if (group instanceof int[]) group938=Stream.of((int[])group).collect(Collectors.toList());
else if (group instanceof long[]) group938=Stream.of((long[])group).collect(Collectors.toList());
else if (group instanceof float[]) group938=Stream.of((float[])group).collect(Collectors.toList());
else if (group instanceof double[]) group938=Stream.of((double[])group).collect(Collectors.toList());
else if (group instanceof byte[]) group938=Stream.of((byte[])group).collect(Collectors.toList());
else if (group instanceof String[]) group938=Stream.of((String[])group).collect(Collectors.toList());
else if (group.getClass().isArray()) group938=Stream.of(group).collect(Collectors.toList());
}
group938.forEach(p->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(p,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(p,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(p,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			\"\", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(p,"groupName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(p,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(p,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(p,"card"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(p,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}