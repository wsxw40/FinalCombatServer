package com.pearl.manager.utils;


public class CommonMsg {
	
	public static final String GIFT_EMAIL							= "来自{0}的礼物";
	public static final String GIFT_EMAIL_SYS						= "来自系统的礼物";
	public static final String EMAIL_FROM_SYS						= "来自系统的消息";
	public static final String ERRO_MESSAGE							= "error={0}";
	
	public static final String PACKAGE_EMAIL_SYS					= "获得大礼包提醒";
	public static final String A_GIFT								= "一点小意思";
	public static final String OUT_OF_DATE_NOTICE					= "物品过期提醒";
	public static final String PACK_EXPIRE_NOTICE					= "背包中有过期或者耐久度为0的物品";
	public static final String NAI_JIU_NOTICE						= "物品耐久提醒";
	public static final String WELCOME								= "Welcome";
	public static final String MEDOL_OPEN_NOTICE					= "锦囊已经打开了";
	public static final String GIFT_BOX_OPEN_NOTICE			= "签到礼品盒已经打开了";
	
	public static final String FRIEND_ONLINE						= "{0} {1}上线了。";
	public static final String FRIEND_OFFLINE						= "{0} {1}下线了。";
	
	public static final String ADD_FRIEND_MSG						= "{0}添加您为好友。";
	public static final String ADD_GROUP_MSG						= "{0}加您进群。";
	public static final String GROUP_ADD_MSG						= "{1} {0}已进入群{1}。";
	public static final String GROUP_DELETE_MSG						= "{1} {0}已离开群{1}。";
//	public static final String GROUP_LEAVE_MSG						= "{0} 您已离开群{0}。";
	public static final String KICK_GROUP_MSG						= "{1} {0}已经将您移出群{1}。";
	
	public static final String BOXING_MSG							= "{0}摆下的擂台战开始！";
	public static final String BOXING_TEAM_MSG						= "{0}战队摆下的擂台战开始！";
	
	public static final String CHALLENGE_MSG						= "{0}挑战{1}的比赛开始！";
	public static final String CHALLENGE_TEAM_MSG					= "{0}战队挑战{1}战队的比赛开始！";
	
	public static final String BOXING_END_WIN_MSG					= "{0}守擂成功！";
	public static final String BOXING_END_LOSE_MSG					= "{0}守擂失败！";
	public static final String BOXING_TEAM_END_WIN_MSG				= "{0}战队守擂成功！";
	public static final String BOXING_TEAM_END_LOSE_MSG				= "{0}战队的擂台被{1}战队打破！";
	
	public static final String CHALLENGE_END_WIN_MSG				= "{0}挑战{1}成功！";
	public static final String CHALLENGE_END_LOSE_MSG				= "{0}挑战{1}失败！";
	public static final String CHALLENGE_TEAM_END_WIN_MSG			= "{0}战队挑战{1}战队成功！";
	public static final String CHALLENGE_TEAM_END_LOSE_MSG			= "{0}战队挑战{1}战队失败！";
	public static final String HIGHLIGHT_STORAGE 					= "'{'cmd = \"highlight_storage\",type={0},'}'";
	public static final String REFRESH_EMAIL_NUM					= "'{'cmd=\"mail\",num={0},'}'" ;
	public static final String REFRESH_TEAM_APPLICATION_NUM			= "'{'cmd=\"team_application\",num={0},'}'" ;
	public static final String REFRESH_TEAM_APPROVE_FAILED			= "'{'cmd=\"team_approve_failed\",num={0},list=\"{1}\"'}'" ;
	public static final String REFRESH_SHOP							= "'{'cmd=\"shop\",'}'";
	public static final String REFRESH_BLACKLIST					= "'{'cmd = \"black\",'}'";
	public static final String BECOME_VIP							= "'{'cmd=\"vip\",'}'";
	public static final String TEAM_NUMBER_CHANGE					= "'{'cmd=\"team_number_change\",'}'";
	public static final String REFRESH_FRIEND						= "{cmd=\"refresh_friend\",}";//刷新好友列表和组,没有用MessageFormat转所以没有单引号
	
