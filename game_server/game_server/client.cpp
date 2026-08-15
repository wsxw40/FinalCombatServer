#include "pch.h"
#include <time.h>

#define INPUT_LIMIT_TIME 180
#define BOMB_SLOT 4

#define DEFAULT_ZOMBIE_NORMAL 20
#define DEFAULT_ZOMBIE_BOMB 21
#define DEFAULT_ZOMBIE_KING 30
#define MAX_HUMAN_ENERGY 1000
#define PRODDAMAGE		160

#define BOSS2_ITEMID_PASSIVESKILL	1
#define BOSS2_ITEMID_INITIATIVE		2
#define BOSS2_ITEMID_OTHER			3

#define ITEMMODE_ITEMID				1
#define ITEMMODE_ITEMID2			2
#define ITEMMODE_ITEMID3			3

#define BUFF_ZOMBIE_ID				51

static const float BASE_VALUE = 1.071f * 1.012f;
const int MOONSCORE[8] = {3400,2720,2040,1700,1360,510,340,170};
const int SURVIVALBAGSIZE = 5;
const int SURVIVALGHOSTBAGSIZE = 1;
float Client::boss2_defence_energy = 0;
float Client::boss2_defence_energy_max = 1000;
int Client::boss2_defence_energy_level = 0;

enum EClientState
{
	CS_Disconnected = -1,
	CS_Connected,
	CS_Loading,
	CS_Waiting,
	CS_Alive,
	CS_Died,
	CS_ErrorWrite,
};

enum EPlayerData
{
	PD_Status			= 1 << 0,
	PD_Rotation			= 1 << 1,
	PD_Position			= 1 << 2,
	PD_Ping				= 1 << 3,
};

enum EPlayerStatus
{
	PS_Jump				= 1 << 0,
	PS_OnGround			= 1 << 1,
	PS_Crouch			= 1 << 2,
	PS_Walk				= 1 << 3,
	PS_MoveForward		= 1 << 4,
	PS_MoveBack			= 1 << 5,
	PS_MoveLeft			= 1 << 6,
	PS_MoveRight		= 1 << 7,
};

enum LeaveGameReason
{
	kLeaveGameReasonNone,
	kLeaveGameReasonIdle,
	kLeaveGameReasonSuccessNovice,
	kLeaveGameReasonFailedNovice,
};

#define	SELFRECOVER_INTERVAL	1

// -----------------------------------------------------------------
// Formating functions
// -----------------------------------------------------------------

float LengthXZOnly(const Vector3 & a)
{
	return sqrtf(a.x * a.x + a.z * a.z);
}

void Client::WriteVector3FP(const Vector3 & position)
{
	short x, y, z;

	x = (short)Clamp(position.x * 128.f, -32767.f, 32767.f);
	y = (short)Clamp(position.y * 128.f, -32767.f, 32767.f);
	z = (short)Clamp(position.z * 128.f, -32767.f, 32767.f);

	WriteShort(x);
	WriteShort(y);
	WriteShort(z);
}

void Client::ReadVector3FP(Vector3 & position)
{
	short x, y, z;

	ReadShort(x);
	ReadShort(y);
	ReadShort(z);

	position.x = (float)x / 128.f;
	position.y = (float)y / 128.f;
	position.z = (float)z / 128.f;
}


void Client::WriteCharacterRotation(const Quaternion & rotation)
{
	Vector3 r = rotation.GetZXY();
	WriteShort((short)(r.x * 8192.f));
	WriteShort((short)(r.y * 8192.f));
}

void Client::ReadCharacterRotation(Quaternion & rotation)
{
	short x, y;

	ReadShort(x);
	ReadShort(y);

	rotation.SetZXY(Vector3((float)x / 8192.f, (float)y / 8192.f, 0));
}


static void GetCharacterItemLevel(const SingleCharacterInfo &info, int &a, int &b, int &c, int &d)
{
	a = 0;
	b = 0;
	c = 0;
	d = 0;

	if (info.packs.size() > 0 && info.packs[0].weapon_set.size() > 0)
	{
		a = info.packs[0].weapon_set.begin()->second.base_info.level;
		if (a > 99)
			a = 0;
	}

	if (info.costumes.size() > 0)
	{
		b = info.costumes[0].base_info.level;
		if (b > 99)
			b = 0;
	}

	if (info.costumes.size() > 1)
	{
		c = info.costumes[1].base_info.level;
		if (c > 99)
			c = 0;
	}

	if (info.costumes.size() > 2)
	{
		d = info.costumes[2].base_info.level;
		if (d > 99)
			d = 0;
	}

	return;
}

/// random float
static float RandomFloat(float x, float y)
{
	float r = (float)rand() / (RAND_MAX);
	float num = x + (y - x) * r;
	return num; 
}

// -----------------------------------------------------------------
// Messages sending to client
// -----------------------------------------------------------------
// send character info
void Client::SendCharacterInfo(Client & client)
{
	if (client.IsConnected())
	{
		BeginWrite();
		WriteByte(SM_CharacterInfo);
		WriteByte(client.uid);
		WriteByte(client.team);
		WriteBaseCharacterInfo(*this, client.GetCharacterInfo());
		if (kTDMode == server.game_type)
		{
			WriteBagInfo(*this, client.GetCharacterInfo().bag);
		}
		WriteSingleCharacter(*this, client.GetCurCharinfo());
		EndWrite();

		server.ForwardClientMessage(*this);
	}
}

// sync game
void Client::Initialize()
{
	////other
	buff_zombie_id = -1;
	sync_holdinfo_timer = 0;
	leavereason = kLeaveGameReasonNone;
	m_bRequestLeaved = false;
	cure_limit_timer = 0;
	input_limit_timer = -1;
	drinkrecover = 0;
	drinkrecovercount = -1;
	gun_tower_hand_info.weapon_data.weapon.type = kWeaponTypeNone;
	
	{
		boss2_human_energy = 0;
		boss2_human_energy_level = 1;
		boss2_human_energy_max = server.config.boss2_human_energy_max_init;
		
		boss2_passiveskill_level[0] = 0;
		boss2_passiveskill_level[1] = 0;
		boss2_passiveskill_level[2] = 0;
		boss2_passiveskill_level[3] = 0;
		
		boss2_initiative_type = 0;
		
		boss2_strange_spawn_use = false;
		boss2_strange_spawn = -1;
	}
	
	{
		itemmode_energy = 0;
		itemmode_energy_max = server.config.itemmode_energy_max_init;
		
		itemmode_item_slot = -1;
		
		itemmode_zibao = false;
		ismoonboss = false;
		ismoonbossflag = false;
	}

	accumulate_damage = 0;
	pack_used = 0;
	weapon_used = false;
	cure_list.clear();
	pickup_dropitems.clear();
	play_time = (int)server.time_max;
	life_time = 0;
	zombie_skill_cd = 0.f;
	SetCurCharInfo(target_career);

	log_write(LOG_DEBUG4, "client initialize, uid : %d", uid);

	round = server.round;
	control_person_id = -1;
	control_person_old_id = -1;
	reveng_person_id.clear();

	projected_ammo_set.clear();

	ischarge_skill = false;
	charge_skill_time = -1;
	selfrecover_interval = SELFRECOVER_INTERVAL;
	
	itemmode_skill_cd = -1;

	newDamage = 0;
	newResistance = 0;

	BeginWrite();
	WriteByte(SM_Initialize);

	// write player info
	WriteByte(uid);
	WriteByte(team);
	WriteByte(is_vip);
	WriteByte(business_card);
	WriteByte(is_gm);
	WriteString(head_icon);
	WriteInt(level);
	WriteIntArray(server.team_kills, 2);
	switch (server.game_type)
	{
	case Game::kHoldPoint:
	case Game::kPushVehicle:
	case Game::kNovice:
	case Game::kBombMode:
		WriteIntArray(server.team_rounds, 2);
		break;

	default:
		WriteIntArray(server.team_rounds, 2);
		break;
	}

	WriteByte(server.GetScoreType());
	WriteInt(play_time);
	WriteByte(server.team_hurt);

	WriteInt(kick_client_count);

	WriteByte(server.game_type);

	switch (server.game_type)
	{
	case Game::kTeam:
	case Game::kKnifeMode:
	case Game::kEditMode:
		break;
	case Game::kTDMode:
		{
			WriteInt(server.max_reshp);
			WriteInt(server.cur_reshp);
		}
		break;
	case Game::kBombMode:
		{
			WriteByte(server.bomb_planted ? 1 : 0);
			if(server.bomb_planted)
			{
				WriteVector3(server.bomb_planted_pos);
				WriteFloat(server.bomb_explode_timer);
			}
		}
		break;
	case Game::kStreetBoyMode:
		{
			WriteByte(server.street_king_info[0].current_street_king_uid);
			WriteByte(server.street_king_info[1].current_street_king_uid);
		}
		break;
	case Game::kZombieMode:
		{
			WriteFloat(server.config.round_time_stepone_zombie);
		}
		break;
	case Game::kCommonZombieMode:
		{
			for (std::vector<BuffItem>::const_iterator i = GetCharacterInfo().item_set.begin(); 
				i < GetCharacterInfo().item_set.end(); i++)
			{
				log_write(LOG_DEBUG3, "i->id = %d", i->type);
				if (i->type == BUFF_ZOMBIE_ID)
				{
					buff_zombie_id = int(i->value);
					log_write(LOG_DEBUG3, "buff_zombie_id = %d", buff_zombie_id);
				}
			}
		}
		break;
	case Game::kBossMode2:
		{
			WriteInt(server.level_info.boss_info.career_id);
			WriteInt(server.level_info.bosspve_info[0].career_id);
			WriteInt(server.level_info.bosspve_info[1].career_id);
			WriteInt(server.level_info.bosspve_info[2].career_id);
			WriteInt(server.level_info.bosspve_info[3].career_id);
		}		
		break;
	case Game::kItemMode:
	case Game::KMoonMode:
		{
			WriteInt(server.level_info.boss_info.career_id);
		}		
		break;
	}

	int write_count = 0;
	// write player info
	for (int i = 0; i < max_client_count; i++)
	{
		Client& c = server.clients[i];

		if (c.IsReady() && &c != this)
		{
			write_count++;
			
			WriteByte(c.uid);
			WriteString(c.GetCharacterInfo().character_name);
			WriteShort(c.status);
			WriteByte(c.team);
			WriteByte(c.is_vip);
			WriteByte(c.business_card);
			WriteString(c.head_icon);
			WriteInt(c.level);
			WriteByte(c.GetCurWeapon());
			WriteShort(c.ping);
			WriteInt(c.health);
			WriteInt(c.max_health);
			WriteInt(c.ex_health);
			WriteInt(c.armor);
			WriteShort(c.num_kill);
			WriteShort(c.num_die);
			WriteShort(c.assist_num);
			WriteInt(c.data.GetScore());
			WriteVector3(c.position);
			WriteQuaternion(c.rotation);
			WriteByte(c.playing);
			WriteByte(c.connected);
			WriteByte(c.IsAlive());
			WriteSingleCharacter(*this, c.GetCurCharinfo());
			WriteByte(server.bomb_owner_id == c.uid);
		}
	}
	WriteByte(0);

	// write bot info
	for (int i = 0; i < max_botclient_count; i++)
	{
		Client& c = server.bot_clients[i];

		if (c.IsReady() && &c != this)
		{
			write_count++;

			WriteByte(c.uid);
			WriteString(c.GetCharacterInfo().character_name);
			WriteShort(c.status);
			WriteByte(c.team);
			WriteByte(c.is_vip);
			WriteByte(c.business_card);
			WriteByte(c.is_gm);
			WriteString(c.head_icon);
			WriteInt(c.level);
			WriteByte(c.GetCurWeapon());
			WriteShort(c.ping);
			WriteInt(c.health);
			WriteInt(c.max_health);
			WriteInt(c.ex_health);
			WriteInt(c.armor);
			WriteShort(c.num_kill);
			WriteShort(c.num_die);
			WriteShort(c.assist_num);
			WriteInt(c.data.GetScore());
			WriteVector3(c.position);
			WriteQuaternion(c.rotation);
			WriteByte(c.playing);
			WriteByte(c.connected);
			WriteByte(c.IsAlive());
			WriteSingleCharacter(*this, c.GetCurCharinfo());
			WriteByte(server.bomb_owner_id == c.uid);
		}
	}
	WriteByte(0);

	EndWrite();
	
	DEBUGLOG_WRITE("SM_Initialize name : %s, write_count : %d", GetCharacterInfo().character_name, write_count);
	
	// send client effect
	for (uint i = 0; i < max_client_count; i ++)
	{
		Client* c = server.clients + i;
		
		if (c->IsReady())
			c->GetSkillEffect().ForceSendToClient(*this);
	}
	for (int i = 0; i < server.dummy_create_vector.size(); i++)
	{
		int dummy_id = server.dummy_create_vector[i].id;

		std::map<uint, DummyBaseInfo>::iterator itor = server.dummy_object_map.find(dummy_id);
		if(itor != server.dummy_object_map.end())
		{
			NotifyDummyCreate(itor->second);
		
		}
	}
}

void Client::UpdatePlayerPing(uint value)
{
	ushort v = value > 65535 ? 65536 : value;

	// HACK: make ping smaller, the value is half of averange delay time.
	v = v > 26 ? v - 25 : 1;

	if (v != ping)
	{
		ping = v;
		sync_flags |= PD_Ping;

		SyncPlayerData();
	}
}

void Client::UpdatePlayerSpeed()
{
	float move_speed = 0;
	SingleCharacterInfo& single_char = GetCurCharinfo();
	Weapon& weapon = GetCurWeaponInfo();
	
	// if (status & PS_Walk)
		// move_speed += single_char.walk_speed;
	// else if (status & PS_Crouch)
		// move_speed += single_char.crouch_speed;
	// else
		move_speed += single_char.run_speed;
	
	float value = 1;
	if (effect.SumEffect(kEffect_Infect_MoveSpeed, value))
	{
		move_speed *= value;
	}
	
	value = 1;
	if (effect.SumEffect(kEffect_Infect_HpInfectMoveSpeed, value))
	{
		float lost_hp = Max(max_health - health, 0);
		value += value * (lost_hp / max_health) * 100.f;
	
		move_speed *= value;
	}

	move_speed += weapon.weapon_data.weapon.move_speed_offset;
	
	checkcheat_movespeed = move_speed;
}

// -----------------------------------------------------------------
// Messages parsing functions
// -----------------------------------------------------------------
void Client::ParseChat()
{
	char channel[character_name_length];
	char msg[chat_length];

	ReadString(channel, sizeof(channel));
	ReadString(msg, sizeof(msg));

	if (gag.Send())
	{
		DictMatch::Replace(msg);

		if (strcmp(channel,"/t") == 0 || strcmp(channel,"/x") == 0)
		{
			for (Client * c = server.clients; c < server.clients + max_client_count; c++)
			{
				if (c->IsReady() && c->team == this->team)
				{
					c->BeginWrite();
					c->WriteByte(SM_NotifyChat);
					c->WriteString(channel);
					c->WriteString(GetCharacterInfo().character_name);
					c->WriteString(msg);
					c->EndWrite();
				}
			}
		}
		else
		{
			for (Client * c = server.clients; c < server.clients + max_client_count; c++)
			{
				if (c->IsReady())
				{
					c->BeginWrite();
					c->WriteByte(SM_NotifyChat);
					c->WriteString(channel);
					c->WriteString(GetCharacterInfo().character_name);
					c->WriteString(msg);
					c->EndWrite();
				}
			}
		}
	}
	else
	{
		BeginWrite();
		WriteByte(SM_NotifyChat);
		WriteString("/gag");
		WriteString("");
		WriteString("");
		EndWrite();
	}
}

void Client::ParseSyncPlayerData()
{
	if (team > 1)
		return;

	if (!IsAlive())
		return;

	byte flags;
	byte delta;
	short ping; // ping from client readed to here.

	ReadByte(delta);
	ReadByte(flags);

	// update sync time
	float delta_time = (float)delta / 255.f;
	sync_time += delta_time;
	checkcheat_delta += delta_time;

	// updat sync flags, except ping
	sync_flags |= (flags & ~PD_Ping);

	if (flags & PD_Status)		ReadShort(status);
	if (flags & PD_Ping)		ReadShort(ping);

	if (flags & PD_Position)
	{
		ReadVector3FP(position);
		OnPositionChanged();
	}

	if (flags & PD_Rotation)
	{
		Quaternion old_rot = rotation;
		ReadCharacterRotation(rotation);
		OnRotationChanged(old_rot, rotation);
	}

	SyncPlayerData();
}

// sync data
void Client::SyncPlayerData()
{
	if (sync_flags)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_SyncPlayerData);
				c->WriteByte(uid);
				c->WriteByte(static_cast<byte>(Clamp(sync_time, 0.f, 1.f) * 255));
				c->WriteByte(sync_flags);

				if (sync_flags & PD_Status)		c->WriteShort(status);
				if (sync_flags & PD_Ping)		c->WriteShort(ping);
				if (sync_flags & PD_Position)	c->WriteVector3FP(position);
				if (sync_flags & PD_Rotation)	c->WriteCharacterRotation(rotation);

				c->EndWrite();
			}
		}

		BeginWrite();
		WriteByte(SM_SyncPlayerData);
		WriteByte(uid);
		WriteByte(static_cast<byte>(Clamp(sync_time, 0.f, 1.f) * 255));
		WriteByte(PD_Ping);
		WriteShort(ping);
		EndWrite();

		sync_flags = 0;
		sync_time = 0;
	}
}

// get weapon damage
float Client::GetWeaponDamage(const Weapon & w, float distance, Client * hurt_client, bool *pboost, byte hurt_type, bool back, float range_ratio)
{
	float weapon_damage = w.weapon_data.weapon.damage_modifier - (rand()%(w.weapon_data.weapon.damage_modifier * 2 + 1));

	if (hurt_client)
	{
		switch (w.weapon_data.weapon.type)
		{
		case kWeaponTypePistol:
		case kWeaponTypeDualPistol:
		case kWeaponTypeSubMachineGun:
		case kWeaponTypeRifle:
		case kWeaponTypeSniperGun:
		case kWeaponTypeShotGun:
		case kWeaponTypeMiniGun:
		case kWeaponTypeMiniMachineGun:
		case kWeaponTypeBow:
			{
				// calculate damage
				float s = fmaxf(fminf((distance - w.weapon_data.gun.range_start) / (w.weapon_data.gun.range_end - w.weapon_data.gun.range_start), 1), 0);
				float s2 = s * s;
				float s3 = s2 * s;
				float h00 =  2 * s3 - 3 * s2 + 1;
				float h01 = -2 * s3 + 3 * s2;

				float factor = 1.f * h00 + w.weapon_data.gun.range_modifier * h01;

				weapon_damage = w.weapon_data.gun.damage + weapon_damage;

				weapon_damage = weapon_damage * factor;

				if(*pboost)
				{
					weapon_damage = weapon_damage * 4;
				}
			}
			break;
		case kWeaponTypeFlameGun:
			{
				// calculate damage
				float s = fmaxf(fminf((distance - w.weapon_data.gun.range_start) / (w.weapon_data.gun.range_end - w.weapon_data.gun.range_start), 1), 0);
				float s2 = s * s;
				float s3 = s2 * s;
				float h00 =  2 * s3 - 3 * s2 + 1;
				float h01 = -2 * s3 + 3 * s2;

				float factor = 1.f * h00 + w.weapon_data.gun.range_modifier * h01;

				weapon_damage = w.weapon_data.gun.damage + weapon_damage;

				weapon_damage = weapon_damage * factor;

				if(*pboost)
				{
					weapon_damage = weapon_damage * 4;
				}
			}
			break;
		case kWeaponTypeKnife:
			{
				float hurt_damage = 0;

				if (hurt_type % 2)
					hurt_damage = w.weapon_data.knife.stab_hurt;
				else
					hurt_damage = w.weapon_data.knife.stab_light_hurt;

				if (back)
					hurt_damage *= w.weapon_data.knife.back_factor;

				weapon_damage = hurt_damage + weapon_damage;

				if(*pboost)
				{
					weapon_damage *= 3;
				}
			}
			break;
		case kWeaponTypeZombieGun:
			{
				float hurt_damage = 0;

				if (hurt_type % 2)
					hurt_damage = w.weapon_data.zb_info.stab_light_hurt;
				else
					hurt_damage = w.weapon_data.zb_info.stab_hurt;

				if (back)
					hurt_damage *= w.weapon_data.zb_info.back_factor;

				weapon_damage = hurt_damage + weapon_damage;

				if(*pboost)
				{
					weapon_damage *= 3;
				}
			}
			break;
		case kWeaponTypeZombieCharge:
			{
				weapon_damage = w.weapon_data.zbc_info.skill_hurt + weapon_damage;

				if(*pboost)
				{
					weapon_damage *= 3;
				}
			}
			break;
		case kWeaponTypeAmmoRocket:
		case kWeaponTypeAmmoGrenade:
		case kWeaponTypeAmmoStick:
			{
				float range = w.weapon_data.ammo.range;
				float value = 1;
				if (effect.SumEffect(kEffect_Infect_AmmoExplodeRange, value))
				{
					range *= fmaxf(value, 0);
				}
				
				if (distance <= range)
				{
					float dist_modifier = 1.0f - (distance / range) * (1 - 0.5f);
					float range_modifier = fmaxf(range_ratio, 0.5f);
					weapon_damage = (w.weapon_data.ammo.damage) * dist_modifier * range_modifier  + weapon_damage;
					if(*pboost)
					{
						weapon_damage = weapon_damage * 4;
					}
				}
				else
				{
					weapon_damage = 0;
				}
			}
			break;
		case kWeaponTypeAmmoMeditNeedle:
			{
				weapon_damage = (w.weapon_data.ammo.damage) * fmax(range_ratio, 0.42f)  + weapon_damage;

				if(*pboost)
				{
					weapon_damage = weapon_damage * 4;
				}
			}
			break;
		case kWeaponTypeAmmoProd:
			{
				float range = w.weapon_data.ammo.range;
				float value = 1;
				if (effect.SumEffect(kEffect_Infect_AmmoExplodeRange, value))
				{
					range *= fmaxf(value, 0);
				}

				if (distance <= range)
				{
					weapon_damage = (w.weapon_data.ammo.damage) + weapon_damage;
					if(*pboost)
					{
						weapon_damage = weapon_damage * 4;
					}
				}
				else
				{
					weapon_damage = 0;
				}
			}
			break;
		case kWeaponTypeAmmoBloodDisk:
			{
				weapon_damage = (w.weapon_data.ammo.damage) + weapon_damage;

				if(*pboost)
				{
					weapon_damage = weapon_damage * 4;
				}
			}
			break;
		case kWeaponTypeGrenade:
		case kWeaponTypeSpecial:
			{
				weapon_damage = (w.weapon_data.grenade.hurt) * powf(1.f - distance / w.weapon_data.grenade.range, 0.7f) + weapon_damage;
				if(*pboost)
				{
					weapon_damage = weapon_damage * 4;
				}
			}
			break;
		default:
			weapon_damage = 0;
			break;
		}
	}
	
	return DamageBeforeResistance(weapon_damage, w, hurt_client);
}

float Client::DamageBeforeResistance(float damage, const Weapon & weapon, Client* hurt_client)
{
	if (weapon.base_info.player_item_id != 0 && 
		weapon.base_info.player_item_id != GetCurWeaponInfo().base_info.player_item_id)
	{
		DEBUGLOG_WRITE("DamageBeforeResistance() [No Damage Infect] name : %s", GetCharacterInfo().character_name);
		
		return damage;
	}
	
	// 计算伤害变化倍率
	float lost_hp = Max(max_health - health, 0);
	float value = 1.f;
	effect.SumEffect(kEffect_Infect_Damage, value);
	{
		float v_tmp = 0;
		if (effect.SumEffect(kEffect_Infect_HpInfectDamage, v_tmp))
		{
			value += v_tmp * (lost_hp / max_health) * 100.f;
		}
	}
	if (hurt_client)
	{
		switch (hurt_client->GetCurCharinfo().career_id)
		{
			case 1:
				effect.SumEffect(kEffect_Infect_CareerDamage_Id1, value);
				break;
			case 2:
				effect.SumEffect(kEffect_Infect_CareerDamage_Id2, value);
				break;
			case 3:
				effect.SumEffect(kEffect_Infect_CareerDamage_Id3, value);
				break;
			case 4:
				effect.SumEffect(kEffect_Infect_CareerDamage_Id4, value);
				break;
			case 5:
				effect.SumEffect(kEffect_Infect_CareerDamage_Id5, value);
				break;
			case 6:
				effect.SumEffect(kEffect_Infect_CareerDamage_Id6, value);
				break;
			case 7:
				effect.SumEffect(kEffect_Infect_CareerDamage_Id7, value);
				break;
			
			default:
				break;
		}
	}
	
	// clamp
	value = fmaxf(value, 0) * (1 + newDamage);
	
	DEBUGLOG_WRITE("DamageBeforeResistance() name : %s, newDamage : %f, value : %f, damage : %f", 
				GetCharacterInfo().character_name, newDamage, value, damage);
	
	return fmaxf(damage * value, 0);
}

void Client::ParseShoot()
{
	Weapon &w = GetCurWeaponInfo();
	
	bool is_boost(false);
	if(w.weapon_data.weapon.type != kWeaponTypeDrum && GetGaiLv(rand_fun.Rand(),rand_fun.GetRandMax()) * 10000 < w.weapon_data.weapon.hit_crit)
		is_boost = true;
		
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	if ((w.weapon_data.weapon.type >= kWeaponTypeKnife && w.weapon_data.weapon.type != kWeaponTypeDrum)|| w.weapon_data.weapon.type == kWeaponTypeNone)
		return;

	if (w.weapon_data.gun.ammo_in_clip == 0 && w.weapon_data.gun.time_to_idle <= 0 && w.weapon_data.weapon.type != kWeaponTypeDrum)
		return;
		
	if( w.weapon_data.gun.time_to_idle > 0)
	{
		if(w.weapon_data.gun.time_to_idlecount > 0)
			return;
		
		w.weapon_data.gun.time_to_idlecount = w.weapon_data.gun.time_to_idle - 1;
	}

	byte part;
	Quaternion dir;
	byte do_effect;

	float rate = 1.0f;
	float distance = 0.f;
	byte personcount = 0;
	byte target_type = 0;
	
	std::vector<Client*> vct_pclient;
	//WeaponInfo weaponinfo;
	
	ReadByte(do_effect);
	ReadFloat(rate);
	ReadCharacterRotation(dir);
	ReadByte(personcount);
	
	if (w.weapon_data.weapon.type != kWeaponTypeSniperGun)
		rate = 1.0f;
	else if (rate > 4.0f)
		rate = 4.0f;

	if (do_effect && w.weapon_data.gun.time_to_idle <= 0)
	{
		w.weapon_data.gun.ammo_in_clip--;
		log_write(LOG_DEBUG5, "ParseShoot() w.weapon_data.gun.ammo_in_clip = %d", w.weapon_data.gun.ammo_in_clip);
	}
	
	Weapon w_tmp = w;
	
	for (byte i = 0; i < personcount; i++)
	{
		ReadByte(target_type);
		
		if(target_type == Character)
		{
			// hitted player.
			Client* hit = ReadClient();

			ReadByte(part);
			ReadFloat(distance);
			//useless data---------------
			uint dummyid;
			ReadInt(dummyid);
			//---------------------------
			if (hit)
			{
				if (CanHurt(hit, server.team_hurt))
				{
					// calculate damage
					if (part < kCharacterPartCount)
					{
						int damage = static_cast<int>(server.config.part_damages[part] * GetWeaponDamage(w_tmp, distance, hit, &is_boost) * rate);

						if(part == kCharacterPartHead && w_tmp.weapon_data.weapon.hit_crit_head != 0 )
						{
							damage *= w_tmp.weapon_data.weapon.hit_crit_head;
						}
						// take damage
						hit->TakeDamage(this, &damage, part, w_tmp, is_boost);
						vct_pclient.push_back(hit);
					}
				}
			}
		}
		else
		{
			Client* hit_owner = ReadClient();
			
			ReadByte(part);
			ReadFloat(distance);
			DummyBaseInfo* hit_dummy = ReadDummyBaseInfo();
			if (hit_dummy)
			{
				if(CanHurtDummy(hit_dummy->owner_id, server.team_hurt))
				{
					int damage = static_cast<int>(GetWeaponDamage(w_tmp, distance, hit_owner, &is_boost) * rate);

					DummyTakeDamage(damage, w_tmp, hit_dummy, is_boost);
				}

				
			}
		}
		
	}
	
	// broadcast player shoot
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerShoot);
			c->WriteByte(uid);
			c->WriteCharacterRotation(dir);
			c->WriteByte(do_effect);
			c->WriteByte(is_boost);
			c->WriteInt((uint)vct_pclient.size());
			if (vct_pclient.size() > 0)
			{
				for (int j = 0; j < vct_pclient.size(); j++)
				{
					Client* hit = vct_pclient[j];
					c->WriteByte(hit->uid);
					//TakeDamage part
					c->WriteByte(part);
					c->WriteInt(data.GetScore());
					c->WriteInt(hit->health);
					c->WriteInt(hit->armor);
					if (hit->health == 0 || (hit->health ==1 && hit->isghost))
					{
						WriteWeapon(*c, w_tmp);
						Client *client = server.GetClient(hit->assist_uid[0]);
						//sucide and have assist attacked
						if(hit->uid == this->uid && client && hit->assist_uid[0] != hit->uid)
						{
							c->WriteShort(client->num_kill);
						}
						else
						{
							c->WriteShort(num_kill);
						}

						c->WriteShort(hit->num_die);
						c->WriteByte(hit->assist_uid[0]);
						if (client)
						{
							c->WriteInt(client->data.GetScore());
							c->WriteShort(client->assist_num);
						}
						else
						{
							c->WriteInt(0);
							c->WriteShort(short(0));
						}

					}
				}
			}
			else
			{
				c->WriteByte(0);
			}

			c->EndWrite();
		}
	}

	OnAction();

	weapon_used = true;
}

void Client::ParseFlameShoot()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	Weapon & w = GetCurWeaponInfo();
	
	bool is_boost(false);
	if (GetGaiLv(rand_fun.Rand(),rand_fun.GetRandMax()) * 10000 < w.weapon_data.weapon.hit_crit)
		is_boost = true;

	if (w.weapon_data.weapon.type != kWeaponTypeFlameGun )
		return;

	if (w.weapon_data.gun.ammo_in_clip == 0  && w.weapon_data.gun.time_to_idle <= 0)
		return;

	if( w.weapon_data.gun.time_to_idle > 0)
	{
		if(w.weapon_data.gun.time_to_idlecount > 0)
			return;
		w.weapon_data.gun.time_to_idlecount = w.weapon_data.gun.time_to_idle - 1;
	}

	byte part;
	Quaternion dir;
	bool do_effect;
	float rate = 1.0f;
	byte target_type;

	byte personcount;
	std::vector<Client*> vct_pclient;
	ReadByte((byte&)do_effect);
	ReadFloat(rate);	
	ReadCharacterRotation(dir);
	
	ReadByte(personcount);
	if (do_effect && w.weapon_data.gun.time_to_idle <= 0)
	{
		w.weapon_data.gun.ammo_in_clip--;
		log_write(LOG_DEBUG5,"ParseFlameShoot() w.weapon_data.gun.ammo_in_clip = %d",w.weapon_data.gun.ammo_in_clip);
	}
	for (byte i = 0; i < personcount; i++)
	{
		ReadByte(target_type);
	
		if(target_type == Character)
		{
			Client* hit = ReadClient();

			if (hit)
			{
				float distance = 0.f;

				ReadByte(part);
				ReadFloat(distance);

				if (CanHurt(hit, server.team_hurt))
				{
					// calculate damage
					if (part < kCharacterPartCount)
					{
						int damage = static_cast<int>(server.config.part_damages[part] * GetWeaponDamage(w, distance, hit, &is_boost) * rate);

						if(part == kCharacterPartHead && w.weapon_data.weapon.hit_crit_head != 0)
						{
							damage *= w.weapon_data.weapon.hit_crit_head;
						}

						// take damage
						hit->TakeDamage(this, &damage, part, w, is_boost);
						vct_pclient.push_back(hit);
					}
				}
				else
				{
					hit = NULL;
				}
			}
		}
		else
		{
			float distance = 0.f;

			Client* hit_owner = ReadClient();
			ReadByte(part);
			ReadFloat(distance);
			DummyBaseInfo* hit_dummy = ReadDummyBaseInfo();
			if (hit_dummy)
			{
				if(CanHurtDummy(hit_dummy->owner_id, server.team_hurt))
				{
					int damage = static_cast<int>(GetWeaponDamage(w, distance, hit_owner, &is_boost) * rate);

					DummyTakeDamage(damage, w, hit_dummy, is_boost);
				}


			}
		}
	}

	// hitted player.

	// broadcast player shoot
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerFlameShoot);
			c->WriteByte(uid);
			c->WriteCharacterRotation(dir);
			c->WriteByte(do_effect);
			c->WriteByte(is_boost);
			c->WriteInt((uint)vct_pclient.size());
			if(vct_pclient.size() > 0)
			{
				for(int j = 0; j < vct_pclient.size(); j++)
				{
					Client* hit = vct_pclient[j];
					
					c->WriteByte(hit->uid);
					//TakeDamage part
					c->WriteByte(part);
					c->WriteInt(data.GetScore());
					c->WriteInt(hit->health);
					c->WriteInt(hit->armor);
					if (hit->health == 0 || (hit->health ==1 && hit->isghost))
					{
						WriteWeapon(*c, w);
						Client *client = server.GetClient(hit->assist_uid[0]);
						//sucide and have assist attacked
						if(hit->uid == this->uid && client && hit->assist_uid[0] != hit->uid)
						{
							c->WriteShort(client->num_kill);
						}
						else
						{
							c->WriteShort(num_kill);
						}
						c->WriteShort(hit->num_die);
						c->WriteByte(hit->assist_uid[0]);

						if(client)
						{
							c->WriteInt(client->data.GetScore());
							c->WriteShort(client->assist_num);
						}
						else
						{
							c->WriteInt(0);
							c->WriteShort(short(0));
						}

					}
					
				}
			}
			else
			{
				c->WriteByte(0);
			}

			c->EndWrite();
		}
	}

	OnAction();

	weapon_used = true;
}

void Client::ParseKickBack()
{
	if (team > 1)
		return;

	Vector3 punch;

	ReadVector3(punch);

	// broadcast player kick back
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c && c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerKickBack);
			c->WriteByte(uid);
			c->WriteVector3(punch);
			c->EndWrite();
		}
	}
}

void Client::ParseFlashBright()
{
	if (team > 1)
		return;

	float bright_time;
	float fade_time;

	ReadFloat(bright_time);
	ReadFloat(fade_time);

	// broadcast player kick back
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c&& c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerFlashBright);
			c->WriteByte(uid);
			c->WriteFloat(bright_time);
			c->WriteFloat(fade_time);
			c->EndWrite();
		}
	}
}

void Client::ParseCameraFovChanged()
{
	if (team > 1)
		return;

	float fov;
	float target_fov;

	ReadFloat(fov);
	ReadFloat(target_fov);

	// broadcast player kick back
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c && c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerCameraFovChanged);
			c->WriteByte(uid);
			c->WriteFloat(fov);
			c->WriteFloat(target_fov);
			c->EndWrite();
		}
	}
}

void Client::ParseGrenadeThrowIn()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	// broadcast player throw grenade
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c  && c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerGrenadeThrowIn);
			c->WriteByte(uid);
			c->EndWrite();
		}
	}

	OnAction();
}

void Client::ParseGrenadeThrowStop()
{
	if (team > 1)
		return;

	// broadcast player throw grenade
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c && c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerGrenadeThrowStop);
			c->WriteByte(uid);
			c->EndWrite();
		}
	}

	OnAction();
}

void Client::ParseGrenadeThrowOut()
{
	if (team > 1)
		return;

	Vector3 pos;
	Vector3 dir;
	byte id;

	ReadByte(id);
	ReadVector3(pos);
	ReadVector3(dir);

	Weapon w;
	Weapon *pW = GetWeaponInfo(id);

	if (pW)
	{
		w = *pW;
	}
	if( w.weapon_data.weapon.time_to_idle > 0)
	{
		if(w.weapon_data.weapon.time_to_idlecount > 0)
			return;
		w.weapon_data.gun.time_to_idlecount = w.weapon_data.weapon.time_to_idle - 1;
	}
	// broadcast player throw grenade
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerGrenadeThrowOut);
			c->WriteByte(uid);
			c->WriteByte(id);
			c->WriteVector3(pos);
			c->WriteVector3(dir);
			WriteWeapon(*c, w);
			c->EndWrite();
		}
	}

	OnAction();

	weapon_used = true;
}

void Client::ParseGrenadeHurt()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	Client* owner = ReadClient();

	if (owner && owner->CanHurt(this, server.team_hurt))
	{
		Vector3 grenade_pos;
		ReadVector3(grenade_pos);

		Weapon w;
		w.weapon_data.weapon.type = 0;
		ReadWeapon(*this, w);

		Vector3 dv;
		dv.x = grenade_pos.x - position.x;
		dv.y = grenade_pos.y - position.y;
		dv.z = grenade_pos.z - position.z;

		float dist = Length(dv);

		if ((w.weapon_data.weapon.type == kWeaponTypeGrenade || w.weapon_data.weapon.type == kWeaponTypeSpecial) 
			&& dist < w.weapon_data.grenade.range && dist >= 0.f)
		{
			bool isboost(false);
			if(( rand() % 10000) < w.weapon_data.weapon.hit_crit)
				isboost = true;
			int damage = (int)owner->GetWeaponDamage(w, dist, this, &isboost, 0, false);
			if (owner->uid == uid) damage = damage / 2;
			TakeDamage(owner, &damage, kCharacterPartTorso, w ,isboost);

			// broadcast grenade hurt
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;

				if (c->IsReady())
				{
					c->BeginWrite();
					c->WriteByte(SM_PlayerGrenadeHurt);
					c->WriteByte(owner->uid);
					c->WriteByte(uid);
					c->WriteVector3(grenade_pos);
					c->WriteByte(isboost);
					// TakeDamage
					c->WriteByte(0);
					c->WriteInt(owner->data.GetScore());
					c->WriteInt(health);
					c->WriteInt(armor);
					
					if (health == 0 || (health ==1 && isghost))
					{
						WriteWeapon(*c, w);
						Client *client = server.GetClient(assist_uid[0]);
						//sucide and have assist attacked
						if(uid == owner->uid && client && assist_uid[0] != uid)
						{
							c->WriteShort(client->num_kill);
						}
						else
						{
							c->WriteShort(owner->num_kill);
						}

						c->WriteShort(num_die);
						c->WriteByte(assist_uid[0]);

						if(client)
						{
							c->WriteInt(client->data.GetScore());
							c->WriteShort(client->assist_num);
						}
						else
						{
							c->WriteInt(0);
							c->WriteShort(short(0));
						}
					}
					c->EndWrite();
				}
			}
		}
	}
}


void Client::ParseDummyGrenadeHurt()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	DummyBaseInfo* hit_dummy = ReadDummyBaseInfo();

	if (hit_dummy)
	{
		Client* hit_owner = server.GetClient(hit_dummy->owner_id);

		if(CanHurtDummy(hit_dummy->owner_id, server.team_hurt))
		{
			Vector3 grenade_pos;
			Vector3 dummy_pos;
			ReadVector3(grenade_pos);
			ReadVector3(dummy_pos);

			Weapon w;
			w.weapon_data.weapon.type = 0;
			ReadWeapon(*this, w);

			Vector3 dv = grenade_pos - dummy_pos;

			float dist = Length(dv);

			if ((w.weapon_data.weapon.type == kWeaponTypeGrenade || w.weapon_data.weapon.type == kWeaponTypeSpecial) && dist < w.weapon_data.grenade.range && dist >= 0.f)
			{
				bool isboost(false);
				if(( rand() % 10000) < w.weapon_data.weapon.hit_crit)
					isboost = true;
		
				int damage = static_cast<int>(GetWeaponDamage(w, dist, this, &isboost, 0, false));
				
				DummyTakeDamage(damage, GetCurWeaponInfo(), hit_dummy, isboost);		
			}
		}		
	}
}

void Client::ParsePoke()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	byte type;
	ReadByte(type);

	// broadcast player poke
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c && c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerPoke);
			c->WriteByte(uid);
			c->WriteByte(type);
			c->EndWrite();
		}
	}

	OnAction();
}

void Client::ParsePokeHurt()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;
	
	bool isboost = false;
	if( GetGaiLv(rand_fun.Rand(),rand_fun.GetRandMax()) * 10000 < GetCurWeaponInfo().weapon_data.weapon.hit_crit)
		isboost = true;
	byte light;
	byte part = 0;
	byte back = 0;

	ReadByte(light);

	// get hurted client.
	Client* hurt = ReadClient();

	if (hurt)
	{
		ReadByte(part);
		ReadByte(back);

		if (CanHurt(hurt, server.team_hurt))
		{
			// calculate damage
			if (part < kCharacterPartCount)
			{
				int damage = static_cast<int>(server.config.part_damages[part] * GetWeaponDamage(GetCurWeaponInfo(), 0.f, hurt, &isboost, light, back));

				if(part == kCharacterPartHead && GetCurWeaponInfo().weapon_data.weapon.hit_crit_head != 0)
				{
					damage *= GetCurWeaponInfo().weapon_data.weapon.hit_crit_head;
				}

				// take damage
				hurt->TakeDamage(this, &damage, part, GetCurWeaponInfo(), isboost);
			}
		}
	}

	// broadcast player poke
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c && c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerPokeHurt);
			c->WriteByte(uid);
			c->WriteByte(light);
			c->WriteByte(isboost);
			
			if (hurt)
			{
				c->WriteByte(hurt->uid);
				c->WriteByte(part);
				c->WriteInt(data.GetScore());
				c->WriteInt(hurt->health);
				c->WriteInt(hurt->armor);
				
				if (hurt->health == 0 || (hurt->health ==1 && hurt->isghost))
				{
					WriteWeapon(*c, GetCurWeaponInfo());

					Client *client = server.GetClient(hurt->assist_uid[0]);
					//sucide and have assist attacked
					if(hurt->uid == this->uid && client && hurt->assist_uid[0] != this->uid)
					{
						c->WriteShort(client->num_kill);

					}
					else
					{
						c->WriteShort(this->num_kill);
					}

					c->WriteShort(hurt->num_die);
					c->WriteByte(hurt->assist_uid[0]);

					if(client)
					{
						c->WriteInt(client->data.GetScore());
						c->WriteShort(client->assist_num);
					}
					else
					{
						c->WriteInt(0);
						c->WriteShort(short(0));
					}
				}
			}
			else
			{
				c->WriteByte(0);
			}

			c->EndWrite();
		}
	}

	OnAction();
}

void Client::ParseDummyPokeHurt()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	bool isboost = false;
	if( GetGaiLv(rand_fun.Rand(),rand_fun.GetRandMax()) * 10000 < GetCurWeaponInfo().weapon_data.weapon.hit_crit)
		isboost = true;
	byte light;
	byte part = 0;
	byte back = 0;

	ReadByte(light);

	DummyBaseInfo* hit_dummy = ReadDummyBaseInfo();
	
	ReadByte(back);

	if(hit_dummy)
	{
		Client* hit_owner = server.GetClient(hit_dummy->owner_id);

		if(CanHurtDummy(hit_dummy->owner_id, server.team_hurt))
		{
			int damage = static_cast<int>(server.config.part_damages[part] * GetWeaponDamage(GetCurWeaponInfo(), 0.f, hit_owner, &isboost, light, back));

			DummyTakeDamage(damage, GetCurWeaponInfo(), hit_dummy, isboost);		
		}
	}
}

void Client::DummyTakeDamage(int damage, const Weapon& w, DummyBaseInfo* dummy, bool isboost)
{
	if(dummy->team == team)
		return;

	//damage = (int)fmaxf(DamageAfterResistance(damage, w.weapon_data.weapon.type, uid), 0);
	int a = this->GetCurCharinfo().player_id;
	if (server.game_type == kTDMode)
	{
		this->alldamage += damage;
	}
	if (server.game_type == kTDMode && 
		dummy->need_stepfather && 
		dummy->team != team && 
		dummy->sub_type == TD_RESBUILDING_SUBTYPE)
	{
		server.cur_reshp -= damage;
		if (server.cur_reshp < 0)
			server.cur_reshp = 0;
		
		log_write(LOG_DEBUG2, 
				"TDMode ResBuilding TakeDamage, id : %u, damage : %d, cur_reshp : %d, max_reshp : %d"
				, dummy->id, damage, server.cur_reshp, server.max_reshp);
				
		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = server.clients + i;
			if(c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_TDMode_ResHpChange);
				c->WriteInt(server.cur_reshp);
				c->WriteInt(server.max_reshp);
				c->EndWrite();
			}
		}
	}

	if(dummy->need_stepfather && dummy->team == team)
		return;

	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;
		if(c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_GunTowerHurt);
			c->WriteInt(dummy->id);
			c->WriteByte(dummy->owner_id);
			c->WriteInt(damage);
			c->WriteByte(isboost ? 1 : 0);
			c->EndWrite();
		}
	}
}

// parse self hurt
void Client::ParseHurt()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	float speed;

	// get hurted client.
	ReadFloat(speed);

	if (CanHurt(this, true))
	{
		if (speed > 12.5f)
		{
			// calculate damage
			int damage = static_cast<int>(powf(speed - 12.5f, 1.5f));

			Weapon w;
			w.weapon_data.weapon.type = kWeaponTypeSelfHurt;

			// take damage
			if (TakeDamage(this, &damage, kCharacterPartTorso, w) && damage > 0)
			{
				// broadcast player poke
				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = server.clients + i;

					if (c->IsReady())
					{
						c->BeginWrite();
						c->WriteByte(SM_PlayerHurt);
						c->WriteByte(uid);
						c->WriteByte(0);
						c->WriteInt(data.GetScore());
						c->WriteInt(health);
						c->WriteInt(armor);

						if (health == 0 || (health ==1 && isghost))
						{
							c->WriteInt(0);

							Client *client = server.GetClient(assist_uid[0]);
							//sucide and have assist attacked
							if(client && assist_uid[0] != uid)
							{
								c->WriteShort(client->num_kill);
							}
							else
							{
								c->WriteShort(this->num_kill);
							}

							c->WriteShort(num_die);
							c->WriteByte(assist_uid[0]);

							if(client)
							{
								c->WriteInt(client->data.GetScore());
								c->WriteShort(client->assist_num);
							}
							else
							{
								c->WriteInt(0);
								c->WriteShort(short(0));
							}
						}

						c->EndWrite();
					}
				}
			}
		}
	}

}

// parse reload
void Client::ParseReload()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	short count;
	ReadShort(count);

	Weapon & w = GetCurWeaponInfo();

	if (w.weapon_data.weapon.type >= kWeaponTypeKnife || w.weapon_data.weapon.type == kWeaponTypeNone)
		return;

	// broadcast player poke
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerReload);
			c->WriteByte(uid);
			c->WriteShort(count);
			c->EndWrite();
		}
	}

	OnAction();

	weapon_used = true;
}

void Client::ParseReloadReady()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;
	Weapon & w = GetCurWeaponInfo();

	if (w.weapon_data.weapon.type >= kWeaponTypeKnife || w.weapon_data.weapon.type == kWeaponTypeNone)
		return;

	if (w.weapon_data.gun.ammo_in_clip >= w.weapon_data.gun.ammo_one_clip)
	{
		//log_write(LOG_DEBUG5,"ParseReloadReady, w.weapon_data.gun.ammo_in_clip = %d, w.weapon_data.gun.ammo_one_clip = %d",w.weapon_data.gun.ammo_in_clip,w.weapon_data.gun.ammo_one_clip);
		return;
	}

	if (w.weapon_data.gun.ammo_count_current <= 0)
	{
		//log_write(LOG_DEBUG5,"ParseReloadReady, w.weapon_data.gun.ammo_count_current = %d",w.weapon_data.gun.ammo_count_current);
		return;
	}

	int count;
	ReadInt(count);
	//log_write(LOG_DEBUG5,"ParseReloadReady, count = %d",count);

	if (count > (w.weapon_data.gun.ammo_one_clip - w.weapon_data.gun.ammo_in_clip))
		return;

	if (count > w.weapon_data.gun.ammo_count_current)
		return;

	w.weapon_data.gun.ammo_in_clip += count;
	w.weapon_data.gun.ammo_count_current -= count;

	// broadcast player poke
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerReloadReady);
			c->WriteByte(uid);
			c->WriteInt(count);
			c->EndWrite();
		}
	}

	weapon_used = true;
}

// ammo recover
bool Client::AmmoRecover(int type, int count)
{
	if (!IsAlive())
		return false;

	const int SUPPLY_VEHICLE_COUNT = 10;
	
	float value = 1;
	if (type == kSupplyAmmo)
	{
		if (effect.SumEffect(kEffect_Infect_SupplyAmmo, value))
		{
			count *= fmaxf(value, 0);
			
			DEBUGLOG_WRITE("AmmoRecover(kSupplyAmmo) name : %s, value : %f", 
							GetCharacterInfo().character_name, value);
		}
	}

	bool ispass = false;
	bool ispass_no_statistics = false;
	PackInfo &pack = GetCurPackInfo();
	for (std::map<byte, Weapon>::iterator itr = pack.weapon_set.begin(); itr != pack.weapon_set.end(); itr++)
	{
		Weapon & w = itr->second;
		if (w.weapon_data.weapon.type >= kWeaponTypeKnife || w.weapon_data.weapon.type == kWeaponTypeNone || w.weapon_data.weapon.type == kWeaponTypeEquipment)
			continue;
		if(w.weapon_data.weapon.time_to_idle > 0)
			continue;
		int ammo = 0;
		switch(type)
		{
		case kSupplyAmmo:
		case kSupplySurvivalItemAmmo:
			{
				int max_ammo_count = w.weapon_data.gun.ammo_count;
				int max_ammo_one_clip = w.weapon_data.gun.ammo_one_clip;
				if (max_ammo_count > 0)
				{
					int ammo_count = w.weapon_data.gun.ammo_count_current;
					if (max_ammo_count * count / 100 == 0)
					{
						ammo_count += 1;
					}
					else
					{
						ammo_count += max_ammo_count * count / 100;
					}
					ammo_count = ammo_count > max_ammo_count ? max_ammo_count : ammo_count;
					w.weapon_data.gun.ammo_count_current = ammo_count;
					ammo = ammo_count;
					ispass = true;
				}
				else if (max_ammo_one_clip > 0)
				{
					int ammo_in_clip = w.weapon_data.gun.ammo_in_clip;
					if (max_ammo_one_clip * count / 100 == 0)
					{
						ammo_in_clip += 1;
					}
					else
					{
						ammo_in_clip += max_ammo_one_clip * count / 100;
					}
					ammo_in_clip = ammo_in_clip > max_ammo_one_clip ? max_ammo_one_clip : ammo_in_clip;
					w.weapon_data.gun.ammo_in_clip = ammo_in_clip;
					ammo = ammo_in_clip;
					ispass = true;
				}
				else if (max_ammo_count == 0 && max_ammo_one_clip == 0)
				{
					int ammo_count = 0;
					ammo_count += w.weapon_data.gun.ammo_default_count * count / 100;
					ammo_count = ammo_count > w.weapon_data.gun.ammo_default_count ? w.weapon_data.gun.ammo_default_count : ammo_count;
					w.weapon_data.gun.ammo_count_current = ammo_count;
					w.weapon_data.gun.ammo_count = ammo_count;
					ammo = ammo_count;

					/*int ammo_in_clip = 0;
					ammo_in_clip += w.weapon_data.gun.ammo_in_clip * count / 100;
					ammo_in_clip = ammo_in_clip > max_ammo_one_clip ? max_ammo_one_clip : ammo_in_clip;
					w.weapon_data.gun.ammo_in_clip = ammo_in_clip;
					ammo = ammo_in_clip;*/

					ispass = true;
				}
			}
			break;
		case kSupplyDropWeapon:
			{
				int max_ammo_count = w.weapon_data.gun.ammo_count;
				int max_ammo_one_clip = w.weapon_data.gun.ammo_one_clip;
				if (max_ammo_count > 0)
				{
					int ammo_count = w.weapon_data.gun.ammo_count_current;
					if (max_ammo_count * count / 100 == 0)
					{
						ammo_count += 1;
					}
					else
					{
						ammo_count += max_ammo_count * count / 100;
					}
					ammo_count = ammo_count > max_ammo_count ? max_ammo_count : ammo_count;
					w.weapon_data.gun.ammo_count_current = ammo_count;
					ammo = ammo_count;
				}
				else if (max_ammo_one_clip > 0)
				{
					int ammo_in_clip = w.weapon_data.gun.ammo_in_clip;
					if (max_ammo_one_clip * count / 100 == 0)
					{
						ammo_in_clip += 1;
					}
					else
					{
						ammo_in_clip += max_ammo_one_clip * count / 100;
					}
					ammo_in_clip = ammo_in_clip > max_ammo_one_clip ? max_ammo_one_clip : ammo_in_clip;
					w.weapon_data.gun.ammo_in_clip = ammo_in_clip;
					ammo = ammo_in_clip;
				}
				else if (max_ammo_count == 0 && max_ammo_one_clip == 0)
				{

				}
			}
			break;
		case kSupplyMedkit:
			{
				if (w.weapon_data.weapon.type != kWeaponTypeGunTowerBuilder)
				{
					int max_ammo_count = w.weapon_data.gun.ammo_count;
					int max_ammo_one_clip = w.weapon_data.gun.ammo_one_clip;
					if (max_ammo_count > 0)
					{
						int ammo_count =  w.weapon_data.gun.ammo_count_current;
						if(ammo_count >= max_ammo_count)
							continue;
						else
						{
							w.weapon_data.gun.ammo_count_current = max_ammo_count;
							ammo = w.weapon_data.gun.ammo_count_current;
						}
						ispass_no_statistics = true;
					}
					else if (max_ammo_one_clip > 0)
					{
						int ammo_in_clip = w.weapon_data.gun.ammo_in_clip;
						if(ammo_in_clip >= max_ammo_one_clip)
							continue;
						else
						{
							w.weapon_data.gun.ammo_in_clip = max_ammo_one_clip;
							ammo = w.weapon_data.gun.ammo_in_clip;
						}
						ispass_no_statistics = true;
					}
					else if (max_ammo_count == 0 && max_ammo_one_clip == 0)
					{
					}
				}
			}
			break;
		case kSupplyVehicle:
			{
				if (w.weapon_data.weapon.type != kWeaponTypeGunTowerBuilder)
				{
					int max_ammo_count = w.weapon_data.gun.ammo_count;
					int max_ammo_one_clip = w.weapon_data.gun.ammo_one_clip;
					if (max_ammo_count > 0)
					{
						int ammo_count = w.weapon_data.gun.ammo_count_current;
						if (max_ammo_count * SUPPLY_VEHICLE_COUNT / 100 == 0)
						{
							ammo_count += 1;
						}
						else
						{
							ammo_count += max_ammo_count * SUPPLY_VEHICLE_COUNT / 100;
						}
						ammo_count = ammo_count > max_ammo_count ? max_ammo_count : ammo_count;
						w.weapon_data.gun.ammo_count_current = ammo_count;
						ammo = ammo_count;
					}
					else if (max_ammo_one_clip > 0)
					{
						int ammo_in_clip = w.weapon_data.gun.ammo_in_clip;
						if (max_ammo_one_clip * SUPPLY_VEHICLE_COUNT / 100 == 0)
						{
							ammo_in_clip += 1;
						}
						else
						{
							ammo_in_clip += max_ammo_one_clip * SUPPLY_VEHICLE_COUNT / 100;
						}
						ammo_in_clip = ammo_in_clip > max_ammo_one_clip ? max_ammo_one_clip : ammo_in_clip;
						w.weapon_data.gun.ammo_in_clip = ammo_in_clip;
						ammo = ammo_in_clip;
					}
					else if (max_ammo_count == 0 && max_ammo_one_clip == 0)
					{
					}
				}
			}
			break;
		case kSupplyNovice:
			{
				int max_ammo_count = w.weapon_data.gun.ammo_count;
				int max_ammo_one_clip = w.weapon_data.gun.ammo_one_clip;
				if (max_ammo_count > 0)
				{
					int ammo_count = w.weapon_data.gun.ammo_count_current;
					ammo_count = max_ammo_count * 1 / 5;
					w.weapon_data.gun.ammo_count_current = ammo_count;
					ammo = ammo_count;
					ispass_no_statistics = true;
				}
				else if (max_ammo_one_clip > 0)
				{
					int ammo_in_clip = w.weapon_data.gun.ammo_in_clip;
					ammo_in_clip = max_ammo_one_clip * 1 / 5;
					w.weapon_data.gun.ammo_in_clip = ammo_in_clip;
					ammo = ammo_in_clip;
					ispass_no_statistics = true;
				}
				else if (max_ammo_count == 0 && max_ammo_one_clip == 0)
				{
				}
			}
			break;
		default:
			break;
		}
		for (uint j = 0; j < max_client_count; j++)
		{
			Client * c = server.clients + j;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_AmmoRecover);
				c->WriteByte(uid);
				c->WriteInt(itr->first);				//weapon_id
				c->WriteInt(ammo);
				if (type != kSupplyVehicle)
				{
					c->WriteInt(count);
				}
				else
				{
					c->WriteInt(SUPPLY_VEHICLE_COUNT);
				}
				c->WriteByte(0);
				c->EndWrite();
			}
		}
	}
	if (ispass)
	{
		num_ammorecover++;
	}

	std::map<uint, DummyBaseInfo>::iterator itor = server.dummy_object_map.begin();
	for(;itor != server.dummy_object_map.end(); itor ++)
	{
		const DummyBaseInfo& info = itor->second;
		if(uid == info.owner_id && info.type == DUMMY_MACHINE_TURRENT && info.need_stepfather == 0)
		{
			for (uint j = 0; j < max_client_count; j++)
			{
				Client * c = server.clients + j;

				if (c->IsReady())
				{
					c->BeginWrite();
					c->WriteByte(SM_AmmoRecover);
					c->WriteByte(uid);
					c->WriteInt(info.id);				//weapon_id
					c->WriteInt(0);
					if (type != kSupplyVehicle)
					{
						c->WriteInt(count);
					}
					else
					{
						c->WriteInt(SUPPLY_VEHICLE_COUNT);
					}
					c->WriteByte(1);
					c->EndWrite();
				}
			}
		}
	}


	if (ispass_no_statistics)
		return true;
	return ispass;
}

void Client::AmmoDisappear()
{
	PackInfo &pack = GetCurPackInfo();

	for (std::map<byte, Weapon>::iterator itr = pack.weapon_set.begin(); itr != pack.weapon_set.end(); itr++)
	{
		Weapon & w = itr->second;
		
		w.weapon_data.gun.ammo_count_current = 0;
		w.weapon_data.gun.ammo_in_clip = 0;
	}

	if (IsReady())
	{
		BeginWrite();
		WriteByte(SM_AmmoDisappear);
		WriteByte(uid);
		EndWrite();
	}
}

void Client::HPItemDisappear()
{

	for (std::list<SurvivalModeItemInfo>::iterator itr = m_character_info.survivalbag.begin(); 
		itr != m_character_info.survivalbag.end();)
	{
		if (itr->type == kSupplySurvivalItemHp)
		{
			m_character_info.survivalbag.erase(itr);
			itr = m_character_info.survivalbag.begin(); 
		}
		else
			itr++;
	}

	if (IsReady())
	{
		BeginWrite();
		WriteByte(SM_Trap_HP_Disappear);
		WriteByte(uid);
		EndWrite();
	}
}

// armor recover
void Client::ArmorRecover(int type, int count)
{
	// armor
	armor += count;
	if (armor > max_armor)
		armor = max_armor;
	
	// broadcast player set team
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_ArmorRecover);
			c->WriteByte(uid);
			c->WriteInt(armor);
			c->WriteByte(type);
			c->EndWrite();
		}
	}
}

// kick client start
void Client::KickClientStart(byte start_uid, byte kicked_uid, byte kicked_reason)
{
	BeginWrite();
	WriteByte(SM_KickClientStart);
	WriteByte(start_uid);
	WriteByte(kicked_uid);
	WriteByte(kicked_reason);
	EndWrite();
}

// kick client end
void Client::KickClientEnd(byte start_uid, byte kicked_uid, byte result)
{
	BeginWrite();
	WriteByte(SM_KickClientEnd);
	WriteByte(start_uid);
	WriteByte(kicked_uid);
	WriteByte((byte&)result);
	EndWrite();
}

// suicide
void Client::Suicide()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	// if (server.round_end_time > 0)
		// return;

	if (!server.playing)
		return;

	Weapon w;
	w.base_info.sid = 1;
	w.weapon_data.weapon.type = kWeaponTypeSelfHurt;

	// take damage
	int damage = health;
	if (TakeDamage(this, &damage, kCharacterPartTorso, w) && damage > 0)
	{
		// broadcast player poke
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_PlayerHurt);
				c->WriteByte(uid);
				c->WriteByte(0);
				c->WriteInt(data.GetScore());
				c->WriteInt(health);
				c->WriteInt(armor);

				if (health == 0 || (health ==1 && isghost))
				{
					c->WriteInt(0);

					Client *client = server.GetClient(assist_uid[0]);
					//sucide and have assist attacked
					if(client && assist_uid[0] != uid)
					{
						c->WriteShort(client->num_kill);
					}
					else
					{
						c->WriteShort(this->num_kill);
					}

					c->WriteShort(num_die);
					c->WriteByte(assist_uid[0]);

					if(client)
					{
						c->WriteInt(client->data.GetScore());
						c->WriteShort(client->assist_num);
					}
					else
					{
						c->WriteInt(0);
						c->WriteShort(short(0));
					}
				}

				c->EndWrite();
			}
		}
	}
}

void Client::SkillHurt(Client* from, int damage)
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	// if (server.round_end_time > 0)
	// return;

	if (!server.playing)
		return;

	Weapon w;
	w.base_info.sid = 1;
	w.weapon_data.weapon.type = kWeaponTypeNone;

	if (TakeDamage(from, &damage, kCharacterPartTorso, w) && damage > 0)
	{
		// broadcast player poke
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_SkillHurt);
				c->WriteByte(uid);
				c->WriteByte(from ? from->uid : 0);
				
				c->WriteByte(0);
				c->WriteInt(data.GetScore());
				c->WriteInt(health);
				c->WriteInt(armor);

				if (health == 0 || (health ==1 && isghost))
				{
					c->WriteInt(0);

					Client *client = server.GetClient(assist_uid[0]);
					//sucide and have assist attacked
					if(client && assist_uid[0] != uid)
					{
						c->WriteShort(client->num_kill);
					}
					else
					{
						c->WriteShort(from->num_kill);
					}

					c->WriteShort(num_die);
					c->WriteByte(assist_uid[0]);

					if(client)
					{
						c->WriteInt(client->data.GetScore());
						c->WriteShort(client->assist_num);
					}
					else
					{
						c->WriteInt(0);
						c->WriteShort(short(0));
					}
				}

				c->EndWrite();
			}
		}
	}
}

// parse pick up supply object
void Client::ParsePickUpSupplyObject()
{
	if (!IsAlive())
		return;

	uint supply_id;

	ReadInt(supply_id);

	SurvivalModeItemInfo iteminfo;

	SupplyObject* supply = server.supply_list.Get(supply_id);

	if (supply && supply->IsActive())
	{
		byte common_zombie_item_type = 0;		//普通生化模式专用
		float common_zombie_item_time = 0.0f;	//普通生化模式专用

		switch (supply->supply.type)
		{
		case kSupplyDropItem:
			{
				std::map<int, int>::iterator itr = pickup_dropitems.find(supply->supply.value);
				if (itr == pickup_dropitems.end())
					pickup_dropitems.insert(std::make_pair(supply->supply.value, 1));
				else
					itr->second++;
			}
			break;
		case kSupplyGold:
		case kSupplyGoldWithForce:
		case kSupplyZombieGold:
			{
				if (server.cServerType == (byte)SvrType_Match)
				{
				}
				else
				{
					data.ScoreDataAdd(supply->supply.value);
				}
			}
			break;
		case kSupplyMoonModeWin:
			{
				server.picked_count_add();
				if(server.get_picked_count() - 1 >= 0 && server.get_picked_count() - 1 < 8)
				{
					if (server.cServerType == (byte)SvrType_Match)
					{
					}
					else
					{
						data.ScoreDataAdd((float)MOONSCORE[server.get_picked_count()-1]);
					}
				}
				if (server.get_picked_count() < 5 && server.game_type == KMoonMode)
				{
					ParseMoonForceSpawn();
				}
				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = server.clients + i;

					if (c->IsReady())
					{
						c->BeginWrite();
						c->WriteByte(SM_MoonMode_PickWin);
						c->WriteString(GetCharacterInfo().character_name);
						c->EndWrite();
					}
				}
			}
			break;
		case kSupplyHp:
			{
				bool isneed_adddate = false;
				isneed_adddate = Recover((int)(max_health * supply->supply.value / 100), kRecoverSupplyHp);
				if (!isneed_adddate)
					return;
			}
			break;
		case kSupplyAllTools:
			{
				bool b1 = false;
				b1 = Recover(max_health, kRecoverBaseHp);
				if (!b1)
					return;
			}
			break;
		case kSupplyAmmo:
		case kSupplyMedkit:
			{
				bool isneed_adddate = false;
				isneed_adddate = AmmoRecover(supply->supply.type, (short)(supply->supply.value));
				if (!isneed_adddate)
					return;
			}
			break;
		case kSupplyCommonZombie:
			if (server.game_type == kCommonZombieMode)
			{
				if (team == 1)
				{
					byte type[20] = {1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 8, 8};
					int pos = rand() % 20;
					switch(type[pos])
					{
					case 1: 
						{
							common_zombie_item_type = 9;
							common_zombie_item_time = 0.0f;
							bool isneed_adddate = false;
							isneed_adddate = Recover(1000, kRecoverSupplyHp);
							if (!isneed_adddate)
								return;
						}
						break;
					case 2:
						{
							common_zombie_item_type = 10;
							common_zombie_item_time = 0.0f;
							bool isneed_adddate = false;
							isneed_adddate = Recover(3000, kRecoverSupplyHp);
							if (!isneed_adddate)
								return;
						}
						break;
					case 3:
						{
							common_zombie_item_type = 1;
							common_zombie_item_time = 10.0f;
							EffectData effectdata;

							effectdata.duration_timer = 10.0f;
							effectdata.type = kEffect_Infect_ResistanceAll;
							float Resistance = 0.1f;
							effectdata.value = (1.0f / (1.0f - Resistance) - 1);
							effectdata.enable = true;

							effect.ApplySystemItemEffect(effectdata, 1);
						}
						break;
					case 4:
						{
							common_zombie_item_type = 2;
							common_zombie_item_time = 10.0f;
							EffectData effectdata;

							effectdata.duration_timer = 10.0f;
							effectdata.type = kEffect_Infect_ResistanceAll;
							float Resistance = 0.1f;
							effectdata.value = (1.0f / (1.0f - Resistance) - 1);
							effectdata.enable = true;

							effect.ApplySystemItemEffect(effectdata, 1);
						}
						break;
					case 5:
						{
							common_zombie_item_type = 11;
							common_zombie_item_time = 0.0f;
							SetCommonZombieEnergy(100);
						}
						break;
					case 6:
						{
							common_zombie_item_type = 12;
							common_zombie_item_time = 0.0f;
							SetCommonZombieEnergy(300);
						}
						break;
					case 7:
						{
							common_zombie_item_type = 3;
							common_zombie_item_time = 10.0f;
							EffectData effectdata;

							effectdata.duration_timer = 10.0f;
							effectdata.type = kEffect_Infect_MoveSpeed;
							effectdata.value = 0.25f;
							effectdata.enable = true;

							effect.ApplySystemItemEffect(effectdata, 1);
						}
						break;
					case 8:
						{
							common_zombie_item_type = 4;
							common_zombie_item_time = 10.0f;
							EffectData effectdata;

							effectdata.duration_timer = 10.0f;
							effectdata.type = kEffect_Infect_JumpHeight;
							effectdata.value = 0.25f;
							effectdata.enable = true;

							effect.ApplySystemItemEffect(effectdata, 1);
						}
						break;
					}
				}
				else if (team == 0)
				{
					byte type[20] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 6, 6};
					int pos = rand() % 20;
					switch(type[pos])
					{
					case 1: 
						{
							common_zombie_item_type = 13;
							common_zombie_item_time = 0.0f;
							bool isneed_adddate = false;
							isneed_adddate = AmmoRecover(kSupplyAmmo, 30);
							if (!isneed_adddate)
								return;
						}
						break;
					case 2:
						{
							common_zombie_item_type = 5;
							common_zombie_item_time = 30.0f;
							EffectData effectdata;

							effectdata.duration_timer = 30.0f;
							effectdata.type = kEffect_Infect_Damage;
							effectdata.value = 0.1f;
							effectdata.enable = true;

							effect.ApplySystemItemEffect(effectdata, 1);
						}
						break;
					case 3:
						{
							common_zombie_item_type = 6;
							common_zombie_item_time = 30.0f;
							EffectData effectdata;

							effectdata.duration_timer = 30.0f;
							effectdata.type = kEffect_Infect_Damage;
							effectdata.value = 0.15f;
							effectdata.enable = true;

							effect.ApplySystemItemEffect(effectdata, 1);
						}
						break;
					case 4:
						{
							common_zombie_item_type = 7;
							common_zombie_item_time = 30.0f;
							EffectData effectdata;

							effectdata.duration_timer = 30.0f;
							effectdata.type = kEffect_Infect_Damage;
							effectdata.value = 0.2f;
							effectdata.enable = true;

							effect.ApplySystemItemEffect(effectdata, 1);
						}
						break;
					case 5:
						{
							common_zombie_item_type = 14;
							common_zombie_item_time = 0.0f;
						}
						break;
					case 6:
						{
							common_zombie_item_type = 8;
							common_zombie_item_time = 5.0f;
							can_Invincible = true;
						}
						break;
					}
				}
			}
			else if (server.game_type == kBossMode2)
			{
				if (team == 0 && 
					GetCurCharinfo().career_id != server.level_info.bosspve_info[0].career_id && 
					GetCurCharinfo().career_id != server.level_info.bosspve_info[1].career_id && 
					GetCurCharinfo().career_id != server.level_info.bosspve_info[2].career_id && 
					GetCurCharinfo().career_id != server.level_info.bosspve_info[3].career_id)
				{
					boss2_strange_spawn = rand() % 4;
					
					Boss2SyncData();
				}
			}
			else if (server.game_type == kItemMode)
			{
				if (/* itemmode_item_slot == -1 && */
					GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
				{
					int item[] = {0,1,2,3,4,5,10,25};
					itemmode_item_slot = item[rand() % elementsof(item)];		
				}
				
				ItemModeSyncData();
			}
			break;
		case kSupplyCommonZombie2:
			if (server.game_type == kItemMode)
			{
				if (GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
					itemmode_item_slot = 14;
				
				ItemModeSyncData();
			}
			break;
		case  kSupplyCCoin:
			{
				CCoinCount++;
			}
			break;
		case  kSupplyMedal:
			{
				MedalCount++;
			}
			break;
		case  kSupplyWrench:
			{
				WrenchCount++;
			}
			break;
		case  kSupplyBoxA:
			{
				BoxACount++;
			}
			break;
		case  kSupplyBoxB:
			{
				BoxBCount++;
			}
			break;
		case  kSupplyBoxC:
			{
				BoxCCount++;
			}
			break;
		case  kSupplyBoxD:
			{
				BoxDCount++;
			}
			break;
		case kSupplySurvivalItemHp:
		case kSupplySurvivalItemAmmo:
		case kSupplySurvivalItemTrapAmmo:
		case kSupplySurvivalItemTrapHP:
		case kSupplySurvivalItemTrapExpose:
		case kSupplySurvivalItemTrapBomb:
		case kSupplySurvivalItemTrapDebuff:
		case kSupplySurvivalIteminitiative:
			{
				iteminfo.sid = supply_id;
				iteminfo.name = supply->supply.name;
				iteminfo.icon = supply->supply.name;
				iteminfo.type = supply->supply.type;
				iteminfo.value = supply->supply.value;
				
				m_character_info.survivalbag.push_back(iteminfo);

				if (m_character_info.survivalbag.size() > SURVIVALBAGSIZE)
				{
					m_character_info.survivalbag.pop_front();
				}
			}
			break;
		case kSupplySurvivalItemRandom:
			{
				int _rand = rand() % 100 + 1;

				if (_rand <= 10)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalItemHp;
					iteminfo.name = "ig_itemmode_skill_ico_blod_50";
					iteminfo.icon = "ig_itemmode_skill_ico_blod_50";
					iteminfo.value = 50;
				}
				else if (_rand <= 18)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalItemHp;
					iteminfo.name = "ig_itemmode_skill_ico_blod_100";
					iteminfo.icon = "ig_itemmode_skill_ico_blod_100";
					iteminfo.value = 80;
				}
				else if (_rand <= 28)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalItemAmmo;
					iteminfo.name = "ig_itemmode_skill_ico_blet_50";
					iteminfo.icon = "ig_itemmode_skill_ico_blet_50";
					iteminfo.value = 50;
				}
				else if (_rand <= 36)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalItemAmmo;
					iteminfo.name = "ig_itemmode_skill_ico_blet_100";
					iteminfo.icon = "ig_itemmode_skill_ico_blet_100";
					iteminfo.value = 100;
				}
				else if (_rand <= 48)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalItemTrapAmmo;
					iteminfo.name = "ig_itemmode_skill_ico_29";
					iteminfo.icon = "ig_itemmode_skill_ico_29";
					iteminfo.value = 100;
				}
				else if (_rand <= 60)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalItemTrapHP;
					iteminfo.name = "ig_itemmode_skill_ico_30";
					iteminfo.icon = "ig_itemmode_skill_ico_30";
					iteminfo.value = 100;
				}
				else if (_rand <= 72)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalItemTrapExpose;
					iteminfo.name = "ig_itemmode_skill_ico_32";
					iteminfo.icon = "ig_itemmode_skill_ico_32";
					iteminfo.value = 100;
				}
				else if (_rand <= 84)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalItemTrapBomb;
					iteminfo.name = "ig_itemmode_skill_ico_33";
					iteminfo.icon = "ig_itemmode_skill_ico_32";
					iteminfo.value = 100;
				}
				else if (_rand <= 96)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalItemTrapDebuff;
					iteminfo.name = "ig_itemmode_skill_ico_34";
					iteminfo.icon = "ig_itemmode_skill_ico_34";
					iteminfo.value = 100;
				}
				else if (_rand <= 100)
				{
					iteminfo.sid = supply_id;
					iteminfo.type = kSupplySurvivalIteminitiative;
					iteminfo.name = "ig_itemmode_skill_ico_28";
					iteminfo.icon = "ig_itemmode_skill_ico_28";
					iteminfo.value = 5;
				}

				m_character_info.survivalbag.push_back(iteminfo);

				if (m_character_info.survivalbag.size() > SURVIVALBAGSIZE)
				{
					m_character_info.survivalbag.pop_front();
				}
			}
			break;
		case kSupplySurvivalItemGhostFire:
			{
				ghostfirecount += 5;
			}
			break;
		default:
			log_write(LOG_DEBUG2, "unknow supply_type : %d, id = %d", supply->supply.type, supply_id);
			break;
		}

		// broadcast player pick up supply object
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;                                                          

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_PickUpSupplyObject);
				c->WriteInt(uid);
				c->WriteInt(supply_id);
				if (supply->supply.type == kSupplySurvivalItemRandom && server.game_type == kSurvivalMode)
				{
					c->WriteByte(iteminfo.type);
				}
				else
				{
					c->WriteByte(supply->supply.type);
				}
				c->WriteInt(data.GetScore());
				if (server.game_type == kCommonZombieMode)
				{
					c->WriteByte(common_zombie_item_type);
					c->WriteFloat(common_zombie_item_time);
				}
				else if (server.game_type == kSurvivalMode)
				{
					if (supply->supply.type == kSupplySurvivalItemRandom)
					{
						c->WriteString(iteminfo.name.c_str());
						c->WriteString(iteminfo.name.c_str());
						c->WriteFloat(iteminfo.value);
					}
					else
					{
						c->WriteString(supply->supply.name.c_str());
						c->WriteString(supply->supply.name.c_str());
						c->WriteFloat(supply->supply.value);
					}
					
				}
				c->EndWrite();
			}
		}

		if (supply->supply.type == kSupplyHp || supply->supply.type == kSupplyAmmo || 
			kSupplyGoldWithForce == supply->supply.type || kSupplyZombieGold == supply->supply.type || 
			kSupplyDropItem == supply->supply.type || kSupplyCommonZombie == supply->supply.type || 
			kSupplyCommonZombie2 == supply->supply.type || kSupplyMoonModeWin == supply->supply.type||
			kSupplyCCoin == supply->supply.type||kSupplyMedal == supply->supply.type||kSupplyWrench == supply->supply.type||
			kSupplyRandomBoxA == supply->supply.type || kSupplyRandomBoxB == supply->supply.type || kSupplyRandomBoxC == supply->supply.type ||
			kSupplyBoxA == supply->supply.type || kSupplyBoxB == supply->supply.type || kSupplyBoxC == supply->supply.type || kSupplyBoxD == supply->supply.type ||
			(supply->supply.type > kSupplySurvivalItemStart && supply->supply.type < kSupplySurvivalItemEnd) )
		{
			supply->Destroy();
		}
	}
}

void Client::ParseOpenMessageClient()
{
	byte flag;
	ReadByte(flag);
	
	server.write_log_to_client = ((flag == 0) ? false : true);

	if(server.write_log_to_client)
	{
		server.WriteLogClient("start client message !!!----------------------");
	}
	
}
void Client::ParsePickUpSupplyObjectNew()
{
	if (!IsAlive())
		return;

	if (team > 0)
		return;

	uint supply_id;

	ReadInt(supply_id);

	DroppedSupply* supply = server.dropped_supply.Get(supply_id);

	if (supply && supply->IsActive())
	{
		switch (supply->type)
		{
		case kSupplyHp:
			{
				bool isneed_adddate = false;
				isneed_adddate = Recover((int)(max_health * supply->value / 100), kRecoverSupplyHp);
				if (!isneed_adddate)
					return;
			}
			break;
		case kSupplyAmmo:
		case kSupplyMedkit:
			{
				bool isneed_adddate = false;
				isneed_adddate = AmmoRecover(supply->type, (short)(supply->value));
				if (!isneed_adddate)
					return;
			}
			break;
		default:
			log_write(LOG_DEBUG2, "unknow supply_type : %d, id = %d", supply->type, supply_id);
			break;
		}

		// broadcast player pick up supply object
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_PickUpSupplyObjectNew);
				c->WriteInt(uid);
				c->WriteInt(supply_id);
				c->WriteByte(supply->type);
				c->EndWrite();
			}
		}

		if (supply->type == kSupplyHp || supply->type == kSupplyAmmo)
		{
			supply->Destroy();
		}
	}
}

void Client::ParseUseSkill()
{
	if (!IsAlive())
		return;
	
	byte skill_type;
	byte skill_to_uid;
	float effect_value;
	float effect_time;

	ReadByte(skill_type);
	ReadByte(skill_to_uid);
	ReadFloat(effect_value);
	ReadFloat(effect_time);
	
	bool isuse = false;
	Client *to_c = server.GetClient(skill_to_uid);
	switch (skill_type)
	{
		case kSkillCureGun:
			if (GetCurePower() >= 100.f && GetCurWeaponInfo().weapon_data.weapon.type == kWeaponTypeCureGun)
			{
				effect.OnClientUseSkill(kSkillSlot_LeftButton);
				ClearCurePower();
				isuse = true;
			}
			break;

		case kSkillZombie:
			if(zombie_skill_cd <= 0.f && GetCurWeaponInfo().weapon_data.weapon.type == kWeaponTypeZombieGun)
			{
				if (server.game_type == kCommonZombieMode)
				{
					if (health > ((int)(max_health / 10.f)) && !zombie_unable_state)
					{
						if (buff_zombie_id != 45)
						{
							SkillHurt(this, (int)(max_health / 10.f));
						}
						effect.OnClientUseSkill(kSkillSlot_LeftButton);
						zombie_skill_cd = GetCurWeaponInfo().weapon_data.zb_info.skill_cooldown;
						EffectData effectdata;

						effectdata.duration_timer = 0;
						effectdata.type = kEffect_Infect_HitBack;
						effectdata.value = -1;
						effectdata.enable = true;

						effect.ApplySystemEffect(effectdata);
						can_Invincible = false;
						isuse = true;
					}
				}
				else
				{
					effect.OnClientUseSkill(kSkillSlot_LeftButton);
					zombie_skill_cd = GetCurWeaponInfo().weapon_data.zb_info.skill_cooldown;
					isuse = true;
				}
			}
			break;

		case kSkillZombieCharge:
			if (charge_skill_cd < 0.f && GetCurWeaponInfo().weapon_data.weapon.type == kWeaponTypeZombieCharge)
			{
				ischarge_skill = true;
				charge_skill_time = -0.1f;
				charge_skill_cd = GetCurWeaponInfo().weapon_data.zbc_info.skill_cooldown;
				charge_skill_alltime = GetCurWeaponInfo().weapon_data.zbc_info.skill_usetime;

				EffectData effectdata;
				// 冲锋时增加75%抗击退效果
				effectdata.duration_timer = charge_skill_alltime;
				effectdata.type = kEffect_Infect_HitBack;
				effectdata.value = -0.75;
				effectdata.enable = true;
				effect.ApplySystemEffect(effectdata);

				// 冲锋时速度提升200%
				effectdata.type = kEffect_Infect_MoveSpeed;
				effectdata.value = 2.0f;
				effect.ApplySystemEffect(effectdata);

				isuse = true;
			}
			break;

		case kSkillHunterCharge:
			{
				isuse = true;
			}
			break;

		case kSkillActiveSlot1:
			{
				effect.OnClientUseSkill(kSkillSlot_Slot1);
				isuse = true;
			}
			break;
		case kSkillInvincible:
			{
				if (can_Invincible)
				{
					EffectData effectdata;

					effectdata.duration_timer = effect_time;
					effectdata.type = kEffect_Invincible;
					effectdata.enable = true;

					effect.ApplySystemEffect(effectdata);
					can_Invincible = false;
				}					
			}
			break;
		case kSkillWindReverse:
			{
				isuse = true;
			}
			break;
		case kSkillControlStickBomb:
			break;
			
		case kSkillBoss2_S1:
			if (server.game_type == kBossMode2 && team == 0)
			{
				if (boss2_initiative_type != 0)
				{
					switch (boss2_initiative_type)
					{
						case 1:
							{
								EffectData effectdata;

								effectdata.duration_timer = 6.f;
								effectdata.type = kEffect_Invincible;
								effectdata.enable = true;

								effect.ApplySystemItemEffect(effectdata, BOSS2_ITEMID_INITIATIVE);
							}
							break;
							
						case 2:
							{
								Recover(9999, kRecoverSelf);
							}
							break;
							
						case 3:
							{
								EffectData effectdata;

								effectdata.duration_timer = 10.f;
								effectdata.type = kEffect_Infect_Damage;
								effectdata.value = 0.3f;
								effectdata.enable = true;

								effect.ApplySystemItemEffect(effectdata, BOSS2_ITEMID_INITIATIVE);
							}
							break;
							
						case 4:
							{
								EffectData effectdata;

								effectdata.duration_timer = 10.f;
								effectdata.type = kEffect_Infect_FireTime;
								effectdata.value = -0.3f;
								effectdata.enable = true;

								effect.ApplySystemItemEffect(effectdata, BOSS2_ITEMID_INITIATIVE);
							}
							// {
								// EffectData effectdata;

								// effectdata.duration_timer = 10.f;
								// effectdata.type = kEffect_Infect_ReloadTime;
								// effectdata.value = -0.3f;
								// effectdata.enable = true;

								// effect.ApplySystemItemEffect(effectdata, BOSS2_ITEMID_INITIATIVE);
							// }
							break;
					}
					
					boss2_initiative_type = 0;
					
					Boss2SyncData();
				}
			}
			break;
			
		case kSkillBoss2_S2:
			if (server.game_type == kBossMode2 && team == 0)
			{
				if (boss2_strange_spawn != -1)
					boss2_strange_spawn_use = true;
			}
			break;

		case kSillCharacter_KC6:
			{
				effect.OnClientUseSkill(kSkillSlot_KC_6);
				isuse = true;
				SkillData* t_skill = effect.GetSkillData(kSkillSlot_KC_6, GetCurCharinfo().career_id);
				if (NULL == t_skill)
					break;

				switch(t_skill->skill_id)
				{
				case kCharacterSkill_EnergyShield:
					{
						EffectData effectdata;
						effectdata.duration_timer = t_skill->param[1];
						effectdata.type = kEffect_Sustain_HurtBloodshed;
						effectdata.value = t_skill->param[3];
						effectdata.enable = true;
						effectdata.dead_disable = true;
						effect.ApplySystemEffect(effectdata);

						effectdata.duration_timer = t_skill->param[1];
						effectdata.type = kEffect_Infect_HurtAbsorb;
						effectdata.value = t_skill->param[2]/100;
						effectdata.enable = true;
						effectdata.dead_disable = true;
						effect.ApplySystemEffect(effectdata);

						t_skill->cd_time = t_skill->param[0];
						t_skill->duration_timer = t_skill->param[1];
						t_skill->loop = true;
						t_skill->enable = true;

						effect_value = t_skill->skill_id;
						effect_time = t_skill->param[0];
					}
					break;
				default:
					assert(NULL == "the skill id is unkown !!");
					log_write(LOG_DEBUG1, "the skill id is unkown !!");
				}
			}
			break;
	}

	if (isuse)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_UseSkill);
				c->WriteByte(to_c ? to_c->uid : 0);//to uid
				c->WriteByte(uid);//from uid
				c->WriteByte(skill_type);
				c->WriteFloat(effect_value);
				c->WriteFloat(effect_time);
				c->EndWrite();
			}
		}
	}
	
	OnAction();
}

void Client::ParseMoonBoss()
{
	ismoonboss = true;
	uint target_career_backup = target_career;

	target_career = server.level_info.boss_info.career_id;

	Transform transform;
	transform.position = position;
	transform.rotation = rotation.GetZXY().y * RAD2DEG;

	Spawn(transform, NULL, false, false, true);

	target_career = target_career_backup;
}

void Client::ParseUseItem_ItemMode()
{
	if (!IsAlive())
		return;
	
	bool flag1 = false;
	int itemmode_item_slot_new = -1;

	uint uids_size;
	std::vector<byte> uids;
	
	ReadInt(uids_size);
	uids.reserve(uids_size);
	for (uint i = 0; i < uids_size; i++)
	{
		byte v;
		
		ReadByte(v);
		
		uids.push_back(v);
	}
	
	if (server.game_type == kItemMode)
	{
		if (itemmode_item_slot != -1 && 
			GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
		{
			switch (itemmode_item_slot)
			{
			case 0:
				{
					Recover((int)(max_health * 1.0f), kRecoverSelf);
				}
				break;
			case 1:
				{
					Recover((int)(max_health * 0.5f), kRecoverSelf);
				}
				break;
			case 2:
				{
					EffectData effectdata;

					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Infect_MoveSpeed;
					effectdata.value = 0.5f;
					effectdata.enable = true;

					effect.ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID);
				}
				break;
			case 3:
				{
					EffectData effectdata;

					effectdata.duration_timer = 5.f;
					effectdata.type = kEffect_Invincible;
					effectdata.enable = true;

					effect.ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID);
				}
				break;
			case 4:
				{
					itemmode_zibao = true;
					
					{
						EffectData effectdata;

						effectdata.duration_timer = 5.f;
						effectdata.type = kEffect_Infect_MoveSpeed;
						effectdata.value = 0.3f;
						effectdata.enable = true;

						effect.ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID2);
					}
					
					{
						EffectData effectdata;

						effectdata.duration_timer = 5.f;
						effectdata.type = kEffect_Infect_FOV;
						effectdata.value = 20.f;
						effectdata.enable = true;

						effect.ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID2);
					}
				}
				break;
			case 5:
				{
					EffectData effectdata;

					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Special_Invisible;
					effectdata.value = 1.0f;
					effectdata.enable = true;

					effect.ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID);
				}
				break;
			case 6:
				{
					EffectData effectdata;

					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Infect_MoveSpeed;
					effectdata.value = -0.7f;
					effectdata.enable = true;

					for (int i = 0; i < uids.size(); i++)
					{
						Client * c = server.GetClient(uids[i]);
						
						if (c->IsReady() && c->IsAlive() && c->team < 2 && c->team != team)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 7:
				{
					EffectData effectdata;

					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Special_ReversalMouse2;
					effectdata.enable = true;
					
					for (int i = 0; i < uids.size(); i++)
					{
						Client * c = server.GetClient(uids[i]);
						
						if (c->IsReady() && c->IsAlive() && c->team < 2 && c->team != team)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 8:
				{
					EffectData effectdata;

					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Special_ReversalKeyBoard;
					effectdata.enable = true;
					
					for (int i = 0; i < uids.size(); i++)
					{
						Client * c = server.GetClient(uids[i]);
						
						if (c->IsReady() && c->IsAlive() && c->team < 2 && c->team != team)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 9:
				{
				
					EffectData effectdata;

					effectdata.duration_timer = 5.f;
					effectdata.type = kEffect_Special_ViewLost;
					effectdata.enable = true;
					
					for (int i = 0; i < uids.size(); i++)
					{
						Client * c = server.GetClient(uids[i]);
						
						if (c->IsReady() && c->IsAlive() && c->team < 2 && c->team != team)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 10:
				{
					EffectData effectdata;

					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Infect_JumpHeight;
					effectdata.value = 1.0f;
					effectdata.enable = true;

					effect.ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID);
				}
				break;
			case 11:
				{
					EffectData effectdata;

					effectdata.duration_timer = 5.f;
					effectdata.type = kEffect_Special_CannotFire;
					effectdata.enable = true;
					
					for (int i = 0; i < uids.size(); i++)
					{
						Client * c = server.GetClient(uids[i]);
						
						if (c->IsReady() && c->IsAlive() && c->team < 2 && c->team != team)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 12:
				{
					EffectData effectdata;

					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Infect_JumpHeight;
					effectdata.value = -1.0f;
					effectdata.enable = true;
					
					for (int i = 0; i < uids.size(); i++)
					{
						Client * c = server.GetClient(uids[i]);
						
						if (c->IsReady() && c->IsAlive() && c->team < 2 && c->team != team)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 13:
				{
					itemmode_item_slot_new = 2*rand() % 2 + 15;
				}
				break;
			case 14:
				{
					flag1 = true;
				}
				break;
			case 15:
				{
					EffectData effectdata;
					
					effectdata.enable = true;
					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Infect_MoveSpeed;
					effectdata.value = -0.6f;
					
					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = server.clients + i;
						
						if (c->IsReady() && c->IsAlive() && c != this)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 16:
				{
					EffectData effectdata;
					
					effectdata.enable = true;
					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Special_ReversalMouse2;
					
					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = server.clients + i;
						
						if (c->IsReady() && c->IsAlive() && c != this)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 17:
				{
					EffectData effectdata;
					
					effectdata.enable = true;
					effectdata.duration_timer = 10.f;
					effectdata.type = kEffect_Infect_JumpHeight;
					effectdata.value = -1.f;
					
					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = server.clients + i;
						
						if (c->IsReady() && c->IsAlive() && c != this)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 18:
				{
					EffectData effectdata;
					
					effectdata.enable = true;
					effectdata.duration_timer = 5.f;
					effectdata.type = kEffect_Special_CannotFire;
					
					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = server.clients + i;
						
						if (c->IsReady() && c->IsAlive() && c != this)
						{
							if (c->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
								c->GetSkillEffect().ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID3);
						}
					}
				}
				break;
			case 25:
				{
					EffectData effectdata;

					effectdata.duration_timer = 3.f;
					effectdata.type = kEffect_Invincible;
					effectdata.enable = true;

					effect.ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID);
					
					itemmode_skill_cd = 3.0f;
					//effectdata.duration_timer = 3.0f;
					//effectdata.interval_timer = 3.0f;
					//effectdata.type = kEffect_Infect_Damage;
					//effectdata.value = 0.5f;
					//effectdata.enable = true;
					//
					//effect.ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID);
				}
				break;
			default:
				log_write(LOG_NOTICE, "ParseUseItem_ItemMode() Unknow item type : %d", itemmode_item_slot);
				break;
			}
		}
		
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_UseItem_ItemMode);
				c->WriteByte(uid);
				c->WriteInt(itemmode_item_slot);
				c->WriteInt((int)uids.size());
				for (int i = 0; i < uids.size(); i++)
				{
					c->WriteByte(uids[i]);
				}
				c->EndWrite();
			}
		}
		
		itemmode_item_slot = itemmode_item_slot_new;
		
		ItemModeSyncData();
	}
	
	OnAction();
	
	if (flag1)
	{
		uint target_career_backup = target_career;
		
		target_career = server.level_info.boss_info.career_id;
		
		Transform transform;
		transform.position = position;
		transform.rotation = rotation.GetZXY().y * RAD2DEG;
		
		Spawn(transform, NULL, false, false, true);
		
		target_career = target_career_backup;
	}
}

void Client::ParseUseItemSurvival()
{
	int index;

	ReadInt(index);
	int count = 1;

	byte type;

	for (std::list<SurvivalModeItemInfo>::iterator itr = m_character_info.survivalbag.begin(); 
		itr != m_character_info.survivalbag.end(); itr++)
	{
		if (count == index)
		{
			type = itr->type;
			switch(itr->type)
			{
			case kSupplySurvivalItemHp:
				{
					bool isneed_adddate = false;
					isneed_adddate = Recover((int)(max_health * itr->value / 100), kRecoverSupplyHp);
				}
				break;
			case kSupplySurvivalItemAmmo:
				{
					bool isneed_adddate = false;
					isneed_adddate = AmmoRecover(kSupplySurvivalItemAmmo, (short)(itr->value));
				}
				break;
			case kSupplySurvivalIteminitiative:
				{
					EffectData effectdata;

					effectdata.duration_timer = 3.f;
					effectdata.type = kEffect_Invincible;
					effectdata.enable = true;

					effect.ApplySystemEffect(effectdata);
					can_Invincible = false;
				}
				break;
			case kSupplySurvivalItemTrapAmmo:
			case kSupplySurvivalItemTrapHP:
			case kSupplySurvivalItemTrapExpose:
			case kSupplySurvivalItemTrapBomb:
			case kSupplySurvivalItemTrapDebuff:
				{
					TrapInfo t;
					t.position = position;
					t.type = itr->type;
					t.uid = uid;
					t.team = team;
					t.time = 180.f;
					server.trap_info.push_back(t);
					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = server.clients + i;

						if (c->IsReady())
						{
							c->BeginWrite();
							c->WriteByte(SM_UseItem_Trap);
							c->WriteVector3(t.position);
							c->WriteByte(t.type);
							c->WriteByte(t.uid);
							c->WriteByte(t.team);
							c->WriteInt(0);
							c->EndWrite();
						}
					}
				}
				break;
			}
			m_character_info.survivalbag.erase(itr);
			break;
		}
		count++;
	}

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_UseItem_SurvivalMode);
			c->WriteByte(uid);
			c->WriteInt(index);
			c->WriteByte(type);
			c->EndWrite();
		}
	}
}

void Client::ParseUseItemSurvivalByGhost()
{
	int index;

	ReadInt(index);

	bool flag = false;

	TrapInfo t;
	t.position = position;
	t.uid = uid;
	t.team = team;
	t.time = 30.f;

	int ghost_fire_count = 0;

	switch(index)
	{
	case 1:
		{
			if (ghostfirecount >= 15)
			{
				t.type = kSupplySurvivalItemTrapAmmo;
				flag = true;
				ghostfirecount -= 15;
				ghost_fire_count = 15;
			}
		}
		break;
	case 2:
		{
			if (ghostfirecount >= 20)
			{
				t.type = kSupplySurvivalItemTrapBomb;
				flag = true;
				ghostfirecount -= 20;
				ghost_fire_count = 20;
			}
		}
		break;
	case 3:
		{
			if (ghostfirecount >= 20)
			{
				t.type = kSupplySurvivalItemTrapDebuff;
				flag = true;
				ghostfirecount -= 20;
				ghost_fire_count = 20;
			}
		}
		break;
	}
	if (flag)
	{
		server.trap_info.push_back(t);
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_UseItem_Trap);
				c->WriteVector3(t.position);
				c->WriteByte(t.type);
				c->WriteByte(t.uid);
				c->WriteByte(t.team);
				c->WriteInt(ghost_fire_count);
				c->EndWrite();
			}
		}
	}
}

void Client::ParseUseSkillSuperMan()
{
	if (super_human_skill_cd_time == 0.0f)
	{
		byte uid;
		ReadByte(uid);
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_UseSkillSuperMan);
				c->WriteByte(uid);
				c->EndWrite();
			}
		}
		ParseSkillSuperManSuccess();
	}
}

void Client::ParseCancel_Invisible()
{
	effect.CancelInvisible();
	byte uid;
	ReadByte(uid);
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_CancelInvisible);
			c->WriteByte(uid);
			c->EndWrite();
		}
	}
}

void Client::ParseUseSmog()
{
	if (GetSkillEffect().HasEffect(kEffect_Special_Smog))
	{
		byte uid;
		Vector3 pos;
		ReadByte(uid);
		ReadVector3(pos);
		std::map<uint, Vector3>::iterator itr = server.somg_area.find(uid);
		if (itr != server.somg_area.end())
		{
			return;
		}
		server.somg_area.insert(std::make_pair(uid, pos));
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_UseSmog);
				c->WriteByte(uid);
				c->WriteVector3(pos);
				c->EndWrite();
			}
		}
	}
}

void Client::ParseSomgAreaCancel()
{
	byte uid;
	ReadByte(uid);
	server.somg_area.erase(uid);
}

void Client::ParseSkillSuperManSuccess()
{
	if (super_human_skill_cd_time == 0.0f)
	{
		BeginWrite();
		WriteByte(SM_SkillSuperManSuccess);
		WriteByte(uid);
		EndWrite();
		super_human_skill_cd_time = 20.f;
	}
}

void Client::ParseChangePack()
{
	log_write(LOG_DEBUG4, "Change Pack");

	if (!IsAlive())
		return;

	if (team > 1)
		return;

	if (weapon_used)
		return;

	byte id;
	ReadByte(id);

	// int index = 0;
	// int pack_index_new = pack_index;

	// const SingleCharacterInfo& current_character = GetCurCharinfo();
	// for (std::vector<PackInfo>::const_iterator i = current_character.packs.begin(); i < current_character.packs.end(); i++)
	// {
		// if (i->id == id && index != pack_index_new)
		// {
			// pack_index_new = index;
			// Weapon bomb = pack.weapon_set[6];
			// pack = *i;
			// pack.weapon_set[6] = bomb;
			// break;
		// }
		// index++;
	// }

	// if (pack_index_new != pack_index)
	// {
		// pack_index = pack_index_new;

		// for (uint i = 0; i < max_client_count; i++)
		// {
			// Client * c = server.clients + i;

			// if (c->IsReady())
			// {
				// c->BeginWrite();
				// c->WriteByte(SM_ChangePack);
				// c->WriteByte(uid);
				// c->WriteByte(pack_index);
				// WritePackInfo(*c, pack);
				// c->EndWrite();
			// }
		// }
	// }
}

void Client::ParseKickClientStart()
{
	byte kicked_uid;
	byte kicked_reason;
	ReadByte(kicked_uid);
	ReadByte(kicked_reason);

	Client* client = server.GetClient(kicked_uid);

	if (!client)
	{
		BeginWrite();
		WriteByte(SM_KickClientError);
		WriteInt(3);		// no client
		EndWrite();
		return;
	}

	if (server.is_voting)
	{
		BeginWrite();
		WriteByte(SM_KickClientError);
		WriteInt(0);		// is voting
		EndWrite();
		return;
	}

	if (is_vip)
	{
		if (team > 1)
		{
			BeginWrite();
			WriteByte(SM_KickClientError);
			WriteInt(1);	// error info
			EndWrite();
			return;
		}

		if (kick_client_count <= 0)
		{
			BeginWrite();
			WriteByte(SM_KickClientError);
			WriteInt(2);	// you have no chance
			EndWrite();
			return;
		}
	}
	else
	{
		if (team > 1)
		{
			BeginWrite();
			WriteByte(SM_KickClientError);
			WriteInt(1);		// error info
			EndWrite();
			return;
		}
		
		if (client->team != team)
		{
			BeginWrite();
			WriteByte(SM_KickClientError);
			WriteInt(4);		// team error
			EndWrite();
			return;
		}

		if (kick_client_count <= 0)
		{
			BeginWrite();
			WriteByte(SM_KickClientError);
			WriteInt(2);		// you have no chance
			EndWrite();
			return;
		}
	}

	server.OnKickClientStart(uid, kicked_uid, kicked_reason);

	kick_client_count--;
}

void Client::ParseKickClientVote()
{
	if (!server.is_voting)
		return;

	if (team > 1)
		return;

	if (is_voted)
		return;

	bool finished = true;

	for (std::vector<short>::iterator i = server.vote_clients.begin(); i < server.vote_clients.end(); i++)
	{
		if (((*i) & 0x00FF) == uid)
		{
			byte result;
			ReadByte(result);
			*i |= result << 8;
			is_voted = true;

			for (uint j = 0; j < max_client_count; j++)
			{
				Client * c = server.clients + j;

				if (c->IsReady())
				{
					c->BeginWrite();
					c->WriteByte(SM_KickClientVote);
					c->WriteByte(uid);
					c->WriteByte(result);
					c->EndWrite();
				}
			}
		}
		else
		{
			Client* client = server.GetClient((*i) & 0x00FF);

			if (client && client->IsReady() && ((*i) & 0xFF00) == 0)
				finished = false;
		}
	}

	if (finished && is_voted)
		server.OnKickClientEnd();
}

void Client::AddDropSupply(const DroppedSupply * dropped_supply)
{
	if (dropped_supply)
	{
		BeginWrite();
		WriteByte(SM_AddDropSupply);
		WriteInt(dropped_supply->uid);
		WriteByte(dropped_supply->type);
		WriteInt(dropped_supply->value);
		WriteVector3(dropped_supply->position);
		WriteFloat(dropped_supply->rotation);
		EndWrite();
	}
}

// add dropped weapon
void Client::AddDroppedWeapon(const DroppedWeapon * dropped_weapon)
{
	if (dropped_weapon)
	{
		BeginWrite();
		WriteByte(SM_AddDroppedWeapon);
		WriteInt(dropped_weapon->uid);
		WriteVector3(dropped_weapon->position);
		WriteFloat(dropped_weapon->rotation);
		WriteWeapon(*this, dropped_weapon->weapon);
		EndWrite();

		log_write(LOG_DEBUG5, "GAME: client add weapon : %d", dropped_weapon->uid);
	}
}

// add level supply
void Client::AddSupplyObject(const SupplyObject * supply, byte owner_id, short team)
{
	if (supply)
	{
		BeginWrite();
		WriteByte(SM_AddSupplyObject);
		WriteInt(supply->uid);
		WriteVector3(supply->position);
		WriteFloat(supply->rotation);
		WriteByte(supply->supply.type);
		WriteByte(owner_id);
		WriteShort(team);
		EndWrite();
	}
}


// dropped gun destroy
void Client::DestroySupplyObject(uint uid)
{
	if (IsConnected())
	{
		BeginWrite();
		WriteByte(SM_DestroySupplyObject);
		WriteInt(uid);
		EndWrite();
	}
}

// on skilleffect changed
void Client::OnSkillEffectChanged()
{
	{
		float value = 1;
		
		effect.SumEffect(kEffect_Infect_MaxHp, value);
		
		max_health = Max((int)(max_health_base * value), 1);
		ex_health = Max((int)(ex_health_base * value), 1);
		
		DEBUGLOG_WRITE("OnSkillEffectChanged() name : %s, max_health : %d, ex_health : %d", 
						GetCharacterInfo().character_name, max_health, ex_health);
	}
	
	{
		float value_aoc = 1;
		float value_ac = 1;
		
		effect.SumEffect(kEffect_Infect_AmmoOneClip, value_aoc);
		effect.SumEffect(kEffect_Infect_AmmoCount, value_ac);
		
		SingleCharacterInfo &cur_charinfo = GetCurCharinfo();
		for (std::vector<PackInfo>::iterator itr = cur_charinfo.packs.begin(); 
			itr != cur_charinfo.packs.end(); itr++)
		{
			PackInfo &packinfo = *itr;
			
			for (std::map<byte, Weapon>::iterator itr = packinfo.weapon_set.begin(); 
				itr != packinfo.weapon_set.end(); itr++)
			{
				if (Weapon_IsGun(itr->second.weapon_data.weapon.type))
				{
					GunInfo &info = itr->second.weapon_data.gun;
					
					bool refill_aoc = (info.ammo_one_clip == info.ammo_in_clip);
					bool refill_ac = (info.ammo_count == info.ammo_count_current);

					if (server.game_type != kSurvivalMode)
					{
						info.ammo_one_clip = Max((int)(info.ammo_default_one_clip * value_aoc), 0);
						if (refill_aoc)
							info.ammo_in_clip = info.ammo_one_clip;
						info.ammo_in_clip = Min(info.ammo_in_clip, info.ammo_one_clip);

						info.ammo_count = Max((int)(info.ammo_default_count * value_ac), 0);
						if (refill_ac)
							info.ammo_count_current = info.ammo_count;
						info.ammo_count_current = Min(info.ammo_count_current, info.ammo_count);

						DEBUGLOG_WRITE("OnSkillEffectChanged() name : %s, sid : %d, ammo_one_clip : %d, ammo_in_clip : %d, ammo_count : %d, ammo_count_current : %d, ammo_default_one_clip : %d, ammo_default_count : %d", 
							GetCharacterInfo().character_name, itr->second.base_info.player_item_id, 
							info.ammo_one_clip, info.ammo_in_clip, info.ammo_count, info.ammo_count_current, 
							info.ammo_default_one_clip, info.ammo_default_count);
					}
					else
					{
						if (info.ammo_one_clip != 0)
						{
							info.ammo_one_clip = Max((int)(info.ammo_default_one_clip * value_aoc), 0);
							if (refill_aoc)
								info.ammo_in_clip = info.ammo_one_clip;
							info.ammo_in_clip = Min(info.ammo_in_clip, info.ammo_one_clip);
						}

						if (info.ammo_count != 0)
						{
							info.ammo_count = Max((int)(info.ammo_default_count * value_ac), 0);
							if (refill_ac)
								info.ammo_count_current = info.ammo_count;
							info.ammo_count_current = Min(info.ammo_count_current, info.ammo_count);
						}
					}
				}
			}
		}
	}
}

// drop weapon
void Client::DropWeapon(byte weapon_id, const Vector3 & pos, float rot)
{
	if (team > 1)
		return;
	
	Weapon *pW = GetWeaponInfo(weapon_id);
	if (!pW)
	{
		return;
	}
	
	Weapon w;
	w = *pW;
	
	// hack fix start
	if (w.weapon_data.weapon.type != kWeaponTypeBomb)
	// hack fix end
		RemoveWeapon(weapon_id);
	
	if (w.weapon_data.weapon.type)
	{
		DroppedWeapon* dropped_weapon = server.dropped_weapon.Allocate();

		if (dropped_weapon)
		{
			log_write(LOG_DEBUG5, "GAME: drop weapon : %d", dropped_weapon->uid);
			dropped_weapon->Create();
			dropped_weapon->weapon = w;

			dropped_weapon->position = pos;
			dropped_weapon->rotation = rot;
			if(w.weapon_data.weapon.type == kWeaponTypeBomb)
			{
				dropped_weapon->auto_destroy = false;
			}

			//broadcast player drop gun
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;

				if (c->IsReady())
				{
					c->BeginWrite();
					c->WriteByte(SM_PlayerDropWeapon);
					c->WriteByte(uid);
					c->WriteByte(weapon_id);
					c->WriteInt(dropped_weapon->uid);
					WriteWeapon(*c, dropped_weapon->weapon);
					c->EndWrite();
				}
			}
		}

	}
}

// drop supply
void Client::AddZombieDropSupply(byte supply_type, const Vector3 & pos, int value)
{
	for (DroppedSupply* droped_supply = server.dropped_supply.Begin(); droped_supply < server.dropped_supply.End(); droped_supply++)
	{
		if (droped_supply && !droped_supply->IsActive())
		{
			droped_supply->Create();
			droped_supply->position = pos;
			droped_supply->value = value;
			droped_supply->type = supply_type;
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;

				if (c->IsReady())
				{
					c->AddDropSupply(droped_supply);
				}
			}
			break;
		}
	}
}

// parse drop weapon
void Client::ParseDropWeapon()
{
	if (team > 1)
		return;

	byte weapon_id;
	int ammo_in_clip;
	int ammo_count;
	Vector3 pos;
	float rot;

	ReadByte(weapon_id);
	ReadInt(ammo_in_clip);
	ReadInt(ammo_count);
	ReadVector3(pos);
	ReadFloat(rot);

	DropWeapon(weapon_id, pos, rot);

	OnAction();
}

// parse select weapon
void Client::ParseSelectWeapon()
{
	byte new_weapon = 0;
	byte old_weapon = GetCurWeapon();

	ReadByte(new_weapon);

	SetCurWeapon(new_weapon);
	new_weapon = GetCurWeapon();

	// broadcast player select weapon
	if (old_weapon != new_weapon)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_PlayerSelectWeapon);
				c->WriteByte(uid);
				c->WriteByte(GetCurWeapon());
				c->EndWrite();
			}
		}
	}

	OnAction();
}


// parse pick up Weapon
void Client::ParsePickUpWeapon()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	uint id;
	byte weapon_id;
	ReadInt(id);

	DroppedWeapon* dropped_weapon = server.dropped_weapon.Get(id);
	if (dropped_weapon && dropped_weapon->IsActive())
	{
		if (dropped_weapon->weapon.weapon_data.weapon.type != kWeaponTypeBomb)
		{
			AmmoRecover(kSupplyDropWeapon, 10);
		}
		else
		{
			server.bomb_owner_id = uid;
			// broadcast player set weapon
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;

				if (c->IsReady())
				{
					c->BeginWrite();
					c->WriteByte(SM_PickUpBomb);
					c->WriteByte(uid);
					c->WriteByte(id);
					c->EndWrite();
				}
			}
		}
		dropped_weapon->Destroy();
	}
}

void Client::ParseProjectedAmmoOut()
{
	log_write(LOG_DEBUG1, "ParseProjectedAmmoOut called, weapon type: %d, team: %d", GetCurWeaponInfo().weapon_data.weapon.type, team);
	if (team > 1)
		return;

	if (!IsAlive())
		return;
/*
	CM_ProjectedAmmoOut Message
		-ProjectedAmmo id(Int16)
		-ProjectedAmmo postion(Vector3)
		-ProjectedAmmo direction(Vector3)
	*/

	ushort ammo_id;
	Vector3 pos;
	Vector3 dir;
	float speed_addon;
	float gravity_addon;

	ReadShort(ammo_id);
	ReadVector3(pos);
	ReadVector3(dir);
	ReadFloat(speed_addon);
	ReadFloat(gravity_addon);

	std::map<ushort, Weapon>::iterator itr = projected_ammo_set.find(ammo_id);
	if (itr != projected_ammo_set.end())
	{
		return;
	}

	Weapon &weapon = GetCurWeaponInfo();
	if (weapon.weapon_data.weapon.type != kWeaponTypeLuncher &&
		weapon.weapon_data.weapon.type != kWeaponTypeMeditNeedleGun &&
		weapon.weapon_data.weapon.type != kWeaponTypeSignal &&
		weapon.weapon_data.weapon.type != kWeaponTypeMiniMachineGun && 
		projected_ammo_info.weapon_data.weapon.type == kWeaponTypeNone)
	{
		return;
	}

	Weapon w = projected_ammo_info;
	bool is_boost(false);
	if(GetGaiLv(rand_fun.Rand(), rand_fun.GetRandMax()) * 10000 < w.weapon_data.weapon.hit_crit)
		is_boost = true;
	LuncherInfo & luncher = weapon.weapon_data.luncher;
	if (luncher.ammo_in_clip == 0 && luncher.time_to_idle <= 0)
	{
		return;
	}
	if (luncher.time_to_idle <= 0)
		luncher.ammo_in_clip--;
	if (luncher.time_to_idle > 0)
	{
		if (luncher.time_to_idlecount > 0)
			return;
		luncher.time_to_idlecount = luncher.time_to_idle - 1;
	}

	w.weapon_data.ammo.isboost = is_boost;
	w.weapon_data.ammo.hit_speed = weapon.weapon_data.luncher.fire_time;
	projected_ammo_set.insert(std::pair<ushort, Weapon>(ammo_id, w));

	// broadcast player fire ProjectedAmmo
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			/*
			SM_ProjectedAmmoOut Message
				-client id(byte)
				-ProjectedAmmo id(Int16)
				-ProjectedAmmo postion(Vector3)
				-ProjectedAmmo direction(Vector3)
				-ProjectedAmmo WeaponInfo
			*/
			c->BeginWrite();
			c->WriteByte(SM_ProjectedAmmoOut);
			c->WriteByte(uid);
			c->WriteShort(ammo_id);
			c->WriteVector3(pos);
			c->WriteVector3(dir);
			WriteWeapon(*c, w);
			c->WriteByte(is_boost);
			c->WriteFloat(speed_addon);
			c->WriteFloat(gravity_addon);
			c->EndWrite();
		}
	}

	OnAction();
}
void Client::ParseNeedDieBuff()
{
	if (team > 1)
		return;

	if (!IsAlive())
		return;

	int iSlot =0;
	ReadInt(iSlot);

	if(iSlot >=0 && (iSlot < (int)(server.level_info.die_buff_data.size())))
	{
		DieBuffData& dd =server.level_info.die_buff_data[iSlot];
		EffectData ed;
		ed.duration_timer =dd.duration_timer;
		if (kEffect_Sustain_AmmoRecover == dd.type)
		{
			ed.interval = 1.0f;
			ed.interval_timer = 1.0f;
		}
		else
		{
			ed.interval =dd.interval;
			ed.interval_timer =0;
		}
		ed.iterval_callback =0;
		ed.player_id =0;
		ed.player_item_id =0;
		ed.attr_slotid =-1;
		ed.attr_sub_slotid =-1;
		ed.type =(EEffect)dd.type;
		ed.value =dd.value;
		ed.enable =true;

		ed.value =ed.value * (1 + 0.1f * die_buff_counter);
		GetSkillEffect().ApplySystemEffect(ed);
		die_buff_counter +=1;
	}
}
void Client::ParseProjectedAmmoDestroy()
{
	if (team > 1)
		return;
	/*
	CM_ProjectedAmmoDestroy Message
		-ProjectedAmmo id(Int16)
		-delta(Int8)
		-ProjectedAmmo postion(Vector3)
		-ProjectedAmmo direction(Vector3)
		-impact_flg(Int8)
		-ProjectedAmmo impact_normal(Vector3)
		-ProjectedAmmo impact_pos(Vector3)
	*/

	ushort ammo_id;

	byte delta;
	Vector3 pos;
	Vector3 dir;

	byte impact_flg;
	Vector3 impact_normal;
	Vector3 impact_pos;

	ReadShort(ammo_id);
	ReadByte(delta);
	ReadVector3FP(pos);
	ReadVector3FP(dir);
	ReadByte(impact_flg);
	ReadVector3FP(impact_normal);
	ReadVector3FP(impact_pos);

	std::map<ushort, Weapon>::iterator itr = projected_ammo_set.find(ammo_id);
	if (itr == projected_ammo_set.end())
	{
		return;
	}
	projected_ammo_set.erase(itr);

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady() && c->uid != uid)
		{
			/*
			SM_ProjectedAmmoDestroy Message
				-client id(byte)
				-ProjectedAmmo id(Int16)
				-delta(Int8)
				-ProjectedAmmo postion(Vector3)
				-ProjectedAmmo direction(Vector3)
				-impact_flg(Int8)
				-ProjectedAmmo impact_normal(Vector3)
				-ProjectedAmmo impact_pos(Vector3)
			*/
			c->BeginWrite();
			c->WriteByte(SM_ProjectedAmmoDestroy);
			c->WriteByte(uid);
			c->WriteShort(ammo_id);
			c->WriteByte(delta);
			c->WriteVector3FP(pos);
			c->WriteVector3FP(dir);
			c->WriteByte(impact_flg);
			c->WriteVector3FP(impact_normal);
			c->WriteVector3FP(impact_pos);
			c->EndWrite();
		}
	}
}

void Client::ResetAllCure()
{
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;
		if (c->IsReady())
		{
			std::map<int, int>::iterator itr = c->cure_list.find(uid);
			if (itr != c->cure_list.end())
				c->cure_list.erase(itr);
		}
	}
}

void Client::CheckCure()
{
	std::map<int, int>::iterator itr = cure_list.begin();
	for(; itr != cure_list.end();itr++)
	{
		Client* player = server.GetClient(itr->first);
		if (player)
		{
			int add_num = itr->second * pow(cure_list.size(),0.4138) / cure_list.size();
			Recover(add_num, kRecoverCuregun, player->uid);
		}
	}
	cure_list.clear();
}

static short GetCureGunSkill(uint player_id, const BaseItemInfo &info, EffectDataList& effects)
{
	short type = kWeaponAttrNone;
	
	for (uint slotid = 0; slotid < info.attrs.size(); slotid++)
	{
		const AttributeList &attributelist = info.attrs[slotid];
		for (uint sub_slotid = 0; sub_slotid < attributelist.size(); sub_slotid++)
		{
			const Attribute &attribute = attributelist[sub_slotid];
			switch (attribute.type)
			{
				case kWeaponAttr_Skill_AddBlood:
					{
						type = attribute.type;
					}
					break;
				case kWeaponAttr_Skill_Invincible:
					{
						type = attribute.type;
						
						EffectData effect;
						
						effect.duration_timer = 1.5f;
						effect.player_id = player_id;
						effect.player_item_id = info.player_item_id;
						effect.attr_slotid = slotid;
						effect.attr_sub_slotid = sub_slotid;
						effect.attr_raw = attribute;
						effect.type = kEffect_Invincible;
						effect.value = attribute.value1 / 100.f;
						effect.enable = true;
						
						effects.push_back(effect);
					}
					break;
				case kWeaponAttr_Skill_Boost:
					{
						type = attribute.type;
						
						EffectData effect;
						
						effect.duration_timer = 1.5f;
						effect.player_id = player_id;
						effect.player_item_id = info.player_item_id;
						effect.attr_slotid = slotid;
						effect.attr_sub_slotid = sub_slotid;
						effect.attr_raw = attribute;
						effect.type = kEffect_Infect_Damage;
						effect.value = attribute.value1 / 100.f;
						effect.enable = true;
						
						effects.push_back(effect);
					}
					break;
					
				default:
					break;
			}
		}
	}
	
	return type;
}

// parse cure character
void Client::ParseCureCharacter()
{
	/*
	CM_CureCharacter Message
		-to_clint id(byte)
	*/
	Client* to = ReadClient();
	if (to)
	{
		Weapon & w = GetCurWeaponInfo();
		if (w.weapon_data.weapon.type == kWeaponTypeCureGun)
		{
			// (100 / 60) * frame_time
			{
				float temp = 100.f / 60.f * w.weapon_data.cure_gun.fire_time;
				ModifyCurePower(temp);
			}

			{
				int add_num = w.weapon_data.cure_gun.add_blood;
				float value = 1;
				if (effect.SumEffect(kEffect_Infect_Cure, value))
				{
					add_num *= fmaxf(value, 0);
				}
				
				if (to->cure_list.find(uid) == to->cure_list.end())
					to->cure_list.insert(std::pair<int, int>(uid, add_num));
			}
				
			if (effect.HasEffect(kEffect_Special_UsingSkill))
			{
				ClearCurePower();
				
				EffectDataList effects;
				short type = GetCureGunSkill(GetCharacterInfo().character_id, w.base_info, effects);
				
				// 对被治疗者使用治疗枪技能
				for (EffectDataList::iterator itr = effects.begin(); itr != effects.end(); itr++)
				{
					to->GetSkillEffect().ApplyEffect(*itr);
				}
				
				float time = 1.5f;
				if (type != kWeaponAttrNone)
				{
					for (uint i = 0; i < max_client_count; i++)
					{
						Client * c = server.clients + i;

						if (c->IsReady())
						{
							c->BeginWrite();
							c->WriteByte(SM_UseCureSkill);
							c->WriteByte(to->uid);//to uid
							c->WriteByte(uid);//from uid
							c->WriteShort(type);
							c->WriteFloat(time);
							c->EndWrite();
						}
					}
				}
			}
		}
		
		OnAction();
	}
}

void Client::ParseStopBurn()
{
	Client* to = ReadClient();
	
	if (to && to->IsAlive())
		to->effect.ClearAcquiredEffect(kEffect_Sustain_HurtBurn_Replace);
}

void Client::ParseNoviceOperation()
{
	int index;
	ReadInt(index);

	switch(index)
	{
	case 4:
		{
			Recover(0,kRecoverNovice);
			AmmoRecover(kSupplyNovice,-1);
			BeginWrite();
			WriteByte(SM_NoviceOperation);
			WriteInt(index);
			EndWrite();
		}
		break;
	case 24:
		{
			server.RequestEndNovice(*this, true);
		}
		break;
	default:
		break;
	}
}

void Client::ParseDrumCheck()
{
	//check distance
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	Weapon & w = GetCurWeaponInfo();

	if (w.weapon_data.weapon.type != kWeaponTypeDrum)
		return;

	if (accumulate_damage >= w.weapon_data.gun.damage)
	{
		effect.OnClientUseSkill(kSkillSlot_LeftButton);
		
		accumulate_damage = 0;
		
		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = server.clients + i;

			if(c->team == team && c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_DrumEffect);
				c->WriteByte(uid);
				c->EndWrite();
			}
		}
		
		OnAction();

		weapon_used = true;
	}
}

void Client::ParseSpray()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	//if (spray_info.type != kBuffTypeSpray)
	//	return;

	//if (spray_info.pid == 0)
	//	return;

	//if (spray_count < 1)
	//	return;

	Vector3 pos;
	Vector3 normal;

	ReadVector3(pos);
	ReadVector3(normal);

	//spray_count = 0;
	//spray_count_used++;

	//// broadcast
	for (Client * c = server.clients; c < server.clients + max_client_count; c++)
	{
		if (c && c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerSpray);
			c->WriteByte(uid);
			c->WriteFloat(spray_index);
			//c->WriteInt(spray_info.pid);
			//c->WriteString(spray_info.path);
			//c->WriteFloat(spray_info.length);
			//c->WriteFloat(spray_info.width);
			c->WriteVector3(pos);
			c->WriteVector3(normal);
			c->EndWrite();
		}
	}

}
void Client::ParseChangeTeam()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;
		
	unsigned short index;
	byte team;

	ReadShort(index);
	ReadByte(team);

	//spray_count = 0;
	//spray_count_used++;

	//// broadcast
	for (Client * c = server.clients; c < server.clients + max_client_count; c++)
	{
		if(c->uid == uid)
			continue;
		if (c && c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_ChangeAmmoTeam);
			c->WriteByte(uid);
			c->WriteShort(index);
			c->WriteByte(team);
			c->EndWrite();
		}
	}
}

void Client::ParseUseSpawnCoin()
{
	bool issuccess = false;
	if (fu_huo_bi > 0 && can_use_spawn_coin)
	{
		if(team == 0 && state == CS_Died && num_die != 0 )
		{
			if((server.game_type == Game::kZombieMode || server.game_type == Game::kBossMode) && fu_huo_bi_use_count < 2)
			{
				if(server.round_playing)
				{
					issuccess = true;
					fu_huo_bi--;

					spawn_time = 0.1f;
					fu_huo_bi_use_count ++;
					can_use_spawn_coin = false;
				}	
			}
			else if(server.game_type == Game::kBossPVE  && fu_huo_bi_use_count < 10)
			{
				if(server.round_playing)
				{
					issuccess = true;
					fu_huo_bi--;

					spawn_time = 0.1f;
					fu_huo_bi_use_count ++;
					can_use_spawn_coin = false;
				}	
			}
			
		}
	}

	for (Client * c = server.clients; c < server.clients + max_client_count; c++)
	{
		if (c && c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_SpawnCoin);
			c->WriteByte(uid);
			c->WriteByte(issuccess ? 1 : 0);
			c->WriteInt(fu_huo_bi);
			c->WriteInt(fu_huo_bi_use_count);
			c->EndWrite();
		}
	}
}

void Client::ZombieSkill_Bomer()
{
	Weapon& weapon = GetCurWeaponInfo();

	Attribute* attr = NULL;
	for (size_t i = 0; i < weapon.base_info.attrs.size(); i++)
	{
		AttributeList &attr_list = weapon.base_info.attrs[i];
		for (size_t i = 0; i < attr_list.size(); i++)
		{
			if (attr_list[i].type == kWeaponAttr_ZombieSkill_Bomer)
			{
				attr = &attr_list[i];
				log_write(LOG_DEBUG1,"bomer skill ");
				break;
			}
		}
	}

	if(attr != NULL)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * client = server.clients + i;

			if (client->IsReady())
			{
				client->BeginWrite();
				client->WriteByte(SM_ZombieBomer);
				client->WriteByte(uid);
				client->EndWrite();

				if (client->team == 0 && Length(client->position - position) < attr->value1)
				{
					int damage_tmp = attr->value2;

					client->TakeDamage(this, &damage_tmp, kCharacterPartTorso, weapon, false, true);

					client->ZombieBomerHurt(this);
				}	
			}
		}
	}
	
}

void Client::ZombieBomerHurt(Client* owner)
{
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_ZombieBomerHurt);
			c->WriteByte(owner->uid);
			c->WriteByte(uid);
			//TakeDamage
			c->WriteByte(0);
			c->WriteInt(owner->data.GetScore());
			c->WriteInt(health);
			c->WriteInt(armor);

			if (health == 0 || (health ==1 && isghost))
			{
				WriteWeapon(*c, owner->GetCurWeaponInfo());
				Client *client = server.GetClient(assist_uid[0]);
				//sucide and have assist attacked
				if(uid == owner->uid && client && assist_uid[0] != uid)
				{
					c->WriteShort(client->num_kill);
				}
				else
				{
					c->WriteShort(owner->num_kill);
				}

				c->WriteShort(num_die);
				c->WriteByte(assist_uid[0]);

				if(client)
				{
					c->WriteInt(client->data.GetScore());
					c->WriteShort(client->assist_num);
				}
				else
				{
					c->WriteInt(0);
					c->WriteShort(short(0));
				}
			}
			c->EndWrite();
		}
	}

}

void Client::ParseStartDefuseBomb()
{
	byte s = server.bomb_defusing ? 0 : 1;

	float defuse_time_total = has_defuse_bomb_item ? server.config.bomb.weapon_data.bomb.defuse_with_item_time : server.config.bomb.weapon_data.bomb.defuse_time;

	if(!server.bomb_defusing)
	{	
		server.StartDefusing(uid, defuse_time_total);
	}

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_StartDefuseBomb);
			c->WriteByte(uid);
			c->WriteByte(s);
			c->WriteFloat(defuse_time_total);
			c->EndWrite();
		}
	}	
}

void Client::DefuseBombSuccess()
{
	BeginWrite();
	WriteByte(SM_DefuseBombSuccess);
	WriteByte(server.bomb_defusing_uid);
	EndWrite();
}

void Client::BombExploded()
{
	BeginWrite();
	WriteByte(SM_BombExploded);
	EndWrite();

	if (!IsAlive())
		return;

	if (team > 1)
		return;

	const BombInfo& bombinfo = server.config.bomb.weapon_data.bomb;
	Vector3 dv;

	dv.x = server.bomb_planted_pos.x - position.x;
	dv.y = server.bomb_planted_pos.y - position.y;
	dv.z = server.bomb_planted_pos.z - position.z;

	float dist = Length(dv);

	if (dist < bombinfo.range  && dist >= 0.f)
	{
		Weapon w;
		w.base_info.sid = 1;
		w.weapon_data.weapon.type = kWeaponTypeBomb;

		// take damage
		int damage = bombinfo.damage * ((bombinfo.range - dist)/bombinfo.range);
		if (TakeDamage(this, &damage, kCharacterPartTorso, w) && damage > 0)
		{
			// broadcast player poke
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;

				if (c->IsReady())
				{
					c->BeginWrite();
					c->WriteByte(SM_PlayerHurt);
					c->WriteByte(uid);
					c->WriteByte(0);
					c->WriteInt(data.GetScore());
					c->WriteInt(health);
					c->WriteInt(armor);

					if (health == 0 || (health ==1 && isghost))
					{
						WriteWeapon(*c, w);

						Client *client = server.GetClient(assist_uid[0]);
						//sucide and have assist attacked
						if(client && assist_uid[0] != uid)
						{
							c->WriteShort(client->num_kill);
						}
						else
						{
							c->WriteShort(this->num_kill);
						}

						c->WriteShort(num_die);
						c->WriteByte(assist_uid[0]);

						if(client)
						{
							c->WriteInt(client->data.GetScore());
							c->WriteShort(client->assist_num);
						}
						else
						{
							c->WriteInt(0);
							c->WriteShort(short(0));
						}
					}

					c->EndWrite();
				}
			}
		}
	}
}

void Client::ParseCancelDefuseBomb()
{
	if(server.bomb_defusing_uid != uid)
		return;
	if(server.bomb_defusing)
		server.ClearDefusingState();
}

void Client::ParseStartPlantBomb()
{
	if (!IsAlive())
		return;

	if (team != 1)
		return;

	if(server.bomb_owner_id != uid || server.bomb_planting || server.bomb_planted)
		return;

	bool is_in_area = server.CheckIsInBombArea(position);


	if(is_in_area)
	{
		server.bomb_planting = true;
		server.bomb_plant_timer = server.config.bomb.weapon_data.bomb.plant_time;
	}


	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_StartPlantBomb);
			c->WriteByte(uid);
			c->WriteByte(is_in_area ? 1 : 0);
			log_write(LOG_DEBUG1,"uid %d is success bomb", uid);
			c->EndWrite();
		}
	}
}

void Client::ParseCancelPlantBomb()
{
	if(server.bomb_owner_id != uid)
		return;
	server.bomb_planting = false;
	CancelPlantBomb();
}

void Client::CancelPlantBomb()
{
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_CancelPlantBomb);
			c->WriteByte(uid);
			c->EndWrite();
		}
	}
}

void Client::ParseStartSaveDying()
{
	byte dying_uid;
	ReadByte(dying_uid);

	if(zombie_mode_saving_dying_uid != 0 || zombie_mode_dying_state)
		return;

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->team == 0 && c->uid == dying_uid && c->IsReady() && c->IsAlive() && c->zombie_mode_dying_state)
		{
			if(c->zombie_mode_saver_uid != 0)
				break;


			zombie_mode_saving_dying_uid = c->uid;
			c->zombie_mode_saver_uid = uid;
			zombie_mode_save_timer = 0.f;

			StartSaveDying(dying_uid);
			break;
		}
	}
}

void Client::ParseCancelSaveDying()
{
	if(zombie_mode_saving_dying_uid == 0)
		return;
	
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->uid == zombie_mode_saving_dying_uid && c->team == 0)
		{
			CancelSaveDying(zombie_mode_saving_dying_uid);
			break;
		}
	}

}

void Client::ParseChargeSomething()
{
	byte c_uid = 0;
	byte isplayer = 0;
	Quaternion rotation;

	ReadByte(c_uid);
	ReadByte(isplayer);
	ReadQuaternion(rotation);

	if (isplayer > 0)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c && c->uid == c_uid)
			{
				KickInfo info;
				info.dir = rotation;
				info.kick_time_interval = 0.4f;
				info.kick_factor = 25.f;
				info.on_static_kick_y_offset = 2.f;
				c->OnHitBack(info);
				break;
			}
		}
	}
	else if (isplayer == 0)
	{
		charge_skill_cd = -1;
		charge_skill_alltime = -1;
		charge_skill_time = -1;
		ischarge_skill = false;

		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_ChargeSomething);
				c->WriteByte(uid);
				c->WriteByte(isplayer);
				c->EndWrite();
			}
		}
	}
}

void Client::ParseSkillKickBack()
{
	byte c_uid = 0;
	KickInfo info;
	ReadByte(c_uid);
	ReadKickInfo(*this, info);
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c && c->uid == c_uid)
		{
			c->OnHitBack(info);
			c->SkillHurt(this, 1000);
			break;
		}
	}	
}

void Client::ParsePVEAmmoOut()
{
}

void Client::ParsePVEAmmoDestroy()
{
}

void Client::ParsePVEAmmoHitHurt()
{
	Client *owner;
	Client *hit;
	float damage;
	
	owner = ReadClient();
	hit = ReadClient();
	ReadFloat(damage);
	
	if (!owner || !hit || hit != this)
		return;
		
	if (!hit->IsAlive())
		return;

	if (hit->team > 1)
		return;
	
	if (owner->CanHurt(hit, server.team_hurt))
	{
		int damage_tmp = damage;
		
		Weapon w;
		w.base_info.sid = 0;
		w.weapon_data.weapon.type = kWeaponTypeNone;
		
		hit->TakeDamage(owner, &damage_tmp, kCharacterPartTorso, w, false, false);
		
		// broadcast
		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = server.clients + i;
			
			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_PVEAmmoHitHurt);
				c->WriteByte(owner->uid);
				c->WriteByte(hit->uid);
				
				// TakePveDamage part
				c->WriteInt(owner->data.GetScore());
				c->WriteInt(hit->health);
				if (hit->health == 0 || (hit->health ==1 && hit->isghost))
				{
					c->WriteShort(hit->num_die);
				}
				c->EndWrite();
			}
		}
	}
}

void Client::ParsePVEAmmoExplodeHurt()
{
	Client *owner;
	Client *hit;
	float damage;
	
	owner = ReadClient();
	hit = ReadClient();
	ReadFloat(damage);
	
	if (!owner || !hit || hit != this)
		return;
		
	if (!hit->IsAlive())
		return;

	if (hit->team > 1)
		return;
	
	if (owner->CanHurt(hit, server.team_hurt))
	{
		int damage_tmp = damage;
		
		Weapon w;
		w.base_info.sid = 0;
		w.weapon_data.weapon.type = kWeaponTypeNone;
		
		hit->TakeDamage(owner, &damage_tmp, kCharacterPartTorso, w, false, false);
		
		// broadcast
		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = server.clients + i;
			
			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_PVEAmmoExplodeHurt);
				c->WriteByte(owner->uid);
				c->WriteByte(hit->uid);
				
				// TakePveDamage part
				c->WriteInt(owner->data.GetScore());
				c->WriteInt(hit->health);
				if (hit->health == 0 || (hit->health ==1 && hit->isghost))
				{
					c->WriteShort(hit->num_die);
				}
				c->EndWrite();
			}
		}
	}
}

void Client::ParsePVEAmmoUpdate()
{
}

void Client::ParseDummySyncCreate()
{
	DummyBaseInfo dummy;
	byte sub_type;
	ReadByte(dummy.type);
	ReadInt(dummy.buf_length);
	Read(dummy.buffer, dummy.buf_length);
	ReadByte(sub_type);
	dummy.owner_id = uid;
	dummy.id = GenerateDummyIndex();
	dummy.need_stepfather = 0;
	dummy.team = team;
	dummy.can_hurt = 0;
	dummy.sub_type = sub_type;

	server.dummy_object_map[dummy.id] = dummy;


	DummyCreateAction action;
	action.action = 1;
	action.id = dummy.id;
	action.owner_id = uid;

	server.dummy_create_vector.push_back(action);
	server.WriteLogClient("Dummy Add %d", server.dummy_create_vector.size());

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->NotifyDummyCreate(dummy);
		}
	}
}

void Client::NotifyServerDummySyncUpdate(uint dummyid, const ServerDummySyncData& dummydata)
{
	if (IsReady())
	{
		BeginWrite();
		WriteByte(SM_DummySyncUpdate);
		WriteInt(dummyid);
		WriteByte(0);
		WriteInt((int)sizeof(ServerDummySyncData));
		Write(&dummydata,(int)sizeof(ServerDummySyncData));
		EndWrite();
	}
}

void Client::NotifyDummyChangeOwner(uint dummyid, byte new_owner)
{
	if (IsReady())
	{
		BeginWrite();
		WriteByte(SM_DummyChangeOwner);
		WriteInt(dummyid);
		WriteByte(new_owner);
		EndWrite();
	}
}

void Client::NotifyDummyCreate(const DummyBaseInfo& dummy)
{
	BeginWrite();
	WriteByte(SM_DummyObjectCreate);
	WriteByte(dummy.owner_id);
	WriteInt(dummy.id);
	WriteByte(dummy.type);
	WriteByte(dummy.sub_type);
	WriteByte(dummy.need_stepfather);
	WriteByte(dummy.team);
	WriteByte(dummy.can_hurt);
	WriteInt(dummy.buf_length);
	Write(dummy.buffer, dummy.buf_length);
	EndWrite();
}

void Client::NotifyDummyDestory(uint id)
{
	BeginWrite();
	WriteByte(SM_DummyObjectDestory);
	WriteInt(id);
	EndWrite();
}

void Client::ParseDummySyncDestory()
{
	int dummy_id;
	ReadInt(dummy_id);
	std::map<uint, DummyBaseInfo>::iterator itor = server.dummy_object_map.find(dummy_id);
	if(itor != server.dummy_object_map.end())
	{
		const DummyBaseInfo& info = itor->second;
		{
			server.DestoryDummy(dummy_id);
		}
	}
}

void Client::ParseDummySyncUpdate()
{
	if (team > 1)
		return;

	byte delta;
	uint id;
	int length;
	char string_buf[SYNC_BUFFER_SIZE];
	ReadInt(id);
	ReadByte(delta);
	ReadInt(length);
	Read(string_buf,length);

	
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady()/* && c->uid != uid*/)
		{
			c->BeginWrite();
			c->WriteByte(SM_DummySyncUpdate);
			c->WriteInt(id);
			c->WriteByte(delta);
			c->WriteInt(length);
			c->Write(string_buf,length);
			c->EndWrite();
		}
	}
}

void Client::ParseGunTowerShoot()
{
	if(team > 1)
		return;
	byte personcount;
	byte part;
	float distance;
	int damage = 50;
	bool is_boost = false;
	uint dummy_id;
	
	int damage_modifier;
	float range_start;
	float range_end;
	float range_modifier;
	int base_damage;

	ReadInt(dummy_id);
	Client* hit = ReadClient();
	ReadByte(part);
	ReadFloat(distance);

	ReadInt(damage_modifier);
	ReadFloat(range_start);
	ReadFloat(range_end);
	ReadFloat(range_modifier);
	ReadInt(base_damage);

	if (hit)
	{
		if (CanHurtStrange(hit, server.team_hurt))
		{
			// calculate damage
			if (part < kCharacterPartCount)
			{
				int damage_tmp = damage;
				Weapon w;
				w.base_info.sid = 0;
				w.weapon_data.weapon.type = kWeaponTypeGunTowerBuilder;
				
				float weapon_damage = damage_modifier - (rand()%(damage_modifier * 2 + 1));

				float s = fmaxf(fminf((distance - range_start) / (range_end - range_start), 1), 0);
				float s2 = s * s;
				float s3 = s2 * s;
				float h00 =  2 * s3 - 3 * s2 + 1;
				float h01 = -2 * s3 + 3 * s2;

				float factor = 1.f * h00 + range_modifier * h01;

				weapon_damage = base_damage + weapon_damage;

				weapon_damage = weapon_damage * factor;
				int damage = weapon_damage;
							
				// take damage
				hit->TakeDamage(this, &damage, part, w, is_boost);

				// broadcast player shoot
				for (uint i = 0; i < max_client_count; i++)
				{
					Client* c = server.clients + i;

					if (c->IsReady())
					{
						c->BeginWrite();
						c->WriteByte(SM_GunTowerShoot);
						c->WriteByte(uid);
						c->WriteInt(dummy_id);
						c->WriteByte(hit->uid);
						c->WriteByte(part);
						c->WriteFloat(distance);

						c->WriteByte(hit->uid);
						
						c->WriteByte(part);
						c->WriteInt(data.GetScore());
						c->WriteInt(hit->health);
						c->WriteInt(hit->armor);

						if (hit->health == 0 || (hit->health ==1 && hit->isghost))
						{
							WriteWeapon(*c, gun_tower_hand_info);
							Client *client = server.GetClient(hit->assist_uid[0]);
							//sucide and have assist attacked
							if(hit->uid == this->uid && client && hit->assist_uid[0] != hit->uid)
							{
								c->WriteShort(client->num_kill);
							}
							else
							{
								c->WriteShort(num_kill);
							}

							c->WriteShort(hit->num_die);
							c->WriteByte(hit->assist_uid[0]);
							if (client)
							{
								c->WriteInt(client->data.GetScore());
								c->WriteShort(client->assist_num);
							}
							else
							{
								c->WriteInt(0);
								c->WriteShort(short(0));
							}

						}
						c->EndWrite();
					}
				}
			}
		}
	}
	

	
}

void Client::ParseCutHurt()
{
	if (team > 1)
		return;

	Client* hitted = ReadClient();
	ushort ammo_id;
	byte hit_part;
	
	ReadShort(ammo_id);
	ReadByte(hit_part);
	
	std::map<ushort, Weapon>::iterator itr = projected_ammo_set.find(ammo_id);
	if (itr == projected_ammo_set.end())
	{
		return;
	}

	Weapon &w = itr->second;

	if (hitted && CanHurt(hitted, server.team_hurt))
	{
		
		int damage = static_cast<int>((server.config.part_damages[kCharacterPartTorso] * GetWeaponDamage(w, 0, hitted, &(w.weapon_data.ammo.isboost), 0, false, 0)));

		if (damage == 0)
			return;

		hitted->TakeDamage(this, &damage, kCharacterPartTorso, w, w.weapon_data.ammo.isboost);

		// broadcast ExplodeAmmo hurt
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_CutHurt);
				c->WriteByte(uid);
				c->WriteByte(hitted->uid);
				c->WriteInt(w.weapon_data.weapon.type);
				c->WriteByte(byte(w.weapon_data.ammo.isboost));

				c->WriteByte(hit_part);//part
				c->WriteInt(data.GetScore());
				c->WriteInt(hitted->health);
				c->WriteInt(hitted->armor);

				if (hitted->health == 0 || (hitted->health ==1 && hitted->isghost))
				{
					WriteWeapon(*c, w);

					Client *client = server.GetClient(hitted->assist_uid[0]);

					//sucide and have assist attacked
					if(hitted->uid == this->uid && client && hitted->assist_uid[0] != this->uid)
					{
						c->WriteShort(client->num_kill);
					}
					else
					{
						c->WriteShort(this->num_kill);
					}


					c->WriteShort(hitted->num_die);
					c->WriteByte(hitted->assist_uid[0]);

					if(client)
					{
						c->WriteInt(client->data.GetScore());
						c->WriteShort(client->assist_num);
					}
					else
					{
						c->WriteInt(0);
						c->WriteShort(short(0));
					}
				}
				c->EndWrite();
			}
		}
		
	}
}

void Client::StartSaveDying(byte dying_uid)
{
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Zombie_Mode_StartSaveDying);
			c->WriteByte(uid);
			c->WriteByte(dying_uid);
			c->WriteFloat(ZOMBIE_MODE_SAVE_DYING_TIME);
			c->EndWrite();
		}
	}
}

void Client::CancelSaveDying(byte dying_uid)
{
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Zombie_Mode_CancelSaveDying);
			c->WriteByte(uid);
			c->WriteByte(dying_uid);
			c->EndWrite();
		}
	}


	zombie_mode_saving_dying_uid = 0;
	zombie_mode_save_timer = 0.f;

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady() && c->uid == dying_uid)
		{
			c->zombie_mode_saver_uid = 0;
			break;
		}
	}
}

void Client::ZombieModeRebirth(byte saver_uid)
{

	SingleCharacterInfo& current_character = GetCurCharinfo();

	float health_scale = GetScale(server.level_info.health_scale);
	max_health_base = max_health_unscale * health_scale;
	ex_health_base = ex_health_unscale * health_scale;

	ResetAllCure();

	float value = 1;
	effect.SumEffect(kEffect_Infect_MaxHp, value);

	max_health = Max((int)(max_health_base * value), 1);
	ex_health = Max((int)(ex_health_base * value), 1);


	{
		EffectData effectdata;

		effectdata.duration_timer = 0.1f;
		effectdata.type = kEffect_Sustain_HurtBloodshed;
		effectdata.value = 10;
		effectdata.interval = 1.f;
		effectdata.enable = true;

		effect.ApplySystemEffect(effectdata);
	}

	float per = 0.5f - 0.1f * float(human_rebirth_counter);
	per = per > 0.1f ? per : 0.1f;
	human_rebirth_counter++;

	health = max_health * per;

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Zombie_Mode_Human_Respawn);
			c->WriteByte(uid);
			c->WriteByte(saver_uid);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->EndWrite();
		}
	}
}

void Client::ClearZombieSavingState()
{
	if(zombie_mode_dying_state && zombie_mode_saver_uid)
	{

		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = server.clients + i;

			if (c->IsReady() && zombie_mode_saver_uid == c->uid)
			{
				c->CancelSaveDying(uid);
				break;
			}
		}
	}

	if(zombie_mode_saving_dying_uid)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = server.clients + i;

			if (c->IsReady() && c->IsAlive() && c->zombie_mode_dying_state)
			{
				CancelSaveDying(uid);
				break;
			}
		}
	}
}

void Client::HumanEnergyChange()
{
	BeginWrite();
	WriteByte(SM_HumanEnergyChange);
	WriteByte(uid);
	WriteInt(human_energy);
	EndWrite();
}

void Client::HumanPowerUp()
{
	{
		EffectData effectdata;

		effectdata.duration_timer = 10.0f;
		effectdata.type = kEffect_Infect_Damage;
		effectdata.value = 1.0f;
		effectdata.enable = true;

		effect.ApplySystemEffect(effectdata);
	}
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_HumanPowerUp);
			c->WriteByte(uid);
			c->EndWrite();
		}
	}
}

void Client::Boss2Cleanup()
{
	boss2_human_energy = 0;
	boss2_human_energy_level = 1;
	boss2_human_energy_max = server.config.boss2_human_energy_max_init;
	
	boss2_passiveskill_level[0] = 0;
	boss2_passiveskill_level[1] = 0;
	boss2_passiveskill_level[2] = 0;
	boss2_passiveskill_level[3] = 0;
	
	boss2_initiative_type = 0;
	
	boss2_strange_spawn_use = false;
	boss2_strange_spawn = -1;
}

void Client::Boss2DoPassiveSkill()
{
	if (server.game_type == Game::kBossMode2)
	{
		if (boss2_passiveskill_level[0] > 0)
		{
			EffectData effectdata;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_MoveSpeed;
			effectdata.value = 0.15f * boss2_passiveskill_level[0];
			effectdata.enable = true;

			effect.ApplySystemItemEffect(effectdata, BOSS2_ITEMID_PASSIVESKILL);
		}
		
		if (boss2_passiveskill_level[1] > 0)
		{
			EffectData effectdata;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_JumpHeight;
			effectdata.value = 0.25f * boss2_passiveskill_level[1];
			effectdata.enable = true;

			effect.ApplySystemItemEffect(effectdata, BOSS2_ITEMID_PASSIVESKILL);
		}
		
		if (boss2_passiveskill_level[2] > 0)
		{
			EffectData effectdata;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_ResistanceAll;
			effectdata.value = 0.05f * boss2_passiveskill_level[2];
			effectdata.enable = true;

			effect.ApplySystemItemEffect(effectdata, BOSS2_ITEMID_PASSIVESKILL);
		}
		
		if (boss2_passiveskill_level[3] > 0)
		{
			EffectData effectdata;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_Damage;
			effectdata.value = 0.1f * boss2_passiveskill_level[3];
			effectdata.enable = true;

			effect.ApplySystemItemEffect(effectdata, BOSS2_ITEMID_PASSIVESKILL);
		}
	}
}

void Client::Boss2DoDefenceUp()
{
	if (server.game_type == Game::kBossMode2 && 
		boss2_defence_energy_level > 0 && team == 1)
	{
		EffectData effectdata;

		effectdata.duration_timer = DURATION_INFINITY;
		effectdata.type = kEffect_Infect_ResistanceAll;
		effectdata.value = 0.2f * boss2_defence_energy_level;
		effectdata.enable = true;

		effect.ApplySystemItemEffect(effectdata, BOSS2_ITEMID_OTHER);
	}
}

void Client::Boss2SyncData()
{
	if (server.game_type == Game::kBossMode2)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_Boss2_SyncData);
				c->WriteByte(uid);
				c->WriteFloat(boss2_human_energy);
				c->WriteFloat(boss2_human_energy_max);
				c->WriteInt(boss2_human_energy_level);
				c->WriteIntArray(boss2_passiveskill_level, 4);
				c->WriteInt(boss2_initiative_type);
				c->WriteInt(boss2_strange_spawn);
				c->WriteFloat(boss2_defence_energy);
				c->WriteFloat(boss2_defence_energy_max);
				c->WriteInt(boss2_defence_energy_level);
				c->EndWrite();
			}
		}
	}
}

void Client::ItemModeSyncData()
{
	if (server.game_type == Game::kItemMode)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_ItemMode_SyncData);
				c->WriteByte(uid);
				c->WriteFloat(itemmode_energy);
				c->WriteFloat(itemmode_energy_max);
				c->WriteInt(itemmode_item_slot);
				//c->WriteByte((byte)itemmode_zibao);
				c->EndWrite();
			}
		}
	}
}

float Client::GetCurePower()
{
	return m_cure_power;
}

void Client::ModifyCurePower(float power)
{
	float old_cure_power = m_cure_power;
	
	float value = 1;
	if (effect.SumEffect(kEffect_Infect_CureEnergy, value))
	{
		power *= fmaxf(value, 0.f);
	}
	
	m_cure_power += power;
	
	m_cure_power = Min(m_cure_power, 100.f);
	
	if (old_cure_power != m_cure_power)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = server.clients + i;
			if(c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_Cure_Power);
				c->WriteByte(uid);
				c->WriteFloat(GetCurePower());
				c->WriteInt(data.GetScore());
				c->EndWrite();
			}
		}
	}
}

void Client::ClearCurePower()
{
	float old_cure_power = m_cure_power;
	
	m_cure_power = 0;
	
	if (old_cure_power != m_cure_power)
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = server.clients + i;
			if(c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_Cure_Power);
				c->WriteByte(uid);
				c->WriteFloat(GetCurePower());
				c->WriteInt(data.GetScore());
				c->EndWrite();
			}
		}
	}
}

void Client::ZombieDyingState(byte from_id)
{
	zombie_mode_dying_state = true;

	if (team > 1)
		return;
		
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;
		if (c->IsReady() && c->team == 1)
		{
			float score_killer = (from_id == c->uid) ? server.config.knock_down_human_killer_score : server.config.knock_down_human_score;
			if (server.cServerType == (byte)SvrType_Match)
			{
			}
			else
			{
				c->data.ScoreDataAdd(score_killer, true);
			}
		}

	}

	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;

	armor = 0;
	max_armor = 0;

	input_limit_timer = INPUT_LIMIT_TIME;
	drinkrecover = 0;
	drinkrecovercount = -1;
	SetCurCharInfo(target_career);

	max_health_base = 2500;
	ex_health_base = 2500;
	max_health = 2500;

	ResetAllCure();

	health = max_health;
	
	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;

	{
		EffectData effectdata;

		effectdata.duration_timer = DURATION_INFINITY;
		effectdata.type = kEffect_Sustain_HurtBloodshed;
		effectdata.value = 10;
		effectdata.interval = 1.f;
		effectdata.enable = true;

		effect.ApplySystemEffect(effectdata);
	}

	// broadcast player spawn
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Zombie_Mode_PlayerDying);
			c->WriteByte(uid);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteInt(armor);
			c->WriteByte(from_id);
			c->EndWrite();

			// 修复SM_PlayerSpawn与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
		}
	}
}

void Client::CommonZombieUnableState()
{
	if (team > 1)
		return;
	unable_time = 20.0f;
	zombie_unable_state = true;

	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;

	max_armor = 0;
	max_health_base = 2000;
	ex_health_base = 2000;
	max_health = 2000;
	ResetAllCure();

	health = 2000;

	{
		EffectData effectdata;
		EffectData effectdata2;
		EffectData effectdata3;

		effectdata.duration_timer = DURATION_INFINITY;
		effectdata.type = kEffect_Sustain_HpRecover2;
		effectdata.value = 0;
		effectdata.interval = 1.f;
		effectdata.enable = false;

		effect.ApplySystemEffect(effectdata);

		effectdata2.duration_timer = DURATION_INFINITY;
		effectdata2.type = kEffect_Infect_MoveSpeed;
		effectdata2.value = (1 - GetCurCharinfo().run_speed) / GetCurCharinfo().run_speed;
		effectdata2.enable = true;

		effect.ApplySystemEffect(effectdata2);

		effectdata3.duration_timer = DURATION_INFINITY;
		effectdata3.type = kEffect_Infect_JumpHeight;
		effectdata3.value = -1.0f;
		effectdata3.enable = true;

		effect.ApplySystemEffect(effectdata3);
	}

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_CommonZombie_Unable);
			c->WriteByte(uid);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteFloat(unable_time);
			c->EndWrite();

			// 修复SM_PlayerSpawn与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
		}
	}
}

void Client::ApplyHackFix()
{
	CharacterInfo& characterinfo = m_character_info;
	
	for (SingleCharacterInfoMap::iterator itr = characterinfo.singlecharacter_set.begin(); 
		itr != characterinfo.singlecharacter_set.end(); itr++)
	{	
		SingleCharacterInfo& singlecharacterinfo = itr->second;
		
		if (server.game_type == kCommonZombieMode)
		{
			if (singlecharacterinfo.career_id == 2)
			{
				PackInfo& pack = *singlecharacterinfo.packs.begin();
				if (pack.weapon_set.size() > 0)
				{
					Weapon& weapon = pack.weapon_set.begin()->second;

					if (weapon.weapon_data.weapon.type == kWeaponTypeMiniMachineGun)
					{
						Attribute attribute;

						attribute.type = kWeaponAttr_Target_SlowDown;
						attribute.value1 = 10;
						attribute.time = 2.0f;

						AttributeList attributelist;
						attributelist.push_back(attribute);

						weapon.base_info.attrs.push_back(attributelist);
					}
				}
			}
			if (singlecharacterinfo.career_id == 6)
			{
				PackInfo& pack = *singlecharacterinfo.packs.begin();
				if (pack.weapon_set.size() > 0)
				{
					Weapon& weapon = pack.weapon_set.begin()->second;

					if (weapon.weapon_data.weapon.type == kWeaponTypeMeditNeedleGun)
					{
						Attribute attribute;

						attribute.type = kWeaponAttr_Target_SlowDown;
						attribute.value1 = 100;
						attribute.time = 2.0f;

						AttributeList attributelist;
						attributelist.push_back(attribute);

						weapon.base_info.attrs.push_back(attributelist);
					}
				}
			}
		}
		// buff
		if (singlecharacterinfo.career_id == 2)
		{
			for (std::vector<BuffItem>::const_iterator itr = characterinfo.item_set.begin(); 
				itr != characterinfo.item_set.end(); itr++)
			{
				const BuffItem &buffitem = *itr;
				
				if (buffitem.id == kBuffIdRoom)
				{
					switch (buffitem.type)
					{
					case kBuffTypeTeamCareerId2:
						if (singlecharacterinfo.packs.size() > 0)
						{
							PackInfo& pack = *singlecharacterinfo.packs.begin();
							if (pack.weapon_set.size() > 0)
							{
								Weapon& weapon = pack.weapon_set.begin()->second;
								
								if (weapon.weapon_data.weapon.type == kWeaponTypeMiniMachineGun)
								{
									Attribute attribute;
									
									attribute.type = kWeaponAttr_Self_PrepareFireTimeInfect;
									attribute.value1 = buffitem.value;
									
									AttributeList attributelist;
									attributelist.push_back(attribute);
									
									weapon.base_info.attrs.push_back(attributelist);
								}
							}
						}
						break;
						
					default:
						break;
					}
				}
			}
		}
		
		for (std::vector<PackInfo>::iterator itr = singlecharacterinfo.packs.begin();
			itr != singlecharacterinfo.packs.end(); itr++)
		{
			PackInfo& pack = *itr;
			for (std::map<byte, Weapon>::iterator itr = pack.weapon_set.begin(); 
				itr != pack.weapon_set.end(); itr++)
			{
				Weapon& weapon = itr->second;
				
				// 为了兼容现存的战鼓
				if (weapon.weapon_data.weapon.type == kWeaponTypeDrum)
				{
					for (std::vector<AttributeList>::iterator itr = weapon.base_info.attrs.begin(); 
						itr != weapon.base_info.attrs.end(); itr++)
					{
						AttributeList &attributelist = *itr;
						for (AttributeList::iterator itr = attributelist.begin(); 
							itr != attributelist.end(); itr++)
						{
							Attribute& attribute = *itr;
							if (attribute.type == kWeaponAttr_Benefit_DamageAdd || 
								attribute.type == kWeaponAttr_Benefit_DefenceAdd)
							{
								attribute.value2 = weapon.weapon_data.drum.range * 10.f;
							}
						}
					}
				}
			}
		}
	}
}

void Client::PlantBombSuccess(float t)
{
	//check distance
	if (!IsAlive())
		return;

	if (team != 1)
		return;

	if(server.bomb_owner_id != uid || server.bomb_planted)
		return;
	



	// broadcast player shoot
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;


		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlantBombSuccess);
			c->WriteByte(uid);
			c->WriteVector3(server.bomb_planted_pos);
			c->WriteFloat(t);
			c->EndWrite();
		}
	}
}

void Client::ParseDrink()
{
	//check distance
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	Weapon & w = GetCurWeaponInfo();

	if (w.weapon_data.weapon.type != kWeaponTypeMilkbottle)
		return;

	if (w.weapon_data.milkbottle.damage > 0 )
	{
		if (w.weapon_data.milkbottle.fire_time > 0)
		{
			drinkrecovercount = (int)w.weapon_data.milkbottle.fire_time / SELFRECOVER_INTERVAL;
			drinkrecover =  w.weapon_data.milkbottle.damage / drinkrecovercount;
		}
		else
		{
			drinkrecovercount = 1;
			drinkrecover = w.weapon_data.milkbottle.damage;
		}
	}
	
	// broadcast player shoot
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;
		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Drink);
			c->WriteByte(uid);
			c->EndWrite();
		}
	}

	OnAction();

	weapon_used = true;
}

void Client::ParseNoviceOperation(int index)
{
	BeginWrite();
	WriteByte(SM_NoviceOperation);
	WriteInt(index);
	EndWrite();
}

void Client::ParseItemMode_ZiBao()
{
	// if (!IsAlive())
		// return;

	if (team > 1)
		return;
		
	if (itemmode_zibao == false)
		return;
		
	uint uids_size;
	std::vector<byte> uids;
	
	ReadInt(uids_size);
	uids.reserve(uids_size);
	for (uint i = 0; i < uids_size; i++)
	{
		byte v;
		
		ReadByte(v);
		
		uids.push_back(v);
	}
	
	for (int i = 0; i < uids.size(); i++)
	{
		Client * c = server.GetClient(uids[i]);
		if (c->IsReady() && CanHurt(c, false))
		{
			c->SkillHurt(this, 1000);
		}
	}
	
	if (CanHurt(this, true))
		Suicide();
	
	// broadcast 
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;
		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_ItemMode_ZiBao);
			c->WriteByte(uid);
			c->EndWrite();
		}
	}
	
	itemmode_zibao = false;
	
	OnAction();
}
void Client::ParseSaveMap()
{
	//prepare
	MapInfo oMapInfo;
	oMapInfo.character_id =GetCharacterInfo().character_id;

	//read
	int iCnt =0;
	ReadInt(iCnt);
	oMapInfo.m_aItemInfo.reserve(iCnt);

	MapItemInfo oItemInfo;
	for(int i=0; i<iCnt; ++i)
	{
		//ReadInt(oItemInfo.DummyObjectType);
		ReadInt(oItemInfo.SystemId);
		//ReadString(oItemInfo.ResKey, sizeof(oItemInfo.ResKey));
		ReadFloatArray(oItemInfo.Position, 3);
		ReadFloatArray(oItemInfo.Rotation, 4);
		oMapInfo.m_aItemInfo.push_back(oItemInfo);
	}

	//request
	server.RequestSaveMap(oMapInfo);
}

void Client::ParseProjectedAmmoUpdate()
{
	if (team > 1)
		return;

	/*
	CM_ProjectedAmmoUpdate Message
		-delta(Int8)
		-ProjectedAmmo num(Int16)
		-ProjectedAmmo id(Int16)
		-ProjectedAmmo postion(Vector3)
		-ProjectedAmmo direction(Vector3)
	*/

	byte delta;
	ushort ammo_num;

	ushort ammo_id;
	Vector3 pos;
	Vector3 dir;
	byte is_impact;
	byte hit_character_uid;

	ReadByte(delta);
	ReadShort(ammo_num);

	/*
	SM_ProjectedAmmoUpdate Message
		-client id(byte)
		-delta(Int8)
		-ProjectedAmmo num(Int16)
		-ProjectedAmmo id(Int16)
		-ProjectedAmmo postion(Vector3)
		-ProjectedAmmo direction(Vector3)
		.....
	*/
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady() && c->uid != uid)
		{
			c->BeginWrite();
			c->WriteByte(SM_ProjectedAmmoUpdate);
			c->WriteByte(delta);
			c->WriteByte(uid);
			c->WriteShort(ammo_num);
		}
	}

	for (ushort i = 0; i < ammo_num; i++)
	{
		ReadShort(ammo_id);
		ReadVector3FP(pos);
		ReadVector3FP(dir);
		ReadByte(is_impact);
		ReadByte(hit_character_uid);

		// broadcast player ProjectedAmmoUpdate
		for (uint j = 0; j < max_client_count; j++)
		{
			Client * c = server.clients + j;

			if (c->IsReady() && c->uid != uid)
			{
				c->WriteShort(ammo_id);
				c->WriteVector3FP(pos);
				c->WriteVector3FP(dir);
				c->WriteByte(is_impact);
				c->WriteByte(hit_character_uid);
			}
		}
	}

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady() && c->uid != uid)
		{
			c->EndWrite();
		}
	}
}

void Client::ParseProjectedAmmoHurt()
{
	/*
	CM_ProjectedAmmoHurt Message
		-hitted id(byte)
		-Ammo id(byte)
		-distance(float)
		-rangeratio(float)
		-hit pos(Vector3)
		-hit part(byte)
		-team hurt(byte)
	*/
	if (team > 1)
		return;

	Client* hitted = ReadClient();
	ushort ammo_id;
	float dist;
	float range_ratio;
	Vector3 pos;
	byte teamhurt;
	byte hit_part;
	float rate;
	
	ReadShort(ammo_id);
	ReadFloat(dist);
	ReadFloat(range_ratio);
	ReadVector3(pos);
	ReadByte(hit_part);
	ReadByte(teamhurt);
	ReadFloat(rate);

	if (rate > 4.0f)
		rate = 4.0f;
	
	std::map<ushort, Weapon>::iterator itr = projected_ammo_set.find(ammo_id);
	if (itr == projected_ammo_set.end())
	{
		return;
	}

	Weapon &w = itr->second;

	if (hitted && CanHurt(hitted, teamhurt))
	{
		if (dist >= 0.f)
		{
			int damage = static_cast<int>((server.config.part_damages[hit_part] * GetWeaponDamage(w, dist, hitted, &(w.weapon_data.ammo.isboost), 0, false, range_ratio)) * rate);
			if (hitted->uid == uid && !teamhurt)
			{
				damage = damage / 2;
			}
			
			if (damage == 0)
				return;

			hitted->TakeDamage(this, &damage, hit_part, w, w.weapon_data.ammo.isboost);
			
			float jumpscale = range_ratio - w.weapon_data.ammo.dmg_modify_max;
			bool isAmmoJump = false;
			
			float value1 = 0;
			float value2 = 0;
			if (effect.SumEffect(kEffect_Special_HitTargetJump, value1))
			{
				isAmmoJump = true;
				
				DEBUGLOG_WRITE("ParseProjectedAmmoHurt() name : %s, jumpscale : %f, value1 : %f", 
								GetCharacterInfo().character_name, jumpscale, value1);
			}
			if (hitted->effect.SumEffect(kEffect_Special_HittedSelfJump, value2))
			{
				isAmmoJump = true;
				
				DEBUGLOG_WRITE("ParseProjectedAmmoHurt() name : %s, jumpscale : %f, value2 : %f", 
								GetCharacterInfo().character_name, jumpscale, value2);
			}
			jumpscale *= value1 + value2;

			//弩不应该有火箭跳效果 FIX
			if (w.weapon_data.weapon.type == kWeaponTypeAmmoMeditNeedle)
			{
				isAmmoJump = false;
			}

			// broadcast ExplodeAmmo hurt
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;

				if (c->IsReady())
				{
					/*
					SM_ProjectedAmmoHurt Message
						-owner id(byte)
						-client id(byte)
						-hit pos(Vector3)
						-ammo type(int)
						-hit part(byte)
						-isboost(byte)
					*/
					c->BeginWrite();
					c->WriteByte(SM_ProjectedAmmoHurt);
					c->WriteByte(uid);
					c->WriteByte(hitted->uid);
					c->WriteVector3(pos);
					c->WriteInt(w.weapon_data.weapon.type);
					c->WriteByte(hit_part);
					c->WriteByte(byte(w.weapon_data.ammo.isboost));
					
					//TakeDamage Part
					c->WriteByte(hit_part);//part
					c->WriteInt(data.GetScore());
					c->WriteInt(hitted->health);
					c->WriteInt(hitted->armor);

					if (hitted->health == 0 || (hitted->health ==1 && hitted->isghost))
					{
						//log_write(LOG_DEBUG4,"----------num_kill %d, hitted->num_die %d------------", (int)num_kill, (int)hitted->num_die);
						WriteWeapon(*c, w);

						Client *client = server.GetClient(hitted->assist_uid[0]);

						//sucide and have assist attacked
						if(hitted->uid == this->uid && client && hitted->assist_uid[0] != this->uid)
						{
							c->WriteShort(client->num_kill);
						}
						else
						{
							c->WriteShort(this->num_kill);
						}


						c->WriteShort(hitted->num_die);
						c->WriteByte(hitted->assist_uid[0]);

						if(client)
						{
							c->WriteInt(client->data.GetScore());
							c->WriteShort(client->assist_num);
						}
						else
						{
							c->WriteInt(0);
							c->WriteShort(short(0));
						}
					}
					c->WriteByte(byte(isAmmoJump));
					c->WriteFloat(jumpscale);
					c->EndWrite();
				}
			}
		}
	}
}

void Client::ParseDummyProjectedAmmoHurt()
{
	/*
	CM_ProjectedAmmoHurt Message
		-hitted id(byte)
		-Ammo id(byte)
		-distance(float)
		-rangeratio(float)
		-hit pos(Vector3)
		-hit part(byte)
		-team hurt(byte)
	*/
	if (team > 1)
		return;

	DummyBaseInfo* hit_dummy = ReadDummyBaseInfo();
	ushort ammo_id;
	float dist;
	float range_ratio;
	Vector3 pos;
	byte teamhurt;
	float rate;
	
	ReadShort(ammo_id);
	ReadFloat(dist);
	ReadFloat(range_ratio);
	ReadVector3(pos);
	ReadByte(teamhurt);
	ReadFloat(rate);

	if (rate > 4.0f)
		rate = 4.0f;
	
	std::map<ushort, Weapon>::iterator itr = projected_ammo_set.find(ammo_id);
	if (itr == projected_ammo_set.end())
	{
		return;
	}

	Weapon &w = itr->second;


	if (dist >= 0.f && hit_dummy)
	{
		Client* hit_owner = server.GetClient(hit_dummy->owner_id);

		if(CanHurtDummy(hit_dummy->owner_id, server.team_hurt))
		{
			int damage = static_cast<int>((GetWeaponDamage(w, dist, hit_owner, &(w.weapon_data.ammo.isboost), 0, false, range_ratio)) * rate);

			if (damage == 0)
				return;

			DummyTakeDamage(damage, w, hit_dummy, w.weapon_data.ammo.isboost);		
		}
	}

}


void Client::ParseProjectedProdHurt()
{
	if (team > 1)
		return;

	Client* hitted = ReadClient();
	ushort ammo_id;
	byte teamhurt;
	byte hit_part;

	ReadShort(ammo_id);
	ReadByte(teamhurt);
	ReadByte(hit_part);

	std::map<ushort, Weapon>::iterator itr = projected_ammo_set.find(ammo_id);
	if (itr == projected_ammo_set.end())
	{
		return;
	}

	Weapon &w = itr->second;

	if (hitted && CanHurt(hitted, teamhurt))
	{
		int a, b, c, d;
		GetCharacterItemLevel(GetCurCharinfo(), a, b, c, d);
		int damage = powf(1.071f, a) * PRODDAMAGE;
		if (hitted->uid == uid && !teamhurt)
		{
			damage = damage / 2;
		}

		if (damage == 0)
			return;

		hitted->TakeDamage(this, &damage, hit_part, w);
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_ProjectedProdHurt);
				c->WriteByte(uid);
				c->WriteByte(hitted->uid);
				c->WriteInt(w.weapon_data.weapon.type);
				c->WriteByte(hit_part);
				c->WriteByte(byte(w.weapon_data.ammo.isboost));
				c->WriteByte(hit_part);//part
				c->WriteInt(data.GetScore());
				c->WriteInt(hitted->health);
				c->WriteInt(hitted->armor);

				if (hitted->health == 0 || (hitted->health ==1 && hitted->isghost))
				{
					WriteWeapon(*c, w);

					Client *client = server.GetClient(hitted->assist_uid[0]);

					//sucide and have assist attacked
					if(hitted->uid == this->uid && client && hitted->assist_uid[0] != this->uid)
					{
						c->WriteShort(client->num_kill);
					}
					else
					{
						c->WriteShort(this->num_kill);
					}


					c->WriteShort(hitted->num_die);
					c->WriteByte(hitted->assist_uid[0]);

					if(client)
					{
						c->WriteInt(client->data.GetScore());
						c->WriteShort(client->assist_num);
					}
					else
					{
						c->WriteInt(0);
						c->WriteShort(short(0));
					}
				}
				c->EndWrite();
			}
		}
	}
}

void Client::ParseDummyProjectedProdHurt()
{
	if (team > 1)
		return;

	DummyBaseInfo* hit_dummy = ReadDummyBaseInfo();

	ushort ammo_id;
	byte teamhurt;

	ReadShort(ammo_id);
	ReadByte(teamhurt);

	std::map<ushort, Weapon>::iterator itr = projected_ammo_set.find(ammo_id);
	if (itr == projected_ammo_set.end())
	{
		return;
	}

	Weapon &w = itr->second;


	Client* hit_owner = server.GetClient(hit_dummy->owner_id);

	if(CanHurtDummy(hit_dummy->owner_id, server.team_hurt))
	{
		int damage = static_cast<int>(GetWeaponDamage(w, 0, hit_owner, &(w.weapon_data.ammo.isboost), 0, false));

		if (damage == 0)
			return;

		DummyTakeDamage(damage, w, hit_dummy, w.weapon_data.ammo.isboost);		
	}
}

void Client::ParseCharacterHeal()
{
	int heal;
	int ammo;
	Client* c = ReadClient();
	ReadInt(heal);
	ReadInt(ammo);
	if(heal > 0)
	{
		c->Recover(heal,kRecoverSelf);
	}
	if(ammo > 0)
	{
		c->AmmoRecover(kSupplyDropWeapon, ammo);
	}
}

void Client::ParseTeleport()
{
	Vector3 position;
	Quaternion rotation;
	
	ReadVector3(position);
	ReadQuaternion(rotation);
	
	{
		EffectData effectdata;

		effectdata.duration_timer = 0.5f;
		effectdata.type = kEffect_Infect_FOV;
		effectdata.value = -20.f;
		effectdata.enable = true;

		effect.ApplySystemEffect(effectdata);
	}
	
	// broadcast player animation start
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Teleport);
			c->WriteByte(uid);
			c->WriteVector3(position);
			c->WriteQuaternion(rotation);
			c->EndWrite();
		}
	}

	OnAction();
}

void Client::ParseForceSpawn()
{
	Transform transform;
	transform.position = position;
	transform.rotation = rotation.GetZXY().y * RAD2DEG;
	Spawn(transform, NULL, false, false);
	OnAction();
}

void Client::ParseMoonForceSpawn()
{
	Transform transform;
	int point_size[2];
	point_size[0] = server.start_point[0].size();
	point_size[1] = server.start_point[1].size();
	transform = server.start_point[this->team][(this->uid & 0x0f) % point_size[this->team]];
	Spawn(transform, NULL, false, false);

	OnAction();
}

void Client::ParseUseItem()
{
	uint sid = 0;
	ushort count = 0;
	ReadInt(sid);
	ReadShort(count);
	UseItem(sid, count);
}


void Client::ParsePlayerAnimationStart()
{
	int weapontype;
	int state;
	ReadInt(weapontype);
	ReadInt(state);
	// broadcast player animation start
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerAnimationStart);
			c->WriteByte(uid);
			c->WriteInt(weapontype);
			c->WriteInt(state);
			c->EndWrite();
		}
	}

	OnAction();
}

void Client::ParsePlayerAnimationEnd()
{
	int weapontype;
	ReadInt(weapontype);
	// broadcast player animation end
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerAnimationEnd);
			c->WriteByte(uid);
			c->WriteInt(weapontype);
			c->EndWrite();
		}
	}

	OnAction();
}

void Client::ParseCallDoctor()
{
	// broadcast player call doctor
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady() && c->team == team)
		{
			c->BeginWrite();
			c->WriteByte(SM_CallDoctor);
			c->WriteByte(uid);
			c->EndWrite();
		}
	}
}

// on position changed
void Client::OnPositionChanged()
{
	// check height of level to die
	if (position.y < server.level_info.dead_height && CanHurt(this, true))
	{
		Weapon w;
		w.base_info.sid = 1;
		w.weapon_data.weapon.type = kWeaponTypeSelfHurt;

		// take damage
		int damage = health;
		if (TakeDamage(this, &damage, kCharacterPartTorso, w) && damage > 0)
		{
			// broadcast player poke
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;

				if (c->IsReady())
				{
					c->BeginWrite();
					c->WriteByte(SM_PlayerHurt);
					c->WriteByte(uid);
					c->WriteByte(0);
					c->WriteInt(data.GetScore());
					c->WriteInt(health);
					c->WriteInt(armor);

					if (health == 0 || (health ==1 && isghost))
					{
						c->WriteInt(0);
						Client *client = server.GetClient(assist_uid[0]);

						//sucide and have assist attacked
						if( client && assist_uid[0] != uid)
						{
							c->WriteShort(client->num_kill);
						}
						else
						{
							c->WriteShort(this->num_kill);
						}
						c->WriteShort(num_die);
						c->WriteByte(assist_uid[0]);

						if(client)
						{
							c->WriteInt(client->data.GetScore());
							c->WriteShort(client->assist_num);
						}
						else
						{
							c->WriteInt(0);
							c->WriteShort(short(0));
						}
					}

					c->EndWrite();
				}
			}
		}
	}

	// on position changed
	float len = Length(position - position_last);
	if (len > 0.01f)
	{
		if (ignore_checkcheat)
		{
			ignore_checkcheat = false;
		}
		else
		{
			float len_xz = LengthXZOnly(position - position_last);
			float len_check = checkcheat_delta * checkcheat_movespeed * appcfg.checkcheat_movespeed_multiple;
			
			if (len_xz > len_check)
			{
				checkcheat_num++;
				
				DEBUGLOG_WRITE("OnPositionChanged(cheat) name : %s, checkcheat_num : %d, len_xz : %f, len_check : %f", 
								GetCharacterInfo().character_name, checkcheat_num, len_xz, len_check);
			}
		}
		checkcheat_delta = 0.f;
		
		position_last = position;
		
		OnAction();
	}
}

// on rotation changed
void Client::OnRotationChanged(const Quaternion & old_rot, const Quaternion & new_rot)
{
	//OnAction();
}

// on kill
void Client::OnKill(Client * target, int & part, const Weapon & weapon, byte assistuid, bool boost, bool sustainhurt)
{
	// skilleffect
	if (target)
	{
		effect.OnClientKill(*target, weapon.base_info);
	}
	
	// calculate score.
	if (target && team != target->team)
	{
		// weapon kill
		if (target != this || weapon.weapon_data.weapon.type != kWeaponTypeSelfHurt)
		{
			int num = 0;
			if (control_person_id == target->id)
			{
				control_person_old_id = control_person_id;
				control_person_id = -1;
				target->reveng_person_id.push_back(this->id);
				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = server.clients + i;
					if (c && c->IsReady())
					{
						c->BeginWrite();
						c->WriteByte(SM_RevengPerson);
						c->WriteByte(this->uid);
						c->WriteByte(target->uid);
						c->EndWrite();
					}
				}
				num |= (1 << kScoreControlKill);
				control_num++;
			}
			if (this->reveng_person_id.size() > 0)
			{
				std::vector<int>::iterator itr;
				for(itr = this->reveng_person_id.begin(); itr != this->reveng_person_id.end();++itr)
				{
					if (*itr == target->id)
					{
						this->reveng_person_id.erase(itr);
						break;
					}
				}
				num |= (1 << kScoreRevengeKill);
				revenge_num++;
			}
			if (boost)
				num |= (1 << kScoreBoostKill);
			if (sustainhurt)
				num |= (1 << kScoreSustainKill);
			if(weapon.weapon_data.weapon.hit_crit_head == 0 && part == kCharacterPartHead)
			{
				part = kCharacterPartTorso;
			}
			
			data.ScoreDataKill(GetCurCharinfo(), target->GetCurCharinfo(), weapon.weapon_data.weapon.type, part, level - target->level, num);
		}

		if (!server.first_kill)
			server.first_kill = GetCharacterInfo().character_id;
	}

	if (target && assistuid != 0)
	{
		log_write(LOG_DEBUG5,"YYYYYYYYYY %d", assistuid);
		Client *assclient = server.GetClient(assistuid);
		if(assclient)
		{
			assclient->data.ScoreDataKillByAssist(assclient->GetCurCharinfo(), target->GetCurCharinfo(),
													weapon.weapon_data.weapon.type, assclient->level - target->level);
			assclient->assist_num++;
		}
	}

	if (weapon.weapon_data.weapon.type == kWeaponTypeKnife)
		knife_kill++;

	if (team != target->team && team < 2)
	{
		num_kill++;
		server.team_kills[team]++;

		die_counter =0;
	}
	//else
	//{
	//	num_kill--;
	//}

	// kill info
	KillInfo info;
	info.position = target->position;
	info.mapid = server.level_info.level_id;
	info.time = time(NULL);

	info.deadid = target->GetCharacterInfo().character_id;
	info.deadcareerid = target->GetCurCharinfo().career_id;

	info.killerid = GetCharacterInfo().character_id;
	info.killercareerid = GetCurCharinfo().career_id;
	info.killerweaponid = weapon.base_info.sid;

	if (info.killerweaponid)
		server.kill_infos.push_back(info);
}


// on died
void Client::OnDied(Client * killer)
{
	data.ScoreDataDied(GetCurCharinfo(), (int)life_time, true);

	m_vecContinueKill.clear();

	if (!server.first_died)
		server.first_died = GetCharacterInfo().character_id;

	input_limit_timer = -1;
	drinkrecover = 0;
	drinkrecovercount = -1;
	num_die++;
	ischarge_skill = false;
	charge_skill_time = -1;
	charge_skill_alltime = -1;
	charge_skill_cd = -1;
	itemmode_skill_cd = -1;
	somg_hurt_interval_time = 0.f;
	state = CS_Died;
	die_counter +=1;
	ResetAllCure();

	//  [11/29/2013 aijiwei] 停止死亡后失效EFFECT
	effect.OnPlayDead();

	//计算重生时间
	spawn_time = fmaxf(server.round_rebirth_time_max, 0);
	spawn_time += died_camera_time;
	if (server.game_type == Game::kHoldPoint && server.hold_points[server.cur_holdpoint].owner_team != InvalidTeam)
	{
		if (server.hold_points[server.cur_holdpoint].owner_team == team)
		{
			spawn_time += server.config.spawn_time_adjust;
		}
		else
		{
			spawn_time -= server.config.spawn_time_adjust;
		}
	}
	float value = 1;
	if (effect.SumEffect(kEffect_Infect_Rebirth, value))
	{
		spawn_time += value;
	}
	
	selfrecover_interval = SELFRECOVER_INTERVAL;
	
	accumulate_damage = 0;
	Weapon & w = GetCurWeaponInfo();
	if (w.weapon_data.weapon.type > kWeaponTypePistol && w.weapon_data.weapon.type < kWeaponTypeKnife)
		DropWeapon(GetCurWeapon(), position, 0);
	
	// drop item
	DoCharacterDropItem();

	switch (server.game_type)
	{
	case Game::kBossMode2:
		{
			if(server.boss2_showtime)
				spawn_time = NEVER_SPAWN;
		}
		break;
	case Game::kTeamDeathMatch:
		{
			if(server.round_start_time  <= 0.f)
				spawn_time = NEVER_SPAWN;
		}
		break;
	case Game::kBossMode:
		{
			if(server.round_start_time  <= 0.f)
				spawn_time = NEVER_SPAWN;
		}
		break;
	case Game::kBombMode:
		{
			if(server.round_start_time  <= 0.f)
				spawn_time = NEVER_SPAWN;

			if(server.bomb_defusing_uid == uid)
				server.ClearDefusingState();
			if(server.bomb_owner_id == uid && server.bomb_planting)
			{
				CancelPlantBomb();
				server.ClearPlantState();
			}
		}
		break;
	case Game::kBossPVE:
		{
			spawn_time = NEVER_SPAWN;
			if(team == 1)
			{
				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = server.clients + i;

					if (c && c->IsReady() && c->team == 0)
					{
						if (server.cServerType == (byte)SvrType_Match)
						{
						}
						else
						{
							c->data.ScoreDataAdd(server.config.kill_boss_pve_score, true);
						}
					}
				}
			}
		}
		break;
	case Game::kCommonZombieMode:
		{
			server.somg_area.erase(uid);
			die_pos = position;
			can_Invincible = false;
			if (team == 0 && this != killer)
			{
				if (this != killer)
					is_common_zombie_spawn = true;
				else
					spawn_time = NEVER_SPAWN;
				if (killer)
				{
					if(is_human_super)
					{
						if (server.cServerType == (byte)SvrType_Match)
						{
						}
						else
						{
							killer->data.ScoreDataAdd(server.config.kill_common_human_super_score, true);
						}
					}
					else
					{
						if (server.cServerType == (byte)SvrType_Match)
						{
						}
						else
						{
							killer->data.ScoreDataAdd(server.config.kill_common_human_score, true);
						}
					}
				}
			}
			else if (team == 1)
			{
				if (is_zombie_super)
				{
					spawn_time = NEVER_SPAWN;
					can_spawn = false;
					if (killer)
					{
						if (server.cServerType == (byte)SvrType_Match)
						{
						}
						else
						{
							killer->data.ScoreDataAdd(server.config.kill_common_zombie_super_score, true);
						}
						for (uint i = 0; i < max_client_count; i++)
						{
							Client * c = server.clients + i;

							if (c && c->IsReady())
							{
								if((c->uid == assist_uid[0] && assist_uid[0] != killer->uid) || (c->uid == assist_uid[1] && assist_uid[1] != killer->uid))
								{
									if (server.cServerType == (byte)SvrType_Match)
									{
									}
									else
									{
										c->data.ScoreDataAdd(server.config.assist_common_zombie_super_score, true);
									}
								}
							}
						}
					}
				}
				else
				{
					if (killer)
					{
						if (server.cServerType == (byte)SvrType_Match)
						{
						}
						else
						{
							killer->data.ScoreDataAdd(is_zombie_king ? server.config.kill_common_king_zombie_score:server.config.kill_common_zombie_score, true);
						}
						for (uint i = 0; i < max_client_count; i++)
						{
							Client * c = server.clients + i;
	
							if (c && c->IsReady())
							{
								if((c->uid == assist_uid[0] && assist_uid[0] != killer->uid) || (c->uid == assist_uid[1] && assist_uid[1] != killer->uid))
								{
									if (server.cServerType == (byte)SvrType_Match)
									{
									}
									else
									{
										c->data.ScoreDataAdd(is_zombie_king ? server.config.assist_common_king_zombie_score: server.config.assist_common_zombie_score, true);
									}
								}
							}
						}
					}
				
					if (common_zombie_level > 0)
					{
						SetCommonZombieLevel(common_zombie_level - 1);
						switch(common_zombie_level)
						{
						case 0: 
							{
								common_zombie_energy = 0;
								NotifyCommonZombieEnergyChange();	
							}
							break;
						case 1: 
							{
								common_zombie_energy = is_zombie_king ? 600 : 300;
								NotifyCommonZombieEnergyChange();
							}
							break;
						}
						spawn_time = 5 + died_camera_time;
					}
					else
					{
						spawn_time = NEVER_SPAWN;
						can_spawn = false;
						int pos[16] = {0};
						int count = 0;
						if (is_zombie_king)
						{
							if (server.zombie_super)
							{
								for (uint i = 0; i < max_client_count; i++)
								{
									Client * c = server.clients + i;
									if (c->team == 1 && c->can_spawn)
									{
										pos[count] = i;
										count++;
									}
								}
							}
							if (count > 0)
							{
								Client * c = server.clients + pos[rand() % count];
								if (c)
								{
									c->NotifyCommonZombieSuper(c->position);
									server.zombie_super = false;
								}
							}
						}
					}
				}
			}
		}
		break;
	case Game::kStreetBoyMode:
		{
			if(team < 2)
			{
				if(server.street_king_info[team].current_street_king_uid == uid)
				{
					spawn_time = NEVER_SPAWN;
					if(killer && killer->uid != uid)
					{
						server.street_king_info[team == 0 ? 1 : 0].next_round_street_king = killer->uid;
					}
				}
				else
				{
					spawn_time = 8.f;
				}
			}
		}
		break;
	case Game::kZombieMode:
		{
			if(team == 0)
			{
				spawn_time = NEVER_SPAWN;
				ClearZombieSavingState();

				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = server.clients + i;

					if (c && c->IsReady() && c->team == 1)
					{
						if(c->uid == assist_uid[0] && assist_uid[0] != killer->uid)
						{
							c->data.ScoreDataAdd(server.config.kill_human_assist_score, true);
						}
						else
						{
							float score_killer = (killer->uid == c->uid) ? server.config.kill_human_killer_score : server.config.kill_human_score;
							if (server.cServerType == (byte)SvrType_Match)
							{
							}
							else
							{
								c->data.ScoreDataAdd(score_killer, true);
							}
						}
					}
				}
				Vector3 from_position = Vector3(0,0,0);
				if(killer)
					from_position = killer->position;

				server.AddDropGoldWithSpeed(position, from_position, kSupplyGoldWithForce, 50, uid, 1);
			}
			else if(team == 1)
			{
				//spawn_time = 10 + zombie_rebirth_counter;
				spawn_time = 20;


				float value1;
				if (effect.SumEffect(kEffect_Infect_Rebirth, value1))
				{
					spawn_time += value1;
				}

				spawn_time -= (float)(zombie_rebirth_counter);
				spawn_time = Clamp(spawn_time,6.f,20.f);


				int num = rand()%10;
				if (num < 4)
				{
					AddZombieDropSupply(kSupplyHp,position,25);
				}
				else
				{
					AddZombieDropSupply(kSupplyAmmo,position,30);
				}
				zombie_rebirth_counter ++;

				for (uint i = 0; i < max_client_count; i++)
				{
					Client * c = server.clients + i;

					if(c->team == 0 && killer->uid == c->uid)
					{
						if (server.cServerType == (byte)SvrType_Match)
						{
						}
						else
						{
							c->data.ScoreDataAdd(server.config.kill_zombie_score,true);
						}
					}
				}

				//僵尸自爆
				ZombieSkill_Bomer();
			}
		}
		break;
	case kTDMode:
		{
			if (num_die <= 10)
				spawn_time += num_die * 1.0f;
			else
				spawn_time += 10;
		}
		break;
	}

	if (server.cServerType == (byte)SvrType_Match)
	{
		data.ScoreDataAdd(-2.0f, true);
	}
}

// on action
void Client::OnAction()
{
	input_limit_timer = INPUT_LIMIT_TIME;
	
	effect.OnAction();
}

// recover stop
void Client::RecoverStop()
{
	if (IsReady())
	{
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c && c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_PlayerRecoverStop);
				c->WriteByte(uid);
				c->EndWrite();
			}
		}
	}
}

void Client::DoCharacterDropItem()
{
	static const float drop_y = 25;
	std::vector<CharacterDropItem> new_dropitem;
	
	new_dropitem.reserve(GetCurCharinfo().dropitems.size());
	
	int fu_huo_bi_use_count_all = 0;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;
		if (c && c->IsReady())
		{
			fu_huo_bi_use_count_all += c->fu_huo_bi_use_count;
		}
	}
	float cun_gailv_sum = 0;
	for (std::vector<CharacterDropItem>::reverse_iterator itr = GetCurCharinfo().dropitems.rbegin(); 
		itr != GetCurCharinfo().dropitems.rend(); itr++)
	{
		const CharacterDropItem &item = *itr;
		if (item.count > 0)
		{
			CharacterDropItem tmp_item;
			bool gailv_over = false;
			float gailv = item.parameter1 + item.parameter2 * fu_huo_bi_use_count_all;
			
			cun_gailv_sum += gailv;
			if (cun_gailv_sum >= 1)
			{
				gailv -= cun_gailv_sum - 1;
				gailv_over = true;
			}
			
			tmp_item.itemid = item.itemid;
			tmp_item.parameter1 = gailv;
			tmp_item.parameter2 = 0;
			tmp_item.count = item.count;
			
			new_dropitem.push_back(tmp_item);
			
			if (gailv_over)
				break;
		}
	}
	
	log_write(LOG_DEBUG3, "DoCharacterDropItem() dropitem : %d, new_dropitem : %d, name : %s, careername : %s", 
			GetCurCharinfo().dropitems.size(), new_dropitem.size(), GetCharacterInfo().character_name, GetCurCharinfo().careername);
	
	for (std::vector<CharacterDropItem>::const_iterator itr = new_dropitem.begin(); 
		itr != new_dropitem.end(); itr++)
	{
		const CharacterDropItem &item = *itr;
		for (int i = 0; i < item.count; i++)
		{
			float randnum = (float)rand() / ((float)RAND_MAX + 1);
			if (randnum > (1 - item.parameter1))
			{
				for (SupplyObject* supply = server.supply_list.Begin(); 
					supply < server.supply_list.End(); supply++)
				{
					if (supply && !supply->IsActive() && 
						supply->supply.type >= kSupplyDropItem)
					{
						float rand_x = RandomFloat(-10, 10);
						float rand_y = RandomFloat(-10, 10);
						float rand_z = RandomFloat(-10, 10);
						
						supply->Create();
						supply->supply.type = kSupplyDropItem;
						supply->supply.value = item.itemid;
						supply->position = Vector3(rand_x < 0 ? rand_x - 5 : rand_x + 10, 
													drop_y + rand_y, 
													rand_z < 0 ? rand_z - 5 : rand_z + 10);
						supply->rotation = 0;
						
						for (int j = 0; j < max_client_count; ++j)
						{
							if (server.clients[j].IsReady())
								server.clients[j].AddSupplyObject(supply);
						}
						
						log_write(LOG_DEBUG3, "DoCharacterDropItem() id : %d, cnt : %d, %d, [%f %f]", 
								item.itemid, item.count, i, randnum, item.parameter1);
						
						break;
					}
				}
			}
		}
	}
}

void Client::ParseSuicide()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	Suicide();
}

void Client::ParseRadioReport()
{
	if (team > 1)
		return;

	int radio_id;
	int radio_item;
	char avrname[40];
	ReadInt(radio_id);
	ReadInt(radio_item);
	ReadString(avrname,sizeof(avrname));
	if (gag.IsEnable())
	{
		// broadcast
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady() ) //&& c != this
			{
				c->BeginWrite();
				c->WriteByte(SM_RadioReport);
				c->WriteByte(uid);
				c->WriteInt(radio_id);
				c->WriteInt(radio_item);
				c->WriteString(avrname);
				c->EndWrite();
			}
		}
	}
	
}

// parse spawn confirm
void Client::ParseSpawnConfirm()
{
	if (team > 1)
		return;

	spawn_confirm = true;
}

void Client::ParseActionOn()
{
	if (!IsAlive())
		return;

	if (team > 1)
		return;

	int action_type;
	bool is_action_on;

	ReadInt(action_type);
	ReadByte((byte&)is_action_on);

	// broadcast
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerActionOn);
			c->WriteByte(uid);
			c->WriteInt(action_type);
			c->WriteByte(is_action_on);
			c->EndWrite();
		}
	}
}

// request change career
void Client::ParseChangeCareer()
{
	int c;
	ReadInt(c);

	target_career = c;

	log_write(LOG_DEBUG1,"%s, %s, uid : %d, target_career %d, socket : %d", __FILE__, __FUNCTION__, GetCharacterInfo().character_id, c, server.channel_socket);
}

// request leave game
void Client::RequestLeaveGame()
{
	ReadInt(leavereason);
	switch(leavereason)
	{
	case kLeaveGameReasonSuccessNovice:
		{
			log_write(LOG_DEBUG1, "%s, %s, reason  : %d", __FILE__, __FUNCTION__, leavereason);
		}
		break;
	case kLeaveGameReasonFailedNovice:
		{
			server.RequestEndNovice(*this, false);
		}
		break;
	default:
		{
			server.RequestStageQuit(*this);
			m_bRequestLeaved = true;
		}
		break;
	}

	log_write(LOG_DEBUG1, "%s, %s, client leave game reason : %d, name : %s", __FILE__, __FUNCTION__, leavereason, m_character_info.character_name);
}

// leave game
void Client::LeaveGame(bool cleared)
{
	BeginWrite();
	WriteByte(SM_GameLeave);
	WriteByte(cleared);
	WriteInt(leavereason);
	EndWrite();
	Disconnect();
}

// dropped gun destroy
void Client::DestroyDroppedWeapon(uint uid)
{
	if (IsConnected())
	{
		BeginWrite();
		WriteByte(SM_DestroyDroppedWeapon);
		WriteInt(uid);
		EndWrite();
	}
}

// round start
void Client::RoundStart(float wait_time)
{
	{
		boss2_human_energy = 0;
		boss2_human_energy_level = 1;
		boss2_human_energy_max = server.config.boss2_human_energy_max_init;
		
		boss2_passiveskill_level[0] = 0;
		boss2_passiveskill_level[1] = 0;
		boss2_passiveskill_level[2] = 0;
		boss2_passiveskill_level[3] = 0;
		
		boss2_initiative_type = 0;
		
		boss2_strange_spawn_use = false;
		boss2_strange_spawn = -1;
		ismoonbossflag = false;
	}

	BeginWrite();
	WriteByte(SM_RoundStart);
	WriteFloat(wait_time);
	WriteFloat(server.round_start_total_time);
	EndWrite();
}

// round start play
void Client::RoundStartPlay()
{
	BeginWrite();
	WriteByte(SM_RoundStartPlay);
	EndWrite();
}

// round end
void Client::RoundEnd(byte team)
{
	data.ScoreDataDied(GetCurCharinfo(), (int)life_time, false);
	life_time = 0;

	BeginWrite();
	WriteByte(SM_RoundEnd);
	WriteIntArray(server.team_rounds, 2);
	WriteByte(team);
	EndWrite();
}

void Client::GameStartTimerNotify(int i)
{
	if(server.round_playing == false)
		return;
	BeginWrite();
	WriteByte(SM_Number_Timer);
	WriteInt(i);
	EndWrite();
}

void Client::NotifyZombieFlash(int player_count, bool is_first)
{
	if(server.round_playing == false)
		return;

	if (team > 1)
		return;

	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;

	armor = 0;
	max_armor = 0;

	int human_count = server.config.zombie_mode_human_number[player_count - 1];
	int zombie_count = player_count - human_count;

	bool is_king = false;

	int rand_num = rand() % 60;
	if(human_count >= rand_num)
		is_king = true;

	uint zombie_career = target_career;
	if (!is_king)
	{
		if (IsSingleCharacterInfoExist(DEFAULT_ZOMBIE_NORMAL))
		{
			int ran = rand()%2;
			if(ran == 0)
			{
				zombie_career = DEFAULT_ZOMBIE_NORMAL;
			}
			else if(IsSingleCharacterInfoExist(DEFAULT_ZOMBIE_BOMB))
			{
				zombie_career = DEFAULT_ZOMBIE_BOMB;
			}
			else
			{
				zombie_career = DEFAULT_ZOMBIE_NORMAL;
			}
		}
		
	}

	if(is_king && IsSingleCharacterInfoExist(DEFAULT_ZOMBIE_KING))
	{
		zombie_career = DEFAULT_ZOMBIE_KING;
	}

	if(!is_first)
	{
		SetCurCharInfo(zombie_default_career, true);
	}
	else
	{
		zombie_default_career = GetCurCharinfo().career_id;
	}

	int a, b, c, d;
	GetCharacterItemLevel(GetCurCharinfo(), a, b, c, d);

	//zombie_career = DEFAULT_ZOMBIE_KING;
	SetCurCharInfo(zombie_career, true);

	SingleCharacterInfo& current_character = GetCurCharinfo();

	max_health_base = max_health_unscale;
	ex_health_base = ex_health_unscale;
	{
		effect.Initialize();
		{
			EffectData effectdata;

			effectdata.duration_timer = 1.f;
			effectdata.type = kEffect_Invincible;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);
		}

		{
			EffectData effectdata;
			EffectData effectdata2;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_Damage;
			effectdata.value = powf(BASE_VALUE, a) - 1.f;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);


			float equip_resistance = powf((3 * sqrtf(powf(BASE_VALUE, b)) + 1 * sqrtf(powf(BASE_VALUE, c)) + 2 * sqrtf(powf(BASE_VALUE, d))) / 6.f, 2) - 1.f;


			//int count = Clamp(player_count, 1, 16);
			//float soft_balance_resistance = server.config.zombie_mode__resistance_all[player_count - 1];
			float soft_balance_resistance = 0.f;
			soft_balance_resistance = 3.f * ((float)human_count/(float)zombie_count - 0.3334f);

			effectdata2.duration_timer = DURATION_INFINITY;
			effectdata2.type = kEffect_Infect_ResistanceAll;
			effectdata2.value = soft_balance_resistance + equip_resistance + soft_balance_resistance * equip_resistance;
			effectdata2.enable = true;

			effect.ApplySystemEffect(effectdata2);
		}

	}

	data.ScoreDataRebirth(current_character.career_id, true);
	uint s_rand = rand();
	rand_fun.Srand(s_rand);

	ResetAllCure();

	health = max_health;

	sync_flags = 0;
	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;

	spawn_time = 0;
	spawn_timing = false;
	state = CS_Alive;
	spawn_confirm = false;
	playing = true;
	connected = true;

	team = 1;

	server.OnTeamAliveChanged(team, false);

	Transform transform;
	transform.position = position;
	transform.rotation = 0;
	
	if(!server.zombie_step_two_flag)
	{
		int size = server.start_point[team].size();
		if (size > 0)
		{
			transform = server.start_point[team][rand() % size];
		}
	}
	else
	{
		int size = server.zombie_step_two_point.size();
		if (size > 0)
		{
			transform = server.zombie_step_two_point[rand() % size];
		}
	}

	position = transform.position;

	rotation.x = 0;
	rotation.y = cosf(3.1415926f / 180.f * transform.rotation * 0.5f);
	rotation.z = 0;
	rotation.w = sinf(3.1415926f / 180.f * transform.rotation * 0.5f);


	//rotation.SetZXY(Vector3(0, transform.rotation, 0));

	// broadcast player spawn
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Zombie_Flash);
			c->WriteByte(uid);
			//setteam
			c->WriteByte(team);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteInt(armor);
			c->WriteInt(s_rand);
			c->WriteVector3(position);
			c->WriteFloat(transform.rotation);
			WriteSingleCharacter(*c, current_character);
			c->EndWrite();

			// 修复SM_StreetKing_Flash与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);

			log_write(LOG_DEBUG1, "client zombie flash %d", uid);
		}
	}
}

void Client::NotifyCommonKingZombieFlash(Vector3 pos)
{
	if (team > 1)
		return;

	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;

	armor = 0;
	max_armor = 0;

	is_zombie_king = true;

	int player_count = 0;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady() && c->IsAlive())
		{
			player_count ++;
		}
	}

	int a, b, c, d;
	GetCharacterItemLevel(GetCurCharinfo(), a, b, c, d);

	if (buff_zombie_id != -1)
		SetCurCharInfo(buff_zombie_id, true);
	else
		SetCurCharInfo(server.level_info.KingZombie_info.career_id, true);


	
	const SingleCharacterInfo& current_character = GetCurCharinfo();

	max_health_base = max_health_unscale;
	ex_health_base = ex_health_unscale;

	effect.Initialize();
	{
		{
			EffectData effectdata;
			EffectData effectdata2;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_Damage;
			effectdata.value = powf(BASE_VALUE, itemlevel_a) - 1.f;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);

			effectdata2.duration_timer = DURATION_INFINITY;
			effectdata2.type = kEffect_Infect_ResistanceAll;
			effectdata2.value = effectdata2.value = powf((3 * sqrtf(powf(BASE_VALUE, itemlevel_b)) + 1 * sqrtf(powf(BASE_VALUE, itemlevel_c)) + 2 * sqrtf(powf(BASE_VALUE, itemlevel_d))) / 6.f, 2) - 1.f;
			effectdata2.enable = true;
			effect.ApplySystemEffect(effectdata2);
		}
	}
	SetCommonZombieLevel(common_zombie_level);
	health = max_health;
	data.ScoreDataRebirth(current_character.career_id, true);
	uint s_rand = rand();
	rand_fun.Srand(s_rand);
	sync_flags = 0;
	SetTeam(1);

	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;
	spawn_time = 0;
	spawn_timing = false;
	state = CS_Alive;
	spawn_confirm = false;
	playing = true;
	connected = true;

	server.OnTeamAliveChanged(team, false);

	int size = server.start_point[team].size();

	position = pos;

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_CommonKingZombie_Flash);
			c->WriteByte(uid);
			//setteam
			c->WriteByte(team);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteInt(armor);
			c->WriteInt(s_rand);
			c->WriteVector3(position);
			c->WriteQuaternion(rotation);
			WriteSingleCharacter(*c, current_character);
			c->WriteInt(common_zombie_level);
			c->WriteFloat(common_zombie_energy);
			c->WriteFloat(0.0f);
			c->EndWrite();

			// 修复SM_Boss_Flash与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
		}
	}
}

void Client::NotifyCommonZombieFlash(const Vector3 position)
{
	if (team > 1)
		return;

	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;

	armor = 0;
	max_armor = 0;

	int player_count = 0;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady() && c->IsAlive())
		{
			player_count ++;
		}
	}

	int a, b, c, d;
	GetCharacterItemLevel(GetCurCharinfo(), a, b, c, d);

	if (buff_zombie_id != -1)
		SetCurCharInfo(buff_zombie_id, true);
	else
		SetCurCharInfo(server.level_info.Zombie_info.career_id, true);

	const SingleCharacterInfo& current_character = GetCurCharinfo();

	max_health_base = max_health_unscale;
	ex_health_base = ex_health_unscale;

	effect.Initialize();
	{
		{
			EffectData effectdata;
			EffectData effectdata2;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_Damage;
			effectdata.value = powf(BASE_VALUE, a) - 1.f;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);

			effectdata2.duration_timer = DURATION_INFINITY;
			effectdata2.type = kEffect_Infect_ResistanceAll;
			effectdata2.value = effectdata2.value = powf((3 * sqrtf(powf(BASE_VALUE, b)) + 1 * sqrtf(powf(BASE_VALUE, c)) + 2 * sqrtf(powf(BASE_VALUE, d))) / 6.f, 2) - 1.f;
			effectdata2.enable = true;
			effect.ApplySystemEffect(effectdata2);
		}
	}

	SetCommonZombieLevel(common_zombie_level);

	health = max_health;

	data.ScoreDataRebirth(current_character.career_id, true);
	uint s_rand = rand();
	rand_fun.Srand(s_rand);

	sync_flags = 0;
	SetTeam(1);

	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;

	spawn_time = 0;
	spawn_timing = false;
	state = CS_Alive;
	spawn_confirm = false;
	playing = true;
	connected = true;

	server.OnTeamAliveChanged(team, false);
	SetTeam(team);

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_CommonZombie_Flash);
			c->WriteByte(uid);
			//setteam
			c->WriteByte(team);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteInt(armor);
			c->WriteInt(s_rand);
			c->WriteVector3(position);
			c->WriteQuaternion(rotation);
			WriteSingleCharacter(*c, current_character);
			c->WriteInt(common_zombie_level);
			c->WriteFloat(common_zombie_energy);
			c->WriteFloat(0.0f);
			c->EndWrite();

			// 修复SM_Boss_Flash与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
		}
	}
	server.special_person_flashed = true;
}

void Client::NotifyCommonZombieHumanDie(byte from, byte to, byte part, Weapon weapon)
{
	if (team > 1)
		return;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_CommonZombieHumanDie);
			c->WriteByte(uid);
			c->WriteByte(from);
			c->WriteByte(to);
			c->WriteByte(part);
			WriteWeapon(*c,  weapon);
			c->EndWrite();
		}
	}
}

void Client::NotifyCommonZombieLevelChange(byte is_up)
{
	if (team > 1)
		return;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_CommonZombie_LevelChange);
			c->WriteByte(uid);
			c->WriteInt(common_zombie_level);
			c->WriteByte(is_up);
			c->EndWrite();
		}
	}
}

void Client::NotifyCommonZombieEnergyChange()
{
	float percent;
	if (is_zombie_king)
	{
		switch(common_zombie_level)
		{
		case 0 : percent = common_zombie_energy / 500.0f; break;
		case 1 : percent = common_zombie_energy / 1200.0f; break;
		case 2 : percent = 1.0f; break;
		}
	}
	else
	{
		switch(common_zombie_level)
		{
		case 0 : percent = common_zombie_energy / 300.0f; break;
		case 1 : percent = common_zombie_energy / 800.0f; break;
		case 2 : percent = 1.0f; break;
		}
	}
	BeginWrite();
	WriteByte(SM_CommonZombie_EnergyChange);
	WriteByte(uid);
	WriteFloat(common_zombie_energy);
	WriteFloat(percent);
	EndWrite();
}

void Client::NotifyCommonZombieSuper(const Vector3 pos)
{
	if (team > 1)
		return;
	zombie_unable_state_flag = false;
	int a, b, c, d;
	GetCharacterItemLevel(GetCurCharinfo(), a, b, c, d);

	SetCurCharInfo(server.level_info.SuperZombie_info.career_id, true);
	const SingleCharacterInfo& current_character = GetCurCharinfo();
	data.ScoreDataRebirth(current_character.career_id, true);
	max_health_base = max_health_unscale;
	ex_health_base = ex_health_unscale;
	effect.Initialize();
	{
		EffectData effectdata;
		EffectData effectdata2;
		EffectData effectdata3;
		EffectData effectdata4;
		EffectData effectdata5;
		EffectData effectdata6;

		effectdata.duration_timer = 1.f;
		effectdata.type = kEffect_Invincible;
		effectdata.enable = true;
		effect.ApplySystemEffect(effectdata);

		effectdata2.duration_timer = DURATION_INFINITY;
		effectdata2.type = kEffect_Sustain_HpRecover2;
		effectdata2.interval = effectdata2.interval_timer = 1;
		effectdata2.value = 300;
		effectdata2.enable = true;

		effect.ApplySystemEffect(effectdata2);

		effectdata3.duration_timer = DURATION_INFINITY;
		effectdata3.type = kEffect_Infect_MoveSpeed;
		effectdata3.value = 0.6f;
		effectdata3.enable = true;

		effect.ApplySystemEffect(effectdata3);

		effectdata4.duration_timer = DURATION_INFINITY;
		effectdata4.type = kEffect_Infect_JumpHeight;
		effectdata4.value = 0.57f;
		effectdata4.enable = true;

		effect.ApplySystemEffect(effectdata4);

		effectdata5.duration_timer = DURATION_INFINITY;
		effectdata5.type = kEffect_Infect_ResistanceAll;
		effectdata5.value = powf((3 * sqrtf(powf(BASE_VALUE, itemlevel_b)) + 1 * sqrtf(powf(BASE_VALUE, itemlevel_c)) + 2 * sqrtf(powf(BASE_VALUE, itemlevel_d))) / 6.f, 2) - 1.f;
		effectdata5.enable = true;
		effect.ApplySystemEffect(effectdata5);

		effectdata6.duration_timer = DURATION_INFINITY;
		effectdata6.type = kEffect_Infect_Damage;
		effectdata6.value = powf(BASE_VALUE, itemlevel_a) - 1.f;
		effectdata6.enable = true;
		effect.ApplySystemEffect(effectdata6);
	}
	health = max_health;
	is_zombie_super = true;
	zombie_skill_cd = GetCurWeaponInfo().weapon_data.zb_info.skill_cooldown;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_CommonZombie_Super);
			c->WriteByte(uid);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteVector3(pos);
			c->WriteQuaternion(rotation);
			WriteSingleCharacter(*c, current_character);
			c->EndWrite();
		}
	}
}

void Client::NotifyCommonHumanSuper()
{
	if (team > 1)
		return;
	super_human_skill_cd_time = 0.0f;
	max_health_base = 20001;
	ex_health_base = 20001;
	is_human_super = true;
	const SingleCharacterInfo& current_character = GetCurCharinfo();
	//data.ScoreDataRebirth(current_character.career_id, true);
	{
		EffectData effectdata;
		EffectData effectdata2;
		EffectData effectdata3;
		EffectData effectdata4;

		effectdata.duration_timer = DURATION_INFINITY;
		effectdata.type = kEffect_Infect_MoveSpeed;
		effectdata.value = (6.0f - GetCurCharinfo().run_speed) / GetCurCharinfo().run_speed;
		effectdata.enable = true;

		effect.ApplySystemEffect(effectdata);

		effectdata2.duration_timer = DURATION_INFINITY;
		effectdata2.type = kEffect_Infect_Damage;
		effectdata2.value = 10.0f;
		effectdata2.enable = true;

		effect.ApplySystemEffect(effectdata2);

		effectdata3.duration_timer = DURATION_INFINITY;
		effectdata3.type = kEffect_Infect_FireTime;
		effectdata3.value = 0.2f;
		effectdata3.enable = true;

		effect.ApplySystemEffect(effectdata3);

		effectdata4.duration_timer = DURATION_INFINITY;
		effectdata4.type = kEffect_Sustain_HpRecover;
		effectdata4.value = 1000.0f;
		effectdata4.interval = effectdata4.interval_timer = 1.0f;
		effectdata4.enable = true;

		effect.ApplySystemEffect(effectdata4);

		
	}
	health = 20001;
	
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_CommonHuman_Super);
			c->WriteByte(uid);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteVector3(position);
			c->WriteQuaternion(rotation);
			WriteSingleCharacter(*c, current_character);
			c->EndWrite();
		}
	}
	
}

void Client::NotifyCommonKingZombieHalfFlash()
{
	if (team != 1)
		return;

	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;

	armor = 0;
	max_armor = 0;

	is_zombie_king = true;

	int player_count = 0;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady() && c->IsAlive())
		{
			player_count ++;
		}
	}

	int a, b, c, d;
	GetCharacterItemLevel(GetCurCharinfo(), a, b, c, d);

	if (buff_zombie_id != -1)
		SetCurCharInfo(buff_zombie_id, true);
	else
		SetCurCharInfo(server.level_info.KingZombie_info.career_id, true);

	const SingleCharacterInfo& current_character = GetCurCharinfo();

	max_health_base = max_health_unscale;
	ex_health_base = ex_health_unscale;

	effect.Initialize();
	{
		{
			EffectData effectdata;
			EffectData effectdata2;

			

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_Damage;
			effectdata.value = powf(BASE_VALUE, itemlevel_a) - 1.f;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);

			effectdata2.duration_timer = DURATION_INFINITY;
			effectdata2.type = kEffect_Infect_ResistanceAll;
			effectdata2.value = effectdata2.value = powf((3 * sqrtf(powf(BASE_VALUE, itemlevel_b)) + 1 * sqrtf(powf(BASE_VALUE, itemlevel_c)) + 2 * sqrtf(powf(BASE_VALUE, itemlevel_d))) / 6.f, 2) - 1.f;
			effectdata2.enable = true;
			effect.ApplySystemEffect(effectdata2);
		}
	}
	SetCommonZombieLevel(common_zombie_level);
	health = max_health / 2;
	data.ScoreDataRebirth(current_character.career_id, true);
	uint s_rand = rand();
	rand_fun.Srand(s_rand);
	sync_flags = 0;
	team = 1;

	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;

	spawn_time = 0;
	spawn_timing = false;
	state = CS_Alive;
	spawn_confirm = false;
	playing = true;
	connected = true;

	server.OnTeamAliveChanged(team, false);

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_King_Zombie_Respawn);
			c->WriteByte(uid);
			//setteam
			c->WriteByte(team);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteInt(armor);
			c->WriteInt(s_rand);
			c->WriteVector3(position);
			c->WriteQuaternion(rotation);
			WriteSingleCharacter(*c, current_character);
			c->WriteInt(common_zombie_level);
			c->WriteFloat(common_zombie_energy);
			c->WriteFloat(0.0f);
			c->EndWrite();

			// 修复SM_Boss_Flash与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
		}
	}
	server.special_person_flashed = true;
	zombie_unable_state = false;
}

void Client::SetCommonZombieLevel(int value)
{
	if (is_zombie_super)
	{
		return;
	}
	float before, now;
	EffectData effectdata;
	EffectData effectdata2;
	EffectData effectdata3;
	EffectData effectdata4;
	EffectData effectdata5;

	effectdata2.duration_timer = DURATION_INFINITY;
	effectdata2.type = kEffect_Sustain_HpRecover2;
	effectdata2.interval = effectdata2.interval_timer = 1;
	effectdata2.value = is_zombie_king ? zombie_level_up_info.KingRecover[value] : zombie_level_up_info.Recover[value];
	effectdata2.enable = true;

	effect.ApplySystemEffect(effectdata2);

	effectdata3.duration_timer = DURATION_INFINITY;
	effectdata3.type = kEffect_Infect_MoveSpeed;
	before = GetCurCharinfo().run_speed;
	now = is_zombie_king ? zombie_level_up_info.KingSpeed[value] : zombie_level_up_info.Speed[value];
	effectdata3.value = (now - before) / before;
	effectdata3.enable = true;

	effect.ApplySystemEffect(effectdata3);

	effectdata4.duration_timer = DURATION_INFINITY;
	effectdata4.type = kEffect_Infect_JumpHeight;
	effectdata4.value = 0.57f;
	effectdata4.enable = true;

	effect.ApplySystemEffect(effectdata4);

	effectdata5.duration_timer = DURATION_INFINITY;
	effectdata5.type = kEffect_Infect_FireTime;
	
	effectdata5.value = is_zombie_king ? zombie_level_up_info.KingFireTime[value] : zombie_level_up_info.FireTime[value];
	effectdata5.enable = true;

	effect.ApplySystemEffect(effectdata5);

	byte is_up;
	if (common_zombie_level == value)
	{
		return;
	}
	is_up = common_zombie_level < value ? 1 : 0;
	common_zombie_level = value;

	NotifyCommonZombieLevelChange(is_up);
}

void Client::SetCommonZombieEnergy(float value)
{
	if (zombie_unable_state || is_zombie_super || buff_zombie_id != -1)
	{
		return;
	}
	if( value >= 0 )
	{
		if(is_zombie_king)
		{
			if (common_zombie_energy < 600 && common_zombie_energy + value >= 600)
			{
				SetCommonZombieLevel(1);
			}
			if (common_zombie_energy < 1300 && common_zombie_energy + value >= 1300)
			{
				SetCommonZombieLevel(2);
			}
		}
		else
		{
			if (common_zombie_energy < 300 && common_zombie_energy + value >= 300)
			{
				SetCommonZombieLevel(1);
			}
			if (common_zombie_energy < 800 && common_zombie_energy + value >= 800)
			{
				SetCommonZombieLevel(2);
			}
		}
	}
	else
	{
		if (is_zombie_king)
		{
			if (common_zombie_energy >= 600 && common_zombie_energy + value < 600)
			{
				SetCommonZombieLevel(0);
			}
			if (common_zombie_energy >= 1300 && common_zombie_energy + value < 1300)
			{
				SetCommonZombieLevel(1);
			}
		}
		else
		{
			if (common_zombie_energy >= 300 && common_zombie_energy + value < 300)
			{
				SetCommonZombieLevel(0);
			}
			if (common_zombie_energy >= 800 && common_zombie_energy + value < 800)
			{
				SetCommonZombieLevel(1);
			}
		}
	}
	common_zombie_energy += value;

	if (is_zombie_king)
	{
		common_zombie_energy = (float)Clamp(common_zombie_energy, 0, 1300);
	}
	else
	{
		common_zombie_energy = (float)Clamp(common_zombie_energy, 0, 800);
	}
	NotifyCommonZombieEnergyChange();
}

void Client::SetHumanEnergy(int value)
{
	if (is_human_super)
	{
		return;
	}
	if (!is_powup)
	{
		if ((human_energy + value) >= MAX_HUMAN_ENERGY)
		{
			is_powup = true;
			powup_time = 10.0f;
			human_energy = 0;
			HumanPowerUp();
		}
		else
		{
			human_energy += value;
			HumanEnergyChange();
		}
	}
}

void Client::NotifyStreetKingFlash()
{
	if(server.round_playing == false)
		return;
	
	if(server.street_king_info[team].select_state == StateNone)
		return;
	
	if (team > 1)
		return;

	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;
		
	armor = 0;
	max_armor = 0;

	SetCurCharInfo(target_career);

	SingleCharacterInfo& current_character = GetCurCharinfo();

	float health_scale = GetScale(server.level_info.health_scale);
	max_health_base = max_health_unscale * health_scale;
	ex_health_base = ex_health_unscale * health_scale;
	current_character.score_scale *= 3;

	effect.Initialize();
	{
		EffectData effectdata;
		
		effectdata.duration_timer = 2.f;
		effectdata.type = kEffect_Invincible;
		effectdata.enable = true;
		
		effect.ApplySystemEffect(effectdata);
	}

	{
		int count = -1;
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady() && c->team != team && c->team < 2)
			{
				count ++;
			}
		}

		{
			EffectData effectdata;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_MaxHp;
			effectdata.value = 0.2f * float(count);
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);
		}

		
		{
			count -= 1;
			count = Max(count , 0);
			EffectData effectdata;
		
			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_ResistanceClose;
			effectdata.value = 0.2f * float(count);
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);
		}


	}

	data.ScoreDataRebirth(current_character.career_id, true);
	uint s_rand = rand();
	rand_fun.Srand(s_rand);

	ResetAllCure();

	health = max_health;

	sync_flags = 0;
	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;

	spawn_time = 0;
	spawn_timing = false;
	state = CS_Alive;
	spawn_confirm = false;
	playing = true;
	connected = true;

	server.OnTeamAliveChanged(team, false);

	Transform transform;
	transform.position = position;
	transform.rotation = 0;

	int size = server.start_point[team].size();
	if (size > 0)
	{
		transform = server.start_point[team][rand() % size];
	}

	position = transform.position;
	rotation.x = 0;
	rotation.y = cosf(3.1415926f / 180.f * transform.rotation * 0.5f);
	rotation.z = 0;
	rotation.w = sinf(3.1415926f / 180.f * transform.rotation * 0.5f);

	// broadcast player spawn
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_StreetKing_Flash);
			c->WriteByte(uid);
			//setteam
			c->WriteByte(team);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteInt(armor);
			c->WriteInt(s_rand);
			c->WriteVector3(position);
			c->WriteFloat(transform.rotation);
			WriteSingleCharacter(*c, current_character);
			c->WriteByte(server.street_king_info[team].select_state);
			c->EndWrite();

			// 修复SM_StreetKing_Flash与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
			
			log_write(LOG_DEBUG1, "client king flash %d", uid);
		}
	}
}

// -----------------------------------------------------------------
// class client
// -----------------------------------------------------------------
Client::Client()
	: BinaryStream(appcfg.client_buffersize)
	, uid_in_room(0)
	, uid_in_channel(0)
	, target_career(0)
	, effect(this)
	, recover_assist_uid(0)
	, recover_assist_timer(0.f)
	, spray_index(0.f)
	, has_defuse_bomb_item(false)
	, zombie_mode_dying_state(false)
	, zombie_mode_saving_dying_uid(0)
	, zombie_mode_saver_uid(0)
	, zombie_mode_save_timer(0.f)
	, human_rebirth_counter(0)
	, zombie_rebirth_counter(0)
	, bosspve_career_id(0)
	, nowactionindex(0)
	, action_timer(0)
	, total_action_timer(0)
	, old_action_timer(0)
	, is_pveboss(false)
	, common_zombie_level(0)
	, common_zombie_energy(0)
	, common_zombie_energy_percent(0.0f)
	, human_energy(0)
	, is_zombie_king(false)
	, die_pos(Vector3(0, 0, 0))
	, zombie_unable_state(false)
	, zombie_unable_state_flag(true)
	, unable_time(-1.0f)
	, can_spawn(true)
	, is_zombie_super(false)
	, is_human_super(false)
	, powup_time(0.0f)
	, is_powup(false)
	, can_Invincible(false)
	, super_human_skill_cd_time(-1.0f)
	, is_common_zombie_spawn(false)
	, boss2_strange_spawn_use(false)
	, boss2_strange_spawn(-1)
	, itemlevel_a(0)
	, itemlevel_b(0)
	, itemlevel_c(0)
	, itemlevel_d(0)
	, buff_zombie_id(-1)
	, somg_hurt_interval_time(0.f)
	, ghostflag(false)
	, isghost(false)
	, spawntimebySurvival(0.f)
{
	m_curpack = 0;
	m_curweapon = 0;
	
	assist_timer[0] = assist_timer[1] = 0;
	assist_uid[0] = assist_uid[1] = 0;
	state = CS_Disconnected;
	kick_client_count = 0;
	fu_huo_bi = 0;
	fu_huo_bi_use_count = 0;
	can_use_spawn_coin = true;
	die_counter =0;
	die_buff_counter =0;
	alldamage = 0;
	CCoinCount = 0;
	MedalCount = 0;
	WrenchCount = 0;
	BoxACount = 0;
	BoxBCount = 0;
	BoxCCount = 0;
	BoxDCount = 0;
}

Client::~Client()
{
}

// connect
void Client::Connect()
{
	OnConnected();
}

// disconnect
void Client::Disconnect()
{
	// forward left messages.
	server.ForwardClientMessage(*this);

	// on disconnected
	OnDisconnected();
}

// is connected
bool Client::IsConnected()
{
	return state >= CS_Connected;
}

// is ready
bool Client::IsReady()
{
	return state > CS_Loading;
}

// is alive
bool Client::IsAlive()
{
	return state == CS_Alive;
}

bool Client::IsDied()
{
	return state == CS_Died;
}

bool Client::CanHurtDummy(byte owner_id, bool team_hurt)
{
	if (!server.playing)
	{
		return false;
	}
	if (server.round_end_time > 0)
	{
		return false;
	}
	if (server.round_playing == false)
	{
		return false;
	}
	
	if (owner_id == 0)
		return true;
	
	Client* owner = server.GetClient(owner_id);
	if(!owner)
	{
		return false;
	}
	if (owner->team > 1)
	{
		return false;
	}
	//if(!team_hurt && owner->uid != uid && owner->team == team)
	//	return false;

	return true;
}

// can hurt
bool Client::CanHurt(Client* client, bool team_hurt)
{
	if (!client)
	{
		return false;
	}
	if (!server.playing)
	{
		return false;
	}
	if (server.round_end_time > 0)
	{
		return false;
	}
	if (!team_hurt && client->uid != uid && client->team == team)
	{
		return false;
	}
	if (!client->IsAlive())
	{
		return false;
	}
	if (client->effect.HasEffect(kEffect_Invincible))
	{
		return false;
	}
	if (client->effect.HasEffect(kEffect_Survival_Ghost))
	{
		return false;
	}
	if (client->team > 1)
	{
		return false;
	}
	if (server.round_playing == false)
	{
		return false;
	}

	return true;
}

bool Client::CanHurtStrange(Client* client, bool team_hurt)
{
	if (!client)
	{
		return false;
	}
	if (!server.playing)
	{
		return false;
	}
	if (server.round_end_time > 0)
	{
		return false;
	}

	if (!client->IsAlive())
	{
		return false;
	}
	if (client->effect.HasEffect(kEffect_Invincible))
	{
		return false;
	}
	if (client->team > 1)
	{
		return false;
	}
	if (server.round_playing == false)
	{
		return false;
	}

	return true;
}

// update magic
void Client::UpdateMagic()
{
	magic = magic + 1;
	if (magic == 0)
		magic = 1;
}

// read client
Client* Client::ReadClient()
{
	byte cid;
	ReadByte(cid);
	return server.GetClient(cid);
}

DummyBaseInfo* Client::ReadDummyBaseInfo()
{
	uint dummyid;
	ReadInt(dummyid);
	
	std::map<uint, DummyBaseInfo>::iterator itor = server.dummy_object_map.find(dummyid);
	if(itor != server.dummy_object_map.end())
	{
		DummyBaseInfo& info = itor->second;
		return &info;
	}
	
	return NULL;
}

// get charinfo
const CharacterInfo& Client::GetCharacterInfo() const
{
	return m_character_info;
}

void Client::SetCharacterInfo(CharacterInfo &charinfo)
{
	m_character_info = charinfo;
	if (m_character_info.singlecharacter_set.size() > 0)
	{
		m_default_career = m_character_info.singlecharacter_set.begin()->second;
		SetCurCharInfo(m_default_career.career_id);
	}
}

bool Client::AddSingleCharacterInfo(SingleCharacterInfo &info)
{
	if (m_character_info.singlecharacter_set.find(info.career_id) == 
		m_character_info.singlecharacter_set.end())
	{
		m_character_info.character_count++;
		m_character_info.singlecharacter_set.insert(SingleCharacterInfoPair(info.career_id, info));
		return true;
	}
	
	return false;
}

// get cur charinfo
SingleCharacterInfo& Client::GetCurCharinfo()
{
	return m_current_career;
}

bool Client::IsSingleCharacterInfoExist(int career)
{
	SingleCharacterInfoMap::iterator itr = m_character_info.singlecharacter_set.find(career);
	if (itr != m_character_info.singlecharacter_set.end())
	{
		return true;
	}

	return false;
}

// set cur charinfo
void Client::SetCurCharInfo(uint career, bool can_use_all)
{
	SingleCharacterInfoMap::iterator itr = m_character_info.singlecharacter_set.find(career);
	if (itr != m_character_info.singlecharacter_set.end())
	{
		if (itr->second.can_select || can_use_all)
		{
			m_current_career = itr->second;
		}
		else
		{
			log_write(LOG_DEBUG1, "SetCurCharInfo() : can't select career[%d], fallback to use default career", career);

			m_current_career = m_default_career;
		}
	}
	else
	{
		log_write(LOG_DEBUG1, "SetCurCharInfo() : can't find career[%d], fallback to use default career", career);

		m_current_career = m_default_career;
	}
	
	//填充
	SingleCharacterInfo &cur_charinfo = GetCurCharinfo();
	float health_infect = 1;
	
	resistance_flame = 0;
	resistance_explode = 0;
	resistance_bullet = 0;
	resistance_close = 0;
	max_health_unscale = cur_charinfo.max_hp;
	ex_health_unscale = cur_charinfo.ex_hp;
	
	for (std::vector<Costume>::iterator itr = cur_charinfo.costumes.begin(); 
		itr != cur_charinfo.costumes.end(); itr++)
	{
		resistance_flame += itr->costume_data.resistance_flame / 100.f;
		resistance_explode += itr->costume_data.resistance_explode / 100.f;
		resistance_bullet += itr->costume_data.resistance_bullet / 100.f;
		resistance_close += itr->costume_data.resistance_close / 100.f;
		health_infect += itr->costume_data.health_infect / 100.f;
	}
	max_health_unscale *= health_infect;
	log_write(LOG_DEBUG1, "totalBloodAddtotalBloodAddtotalBloodAddtotalBloodAddtotalBloodAddtotalBloodAdd : %f,%f", cur_charinfo.totalBloodAdd,max_health_unscale);	
	max_health_unscale *= cur_charinfo.totalBloodAdd;
	ex_health_unscale *= health_infect;
	
	//设置背包
	SetCurPack(0);
}

// get default charinfo
const SingleCharacterInfo& Client::GetDefaultCharInfo() const
{
	return m_default_career;
}

// use default charinfo
void Client::UseDefaultCharInfo()
{
	SetCurCharInfo(GetDefaultCharInfo().career_id);
}

// get cur pack
byte Client::GetCurPack() const
{
	return m_curpack;
}

// set cur pack
void Client::SetCurPack(byte val)
{
	if (val >= GetCurCharinfo().packs.size())
	{
		val = 0;
	}
	
	if (m_curpack != val)
	{
		// disable effect
		PackInfo &pack = GetCurPackInfo();
		for (std::map<byte, Weapon>::const_iterator itr = pack.weapon_set.begin(); 
			itr != pack.weapon_set.end(); itr++)
		{
			effect.SetEnable(itr->second.base_info, false);
		}
		
		// set
		m_curpack = val;
		
		SetCurWeapon(0);
	}
}

// get cur pack info
PackInfo& Client::GetCurPackInfo()
{
	static PackInfo default_pack;
	
	if (m_curpack >= GetCurCharinfo().packs.size())
	{
		default_pack = PackInfo();
		
		DEBUGLOG_WRITE("GetCurPackInfo() invalid pack[%d], name : %s", m_curpack, GetCharacterInfo().character_name);
		
		return default_pack;
	}
	
	return GetCurCharinfo().packs[m_curpack];
}

// get cur weapon
byte Client::GetCurWeapon() const
{
	return m_curweapon;
}

// set cur weapon
void Client::SetCurWeapon(byte val)
{
	PackInfo &pack = GetCurPackInfo();
	if (pack.weapon_set.find(val) == pack.weapon_set.end())
	{
		val = pack.weapon_set.begin()->first;
	}
	
	// disable effect
	Weapon &w_old = GetCurWeaponInfo();
	effect.SetEnable(w_old.base_info, false);
	
	// set
	m_curweapon = val;
	
	Weapon &w = GetCurWeaponInfo();
	
	// enable effect
	effect.SetEnable(w.base_info, true);
	
	projected_ammo_info.weapon_data.weapon.type = kWeaponTypeNone;
	
	// set projected_ammo_info
	if (w.weapon_data.weapon.type == kWeaponTypeLuncher ||
		w.weapon_data.weapon.type == kWeaponTypeMeditNeedleGun ||
		w.weapon_data.weapon.type == kWeaponTypeSignal)
	{
		projected_ammo_info = w;

		projected_ammo_info.base_info.sid = w.base_info.sid;
		projected_ammo_info.base_info.part_count = 1;
		strcpy(projected_ammo_info.base_info.part_key[0], w.weapon_data.luncher.ammopart_key);
		
		projected_ammo_info.weapon_data.weapon.type = w.weapon_data.luncher.ammo_type;

		projected_ammo_info.weapon_data.ammo.maxalive_time = w.weapon_data.luncher.maxalive_time;
		projected_ammo_info.weapon_data.ammo.gravity = w.weapon_data.luncher.gravity;
		projected_ammo_info.weapon_data.ammo.range = w.weapon_data.luncher.range;
		projected_ammo_info.weapon_data.ammo.damage = w.weapon_data.luncher.damage;
		projected_ammo_info.weapon_data.ammo.dmg_modify_timer_min = w.weapon_data.luncher.dmg_modify_timer_min;
		projected_ammo_info.weapon_data.ammo.dmg_modify_timer_max = w.weapon_data.luncher.dmg_modify_timer_max;
		projected_ammo_info.weapon_data.ammo.dmg_modify_min = w.weapon_data.luncher.dmg_modify_min;
		projected_ammo_info.weapon_data.ammo.dmg_modify_max = w.weapon_data.luncher.dmg_modify_max;
		projected_ammo_info.weapon_data.ammo.capsule_height = w.weapon_data.luncher.capsule_height;
		projected_ammo_info.weapon_data.ammo.capsule_radius = w.weapon_data.luncher.capsule_radius;
	}
	else if (w.weapon_data.weapon.type == kWeaponTypeMiniMachineGun && 
			w.weapon_data.minimachine_gun.ammo_type != kWeaponTypeNone)
	{
		projected_ammo_info = w;

		projected_ammo_info.base_info.sid = w.base_info.sid;
		projected_ammo_info.base_info.part_count = 1;
		strcpy(projected_ammo_info.base_info.part_key[0], w.weapon_data.minimachine_gun.ammopart_key);
		
		projected_ammo_info.weapon_data.weapon.type = w.weapon_data.minimachine_gun.ammo_type;

		projected_ammo_info.weapon_data.ammo.maxalive_time = w.weapon_data.minimachine_gun.maxalive_time;
		projected_ammo_info.weapon_data.ammo.gravity = w.weapon_data.minimachine_gun.gravity;
		projected_ammo_info.weapon_data.ammo.range = w.weapon_data.minimachine_gun.range;
		projected_ammo_info.weapon_data.ammo.damage = w.weapon_data.minimachine_gun.damage;
		projected_ammo_info.weapon_data.ammo.dmg_modify_timer_min = w.weapon_data.minimachine_gun.dmg_modify_timer_min;
		projected_ammo_info.weapon_data.ammo.dmg_modify_timer_max = w.weapon_data.minimachine_gun.dmg_modify_timer_max;
		projected_ammo_info.weapon_data.ammo.dmg_modify_min = w.weapon_data.minimachine_gun.dmg_modify_min;
		projected_ammo_info.weapon_data.ammo.dmg_modify_max = w.weapon_data.minimachine_gun.dmg_modify_max;
		projected_ammo_info.weapon_data.ammo.capsule_height = w.weapon_data.minimachine_gun.capsule_height;
		projected_ammo_info.weapon_data.ammo.capsule_radius = w.weapon_data.minimachine_gun.capsule_radius;
	}

	if(w.weapon_data.weapon.type == kWeaponTypeGunTowerBuilder)
	{
		gun_tower_hand_info = w;
	}
}

// get cur weapon info
Weapon& Client::GetCurWeaponInfo()
{
	static Weapon default_weapon;
	
	Weapon *pWeapon = GetWeaponInfo(m_curweapon);
	if (!pWeapon)
	{
		default_weapon = Weapon();
		
		DEBUGLOG_WRITE("GetCurWeaponInfo() invalid weapon[%d], name : %s", m_curweapon, GetCharacterInfo().character_name);
		
		return default_weapon;
	}
		
	return *pWeapon;
}

// get weapon info
Weapon* Client::GetWeaponInfo(byte val)
{
	PackInfo &pack = GetCurPackInfo();
	std::map<byte, Weapon>::iterator itr = pack.weapon_set.find(val);
	
	if (itr == pack.weapon_set.end())
		return NULL;
	
	return &itr->second;
}

// replace weapon
void Client::ReplaceWeapon(byte slot, Weapon &weapon)
{
	PackInfo &pack = GetCurPackInfo();
	std::map<byte, Weapon>::iterator itr = pack.weapon_set.find(slot);
	
	if (itr == pack.weapon_set.end())
	{
		pack.weapon_set.insert(std::pair<byte, Weapon>(slot, weapon));
	}
	else
	{
		effect.ChangeBaseItemInfo(itr->second.base_info, false, false);
		
		itr->second = weapon;
	}
	
	effect.ChangeBaseItemInfo(weapon.base_info, true, false);
}

// add weapon
bool Client::RemoveWeapon(byte val)
{
	PackInfo &pack = GetCurPackInfo();
	std::map<byte, Weapon>::iterator itr = pack.weapon_set.find(val);
	
	if (itr == pack.weapon_set.end())
		return false;
	
	effect.ChangeBaseItemInfo(itr->second.base_info, false, false);
	
	pack.weapon_set.erase(itr);
	
	return true;
}

// get skilleffect
SkillEffect& Client::GetSkillEffect()
{
	return effect;
}

// damage after armor
float Client::DamageAfterResistance(float damage, int weapon_type, byte from_uid)
{
	// 自杀，摔伤不减伤
	if (weapon_type == kWeaponTypeSelfHurt)
		return damage;
	
	const float damage_raw = damage;
	const float armor_raw = armor;
	
	// calc armor
	if (armor >= damage)
	{
		damage = 0;
		armor -= damage;
	}
	else
	{
		damage -= armor;
		armor = 0;
	}
	
	float resistance = 0;
	int damage_type = GetWeaponDamageType(weapon_type);
	
	effect.SumEffect(kEffect_Infect_ResistanceAll, resistance);
	switch (damage_type)
	{
		case kWeaponDamageFlame:
			effect.SumEffect(kEffect_Infect_ResistanceFlame, resistance);
			resistance += resistance_flame;
			break;
		case kWeaponDamageExplode:
			effect.SumEffect(kEffect_Infect_ResistanceExplode, resistance);
			resistance += resistance_explode;
			break;
		case kWeaponDamageBullet:
			effect.SumEffect(kEffect_Infect_ResistanceBullet, resistance);
			resistance += resistance_bullet;
			break;
		case kWeaponDamageClose:
			effect.SumEffect(kEffect_Infect_ResistanceClose, resistance);
			resistance += resistance_close;
			break;
			
		default:
			break;
	}
	
	if (from_uid == uid)
	{
		// 自伤
		effect.SumEffect(kEffect_Infect_SelfResistanceAll, resistance);
		
		switch (damage_type)
		{
			case kWeaponDamageFlame:
				effect.SumEffect(kEffect_Infect_SelfResistanceFlame, resistance);
				break;
			case kWeaponDamageExplode:
				effect.SumEffect(kEffect_Infect_SelfResistanceExplode, resistance);
				break;
			case kWeaponDamageBullet:
				effect.SumEffect(kEffect_Infect_SelfResistanceBullet, resistance);
				break;
			case kWeaponDamageClose:
				effect.SumEffect(kEffect_Infect_SelfResistanceClose, resistance);
				break;
				
			default:
				break;
		}
	}
	
	Client *from_c = server.GetClient(from_uid);
	if (from_c)
	{
		switch (from_c->GetCurCharinfo().career_id)
		{
			case 1:
				effect.SumEffect(kEffect_Infect_CareerResistance_Id1, resistance);
				break;
			case 2:
				effect.SumEffect(kEffect_Infect_CareerResistance_Id2, resistance);
				break;
			case 3:
				effect.SumEffect(kEffect_Infect_CareerResistance_Id3, resistance);
				break;
			case 4:
				effect.SumEffect(kEffect_Infect_CareerResistance_Id4, resistance);
				break;
			case 5:
				effect.SumEffect(kEffect_Infect_CareerResistance_Id5, resistance);
				break;
			case 6:
				effect.SumEffect(kEffect_Infect_CareerResistance_Id6, resistance);
				break;
			case 7:
				effect.SumEffect(kEffect_Infect_CareerResistance_Id7, resistance);
				break;
				
			default:
				break;
		}
	}
	
	float v_tmp = 0;
	if (effect.SumEffect(kEffect_Infect_HpInfectResistance, v_tmp))
	{
		float lost_hp = Max(max_health - health, 0);
		resistance += v_tmp * (lost_hp / max_health) * 100.f;
	}
	
	float resistance_old = resistance;
	resistance = ((100 + 100 * resistance) * (1 + newResistance) - 100) / 100.f;
	/*
	抗性伤害公式：
	如果抗性>0，则伤害衰减=1/(1+抗性/100)
	如果抗性<=0，则伤害衰减=1-抗性/100
	注：抗性值为1表示1%
	*/
	if (resistance > 0)
		resistance = 1.f / (1.f + resistance);
	else
		resistance = 1.f - resistance;
	
	float damage2 = damage * resistance;
	if (damage2 < 0)
		damage2 = 0;
	
	DEBUGLOG_WRITE("DamageAfterResistance() name : %s, newResistance : %f, resistance_old : %f, resistance : %f, damage_raw : %f, damage : %f, damage2 : %f", 
				GetCharacterInfo().character_name, newResistance, resistance_old, resistance, damage_raw, damage, damage2);
	
	return damage2;
}

// take damage
bool Client::TakeDamage(Client* from, int* damage, int part, const Weapon &weapon, bool boost, bool sustainhurt)
{
	if (health > 0)
	{
		// calc armor
		*damage = (int)fmaxf(DamageAfterResistance(*damage, weapon.weapon_data.weapon.type, from ? from->uid : 0), 0);
		if (*damage > health)
			*damage = health;

		if(this->GetCharacterInfo().character_id != from->GetCharacterInfo().character_id && server.game_type == kTDMode)
		{
			from->alldamage+=*damage;
		}
		// 伤害吸收
		float t_value = 1.0f;
		if (GetSkillEffect().SumEffect(kEffect_Infect_HurtAbsorb, t_value) )
		{
			t_value = Clamp(t_value, 0.0f, 1.0f);
			SkillData* t_skill = GetSkillEffect().GetSkillData(kCharacterSkill_EnergyShield);
			if (t_skill)
			{
				t_skill->data[0] = *damage * (1-t_value);
			}
		}
		*damage *= t_value;
		health -= *damage;

		switch(server.game_type)
		{
		case Game::kBossMode:
			{
				if(team == 1)
				{
					server.boss_life_gold_count += *damage;
					if (server.boss_life_gold_count > server.config.life_each_boss_gold && from) 
					{
						server.boss_life_gold_count -= server.config.life_each_boss_gold;
												
						server.AddDropGoldWithSpeed(position, from->position, kSupplyGoldWithForce,150, uid, 0);
					}
				}
			}
			break;
		case Game::kNovice:
			{
				if(health < 1)
				{
					health = 1;
				}
			}
			break;
		case Game::kZombieMode:
			{

				if(!server.special_person_flashed)
				{
					return true;
				}
				else
				{
					if(from && from != this)
					{
						EffectData effectdata;

						effectdata.duration_timer = 1.f;//powf(*damage, 0.8f) / 50;
						effectdata.type = kEffect_Infect_MoveSpeed;
						effectdata.value = team == 0 ? -0.20f : -0.6f;
						effectdata.enable = true;

						effect.ApplySystemEffect(effectdata);					
					}

				}

				if(team == 0)
				{
					//drop gold
					if(!sustainhurt)
						server.AddZombieDropGold(position + Vector3 (0, 3, 0));

					//dying state
					if(!zombie_mode_dying_state && health < 1)
					{
						byte from_id = 0;
						if(from)
						{
							from_id = from->uid;
						}
						ZombieDyingState(from_id);
						return true;
					}
				}
			}
			break;
		case Game::kCommonZombieMode:
			{
				if (!is_zombie_super)
				{
					if (team == 1)
					{
						if ( weapon.weapon_data.weapon.type == kWeaponTypeAmmoMeditNeedle )
						{
							SetCommonZombieEnergy(-20);
						}
						else if (weapon.weapon_data.weapon.type == kWeaponTypeAmmoBloodDisk)
						{
							SetCommonZombieEnergy(-4);
						}
						else
						{
							//SetCommonZombieEnergy(*damage * (0.1f));
						}
						if (!zombie_unable_state && zombie_unable_state_flag && health < 1 && is_zombie_king && common_zombie_level == 0 && buff_zombie_id == -1)
						{
							CommonZombieUnableState();
							return true;
						}
					}
				}
			}
			break;
		case Game::kSurvivalMode:
			{
				if (health < 1 && CanBeGhost())
				{
					health = 1;
					ChangeGhost(from, &weapon, part, boost, sustainhurt);
				}
			}
		}

		if (from)
		{
			effect.OnTakeDamage(*from);
			// 持续型伤害不计算OnClientHit（会导致循环应用技能）
			if (!sustainhurt)
				from->effect.OnClientHit(*this, weapon.base_info);
			
			from->accumulate_damage += *damage;

			if(server.game_type == kCommonZombieMode)
			{
				if (this != from )
				{
					if (from->team == 1)
					{
						if (!is_zombie_super)
							from->SetCommonZombieEnergy(200);	
					}
					else
						from->SetHumanEnergy(*damage);
				}
			}
			
			if(server.game_type == kBossMode2)
			{
				if (this != from && 
					this->GetCurCharinfo().career_id == server.level_info.boss_info.career_id &&
					from->GetCurCharinfo().career_id != server.level_info.boss_info.career_id && 
					from->GetCurCharinfo().career_id != server.level_info.bosspve_info[0].career_id && 
					from->GetCurCharinfo().career_id != server.level_info.bosspve_info[1].career_id && 
					from->GetCurCharinfo().career_id != server.level_info.bosspve_info[2].career_id && 
					from->GetCurCharinfo().career_id != server.level_info.bosspve_info[3].career_id)
				{
					from->boss2_human_energy += server.config.boss2_human_energy_up_speed * (*damage);
					if (from->boss2_human_energy >= from->boss2_human_energy_max)
					{
						from->boss2_human_energy -= from->boss2_human_energy_max;
						from->boss2_human_energy_max += server.config.boss2_human_energy_max_add;
						from->boss2_human_energy_level++;
						
						int rand_0 = rand() % 4;
						from->boss2_passiveskill_level[rand_0]++;
						if (from->boss2_passiveskill_level[rand_0] >= 9)
							from->boss2_passiveskill_level[rand_0] = 9;
						
						int rand_1 = rand() % 4 + 1;
						from->boss2_initiative_type = rand_1;
						
						from->Boss2DoPassiveSkill();
					}
					
					from->Boss2SyncData();
				}
			}
			
			if(server.game_type == kItemMode)
			{
				if (this != from && 
					from->GetCurCharinfo().career_id != server.level_info.boss_info.career_id)
				{
					from->itemmode_energy += server.config.itemmode_energy_up_speed * (*damage);
					if (from->itemmode_energy >= from->itemmode_energy_max)
					{
						from->itemmode_energy -= from->itemmode_energy_max;
						
						//if (from->itemmode_item_slot == -1)
						{
							int item[] = {0,1,2,3,4,5,6,10,12,13,25};
							from->itemmode_item_slot = item[rand() % elementsof(item)];
						}
					}
					
					from->ItemModeSyncData();
				}
			}
		}

		// data collect
		if (from && from != this && from->team != team && part < kCharacterPartCount)
		{
			from->data.ScoreDataHit(from->GetCurCharinfo(), weapon.weapon_data.weapon.type, *damage, part);
		}

		if (health == 0)
		{
			OnDied(from);
			Client *assistkiller = NULL;

			if (from)
			{
				if(server.game_type == kBossMode2)
				{
					if (this != from && 
						this->GetCurCharinfo().career_id != server.level_info.boss_info.career_id &&
						from->GetCurCharinfo().career_id == server.level_info.boss_info.career_id)
					{
						from->boss2_defence_energy += server.config.boss2_defence_energy_kill_add;
						if (from->boss2_defence_energy >= from->boss2_defence_energy_max)
						{
							from->boss2_defence_energy -= from->boss2_defence_energy_max;
							from->boss2_defence_energy_max += server.config.boss2_defence_energy_max_add;
							from->boss2_defence_energy_level++;
							
							for (uint i = 0; i < max_client_count; i++)
							{
								Client * c = server.clients + i;
								
								if (c->IsReady() && c->IsAlive() && c->team == 1)
								{
									c->Boss2DoDefenceUp();
								}
							}
						}
					
						from->Boss2SyncData();
					}
				}
				
				if(server.game_type == kItemMode)
				{
					// if (this != from && 
						// from->GetCurCharinfo().career_id == server.level_info.boss_info.career_id && 
						// weapon.weapon_data.weapon.type == kWeaponTypeAmmoRocket)
					// {
					// }
				}
			
				if(from == this)
				{
					Client *pclient = NULL;
					if (assist_uid[0] == from->uid)
					{
						pclient = server.GetClient(assist_uid[1]);
					}
					else
					{
						pclient = server.GetClient(assist_uid[0]);
					}
					
					if (pclient && pclient->uid != from->uid)
					{
						log_write(LOG_DEBUG5,"calc sucide score by assist ");
						
						int tmp_part = kCharacterPartTorso;
						pclient->OnKill(this, tmp_part, weapon, 0);
						assist_uid[0] = assist_uid[1] = pclient->uid;
						assistkiller = pclient;
					}
					else
					{
						log_write(LOG_DEBUG5,"sucide by self");
						
						from->OnKill(this, part, weapon, 0, boost, sustainhurt);
					}
				}
				else
				{
					Client *pclient = server.GetClient(from->recover_assist_uid);
					if(!pclient)
					{
						if(assist_uid[0] == from->uid)
						{
							pclient = server.GetClient(assist_uid[1]);
						}
						else
						{
							pclient = server.GetClient(assist_uid[0]);
						}
						
						if(pclient)
							assist_uid[0] = assist_uid[1] = pclient->uid;
						else
							assist_uid[0] = assist_uid[1] = 0;
					}
					else
					{
						assist_uid[0] = assist_uid[1] = from->recover_assist_uid;
					}
					
					if(pclient && pclient->uid != from->uid && pclient->uid != uid)
					{
						log_write(LOG_DEBUG5,"calc score by killer and assist");
						from->OnKill(this, part, weapon, pclient->uid, boost, sustainhurt);
					}
					else
					{
						log_write(LOG_DEBUG5,"calc score by killer no assist");
						from->OnKill(this, part, weapon, 0, boost, sustainhurt);
					}
				}
				//
				if(assistkiller)
					server.OnTeamKillsChanged(assistkiller->team);
				else
					server.OnTeamKillsChanged(from->team);
			}
			server.OnTeamAliveChanged(team);
		}
		else if (from)
		{
			if( from->uid != uid && from->team != team)
			{
				if( assist_uid[0] == from->uid)
				{
					assist_timer[0] = 10.0f;
				}
				else
				{
					if(assist_uid[1] == assist_uid[0])
					{
						//第一次
						assist_uid[0] = from->uid;
						assist_timer[0] =  10.0f;
					}
					else
					{
						assist_uid[1] = assist_uid[0];
						assist_timer[1] = assist_timer[0];

						assist_uid[0] = from->uid;
						assist_timer[0] = 10.0f;
					}
				}
			}
		}
		
		OnAction();

		return true;
	}
	
	return false;
}

// game end
void Client::GameEnd(byte team)
{
	m_bRequestLeaved = true;
	if (IsConnected())
	{
		BeginWrite();
		WriteByte(SM_GameEnd);
		WriteByte(team);
		EndWrite();
	}
}

// spawn
void Client::Spawn(const Transform & transform, Weapon* bomb, bool round_start, bool has_bomb, bool doit)
{
	if (team > 1)
		return;
		
	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;
	ischarge_skill = false;
	charge_skill_time = -1;
	charge_skill_alltime = -1;
	charge_skill_cd = -1;
	itemmode_skill_cd = -1;
	armor = 0;
	max_armor = 0;
	zombie_skill_cd = 0.f;
	input_limit_timer = INPUT_LIMIT_TIME;
	drinkrecover = 0;
	drinkrecovercount = -1;
	can_use_spawn_coin = true;
	isghost = false;

	if (server.game_type == Game::kItemMode && server.is_hahatime)
	{
		target_career = server.level_info.boss_info.career_id;
		doit = true;
	}
	if (server.game_type == Game::KMoonMode && ismoonboss && ismoonbossflag == false)
	{
		target_career = server.level_info.boss_info.career_id;
		doit = true;
		ismoonboss = false;
		ismoonbossflag = true;
	}
	
	if((server.game_type == Game::kBossMode2 || server.game_type == Game::kItemMode || server.game_type == Game::kTDMode || server.game_type == Game::KMoonMode) && doit)
		SetCurCharInfo(target_career, true);
	else
		SetCurCharInfo(target_career);
	
	SingleCharacterInfo& current_character = GetCurCharinfo();
	
	float health_scale = GetScale(server.level_info.health_scale);
	max_health_base = max_health_unscale * health_scale;
	ex_health_base = ex_health_unscale * health_scale;
	

	server.DestoryDummyByOwner(uid);
	{
		effect.Initialize();
		if(server.game_type == Game::kStreetBoyMode)
		{
			EffectData effectdata;

			effectdata.duration_timer = 2.f;
			effectdata.type = kEffect_Invincible;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);
		}
		else if(server.game_type == Game::kZombieMode)
		{
			EffectData effectdata;

			effectdata.duration_timer = 10.f;
			effectdata.type = kEffect_Invincible;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);
		}
		else
		{
			EffectData effectdata;

			effectdata.duration_timer = 5.f;
			effectdata.type = kEffect_Invincible;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);
		}
		
		if(server.game_type == Game::kBossPVE)
		{
			EffectData effectdata;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_AmmoFlySpeed;
			effectdata.value = 1.0f;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);
		}

		if (server.game_type == Game::kBossMode2)
		{
			if (current_character.career_id != server.level_info.boss_info.career_id && 
				current_character.career_id != server.level_info.bosspve_info[0].career_id && 
				current_character.career_id != server.level_info.bosspve_info[1].career_id && 
				current_character.career_id != server.level_info.bosspve_info[2].career_id && 
				current_character.career_id != server.level_info.bosspve_info[3].career_id)
			{
				Boss2DoPassiveSkill();
			}
		}
	}

	data.ScoreDataRebirth(current_character.career_id, true);
	uint s_rand = rand();
	rand_fun.Srand(s_rand);

	ResetAllCure();
	
	boss2_strange_spawn_use = false;
	boss2_strange_spawn = -1;

	health = max_health;
	sync_flags = 0;

	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;

	spawn_time = 0;
	spawn_timing = false;
	
	state = CS_Alive;
	spawn_confirm = false;
	playing = true;
	connected = true;

	position = transform.position;
	rotation.x = 0;
	rotation.y = cosf(3.1415926f / 180.f * transform.rotation * 0.5f);
	rotation.z = 0;
	rotation.w = sinf(3.1415926f / 180.f * transform.rotation * 0.5f);

	cure_list.clear();

	server.OnTeamAliveChanged(team, round_start);
	
	switch (server.game_type)
	{
	case Game::kBombMode:
		{
			if (bomb && team == 1)
			{
				ReplaceWeapon(BOMB_SLOT, *bomb);
			}
		}
		break;
	case Game::kStreetBoyMode:
		{
			Weapon w;
			for (byte i = 0; i < 3; i++)
			{
				Weapon *pWeapon = GetWeaponInfo(i);
				if (pWeapon && pWeapon->weapon_data.weapon.type == kWeaponTypeKnife)
					w = *pWeapon;
				
				RemoveWeapon(i);
			}
			
			ReplaceWeapon(0, w);
		}
		break;
	case Game::kCommonZombieMode:
		{
			GetCharacterItemLevel(GetCurCharinfo(), itemlevel_a, itemlevel_b, itemlevel_c, itemlevel_d);
		}
		break;
	case Game::kSurvivalMode:
		{
			EffectData effectdata;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Sustain_Survival_Mode;
			effectdata.value = 0.0075f;
			effectdata.interval = 1.f;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);
		}
		break;
	}

	// broadcast player spawn
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			server.ForwardClientMessage(*c);
			c->BeginWrite();
			c->WriteByte(SM_PlayerSpawn);
			c->WriteByte(uid);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteInt(armor);
			c->WriteInt(s_rand);
			c->WriteVector3(transform.position);
			c->WriteFloat(transform.rotation);
			WriteSingleCharacter(*c, current_character);
			c->WriteByte(has_bomb ? 1 : 0);
			c->WriteInt(die_counter);
			c->WriteInt(die_buff_counter);
			//c->WriteByte(pack_index);
			//WritePackInfo(*c, pack);
			c->EndWrite();
			server.ForwardClientMessage(*c);
			
			// 修复SM_PlayerSpawn与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
		}
	}

	if (server.game_type == Game::kBossMode && server.special_person_flashed)
	{
		int bossmode_alive = server.GetBossAliveNumber() - 1;
		bossmode_alive = bossmode_alive < 0 ? 0 :bossmode_alive;
		server.SetBossAliveNumber(bossmode_alive);

		server.NotifyBossAliveChanged();

		server.CheckRoundEnd();
	}
	else if (server.game_type == Game::kBossMode2)
	{
		Boss2SyncData();
	}
	else if (server.game_type == Game::kItemMode)
	{
		if (round_start)
		{
			itemmode_energy = 0;
			itemmode_energy_max = server.config.itemmode_energy_max_init;
		}
		
		itemmode_item_slot = -1;
		itemmode_zibao = false;
		
		ItemModeSyncData();
	}
	else if (server.game_type == Game::kSurvivalMode)
	{
		AmmoDisappear();

		EffectData effectdata;

		effectdata.duration_timer = 0.5f;
		effectdata.type = kEffect_Sustain_AmmoRecover;
		effectdata.value = 20.f;
		effectdata.interval = 1.f;
		effectdata.enable = true;

		effect.ApplySystemEffect(effectdata);
	}

	ClearCurePower();
	zombie_mode_dying_state = false;
	human_rebirth_counter = 0;
}

// set team
void Client::SetTeam(byte t)
{
	if (team > 1)
		return;

	team = t;
	// broadcast player set team
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_PlayerSetTeam);
			c->WriteByte(uid);
			c->WriteByte(team);
			c->EndWrite();
		}
	}
}

// recover
bool Client::Recover(int hp, byte recover_type, byte from, float userdata)
{
	if (!IsReady())
		return false;

	if (!IsAlive())
		return false;

	if (health > 0)
	{
		if (recover_type == kRecoverMinusMax)
		{
			hp = health - hp;
			if (health < max_health)
			{
				return false;
			}
			else if (hp < max_health)
			{
				hp = max_health;
			}
		}
		else if (recover_type == kRecoverBaseHp)
		{
			effect.CancelSustainHurt();
			
			if (health < max_health)
				hp = max_health;
			else
				return false;
		}
		else if (recover_type == kRecoverSupplyHp ||
				recover_type == kRecoverSelf ||
				recover_type == kRecoverVehicle)
		{
			if (recover_type == kRecoverSupplyHp)
			{
				effect.CancelSustainHurt();
				
				num_healthrecover++;
				
				float value = 0;
				if (effect.SumEffect(kEffect_Infect_SupplyHpRecover, value))
				{
					float hp_add = hp * value;
					hp = Max((int)(hp + hp_add), 0);
					
					DEBUGLOG_WRITE("Recover(kRecoverSupplyHp) name : %s, value : %f", 
									GetCharacterInfo().character_name, value);
				}
			}
			
			if (recover_type == kRecoverSelf)
			{
				float value = 0;
				if (effect.SumEffect(kEffect_Infect_SelfHpRecover, value))
				{
					float hp_add = hp * value;
					hp = Max((int)(hp + hp_add), 0);
					
					DEBUGLOG_WRITE("Recover(kRecoverSelf) name : %s, value : %f", 
									GetCharacterInfo().character_name, value);
				}
			}
			
			hp = health + hp;
			
			if (health > max_health)
			{
				hp = health;
			}
			else
			{
				hp = Min(Max(hp, 0), max_health);
			}
		}
		else if (recover_type == kRecoverCuregun)
		{
			float value = 0;
			if (effect.SumEffect(kEffect_Infect_CureHpRecover, value))
			{
				float hp_add = hp * value;
				hp = Max((int)(hp + hp_add), 0);
				
				DEBUGLOG_WRITE("Recover(kRecoverCuregun) name : %s, value : %f", 
								GetCharacterInfo().character_name, value);
			}
		
			int addnum = 0;
			{
				addnum = hp;
				hp = health + addnum;
			}

			Client * from_c = server.GetClient(from);
			addnum = hp >= ex_health ? 0 : addnum;
			if (from_c && addnum > 0)
				from_c->data.ScoreDataHealth(from_c->GetCurCharinfo(),addnum);

			hp = Min(Max(hp, 0), ex_health);
			
			cure_limit_timer = 3.0f;
			
			recover_assist_timer = 3.0f;
			recover_assist_uid = from;
		}
		else if(recover_type == kRecoverNovice)
		{
			hp = (int)(0.2f * max_health);
		}
		else if (recover_type == kRecoverWaitAddBlood)
		{
			if (health < max_health)
			{
				hp += health;
				hp = hp > max_health ? max_health : hp;
			}
			else
				return false;
		}

		health = hp;

		// broadcast player set team
		for (uint i = 0; i < max_client_count; i++)
		{
			Client * c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_HealthRecover);
				c->WriteByte(uid);
				c->WriteInt(health);
				c->WriteByte(recover_type);
				c->WriteByte(from);
				c->WriteFloat(userdata);
				c->EndWrite();
			}
		}
		return true;
	}
	return false;
}

// sustain hurt
bool Client::TakeSustainHurt(Client *from, int damage, byte hurttype)
{
	if (!IsReady())
		return false;
	
	if (!IsAlive())
		return false;
	
	bool team_hurt = server.team_hurt;
	if (!from)
	{
		from = this;
		team_hurt = true;
		
		DEBUGLOG_WRITE("TakeSustainHurt() name : %s, No From!!!", GetCharacterInfo().character_name);
	}
	
	if (from && from->CanHurt(this, team_hurt) && health > 0)
	{
		int damage_tmp = damage;
		Weapon w;
		w.base_info.sid = 0;
		w.weapon_data.weapon.type = kWeaponTypeNone;
		
		if (!TakeDamage(from, &damage_tmp, kCharacterPartTorso, w, false, true))
		{
			return false;
		}
		
		// broadcast player shoot
		for (uint i = 0; i < max_client_count; i++)
		{
			Client* c = server.clients + i;

			if (c->IsReady())
			{
				c->BeginWrite();
				c->WriteByte(SM_PlayerSustainHurt);
				c->WriteByte(uid);
				c->WriteByte(from->uid);
				c->WriteByte(hurttype);
				c->WriteInt(from->data.GetScore());
				c->WriteInt(health);
				if (health == 0 || (health ==1 && isghost))
				{
					WriteWeapon(*c,  w);
					Client *client = server.GetClient(assist_uid[0]);

					//sucide and have assist attacked
					if(from->uid == uid && client && assist_uid[0] != uid)
					{
						c->WriteShort(client->num_kill);
					}
					else
					{
						c->WriteShort(from->num_kill);
					}

					c->WriteShort(num_die);
					c->WriteByte(assist_uid[0]);

					if(client)
					{
						c->WriteInt(client->data.GetScore());
						c->WriteShort(client->assist_num);
					}
					else
					{
						c->WriteInt(0);
						c->WriteShort(short(0));
					}
				}
				c->EndWrite();
			}
		}
	}

	return true;
}

// sync holdpointinfo
void Client::SyncHoldPointInfo()
{
	if (!IsReady())
		return;

	BeginWrite();
	WriteByte(SM_SyncHoldPointInfo);
	WriteInt((int)server.hold_points.size());
	for (std::vector<HoldPointInfo>::const_iterator point = server.hold_points.begin(); point < server.hold_points.end(); point++)
	{
		WriteByte(point->owner_team);
		WriteByte(point->snatch_team);
		WriteFloat(point->snatching_timer);
	}
	WriteFloat(server.team_timer[0]);
	WriteFloat(server.team_timer[1]);
	WriteByte((byte)server.cur_holdpoint_diffnum);

	WriteInt(data.GetScore());
	EndWrite();
}

void Client::NotifyBossModeAliveNumber(int num)
{
	BeginWrite();
	WriteByte(SM_BossModeAliveChanged);
	WriteInt(num);
	EndWrite();

}
void Client::OnHitBack(const KickInfo& info)
{
	BeginWrite();
	WriteByte(SM_PlayerHitBack);
	WriteKickInfo(*this, info);
	EndWrite();
}

void Client::InitializeVehicleInfo()
{
	BeginWrite();
	WriteByte(SM_InitializePushVehiclePointInfo);

	WriteVector3(server.vehicle_current_info[0].position);
	WriteVector3(server.vehicle_current_info[0].dir);
	WriteVector3(server.vehicle_current_info[1].position);
	WriteVector3(server.vehicle_current_info[1].dir);

	WriteFloat(server.level_info.push_vehicle_total_length[0]);
	WriteFloat(server.level_info.push_vehicle_total_length[1]);

	WriteFloat(server.vehicle_timer_interval);

	EndWrite();
}

void Client::UpdateVehicleInfo()
{
	BeginWrite();
	WriteByte(SM_UpdatePushVehiclePointInfo);

	WriteVector3(server.vehicle_current_info[0].position);
	WriteVector3(server.vehicle_current_info[0].dir);
	WriteVector3(server.vehicle_current_info[1].position);
	WriteVector3(server.vehicle_current_info[1].dir);

	WriteFloat(server.vehicle_current_info[0].total_length);
	WriteFloat(server.vehicle_current_info[1].total_length);
	WriteInt(server.vehicle_current_info[0].sliding);
	WriteInt(server.vehicle_current_info[1].sliding);

	WriteInt(server.vehicle_current_info[0].player_count);
	WriteInt(server.vehicle_current_info[1].player_count);

	WriteVector3(server.vehicle_aabb[0].Min);
	WriteVector3(server.vehicle_aabb[0].Max);
	WriteVector3(server.vehicle_aabb[1].Min);
	WriteVector3(server.vehicle_aabb[1].Max);

	//WriteVector3(server.vehicle_kick_aabb[0].Min);
	//WriteVector3(server.vehicle_kick_aabb[0].Max);
	//WriteVector3(server.vehicle_kick_aabb[1].Min);
	//WriteVector3(server.vehicle_kick_aabb[1].Max);

	WriteFloat(server.vehicle_timer_interval);

	WriteInt(data.GetScore());

	EndWrite();
}

// sync server script value
void Client::SyncServerScriptValue(byte full_syn)
{
	BeginWrite();
	WriteByte(SM_SynServerScriptValue);
	
	WriteByte(full_syn);
	
	if (full_syn)
	{
		WriteInt((uint)server.server_script_stringvalue.size());
		for (std::map<std::string, std::string>::const_iterator itr = server.server_script_stringvalue.begin(); 
			itr != server.server_script_stringvalue.end(); itr++)
		{
			WriteString(itr->first.c_str());
			WriteString(itr->second.c_str());
		}
		
		WriteInt((uint)server.server_script_numbervalue.size());
		for (std::map<std::string, float>::const_iterator itr = server.server_script_numbervalue.begin(); 
			itr != server.server_script_numbervalue.end(); itr++)
		{
			WriteString(itr->first.c_str());
			WriteFloat(itr->second);
		}
	}
	else
	{
		WriteInt((uint)server.server_script_stringvalue_dirty.size());
		for (std::set<std::string>::const_iterator itr = server.server_script_stringvalue_dirty.begin(); 
			itr != server.server_script_stringvalue_dirty.end(); itr++)
		{
			WriteString(itr->c_str());
			
			std::map<std::string, std::string>::const_iterator itr2 = server.server_script_stringvalue.find(*itr);
			if (itr2 != server.server_script_stringvalue.end())
				WriteString(itr2->second.c_str());
			else
				WriteString("");
		}
		
		WriteInt((uint)server.server_script_numbervalue_dirty.size());
		for (std::set<std::string>::const_iterator itr = server.server_script_numbervalue_dirty.begin(); 
			itr != server.server_script_numbervalue_dirty.end(); itr++)
		{
			WriteString(itr->c_str());
			
			std::map<std::string, float>::const_iterator itr2 = server.server_script_numbervalue.find(*itr);
			if (itr2 != server.server_script_numbervalue.end())
				WriteFloat(itr2->second);
			else
				WriteFloat(0);
		}
	}
	
	EndWrite();
}

void Client::NotifyBossFlash()
{
	if (team > 1)
		return;
	
	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;

	armor = 0;
	max_armor = 0;
	
	int player_count = 0;
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady() && c->IsAlive())
		{
			player_count ++;
		}
	}
	
	int a, b, c, d;
	GetCharacterItemLevel(GetCurCharinfo(), a, b, c, d);
	
	SetCurCharInfo(server.level_info.boss_info.career_id, true);
	const SingleCharacterInfo& current_character = GetCurCharinfo();

	if (server.level_info.boss_info.career_id != current_character.career_id)
	{
		log_write(LOG_DEBUG3, "NotifyBossFlash() : can't set boss");

		assert(0);
		
		server.RequestDisconnect();
		
		return;
	}
	
	int addon_hp = (player_count - 10) * 2000;
	addon_hp = addon_hp < 0 ? 0 : addon_hp;

	max_health_base = (int)(max_health_unscale) + addon_hp;
	ex_health_base = (int)(ex_health_unscale) + addon_hp;
	
	effect.Initialize();
	{
		EffectData effectdata;
		
		effectdata.duration_timer = 3.f;
		effectdata.type = kEffect_Invincible;
		effectdata.enable = true;
		
		effect.ApplySystemEffect(effectdata);


		{
			EffectData effectdata;
			EffectData effectdata2;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_Damage;
			effectdata.value = powf(BASE_VALUE, a) - 1.f;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);

			effectdata2.duration_timer = DURATION_INFINITY;
			effectdata2.type = kEffect_Infect_ResistanceAll;
			effectdata2.value = powf((3 * sqrtf(powf(BASE_VALUE, b)) + 1 * sqrtf(powf(BASE_VALUE, c)) + 2 * sqrtf(powf(BASE_VALUE, d))) / 6.f, 2) - 1.f;
			effectdata2.enable = true;

			effect.ApplySystemEffect(effectdata2);
		}
	}
	

	data.ScoreDataRebirth(current_character.career_id, true);
	uint s_rand = rand();
	rand_fun.Srand(s_rand);

	health = max_health;
	
	sync_flags = 0;
	team = 1;

	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;

	spawn_time = 0;
	spawn_timing = false;
	state = CS_Alive;
	spawn_confirm = false;
	playing = true;
	connected = true;

	server.OnTeamAliveChanged(team, false);

	Transform transform;
	transform.position = position;
	transform.rotation = 0;

	int size = server.start_point[team].size();
	if (size > 0)
	{
		transform = server.start_point[team][rand() % size];
	}

	position = transform.position;

	rotation.x = 0;
	rotation.y = cosf(3.1415926f / 180.f * transform.rotation * 0.5f);
	rotation.z = 0;
	rotation.w = sinf(3.1415926f / 180.f * transform.rotation * 0.5f);

	//rotation.SetZXY(Vector3(0, transform.rotation, 0));

	// broadcast player spawn
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Boss_Flash);
			c->WriteByte(uid);
			//setteam
			c->WriteByte(team);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteInt(armor);
			c->WriteInt(s_rand);
			c->WriteVector3(position);
			c->WriteFloat(transform.rotation);
			WriteSingleCharacter(*c, current_character);
			c->EndWrite();
			
			// 修复SM_Boss_Flash与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
		}
	}
	//int addon_player = (player_count - 10) * 2;
	//addon_player = addon_player < 0 ? 0 : addon_player;

	//server.SetBossAliveNumber(22 + addon_player);

	server.SetBossAliveNumber(0);

	server.NotifyBossAliveChanged();

	server.special_person_flashed = true;
}

void Client::NotifBoss2Flash(int spawn_slot)
{
	if (team > 1)
		return;
		
	boss2_strange_spawn_use = false;
	boss2_strange_spawn = -1;
	
	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;

	armor = 0;
	max_armor = 0;
	
	// int player_count = 0;
	// for (uint i = 0; i < max_client_count; i++)
	// {
		// Client * c = server.clients + i;

		// if (c->IsReady() && c->IsAlive())
		// {
			// player_count ++;
		// }
	// }
	
	int a, b, c, d;
	GetCharacterItemLevel(GetCurCharinfo(), a, b, c, d);
	
	SetCurCharInfo(server.level_info.boss_info.career_id, true);
	const SingleCharacterInfo& current_character = GetCurCharinfo();

	if (server.level_info.boss_info.career_id != current_character.career_id)
	{
		log_write(LOG_DEBUG3, "NotifBoss2Flash() : can't set boss");

		assert(0);
		
		server.RequestDisconnect();
		
		return;
	}
	
	// int addon_hp = (player_count - 10) * 2000;
	// addon_hp = addon_hp < 0 ? 0 : addon_hp;
	int addon_hp = 0;

	max_health_base = (int)(max_health_unscale) + addon_hp;
	ex_health_base = (int)(ex_health_unscale) + addon_hp;
	
	effect.Initialize();
	{
		EffectData effectdata;
		
		effectdata.duration_timer = 3.f;
		effectdata.type = kEffect_Invincible;
		effectdata.enable = true;
		
		effect.ApplySystemEffect(effectdata);

		{
			EffectData effectdata;
			EffectData effectdata2;

			effectdata.duration_timer = DURATION_INFINITY;
			effectdata.type = kEffect_Infect_Damage;
			effectdata.value = powf(BASE_VALUE, a) - 1.f;
			effectdata.enable = true;

			effect.ApplySystemEffect(effectdata);

			effectdata2.duration_timer = DURATION_INFINITY;
			effectdata2.type = kEffect_Infect_ResistanceAll;
			effectdata2.value = powf((3 * sqrtf(powf(BASE_VALUE, b)) + 1 * sqrtf(powf(BASE_VALUE, c)) + 2 * sqrtf(powf(BASE_VALUE, d))) / 6.f, 1) - 1.f;
			effectdata2.enable = true;

			effect.ApplySystemEffect(effectdata2);
		}
	}
	

	data.ScoreDataRebirth(current_character.career_id, true);
	uint s_rand = rand();
	rand_fun.Srand(s_rand);

	health = max_health;
	
	sync_flags = 0;
	SetTeam(1);

	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;

	spawn_time = 0;
	spawn_timing = false;
	state = CS_Alive;
	spawn_confirm = false;
	playing = true;
	connected = true;

	server.OnTeamAliveChanged(team, false);

	Transform transform;
	transform.position = position;
	transform.rotation = 0;

	int size = server.start_point[team].size();
	if (spawn_slot >= 0 && spawn_slot < size)
	{
		transform = server.start_point[team][spawn_slot];
	}
	else
	{
		log_write(LOG_DEBUG3, "NotifBoss2Flash() : spawn_slot[%d] error", spawn_slot);
		
		transform = server.start_point[team][0];
	}

	position = transform.position;

	rotation.x = 0;
	rotation.y = cosf(3.1415926f / 180.f * transform.rotation * 0.5f);
	rotation.z = 0;
	rotation.w = sinf(3.1415926f / 180.f * transform.rotation * 0.5f);
	
	//log_write(LOG_DEBUG3, "NotifBoss2Flash() : position[%f,%f,%f] %d", position.x, position.y, position.z, spawn_slot);

	//rotation.SetZXY(Vector3(0, transform.rotation, 0));

	// broadcast player spawn
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_Boss2_Flash);
			c->WriteByte(uid);
			//setteam
			c->WriteByte(team);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteInt(armor);
			c->WriteInt(s_rand);
			c->WriteVector3(position);
			c->WriteFloat(transform.rotation);
			WriteSingleCharacter(*c, current_character);
			c->EndWrite();
			
			// 修复SM_Boss2_Flash与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
		}
	}
}

void Client::NotifyBoss2Showtime()
{
	if (server.game_type == kBossMode2)
	{
		if (team == 0)
		{
		}
		else if (team == 1)
		{
		}
		
		BeginWrite();
		WriteByte(SM_Boss2_Showtime);
		WriteByte(uid);
		EndWrite();
	}
}

// on client connected
void Client::OnConnected()
{
	ignore_checkcheat = true;
	checkcheat_num = 0;
	checkcheat_delta = 0.f;
	checkcheat_cleantimer = 0.f;

	num_kill = 0;
	num_die = 0;
	num_healthrecover = 0;
	num_ammorecover  = 0;
	control_num = 0;
	revenge_num = 0;
	assist_num = 0;
	knife_kill = 0;
	input_limit_timer = INPUT_LIMIT_TIME;

	drinkrecover = 0;
	drinkrecovercount = -1;
	recv_offset = 0;
	send_offset = 0;
	health = 0;
	max_health = 0;
	ex_health = 0;
	ping = 0;
	status = 0;
	sync_flags = 0;
	sync_time = 0;
	UpdateMagic();

	died_camera_time = 3.5f;
	position.x = 0;
	position.y = 0;
	position.z = 0;
	position_last.x = 0;
	position_last.y = 0;
	position_last.z = 0;
	rotation.x = 0;
	rotation.y = 0;
	rotation.z = 0;
	rotation.w = 1;
	playing = false;
	connected = false;

	target_career = 0;
	zombie_default_career = 0;

	is_voted = false;
	
	pickup_dropitems.clear();

	//spray_count = 0;
	//spray_count_used = 0;
	//spray_info.pid = 0;
	//spray_info.path[0] = 0;
	//spray_info.count = 0;
	
	// use buff item
	spray_index = 0.0f;
	for (std::vector<BuffItem>::const_iterator i = GetCharacterInfo().item_set.begin(); 
		i < GetCharacterInfo().item_set.end(); i++)
	{
		if (i->id == kBuffIdRoom)
		{
			switch (i->type)
			{
				case kBuffTypeSpray:
					{
						spray_index = i->value;
					}
					break;
			}
		}
	}
	
	accumulate_damage = 0;

	state = CS_Connected;

	start_time = time(NULL);

	// spawn confirm
	spawn_confirm = false;

	// response game enter
	BeginWrite();
	WriteByte(SM_ResponseGameEnter);
	WriteInt(kErrorNone);
	EndWrite();

	// on client connected
	server.OnClientStateChanged();

	log_write(LOG_DEBUG4, "OnConnected client uid : %d, name : %s, Authentication", uid, GetCharacterInfo().character_name);

	// authentication success
	BeginWrite();
	WriteByte(SM_Authentication);
	WriteString(server.level_info.name);
	WriteByte(server.level_info.is_use_normal_weapon);
	WriteByte(server.game_type);
	WriteInt(server.rule_value);
	
	WriteRoomOption(*this, server.room_option);

	switch (server.game_type)
	{
	case Game::kHoldPoint:
		{
			WriteInt((int)server.hold_points.size());

			for (std::vector<HoldPointInfo>::const_iterator point = server.hold_points.begin(); point < server.hold_points.end(); point++)
			{
				WriteByte(point->owner_team);
				WriteByte(point->snatch_team);
				WriteFloat(point->snatching_timer);

				WriteVector3(point->aabb.Min);
				WriteVector3(point->aabb.Max);
			}

			WriteFloat(server.team_timer[0]);
			WriteFloat(server.team_timer[1]);
			WriteByte((byte)server.cur_holdpoint_diffnum);
		}
		break;
	case Game::kBombMode:
		{
			WriteInt((int)server.bomb_aabb_area.size());
			for (std::vector<AxisAlignedBox>::const_iterator point = server.bomb_aabb_area.begin(); point < server.bomb_aabb_area.end(); point++)
			{
				WriteVector3(point->Min);
				WriteVector3(point->Max);
			}
		}
		break;
	case Game::kZombieMode:
		{
			
		}
		break;
	case Game::kPushVehicle:
	case Game::kNovice:
		{

		}
		break;
	case Game::kTeamDeathMatch:
		{

		}
		break;
	}

	// level weapon preload
	int size = server.level_info.weapon_set.size();
	WriteInt(size);
	for (std::vector<Weapon>::const_iterator weapon = server.level_info.weapon_set.begin(); weapon < server.level_info.weapon_set.end(); weapon++)
		WriteWeapon(*this, *weapon);

	WriteByte(uid);
	WriteFloat(appcfg.network_delay);
	EndWrite();

	// send player info
	SendCharacterInfo(*this);

	// send bot info
	for (uint i = 0; i < max_botclient_count; ++i)
	{
		Client* c = server.bot_clients + i;
		if (c != this && (c->IsConnected() && c->state > CS_Connected))
		{
			SendCharacterInfo(*c);
		}
	}

	// send character info
	for (uint i = 0; i < max_client_count; ++i)
	{
		Client* c = server.clients + i;
		if (c != this && (c->IsConnected() && c->state > CS_Connected))
		{
			SendCharacterInfo(*c);
		}
	}

	// broadcast client join
	for (uint i = 0; i < max_client_count; i ++)
	{
		Client* c = server.clients + i;
		if (c != this && (c->IsConnected() && c->state > CS_Connected))
		{
			c->SendCharacterInfo(*this);
		}
	}

	// log player join
	log_write(LOG_DEBUG1, "client uid : %d, name : %s, client joined", uid, GetCharacterInfo().character_name);

	// send dropped weapon
	for (DroppedWeapon* weapon = server.dropped_weapon.Begin(); weapon < server.dropped_weapon.End(); weapon++)
	{
		if (weapon && weapon->IsActive())
			AddDroppedWeapon(weapon);
	}
	// send dropped supply
	for (DroppedSupply * supply = server.dropped_supply.Begin(); supply < server.dropped_supply.End(); supply++)
	{
		if (supply && supply->IsActive())
			AddDropSupply(supply);
	}
	// loading
	BeginWrite();
	WriteByte(SM_Loading);
	EndWrite();

	if(server.game_type == Game::kPushVehicle || server.game_type == Game::kNovice)
	{
		InitializeVehicleInfo();
	}

	data.Initialize(*this);

	// change to loading state
	state = CS_Loading;

	if (team < 2)
	{
		bool ispass = true;
		for (int i = 0; i < server.team_ids[team].size();++i)
		{
			if (server.team_ids[team][i] == id)
			{
				ispass = false;
				break;
			}
		}
		if (ispass)
		{
			server.team_ids[team].push_back(id);
		}
	}
	
	if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
	{
		log_write(LOG_DEBUG1, "%s, %s, matching_level_check name : %s, match level : %d, team : %d", __FILE__, __FUNCTION__, GetCharacterInfo().character_name, GetCharacterInfo().nMatchingLevel, team);
	}
}

void Client::OnDisconnected()
{
	log_write(LOG_DEBUG4, "client uid : %d, name : %s, client leave", uid, GetCharacterInfo().character_name, uid_in_room);

	if (IsAlive())
	{
		server.OnTeamAliveChanged(team);
	}

	uint client_count = 0;
	if (leavereason == kLeaveGameReasonIdle)
	{
		log_write(LOG_ERROR, "client disconnected reason : %d, name : %s", leavereason, GetCharacterInfo().character_name);
	}
	leavereason = kLeaveGameReasonNone;
	input_limit_timer = -1;
	drinkrecover = 0;
	drinkrecovercount = -1;
	playing = false;
	connected = false;

	level = 0;
	is_vip = 0;

	target_career = 0;

	state = CS_Disconnected;
	
	if(server.game_type == Game::kBombMode)
	{
		if(server.bomb_owner_id == uid)
		{
			DropWeapon(BOMB_SLOT,position,0);

			if(server.bomb_owner_id == uid && server.bomb_planting)
			{
				CancelPlantBomb();
				server.ClearPlantState();
			}

			server.bomb_owner_id = 0;
		}
	}

	if(server.game_type == Game::kZombieMode)
	{
		ClearZombieSavingState();
	}

	server.ChangeStepFatherDummyOwner(uid);
	
	server.DestoryDummyByOwner(uid);

	

	
	// broadcast leave message
	for (uint i = 0; i < max_client_count; i++)
	{
		Client* c = server.clients + i;

		if (c->IsConnected())
		{
			c->BeginWrite();
			c->WriteByte(SM_ClientLeave);
			c->WriteByte(uid);
			c->EndWrite();
			client_count++;
		}
		if (c->control_person_old_id == id)
		{
			c->control_person_old_id = -1;
		}
		if (c->control_person_id == id)
		{
			c->control_person_old_id = -1;
			c->control_person_id = -1;
		}
	}

	ResetAllCure();

	if (team < 2)
	{
		control_person_old_id = -1;
		control_person_id = -1;
		reveng_person_id.clear();
		std::vector<int>::iterator itr;
		for (itr = server.team_ids[team].begin(); itr != server.team_ids[team].end();++itr)
		{
			if (*itr == id)
			{
				server.team_ids[team].erase(itr);
				break;
			}
		}
	}
	//log_write(LOG_INFO, "OnDisconnected  server.team_ids[0] %d      server.team_ids[1] %d",server.team_ids[0].size(), server.team_ids[1].size());

	// notify channel server client leave
	server.NotifyClientLeave(*this);

	if (client_count == 0)
	{
		if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
		{
			log_write(LOG_ERROR, "%s, %s, no client", __FILE__, __FUNCTION__);
		}
		// update kill info
		server.UpdateKillInfo();
		server.RequestDisconnect();
	}

	// on client disconnected
	server.OnClientStateChanged();

	//
	data.Clear();

	if(server.game_type == Game::kBombMode)
	{
		if(server.bomb_defusing_uid == uid)
			server.ClearDefusingState();
	
	}
	
	log_write(LOG_DEBUG1, "%s, %s leave and server type : %d name : %s, m_bRequestLeaved : %d", __FILE__, __FUNCTION__, (int)server.cServerType, GetCharacterInfo().character_name, (int)m_bRequestLeaved);
	if ((server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting) && !m_bRequestLeaved)
	{
		log_write(LOG_DEBUG1, "%s, %s leave and server type : %d, and request to info, name : %s, m_bRequestLeaved : %d", __FILE__, __FUNCTION__, (int)server.cServerType, GetCharacterInfo().character_name, (int)m_bRequestLeaved);
		server.RequestStageQuit(*this);
	}
	
	m_bRequestLeaved = false;
	
	if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
	{
		log_write(LOG_DEBUG1, "%s, %s, matching_level_check name : %s, match level : %d, team : %d", __FILE__, __FUNCTION__, GetCharacterInfo().character_name, GetCharacterInfo().nMatchingLevel, team);
	}
}

// update
void Client::Update(float delta)
{
	try
	{
		if (appcfg.max_checkcheat_num > 0 && 
			checkcheat_num > appcfg.max_checkcheat_num)
		{
			Disconnect();
		}

		if (server.game_type == kSurvivalMode && ghostflag && !isghost)
		{
			ChangeGhost(NULL);
		}

		if (isghost && !GetSkillEffect().HasEffect(kEffect_Survival_Ghost))
		{
			ChangeGhost(NULL);
		}

		if (isghost && spawntimebySurvival > 0.f)
		{
			spawntimebySurvival -= delta;
			if (spawntimebySurvival < 0.f)
			{
				Transform transform;
				int size = server.start_point[team].size();
				transform = server.start_point[team][rand() % size];
				Spawn(transform);
				spawntimebySurvival = 0.f;
			}
		}
		
		checkcheat_cleantimer += delta;
		if (checkcheat_cleantimer >= appcfg.checkcheat_cleantime)
		{
			checkcheat_num = 0;
			checkcheat_cleantimer += appcfg.checkcheat_cleantime;
		}
		
		if (control_person_id < 0)
		{
			if (server.team_ids[0].size() >= 2 && server.team_ids[1].size() >= 2)
			{
				int temp_id = -1;
				do
				{
					temp_id = rand() % server.team_ids[team==0?1:0].size();
					temp_id = server.team_ids[team==0?1:0][temp_id];
				} while (temp_id == control_person_old_id);
				Client* temp_c = server.clients + temp_id;
				if (temp_c && temp_c->IsReady())
				{
					control_person_id = temp_id;
					BeginWrite();
					WriteByte(SM_ControlPerson);
					WriteByte(temp_c->uid);
					EndWrite();
				}
			}
		}
		if (itemmode_skill_cd > 0)
		{
			itemmode_skill_cd -= delta;
			if (itemmode_skill_cd < 0)
			{
				itemmode_skill_cd = -1;
				EffectData effectdata;
				effectdata.duration_timer = 3.0f;
				effectdata.interval_timer = 3.0f;
				effectdata.type = kEffect_Infect_Damage;
				effectdata.value = 0.5f;
				effectdata.enable = true;
				effect.ApplySystemItemEffect(effectdata, ITEMMODE_ITEMID);
			}
		}

		int cur_time = (int)server.time_max - (int)server.play_time;
		if (cur_time < 0)
			cur_time = 0;
		if (cur_time != play_time)
		{
			play_time = cur_time;

			if (IsReady())
			{
				BeginWrite();
				WriteByte(SM_SyncTime);
				WriteInt(play_time);
				EndWrite();
			}
		}

		life_time += delta;
		for (std::map<byte, Weapon>::iterator itr = GetCurPackInfo().weapon_set.begin(); 
			itr != GetCurPackInfo().weapon_set.end(); itr++)
		{
			if (itr->second.weapon_data.weapon.time_to_idlecount > 0)
				itr->second.weapon_data.weapon.time_to_idlecount -= delta;
		}

		static const float sync_holdinfo_interval = 1.0f;
		sync_holdinfo_timer += delta;
		if (sync_holdinfo_timer >= sync_holdinfo_interval)
		{
			sync_holdinfo_timer = 0.0f;

			switch (server.game_type)
			{
			case Game::kHoldPoint:
				{
					SyncHoldPointInfo();
				}
				break;
			case Game::kPushVehicle:
			case Game::kNovice:
				{

				}
				break;
			}
		}

		switch (state)
		{
		case CS_Alive:
			if (input_limit_timer > 0 && server.idle_kick_open)
			{
				input_limit_timer -= delta;
				if (input_limit_timer <= 0)
				{
					input_limit_timer = 0;
					leavereason = kLeaveGameReasonIdle;
					server.RequestStageQuit(*this);
					if (server.cServerType == (byte)SvrType_Match || server.cServerType == (byte)SvrType_MatchFighting)
					{
						log_write(LOG_ERROR, "%s, %s, input_limit_timer name : %s", __FILE__, __FUNCTION__, GetCharacterInfo().character_name);
					}
					return;
				}
			}
			
			if (charge_skill_alltime > 0)
			{
				charge_skill_alltime -= delta;
				if (charge_skill_alltime < 0)
				{
					charge_skill_time = -1;
					charge_skill_alltime = -1;
					ischarge_skill = false;
				}
			}
			if (charge_skill_cd >= 0)
			{
				charge_skill_cd -= delta;
			}

			//冲锋僵尸使用冲锋技能
			if (ischarge_skill)
			{
				if (charge_skill_time < 0.f && charge_skill_time > -1.f)
				{
					effect.OnClientUseSkill(kSkillSlot_LeftButton);
					charge_skill_time = -0.1f;
				}
				else
				{
					charge_skill_time -= delta;
				}
			}

			if (super_human_skill_cd_time > 0 )
			{
				if (super_human_skill_cd_time < delta)
					super_human_skill_cd_time = 0.0f;
				else
					super_human_skill_cd_time -= delta;
			}

			UpdatePlayerSpeed();
			
			effect.Update(delta);

			recover_assist_timer -= delta;
			if(recover_assist_timer < 0.f)
			{
				recover_assist_uid = 0;
			}
			assist_timer[0] -= delta;
			if(assist_timer[0] < 0.f)
			{
				assist_uid[0] = 0;
			}
			assist_timer[1] -= delta;
			if(assist_timer[1] < 0.f)
			{
				assist_uid[1] = 0;
			}
			
			if (zombie_skill_cd >= 0.f)
				zombie_skill_cd -= delta;

			// cure MinusMax
			cure_limit_timer -= delta;
			if (cure_limit_timer < 0.f && health > max_health)
			{
				Recover((int)(max_health * 2 / 100.f),kRecoverMinusMax);
				
				cure_limit_timer += 1.f;
			}
			
			selfrecover_interval -= delta;
			if (selfrecover_interval <= 0)
			{
				if(drinkrecovercount > 0)
				{
					Recover(drinkrecover,kRecoverSelf);
					drinkrecovercount--;
				}

				selfrecover_interval += SELFRECOVER_INTERVAL;
			}
			
			if (server.game_type == kCommonZombieMode)
			{
				if(is_powup)
				{
					powup_time -= delta;
					if (powup_time < 0.0f)
					{
						powup_time = 0.0f;
						is_powup = false;
					}
				}
				if (unable_time > 0.0f)
				{
					unable_time -= delta;
					if (unable_time < 0.0f)
					{
						unable_time = -1.0f;
						NotifyCommonKingZombieHalfFlash();
					}
				}
			}
			
			if (server.game_type == kBossMode2)
			{
				if (boss2_strange_spawn_use)
				{
					if (boss2_strange_spawn != -1)
					{
						uint target_career_backup = target_career;
						boss2_strange_spawn = boss2_strange_spawn%4;
						
						target_career = server.level_info.bosspve_info[boss2_strange_spawn].career_id;
						
						int pos = server.boss2_rebirth_rand_start[boss2_strange_spawn] % server.boss2_rebirth_point[boss2_strange_spawn].size();
						server.boss2_rebirth_rand_start[boss2_strange_spawn] = pos + 1;
						Transform transform = server.boss2_rebirth_point[boss2_strange_spawn][pos];
						
						Spawn(transform, NULL, false, false, true);
						
						target_career = target_career_backup;
						
						boss2_strange_spawn = -1;
						
						Boss2SyncData();
					}

					boss2_strange_spawn_use = false;
				}
			}
			

			if (server.game_type == kCommonZombieMode && team == 0)
			{
				if (somg_hurt_interval_time > 0.f)
				{
					somg_hurt_interval_time -= delta;
				}
				else
				{
					for (std::map<uint, Vector3>::const_iterator itr = server.somg_area.begin(); 
						itr != server.somg_area.end(); itr++)
					{
						if (Length(itr->second - position) < 3.f)
						{
							bool is_die = false;
							Client* from = server.GetClient(itr->first);
							if(from)
							{
								int damage = 50;
								TakeDamage(from, &damage, kCharacterPartTorso, from->GetCurWeaponInfo());
								for (uint i = 0; i < max_client_count; i++)
								{
									Client * c = server.clients + i;

									if (c->IsReady())
									{
										c->BeginWrite();
										c->WriteByte(SM_CutHurt);
										c->WriteByte(from->uid);
										c->WriteByte(uid);
										c->WriteInt(from->GetCurWeaponInfo().weapon_data.weapon.type);
										c->WriteByte(0);

										c->WriteByte(0);//part
										c->WriteInt(data.GetScore());
										c->WriteInt(health);
										c->WriteInt(armor);

										if (health == 0 || (health ==1 && isghost))
										{
											WriteWeapon(*c, from->GetCurWeaponInfo());

											Client *client = from;

											is_die = true;

											//sucide and have assist attacked
											if(uid == this->uid && client && from->assist_uid[0] != this->uid)
											{
												c->WriteShort(client->num_kill);
											}
											else
											{
												c->WriteShort(this->num_kill);
											}


											c->WriteShort(num_die);
											c->WriteByte(from->assist_uid[0]);

											if(client)
											{
												c->WriteInt(client->data.GetScore());
												c->WriteShort(client->assist_num);
											}
											else
											{
												c->WriteInt(0);
												c->WriteShort(short(0));
											}
										}
										c->EndWrite();
									}
								}
								if (is_die)
								{
									from->data.ScoreDataAdd(0, true);
								}
								somg_hurt_interval_time = 0.5f;
								break;
							}
						}
					}
				}
			}

			break;
		case CS_Died:
			if (team < 2)
			{
				switch (server.game_type)
				{
				case Game::kStreetBoyMode:
					{
						if (spawn_time >= 0)
						{
							spawn_time -= delta;

							if (spawn_time <= 0)
							{
								Transform transform;
								if(server.special_person_flashed)
								{
									transform.position = server.street_king_info[team].street_king_last_position;
									transform.rotation = 0;
								}
								else
								{
									transform.position = position;
									transform.rotation = 0;

									int size = server.start_point[team].size();
									if (size > 0)
									{
										transform = server.start_point[team][rand() % size];
									}
								}

								Spawn(transform);
							}
							else if (spawn_timing == false && spawn_time < server.config.spawn_time)
							{
								BeginWrite();
								WriteByte(SM_PlayerSpawnTiming);
								WriteFloat(spawn_time);
								EndWrite();

								spawn_timing = true;
							}
						}
					}
					break;
				case Game::kZombieMode:
					{
						if (spawn_time >= 0)
						{
							spawn_time -= delta;

							if (spawn_time <= 0)
							{
								if(team == 1)
								{
									int player_count = 0;

									for (int i = 0; i < max_client_count; ++ i)
									{
										Client * c = server.clients + i;
										if (c->IsReady())
										{
											player_count++;
										}
									}

									NotifyZombieFlash(player_count, false);
								}
								
							}
							else if (spawn_timing == false)
							{
								if(spawn_time < server.config.spawn_time || team == 1)
								{
									BeginWrite();
									WriteByte(SM_PlayerSpawnTiming);
									WriteFloat(spawn_time);
									EndWrite();

									spawn_timing = true;
								}
								
							}
						}
					}
					break;
				case kCommonZombieMode:
					{
						if (is_common_zombie_spawn)
						{
							NotifyCommonZombieFlash(die_pos);
							is_common_zombie_spawn = false;
						}
						else
						{
							if (spawn_time >= 0)
							{
								spawn_time -= delta;

								if (spawn_time <= 0)
								{
									if(team == 1)
									{
										if (!server.special_person_flashed)
										{
											SetTeam(0);
											Transform transform;
											transform.position = position;
											transform.rotation = 0;

											int size = server.start_point[team].size();
											if (size > 0)
											{
												transform = server.start_point[team][(uid & 0x0f) % size];
											}
											Spawn(transform);
										}
										else
										{
											if (is_zombie_super)
											{
												NotifyCommonZombieSuper(die_pos);
											}
											else
											{
												Transform transform;
												transform = server.start_point[0][rand() % server.start_point[0].size()];
												if (is_zombie_king)
													NotifyCommonKingZombieFlash(transform.position);
												else
												{
													NotifyCommonZombieFlash(transform.position);
												}
											}
										}
									}
									else
									{
										if (!server.special_person_flashed)
										{
											SetTeam(0);
											Transform transform;
											transform.position = position;
											transform.rotation = 0;

											int size = server.start_point[team].size();
											if (size > 0)
											{
												transform = server.start_point[team][(uid & 0x0f) % size];
											}
											Spawn(transform);
										}
									}
								}
								else if (spawn_timing == false)
								{
									if(spawn_time < server.config.spawn_time || team == 1)
									{
										BeginWrite();
										WriteByte(SM_PlayerSpawnTiming);
										WriteFloat(spawn_time);
										EndWrite();

										spawn_timing = true;
									}
								}
							}
							else
							{
								if (!server.special_person_flashed)
								{
									SetTeam(0);
									Transform transform;
									transform.position = position;
									transform.rotation = 0;

									int size = server.start_point[team].size();
									if (size > 0)
									{
										transform = server.start_point[team][(uid & 0x0f) % size];
									}
									Spawn(transform);
								}
							}
						}
					}
					break;
				case kBossMode2:
					{
						if (spawn_time >= 0)
						{
							spawn_time -= delta;

							if (spawn_time <= 0)
							{
								if (team == 1)
									SetTeam(0);
							
								Transform transform;
								transform.position = position;
								transform.rotation = 0;

								int size = server.start_point[team].size();
								if (size > 0)
								{
									transform = server.start_point[team][rand() % size];
								}
								Spawn(transform);
							}
							else if (spawn_timing == false && spawn_time < server.config.spawn_time)
							{
								BeginWrite();
								WriteByte(SM_PlayerSpawnTiming);
								WriteFloat(spawn_time);
								EndWrite();

								spawn_timing = true;
							}
						}
					}
					break;
				default:
					{
						if (spawn_time >= 0)
						{
							spawn_time -= delta;

							if (spawn_time <= 0)
							{
								Transform transform;
								transform.position = position;
								transform.rotation = 0;

								int size = server.start_point[team].size();
								if (size > 0)
								{
									transform = server.start_point[team][rand() % size];
								}
								Spawn(transform);
							}
							else if (spawn_timing == false && (spawn_time < server.config.spawn_time || kTDMode == server.game_type) )
							{
								BeginWrite();
								WriteByte(SM_PlayerSpawnTiming);
								WriteFloat(spawn_time);
								EndWrite();

								spawn_timing = true;
							}
						}
					}
					break;
				}
			}
			break;

		case CS_ErrorWrite:
			throw ERR_Write;
		};
	}
	catch (...)
	{
		Disconnect();
	}
}

// receive message
void Client::OnMessage()
{
	switch (state)
	{
	case CS_Loading:
		{
			byte msg;
			ReadByte(msg);

			switch (msg)
			{
			case CM_ReadyForGame:
				{
					ReadInt(target_career);
					
					Initialize();

					// broadcast client join
					for (uint i = 0; i < max_client_count; i ++)
					{
						Client* c = server.clients + i;
						if (c->IsReady())
						{
							c->BeginWrite();
							c->WriteByte(SM_ClientJoin);
							c->WriteByte(uid);
							c->WriteByte(team);
							c->WriteByte(is_vip);
							c->WriteByte(business_card);
							c->WriteByte(is_gm);
							c->WriteString(head_icon);
							c->WriteInt(level);
							c->EndWrite();
						}
					}
					
					state = CS_Died;
					
					switch (server.game_type)
					{
					case Game::kTeamDeathMatch:
					case Game::KMoonMode:
						{
							if(server.round_start_time  <= 0.f)
							{
								spawn_time = NEVER_SPAWN;
							}
							else
							{
								spawn_time = 0.1f;
							}
						}
						break;
					case Game::kBombMode:
						{
							if(server.round_start_time  <= 0.f)
							{
								spawn_time = NEVER_SPAWN;
							}
							else
							{
								spawn_time = 0.1f;
							}
						}
						break;
					case Game::kCommonZombieMode:
					case Game::kBossMode:
						{
							if(!server.special_person_flashed)
							{
								spawn_time = 0.1f;
							}
							else
							{
								spawn_time = NEVER_SPAWN;
							}
						}
						break;
					default:
						{
							spawn_time = 0.1f;
						}
						break;
					}

					connected = true;
					server.OnClientStateChanged();

					// send level supply
					for (SupplyObject* supply = server.supply_list.Begin(); supply < server.supply_list.End(); supply++)
					{
						if (supply && supply->IsActive() && server.CheckSupplyType(supply->supply.type))
							AddSupplyObject(supply);
					}

					if(server.round_start_time > 0.f)
					{
						RoundStart(server.round_start_time);
					}

					break;
				}

			case CM_LeaveGame:		RequestLeaveGame();	 	break;
			case CM_OpenMessageClient:	ParseOpenMessageClient();	break;

			default:
				log_write(LOG_DEBUG1, "client uid: %d, on message warning, state: %d, msg: %d", uid, state, msg);
				break;
			}
		}
		break;

	case CS_Waiting:
	case CS_Alive:
	case CS_Died:
		{
			byte msg;
			ReadByte(msg);

			switch (msg)
			{
			case CM_LeaveGame:				RequestLeaveGame();		break;
			case CM_RequestChat:			ParseChat();			break;
			case CM_KickClientStart:		ParseKickClientStart();	break;
			case CM_KickClientVote:			ParseKickClientVote();	break;
			case CM_SpawnConfirm:			ParseSpawnConfirm();	break;
			case CM_ChangeCareer:			ParseChangeCareer();	break;
			
			case CM_ProjectedAmmoOut:		ParseProjectedAmmoOut();	break;
			case CM_ProjectedAmmoHurt:		ParseProjectedAmmoHurt();break;
			case CM_ProjectedProdHurt:		ParseProjectedProdHurt();	break;
			case CM_ProjectedAmmoDestroy:	ParseProjectedAmmoDestroy();break;
			case CM_ProjectedAmmoUpdate:	ParseProjectedAmmoUpdate();break;
			
			case CM_PVEAmmoOut:				ParsePVEAmmoOut();		break;
			case CM_PVEAmmoDestroy:			ParsePVEAmmoDestroy();	break;
			case CM_PVEAmmoHitHurt:			ParsePVEAmmoHitHurt();	break;
			case CM_PVEAmmoExplodeHurt:		ParsePVEAmmoExplodeHurt();break;
			case CM_PVEAmmoUpdate:			ParsePVEAmmoUpdate();	break;
			case CM_CutHurt:				ParseCutHurt();			break;
			case CM_DummyObjectCreate:		ParseDummySyncCreate();	break;
			case CM_DummyObjectDestory:		ParseDummySyncDestory();break;
			case CM_DummySyncUpdate:		ParseDummySyncUpdate();	break;
			case CM_GunTowerShoot:			ParseGunTowerShoot();	break;
			case CM_DummyPokeHurt:			ParseDummyPokeHurt();	break;
			case CM_DummyProjectedAmmoHurt: ParseDummyProjectedAmmoHurt();	break;
			case CM_DummyGrenadeHurt:		ParseDummyGrenadeHurt(); break;
			case CM_DummyProjectedProdHurt:	ParseDummyProjectedProdHurt();	break;
			case CM_CharacterHeal:			ParseCharacterHeal();	break;
			case CM_Teleport:				ParseTeleport();		break;
			case CM_ForceSpawn:				ParseForceSpawn();		break;
			case CM_NEED_DIE_BUFF:			ParseNeedDieBuff();		break;
						
			default:
				{
					if (spawn_confirm)
					{
						switch (msg)
						{
						case CM_SyncPlayerData:	ParseSyncPlayerData();	break;
						case CM_Shoot:			ParseShoot(); 			break;
						case CM_Poke:			ParsePoke(); 			break;
						case CM_PokeHurt:		ParsePokeHurt(); 		break;
						case CM_GrenadeThrowIn:	ParseGrenadeThrowIn(); 	break;
						case CM_GrenadeThrowStop:	ParseGrenadeThrowStop(); 	break;
						case CM_GrenadeThrowOut:ParseGrenadeThrowOut();	break;
						case CM_Hurt:			ParseHurt(); 			break;
						case CM_Reload:			ParseReload(); 			break;
						case CM_ReloadReady:	ParseReloadReady(); 	break;
						case CM_DropWeapon:		ParseDropWeapon();		break;
						case CM_PickUpWeapon:	ParsePickUpWeapon();	break;
						case CM_SelectWeapon:	ParseSelectWeapon();	break;
						case CM_GrenadeHurt:	ParseGrenadeHurt(); 	break;

						case CM_KickBack:		ParseKickBack();		break;
						case CM_FlashBright:	ParseFlashBright();		break;
						case CM_CameraFovChanged:	ParseCameraFovChanged();	break;
						case CM_PickUpSupplyObject: ParsePickUpSupplyObject();	break;
						case CM_PickUpSupplyObjectNew: ParsePickUpSupplyObjectNew();	break;
						case CM_OpenMessageClient:	ParseOpenMessageClient();	break;

						case CM_UseSkill:		ParseUseSkill();		break;
						case CM_ChangePack:		ParseChangePack();		break;
						case CM_Suicide:		ParseSuicide();			break;

						case CM_RadioReport:	ParseRadioReport();		break;
						case CM_ActionOn:		ParseActionOn();		break;
						//////////////////////////////////////New Edited Start/////////////////////////////////////////
						case CM_PlayerAnimationStart:	ParsePlayerAnimationStart();	break;
						case CM_PlayerAnimationEnd:		ParsePlayerAnimationEnd();		break;
						case CM_CallDoctor:			ParseCallDoctor();			break;
						//////////////////////////////////////New Edited End/////////////////////////////////////////
						case CM_CureCharacter:		ParseCureCharacter();	break;
						case CM_PlayerFlameShoot:	ParseFlameShoot();		break;
						case CM_StopBurn:			ParseStopBurn();		break;
						case CM_NoviceOperation:	ParseNoviceOperation();	break;
						case CM_DrumCheck:			ParseDrumCheck();		break;
						case CM_Drink:				ParseDrink();			break;
						case CM_Spray:				ParseSpray();			break;
						case CM_ChangeAmmoTeam:		ParseChangeTeam();		break;
						case CM_UseSpawnCoin:		ParseUseSpawnCoin();	break;

						case CM_StartPlantBomb:		ParseStartPlantBomb();	break;
						case CM_CancelPlantBomb:	ParseCancelPlantBomb();	break;

						case CM_StartDefuseBomb:	ParseStartDefuseBomb();	break;
						case CM_CancelDefuseBomb:	ParseCancelDefuseBomb();break;
						case CM_StartSaveDying:		ParseStartSaveDying();	break;
						case CM_CancelSaveDying:	ParseCancelSaveDying();	break;
						case CM_ChargeSomething:	ParseChargeSomething();	break;
						case CM_SkillKickBack:		ParseSkillKickBack();	break;
						case CM_UseSkillSuperMan:	ParseUseSkillSuperMan();break;
						case CM_CancelInvisible:	ParseCancel_Invisible();break;
						case CM_UseSmog:            ParseUseSmog();			break;
						case CM_SomgAreaCancel:		ParseSomgAreaCancel();	break;
						
						case CM_UseItem_ItemMode:	ParseUseItem_ItemMode();break;
						case CM_ItemMode_ZiBao:		ParseItemMode_ZiBao();break;
						case CM_UseItem:			ParseUseItem();break;
						case CM_UseItemSurvival:	ParseUseItemSurvival();break;
						case CM_UseItemSurvivalByGhost: ParseUseItemSurvivalByGhost(); break;
						case CM_SAVE_MAP:			ParseSaveMap();break;
						case CM_MoonBoss:			ParseMoonBoss();break;

						default:
							log_write(LOG_DEBUG1, "client uid: %d, on message warning, state: %d, msg: %d", uid, state, msg);
							break;
						}
					}
				}
			}
		}
		break;
	}
}

// bot
void Client::OnBotCreate(CharacterInfo &charinfo)
{
	team = 1;

	ignore_checkcheat = true;
	checkcheat_num = 0;
	checkcheat_delta = 0.f;
	checkcheat_cleantimer = 0.f;

	num_kill = 0;
	num_die = 0;
	num_healthrecover = 0;
	num_ammorecover  = 0;
	control_num = 0;
	revenge_num = 0;
	assist_num = 0;
	knife_kill = 0;
	input_limit_timer = INPUT_LIMIT_TIME;

	drinkrecover = 0;
	drinkrecovercount = -1;
	recv_offset = 0;
	send_offset = 0;
	health = 0;
	max_health = 0;
	ex_health = 0;
	ping = 0;
	status = 0;
	sync_flags = 0;
	sync_time = 0;

	UpdateMagic();

	died_camera_time = 3.5f;
	position.x = 0;
	position.y = 0;
	position.z = 0;
	position_last.x = 0;
	position_last.y = 0;
	position_last.z = 0;
	rotation.x = 0;
	rotation.y = 0;
	rotation.z = 0;
	rotation.w = 1;

	target_career = 0;
	zombie_default_career = 0;

	is_voted = false;

	state = CS_Waiting;
	connected = true;

	accumulate_damage = 0;

	target_career = 0;
	
	pickup_dropitems.clear();

	SetCharacterInfo(charinfo);

	ApplyHackFix();
}

void Client::OnBotDestroy()
{

}

void Client::SetBosspveCareerid(uint cid)
{
	is_pveboss = true;
	bosspve_career_id = cid;
}

uint Client::GetBosspveCareerid()
{
	return bosspve_career_id;
}

void Client::SetBossActionInfo(std::vector<BossActionInfo> & info)
{
	alive_boss_actioninfo.clear();
	nowactionindex = 0;
	alive_boss_actioninfo = info;
}

void Client::OnBotSpawn(const Vector3 & pos)
{
	server.special_person_flashed = true;
	team = 1;
	ignore_checkcheat = true;
	checkcheat_delta = 0;
	checkcheat_movespeed = 0;
	ischarge_skill = false;
	charge_skill_time = -1;
	charge_skill_alltime = -1;
	charge_skill_cd = -1;
	armor = 0;
	max_armor = 0;
	zombie_skill_cd = 0.f;
	input_limit_timer = INPUT_LIMIT_TIME;
	drinkrecover = 0;
	drinkrecovercount = -1;

	SingleCharacterInfo& current_character = GetCurCharinfo();
	float health_scale = GetScale(server.level_info.health_scale);
	max_health_base = max_health_unscale * health_scale;
	ex_health_base = ex_health_unscale * health_scale;

	effect.Initialize();

	data.ScoreDataRebirth(current_character.career_id, true);
	uint s_rand = rand();
	rand_fun.Srand(s_rand);

	ResetAllCure();

	health = max_health;
	sync_flags = 0;

	weapon_used = false;

	assist_uid[0] = assist_uid[1] = 0;

	accumulate_damage = 0;

	life_time = 0;

	spawn_time = 0;
	spawn_timing = false;

	state = CS_Alive;
	spawn_confirm = true;
	playing = true;
	connected = true;

	cure_list.clear();

	position.x = pos.x;
	position.y = pos.y;
	position.z = pos.z;

	rotation.x = 0;
	rotation.y = cosf(3.1415926f / 180.f * 180.f * 0.5f);
	rotation.z = 0;
	rotation.w = sinf(3.1415926f / 180.f * 180.f * 0.5f);

	// broadcast player spawn
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_BotSpawn);
			c->WriteByte(uid);
			c->WriteInt(health);
			c->WriteInt(max_health);
			c->WriteInt(ex_health);
			c->WriteByte(team);
			c->WriteInt(armor);
			c->WriteInt(s_rand);
			c->WriteVector3(position);
			c->WriteFloat(180.f);
			WriteSingleCharacter(*c, current_character);
			c->EndWrite();
			// 修复SM_PlayerSpawn与SM_SyncSkillEffect的先后顺序
			effect.ForceSendToClient(*c);
		}
	}

	ClearCurePower();
	zombie_mode_dying_state = false;
	human_rebirth_counter = 0;
}

void Client::BotUpdate(float delta)
{
	switch (state)
	{
	case CS_Alive:
		effect.Update(delta);
		break;

	default:
		break;
	}

	if (bosspve_career_id == 0)
		return;

	if (alive_boss_actioninfo.size() <= 0)
		return;

	if (IsDied())
		return;

	action_timer += delta;
	if (action_timer < total_action_timer)
	{
		if (abs(action_timer - old_action_timer) >= 0.5f)
		{
			old_action_timer = action_timer;
			std::string mode = "BOSSPVE_";
			std::string boss_name = m_character_info.character_name;
			mode += boss_name;
			mode += "_ACTION_TIMER";
			server.SetServerScriptNumberValue(mode, action_timer);
		}
		return;
	}

	if (nowactionindex < 0)
		return;

	int max_action = 0;
	for (int i = 0; i < (int)alive_boss_actioninfo.size(); i++)
	{
		if (max_action < alive_boss_actioninfo[i].action_id)
		{
			max_action = alive_boss_actioninfo[i].action_id;
		}
	}
	float defaulttimer = 45.f;
	if (nowactionindex < alive_boss_actioninfo.size() && nowactionindex != 0)
	{
		sycn_boss_action.startpos = alive_boss_actioninfo[nowactionindex-1].position;
		sycn_boss_action.endpos = alive_boss_actioninfo[nowactionindex].position;
		sycn_boss_action.startrot = alive_boss_actioninfo[nowactionindex-1].rotangle;
		sycn_boss_action.endrot = alive_boss_actioninfo[nowactionindex].rotangle;

		if (alive_boss_actioninfo[nowactionindex].action_timer > 0)
			sycn_boss_action.total_time = total_action_timer = alive_boss_actioninfo[nowactionindex].action_timer;
		else
			sycn_boss_action.total_time = total_action_timer = defaulttimer;

		sycn_boss_action.move_time = alive_boss_actioninfo[nowactionindex].move_timer;
		if (sycn_boss_action.move_time <= 0)
			sycn_boss_action.move_time = total_action_timer;

		if (alive_boss_actioninfo[nowactionindex].action_id > 0 && sycn_boss_action.action_id != alive_boss_actioninfo[nowactionindex].action_id)
			sycn_boss_action.action_id = alive_boss_actioninfo[nowactionindex].action_id;
		else
		{
			int id = 0;
			for (int i = 0; i < 5; ++i)
			{
				id = rand()%(max_action-3) + 4;
				if (id != sycn_boss_action.action_id)
					break;
			}
			sycn_boss_action.action_id = id;		//预留1, 2 ,3
		}
		nowactionindex++;
		//log_write(LOG_DEBUG1,"nowactionindex = %d  sycn_boss_action.action_id = %d",nowactionindex,sycn_boss_action.action_id);
	}
	else if (nowactionindex == 0)
	{
		sycn_boss_action.startpos = alive_boss_actioninfo[0].position;
		sycn_boss_action.endpos = alive_boss_actioninfo[0].position;
		sycn_boss_action.startrot = alive_boss_actioninfo[0].rotangle;
		sycn_boss_action.endrot = alive_boss_actioninfo[0].rotangle;

		if (alive_boss_actioninfo[0].action_timer > 0)
			sycn_boss_action.total_time = total_action_timer = alive_boss_actioninfo[0].action_timer;
		else
			sycn_boss_action.total_time = total_action_timer = defaulttimer;

		sycn_boss_action.move_time = alive_boss_actioninfo[0].move_timer;
		if (sycn_boss_action.move_time <= 0)
			sycn_boss_action.move_time = total_action_timer;

		if (alive_boss_actioninfo[0].action_id > 0)
			sycn_boss_action.action_id = alive_boss_actioninfo[0].action_id;
		else
			sycn_boss_action.action_id = 0;
		nowactionindex++;
		//log_write(LOG_DEBUG1,"nowactionindex = %d  sycn_boss_action.action_id = %d",nowactionindex,sycn_boss_action.action_id);
	}
	else
	{
		sycn_boss_action.startpos = alive_boss_actioninfo[nowactionindex-1].position;
		sycn_boss_action.endpos = alive_boss_actioninfo[3].position;
		sycn_boss_action.startrot = alive_boss_actioninfo[nowactionindex-1].rotangle;
		sycn_boss_action.endrot = alive_boss_actioninfo[3].rotangle;

		if (alive_boss_actioninfo[3].action_timer > 0)
			sycn_boss_action.total_time = total_action_timer = alive_boss_actioninfo[3].action_timer;
		else
			sycn_boss_action.total_time = total_action_timer = defaulttimer;

		sycn_boss_action.move_time = alive_boss_actioninfo[3].move_timer;
		if (sycn_boss_action.move_time <= 0)
			sycn_boss_action.move_time = total_action_timer;

		if (alive_boss_actioninfo[3].action_id > 0 && sycn_boss_action.action_id != alive_boss_actioninfo[3].action_id)
		{
			sycn_boss_action.action_id = alive_boss_actioninfo[3].action_id;
		}
		else
		{
			int id = 0;
			for (int i = 0; i < 5; ++i)
			{
				id = rand()%(max_action-3) + 4;
				if (id != sycn_boss_action.action_id)
					break;
			}
			sycn_boss_action.action_id = id;		//预留1, 2 ,3
		}
		nowactionindex = 4;
		//log_write(LOG_DEBUG1,"nowactionindex = %d  sycn_boss_action.action_id = %d",nowactionindex,sycn_boss_action.action_id);
	}
	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_SycnBossAction);
			c->WriteByte(uid);
			c->WriteVector3(sycn_boss_action.startpos);
			c->WriteVector3(sycn_boss_action.endpos);
			c->WriteFloat(sycn_boss_action.startrot);
			c->WriteFloat(sycn_boss_action.endrot);
			c->WriteInt(sycn_boss_action.move_time);
			c->WriteFloat(sycn_boss_action.total_time);
			c->WriteInt(sycn_boss_action.action_id);
			c->EndWrite();
		}
	}

	old_action_timer = action_timer = 0;
	std::string mode = "BOSSPVE_";
	std::string boss_name = m_character_info.character_name;
	mode += boss_name;
	mode += "_ACTION_TIMER";
	server.SetServerScriptNumberValue(mode, action_timer);

	//获取当前BOSS的攻击小节号
	server.SetServerScriptNumberValue(m_character_info.character_name, sycn_boss_action.action_id);
}

InGameItemInfo* Client::GetBagItem(uint sid)
{
	ItemBag::iterator it = m_character_info.bag.find(sid);
	if (it != m_character_info.bag.end() )
	{
		return &it->second;
	}

	return NULL;
}

bool Client::UseItem(uint sid, ushort count)
{
	if (IsDied() )
		return true;

	InGameItemInfo* item =  GetBagItem(sid);
	if (NULL == item || item->count < count)
	{
		log_write(LOG_WARNING, "item count no enough or item can't find!!!");
		return false;
	}

	if (server.game_type != kTDMode)
	{
		if(item->subType == 6)
		{
			// 资源争夺战类型判断
			return false;
		}
	}

	double now_time = Event::GetTime();

	if (ItemIsCDTime(item, now_time) || IsCarrierMode() )
		return false;

	if (ItemIsRedBottle(item) )
	{
		EffectData effectdata;
		effectdata.duration_timer = item->param[1]<1.0f ? 1.0f : item->param[1];
		effectdata.type = kEffect_Sustain_HpRecover;
		effectdata.value = max_health*item->param[0]/100.0f/effectdata.duration_timer+0.5f;	//计算每次所加数值
		effectdata.interval = 1.0f;
		effectdata.interval_timer = 1.0f;
		effectdata.enable = true;

		effect.ApplySystemEffect(effectdata);
		item->interval_timer = now_time;
	}
	else if (ItemIsAmmo(item) )
	{
		Weapon* weapon = GetWeaponInfo(GetCurWeapon());
		if (NULL == weapon)
		{
			log_write(LOG_WARNING, "can't find current weapon !!!");
			return false;
		}
		EffectData effectdata;
		effectdata.duration_timer = item->param[1]<1.0f ? 1.0f : item->param[1];
		effectdata.type = kEffect_Sustain_AmmoRecover;
		effectdata.value = item->param[0]/effectdata.duration_timer;//计算每次所加百分比
		effectdata.interval = 1.0f;
		effectdata.interval_timer = 1.0f;
		effectdata.enable = true;

		effect.ApplySystemEffect(effectdata);
		item->interval_timer = now_time;
	}
	else if (ItemIsCarrier(item) )
	{
		uint target_career_backup = target_career;

		target_career = (int)item->param[0];

		Transform transform;
		transform.position = position;
		transform.rotation = rotation.GetZXY().y * RAD2DEG;

		Spawn(transform, NULL, false, false, true);

		target_career = target_career_backup;
	}
	else if (ItemIsBuffer(item, ITEM_ATTACK) || ItemIsBuffer(item, ITEM_ATTACK_SPEED) || 
		ItemIsBuffer(item, ITEM_RESISTANCE) || ItemIsBuffer(item, ITEM_MOVE_SPEED))
	{
		EffectData effectdata;
		effectdata.duration_timer = item->param[1]<1.0f ? 1.0f : item->param[1];
		effectdata.value = item->param[0]/100.0f;
		effectdata.dead_disable = item->param[2]>0;
		switch(item->functionID)
		{
		case ITEM_ATTACK:
			effectdata.type = kEffect_Infect_Damage;
			break;
		case ITEM_ATTACK_SPEED:
			effectdata.type = kEffect_Infect_FireTime;
			break;
		case ITEM_RESISTANCE:
			effectdata.type = kEffect_Infect_ResistanceAll;
			break;
		case ITEM_MOVE_SPEED:
			effectdata.type = kEffect_Infect_MoveSpeed;
			break;
		default:
			assert("unkown type!" == NULL);
		}

		effectdata.enable = true;
		effect.ApplySystemEffect(effectdata);
		item->interval_timer = now_time;
	}
	else
	{
		log_write(LOG_WARNING, "item use fail !!!");
		return false;
	}


	item->count -= count;
	RefreshBagItem(sid);

	return true;
}

bool Client::ItemIsCDTime(const InGameItemInfo* item_info, double now_time)
{
	return (now_time-item_info->interval_timer) < item_info->CDTime;
}

bool Client::RefreshBagItem(uint sid)
{
	if (!IsReady())
		return false;

	if (0 == sid)
	{
		BeginWrite();
		WriteByte(SM_RefreshBagItem);
		WriteShort((ushort)GetCharacterInfo().bag.size() );
		ItemBag::iterator it = m_character_info.bag.begin();
		for (; it != m_character_info.bag.end(); ++it)
		{
			WriteInt(it->second.sid);
			WriteShort(it->second.count);
		}
		EndWrite();
	}
	else
	{
		InGameItemInfo* item = GetBagItem(sid);
		if (NULL == item)
			return false;
		BeginWrite();
		WriteByte(SM_RefreshBagItem);
		WriteShort((ushort)1);
		WriteInt(item->sid);
		WriteShort(item->count);
		EndWrite();
	}

	return true;
}

bool Client::ItemIsRedBottle(const InGameItemInfo* item_info)
{
	if (NULL == item_info)
		return false;

	if (6 == item_info->subType && ITEM_REDBOTTLE == item_info->functionID)
		return true;

	return false;
}

bool Client::ItemIsAmmo(const InGameItemInfo* item_info)
{
	if (NULL == item_info)
		return false;

	if (6 == item_info->subType && ITEM_AMMO == item_info->functionID)
		return true;

	return false;
}

bool Client::ItemIsCarrier(const InGameItemInfo* item_info)
{
	if (NULL == item_info)
		return false;

	if (3 == item_info->subType && ITEM_CARRIER == item_info->functionID)
		return true;

	return false;
}

bool Client::ItemIsBuffer(const InGameItemInfo* item_info, int buff_type)
{
	if (NULL == item_info)
		return false;

	if (6 == item_info->subType && buff_type == item_info->functionID)
		return true;

	return false;
}

bool Client::IsCarrierMode()
{
	if (kTDMode == server.game_type && false == GetCurCharinfo().can_select)
		return true;

	return false;
}

void Client::AddSkill(EEffect type, float timer)
{
	EffectData effectdata;

	effectdata.duration_timer = timer;
	effectdata.type = type;
	effectdata.value = 1.0f;
	effectdata.enable = true;

	effect.ApplySystemEffect(effectdata);
}

void Client::ChangeGhost(Client *from, const Weapon *weapon, int part, bool boost, bool sustainhurt)
{
	EffectData effectdata;

	effectdata.duration_timer = DURATION_INFINITY;
	effectdata.type = kEffect_Survival_Ghost;
	effectdata.value = 0.05f;
	effectdata.interval = 1.f;
	effectdata.enable = true;

	effect.ApplySystemEffect(effectdata);

	effectdata.duration_timer = DURATION_INFINITY;
	effectdata.type = kEffect_Special_Invisible;
	effectdata.value = 0.05f;
	effectdata.interval = 1.f;
	effectdata.enable = true;

	effect.ApplySystemEffect(effectdata);

	ghostflag = false;
	isghost = true;
	beghostcount++;

	num_die++;
	int ratio = ceil(server.play_time) / 300 + 10;

	if (from)
	{
		Client *pclient = server.GetClient(from->recover_assist_uid);
		if(!pclient)
		{
			if(assist_uid[0] == from->uid)
			{
				pclient = server.GetClient(assist_uid[1]);
			}
			else
			{
				pclient = server.GetClient(assist_uid[0]);
			}

			if(pclient)
				assist_uid[0] = assist_uid[1] = pclient->uid;
			else
				assist_uid[0] = assist_uid[1] = 0;
		}
		else
		{
			assist_uid[0] = assist_uid[1] = from->recover_assist_uid;
		}

		if(pclient && pclient->uid != from->uid && pclient->uid != uid)
		{
			log_write(LOG_DEBUG5,"calc score by killer and assist");
			from->OnKill(this, part, *weapon, pclient->uid, boost, sustainhurt);
		}
		else
		{
			log_write(LOG_DEBUG5,"calc score by killer no assist");
			from->OnKill(this, part, *weapon, 0, boost, sustainhurt);
		}
	}

	

	spawntimebySurvival = beghostcount * 2 * ratio;

	m_character_info.survivalbag.clear();

	for (uint i = 0; i < max_client_count; i++)
	{
		Client * c = server.clients + i;

		if (c->IsReady())
		{
			c->BeginWrite();
			c->WriteByte(SM_SurvivalMode_Ghost);
			c->WriteByte(uid);
			c->WriteInt(beghostcount * 2 * ratio);
			c->EndWrite();
		}
	}
}

bool Client::CanBeGhost()
{
	return !isghost;
}
