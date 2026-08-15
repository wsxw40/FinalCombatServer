#pragma once

class Client;

static const float DURATION_INFINITY = 3600 * 24 * 30;
static const int		SKILL_PARAM_NUM		= 6;
static const int		SKILL_DATA_NUM		= 2;

enum EEffect
{
	kEffect_None = 0,
	
	// 时长+间隔 Start
	kEffect_IntervalBase_Start = 10,		//时长+间隔类型开始标志（无实际用途）
	
	kEffect_Sustain_HurtBurn_Replace,		//替换型持续燃烧伤害，value=伤害值
	kEffect_Sustain_HurtBloodshed_Replace,	//替换型持续流血伤害，value=伤害值
	kEffect_Sustain_HurtPoison_Replace,		//替换型持续毒伤害，value=伤害值
	kEffect_Sustain_HurtBurn,				//持续燃烧伤害，value=伤害值
	kEffect_Sustain_HurtBloodshed,			//持续流血伤害，value=伤害值
	kEffect_Sustain_HurtPoison,				//持续毒伤害，value=伤害值
	kEffect_Sustain_HpRecover,				//持续回复Hp（受攻击暂时停止），value=回复值
	kEffect_Sustain_HpRecover2,				//持续回复Hp（受攻击和移动暂时停止），value=回复值
	kEffect_Sustain_AmmoRecover,			//持续回复Ammo（受攻击暂时停止），value=回复值
	kEffect_Sustain_AmmoRecover2,			//持续回复Ammo（受攻击和移动暂时停止），value=回复值
	kEffect_Sustain_ArmorRecover,			//持续回复Armor（受攻击暂时停止），value=回复值
	kEffect_Sustain_ArmorRecover2,			//持续回复Armor（受攻击和移动暂时停止），value=回复值
	kEffect_Sustain_Survival_Mode,			//持续伤害对应生存模式， value=扣除当前最大生命值百分比
	
	kEffect_IntervalBase_End,				//时长+间隔类型结束标志（无实际用途）
	// 时长+间隔 End
	
	// 时长 Start
	kEffect_TimeBase_Start = 500,			//时长类型开始标志（无实际用途）
	
