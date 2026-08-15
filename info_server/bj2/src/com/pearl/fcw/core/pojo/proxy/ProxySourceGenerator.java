package com.pearl.fcw.core.pojo.proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import com.pearl.fcw.core.pojo.DmExtModel;
import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.lobby.pojo.ParamObject;
import com.pearl.fcw.lobby.pojo.WPlayerInfo;
import com.pearl.fcw.lobby.pojo.proxy.ProxyParamObject;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.utils.ConfigurationUtil;

public class ProxySourceGenerator {
    private static Logger logger = LoggerFactory.getLogger(ProxySourceGenerator.class);
    private static final String LINE_FEED = "\r\n";
    private static final String TAB_FEED = "\t";
    private static final String TARGET_DIR_PATH = ConfigurationUtil.APP_ROOT_PATH + "/src/com/pearl/fcw/lobby/pojo/proxy/";

    public static void main(String[] args) throws Exception {
        //        generate(ParamObject.class, TARGET_DIR_PATH);
		generate(WPlayerInfo.class, TARGET_DIR_PATH);
        //        generate();
    }

    public static void generate() throws Exception {
        Set<Class<?>> classes = getDmmodelClassList().stream().filter(p -> {
            Schema schema = p.getAnnotation(GoSchema.class).type();
            return schema.equals(Schema.MAIN) || schema.equals(Schema.EXT);
        }).collect(Collectors.toSet());
        for (Class<?> c : classes) {
            generate(c, TARGET_DIR_PATH);
        }
        logger.info("Source generation completed");
    }

