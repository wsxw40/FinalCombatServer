package com.pearl.o2o.pojo;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.TeamConstants;

public class Team extends BaseMappingBean<Team> {

	private static final long serialVersionUID = 727386169361377369L;
	private String name;
	private String declaration;
	private String description;
	private String board;
	private String logo;
	private int size;			//限制人数
	private int kill;
	private int headShot;
	private int gameWin;
	private int gameTotal;
	private int challengeWin;
	private int challengeTotal;
	private Date createTime;
	
	private int headerId;
	private String deleted;
	private Integer score;
	private Integer number;//战队当前人数，对应数据库的HIT_SCORE字段
	
	private String gameRatio;
	private String challengeRatio;
	private List<Team> teamList;
	private List<PlayerTeam> memberList;
	
	private String createTimeStr;
	
	private String province;
	private String city;
	
	private int memberCount;//冗余字段
	private int rank;				//加入条件
	@SuppressWarnings("unused")
	private String leaderName;
	
	private int level;				//战队等级
	private int exp;				//战队经验
	
	private int recoreRankingFormer;
	private int recoreRankingCurr;	//战绩当前排名
	private int fightRankingFormer;
	private int fightRankingCurr;	//战斗力当前排名

	private int fight;
	
	private int preweekResAmount;
	
	private int predayResAmount;//用于记录当前资源抢夺数量，没有存库
	
	
	/**资源争夺战战队空间等级*/
	private int teamSpaceLevel;
	
	/**资源争夺战战队不可用资源数*/
	private int unusableResource;

	/**资源争夺战战队可用资源数*/
	private int usableResource;
	
	/**是否资源争夺战活跃,0否，1是*/
	private int teamSpaceActive;
	
	private Integer tResourceBeginTime; //战队资源转化开始时间
	
	private long lastTeamPlaceLevelUpTime;
	
	private HashMap<String, Integer> resMap=new HashMap<String, Integer>();
	public long getLastTeamPlaceLevelUpTime() {
		return lastTeamPlaceLevelUpTime;
	}



	public void setLastTeamPlaceLevelUpTime(long lastTeamPlaceLevelUpTime) {
		this.lastTeamPlaceLevelUpTime = lastTeamPlaceLevelUpTime;
	}



	public int getSalary(){
		return level*level/2+level/2+1;
	}
	
	
	
	public int getTotalExp() {
		int tmp = (int) (6958.5*(level+10)*Math.pow(1.61672, level));
		return tmp/1000*1000;
	}
	public static void main(String[] args) {
		int tmp = (int) (6958.5*(10+10)*1.61672);
	}
	
	
	public int getPredayResAmount() {
		return predayResAmount;
	}



	public void setPredayResAmount(int predayResAmount) {
		this.predayResAmount = predayResAmount;
	}



	public int getFight() {
		return fight;
	}

	public void setFight(int fight) {
		this.fight = fight;
	}

	public int getRecoreOffset(){
		return recoreRankingFormer - recoreRankingCurr;
	}
	
