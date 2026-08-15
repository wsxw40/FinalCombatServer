package com.pearl.o2o.pojo;

public class TeamTechnology extends BaseMappingBean<TeamTechnology> {
	private static final long serialVersionUID = -5711510478984038009L;
	private int place;	//处于科技树的 位置
	private int curNode;
	private int nextNode;
	private int treeType;
	private int openTower;
	
	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public int getTreeType() {
		return treeType;
	}

	public void setTreeType(int treeType) {
		this.treeType = treeType;
	}

	public int getCurNode() {
		return curNode;
	}

	public void setCurNode(int curNode) {
		this.curNode = curNode;
	}

	public int getNextNode() {
		return nextNode;
	}

	public void setNextNode(int nextNode) {
		this.nextNode = nextNode;
	}

	public int getOpenTower() {
		return openTower;
	}

	public void setOpenTower(int openTower) {
		this.openTower = openTower;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + curNode;
		result = prime * result + id;
		result = prime * result + nextNode;
		result = prime * result + place;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TeamTechnology other = (TeamTechnology) obj;
		if (curNode != other.curNode)
			return false;
		if (id != other.id)
			return false;
		if (nextNode != other.nextNode)
			return false;
		if (place != other.place)
			return false;
		return true;
	}

	
}
