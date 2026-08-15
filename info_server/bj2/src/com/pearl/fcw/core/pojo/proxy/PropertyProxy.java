package com.pearl.fcw.core.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 基本类型字段代理。
 * @param <T> 被代理字段类型
 */
public class PropertyProxy<T> extends Proxy<T> {

    private final Supplier<T> getter;
    private final Consumer<T> setter;

    public PropertyProxy(Object element, Proxy<?> owner, Supplier<T> getter, Consumer<T> setter) {
        super(element, owner);
        this.getter = getter;
        this.setter = setter;
    }

    public T get() {
        return getter.get();
    }

    /**
	 * 有监听器设值
	 * @param value
	 */
    public void set(T value) {
        setter.accept(value);
        listener.onUpdate(this.getPath(), value);
    }

    /**
	 * 无监听器设值
	 * @param value
	 */
    protected void setWithNolistener(T value) {
        setter.accept(value);
    }

    public boolean isNull() {
		return getter.get() == null;
    }

    public boolean eq(T t) {
        T self = get();
        return self != null ? self.equals(t) : t == null;
    }

    public boolean ne(T t) {
        return !eq(t);
    }

}
