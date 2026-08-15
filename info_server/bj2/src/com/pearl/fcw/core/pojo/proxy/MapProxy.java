package com.pearl.fcw.core.pojo.proxy;

import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.proxy.BeanProxy.Constructor;


/**
 * Map代理类。
 * 当前仅支持内部元素为{@link BeanProxy}。
 * @param <K> Key类型
 * @param <V> Value类型
 * @param <P> Map中Value的代理类型
 */
public class MapProxy<K, V, P extends Proxy<V>> extends Proxy<Map<K, V>> {

    private final Supplier<Map<K, V>> getter;
    private final Consumer<Map<K, V>> setter;
    private final Constructor<V, P> constructor;

    private final Map<K, P> cache = new HashMap<>();

    public MapProxy(Object element, Proxy<?> owner, Supplier<Map<K, V>> getter, Consumer<Map<K, V>> setter, Constructor<V, P> constructor) {
        super(element, owner);
        this.getter = getter;
        this.setter = setter;
        this.constructor = constructor;
    }

    public Map<K, V> get() {
        return getter.get();
    }

    public void set(Map<K, V> value) {
        cache.clear();
        setter.accept(value);
        listener.onSet(this.getPath(), value);
    }

    /**
     * 封装Value为代理类型。注意：在该返回Map上的增、删、改操作（如：put、remove）均不会同步至持久层。
     * 该返回值仅能用于读取，但对返回Map的Value元素进行修改是允许的。
     * @return 值为代理的Map
     */
    public Map<K, P> fetch() {
        return get().entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> get(e.getKey())));
    }

    public P get(K key) {
        P p = cache.get(key);
        if (p == null) {
            V v = get().get(key);
            if (v != null) {
                p = constructor.newInstance(key, this, v);
                cache.put(key, p);
            }
        }
        return p;
    }

    public void put(K key, V value) {
        V v = get().put(key, value);
        if (v != null) {
            cache.remove(key);
        }
        listener.onPut(makeSubPath(key), value);
    }

    public void remove(K key) {
        V v = get().remove(key);
        if (v != null) {
            cache.remove(key);
            listener.onRemove(makeSubPath(key));
        }
    }

    /**
     * 移除keys包含之外的主键。MapProxy不能在同一个事务内实现clear和add
     * @param keys
     */
    @SuppressWarnings("unchecked")
    public void removeExcept(List<K> keys) {
        for (Object k : get().keySet().toArray()) {
            if (!keys.contains(k)) {
                remove((K) k);
            }
        }
    }

    public int size() {
        return get().size();
    }

    public boolean isEmpty() {
        return get().isEmpty();
    }

    public boolean containsKey(K key) {
        return get().containsKey(key);
    }

    public void clear() {
        set(new LinkedHashMap<>());
    }

    public void forEach(BiConsumer<K, P> action) {
        get().forEach((k, v) -> {
            P p = get(k);
            action.accept(k, p);
        });
    }

    public Set<Entry<K, P>> filter(Predicate<Entry<K, V>> predicate) {
        return sfilter(predicate).collect(Collectors.toSet());
    }

    public Set<Entry<K, P>> filter(BiPredicate<K, V> predicate) {
        return sfilter(predicate).collect(Collectors.toSet());
    }

    public List<P> filterValue(Predicate<V> predicate) {
        return sfilterValue(predicate).collect(Collectors.toList());
    }

    public Stream<Entry<K, P>> sfilter(Predicate<Entry<K, V>> predicate) {
        return get().entrySet().stream().filter(predicate)
                .map(e -> new SimpleImmutableEntry<>(e.getKey(), get(e.getKey())));
    }

    public Stream<Entry<K, P>> sfilter(BiPredicate<K, V> predicate) {
        return get().entrySet().stream().filter(e -> predicate.test(e.getKey(), e.getValue()))
                .map(e -> new SimpleImmutableEntry<>(e.getKey(), get(e.getKey())));
    }

    public Stream<P> sfilterValue(Predicate<V> predicate) {
        return get().entrySet().stream().filter(e -> predicate.test(e.getValue())).map(e -> get(e.getKey()));
    }

    public P findValueFirst(Predicate<V> predicate) {
        return sfilterValue(predicate).findFirst().orElse(null);
    }

}
