
package com.pearl.fcw.info.core.network;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Objects;

import javax.annotation.Generated;

import io.protostuff.GraphIOUtil;
import io.protostuff.Input;
import io.protostuff.Message;
import io.protostuff.Output;
import io.protostuff.Schema;
import io.protostuff.UninitializedMessageException;
@Generated("java_bean")
public final class Header implements Externalizable, Message<Header>, Schema<Header>
{

    public static Schema<Header> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static Header getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final Header DEFAULT_INSTANCE = new Header();


    private PacketType type;
    private String methodName;
    private long uid;
    private long pid;
    private int code;
    private int rpcId;
    private int packetId;

    public Header()
    {

    }

    public Header(
        PacketType type,
        String methodName
    )
    {
        this.type = type;
        this.methodName = methodName;
    }

    // getters and setters

    // type

    public PacketType getType()
    {
        return type;
    }


    public void setType(PacketType type)
    {
        this.type = type;
    }

    // methodName

    public String getMethodName()
    {
        return methodName;
    }


    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }

    // uid

    public long getUid()
    {
        return uid;
    }


    public void setUid(long uid)
    {
        this.uid = uid;
    }

    // pid

    public long getPid()
    {
        return pid;
    }


    public void setPid(long pid)
    {
        this.pid = pid;
    }

    // code

    public int getCode()
    {
        return code;
    }


    public void setCode(int code)
    {
        this.code = code;
    }

    // rpcId

    public int getRpcId()
    {
        return rpcId;
    }


    public void setRpcId(int rpcId)
    {
        this.rpcId = rpcId;
    }

    // packetId

    public int getPacketId()
    {
        return packetId;
    }


    public void setPacketId(int packetId)
    {
        this.packetId = packetId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final Header that = (Header) obj;
        return
                Objects.equals(this.type, that.type) &&
                Objects.equals(this.methodName, that.methodName) &&
                Objects.equals(this.uid, that.uid) &&
                Objects.equals(this.pid, that.pid) &&
                Objects.equals(this.code, that.code) &&
                Objects.equals(this.rpcId, that.rpcId) &&
                Objects.equals(this.packetId, that.packetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, methodName, uid, pid, code, rpcId, packetId);
    }

    @Override
    public String toString() {
        return "Header{" +
                    "type=" + type +
                    ", methodName=" + methodName +
                    ", uid=" + uid +
                    ", pid=" + pid +
                    ", code=" + code +
                    ", rpcId=" + rpcId +
                    ", packetId=" + packetId +
                '}';
    }
    // java serialization

    @Override
    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, this);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, this);
    }

    // message method

    @Override
    public Schema<Header> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    @Override
    public Header newMessage()
    {
        return new Header();
    }

    @Override
    public Class<Header> typeClass()
    {
        return Header.class;
    }

    @Override
    public String messageName()
    {
        return Header.class.getSimpleName();
    }

    @Override
    public String messageFullName()
    {
        return Header.class.getName();
    }

    @Override
    public boolean isInitialized(Header message)
    {
        return
            message.type != null
            && message.methodName != null;
    }

    @Override
    public void mergeFrom(Input input, Header message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;
                case 1:
                    message.type = PacketType.valueOf(input.readEnum());
                    break;

                case 2:
                    message.methodName = input.readString();
                    break;
                case 4:
                    message.uid = input.readSInt64();
                    break;
                case 5:
                    message.pid = input.readSInt64();
                    break;
                case 6:
                    message.code = input.readSInt32();
                    break;
                case 7:
                    message.rpcId = input.readUInt32();
                    break;
                case 8:
                    message.packetId = input.readUInt32();
                    break;
                default:
                    input.handleUnknownField(number, this);
            }
        }
    }


    @Override
    public void writeTo(Output output, Header message) throws IOException
    {
        if(message.type == null)
            throw new UninitializedMessageException(message);
        output.writeEnum(1, message.type.number, false);

        if(message.methodName == null)
            throw new UninitializedMessageException(message);
        output.writeString(2, message.methodName, false);

        if(message.uid != 0)
            output.writeSInt64(4, message.uid, false);

        if(message.pid != 0)
            output.writeSInt64(5, message.pid, false);

        if(message.code != 0)
            output.writeSInt32(6, message.code, false);

        if(message.rpcId != 0)
            output.writeUInt32(7, message.rpcId, false);

        if(message.packetId != 0)
            output.writeUInt32(8, message.packetId, false);
    }

    @Override
    public String getFieldName(int number)
    {
        return Integer.toString(number);
    }

    @Override
    public int getFieldNumber(String name)
    {
        return Integer.parseInt(name);
    }


}
