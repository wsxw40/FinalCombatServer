package com.pearl.fcw.core.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 实例代理。
 * @param <T> 被代理类类型
 */
public class BeanProxy<T> extends Proxy<T> {

    private final Supplier<T> getter;
    private final Consumer<T> setter;

    public BeanProxy(Object element, Proxy<?> owner, Supplier<T> getter, Consumer<T> setter) {
        super(element, owner);
        this.getter = getter;
        this.setter = setter;
        this.initFields();
    }
    public BeanProxy(Object element, Proxy<?> owner, T instance) {
        this(element, owner, () -> instance, BeanProxy::unsupportedSetter);
    }

    public BeanProxy(T instance, OperationListener listener) {
        super(listener);
        this.getter = () -> instance;
        this.setter = BeanProxy::unsupportedSetter;
        this.initFields();
    }

    private static <T> void unsupportedSetter(T v) {
        throw new UnsupportedOperationException("该实例不可变，可能由于当前代理为顶层根实例或容器（List、Map）内元素。值为" + v);
    }

    /**
     * 初始化所有字段代理，初始化以及重新设置当前代理实例时，会调用该方法。
     */
    public void initFields() {

    }

    public T get() {
        return getter.get();
    }

    /**
     * 该方法会重新实例化所有属性字段的代理，存在一定损耗。
     * @param value 被代理实例
     */
    public void set(T value) {
        setter.accept(value);
        initFields();
        listener.onSet(this.getPath(), value);
    }

    public boolean isNull() {
        return get() == null;
    }

    @FunctionalInterface
    public interface Constructor<T, P extends Proxy<T>> {

        P newInstance(Object element, Proxy<?> owner, T instance);

    }

}
