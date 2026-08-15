package com.pearl.fcw.info.lobby.pojo.events;

import java.util.Date;

import com.pearl.fcw.info.lobby.pojo.records.PlayerNosqlRecord;
import com.pearl.fcw.info.lobby.utils.CommonUtil;

public class BasePlayerLogEvent extends PlayerNosqlRecord {
	public String content;
	public long time;// seconds
	public String timeStr;
	public int type;
	private String nosqlPlainStr;

	public static final NoSqlDataType DATATYPE = NoSqlDataType.LIST;
	public static final String RECORDTYPE = "PlayerLogEvent";

	public enum EventType {
		LOGINCHANNEL, ROOMIN, DEAD, KILL, GETEXP, GETGP, GETVOUCHER, PAYGP, PAYCR, BUYITEM, PAYVOUCHER, PAYSTONE
	}

	public BasePlayerLogEvent(String content, long time, int playerId,
			String playerName, int type) {
		super(playerId, playerName);
		this.time = time;
		this.timeStr = CommonUtil.simpleDateFormat
				.format(new Date(time * 1000));
		this.content = content;
		this.type = type;
	}

	public NoSqlDataType getDataTypeKey() {
		return DATATYPE;
	}

	public long getTime() {
		return time;
	}

	protected String getRecordType() {
		return RECORDTYPE;
	}

	// time|template
	@Override
	public String generateNosqlPlainStr() {
		return "" + time + DELEMETER + content + DELEMETER + type;
	}

	public static BasePlayerLogEvent getFromNosqlPlainStr(String str,
			int playerId, String playerName) {
		String[] fields = str.split("\\" + DELEMETER);
		return new BasePlayerLogEvent(fields[1], Long.valueOf(fields[0]),
				playerId, playerName, Integer.valueOf(fields[2]));
	}

	public String getContent() {
		return content;
	}

	/**
	 * need be overided
	 */
	public int getType() {
		return type;
	}

	public String getNosqlPlainStr() {
		return nosqlPlainStr;
	}
}
