package com.pearl.fcw.info.core.network;

import io.protostuff.LinkedBuffer;
import io.protostuff.Message;
import io.protostuff.ProtobufIOUtil;

public class ProtostuffUtil {

    private static final int DEFAULT_SIZE = 1024 * 4;

    private static final ThreadLocal<LinkedBuffer> buffers = new ThreadLocal<>();

    public static LinkedBuffer getLinkedBuffer() {
        LinkedBuffer lb = buffers.get();
        if (lb == null) {
            lb = LinkedBuffer.allocate(DEFAULT_SIZE);
            buffers.set(lb);
        }
        return lb;
    }

    public static <T extends Message<T>> byte[] toByteArray(T message) {
        LinkedBuffer lb = getLinkedBuffer();
        try {
            return ProtobufIOUtil.toByteArray(message, message.cachedSchema(), getLinkedBuffer());
        } finally {
            lb.clear();
        }
    }

    public static <T extends Message<T>> T mergeFrom(byte[] data, T message) {
        ProtobufIOUtil.mergeFrom(data, message, message.cachedSchema());
        return message;
    }
}
