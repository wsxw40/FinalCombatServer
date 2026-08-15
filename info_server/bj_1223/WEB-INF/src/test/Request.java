package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.pearl.o2o.utils.BinaryOut;

public abstract class Request {

    protected int rpc;
    protected int userData;
    protected String url;

    public Request(String url) {
        this.url = url;
    }

    public abstract byte getType();

    public int getRpc() {
        return rpc;
    }

    protected void setRpc(int rpc) {
        this.rpc = rpc;
    }

    public int getUserData() {
        return userData;
    }

    protected void setUserData(int userData) {
        this.userData = userData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] encode() {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryOut writer = new BinaryOut(baos);

        try {
            writer.writeByte(this.getType());
            writer.writeInt(this.getRpc());
            writer.writeString(this.getUrl());
            writer.writeInt(this.getUserData());
            writerParameters(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }

    protected abstract void writerParameters(BinaryOut writer) throws IOException;


}