	public static final String REFRESH_MONEY						= "'{'cmd = \"money\",gp={0},cr={1},v={2},'}'";
	public static final String REFRESH_FIGHT_NUM					= "'{'cmd = \"fightnum\",num=\"{0}\",'}'";
	public static final String REFRESH_EXP 							= "'{'cmd = \"exp\",exp={0},rank={1},'}'";
	public static final String REFRESH_BUFF_LIST					= "'{'cmd = \"refreshBuffList\",'}'";
	public static final String REFRESH_EXPIRE_BUBBLE				= "'{'cmd = \"expireBubble\",'}'";
	
	public static final String REFRESH_FRIENDNEWS 					= "'{'cmd = \"friendnew\",'}'";
	public static final String REFRESH_PLAYERTOP 					= "'{'cmd = \"top\",'}'";
	public static final String REFRESH_ABLETOJOINTEAM 				= "'{'cmd = \"ableToJoinTeam\",'}'";
	public static final String REFRESH_ONLINE_FRIEND_LIST 			= "'{'cmd = \"refreshFriendList\",'}'";
	public static final String ACCEPT_FRIEND 						= "'{'cmd = \"acceptFriend\",pid={0},pname=\"{1}\",'}'";
	public static final String ACCEPT_GROUP 						= "'{'cmd = \"acceptGroup\",pid={0},pname=\"{1}\",'}'";
	public static final String REFRESH_GROUP_LIST 					= "'{'cmd = \"refreshGroupList\",'}'";
	public static final String GROUP_LEAVE_MSG 						= "'{'cmd = \"leavegroup\",gname=\"{0}\",'}'";
	public static final String RELEASE_CHARACTER 					= "'{'cmd = \"releaseCharacter\",cid={0}'}'";
	//新开放成长任务数量
	public static final String NEWGROWTHMISSION_NUM 					= "'{'cmd = \"new_growth_mission_num\",num={0}'}'";
	
	public static final String WARN_MSG 							= "'{'cmd = \"warnMsg\",msg=\"{0}\"'}'";
	public static final String WARN_MSG_STR 						= "此职业数据异常，已恢复为初始状态，\\n购买物品将放在仓库中。";
	
