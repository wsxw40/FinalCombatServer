package com.pearl.fcw.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.ConfigurationUtil;

/**
 * 代码生成工具
 * 生成Smarty4j代码文件
 */
public class CodeGenerateSmarty4jUtil2 {
    private static Logger logger = LoggerFactory.getLogger(CodeGenerateSmarty4jUtil2.class);
	//模板文件夹路径
    private static final String TEMPLATE_DIR_PATH = ConfigurationUtil.TEMPLATE_PATH2;
	//生成Smarty4j.java文件路径
    private static final String TARGET_FILE_PATH = ConfigurationUtil.APP_ROOT_PATH + "/src/com/pearl/fcw/utils/Smarty4jUtil2.java";
	//生成模板对应的java文件所属文件夹路径
    private static final String TARGET_DIR_PATH = ConfigurationUtil.APP_ROOT_PATH + "/src/com/pearl/fcw/utils/smarty4j2/";

    private static final String LINE_FEED = "\r\n";

    private static final Pattern PATTERN1 = Pattern.compile("#foreach[^#]+#");
    private static final Pattern PATTERN2 = Pattern.compile("#/foreach[^#]*#");
    private static final Pattern PATTERN3 = Pattern.compile("from[\\s]*=[\\s]*\\$([^\\s]+)");
    private static final Pattern PATTERN4 = Pattern.compile("item[\\s]*=[\\s]*([^\\s,#]+)");
	private static final Pattern PATTERN5 = Pattern.compile("#([^#]*\\$[^#]*)#");//截取smarty4j的变量(#$abc#)或者常量
	private static final Pattern PATTERN6 = Pattern.compile("([\\$a-zA-Z0-9_\\.\\[\\]]+)");//截取smarty4j的变量($abc)或者常量
	private static final Pattern PATTERN6_1 = Pattern.compile("(\\$[a-zA-Z0-9_\\[\\]]+)");//截取smarty4j的变量($abc)或者常量（文件导入替换使用，不含.）
    private static final Pattern PATTERN7 = Pattern.compile("#if[^#]*#");
    private static final Pattern PATTERN8 = Pattern.compile("#/if#");
    private static final Pattern PATTERN9 = Pattern.compile("#elseif[^#]+#");
    private static final Pattern PATTERN10 = Pattern.compile("#else[^#]*#");
	//截取运算比较符号
    private static final Pattern PATTERN11 = Pattern.compile("([\\$|a-z|A-Z|0-9|_|\\.|\"|\\\"|\\\\\"]+)[\\s]*(>|<|>=|<=|==|!=)[\\s]*([\\$|a-z|A-Z|0-9|_|\\.|\"|\\\"|\\\\\"]+)");
	//截取运算符号
    private static final Pattern PATTERN11_1 = Pattern.compile("([\\$a-zA-Z0-9_\\.^O2o]+)[\\s]*[\\+|\\-|\\*|/][\\s]*([\\$a-zA-Z0-9_\\.]+)");
	private static final Pattern PATTERN11_2 = Pattern.compile("([^#]*)(#[^#\\+\\-\\*/]+[\\+\\-\\*/][^#\\+\\-\\*/]+#)([^#]*)");//逻辑和打印混合
	private static final Pattern PATTERN11_3 = Pattern.compile("^#[^#]+[\\+\\-\\*/]+[^#]+#$");//计算语句块(已去除两端空白符号)
	private static final Pattern PATTERN12 = Pattern.compile("#include file=\"([a-zA-Z0-9\\.]+)\"[^#]*#");//引入其他文件
	private static final Pattern PATTERN13 = Pattern.compile("[\\s]+([a-zA-Z0-9_\\.]+)[\\s]*=[\\s]*([\\$]{0,1}[a-zA-Z0-9_\\.\\\"]+)");//引入其他文件参数变换
	private static final Pattern PATTERN14 = Pattern.compile("#\\*[^#]+\\*#");//注释内容，不输出
    private static final Pattern PATTERN0 = Pattern.compile("(#foreach[^#]+#)|(#/foreach[^#]*#)|(#if[^#]*#)|(#/if#)|(#elseif[^#]+#)|(#else[^#]*#)");
	//导入文件Context变量的前缀
    private static final String INCLUDE_CONTEXT_VAR_PREFIX = "includeContextVar";

