package com.pearl.o2o.pojo;

import java.util.Comparator;


public class BannedWord extends BaseMappingBean<BannedWord> implements Comparator<BannedWord>{

	private static final long serialVersionUID = 5379981265944373795L;

	private String bannedWord;
	private String isDeleted;
	private String includeInWord;

	public String getBannedWord() {
		return bannedWord;
	}
	public void setBannedWord(String bannedWord) {
		this.bannedWord = bannedWord;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getIncludeInWord() {
		return includeInWord;
	}
	public void setIncludeInWord(String includeInWord) {
		this.includeInWord = includeInWord;
	}
	@Override
	public int compare(BannedWord arg0, BannedWord arg1) {
		if(arg0.getBannedWord().length()>arg1.getBannedWord().length())
			return -1;
		else 
			return 1;
	}
}
