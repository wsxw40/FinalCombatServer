#pragma once

enum ServerError
{
	kErrorNone = 0,
};

enum ServerErrorProxy
{
	kErrorProxyStart = 10000,					// proxy error start

	kErrorProxyEnterServer,						// enter server error
	kErrorProxyEnterServerLevelHight,			// enter server client level not match
	kErrorProxyEnterServerLevelLow,				// enter server client level not match
	kErrorProxyEnterServerClientCount,			// enter server client count greater than max

	kErrorProxyChannelConnect,					// channel connect error
	kErrorProxyChannelConnectLevel,				// channel connect client level not match
	kErrorProxyChannelConnectLevelLow,				// channel connect client level not match
	kErrorProxyChannelConnectClientCount,		// channel connect client count greater than max
	kErrorProxyChannelConnectAlreadyIn,			// channel connect client already in channel

	kErrorProxyTeamInvite,						// team invite error
	kErrorProxyTeamInviteSelf,					// team invite invite self
	kErrorProxyTeamInviteAlreadyInOtherTeam,	// team invite already in other team
	kErrorProxyTeamInviteAlreadyInTeam,			// team invite already in team
	kErrorProxyTeamInviteClientError,			// team invite client error
	kErrorProxyTeamInviteNotTeamLeader,			// team invite client not team leader
	kErrorProxyTeamInviteTeamFull,				// team invite already full

	kErrorProxyTeamJoin,						// team join error
	kErrorProxyTeamJoinLeaderError,				// team join leader error
	kErrorProxyTeamJoinLeaderIdError,			// team join leader id error
	kErrorProxyTeamJoinMemberError,				// team join member error
	kErrorProxyTeamJoinTeamFull,				// team join team already full

	kErrorProxyTeamCall,						// team call error
	kErrorSamePlayerOnline,						// same player online error
	kErrorBattlePower,							// battle power not enough

	// [2015-10-8] add by dengxiaobo 关于匹配
	kErrorProxyMatchingTeamJoin,						// team join error
	kErrorProxyMatchingTeamJoinLeaderError,				// team join leader error
	kErrorProxyMatchingTeamJoinLeaderIdError,			// team join leader id error
	kErrorProxyMatchingTeamJoinMemberError,				// team join member error
	kErrorProxyMatchingTeamJoinTeamFull,				// team join team already full
	kErrorProxyMatchingTeamIsmatching,						// team join error

	kErrorProxyMatchingNone,			// todo这个是暂时的 到时要删掉
	kErrorProxyMatchingFailed,					// matching failed
	kErrorProxyCancelMatchingFailed,			// cancel matching failed
	kErrorProxyMatchingFailedServerOffline,		// matching server offline
	kErrorProxyMatchingGuildTeamNotStarted,		// guild team matching not started
	kErrorProxyMatchingGuildTeamIsStopped,		// guild team matching is stoped

	kErrorProxyTeamMatchingNotLeader,			// team matching not leader
	kErrorProxyTeamMatchingCancelNotLeader,		// team cancel matching not leader
	kErrorProxyTeamMatchingCannotFindLeader,	// 接受匹配邀请 进入房间后 找不到房主
	kErrorProxyTeamMatchingCannotFind,			// 找不到这个匹配组
	// end by dengxiaobo
	
	kErrorProxyHageBattleTime,					// 战队联赛没到时间
	kErrorProxyHappyHageBattleAlready,			// 战队联赛已参加过跳跳乐
};

enum ServerErrorChannel
{
	kErrorChannelStart = 20000,					// channel error start
	
	kErrorChannelRoomCreate,					// room create error
	kErrorChannelRoomCreateFullRoom,			// room create rooms are full 
	kErrorChannelRoomCreateClientCount,			// room create client count error

	kErrorChannelOption,						// option change error
	kErrorChannelOptionRoomError,				// option change room error
	kErrorChannelOptionLevelIdInvalid,			// option change level id invalid
	kErrorChannelOptionNotHost,					// option change client is not host
	kErrorChannelOptionRuleValue,				// option change rule value error
	kErrorChannelOptionVictoryRule,				// option change victory rule error
	kErrorChannelOptionGameType,				// option change game type error
	kErrorChannelOptionSpawnTime,				// option change spawn time error
	kErrorChannelOptionSpecialMode,				// option change special mode error
	kErrorChannelOptionDeadViewMode,			// option change dead view mode error
	kErrorChannelOptionNoGroup,					// option change group match with no group

	kErrorChannelRoomEnter,						// room enter error
	kErrorChannelRoomEnterRoomError,			// room enter room error
	kErrorChannelRoomEnterJoinOnPlaying,		// room enter can not joint on playing
	kErrorChannelRoomEnterMemberSize,			// room enter member size greater than 4
	kErrorChannelRoomEnterEnoughSlots,			// room enter has not enough empth slots
	kErrorChannelRoomEnterPassword,				// room enter password error
	kErrorChannelRoomEnterKickedClient,			// room enter kicked client

	kErrorChannelRoomChangeTeam,				// room change team error
	kErrorChannelRoomChangeTeamTeamError,		// room change team team error
	kErrorChannelRoomChangeTeamSameTeam,		// room change team changed team is same
	kErrorChannelRoomChangeTeamIdError,			// room change team team client in room id error
	kErrorChannelRoomChangeTeamIsReady,			// room change team team client is ready
	kErrorChannelRoomChangeTeamBalance,			// room change team ready count not balance