    public static void main(String[] args) throws Exception {
        generateSmarty4j();
    }

    /**
	 * 生成Smraty4j的java转义代码文件
	 * @throws Exception
	 */
    public static void generateSmarty4j() throws Exception {
		//模板文件夹
        File file = new File(TEMPLATE_DIR_PATH);

		//生成总的引用类
        generateSmarty4j(null, new StringBuilder(), null);
		//生成对应每个模板文件的模板类
        for (File f : file.listFiles()) {
			generateSmarty4j(f, new StringBuilder(), "SysItem.st");
        }
        logger.info("Smarty4j2 generate end.");
    }

    /**
	 * 按照文件或者文件夹内单层文件生成Smarty4jUtil代码
	 * @param file
	 * @param sb
	 * @param fileStarts 为null时无限制条件。为特定字符串时，只编译以此字符串开头的文件名的文件生成Smarty4jUtil代码
	 * @throws Exception
	 */
    private static void generateSmarty4j(File file, final StringBuilder sb, String fileStarts) throws Exception {
        if (sb.length() < 1 && null == file) {
			//文件头部导包
            sb.append("package com.pearl.fcw.utils;").append(LINE_FEED).append(LINE_FEED);
            //            sb.append("import org.lilystudio.smarty4j.Context;").append(LINE_FEED);
            sb.append("import java.util.Map;").append(LINE_FEED);
            sb.append("import org.slf4j.Logger;").append(LINE_FEED);
            sb.append("import org.slf4j.LoggerFactory;").append(LINE_FEED);
            sb.append(LINE_FEED);
			//类名语句块开始
            sb.append("public class Smarty4jUtil2 {").append(LINE_FEED);
			//固定类成员logger
            sb.append("private static Logger logger = LoggerFactory.getLogger(Smarty4jUtil2.class);").append(LINE_FEED).append(LINE_FEED);
			//固定方法get
            sb.append("public static <T> String get(String template, Map<String, T> context) throws Exception{").append(LINE_FEED);
            sb.append("logger.info(template);").append(LINE_FEED);
            sb.append("try {").append(LINE_FEED);
            sb.append("String className = template.substring(0, template.indexOf(\".\"));").append(LINE_FEED);
            sb.append("Class<?> clazz = Class.forName(\"com.pearl.fcw.utils.smarty4j2.\" + className);").append(LINE_FEED);
            sb.append("String str = ((Ctx)clazz.newInstance()).get(context);").append(LINE_FEED);
			//            sb.append("logger.info(str);").append(LINE_FEED);//FIXME 生产环境中可删除该行代码
            sb.append("return str;").append(LINE_FEED);
            sb.append("} catch (Exception e) {").append(LINE_FEED);
            sb.append("throw e;").append(LINE_FEED);
            sb.append("}").append(LINE_FEED);
            sb.append("}").append(LINE_FEED);
			//固定接口Ctx
            sb.append("public  interface Ctx{").append(LINE_FEED);
            sb.append("<T> String get(Map<String, T> context) throws Exception;").append(LINE_FEED);
            sb.append("}").append(LINE_FEED).append(LINE_FEED);
			//类结尾
            sb.append("}");
			//生成文件
            File target = new File(TARGET_FILE_PATH);
            if (!target.exists()) {
                target.createNewFile();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(target);
                fos.write(sb.toString().getBytes("utf-8"));
            } catch (Exception e) {
                throw e;
            } finally {
                if (null != fos) {
                    fos.close();
                }
                logger.info("Smarty4j2.java creating is end.");
            }
        } else if (sb.length() < 1 && null != file) {
            if (null != fileStarts && !file.getName().startsWith(fileStarts)) {
                return;
            }
			//文件头部导包
            sb.append("package com.pearl.fcw.utils.smarty4j2;").append(LINE_FEED).append(LINE_FEED);
            sb.append("import java.util.List;").append(LINE_FEED);
            sb.append("import java.util.ArrayList;").append(LINE_FEED);
            sb.append("import java.util.HashMap;").append(LINE_FEED);
            sb.append("import java.util.Arrays;").append(LINE_FEED);
            sb.append("import java.util.stream.Collectors;").append(LINE_FEED);
            sb.append("import java.util.stream.Stream;").append(LINE_FEED);
            //            sb.append("import org.lilystudio.smarty4j.Context;").append(LINE_FEED);
            sb.append("import java.util.Map;").append(LINE_FEED);
            sb.append("import org.slf4j.Logger;").append(LINE_FEED);
            sb.append("import org.slf4j.LoggerFactory;").append(LINE_FEED);
            sb.append("import com.pearl.fcw.utils.O2oUtil;").append(LINE_FEED);
            sb.append("import com.pearl.fcw.utils.Smarty4jUtil2.Ctx;").append(LINE_FEED);
            sb.append(LINE_FEED);
			//类名语句块开始
            sb.append("public class ").append(file.getName().subSequence(0, file.getName().indexOf("."))).append(" implements Ctx {").append(LINE_FEED);
			//固定类成员logger
            sb.append("private Logger logger = LoggerFactory.getLogger(getClass());").append(LINE_FEED).append(LINE_FEED);
            if (null == fileStarts) {
                generateSmarty4jMethod(file, sb);
            } else if (file.getName().startsWith(fileStarts)) {
                generateSmarty4jMethod(file, sb);
            }
			//类结尾
            sb.append("}");
			//生成文件
            String fileName = file.getName().substring(0, file.getName().indexOf("."));
            File target = new File(TARGET_DIR_PATH + fileName + ".java");
            if (!target.exists()) {
                target.createNewFile();
            }
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(target);
                fos.write(sb.toString().getBytes("utf-8"));
            } catch (Exception e) {
                throw e;
            } finally {
                if (null != fos) {
                    fos.close();
                }
                logger.info(fileName + ".java creating is end.");
            }
        }
    }

    /**
	 * 生成对应一个模板文件的Smarty4j转义代码
	 * @param file
	 * @param sb
	 * @throws Exception
	 */
    private static void generateSmarty4jMethod(File file, final StringBuilder sb) throws Exception {
        FileReader fr = null;
        BufferedReader br = null;
		List<String> varList = new ArrayList<>();//全体语句块的自设变量集合
		Map<String, String> includeVarMap = new HashMap<>();//文件导入时用于变量替换
		//方法名语句块开始
        String str = file.getName().substring(0, file.getName().indexOf("."));
        sb.append("@SuppressWarnings({ \"unchecked\", \"rawtypes\" })").append(LINE_FEED);
        sb.append("public <T> String get").append("(Map<String,T> context) throws Exception {").append(LINE_FEED);
		//方法体内容
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            str = br.readLine();
            sb.append("StringBuilder sb = new StringBuilder();").append(LINE_FEED);
            while (null != str) {
                generateSmarty4jMutiLine(file, str, sb, varList, includeVarMap);
                str = br.readLine();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            br.close();
            fr.close();
        }
        sb.append("return sb.toString();").append(LINE_FEED);

		//方法名语句块结束
        sb.append("}").append(LINE_FEED).append(LINE_FEED);
    }

    /**
	 * 将Smarty4j一行内可能隐藏的多行代码拆解为多行，并且解析
	 * @param file
	 * @param str
	 * @param sb
	 * @param varList
	 * @param includeVarMap
	 * @throws Exception
	 */
    private static void generateSmarty4jMutiLine(final File file, String str, final StringBuilder sb, final List<String> varList, final Map<String, String> includeVarMap) throws Exception {
        str += " ";
        Matcher m = PATTERN0.matcher(str);
        List<String> list = new ArrayList<>();
        while (m.find()) {
            list.add(m.group());
            str = str.replace(m.group(), "%s");
        }
        if (str.contains("%s")) {
            int i = 0;
            for (String s : str.split("%s")) {
                if (str.trim().startsWith("%s")) {
                    if (i < list.size()) {
                        generateSmarty4jSingleLine(file, list.get(i++), sb, varList, includeVarMap);
                    }
                    if (!s.trim().equals("")) {
                        generateSmarty4jSingleLine(file, s, sb, varList, includeVarMap);
                    }
                } else {
                    if (!s.trim().equals("")) {
                        generateSmarty4jSingleLine(file, s, sb, varList, includeVarMap);
                    }
                    if (i < list.size()) {
                        generateSmarty4jSingleLine(file, list.get(i++), sb, varList, includeVarMap);
                    }
                }
            }
        } else {
            generateSmarty4jSingleLine(file, str, sb, varList, includeVarMap);
        }
    }

    /**
	 * 解析Smarty4j的一行代码
	 * @param file
	 * @param str
	 * @param sb
	 * @param varList
	 * @param includeVarMap
	 * @throws Exception
	 */
    private static void generateSmarty4jSingleLine(final File file, String str, final StringBuilder sb, final List<String> varList, final Map<String, String> includeVarMap) throws Exception {
		//检查是否有文件导入
        Matcher m = PATTERN12.matcher(str);
		//有文件导入
        if (m.find()) {
            Matcher m1 = PATTERN13.matcher(str);
			//引用其他模板类的类名
            String className = null;
			//导入文件的Context变量
            String includeContextVar = INCLUDE_CONTEXT_VAR_PREFIX + new Random().nextInt(10000);
            while (varList.contains(includeContextVar)) {
                includeContextVar = INCLUDE_CONTEXT_VAR_PREFIX + new Random().nextInt(10000);
            }
            varList.add(includeContextVar);
            sb.append("Map<String, Object> ").append(includeContextVar).append("=new HashMap<>();").append(LINE_FEED);
            while (m1.find()) {
                if (m1.group(1).equals("file")) {
                    className = m1.group(2).substring(1, m1.group(2).indexOf("."));
                } else if (m1.group(2).startsWith("$")) {
                    String var = generateSmarty4jVarInLogic(m1.group(2), varList);
                    sb.append(includeContextVar).append(".put(\"").append(m1.group(1)).append("\",").append(var).append(");").append(LINE_FEED);
                } else {
                    sb.append(includeContextVar).append(".put(\"").append(m1.group(1)).append("\",").append(m1.group(2)).append(");").append(LINE_FEED);
                }
            }
			//引用其他模板类生成字符串
            if (null != className) {
                sb.append("sb.append(new ").append(className).append("().get(").append(includeContextVar).append("));").append(LINE_FEED);
            }
            return;
        }

		//无文件导入
        Matcher m1 = PATTERN6_1.matcher(str);
		while (m1.find()) {//文件导入中的变量替换
            if (includeVarMap.containsKey(m1.group())) {
                str = str.replaceFirst("\\" + m1.group(), "\\" + includeVarMap.get(m1.group()));
            }
        }
		if (PATTERN1.matcher(str).find()) {//for循环语句块开始
            str = generateSmarty4jCal(str);
            str = generateSmarty4jCompare(str);
			//获取列表对象
            m = PATTERN3.matcher(str);
            if (m.find()) {
                String var = m.group(1);
                var = generateSmarty4jVarInLogic("$" + var, varList);
                String tmpVar = m.group(1).replace(".", "") + new Random().nextInt(1000);
                while (varList.contains(tmpVar) || includeVarMap.keySet().contains(tmpVar) || includeVarMap.values().contains(tmpVar)) {
                    tmpVar = m.group(1).replace(".", "") + new Random().nextInt(1000);
                }
                sb.append("List ").append(tmpVar).append(" = new ArrayList<>();").append(LINE_FEED);
                sb.append("if (null!=").append(var).append("){").append(LINE_FEED);
                sb.append("if (").append(var).append(" instanceof List) ").append(tmpVar).append("=(List<?>)").append(var).append(";").append(LINE_FEED);
                //                sb.append("else if (").append(var).append(" instanceof int[]) ").append(tmpVar).append("=Stream.of((int[])").append(var).append(").collect(Collectors.toList());").append(LINE_FEED);
                //                sb.append("else if (").append(var).append(" instanceof long[]) ").append(tmpVar).append("=Stream.of((long[])").append(var).append(").collect(Collectors.toList());").append(LINE_FEED);
                //                sb.append("else if (").append(var).append(" instanceof float[]) ").append(tmpVar).append("=Stream.of((float[])").append(var).append(").collect(Collectors.toList());")
                //                        .append(LINE_FEED);
                //                sb.append("else if (").append(var).append(" instanceof double[]) ").append(tmpVar).append("=Stream.of((double[])").append(var).append(").collect(Collectors.toList());")
                //                        .append(LINE_FEED);
                //                sb.append("else if (").append(var).append(" instanceof byte[]) ").append(tmpVar).append("=Stream.of((byte[])").append(var).append(").collect(Collectors.toList());").append(LINE_FEED);
                //                sb.append("else if (").append(var).append(" instanceof String[]) ").append(tmpVar).append("=Stream.of((String[])").append(var).append(").collect(Collectors.toList());")
                //                        .append(LINE_FEED);
                sb.append("else if (").append(var).append(".getClass().isArray()) ").append(tmpVar).append("=Stream.of((Object[])").append(var).append(").collect(Collectors.toList());")
                        .append(LINE_FEED);
                sb.append("}").append(LINE_FEED);
                sb.append(tmpVar).append(".forEach(");
				//获取列表成员对象
                m = PATTERN4.matcher(str);
                if (m.find()) {
                    var = m.group(1);
                    varList.add(var);
                    sb.append(generateSmarty4jVarInLogic("$" + var, varList)).append("->{").append(LINE_FEED);
                }
            }
            sb.append("try{").append(LINE_FEED);
		} else if (PATTERN2.matcher(str).find()) {//for循环语句块结束
            sb.append("}catch(Exception e").append(varList.size()).append("){").append(LINE_FEED);
            sb.append("logger.error(e").append(varList.size()).append(".toString());").append(LINE_FEED);
			//            sb.append("e").append(varList.size()).append(".printStackTrace();").append(LINE_FEED);//FIXME 生产环境中可去除该行代码
            sb.append("}").append(LINE_FEED);
            sb.append("});").append(LINE_FEED);
		} else if (PATTERN7.matcher(str).find()) {//if语句块开始
            str = generateSmarty4jCal(str);
            str = generateSmarty4jCompare(str.replace("#if", "#if(").replace("#", "") + ")");
            str = generateSmarty4jVarInLogic(str, varList);
            sb.append(str).append("{").append(LINE_FEED);
		} else if (PATTERN8.matcher(str).find()) {//if语句块结束
            sb.append("}").append(LINE_FEED);
		} else if (PATTERN9.matcher(str).find()) {//elseif语句块
            str = generateSmarty4jCal(str);
            str = generateSmarty4jCompare(str.replace("#elseif", "#} else if (").replace("#", "") + "){");
            str = generateSmarty4jVarInLogic(str, varList);
            sb.append(str).append(LINE_FEED);
		} else if (PATTERN10.matcher(str).find()) {//else语句块
            str = str.replace("#else", "#} else {").replace("#", "");
            sb.append(str).append(LINE_FEED);
		} else if (PATTERN11_3.matcher(str).find()) {//计算语句块
            str = generateSmarty4jCal(str);
            str = generateSmarty4jVarInLogic(str, varList);
            sb.append("sb.append(").append(str.replace("#", "")).append(");").append(LINE_FEED);
		} else if (PATTERN14.matcher(str).find()) {//注释内容不输出
            m = PATTERN14.matcher(str);
            while (m.find()) {
                str = str.replace(m.group(), "");
            }
            generateSmarty4jSingleLine(file, str, sb, varList, includeVarMap);
		} else if (PATTERN11_2.matcher(str).find()) {//打印语句和逻辑运算语句混合，分段处理
            m = PATTERN11_2.matcher(str);
            while (m.find()) {
                generateSmarty4jSingleLine(file, m.group(1), sb, varList, includeVarMap);
                generateSmarty4jSingleLine(file, m.group(2), sb, varList, includeVarMap);
                generateSmarty4jSingleLine(file, m.group(3), sb, varList, includeVarMap);
            }
		} else {//普通打印语句块
            str = str.replace("\"", "\\\"");
            str = str.replace("[#", ";mlt;#").replace("#]", "#;mgt;");
            generateSmarty4jVarInPrint(str, varList, sb);
            if (!str.trim().endsWith("=")) {
                sb.append("sb.append(\"\\r\\n\");").append(LINE_FEED);
            }
        }
    }

    /**
	 * 转换Smarty4j文件中的比较符号
	 * @param str
	 * @return
	 */
    private static String generateSmarty4jCompare(String str) {
        Matcher m = PATTERN11.matcher(str);
        while (m.find()) {
            String compare = "O2oUtil.compare(" + m.group(1) + ",\"" + m.group(2) + "\"," + m.group(3) + ")";
            str = str.replace(m.group(), compare);
        }
        return str;
    }

    /**
	 * Smarty4j文件中的计算符号
	 * @param str
	 * @return
	 */
    private static String generateSmarty4jCal(String str) {
        Matcher m = PATTERN11_1.matcher(str);
        if (m.find()) {
            if (!m.group(1).startsWith("O2oUtil.parseFloat")) {
                str = str.replace(m.group(1), "O2oUtil.parseFloat(" + m.group(1) + ")");
            }
            if (!m.group(2).startsWith("O2oUtil.parseFloat")) {
                str = str.replace(m.group(2), "O2oUtil.parseFloat(" + m.group(2) + ")");
            }
        }
        return str;
    }

    /**
	 * 转换Smarty4j文件打印语句块中的变量 #$a.b.c#
	 * @param str
	 * @param varList 在本文件的所有语句块中定义的变量集合
	 * @return
	 */
    private static void generateSmarty4jVarInPrint(String str, final List<String> varList, final StringBuilder sb) {
        Matcher m = PATTERN5.matcher(str);
        if (!m.find()) {
            sb.append("sb.append(\"").append(str).append("\");").append(LINE_FEED);
            return;
        }
        str = str.replace("#", "");
        List<String> tmpVarList = new ArrayList<>();
        m = PATTERN6.matcher(str);
        while (m.find()) {
            if (m.group().startsWith("$")) {
                String tmpStr = "\\" + m.group();
                tmpStr = tmpStr.replace("[", "\\[").replace("]", "\\]").replace(".", "\\.");
				str = str.replaceFirst(tmpStr, "%s");//把语句块中的变量变为占位符
                tmpVarList.add(m.group().substring(1));
            }
        }
        int i = 0;
        for (String s : str.split("%s")) {
            sb.append("sb.append(\"").append(s.replace(";mlt;", "[").replace(";mgt;", "]")).append("\");").append(LINE_FEED);
            if (i < tmpVarList.size()) {
                String var = tmpVarList.get(i++);
				if (var.contains(".")) {//变量为嵌套变量
					String var1 = var.substring(0, var.indexOf("."));//获取第一层变量
					if (var1.contains("[") && var1.contains("]")) {//第一层变量是数组或者List，拆分为嵌套变量
                        String var2 = var1.substring(0, var1.indexOf("["));
						if (!varList.contains(var2)) {//拆分后的第一层的变量不属于自定义变量
                            var2 = "context.get(\"" + var2 + "\")";
                        }
                        var1 = "O2oUtil.getSmarty4jProperty(" + var2 + ",\"" + var1.substring(var1.indexOf("[")) + "\")";
					} else if (!varList.contains(var1)) {//第一层的变量不属于自定义变量
                        var1 = "context.get(\"" + var1 + "\")";
                    }
                    var = "O2oUtil.getSmarty4jPropertyNil(" + var1 + ",\"" + var.substring(var.indexOf(".") + 1) + "\")";
				} else if (var.contains("[") && var.contains("]")) {//变量是数组或者List，拆分为嵌套变量
					String var1 = var.substring(0, var.indexOf("["));//获取第一层变量
					if (!varList.contains(var1)) {//第一层的变量不属于自定义变量
                        var1 = "context.get(\"" + var1 + "\")";
                    }
                    var = "O2oUtil.getSmarty4jPropertyNil(" + var1 + ",\"" + var.substring(var.indexOf("[")) + "\")";
                } else {
					if (!varList.contains(var)) {//变量不属于自定义变量
                        var = "context.get(\"" + var + "\")";
                    }
                }
                sb.append("sb.append(").append(var).append(");").append(LINE_FEED);
            }
        }
    }

    /**
	 * 转换Smarty4j文件逻辑语句块中的变量 $a.b.c
	 * @param str
	 * @param varList 在本文件的所有语句块中定义的变量集合
	 * @return
	 */
    private static String generateSmarty4jVarInLogic(String str, final List<String> varList) {
        Matcher m = PATTERN6.matcher(str);
        List<String> tmpVarList = new ArrayList<>();
        List<String> tmpVarList1 = new ArrayList<>();
        while (m.find()) {
            if (m.group().startsWith("$")) {
				str = str.replaceFirst("\\" + m.group(), "%s");//把语句块中的变量变为占位符
                tmpVarList.add(m.group().substring(1));
            }
        }
		if (!tmpVarList.isEmpty()) {//处理该语句块中的变量
            for (String var : tmpVarList) {
				if (var.contains(".")) {//变量为嵌套变量
					String var1 = var.substring(0, var.indexOf("."));//获取第一层变量
					if (var1.contains("[") && var1.contains("]")) {//第一层变量是数组或者List，拆分为嵌套变量
                        String var2 = var1.substring(0, var1.indexOf("["));
						if (!varList.contains(var2)) {//拆分后的第一层的变量不属于自定义变量
                            var2 = "context.get(\"" + var2 + "\")";
                        }
                        var1 = "O2oUtil.getSmarty4jProperty(" + var2 + ",\"" + var1.substring(var1.indexOf("[")) + "\")";
					} else if (!varList.contains(var1)) {//第一层的变量不属于自定义变量
                        var1 = "context.get(\"" + var1 + "\")";
                    }
                    var = "O2oUtil.getSmarty4jProperty(" + var1 + ",\"" + var.substring(var.indexOf(".") + 1) + "\")";
				} else if (var.contains("[") && var.contains("]")) {//变量是数组或者List，拆分为嵌套变量
					String var1 = var.substring(0, var.indexOf("["));//获取第一层变量
					if (!varList.contains(var1)) {//第一层的变量不属于自定义变量
                        var1 = "context.get(\"" + var1 + "\")";
                    }
                    var = "O2oUtil.getSmarty4jProperty(" + var1 + ",\"" + var.substring(var.indexOf("[")) + "\")";
                } else {
					if (!varList.contains(var)) {//变量不属于自定义变量
                        var = "context.get(\"" + var + "\")";
                    }
                }
                tmpVarList1.add(var);
            }
        }
        return String.format(str, tmpVarList1.toArray());
    }
}
