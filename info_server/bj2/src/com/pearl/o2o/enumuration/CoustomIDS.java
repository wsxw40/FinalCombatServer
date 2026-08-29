package com.pearl.o2o.enumuration;

public enum CoustomIDS {
	TeamSpaceLevelUp(100001),
	TransformStoneP(100002),
	TransformStoneT(100003),
	BuyStoneT(100004),
	StartChallenge(100005);
	
	private int id;
	private CoustomIDS(int id){
		this.id=id;
	}
	
	public int getId(){
		return this.id;
	}
}
