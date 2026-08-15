package com.pearl.fcw.info.core.network;

import java.util.concurrent.Future;

import io.netty.buffer.ByteBuf;
import io.protostuff.Message;

public class Packet {

//    public static final int MIN_PACK_SIZE = 4;          // 最小包长度
    public static final int MIN_PACK_SIZE = 0;          // 最小包长度
    public static final int MAX_PACK_SIZE = 1024 * 3072; // 最大包长度3mb
    public static final int HEADER_MAX_LENGTH = 0xFFFF; // 最大包头长度

    private Header header;
    private ByteBuf body;

    public Packet() {
    }

    public Packet(Header header, ByteBuf body) {
        this.header = header;
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public ByteBuf getBody() {
        return body;
    }

    public void setBody(ByteBuf body) {
        this.body = body;
    }

    public <F extends Future<?>> void operationComplete(F future) throws Exception {
        releaseBody();
    }

    public void releaseBody() {
        if (body != null) {
            body.release();
        }
    }

    public Packet duplicate() {
        Packet p = new Packet();
        p.setHeader(header);
        if (body != null) {
            p.setBody(body.duplicate().retain());
        }
        return p;
    }

    public byte[] extractBody() {
        if (body == null) {
            return null;
        }

        byte[] ba = new byte[body.readableBytes()];
        body.readBytes(ba);
        return ba;
    }

    public <T extends Message<T>> T readAndReleaseBody(T message) {
        byte[] ba = this.extractAndReleaseBody();
        if (ba != null) {
            ProtostuffUtil.mergeFrom(ba, message);
        }
        return message;
    }

    public byte[] extractAndReleaseBody() {
        byte[] ba = extractBody();
        releaseBody();
        return ba;
    }
}