	kErrorChannelChangeSlot,					// change slot error
	kErrorChannelChangeSlotRoomError,			// change slot room error
	kErrorChannelChangeSlotNotExist,			// change slot slot not exist
	kErrorChannelChangeSlotEqual,				// change slot slot equal
	kErrorChannelChangeSlotHasClient,			// change slot slot has Client already
	kErrorChannelChangeSlotPreserveForOther,	// change slot slot preserved for other client
	kErrorChannelChangeSlotClose,				// change slot slot closed
	kErrorChannelChangeSlotReady,				// change slot client is ready
	kErrorChannelChangeSlotBalance,				// change slot ready count not balance

	kErrorChannelChangeSlotStatus,				// change slot status error
	kErrorChannelChangeSlotStatusRoomError,		// change slot status room error
	kErrorChannelChangeSlotStatusNotHost,		// change slot status client is not host
	kErrorChannelChangeSlotStatusNotExist,		// change slot status slot not exist
	kErrorChannelChangeSlotStatusStateError,	// change slot status client state error
	kErrorChannelChangeSlotStatusEqual,			// change slot status status equal
	kErrorChannelChangeSlotStatusBalance,		// change slot status ready count not balance

	kErrorChannelPreserveSlot,					// preserve slot error
	kErrorChannelPreserveSlotCannot,			// preserve slot slot can not preserve
	kErrorChannelPreserveSlotAlreadyPreserved,	// preserve slot client already preserved in this room
	kErrorChannelPreserveSlotAlreadyInRoom,		// preserve slot client already in room

	kErrorChannelClientReady,					// client ready error
	kErrorChannelClientReadyAlready,			// client ready already ready
	kErrorChannelClientReadyTeam,				// client ready team error
	kErrorChannelClientReadyHost,				// client ready client is host
	kErrorChannelClientReadyBalance,			// client ready ready count not balance

	kErrorChannelGameStart,						// game start error
	kErrorChannelGameStartBalance,				// game start client count not balance
	kErrorChannelGameStartGroupMatchNoGroup,	// game start group match client has no group
	kErrorChannelGameStartGroupMatchGroupError,	// game start group match client group not equal
	kErrorChannelGameStartGroupMatchGroupEqual,	// game start group match both group equal
	kErrorChannelGameStartGameServerError,		// game start game server start error
	kErrorChannelGameStartNoOne,				// game start game start no one

	kErrorChannelGameEnter,						// game enter error
	kErrorChannelGameEnterBalance,				// game enter client count not balance
	kErrorChannelGameEnterJoinOnPlaying,		// game enter can not join on playing
	kErrorChannelGameEnterJoinPK,				// game enter can not join on pk game
	kErrorChannelGameEnterRoomIdError,			// game enter room id not right
	kErrorChannelGameEnterLeaveWhenConnecting,	// game enter client leave when connecting
	kErrorChannelGameEnterCharacterInfoError,	// game enter client get character info error
	kErrorChannelGameEnterWeaponDurableError,	// game enter client get weapon durable is zero

	kErrorChannelKickClient,					// kick client error
	kErrorChannelKickClientNotHost,				// kick client client is not host
	kErrorChannelKickClientIdError,				// kick client id in room not right 

	kErrorChannelDeathMatchPlayerNumberError,	// game death match number is not enough
	kErrorChannelBossPlayerNumberError,			// game boss number is not enough
	kErrorChannelBoss2PlayerNumberError,		// game boss2 number is not enough
	kErrorChannelStreetBoyPlayerNumberError,	// game street boy number is not enough
	kErrorChannelZombiePlayerNumberError,
	kErrorChannelBombPlayerNumberError,

	kErrorChannelCommonZombiePlayerNumberError,

	kErrorChannelRoomCreateByGmError,

	kErrorChannelAdvPlayerNumberError,

	kErrorChannelAdvServerError,
};

enum ServerErrorGame
{
	kErrorGameStart = 30000,					// game error start

	kErrorGameUseEquipment,						// game error use equipment
	kErrorGameUseEquipmentNotAlive,				// game error use equipment client not alive
	kErrorGameUseEquipmentUseNone,				// game error use equipment client use none equipment
	kErrorGameUseEquipmentUseElse,				// game error use equipment client use else equipment
	kErrorGameUseEquipmentTeamError,			// game error use equipment team not match
	kErrorGameUseEquipmentNotInArea,			// game error use equipment client not in area
	kErrorGameUseEquipmentWeaponNotBomb,		// game error use equipment client current weapon not bomb
	kErrorGameUseEquipmentBombPlanted,			// game error use equipment bomb already planted
	kErrorGameUseEquipmentBombNotPlanted,		// game error use equipment bomb not planted
	kErrorGameUseEquipmentBombDefusing,			// game error use equipment bomb already defusing
	kErrorGameUseEquipmentBannerCaptured,		// game error use equipment banner already captured
	kErrorGameUseEquipmentBannerNotCaptured,	// game error use equipment banner not captured
	kErrorGameUseEquipmentBannerPickup,			// game error use equipment banner already Pickup
	kErrorGameUseEquipmentBannerNotPickup,		// game error use equipment banner not Pickup
	kErrorGameUseEquipmentBannerPickupId,		// game error use equipment banner pickup client not match
	kErrorGameUseEquipmentBannerNotshow,		// game error use equipment banner not show
	kErrorGameUseEquipmentBannerWin,			// game error use equipment banner already win

	kErrorGameUseEquipmentStop,					// game error use equipment stop
	kErrorGameUseEquipmentStopUseNone,			// game error use equipment client use none equipment
	kErrorGameUseEquipmentStopTeamError,		// game error use equipment team not match
};
