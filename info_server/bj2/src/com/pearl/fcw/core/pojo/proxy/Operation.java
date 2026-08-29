package com.pearl.fcw.core.pojo.proxy;

import java.io.Serializable;

public class Operation implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8298526834666863859L;

    public enum Type {
		INCREASE,  //实例内的字段值递增
		UPDATE,     // 修改实例内的字段值
		SET,        // List和Map修改或替换内部实例
		REMOVE,     // List和Map移除内部实例
		ADD,        // List中追加新实例
		PUT,        // Map中放置实例
		VERSION,     // 修改版本号
		NEWINSTANCE //新建实例(只起标识作用，不影响数据写入)
    }

    private final Type type;
    private final String path;
    private final Object value;

    public Operation(Type type, String path) {
        this(type, path, null);
    }

    public Operation(Type type, String path, Object value) {
        this.type = type;
        this.path = path;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Operation [type=" + type + ", path=" + path + ", value=" + value + "]";
    }

}
