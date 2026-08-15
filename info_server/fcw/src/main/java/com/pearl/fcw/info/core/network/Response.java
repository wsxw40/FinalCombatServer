package com.pearl.fcw.info.core.network;

public class Response {

    public static Packet error() {
        Header h = new Header();
        h.setType(PacketType.RESPONSE);
        return new Packet(h, null);
    }

    public static Packet serverBusy() {
        Header h = new Header();
        h.setType(PacketType.RESPONSE);
        return new Packet(h, null);
    }


    public static Packet playerBanned() {
        Header h = new Header();
        h.setType(PacketType.RESPONSE);
        return new Packet(h, null);
    }
}
