#pragma once

enum ERoomMessage
{
	RM_UpdateKeywords,
	RM_RequestClientEnter,
	RM_UpdateCharacterData,
	RM_RequestClientLeave,
	RM_ResponseBinaryRPC,
	RM_ForwardClientMessage,
	RM_ResponseDisconnect,
	RM_UpdateCharacterPing,
};

enum EGameMessage
{
	GM_ResponseClientEnter,
	GM_NotifyClientLeave,
	GM_RequestBinaryRPC,
	GM_ForwardClientMessage,
	GM_RequestDisconnect,
	GM_KickPerson,
	GM_EndRequestMatchClient,
};

enum EServerMessage
{
	// 模式无关消息 start
	SM_ResponseGameEnter = 100,	// response client enter			(done)
	SM_NotifyChat,				// chat								(done)
	SM_CharacterInfo,			// server tells character info		(done)
	SM_Loading,					// server tell loading				(done)
	SM_ClientJoin,				// send when a client joined game.	(done)
	SM_ClientLeave,				// send when a client leaved game.	(done)
	SM_SyncPlayerData,			// sync player data.				(done)
	SM_PlayerSpawn,				// tells a player had spawned.		(done)
	SM_BotSpawn,				// tells a bot had spawned.			(done)
	SM_PlayerPoke,				// a player is poking.
	SM_PlayerPokeHurt,			// a player poke hurt.
	SM_PlayerShoot,				// a player is shooting.
	SM_PlayerGrenadeThrowIn,	// a player is throwing a grenade.
	SM_PlayerGrenadeThrowStop,	// a player is throwing a grenade.
	SM_PlayerGrenadeThrowOut,	// a player is throwing a grenade.
	SM_PlayerHurt,				// a player hurt himself.
	SM_PlayerGrenadeHurt,		// a player hurt by greande.
	SM_PlayerReload,			// a player is reloading.
	SM_PlayerDropWeapon,		// a player is drop weapon
	SM_PlayerPickUpWeapon,		// a player is pick up weapon
	SM_PlayerSelectWeapon,		// a player is select a weapon
	SM_DestroyDroppedWeapon,	// dropped gun destroy
	SM_SyncTime,				// sync global time.				(done)
	SM_GameLeave,				// game leave						(done)
	SM_PlayerSetTeam,			// player set team
	SM_HealthRecover,			// health recover
	SM_PlayerSpawnTiming,		// player spawn timing				(done)
	SM_PlayerRecoverStop,		// player stop recover
	SM_PlayerKickBack,			// player shoot kickback
	SM_PlayerFlashBright,		// player flash bright
	SM_PlayerCameraFovChanged,	// palyer camera fov changed
	SM_AddDroppedWeapon,		// add dropped weapon				(done)
	SM_AddSupplyObject,			// add supply object				(done)
	SM_DestroySupplyObject,		// supply object destroy
	SM_PickUpSupplyObject,		// pick up supply object
	SM_AmmoRecover,				// ammo recover
	SM_AmmoDisappear,			// ammo disappear
	SM_ArmorRecover,			// armor recover
	SM_UseSkill,				// use skill
	SM_UseCureSkill,			// use cure skill
	SM_ChangePack,				// change pack
	SM_KickClientStart,			// kick client start
	SM_KickClientError,			// kick client error				(done)
	SM_KickClientVote,			// kick client vote					(done)
	SM_KickClientEnd,			// kick client end
	SM_RadioReport,				// radio report
	SM_PlayerReloadReady,		// a player reload ready.
	SM_PlayerActionOn,			// player action on changed
	SM_ProjectedAmmoOut,		// a player is fire a ProjectedAmmo.
	SM_ProjectedAmmoDestroy,	// a player is destroy a ProjectedAmmo.
	SM_ProjectedAmmoUpdate,		// a player is updating a ProjectedAmmo
	SM_ProjectedAmmoHurt,		// a player hurt by ProjectedAmmo.
	SM_ProjectedProdHurt,
	SM_PlayerAnimationStart,	// animation start
	SM_PlayerAnimationEnd,		// animation end
	SM_CallDoctor,				// call doctor
	SM_PlayerHitBack,
	SM_PlayerFlameShoot,		// player flame shoot
	SM_PlayerSustainHurt,		// sustain hurt
	SM_ControlPerson,			// control person
	SM_RevengPerson,
	SM_DrumEffect,				// drum check
	SM_Drink,					// drink
	SM_Cure_Power,
	SM_PlayerSpray,
	SM_PlayerSprayClear,
	SM_ChangeAmmoTeam,
	SM_SyncSkillEffect,
	SM_SpawnCoin,
	SM_AddDropSupply,			// drop supply
	SM_PickUpSupplyObjectNew,	// pick up supply object new
	SM_SynServerScriptValue,	//
	SM_SynScore,				//
	SM_MessageClient,			//
	SM_PVEAmmoOut,				//
	SM_PVEAmmoDestroy,			//
	SM_PVEAmmoHitHurt,			//
	SM_PVEAmmoExplodeHurt,		//
	SM_PVEAmmoUpdate,			//
	SM_CutHurt,					//
	SM_DummyObjectCreate,		//
	SM_DummyObjectDestory,		//
	SM_DummySyncUpdate,			//
	SM_DummyChangeOwner,		//
	SM_GunTowerShoot,
	SM_GunTowerHurt,
	SM_Teleport,
	SM_SkillHurt,				//
	SM_RefreshBagItem,
	SM_DIE_BUFF_DATA,
	// 模式无关消息 end

