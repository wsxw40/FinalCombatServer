package com.pearl.fcw.core.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class IntegerProxy extends PropertyProxy<Integer> {

    public IntegerProxy(Object element, Proxy<?> owner, Supplier<Integer> getter, Consumer<Integer> setter) {
        super(element, owner, getter, setter);
    }

	public void increase(Integer value) throws NullPointerException {
        setWithNolistener(get() + value);
        listener.onIncrease(path, value);
    }

    @Override
    public Integer get() {
        return null == super.get() ? 0 : super.get();
    }

    /**
	 * 代理的原始值是否小于给定值，必须保证两个值均不为null。
	 * @param other 给定值
	 * @return true：当前值小于给定值
	 * @throws NullPointerException 原始值或给定值为null
	 */
    public boolean lt(Integer other) throws NullPointerException {
        return get() < other;
    }

    /**
	 * 代理的原始值是否小于等于给定值，必须保证两个值均不为null。
	 * @param other 给定值
	 * @return true：当前值小于等于给定值
	 * @throws NullPointerException 原始值或给定值为null
	 */
    public boolean le(Integer other) throws NullPointerException {
        return get() <= other;
    }

    /**
	 * 代理的原始值是否大于给定值，必须保证两个值均不为null。
	 * @param other 给定值
	 * @return true：当前值大于给定值
	 * @throws NullPointerException 原始值或给定值为null
	 */
    public boolean gt(Integer other) throws NullPointerException {
        return get() > other;
    }

    /**
	 * 代理的原始值是否大于等于给定值，必须保证两个值均不为null。
	 * @param other 给定值
	 * @return true：当前值大于等于给定值
	 * @throws NullPointerException 原始值或给定值为null
	 */
    public boolean ge(Integer other) throws NullPointerException {
        return get() >= other;
    }

}
