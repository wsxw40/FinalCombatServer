package com.pearl.fcw.info.core.persistence.serializer;

public interface Serializer {

    byte[] serialize(final Object obj);

    <T> T deserialize(final byte[] in);

}
