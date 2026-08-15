package com.pearl.fcw.info.core.persistence.serializer;

import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class KryoSerializer implements Serializer {

    private final GenericObjectPool<Kryo> pool;

    public KryoSerializer(KryoFactory factory) {
        GenericObjectPoolConfig cfg = new GenericObjectPoolConfig();
        cfg.setMaxTotal(100);
        cfg.setMinIdle(10);
        cfg.setMaxWaitMillis(-1);
        cfg.setMinEvictableIdleTimeMillis(600000);
        pool = new GenericObjectPool<>(factory, cfg);
    }

    public Kryo getKryo() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void returnKryo(final Kryo kryo) {
        pool.returnObject(kryo);
    }

    public void callConsumer(Consumer<Kryo> func) {
        Kryo k = getKryo();
        try {
            func.accept(k);
        } finally {
            returnKryo(k);
        }
    }

    public <T> T callFunction(Function<Kryo, T> func) {
        Kryo k = getKryo();
        try {
            return func.apply(k);
        } finally {
            returnKryo(k);
        }
    }

    @Override
    public byte[] serialize(final Object obj) {
        return callFunction(kyro -> {
            // 4K - 1M
            try (Output o = new Output(4096, 1048576)) {
                kyro.writeClassAndObject(o, obj);
                return o.toBytes();
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(byte[] in) {
        Object o = callFunction(kyro -> {
            try (Input i = new Input(in)) {
                return kyro.readClassAndObject(i);
            }
        });
        return (T) o;
    }
}
