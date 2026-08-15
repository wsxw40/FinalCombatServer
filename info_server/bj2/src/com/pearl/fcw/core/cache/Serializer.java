package com.pearl.fcw.core.cache;

public interface Serializer {

    byte[] serialize(final Object obj);

    <T> T deserialize(final byte[] in);

}