	public static final String GIFT_EAMIL_ACCEPT					= "\\n领取完成。";
	public static final String SYSTEM_NAME							= "System" ;
	public static final String TEAM_APPLY							= "战队批准提醒";
	public static final String TEAM_APPLY_CONTENT					= "亲爱的玩家：\\n    您向“{0}”战队提交的申请已获得批准，恭喜成为战队成员！";
//	public static final String WELCOME_EMAIL                		= "亲爱的玩家：\\n    欢迎进入《大冲锋》，本次测试为研发删档性质，每日开服时间10:00-22:00。由于还在研发过程中，可能在数值、打击感、界面等方面存在不足，我们将通过测试进行优化改进。欢迎大家通过ESC键呼出“您提我改”界面踊跃提交您的问题和建议，谢谢！\\n\\n《大冲锋》运营团队";
	public static final String EXPIRED_ITEM_MAIL            		= "亲爱的玩家：\\n    您的以下物品已过期：{0}";
	public static final String TEAM_FIRED_MAIL						= "亲爱的玩家：\\n    您已被“{0}”战队移除。您可以重新加入其他战队或创建自己的战队。";
	public static final String TEAM_QUIT_MAIL						= "亲爱的玩家：\\n    队员“{0}”已经退出您的战队！";
	public static final String TEAM_DISMISS_MAIL					= "亲爱的玩家：\\n    您所在的“{0}”战队已经解散。您可以重新加入其他战队或创建自己的战队。";
	public static final String TEAM_REJECT_MAIL						= "亲爱的玩家：\\n    您加入战队“{0}”的申请被拒绝。您可以重新加入其他战队或创建自己的战队。";
	public static final String SYSGIFT_NEWPLAYER_MAIL				= "{0}：\\n\\t恭喜您晋升为“一级下士”。为了纪念这个时刻，我们给您准备了一点小意思。希望您能喜欢。";
	public static final String AWARD_DLB							= "“{0}”幸运地被系统抽中，获得了“{1}”！";
	public static final String STRENGTH_SYS							= "@!0@!恭喜玩家@!{0}@!0@!在强化装备时，运气不错！将“@!{2}@!0@!”强化到了@!{1}@!0@!级！战斗力大增！";
	public static final String STRENGTH_MAX_SYS						= "@!0@!恭喜玩家@!{0}@!0@!在强化装备时，人品爆发！将“@!{2}@!0@!”强化到了@!{1}@!0@!级！战斗力巨增！";
	public static final String MAGIC_BOX_SYS						= "@!0@!恭喜玩家@!{0}@!0@!在开密码箱时，人品爆发！获得了@!{1} x{2}";
//	public static final String SIGN_SYS								= "恭喜玩家{0}在开签到礼盒时，人品爆发！获得了{1}！!";
	public static final String VIP_BOX_SYS							= "@!0@!恭喜玩家@!{0}@!0@!在开VIP保险柜时，人品爆发！获得了@!{1} x{2}";
//	public static final String MYSTERY_BOX_SYS						= "恭喜玩家{0}在开神秘锦囊时，人品爆发！获得了{1}!";
//	public static final String LEVEL_PACAKGE_SYS					= "恭喜玩家{0}在开等级礼包时，人品爆发！获得了{1}!";
	public static final String LUCKY_PACAKGE_SYS					= "@!0@!恭喜玩家@!{0}@!0@!在开彩盒时，人品爆发！获得了@!{1} x{2}";
//	public static final String ADWANCED_LEVEL_PACAKGE_SYS			= "恭喜玩家{0}在开进阶礼包时，人品爆发！获得了{1}!";
	public static final String ACTIVITY_DLB							= "“{0}”在系统活动规定的时段内登录，将获得系统赠送的抵用券{1}";
	public static final String PRESENT_TO_VIP						= "亲爱的玩家：\\n    恭喜您已经成为VIP！ \\n   大冲锋送给您礼物：";
	public static final String PRESENT_TO_XUNLEI_VIP				= "亲爱的迅雷VIP玩家：\\n    恭喜您已经成为VIP！ \\n   大冲锋送给您礼物：";
	public static final String PRESENT_TO_PLAYER					= "亲爱的玩家：\\n    恭喜您已获得系统送出的“{0}”道具奖励，请您在【仓库】中查收。\\n    《大冲锋》运营团队\\n    {1}";
	public static final String TEAM_DISMISS							= "战队解散提醒";
	public static final String TEAM_FIRED							= "战队开除提醒";
	public static final String TEAM_QUIT							= "战队成员退出提醒";
	public static final String TEAM_REJECT							= "申请加入战队失败";
	public static final String TEAM_RENAME_MAIL_SUBJECT				= "战队更名提醒";
	public static final String TEAM_RENAME_MAIL_CONTENT				= "亲爱的玩家：\\n    您所在的战队“{0}”已经更名为“{1}”（战队群组名会在您结束一局游戏或者下次登录时更新）。";
	public static final String RESET_WEAPON_MAIL_SUBJECT			= "武器重置提醒";	
	public static final String RESET_WEAPON_MAIL_CONTENT			= "亲爱的玩家：\\n    由于您的帐号异常，已经重置您的武器库，请重新装备您的武器。对此带来的不便，在此表示抱歉。";	
	public static final String MEDOL_GIFTS_CONTENT					= "亲爱的玩家：\\n    您已经打开一个锦囊，恭喜您获得如下奖品。请您在【仓库】中查收。";
	public static final String GIFT_BOX_CONTENT						= "亲爱的玩家：\\n    您已经打开一个签到礼品盒，恭喜您获得如下奖品。请您在【仓库】中查收。";
	
