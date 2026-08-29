package com.pearl.fcw.core.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.BaseEntity;



/**
 * 顶层实例代理。
 * @param <T> 被代理类类型
 */
public class EntityProxy<T extends BaseEntity> extends BeanProxy<T> {

    public EntityProxy(Object element, Proxy<?> owner, Supplier<T> getter, Consumer<T> setter) {
        super(element, owner, getter, setter);
    }

    public EntityProxy(Object element, Proxy<?> owner, T instance) {
        super(element, owner, instance);
    }

    public EntityProxy(T instance, OperationListener listener) {
        super(instance, listener);
    }

    public EntityProxy(T instance) {
        super(instance, new OperationListener());
    }

}