	kEffect_Invincible,						//无敌，value=未使用
	kEffect_Infect_Score,					//分数+，value=分数变动百分比
	kEffect_Infect_Damage,					//威力+，value=威力变动百分比
	kEffect_Infect_CareerDamage_Id1,		//对火箭兵威力+，value=威力变动百分比
	kEffect_Infect_CareerDamage_Id2,		//对重机枪手威力+，value=威力变动百分比
	kEffect_Infect_CareerDamage_Id3,		//对狙击手威力+，value=威力变动百分比
	kEffect_Infect_CareerDamage_Id4,		//对突击手威力+，value=威力变动百分比
	kEffect_Infect_CareerDamage_Id5,		//对火焰兵威力+，value=威力变动百分比
	kEffect_Infect_CareerDamage_Id6,		//对医疗兵威力+，value=威力变动百分比
	kEffect_Infect_CareerDamage_Id7,		//对工程兵威力+，value=威力变动百分比
	kEffect_Infect_HpInfectDamage,			//血量影响威力+，value=威力变动百分比
	kEffect_Infect_ResistanceAll,			//抗性All+，value=抗性变动百分比
	kEffect_Infect_ResistanceBullet,		//抗性Bullet+，value=抗性变动百分比
	kEffect_Infect_ResistanceExplode,		//抗性Explode+，value=抗性变动百分比
	kEffect_Infect_ResistanceClose,			//抗性Close+，value=抗性变动百分比
	kEffect_Infect_ResistanceFlame,			//抗性Flame+，value=抗性变动百分比
	kEffect_Infect_SelfResistanceAll,		//自我抗性All+，value=抗性变动百分比
	kEffect_Infect_SelfResistanceBullet,	//自我抗性Bullet+，value=抗性变动百分比
	kEffect_Infect_SelfResistanceExplode,	//自我抗性Explode+，value=抗性变动百分比
	kEffect_Infect_SelfResistanceClose,		//自我抗性Close+，value=抗性变动百分比
	kEffect_Infect_SelfResistanceFlame,		//自我抗性Flame+，value=抗性变动百分比
	kEffect_Infect_CareerResistance_Id1,	//对火箭兵抗性All+，value=抗性变动百分比
	kEffect_Infect_CareerResistance_Id2,	//对重机枪手抗性All+，value=抗性变动百分比
	kEffect_Infect_CareerResistance_Id3,	//对狙击手抗性All+，value=抗性变动百分比
	kEffect_Infect_CareerResistance_Id4,	//对突击手抗性All+，value=抗性变动百分比
	kEffect_Infect_CareerResistance_Id5,	//对火焰兵抗性All+，value=抗性变动百分比
	kEffect_Infect_CareerResistance_Id6,	//对医疗兵抗性All+，value=抗性变动百分比
	kEffect_Infect_CareerResistance_Id7,	//对工程兵抗性All+，value=抗性变动百分比
	kEffect_Infect_HpInfectResistance,		//血量影响抗性All+，value=抗性变动百分比
	kEffect_Infect_Rebirth,					//重生速度+，value=重生速度变动值
	kEffect_Infect_SupplyAmmo,				//弹药包补弹药量+，value=补弹药量变动百分比
	kEffect_Infect_SupplyHpRecover,			//医疗包补血量+，value=补血量变动百分比
	kEffect_Infect_CureHpRecover,			//治疗补血量+，value=补血量变动百分比
	kEffect_Infect_SelfHpRecover,			//自身回复补血量+，value=补血量变动百分比
	kEffect_Infect_Cure,					//对他人治疗量+，value=治疗量变动百分比
	kEffect_Infect_CureEnergy,				//治疗充能速度+，value=治疗充能速度变动百分比
	kEffect_Infect_GunEnergy,				//枪充能速度+，value=枪充能速度变动百分比
	kEffect_Infect_JumpHeight,				//跳跃高度+，value=跳跃高度变动百分比
	kEffect_Infect_MoveSpeed,				//移动速度+，value=移动速度变动百分比
	kEffect_Infect_HpInfectMoveSpeed,		//血量影响移动速度+，value=移动速度变动百分比
	kEffect_Infect_MaxHp,					//血量上限+，value=血量上限变动百分比
	kEffect_Infect_AmmoOneClip,				//上膛量+，value=上膛量变动百分比
	kEffect_Infect_AmmoCount,				//载弹量+，value=载弹量变动百分比
	kEffect_Infect_FireTime,				//攻击速度+，value=攻击速度变动百分比(负值为加快)
	kEffect_Infect_ReloadTime,				//换弹速度+，value=换弹速度变动百分比(负值为加快)
	kEffect_Infect_ChangeInTime,			//切换武器速度+，value=切换武器速度变动百分比(负值为加快)
	kEffect_Infect_AmmoFlySpeed,			//炮弹飞行速度+，value=炮弹飞行速度变动百分比
	kEffect_Infect_AmmoExplodeRange,		//炮弹爆炸范围+，value=炮弹爆炸范围变动百分比
	kEffect_Infect_FOV,						//FOV+，value=FOV变动值
	kEffect_Infect_HitBack,					//被击退+，value=被击退变动值
	kEffect_Infect_HurtAbsorb,			//伤害吸收，value=伤害吸收百分比(负值)
	
	kEffect_Invalid_MoveSpeed = 1000,		//不受移动速度+影响，value=未使用
	kEffect_Invalid_Sustain_HurtBurn,		//不受持续燃烧伤害影响，value=未使用
	kEffect_Invalid_Sustain_HurtBloodshed,	//不受持续流血伤害影响，value=未使用
	kEffect_Invalid_Sustain_HurtPoison,		//不受持续毒伤害影响，value=未使用
	
	kEffect_Special_CannotOutFlame = 1500,	//不能使用灭火功能，value=未使用
	kEffect_Special_CannotHide,				//无法隐形，value=未使用
	kEffect_Special_CannotFire,				//无法开火，value=未使用
	kEffect_Special_ReversalMouse,			//鼠标反转，value=未使用
	kEffect_Special_ReversalMouse2,			//鼠标反转，value=未使用
	kEffect_Special_ReversalKeyBoard,		//键盘反转，value=未使用
	kEffect_Special_ViewLost,				//视野模糊，value=未使用
	kEffect_Special_ViewLost2,				//视野模糊，value=未使用
	kEffect_Special_HitTargetJump,			//被击中的玩家跳，value=跳跃比例
	kEffect_Special_HittedSelfJump,			//自身被击中后跳，value=跳跃比例
	kEffect_Special_HitAddScore,			//击中对方后加分，value=加分
	kEffect_Special_UsingSkill,				//正在使用技能，value=未使用
	kEffect_Special_Invisible,				//隐身，value=未使用
	kEffect_Special_Smog,					//毒气，value=未使用
	kEffect_Survival_Expose,				//暴露目标， value=未使用
	kEffect_Survival_Debuff,				//生存模式掉血加倍， value=未使用
	kEffect_Survival_Ghost,					//生存模式幽灵状态， value=未使用
	
