package com.pearl.fcw.core.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class LongProxy extends PropertyProxy<Long> {

    public LongProxy(Object element, Proxy<?> owner, Supplier<Long> getter, Consumer<Long> setter) {
        super(element, owner, getter, setter);
    }

    @Override
    public Long get() {
        return null == super.get() ? 0L : super.get();
    }

    public void increase(Long value) {
        setWithNolistener(get() + value);
        listener.onIncrease(path, value);
    }

    /**
     * 代理的原始值是否小于给定值，必须保证两个值均不为null。
     * @param other 给定值
     * @return true：当前值小于给定值
     * @throws NullPointerException 原始值或给定值为null
     */
    public boolean lt(Long other) throws NullPointerException {
        return get() < other;
    }

    /**
     * 代理的原始值是否小于等于给定值，必须保证两个值均不为null。
     * @param other 给定值
     * @return true：当前值小于等于给定值
     * @throws NullPointerException 原始值或给定值为null
     */
    public boolean le(Long other) throws NullPointerException {
        return get() <= other;
    }

    /**
     * 代理的原始值是否大于给定值，必须保证两个值均不为null。
     * @param other 给定值
     * @return true：当前值大于给定值
     * @throws NullPointerException 原始值或给定值为null
     */
    public boolean gt(Long other) throws NullPointerException {
        return get() > other;
    }

    /**
     * 代理的原始值是否大于等于给定值，必须保证两个值均不为null。
     * @param other 给定值
     * @return true：当前值大于等于给定值
     * @throws NullPointerException 原始值或给定值为null
     */
    public boolean ge(Long other) throws NullPointerException {
        return get() >= other;
    }

}
