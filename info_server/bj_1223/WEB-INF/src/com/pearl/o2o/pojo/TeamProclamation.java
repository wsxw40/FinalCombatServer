package com.pearl.o2o.pojo;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Date;

import com.pearl.o2o.utils.DateUtil;
import com.pearl.o2o.utils.StringUtil;


public class TeamProclamation extends BaseMappingBean<TeamProclamation> implements Comparable<Object>{

	/**
	 * eclipse 自动生成的
	 */
	private static final long serialVersionUID = -6849000491105742700L;
	private Integer teamId;
	private Integer playerId;
	private String content;
	private long createTime;
	
	
	
	//附加属性
	private transient String icon;
	private transient String nikeName;
	
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getAsciiContent() throws UnsupportedEncodingException{
		return StringUtil.stringToAscii(content);
	}
	
	public long getCreateTime() {
		return createTime;
	}
	
	public String getCreateTimeStr(){
		return DateUtil.df.format(new Date(createTime));
	}
	
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TeamProclamation [teamId=" + teamId + ", playerId=" + playerId
				+ ", content=" + content + ", createTime=" + createTime + "]";
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getNikeName() {
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	@Override
	public int compareTo(Object o) {
		TeamProclamation t=(TeamProclamation)o;
		return id>t.id?1:0;
	}
	@SuppressWarnings("rawtypes")
	static class TeamProclamationComparator implements Comparator{

		@Override
		public int compare(Object o1, Object o2) {
			TeamProclamation t1=(TeamProclamation)o1;
			TeamProclamation t2=(TeamProclamation)o2;
			int num=t1.id>t2.id?1:0;
			return num;
		}
		
	}
	
}
