package com.pearl.fcw.core.pojo.proxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.proxy.BeanProxy.Constructor;

/**
 * List代理。
 * 当前仅支持内部元素为{@link BeanProxy}。
 * @param <T> 被代理类类型
 * @param <P> List中元素的代理类型
 */
public class ListProxy<T, P extends Proxy<T>> extends Proxy<List<T>> {

    private final Supplier<List<T>> getter;
    private final Consumer<List<T>> setter;
    private final Constructor<T, P> constructor;

    private final Map<Integer, P> cache = new HashMap<>();

    public ListProxy(Object element, Proxy<?> owner, Supplier<List<T>> getter, Consumer<List<T>> setter, Constructor<T, P> constructor) {
        super(element, owner);
        this.getter = getter;
        this.setter = setter;
        this.constructor = constructor;
    }

    public List<T> get() {
        return getter.get();
    }

    public void set(List<T> value) {
        cache.clear();
        setter.accept(value);
        listener.onSet(this.getPath(), value);
    }

    /**
     * 封装Value为代理类型。注意：在该返回List上的增、删、改操作（如：add、remove）均不会同步至持久层。
     * 该返回值仅能用于读取，但对返回值内的元素进行修改是允许的。
     * @return 值为代理的List
     */
    public List<P> fetch() {
        int size = get().size();
        List<P> pl = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            pl.add(get(i));
        }
        return pl;
    }

    public P get(int i) {
        P p = cache.get(i);
        if (p == null) {
            T t = get().get(i);
            p = constructor.newInstance(i, this, t);
            if (t != null) {
                cache.put(i, p);
            }
        }
        return p;
    }

    public boolean add(T value) {
        boolean r = get().add(value);
        listener.onAdd(this.getPath(), value);
        return r;
    }

    public boolean addAll(Collection<T> c) {
        boolean r = get().addAll(c);
        listener.onAdd(this.getPath(), c.toArray());
        return r;
    }

    public void remove(int i) {
        cache.remove(i);
        get().remove(i);
        listener.onRemove(this.makeSubPath(i));
    }

    public void removeFirst(Predicate<T> predicate) {
        List<T> l = get();
        for (int i = 0; i < l.size(); i++) {
            if (predicate.test(l.get(i))) {
                remove(i);
                return;
            }
        }
    }

    public int size() {
        return get().size();
    }

    public boolean isEmpty() {
        return get().isEmpty();
    }

    public boolean contains(T value) {
        return get().contains(value);
    }

    public void forEach(Consumer<P> action) {
        for (int i = 0; i < get().size(); i++) {
            action.accept(get(i));
        }
    }

    public List<P> filter(Predicate<T> predicate) {
        return sfilter(predicate).collect(Collectors.toList());
    }

    public Stream<P> sfilter(Predicate<T> predicate) {
        List<T> l = get();
        return IntStream.range(0, l.size()).filter(i -> predicate.test(l.get(i))).mapToObj(this::get);
    }

    public P findFirst(Predicate<T> predicate) {
        return sfilter(predicate).findFirst().orElse(null);
    }

    public P getFirst() {
        return get().isEmpty() ? null : get(0);
    }

    public P getLast() {
        int i = get().size() - 1;
        return i < 0 ? null : get(i);
    }

    public P addAndGet(T value) {
        add(value);
        return getLast();
    }

}