	// 模式相关消息 start
	// 模式通用
	SM_Authentication,			// server authentication			(done)
	SM_Initialize,				// server tells initialize info.	(done)
	SM_GameEnd,					// game end							(done)
	SM_RoundStart,				// round start						(done)
	SM_RoundStartPlay,			// round start play					(done)
	SM_RoundEnd,				// round end
	// 占点模式
	SM_SyncHoldPointInfo,		// sync hold point info
	// 推车模式
	SM_InitializePushVehiclePointInfo,
	SM_UpdatePushVehiclePointInfo,	// push vehicle info
	// 训练关
	SM_NoviceOperation,
	// BOSS模式
	SM_Boss_Flash,
	SM_Number_Timer,
	SM_BossModeAliveChanged,
	// 爆破模式
	SM_StartPlantBomb,
	SM_CancelPlantBomb,
	SM_PlantBombSuccess,
	SM_PickUpBomb,
	SM_StartDefuseBomb,
	SM_BombExploded,
	SM_DefuseBombSuccess,
	// 大哥模式
	SM_StreetKing_Flash,
	//生化模式
	SM_Zombie_Flash,
	SM_Zombie_Mode_PlayerDying,
	SM_Zombie_Mode_StartSaveDying,
	SM_Zombie_Mode_CancelSaveDying,
	SM_Zombie_Mode_Human_Respawn,
	SM_Zombie_Mode_Step_Two,
	SM_Zombie_Mode_StartGame,
	SM_ZombieBomer,		// zombie bomer
	SM_ZombieBomerHurt,	// zombie bomer hurt
	SM_ChargeSomething, // 撞到东西了
	//传统生化
	SM_CommonKingZombie_Flash,
	SM_CommonZombie_Flash,
	SM_CommonZombie_LevelChange,
	SM_CommonZombie_EnergyChange,
	SM_CommonZombie_Super,
	SM_CommonHuman_Super,
	SM_CommonZombie_Unable,
	SM_King_Zombie_Respawn,
	SM_HumanEnergyChange,
	SM_HumanPowerUp,
	SM_UseSkillSuperMan,
	SM_SkillSuperManSuccess,
	SM_CommonZombieHumanDie,
	SM_CancelInvisible,
	SM_UseSmog,
	//bosspve模式
	SM_SycnBossAction,
	//boss2模式
	SM_Boss2_Flash,
	SM_Boss2_Showtime,
	SM_Boss2_SyncData,
	//道具战
	SM_UseItem_ItemMode,
	SM_ItemMode_ZiBao,
	SM_ItemMode_SyncData,
	//TD模式
	SM_TDMode_ResHpChange,
	//MoonMode
	SM_MoonMode_PickWin,
	//SurvivalMode
	SM_UseItem_SurvivalMode,
	SM_UseItem_Trap,
	SM_UseItem_Trap_Trigger,
	SM_Trap_HP_Disappear,
	SM_SurvivalMode_Ghost,
	// 模式相关消息 end
};

