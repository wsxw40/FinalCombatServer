package com.pearl.fcw.core.cache;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoPool;

public class KryoSerializer implements Serializer {

    private int defaultBufferSize = 4096;
    private int maxBufferSize = 1024 * 1024;

    private final KryoPool pool;
    private final Queue<Output> buffers = new ConcurrentLinkedDeque<>();

    public KryoSerializer(KryoFactory factory) {
        pool = new KryoPool.Builder(factory).softReferences().build();
    }

    public void setDefaultBufferSize(int defaultBufferSize) {
        this.defaultBufferSize = defaultBufferSize;
    }

    public void setMaxBufferSize(int maxBufferSize) {
        this.maxBufferSize = maxBufferSize;
    }

    public Output borrowOutput() {
        Output output = buffers.poll();
        if (output != null) {
            return output;
        }
        return new Output(defaultBufferSize, maxBufferSize);
    }

    public void releaseOutput(Output output) {
        if (output.position() > defaultBufferSize) {
            return;
        }
        output.clear();
        buffers.offer(output);
    }

    @Override
    public byte[] serialize(final Object obj) {
        return pool.run(kryo -> {
            Output o = borrowOutput();
            try {
                kryo.writeClassAndObject(o, obj);
                return o.toBytes();
            } finally {
                releaseOutput(o);
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T deserialize(final byte[] in) {
        if (in == null) {
            return null;
        }
        return (T) pool.run(kryo -> {
            try (Input i = new Input(in)) {
                return kryo.readClassAndObject(i);
            }
        });
    }

    public <T> T copy(T t) {
        return pool.run(kryo -> kryo.copy(t));
    }

    public <T> T copyShallow(T t) {
        return pool.run(kryo -> kryo.copyShallow(t));
    }

}