	/*GM filter*/
	public static final String GIFT_TO_PLAYER						= "赠送礼物给单个玩家";
	public static final String GIFT_TO_PLAYERS						= "赠送礼物给多个玩家";
	public static final String ADD_SYS_CONFIG						= "添加系统配置";
	public static final String ADD_SYS_CHARACTER					= "添加系统角色";
	public static final String ADD_SERVER							= "新建服务器";
	public static final String ADD_CHANNEL							= "新建频道";
	public static final String ADD_MAP								= "新建地图";
	public static final String ADD_LEVEL_WEAPON						= "新建LevelWeapon";
	public static final String ADD_SYS_ITEM							= "添加SysItem";
	public static final String ADD_PAYMENT							= "添加Payment";
	public static final String ADD_SYS_NOTICE						= "新建系统公告";
	public static final String ADD_GM_NOTICE						= "新建及时公告";
	public static final String ADD_BANNED_IP						= "禁用IP";
	public static final String ADD_BANNED_WROD						= "添加敏感词汇";
	public static final String SEND_SYS_MESSAGE						= "发送系统消息";
	public static final String KICK_PLAYER							= "踢出玩家";
	public static final String ADD_GM_USER							= "创建GM用户";
	public static final String ADD_GM_GROUP							= "创建GM组";
	public static final String ADD_GM_USER_TO_GROUP					= "添加GM用户至组";
	public static final String PRIVILEGE_TO_GROUP					= "为组分配权限";
	public static final String DEL_CHARACTER						= "删除系统角色";
	public static final String DEL_SYS_CONFIG						= "删除系统配置";
	public static final String DEL_SERVER							= "删除服务器";
	public static final String DEL_CHANNEL							= "删除频道";
	public static final String DEL_PACKGE_ITEM						= "删除背包中所有物品";
	public static final String DEL_SYS_ITEM							= "删除SysItem";
	public static final String DEL_PAYMENT							= "删除Payment";
	public static final String DEL_PLAYER_ITEM						= "删除用户物品";
	public static final String DEL_MAP								= "删除地图";
	public static final String DEL_LEVEL_WEAPON						= "删除LevelWeapon";
	public static final String DEL_SYS_NOTICE						= "删除系统公告";
	public static final String DEL_BANNED_IP						= "解禁IP";
	public static final String DEL_GM_GROUP							= "删除GM组";
	public static final String DEL_GM_USER_FROM_GROUP				= "将GM用户从GM组中移出";
	public static final String DEL_GM_USER							= "删除GM用户";
	public static final String UPDATE_CHARACTER						= "修改系统角色";
	public static final String UPDATE_PLAYER						= "修改玩家信息";
	public static final String UPDATE_SERVER						= "修改服务器信息";
	public static final String UPDATE_MAPS							= "修改多个地图信息";
	public static final String UPDATE_MAP							= "修改单个地图信息";
	public static final String UPDATE_LEVEL_WEAPONS					= "修改LevelWeapons";
	public static final String UPDATE_CHANNEL						= "修改频道信息";
	public static final String UPDATE_SYS_ITEM						= "修改系统物品";
	public static final String UPDATE_SYS_NOTICE					= "修改系统公告";
	public static final String UPDATE_BANNED_IP						= "修改禁用IP";
	public static final String UPDATE_BLACK_WHITE					= "修改黑白名单";
	public static final String UPDATE_BANNED_WORD					= "修改敏感词汇";
	public static final String LOAD_BANNED_WORD						= "加载敏感词汇库文件";
	public static final String UPDATE_SYS_CONFIG					= "修改系统配置";
	public static final String UPDATE_GM_USER						= "修改GM用户信息";
	public static final String UPDATE_GM_GROUP						= "修改GM组信息";
	
	public static final String TIME_OF_GET_GIFT 					= "获取邮件用时：";
	public static final String TONGZIJUNBIYE 						= "童子军毕业";
	public static final String NEW_PLAYER_G							= "新手关";
	public static final String FINISH_ACHIEVEMENT_WITH_GIFT			= "亲爱的玩家：\\n    恭喜您达成“{0}”的成就条件。 \\n   同时获得以下成就奖励：\\n";
	public static final String FINISH_ACHIEVEMENT_WITHOUT_GIFT		= "亲爱的玩家：\\n    恭喜您达成“{0}”的成就条件。 \\n";
	public static final String NEW_MISSION							= "任务授权书";
	public static final String NEW_MISSION_LIST						= "亲爱的玩家：\\n    您获得了如下任务:\\n{0}\\n请尽快完成任务！";
	public static final String FINISH_MISSION						= "亲爱的玩家：\\n    恭喜您完成了以下任务：“{0}”，获得如下奖励：\\n";
	public static final String FINISH_ACTIVITY						= "亲爱的玩家：\\n    恭喜您{0}活动中获得如下奖励：\\n";
	public static final String PACKAGE_EMAIL						= "亲爱的玩家：\\n    您获得了大礼包：“{0}”，以下的物品已经放到您的背包里（如果大礼包包含C币道具将直接添加）：";
	public static final String TITLE1 								= "无耻混蛋";
	public static final String TITLE2								= "不死之身";
	public static final String TITLE3 								= "超级战士";
	public static final String TITLE4 								= "人肉炸弹";
	
