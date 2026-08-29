package com.pearl.o2o.pojo;

public class QwPlayerSum {
	//积分0|杀人1|死亡2|助攻3|胜利4|失败5|段位6|排名7|k值8|9名字|10等级|11vip等级 
	public static final String  QW_SUM_DEFAULT_DATA			= "0|0|0|0|0|0|-1|-1|12|-1|-1|-1";//用户单日默认数据
	private int scoce; 		//积分0
	private int kill;		//杀人1
	private int dead;		//死亡2
	private int assist;		//助攻3
	private int win;		//胜利4
	private int fall;		//失败5
	private int mateRank;	//段位6
	private int rankNum;	//排名7
	private int kValue;		//k值8
	private String name;	//名字9
	private int playerRank;	//玩家等级等级10
	private int vipRank;	//vip等级11
	private boolean notNull	=false;//是否为空或格式不正确

	public QwPlayerSum(String playerSum) {
		String[] split;
		if (playerSum == null){
			playerSum = QW_SUM_DEFAULT_DATA;
			this.notNull=true;
		}
		split = playerSum.split("\\|");
		if (split.length != 12){ // 不等于12，就是数据有问题
			split = QW_SUM_DEFAULT_DATA.split("\\|");
			this.notNull=true;
		}
		this.scoce 		= Integer.valueOf(split[0]);
		this.kill 		= Integer.valueOf(split[1]);
		this.dead 		= Integer.valueOf(split[2]);
		this.assist 	= Integer.valueOf(split[3]);
		this.win 		= Integer.valueOf(split[4]);
		this.fall 		= Integer.valueOf(split[5]);
		this.mateRank 	= Integer.valueOf(split[6]);
		this.rankNum 	= Integer.valueOf(split[7]);
		this.kValue 	= Integer.valueOf(split[8]);
		this.name 		= split[9];
		this.playerRank = Integer.valueOf(split[10]);
		this.vipRank 	= Integer.valueOf(split[11]);
	}

	public String toString() {
		return scoce + "|" + kill + "|" + dead + "|" 
				+ assist + "|" + win + "|"	+ fall + "|" 
				+ mateRank + "|" + rankNum + "|" + kValue + "|"
				+ name + "|" + playerRank + "|" + vipRank;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public int additionScoce(int num) {
		this.scoce +=num;
		return this.scoce;
	}
	public int additionKill(int num) {
		this.kill +=num;
		return this.kill;
	}
	public int additionDead(int num) {
		this.dead +=num;
		return this.dead;
	}
	public int additionAssist(int num) {
		this.assist +=num;
		return this.assist;
	}
	public int additionWin(int num) {
		this.win +=num;
		return this.win;
	}
	public int additionFall (int num) {
		this.fall +=num;
		return this.fall;
	}
	public int additionMateRank(int num) {
		this.mateRank +=num;
		return this.mateRank;
	}
	public int additionKValue(int num) {
		this.kValue +=num;
		return this.kValue;
	}
	
	public int getScoce() {
		return scoce;
	}

	public void setScoce(int scoce) {
		this.scoce = scoce;
	}

	public int getKill() {
		return kill;
	}

	public void setKill(int kill) {
		this.kill = kill;
	}

	public int getDead() {
		return dead;
	}

	public void setDead(int dead) {
		this.dead = dead;
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getFall() {
		return fall;
	}

	public void setFall(int fall) {
		this.fall = fall;
	}

	public int getMateRank() {
		return mateRank;
	}

	public void setMateRank(int mateRank) {
		this.mateRank = mateRank;
	}

	public int getRankNum() {
		return rankNum;
	}

	public void setRankNum(int rankNum) {
		this.rankNum = rankNum;
	}

	public int getKValue() {
		return kValue;
	}

	public void setKValue(int value) {
		kValue = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPlayerRank() {
		return playerRank;
	}

	public void setPlayerRank(int playerRank) {
		this.playerRank = playerRank;
	}

	public int getVipRank() {
		return vipRank;
	}

	public void setVipRank(int vipRank) {
		this.vipRank = vipRank;
	}
}