	kEffect_TimeBase_End,					//时长类型结束标志（无实际用途）
	// 时长 End
};

struct EffectData
{
	typedef void (*EffectDataCallBack)(EffectData &effectdata, Client &client);
	
	double				duration_timer;		//持续时间计时器（s）
	double				interval;			//间隔时间（s）
	double				interval_timer;		//间隔时间计时器（s）
	uint				player_id;			//Effect施展者，0代表施展者为系统*
	uint				player_item_id;		//Effect施展者所用item，0代表非器具**
	byte				attr_slotid;		//原始属性主槽位id。仅用于比较，无实际用途(系统施展时为-1)
	byte				attr_sub_slotid;	//原始属性副槽位。仅用于比较，无实际用途(系统施展时为-1)
	Attribute			attr_raw;			//原始属性内容，无实际用途
	EEffect				type;				//Effect类型(参照EEffect)
	float				value;				//Effect参数
	bool				enable;				//Effect是否激活。未激活时倒计时继续，但无实际效果
	bool				dead_disable;	// 死亡后失效
	
	EffectDataCallBack	iterval_callback;	//间隔CallBack
	
	EffectData()
		: duration_timer(0)
		, interval(0)
		, interval_timer(0)
		, player_id(0)
		, player_item_id(0)
		, attr_slotid(-1)
		, attr_sub_slotid(-1)
		, type(kEffect_None)
		, value(0)
		, enable(false)
		, dead_disable(false)
		
		, iterval_callback(NULL)
	{
	}
	
	std::string ToString()
	{
		char buffer[512];
		
		sprintf(buffer, "duration_timer : %f", duration_timer);
		sprintf(buffer, "%s, interval : %f", buffer, interval);
		sprintf(buffer, "%s, interval_timer : %f", buffer, interval_timer);
		sprintf(buffer, "%s, player_id : %d", buffer, player_id);
		sprintf(buffer, "%s, player_item_id : %d", buffer, player_item_id);
		sprintf(buffer, "%s, attr_slotid : %d", buffer, attr_slotid);
		sprintf(buffer, "%s, attr_sub_slotid : %d", buffer, attr_sub_slotid);
		sprintf(buffer, "%s, attr_raw.type : %d", buffer, attr_raw.type);
		sprintf(buffer, "%s, attr_raw.value1 : %d", buffer, attr_raw.value1);
		sprintf(buffer, "%s, attr_raw.value2 : %d", buffer, attr_raw.value2);
		sprintf(buffer, "%s, attr_raw.time : %d", buffer, attr_raw.time);
		sprintf(buffer, "%s, type : %d", buffer, type);
		sprintf(buffer, "%s, value : %f", buffer, value);
		sprintf(buffer, "%s, enable : %d", buffer, enable);
		
		return std::string(buffer);
	}
};
// *这里的系统是指场景内道具等
// **器具是指武器、服饰及饰品

typedef std::list<EffectData> EffectDataList;
typedef std::list<EffectData*> EffectDataPtrList;

enum ESkill
{
	kSkill_None = 0,
	
	kSkill_Self,						//对自己使用SkillData中的effect，value=未使用
	kSkill_Target,						//对目标使用SkillData中的effect，value=未使用
	kSkill_SelfTeam,					//对同队使用SkillData中的effect，value=有效半径（米）
	kSkill_EnemyTeam,					//对敌方队使用SkillData中的effect，value=有效半径（米）
	kSkill_SuckBlood,					//吸血，value=吸血值
	kSkill_CureEnergy,					//充能能量，value=能量
};

enum ESkillSlot
{
	kSkillSlot_LeftButton,				//右键触发
	kSkillSlot_Slot1,					//按使用技能槽1触发
	kSkillSlot_KC_6,						// 使用数字6触发
};

struct SkillData
{
	typedef void (*SkillDataCallBack1)(SkillData &skilldata, Client &self);
	typedef void (*SkillDataCallBack2)(SkillData &skilldata, Client &self, Client &target);
	
	
	EffectData		effect;				//技能被应用时造成的Effect
	ESkill					type;					//Skill类型(参照ESkill)
	ESkillSlot			slot;					//技能触发槽(参照ESkillSlot)
	double				duration_timer;		//持续时间计时器（s）
	float					value;				//Skill参数
	bool					enable;				//Skill是否激活。未激活时倒计时继续，无法使用，但可以攻击和击杀