    public static void generate(Class<?> clazz, String dirPath) throws Exception {
        StringBuilder sb = new StringBuilder();
        generatePakage(clazz, sb);
        String proxyClassName = "Proxy" + clazz.getSimpleName();
        generateClass(clazz, sb, proxyClassName);
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File target = new File(dirPath + proxyClassName + ".java");
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
            logger.info(proxyClassName + ".java creating is end.");
        }
    }

    public static void generatePakage(Class<?> clazz, StringBuilder sb) throws Exception {
        sb.append("package ").append(clazz.getPackage().getName()).append(".proxy;").append(LINE_FEED);
        sb.append(LINE_FEED);
        sb.append("import java.util.function.Supplier;").append(LINE_FEED);
        sb.append("import java.util.function.Consumer;").append(LINE_FEED);
        sb.append("import com.pearl.fcw.core.pojo.proxy.*;").append(LINE_FEED);
        sb.append("import ").append(clazz.getName()).append(";").append(LINE_FEED);
        sb.append(LINE_FEED);
    }

    public static void generateClass(Class<?> clazz, StringBuilder sb, String proxyClassName) throws Exception {
        //implements java.io.Serializable
        if (clazz.getDeclaredAnnotation(GoSchema.class).type().equals(Schema.NEST)) {
            sb.append("public class ").append(proxyClassName).append("<T> extends BeanProxy<").append(clazz.getSimpleName()).append("<T>> {").append("\r\n");
        } else {
            sb.append("public class ").append(proxyClassName).append(" extends EntityProxy<").append(clazz.getSimpleName()).append("> {").append("\r\n");
        }
        StringBuilder sb1 = new StringBuilder();
        generateProperties(clazz, sb1);
        generateConstructor(clazz, sb1, proxyClassName);
        sb.append(sb1.toString());
        sb.append("}");
    }

    public static void generateProperties(Class<?> clazz, StringBuilder sb) throws Exception {
        Class<?> clazz1 = clazz;
        Map<Field, String> fieldMap = new LinkedHashMap<>();
		//获取数据库字段
        while (null != clazz1 && !clazz1.equals(DmModel.class) && !clazz1.equals(DmExtModel.class)) {
            for (Field f : clazz1.getDeclaredFields()) {
                if (f.getModifiers() == Modifier.PRIVATE) {
                    fieldMap.put(f, getProxyTypeStr(f));
                }
            }
            clazz1 = clazz1.getSuperclass();
        }
		//生成字段
        for (Field f : fieldMap.keySet()) {
            sb.append(TAB_FEED).append("private").append(fieldMap.get(f)).append(f.getName()).append(";").append(LINE_FEED);
        }
        sb.append(LINE_FEED);
		//生成属性方法
        for (Field f : fieldMap.keySet()) {
            sb.append(TAB_FEED).append("public").append(fieldMap.get(f)).append(f.getName()).append("() {").append(LINE_FEED);
            sb.append(TAB_FEED).append(TAB_FEED).append("return ").append(f.getName()).append(";").append(LINE_FEED);
            sb.append(TAB_FEED).append("}").append(LINE_FEED);
            sb.append(LINE_FEED);
        }
		//生成初始化方法
        sb.append(TAB_FEED).append("@Override").append(LINE_FEED);
        sb.append(TAB_FEED).append("public void initFields() {").append(LINE_FEED);
        sb.append(TAB_FEED).append(TAB_FEED).append(clazz.getSimpleName()).append(" instance = get();").append(LINE_FEED);
        for (Field f : fieldMap.keySet()) {
            String getter = "instance::get" + StringUtil.toPascal(f.getName());
            String setter = "instance::set" + StringUtil.toPascal(f.getName());
			if (DmModel.PATTERN_ONLY_FIRST_ONE_LOWER.matcher(f.getName()).find()) {//get，set的特殊规则：字段名只有首字母一个小写
                getter = "instance::get" + f.getName();
                setter = "instance::set" + f.getName();
            }
            if (fieldMap.get(f).startsWith(" ListProxy")) {
                sb.append(TAB_FEED).append(TAB_FEED).append(f.getName()).append(" = new ").append(fieldMap.get(f)).append("(\"").append(f.getName()).append("\", this, ").append(getter).append(", ")
                        .append(setter).append(", ").append(ProxyParamObject.class.getName()).append("::new);").append(LINE_FEED);
            } else if (fieldMap.get(f).startsWith(" MapProxy")) {
                sb.append(TAB_FEED).append(TAB_FEED).append(f.getName()).append(" = new ").append(fieldMap.get(f)).append("(\"").append(f.getName()).append("\", this, ").append(getter).append(", ")
                        .append(setter).append(", ").append(ProxyParamObject.class.getName()).append("::new);").append(LINE_FEED);
            } else if (fieldMap.get(f).startsWith(" DateProxy")) {
                sb.append(TAB_FEED).append(TAB_FEED).append(f.getName()).append(" = new ").append(fieldMap.get(f)).append("(\"").append(f.getName()).append("\", this, ").append(getter).append(", ")
                        .append(setter).append(");").append(LINE_FEED);
            } else {
                sb.append(TAB_FEED).append(TAB_FEED).append(f.getName()).append(" = new ").append(fieldMap.get(f)).append("(\"").append(f.getName()).append("\", this, ").append(getter).append(", ")
                        .append(setter).append(");").append(LINE_FEED);
            }
        }
        sb.append(TAB_FEED).append("}").append(LINE_FEED).append(LINE_FEED);
    }

    public static void generateConstructor(Class<?> clazz, StringBuilder sb, String proxyClassName) throws Exception {
        if (!clazz.getDeclaredAnnotation(GoSchema.class).type().equals(Schema.NEST)) {
            sb.append(TAB_FEED).append("public ").append(proxyClassName).append("(").append(clazz.getSimpleName()).append("  instance) {").append(LINE_FEED);
            sb.append(TAB_FEED).append(TAB_FEED).append("super(instance);").append(LINE_FEED);
            sb.append(TAB_FEED).append("}").append(LINE_FEED);
            sb.append(LINE_FEED);
        }

        sb.append(TAB_FEED).append("public ").append(proxyClassName).append("(Object element, Proxy<?> owner, Supplier<").append(clazz.getSimpleName()).append("> getter,  Consumer<")
                .append(clazz.getSimpleName()).append("> setter) {").append(LINE_FEED);
        sb.append(TAB_FEED).append(TAB_FEED).append("super(element, owner, getter, setter);").append(LINE_FEED);
        sb.append(TAB_FEED).append("}").append(LINE_FEED);
        sb.append(LINE_FEED);

        sb.append(TAB_FEED).append("public ").append(proxyClassName).append("(Object element, Proxy<?> owner, ").append(clazz.getSimpleName()).append(" instance) {").append(LINE_FEED);
        sb.append(TAB_FEED).append(TAB_FEED).append("super(element, owner, instance);").append(LINE_FEED);
        sb.append(TAB_FEED).append("}").append(LINE_FEED);
        sb.append(LINE_FEED);
    }

    public static String getProxyTypeStr(Field f) {
        String typeStr = " PropertyProxy<" + f.getType().getName() + "> ";
        if (f.getType().equals(Byte.class)) {
            typeStr = " ByteProxy ";
        } else if (f.getType().equals(Integer.class)) {
            typeStr = " IntegerProxy ";
        } else if (f.getType().equals(Long.class)) {
            typeStr = " LongProxy ";
        } else if (f.getType().equals(Float.class)) {
            typeStr = " FloatProxy ";
        } else if (f.getType().equals(Double.class)) {
            typeStr = " DoubleProxy ";
        } else if (f.getType().equals(Date.class)) {
            typeStr = " DateProxy<" + f.getType().getName() + "> ";
        } else if (f.getType().equals(List.class)) {
            typeStr = " ListProxy<" + ParamObject.class.getName() + "<T>, " + ProxyParamObject.class.getName() + "<T>> ";
        } else if (f.getType().equals(Map.class)) {
            typeStr = " MapProxy<String, " + ParamObject.class.getName() + "<T>, " + ProxyParamObject.class.getName() + "<T>> ";
        }
        return typeStr;
    }

    public static List<Class<?>> getDmmodelClassList() {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        List<TypeFilter> filters = Arrays.asList(new AnnotationTypeFilter(GoSchema.class));
        scanner.addIncludeFilter((metadataReader, metadataReaderFactory) -> {
            for (TypeFilter f : filters) {
                if (!f.match(metadataReader, metadataReaderFactory)) {
                    return false;
                }
            }
            return true;
        });
        Set<BeanDefinition> bds = scanner.findCandidateComponents("com.pearl.fcw");
        List<Class<?>> classes = bds.stream().map(bd -> {
            try {
                return Class.forName(bd.getBeanClassName());
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toList());
        return classes;
    }
}
