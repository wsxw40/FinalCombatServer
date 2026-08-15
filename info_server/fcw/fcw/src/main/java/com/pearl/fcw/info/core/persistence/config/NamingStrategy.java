package com.pearl.fcw.info.core.persistence.config;

public class NamingStrategy {

    public String classToTableName(String className) {
        return convert(className);
    }

    public String propertyToColumnName(String propertyName) {
        return convert(propertyName);
    }

    public String foreignKeyColumnName(String fieldName, String referenceColumnName) {
        return convert(fieldName) + "_" + referenceColumnName;
    }

    public String foreignKeyFieldName(String fieldName, String referenceFieldName) {
        return fieldName + Character.toUpperCase(referenceFieldName.charAt(0)) + referenceFieldName.substring(1);
    }

    protected String convert(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            sb.append(Character.toUpperCase(str.charAt(i)));
            if (i + 1 < str.length() && Character.isUpperCase(str.charAt(i + 1))) {
                sb.append('_');
            }
        }
        return sb.toString();
    }

}
