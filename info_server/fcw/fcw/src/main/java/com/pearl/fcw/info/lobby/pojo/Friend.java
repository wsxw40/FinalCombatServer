package com.pearl.fcw.info.lobby.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.pearl.fcw.info.core.persistence.config.annotation.Schema;

@Entity
@Schema(GoSchema.MAIN)
public class Friend extends BasePojo{
	private static final long serialVersionUID = -3518175517759453431L;

	@Id
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id=id;
	}

	private String name;
	private Integer playerId;
	private Integer friendId;
	private Integer type;
	private String acceptted;

	@Transient private Integer rank;
	@Transient private Integer isVip;
	@Transient private Integer internetCafe;
	@Transient private String icon;
	@Transient private int card;
	@Transient private String team;
	@Transient private int online = 0;
	@Transient private String groupName;
	@Transient private int gameRank;

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	/**
	 * @return the playerId
	 */
	public Integer getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the friendId
	 */
	public Integer getFriendId() {
		return friendId;
	}

	/**
	 * @param friendId
	 *            the friendId to set
	 */
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAcceptted() {
		return acceptted;
	}

	public void setAcceptted(String acceptted) {
		this.acceptted = acceptted;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rank
	 */
	public Integer getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public int getGameRank() {
		return gameRank;
	}

	public void setGameRank(int gameRank) {
		this.gameRank = gameRank;
	}

	public int getOnline() {
		return online;
	}

	public void setOnline(int online) {
		this.online = online;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}

	public Integer getInternetCafe() {
		return internetCafe;
	}

	public void setInternetCafe(Integer internetCafe) {
		this.internetCafe = internetCafe;
	}
}
