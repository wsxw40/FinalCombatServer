package com.pearl.fcw.info.core.network;

public enum ClientMessage {
	CM_RequestTextRPC,
	CM_RequestBinaryRPC,
	CM_ResponseStatus,
	CM_ResponseOnlineInfo,
	CM_UpdateServerInfo,
	CM_CancelRPC,
	CM_SyncRPCQueue
}