	public static final String[] GUN_PROPERTY_MSG   				= {
		"击中:对方{0}秒内减慢移动速度{1}%",//1,10,0,5
		"击中:对方{0}秒内受到的攻击都是暴击",//2,0,0,5
		"击中:对方{0}秒内受到的攻击增加35%",
		"击中:对方{0}秒内每秒减少{1}的血量",//4,10,1,5
		"击中:对方{0}秒内子弹抗性减少{1}%",
		"击中:对方{0}秒内爆炸抗性减少{1}%",
		"击中:对方{0}秒内近身抗性减少{1}%",
		"击中:对方{0}秒内燃烧抗性减少{1}%",
		"击中:对方{0}秒内减少{1}%医疗包补血量",
		"击中:对方{0}秒内减少{1}%的被治疗量",
		"击中:对方{0}秒内无法隐形",
		"击中:对方{0}秒内鼠标反转",
		"击中:对方{0}秒内视野模糊",
		"装备:具备火箭跳功能",//14,0,0,0  //14
		
		"","","","","","","","","","","","","","","",
		
		"特性:每次杀人增加移动速度的{0}%",//30
		"特性:血量每减少{0}%伤害就增加{1}%",//31,10,30,0
		"负面:无法造成随机暴击",
		"特性:每次杀人获得{0}秒的暴击状态",
		"特性:每次杀人后{0}秒内伤害为115%",
		"特性:击中对方为自己恢复{0}的血量",
		"特性:每次杀人增加{0}%的血量上限",
		"特性:每次杀人恢复血量{0}",
		"特性:击中对方背后都是小暴击",
		"特性:对燃烧的敌人{0}%几率增加35%伤害",
		"击中:增加{0}%充能能量",
		"特性:每次杀人增加{0}%充能能量",
		"击中:增加{0}%隐形能量",
		"特性:每次杀人后对方会掉落医疗包",
		"特性:武器对敌方建筑增加{0}%的伤害",
		"特性:当前武器对敌方增加{0}%的伤害",//45
		"特性:静止时进入半透明状态",
		"特性:静止时射击逐渐提高精准度",
		"","","","","","","","","","","","",
		
		"装备:免疫减速",//60
		"装备:移动速度{0}%",
		"装备:免疫随机暴击",
		"装备:不受余焰影响",
		"装备:不受流血影响",
		"负面:承受的伤害增加35%",
		"装备:医疗包补血量少量增加",
		"装备:子弹抗性{0}%",
		"装备:爆炸抗性{0}%",
		"装备:近身抗性{0}%",
		"装备:火焰抗性{0}%",
		"装备:医疗包补血量略微增加",
		"装备:被治疗时的治疗量{0}%",
		"装备:血量上限{0}%",
		"装备:子弹上膛量{0}%",
		"装备:载弹量{0}%",
		"装备:攻击速度{0}%",
		"装备:换弹速度{0}%",
		"装备:切换武器速度{0}%",
		"装备:玩家造成伤害{0}%",
		"负面:无法被医疗兵治疗或连携冲锋",
		"装备:子弹飞行速度{0}%",
		"装备:子弹爆炸范围{0}%",
		"装备:玩家对自身伤害减少{0}%",
		"装备:子弹延迟爆炸时间({0}%",
		"装备:热枪速度{0}%",
		"装备:使用机枪时移动速度{0}%",
		"装备:充能速度{0}%",
		"装备:建造速度{0}%",
		"装备:建造出的建筑血量{0}%",
		"装备:增加治疗枪充能速度{0}%",
		"装备:每秒自动回复血量{0}",
		"不能使用灭火功能",
		"具备瞄镜辅助功能",//93
		"装备:血量每减少{0}%移动速度{1}%",
		"","","","","",
		"技能:使用后周围友军{0}秒内攻击{1}%",
		"技能:使用后周围友军{0}秒内防御{1}%",
		"技能:使用后3秒内回复200血量",//102
		"","","","","","","","",//110
		"","","","","","","","","","",//120
		"",
		"装备:游戏中获得积分提高{0}%",//122
		"","","","","","","","",
		"","","","","","","","","","",
		"","","","","","","","","","技能:短时内加强治疗量和自身恢复量",//150
		"技能:短时内治疗对象和自己无敌",
		"技能:短时内对象和自身提高暴击率",
	};
}
