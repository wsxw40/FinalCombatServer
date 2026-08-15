package com.pearl.fcw.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.gm.pojo.WGmTransaction;
import com.pearl.fcw.gm.pojo.WSysItem;

/**
 * 代码生成工具
 */
public class CodeGenerateUtil {
    private static Logger logger = LoggerFactory.getLogger(CodeGenerateUtil.class);

    public static void main(String[] args) throws Exception {
        //生成jsp页面代码的示例
        //        System.out.println(generateItemJsp(WSysCharacter.class, "id", "name"));
        //        System.out.println(generateItemsJs(WSysItem.class, "id", "name", "displayName", "description"));
        //        System.out.println(generateItemJsp(WSysLevel.class, "id", "name", "displayName"));
        //        System.out.println(generateItemJsp(WSysCharacter.class, "id", "name"));
        System.out.println(generateItemsJs(WGmTransaction.class));
    }

    /**
     * 生成i18n文件部分重复代码
     * @param clazz
     * @param ingoreFields
     * @return
     */
    public static String generateItemI18n(Class<? extends DmModel> clazz, String... ingoreFields) {
        String modelName = StringUtil.toLowerCase(clazz.getSimpleName().substring(1), 0, 1) + "s";
        StringBuilder sb = new StringBuilder();
        List<Field> fs = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        List<String> ingoreFieldList = new ArrayList<>(Arrays.asList(ingoreFields));
        fs.removeIf(p -> "serialVersionUID".equals(p.getName()) || "isDeleted".equals(p.getName()) || ingoreFieldList.contains(p.getName()));
        for (Field f : fs) {
            sb.append("web.gm.").append(modelName).append(".col.").append(f.getName()).append(".title=").append(f.getName()).append("\n");
            sb.append("web.gm.").append(modelName).append(".col.").append(f.getName()).append(".toolTips=").append(f.getName()).append("\n");
        }
        return sb.toString();
    }

    /**
     * 生成详情页面的部分jsp重复代码
     * @return
     */
    public static String generateItemJsp(Class<? extends DmModel> clazz, String... ingoreFields) {
        String modelName = StringUtil.toLowerCase(clazz.getSimpleName().substring(1), 0, 1) + "s";
        StringBuilder sb = new StringBuilder();
        int i = 0;
        List<Field> fs = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        List<String> ingoreFieldList = new ArrayList<>(Arrays.asList(ingoreFields));
        fs.removeIf(p -> "serialVersionUID".equals(p.getName()) || "isDeleted".equals(p.getName()) || ingoreFieldList.contains(p.getName()));
        for (Field f : fs) {
            if (true) {
                //                String otherName = StringUtil.toLowerCase(f.getName(), 1, 1);
                if (i % 2 == 0) {
                    sb.append("<div class=\"form-group\">").append("\n");
                }

                sb.append("\t").append("<label for=\"").append(f.getName()).append("\" class=\"col-sm-2 control-label\"><fmt:message key=\"web.gm.").append(modelName).append(".col.")
                        .append(f.getName()).append(".title\"/></label>").append("\n");

                sb.append("\t").append("<div class=\"col-sm-4\" title=\"<fmt:message key=\"web.gm.").append(modelName).append(".col.").append(f.getName()).append(".toolTips\"/>\">").append("\n");

                sb.append("\t\t").append("<input class=\"form-control\" id=\"").append(f.getName()).append("\" ng-model=\"").append(f.getName()).append("\"  name=\"").append(f.getName()).append("\"")
                        .append("placeholder=\"<fmt:message key=\"web.gm.").append(modelName).append(".col.").append(f.getName()).append(".toolTips\"/>\" ");
                if (f.getType().equals(Long.class) || f.getType().equals(Integer.class) || f.getType().equals(Short.class) || f.getType().equals(Byte.class) || f.getType().equals(Boolean.class)) {
                    sb.append(" type=\"number\"");
                } else if (f.getType().equals(Float.class) || f.getType().equals(Double.class)) {
                    sb.append(" type=\"number\"").append(" step=\"0.0001\"");
                }
                sb.append("/>").append("\n");

                sb.append("\t").append("</div>").append("\n");

                if (i % 2 != 0 || i == fs.size() - 1) {
                    sb.append("</div>").append("\n");
                }

                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 生成列表页面的部分重复代码
     * @return
     */
    public static String generateItemsJsp(Class<? extends DmModel> clazz, String... ingoreFields) {
        String modelName = StringUtil.toLowerCase(clazz.getSimpleName().substring(1), 0, 1) + "s";
        StringBuilder sb = new StringBuilder();
        List<Field> fs = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        List<String> ingoreFieldList = new ArrayList<>(Arrays.asList(ingoreFields));
        fs.removeIf(p -> "serialVersionUID".equals(p.getName()) || "isDeleted".equals(p.getName()) || ingoreFieldList.contains(p.getName()));
        if (clazz.equals(WSysItem.class)) {//因字段过多，列表中忽略这些指定的字段，保证浏览器显示速度
            fs.removeIf(p -> p.getName().startsWith("w") || p.getName().startsWith("team") || p.getName().startsWith("gunPro"));
        }
        for (Field f : fs) {
            sb.append("<th property=\"").append(f.getName()).append("\" title=\"<fmt:message key=\"web.gm.").append(modelName).append(".col.").append(f.getName()).append(".toolTips\" />\">")
                    .append("\n");
            sb.append("\t").append("<fmt:message key=\"web.gm.").append(modelName).append(".col.").append(f.getName()).append(".title\" />").append("\n");
            sb.append("</th>").append("\n");
        }
        return sb.toString();
    }

    /**
     * 生成列表JS的部分重复代码
     * @param clazz
     * @param ingoreFields
     * @return
     */
    public static String generateItemsJs(Class<? extends DmModel> clazz, String... ingoreFields) {
        StringBuilder sb = new StringBuilder();
        List<Field> fs = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
        List<String> ingoreFieldList = new ArrayList<>(Arrays.asList(ingoreFields));
        fs.removeIf(p -> "serialVersionUID".equals(p.getName()) || "isDeleted".equals(p.getName()) || ingoreFieldList.contains(p.getName()));
        if (clazz.equals(WSysItem.class)) {//因字段过多，列表中忽略这些指定的字段，保证浏览器显示速度
            fs.removeIf(p -> p.getName().startsWith("w") || p.getName().startsWith("team") || p.getName().startsWith("gunPro"));
        }
        for (Field f : fs) {
            sb.append("{data:'").append(f.getName()).append("'},").append("\n");
        }
        return sb.toString();
    }
}