	public int getFightOffset(){
		return fightRankingFormer - fightRankingCurr;
	}
	
	
	public int getFightRankingFormer() {
		return fightRankingFormer;
	}
	public void setFightRankingFormer(int fightRankingFormer) {
		this.fightRankingFormer = fightRankingFormer;
	}
	public int getFightRankingCurr() {
		return fightRankingCurr;
	}
	public void setFightRankingCurr(int fightRankingCurr) {
		this.fightRankingCurr = fightRankingCurr;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getRecoreRankingFormer() {
		return recoreRankingFormer;
	}
	public void setRecoreRankingFormer(int recoreRankingFormer) {
		this.recoreRankingFormer = recoreRankingFormer;
	}
	public int getRecoreRankingCurr() {
		return recoreRankingCurr;
	}
	public void setRecoreRankingCurr(int recoreRankingCurr) {
		this.recoreRankingCurr = recoreRankingCurr;
	}
	/**
	 * 
	 * @param add
	 * @return
	 */
	public Team addExp(int add){
		if(level>=TeamConstants.Team_Level_Limit){
			level = TeamConstants.Team_Level_Limit;
			exp = 0;
			return this;
		}
		
		this.exp+=add;
		while(exp >= getTotalExp()){
			exp -= getTotalExp();
			level++;

			if(level>=TeamConstants.Team_Level_Limit){
				level = TeamConstants.Team_Level_Limit;
				exp = 0;
//				return this;
			}
			
			if(5*level+50>size){
				size = 5*level+50;
			}
		}
		return this;
	}
	
	public int getContributionLimit(){
		return getContributionLimitByLevel(this.level);
	}
	private static final double 	a = 1391.7;
	private static final double	 	b = 1.61672;
	public static int getContributionLimitByLevel(int level){
		return (int)(Math.ceil(a/3*b*(1-Math.pow(b, level))/(1-b)));
	}
	
	private static DecimalFormat DF = new DecimalFormat("0.0");
	
//	public void calculateParam(){
//		if(gameTotal==0){
//			gameRatio="0%";
//		}else{
//			double rate = gameWin*100.0/gameTotal;
//			if (rate == 0){
//				gameRatio="0%";
//			}else{
//				gameRatio=DF.format(rate)+"%";
//			}
//		}
//		
//		if(challengeTotal==0){
//			challengeRatio="0%";
//		}else{
//			double rate = challengeWin*100.0/challengeTotal;
//			if (rate == 0) {
//				challengeRatio="0%";
//			}else{
//				challengeRatio=DF.format(rate)+"%";
//			}
//		}
//	}
//	
//	public void calculateAllyParam(){
//		calculateParam();
//		if(memberList == null){
//			return ;
//		}
//		/*for (Team t: teamList){
//			t.calculateParam();
//		}*/
//	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeclaration() {
		return declaration;
	}
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getSize() {
		return this.size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public int getKill() {
		return kill;
	}
	public void setKill(Integer kill) {
		this.kill = kill;
	}
	public int getHeadShot() {
		return headShot;
	}
	public void setHeadShot(Integer headShot) {
		this.headShot = headShot;
	}
	public int getGameWin() {
		return gameWin;
	}
	public void setGameWin(Integer gameWin) {
		this.gameWin = gameWin;
	}
	public int getGameTotal() {
		return gameTotal;
	}
	public void setGameTotal(Integer gameTotal) {
		this.gameTotal = gameTotal;
	}
	public int getChallengeWin() {
		return challengeWin;
	}
	public void setChallengeWin(Integer challengeWin) {
		this.challengeWin = challengeWin;
	}
	public int getChallengeTotal() {
		return challengeTotal;
	}
	public void setChallengeTotal(Integer challengeTotal) {
		this.challengeTotal = challengeTotal;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.createTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(createTime);
	}
	public List<Team> getTeamList() {
		return teamList;
	}
	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}
	public List<PlayerTeam> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<PlayerTeam> memberList) {
		if(memberList!=null)
			this.memberCount = memberList.size();
		this.memberList = memberList;
	}
	public String getGameRatio() {
		if(gameTotal==0){
			gameRatio="0%";
		}else{
			double rate = gameWin*100.0/gameTotal;
			if (rate == 0){
				gameRatio="0%";
			}else{
				gameRatio=DF.format(rate)+"%";
			}
		}
		return gameRatio;
	}
	public void setGameRatio(String gameRatio) {
		this.gameRatio = gameRatio;
	}
	public String getChallengeRatio() {
		if(challengeTotal==0){
			challengeRatio="0%";
		}else{
			double rate = challengeWin*100.0/challengeTotal;
			if (rate == 0) {
				challengeRatio="0%";
			}else{
				challengeRatio=DF.format(rate)+"%";
			}
		}
		return challengeRatio;
	}
	public void setChallengeRatio(String challengeRatio) {
		this.challengeRatio = challengeRatio;
	}
	
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getLeaderName(){
		
			for (PlayerTeam pt : this.memberList) {
				if (pt.getJob() == TeamConstants.TEAMJOB.TEAM_CAPTAIN.getValue()) {
					return pt.getNickName();
				}
			}
			return "";
		
	}

	public int getHeaderId() {
		return headerId;
	}

	public void setHeaderId(int headerId) {
		this.headerId = headerId;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setChallengeWin(int challengeWin) {
		this.challengeWin = challengeWin;
	}

	public void setChallengeTotal(int challengeTotal) {
		this.challengeTotal = challengeTotal;
	}
	public int getTeamSpaceLevel() {
		return teamSpaceLevel;
	}

	public void setTeamSpaceLevel(int teamSpaceLevel) {
		this.teamSpaceLevel = teamSpaceLevel;
	}

	public HashMap<String, Integer> getLatestTeamRes(){
		Calendar calendar=Calendar.getInstance();
		int nowMin=calendar.get(Calendar.MINUTE);
		int nowHour=calendar.get(Calendar.HOUR_OF_DAY);
		if((nowMin==0&&nowHour%4==0) || nowMin==30){
			caculateTeamRes(nowMin,nowHour);
		}
		
		resMap.put(ORG_RES,getUnusableResource());
		resMap.put(RES, getUsableResource());
		return resMap;
	}
	
	private void caculateTeamRes(int minute,int hour){
		Constants.TEAMSPACELEVELCONSTANTS baseTeamSLC=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(0);
		Constants.TEAMSPACELEVELCONSTANTS teamSLC=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(teamSpaceLevel);
		
		final int nowSeconds=(int)(System.currentTimeMillis()/1000);
		final int preSeconds=this.gettResourceBeginTime();
		
		int newUsableRes=this.getUsableResource();
		int increaseConvertNum=0;
		int increaseOrgNum=0;
		int increaseGiftNum=0;
		if(nowSeconds-preSeconds>0){
			if(minute==30){ //30分时，转换
				increaseConvertNum=(nowSeconds-preSeconds)/Constants.TeamSpaceConstants.TEAM_ORG_RES_CONVERT_INTERVAL
						*(teamSLC.gettOrgResConvertNum()+baseTeamSLC.gettOrgResConvertNum());
				increaseGiftNum=(nowSeconds-preSeconds)/Constants.TeamSpaceConstants.TEAM_ORG_RES_CONVERT_INTERVAL
						*(teamSLC.gettGiftRes());
			}
		
			newUsableRes+=increaseGiftNum+increaseConvertNum;
			int reaminSeconds=(nowSeconds-preSeconds)%Constants.TeamSpaceConstants.TEAM_ORG_RES_CONVERT_INTERVAL;
			if(hour%4==0&&minute==0){//4小时 发送一次原石
				increaseOrgNum=(nowSeconds-preSeconds)/Constants.TeamSpaceConstants.TEAM_ORG_RES_PRODUCT_INTERVAL
						*teamSLC.gettOutPutOrgRes();
			}
			
			int newUnusableRes=this.getUnusableResource()+increaseOrgNum-increaseConvertNum*Constants.TeamSpaceConstants.TEAM_ORG_TO_RES_SCALE;
			
			if(newUnusableRes<0 || newUnusableRes>teamSLC.gettMaxOrgRes()){
				newUnusableRes=teamSLC.gettMaxOrgRes();
			}
			if(newUsableRes<0 || newUsableRes>teamSLC.gettMaxRes()){
				newUsableRes=teamSLC.gettMaxRes();
			}
			
			this.setUsableResource(newUsableRes);
			this.setUnusableResource(newUnusableRes);
			this.settResourceBeginTime(nowSeconds-reaminSeconds);
		}
	}
	public int getUnusableResource() {
		return unusableResource;
	}
	

	public void setUnusableResource(int unusableResource) {
		if(unusableResource<0){
			this.unusableResource=0;
		}else{
			Constants.TEAMSPACELEVELCONSTANTS teamSLC=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(teamSpaceLevel);
			if(unusableResource> teamSLC.gettMaxOrgRes()){
				this.unusableResource=teamSLC.gettMaxOrgRes();
			}else{
				this.unusableResource = unusableResource;
			}	
		}
		
	}

	public int getUsableResource() {
		return usableResource;
	}

	public void setUsableResource(int usableResource) {
		if(usableResource<0){
			this.usableResource=0;
		}else{
			Constants.TEAMSPACELEVELCONSTANTS teamSLC=Constants.TEAMSPACELEVELCONSTANTS.getTeamSpaceLevelConstants(teamSpaceLevel);
			if(usableResource> teamSLC.gettMaxRes()){
				this.usableResource=teamSLC.gettMaxRes();
			}else{
				this.usableResource = usableResource;
			}	
		}
		
	}

	public int getTeamSpaceActive() {
		return teamSpaceActive;
	}

	public void setTeamSpaceActive(int teamSpaceActive) {
		this.teamSpaceActive = teamSpaceActive;
	}

	@Override
	public boolean equals(Object arg0) {
		if(arg0!=null&& arg0 instanceof Team){
			Team team = (Team)arg0;
			return this.getId()==team.getId();
		}
		return false;
	}

	public int getPreweekResAmount() {
		return preweekResAmount;
	}

	public void setPreweekResAmount(int preweekResAmount) {
		this.preweekResAmount = preweekResAmount;
	}

	public Integer gettResourceBeginTime() {
		return tResourceBeginTime;
	}

	public void settResourceBeginTime(Integer tResourceBeginTime) {
		this.tResourceBeginTime = tResourceBeginTime;
	}
	
	/**
	 * 获得本物品的可以levelup的剩余CD
	 * @param fullcd
	 * @return
	 * @throws ParseException
	 */
	public long getLevelupCD(long fullcd) throws ParseException {
		long result = 0l;
		if (lastTeamPlaceLevelUpTime != 0) {
			long past = System.currentTimeMillis() - lastTeamPlaceLevelUpTime*1000;//过去的毫秒数
			past = past < 0 ? 0 : past;
			result = fullcd - past;
		}
		result = result < 0 ? 0 : result;
		return result;
	}	
	
	public static final String ORG_RES="orgRes";
	public static final String RES="res";
}