enum EClientMessage
{
	// 模式无关消息 start
	CM_RequestGameEnter = 100,	// game enter request
	CM_RequestChat,				// chat												(done)
	CM_ReadyForGame,			// finished preparing resource, ready to join game.	(done)
	CM_SyncPlayerData,			// sync player data.								(done)
	CM_Shoot,					// shooting.
	CM_Poke,					// poking.
	CM_PokeHurt,				// poke hurt.
	CM_GrenadeThrowIn,			// throwing a grenade.
	CM_GrenadeThrowStop,		// throwing a grenade.
	CM_GrenadeThrowOut,			// throwing a grenade.
	CM_Hurt,					// hurt myself.
	CM_GrenadeHurt,				// hurt by grenade.
	CM_Reload,					// reloading
	CM_DropWeapon,				// drop Weapon
	CM_PickUpWeapon,			// pick up Weapon
	CM_SelectWeapon,			// select weapon
	CM_LeaveGame,				// leave game										(done)
	CM_KickBack,				// kick back
	CM_FlashBright,				// flash bright
	CM_CameraFovChanged,		// camera fov changed
	CM_PickUpSupplyObject,		// pick up supply obejct
	CM_UseSkill,				// use skill
	CM_ChangePack,				// change pack
	CM_KickClientStart,			// kick client start
	CM_KickClientVote,			// kick client vote
	CM_Suicide,					// suicide
	CM_RadioReport,				// radio report
	CM_SpawnConfirm,			// spawn confirm									(done)
	CM_ReloadReady,				// reload ready
	CM_ActionOn,				// player action on changed
	CM_ProjectedAmmoOut,		// fire a ProjectedAmmo.
	CM_ProjectedAmmoDestroy,	// destroy a ProjectedAmmo.
	CM_ProjectedAmmoUpdate,		// update a ProjectedAmmo
	CM_ProjectedAmmoHurt,		// hurt by ProjectedAmmo.
	CM_ProjectedProdHurt,		// hurt by ProjectedProd.
	CM_ChangeCareer,			//													(done)
	CM_CureCharacter,			//
	CM_PlayerAnimationStart,	// animation start
	CM_PlayerAnimationEnd,		// animation end
	CM_CallDoctor,				// call doctor
	CM_PlayerFlameShoot,		// player flame shoot
	CM_StopBurn,				// stop burn
	CM_DrumCheck,				// drum check
	CM_Drink,					// drink
	CM_Spray,					// spray
	CM_ChangeAmmoTeam,			// change ammo team
	CM_UseSpawnCoin,
	CM_PickUpSupplyObjectNew,	// pick up supply object new
	CM_OpenMessageClient,
	CM_PVEAmmoOut,				//
	CM_PVEAmmoDestroy,			//
	CM_PVEAmmoHitHurt,			//
	CM_PVEAmmoExplodeHurt,		//
	CM_PVEAmmoUpdate,			//
	CM_CutHurt,					//
	CM_DummyObjectCreate,		//
	CM_DummyObjectDestory,		//
	CM_DummySyncUpdate,			//
	CM_GunTowerShoot,			//
	CM_DummyPokeHurt,			//
	CM_DummyProjectedAmmoHurt,	//
	CM_DummyGrenadeHurt,
	CM_DummyProjectedProdHurt,
	CM_CharacterHeal,
	CM_Teleport,
	CM_ForceSpawn,
	CM_UseItem,
	CM_NEED_DIE_BUFF,
	// 模式无关消息 end

	// 模式相关消息 start
	// 训练关
	CM_NoviceOperation,			// novice operation
	// 爆破模式
	CM_StartPlantBomb,
	CM_CancelPlantBomb,
	CM_StartDefuseBomb,			// defuse bomb
	CM_CancelDefuseBomb,
	// 生化模式
	CM_StartSaveDying,
	CM_CancelSaveDying,
	CM_ChargeSomething,
	//普通生化
	CM_SkillKickBack,
	CM_UseSkillSuperMan,
	CM_CancelInvisible,
	CM_UseSmog,
	CM_SomgAreaCancel,
	//道具战
	CM_UseItem_ItemMode,
	CM_ItemMode_ZiBao,
	//TD编辑模式
	CM_SAVE_MAP,
	//moon模式
	CM_MoonBoss,
	//SurvivalMode
	CM_UseItemSurvival,
	CM_UseItemSurvivalByGhost,
	// 模式相关消息 end
};
