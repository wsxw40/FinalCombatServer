package test;

import java.util.Date;

public class Event<T extends Request> {

    protected T request = null;
    protected Response response = null;

    protected Date sendTime = null;
    protected Date receivedTime = null;

    Event() {
    }

    public T getRequest() {
        return request;
    }

    protected void setRequest(T request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    protected void setResponse(Response response) {
        this.response = response;
    }

    public Date getSendTime() {
        return sendTime;
    }

    protected void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReceivedTime() {
        return receivedTime;
    }

    protected void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    public long getExpended() {
        if (sendTime == null || receivedTime == null)
            throw new RuntimeException("尚未完成请求的发送或接受动作。");
        return receivedTime.getTime() - sendTime.getTime();
    }

}
