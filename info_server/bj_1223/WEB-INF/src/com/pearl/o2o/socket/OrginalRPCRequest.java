package com.pearl.o2o.socket;

public class OrginalRPCRequest {
	private int rpcId;
	//the orginal bytes of this request, include rpcId
	private byte[] requestBytes;
	private boolean isCancel = false;
	private boolean isSyncRPC = false;
	private int queueId;//只有RPC的请求带队列ID
	private int queueSize;//SyncRPCQueue的Size

	public int getRpcId() {
		return rpcId;
	}
	public void setRpcId(int rpcId) {
		this.rpcId = rpcId;
	}
	public byte[] getRequestBytes() {
		return requestBytes;
	}
	public void setRequestBytes(byte[] requestBytes) {
		this.requestBytes = requestBytes;
	}
	public boolean isCancel() {
		return isCancel;
	}
	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}		
    public boolean isSyncRPC() {
		return isSyncRPC;
	}
	public void setSyncRPC(boolean isSyncRPC) {
		this.isSyncRPC = isSyncRPC;
	}
	public int getQueueId() {
        return queueId;
    }
    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }
	public int getQueueSize() {
		return queueSize;
	}
	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}
}