	// 用于角色自带的特殊技能 [11/29/2013 aijiwei]
	uint					skill_id;				// 技能ID WeaponAttributeType
	uint					carrer_id;			// 技能对应的职业，0为非玩家角色信息中读取的技能
	float					param[SKILL_PARAM_NUM];		// 技能参数
	float					data[SKILL_DATA_NUM];				// 技能附加数据
	float					cd_time;			// 技能冷却时间。当冷却时间大于0时不能使用，但可以攻击和击杀
	bool					loop;					// 是否可循环使用。循环时必须在激活状态才会倒计时，倒计时结束技能不会删除，并且会设置为未激活状态
	//  [11/29/2013 aijiwei] end
	
	SkillDataCallBack1	use_callback;		//使用时
	SkillDataCallBack2	hit_callback;		//攻击时
	SkillDataCallBack2	kill_callback;		//击杀时
	
	SkillData()
		: duration_timer(0)
		, type(kSkill_None)
		, slot(kSkillSlot_LeftButton)
		, value(0)
		, enable(false)

		, carrer_id(0)
		, skill_id(0)
		, cd_time(0)
		, loop(false)
		
		, use_callback(NULL)
		, hit_callback(NULL)
		, kill_callback(NULL)
	{
		memset(param, 0, sizeof(param) );
		memset(data, 0, sizeof(data) );
	}
	
	std::string ToString()
	{
		char buffer[512];
	
		sprintf(buffer, "duration_timer : %f", duration_timer);
		sprintf(buffer, "%s, effect[%s]", buffer, effect.ToString().c_str());
		sprintf(buffer, "%s, type : %d", buffer, type);
		sprintf(buffer, "%s, slot : %d", buffer, slot);
		sprintf(buffer, "%s, value : %f", buffer, value);
		sprintf(buffer, "%s, enable : %d", buffer, enable);
		
		return std::string(buffer);
	}
};

typedef std::list<SkillData> SkillDataList;
typedef std::list<SkillData*> SkillDataPtrList;

class SkillEffect
{
public:
	SkillEffect(Client *pClient);
	
	~SkillEffect();
	
public:
	void Initialize();
	
	void Update(float delta);
	
	void SetEnable(const BaseItemInfo &info, bool is_enable);
	
	void ChangeBaseItemInfo(const BaseItemInfo &info, bool add, bool is_enable);
	
	bool HasEffect(EEffect type);
	
	bool SumEffect(EEffect type, float& value);
	
	void ApplyEffect(const EffectData &effectdata);
	
	void ApplySystemEffect(EffectData effectdata);
	
	void ApplySystemItemEffect(EffectData effectdata, uint item_id);//item_id不应为0
	
	void ClearAcquiredEffect(EEffect type);
	
	void CancelSustainHurt();

	void CancelInvisible();

	void OnPlayDead();
	
	const SkillDataList& GetSkill();

	SkillData* GetSkillData(uint skill_id);

	SkillData* GetSkillData(uint slot, uint career_id);
	
	const EffectDataList& GetNaturalEffect();
	
	const EffectDataList& GetAcquiredEffect();
	
public:
	// 击杀
	void OnClientKill(Client &client, const BaseItemInfo &info);
	
	// 击中
	void OnClientHit(Client &client, const BaseItemInfo &info);
	
	// 被击中
	void OnTakeDamage(Client &client);
	
	// 动作
	void OnAction();
	
	// 用技能
	void OnClientUseSkill(ESkillSlot slot);
	
public:
	// 强制发送SkillEffect，用于CM_ReadyForGame时
	void ForceSendToClient(Client &client);
	
private:
	void OnSkillEffectChanged();
	
	void ChangeBaseItemInfo_Inside(const BaseItemInfo &info, bool add, bool is_enable);

	void AddSkillFromCharacter(std::string skill_str);
	
	void ClearUp();

	void OnSkillEffectEnd(SkillData& skillData);
	
	// SM_SyncSkillEffect
	static void Send_SyncSkillEffect(Client &client, const void *userdata);

private:
	SkillDataList m_Skill;
	EffectDataList m_NaturalEffect;
	EffectDataList m_AcquiredEffect;
	
	bool m_DirtFlgSkill;
	bool m_DirtFlgNatural;
	bool m_DirtFlgAcquired;
	
	//hack
	bool m_ApplyEffectLock;
	
	Client *m_pClient;
};
