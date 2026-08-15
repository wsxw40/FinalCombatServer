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

public class CharacterList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("characters = { ");
sb.append("\r\n");
List characterList518 = new ArrayList<>();
if (null!=context.get("characterList")){
if (context.get("characterList") instanceof List) characterList518=(List<?>)context.get("characterList");
else if (context.get("characterList") instanceof int[]) characterList518=Stream.of((int[])context.get("characterList")).collect(Collectors.toList());
else if (context.get("characterList") instanceof long[]) characterList518=Stream.of((long[])context.get("characterList")).collect(Collectors.toList());
else if (context.get("characterList") instanceof float[]) characterList518=Stream.of((float[])context.get("characterList")).collect(Collectors.toList());
else if (context.get("characterList") instanceof double[]) characterList518=Stream.of((double[])context.get("characterList")).collect(Collectors.toList());
else if (context.get("characterList") instanceof byte[]) characterList518=Stream.of((byte[])context.get("characterList")).collect(Collectors.toList());
else if (context.get("characterList") instanceof String[]) characterList518=Stream.of((String[])context.get("characterList")).collect(Collectors.toList());
else if (context.get("characterList").getClass().isArray()) characterList518=Stream.of(context.get("characterList")).collect(Collectors.toList());
}
characterList518.forEach(character->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		id=");
sb.append(O2oUtil.getSmarty4jProperty(character,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gender=\"");
sb.append(O2oUtil.getSmarty4jProperty(character,"gender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		cost=");
sb.append(O2oUtil.getSmarty4jProperty(character,"cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("		team0={ ");
sb.append("\r\n");
List charactercostumeT531 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(character,"costumeT")){
if (O2oUtil.getSmarty4jProperty(character,"costumeT") instanceof List) charactercostumeT531=(List<?>)O2oUtil.getSmarty4jProperty(character,"costumeT");
else if (O2oUtil.getSmarty4jProperty(character,"costumeT") instanceof int[]) charactercostumeT531=Stream.of((int[])O2oUtil.getSmarty4jProperty(character,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeT") instanceof long[]) charactercostumeT531=Stream.of((long[])O2oUtil.getSmarty4jProperty(character,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeT") instanceof float[]) charactercostumeT531=Stream.of((float[])O2oUtil.getSmarty4jProperty(character,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeT") instanceof double[]) charactercostumeT531=Stream.of((double[])O2oUtil.getSmarty4jProperty(character,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeT") instanceof byte[]) charactercostumeT531=Stream.of((byte[])O2oUtil.getSmarty4jProperty(character,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeT") instanceof String[]) charactercostumeT531=Stream.of((String[])O2oUtil.getSmarty4jProperty(character,"costumeT")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeT").getClass().isArray()) charactercostumeT531=Stream.of(O2oUtil.getSmarty4jProperty(character,"costumeT")).collect(Collectors.toList());
}
charactercostumeT531.forEach(resourceT->{
try{
if((O2oUtil.compare(resourceT,"!=",""))){
sb.append("					\"");
sb.append(resourceT);
sb.append("\", ");
sb.append("\r\n");
} else {
sb.append("					\"\",  ");
sb.append("\r\n");
}
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		team1={ ");
sb.append("\r\n");
List charactercostumeP414 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(character,"costumeP")){
if (O2oUtil.getSmarty4jProperty(character,"costumeP") instanceof List) charactercostumeP414=(List<?>)O2oUtil.getSmarty4jProperty(character,"costumeP");
else if (O2oUtil.getSmarty4jProperty(character,"costumeP") instanceof int[]) charactercostumeP414=Stream.of((int[])O2oUtil.getSmarty4jProperty(character,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeP") instanceof long[]) charactercostumeP414=Stream.of((long[])O2oUtil.getSmarty4jProperty(character,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeP") instanceof float[]) charactercostumeP414=Stream.of((float[])O2oUtil.getSmarty4jProperty(character,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeP") instanceof double[]) charactercostumeP414=Stream.of((double[])O2oUtil.getSmarty4jProperty(character,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeP") instanceof byte[]) charactercostumeP414=Stream.of((byte[])O2oUtil.getSmarty4jProperty(character,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeP") instanceof String[]) charactercostumeP414=Stream.of((String[])O2oUtil.getSmarty4jProperty(character,"costumeP")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(character,"costumeP").getClass().isArray()) charactercostumeP414=Stream.of(O2oUtil.getSmarty4jProperty(character,"costumeP")).collect(Collectors.toList());
}
charactercostumeP414.forEach(resourceP->{
try{
if((O2oUtil.compare(resourceP,"!=",""))){
sb.append("					");
sb.append(resourceP);
sb.append("\", ");
sb.append("\r\n");
} else {
sb.append("					\"\", ");
sb.append("\r\n");
}
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		},  ");
sb.append("\r\n");
sb.append("		weapon_info ={type=3,\"mk18/bh1\", \"mk18/rv1\", \"mk18/gs1\", \"mk18/si1\", \"mk18/tg1\", \"mk18/mz1\", \"mk18/rs1\", \"mk18/bl1\", \"mk18/fh1\", }, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	 ");
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