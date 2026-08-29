package test;

import java.io.IOException;

import com.pearl.o2o.utils.BinaryChannelBuffer;



public class Response {

    protected byte type;
    protected int rpc;
    protected int userData;
    protected byte[] bodyBytes;
    public Response(byte[] message) {

        BinaryChannelBuffer reader = new BinaryChannelBuffer(message);
        try {
            this.type = reader.readByte();
            this.rpc = reader.readInt();
            this.userData = reader.readInt();
            byte[] bodyBytes = new byte[reader.readableBytes()];
            reader.readBytes(bodyBytes);
            this.bodyBytes=bodyBytes;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte getType() {
        return type;
    }

    public int getRpc() {
        return rpc;
    }

    public int getUserData() {
        return userData;
    }
    public  byte[] getResponseBody(){
    	return bodyBytes;
    }

}
