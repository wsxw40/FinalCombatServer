package com.pearl.fcw.core.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ByteProxy extends PropertyProxy<Byte> {

    public ByteProxy(Object element, Proxy<?> owner, Supplier<Byte> getter, Consumer<Byte> setter) {
        super(element, owner, getter, setter);
    }

    @Override
    public Byte get() {
        return null == super.get() ? (byte) 0 : super.get();
    }

    public void increase(Byte value) {
        setWithNolistener((byte) (get() + value));
        listener.onIncrease(path, value);
    }

    /**
     * 代理的原始值是否小于给定值，必须保证两个值均不为null。
     * @param other 给定值
     * @return true：当前值小于给定值
     * @throws NullPointerException 原始值或给定值为null
     */
    public boolean lt(Byte other) throws NullPointerException {
        return get() < other;
    }

    /**
     * 代理的原始值是否小于等于给定值，必须保证两个值均不为null。
     * @param other 给定值
     * @return true：当前值小于等于给定值
     * @throws NullPointerException 原始值或给定值为null
     */
    public boolean le(Byte other) throws NullPointerException {
        return get() <= other;
    }

    /**
     * 代理的原始值是否大于给定值，必须保证两个值均不为null。
     * @param other 给定值
     * @return true：当前值大于给定值
     * @throws NullPointerException 原始值或给定值为null
     */
    public boolean gt(Byte other) throws NullPointerException {
        return get() > other;
    }

    /**
     * 代理的原始值是否大于等于给定值，必须保证两个值均不为null。
     * @param other 给定值
     * @return true：当前值大于等于给定值
     * @throws NullPointerException 原始值或给定值为null
     */
    public boolean ge(Byte other) throws NullPointerException {
        return get() >= other;
    }

}
