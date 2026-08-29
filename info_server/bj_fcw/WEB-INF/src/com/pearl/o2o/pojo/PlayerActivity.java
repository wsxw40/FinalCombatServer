package com.pearl.o2o.pojo;

import java.util.Date;

import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.DBRouteUtil;

public class PlayerActivity extends BaseMappingBean<PlayerActivity> {


	private static final long serialVersionUID = -2622640410394605675L;
	private int playerId;
	private int action;
	private int status;
	private int number;
	private int target;
	private int award;
	private int sysActivityId;
	private Date startTime;
	private Date endTime;
	private Date createTime;
	private String name;
	private int achievementAction;
	private int characterId;
	private SysActivity sysActivity;


	public PlayerActivity(){
		
	}
	
	public PlayerActivity(SysActivity sysActivity,int playerId) {
		Object[] o2={CommonUtil.dateFormat.format(sysActivity.getStartTime()),CommonUtil.dateFormat.format(sysActivity.getEndTime())};
		if(sysActivity.getAction()==5){
			this.name=CommonUtil.messageFormatI18N2(""+357,"<C",2,o2);
			this.name+="<AD"+sysActivity.getName()+"^0>";
		}else{
			this.name=sysActivity.getName();
		}
		this.playerId=playerId;
		this.action=sysActivity.getAction();
		this.status=0;
		this.number=0;
		if(sysActivity.getAction()==5||sysActivity.getAction()==6||sysActivity.getAction()==7||sysActivity.getAction()==10
				||sysActivity.getAction()==18||sysActivity.getAction()==19||sysActivity.getAction()==20||sysActivity.getAction()==24){
			this.target=sysActivity.getTargetNum();
		}else{
			this.target=sysActivity.getValue();
		}
	
		this.award=0;
		this.sysActivityId=sysActivity.getId();
		this.startTime= sysActivity.getStartTime();
		this.endTime=sysActivity.getEndTime();
		this.createTime=new Date();
		this.characterId=sysActivity.getChracter_id();
		this.achievementAction=sysActivity.getAchievement_action();
	}
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getAward() {
		return award;
	}

	public void setAward(int award) {
		this.award = award;
	}

	public int getSysActivityId() {
		return sysActivityId;
	}

	public void setSysActivityId(int sysActivityId) {
		this.sysActivityId = sysActivityId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public SysActivity getSysActivity() {
		return sysActivity;
	}

	public void setSysActivity(SysActivity sysActivity) {
		this.sysActivity = sysActivity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public int getAchievementAction() {
		return achievementAction;
	}

	public void setAchievementAction(int achievementAction) {
		this.achievementAction = achievementAction;
	}

	public int getCharacterId() {
		return characterId;
	}

	public void setCharacterId(int characterId) {
		this.characterId = characterId;
	}
	@Override
    public String getTableSuffix() {
        return DBRouteUtil.getTableSuffix(PlayerActivity.class, playerId);
    }
}
