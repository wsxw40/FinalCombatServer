package com.pearl.o2o.pojo;

public class QwPlayerDay {
	//积分0|杀人1|死亡2|助攻3|胜利4|失败5|段位6|排名7|k值8|总杀人数目9  	
	public static final String  QW_DAY_DEFAULT_DATA			= "0|0|0|0|0|0|-1|-1|-1|-1|0";//用户单日默认数据
	private int scoce; 		//积分0
	private int kill;		//杀人1
	private int dead;		//死亡2
	private int assist;		//助攻3
	private int win;		//胜利4
	private int fall;		//失败5
	private int mateRank;	//段位6
	private int rankNum;	//排名7
	private int kValue;		//k值8
	private int killSum;	//总杀人数目9  	
	private int boutSum;	//游戏局数10  	
	private boolean notNull	=false;//是否为空或格式不正确

	public QwPlayerDay(String playerDay) {
		String[] split;
		if (playerDay == null){
			playerDay = QW_DAY_DEFAULT_DATA;
			this.notNull=true;
		}
		split = playerDay.split("\\|");
		if (split.length == 10){ // 等于10，为老的数据格式，所以加上新的游戏局数；
			split = (playerDay+"|0").split("\\|");
		}
		if (split.length != 11){ // 不等于11，就是数据有问题
			split = QW_DAY_DEFAULT_DATA.split("\\|");
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
		this.killSum 	= Integer.valueOf(split[9]);
		this.boutSum 	= Integer.valueOf(split[10]);
	}
	

	@Override
	public String toString() {
		return scoce + "|" + kill + "|" + dead + "|" 
				+ assist + "|" + win + "|"	+ fall + "|" 
				+ mateRank + "|" + rankNum + "|" + kValue + "|"
				+ killSum+ "|" + boutSum;
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
	public int additionKillSum(int num) {
		this.killSum +=num;
		return this.killSum;
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

	public int getKillSum() {
		return killSum;
	}

	public void setKillSum(int killSum) {
		this.killSum = killSum;
	}


	public int getBoutSum() {
		return boutSum;
	}


	public void setBoutSum(int boutSum) {
		this.boutSum = boutSum;
	}

}
