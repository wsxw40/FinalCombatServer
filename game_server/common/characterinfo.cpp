#include "characterinfo.h"
#include "log.h"

int GetWeaponDamageType(int weapon_type)
{
	int type = kWeaponDamageNormal;
	
	switch (weapon_type)
	{
		case kWeaponTypePistol:
		case kWeaponTypeSubMachineGun:
		case kWeaponTypeRifle:
		case kWeaponTypeSniperGun:
		case kWeaponTypeShotGun:
		case kWeaponTypeMiniMachineGun:
		case kWeaponTypeMiniGun:
		case kWeaponTypeDualPistol:
		case kWeaponTypeGunTowerBuilder:
			type = kWeaponDamageBullet;
			break;
		
		case kWeaponTypeFlameGun:
			type = kWeaponDamageFlame;
			break;
		
		case kWeaponTypeGrenade:
		case kWeaponTypeSpecial:
		case kWeaponTypeAmmoRocket:
		case kWeaponTypeAmmoGrenade:
		case kWeaponTypeAmmoStick:
		case kWeaponTypeBomb:
			type = kWeaponDamageExplode;
			break;
			
		case kWeaponTypeKnife:
		case kWeaponTypeAmmoMeditNeedle:
		case kWeaponTypeAmmoArrow:
		case kWeaponTypeAmmoBloodDisk:
		case kWeaponTypeZombieGun:
		case kWeaponTypeAmmoProd:
			type = kWeaponDamageClose;
			break;
		
		default:
			break;
	}
	
	return type;
}

bool Weapon_IsGun(int weapon_type)
{
	switch (weapon_type)
	{
		case kWeaponTypePistol:
		case kWeaponTypeSubMachineGun:
		case kWeaponTypeRifle:
		case kWeaponTypeSniperGun:
		case kWeaponTypeShotGun:
		case kWeaponTypeMiniMachineGun:
		case kWeaponTypeMiniGun:
		case kWeaponTypeDualPistol:
		case kWeaponTypeMeditNeedleGun:
		case kWeaponTypeSignal:
		case kWeaponTypeCureGun:
		case kWeaponTypeFlameGun:
		case kWeaponTypeLuncher:
			return true;
	}
	
	return false;
}

bool Weapon_IsKnife(int weapon_type)
{
	switch (weapon_type)
	{
		case kWeaponTypeKnife:
		case kWeaponTypeZombieGun:
			return true;
	}
	
	return false;
}

static bool WriteBaseItemInfo(BinaryStream & stream, const BaseItemInfo & info)
{
	stream.WriteInt(info.sid);
	if (info.sid)
	{
		stream.WriteInt(info.player_item_id);
		
		stream.WriteString(info.display_name);
		stream.WriteString(info.name);
		
		stream.WriteByte(info.color);
		stream.WriteByte(info.level);
		stream.WriteByte(info.star);
		stream.WriteByte(info.durable);
		
		stream.WriteByte((byte)info.attrs.size());
		for (uint i = 0; i < info.attrs.size(); i++)
		{
			const AttributeList &attributelist = info.attrs[i];
			stream.WriteByte((byte)attributelist.size());
			
			for (uint j = 0; j < attributelist.size(); j++)
			{
				stream.WriteShort(attributelist[j].type);
				stream.WriteShort(attributelist[j].value1);
				stream.WriteShort(attributelist[j].value2);
				stream.WriteShort(attributelist[j].time);
			}
		}
		
		stream.WriteInt(info.part_count);
		for (int i = 0; i < info.part_count; ++i)
			stream.WriteString(info.part_key[i]);
			
		log_write(LOG_DEBUG5, "WriteBaseItemInfo() info.player_item_id : %d, info.display_name : %s, part_count : %d", 
				info.player_item_id, info.display_name, info.part_count);
			
		return true;
	}
	
	return false;
}

static bool ReadBaseItemInfo(BinaryStream & stream, BaseItemInfo & info)
{
	stream.ReadInt(info.sid);
	if (info.sid)
	{
		stream.ReadInt(info.player_item_id);
		
		stream.ReadString(info.display_name, sizeof(info.display_name));
		stream.ReadString(info.name, sizeof(info.name));
		
		stream.ReadByte(info.color);
		stream.ReadByte(info.level);
		stream.ReadByte(info.star);
		stream.ReadByte(info.durable);

		byte size = 0;
		stream.ReadByte(size);
		info.attrs.resize(size);
		for (byte i = 0; i < size; i++)
		{
			byte size2 = 0;
			stream.ReadByte(size2);
			AttributeList &attributelist = info.attrs[i];
			attributelist.resize(size2);
			for (byte j = 0; j < size2; j++)
			{
				stream.ReadShort(attributelist[j].type);
				stream.ReadShort(attributelist[j].value1);
				stream.ReadShort(attributelist[j].value2);
				stream.ReadShort(attributelist[j].time);
			}
		}
		
		stream.ReadInt(info.part_count);
		for (int i = 0; i < info.part_count; ++i)
		{
			stream.ReadString(info.part_key[i], sizeof(info.part_key[i]));
		}
		
		log_write(LOG_DEBUG5, "ReadBaseItemInfo() info.player_item_id : %d, info.display_name : %s, part_count : %d", 
				info.player_item_id, info.display_name, info.part_count);
		
		return true;
	}
	
	return false;
}

static void WriteWeaponInfo(BinaryStream & stream, const WeaponInfo & info)
{
	stream.WriteInt(info.type);

	if (info.type)
	{
		stream.WriteFloat(info.change_in_time);
		stream.WriteFloat(info.move_speed_offset);
		stream.WriteFloat(info.cross_offset);
		stream.WriteFloat(info.cross_length_base);
		stream.WriteFloat(info.cross_length_factor);
		stream.WriteFloat(info.cross_distance_base);
		stream.WriteFloat(info.cross_distance_factor);

		stream.WriteFloat(info.hit_speed);
		stream.WriteFloat(info.hit_acceleration);
		stream.WriteFloat(info.hit_distance);
		stream.WriteInt(info.hit_crit);
		stream.WriteInt(info.hit_crit_head);		
		stream.WriteInt(info.damage_modifier);
		
		stream.WriteFloat(info.time_to_idle);
		
		stream.WriteInt(info.combat_power);
		stream.WriteFloat(info.hitdamage_gui);
		stream.WriteFloat(info.hitspeed_gui);
	}
}

static void ReadWeaponInfo(BinaryStream & stream, WeaponInfo & info)
{
	info.time_to_idlecount = 0;
	
	//log_write(LOG_DEBUG5, "info type before : %d", info.type);
	if (info.type == kWeaponTypeNone)
		stream.ReadInt(info.type);

	if (info.type)
	{
		stream.ReadFloat(info.change_in_time);
		stream.ReadFloat(info.move_speed_offset);
		stream.ReadFloat(info.cross_offset);
		stream.ReadFloat(info.cross_length_base);

		stream.ReadFloat(info.cross_length_factor);
		stream.ReadFloat(info.cross_distance_base);
		stream.ReadFloat(info.cross_distance_factor);
		stream.ReadFloat(info.hit_speed);
		stream.ReadFloat(info.hit_acceleration);
		stream.ReadFloat(info.hit_distance);
		stream.ReadInt(info.hit_crit);
		stream.ReadInt(info.hit_crit_head);		
		stream.ReadInt(info.damage_modifier);

		stream.ReadFloat(info.time_to_idle);
		
		stream.ReadInt(info.combat_power);
		stream.ReadFloat(info.hitdamage_gui);
		stream.ReadFloat(info.hitspeed_gui);
	}
}

static void WriteGunInfo(BinaryStream & stream, const GunInfo & info)
{
	WriteWeaponInfo(stream, info);

	if (info.type)
	{
		stream.WriteInt(info.accuracy_divisor);
		stream.WriteFloat(info.accuracy_offset);
		stream.WriteFloat(info.max_inaccuracy);

		stream.WriteInt(info.penetration);
		stream.WriteInt(info.damage);

		stream.WriteFloat(info.range_modifier);
		stream.WriteFloat(info.range_start);
		stream.WriteFloat(info.range_end);

		stream.WriteFloat(info.fire_time);
		stream.WriteFloat(info.reload_time);

		stream.WriteInt(info.ammo_default_one_clip);
		stream.WriteInt(info.ammo_default_count);

		stream.WriteByte((byte&)info.auto_fire);

		stream.WriteFloat(info.normal_offset);
		stream.WriteFloat(info.normal_factor);
		stream.WriteFloat(info.onair_offset);
		stream.WriteFloat(info.onair_factor);
		stream.WriteFloat(info.move_offset);
		stream.WriteFloat(info.move_factor);

		stream.WriteInt(info.sight_count);
		for (int i = 0; i < info.sight_count; i++)
		{
			stream.WriteFloat(info.sight_set[i].level);
			stream.WriteFloat(info.sight_set[i].sensitivity);
			stream.WriteFloat(info.sight_set[i].speed_offset);
			stream.WriteFloat(info.sight_set[i].fire_speed_factor);
			stream.WriteFloat(info.sight_set[i].kickbackscale);
		}
	}
}

static void ReadGunInfo(BinaryStream & stream, GunInfo & info)
{
	ReadWeaponInfo(stream, info);
 
	if (info.type)
	{
		stream.ReadInt(info.accuracy_divisor);
		stream.ReadFloat(info.accuracy_offset);
		stream.ReadFloat(info.max_inaccuracy);

		stream.ReadInt(info.penetration);
		stream.ReadInt(info.damage);

		stream.ReadFloat(info.range_modifier);
		stream.ReadFloat(info.range_start);
		stream.ReadFloat(info.range_end);

		stream.ReadFloat(info.fire_time);
		stream.ReadFloat(info.reload_time);

		stream.ReadInt(info.ammo_default_one_clip);
		info.ammo_one_clip = info.ammo_default_one_clip;
		info.ammo_in_clip = info.ammo_one_clip;

		stream.ReadInt(info.ammo_default_count);
		info.ammo_count = info.ammo_default_count;
		info.ammo_count_current = info.ammo_count;

		stream.ReadByte((byte&)info.auto_fire);

		stream.ReadFloat(info.normal_offset);
		stream.ReadFloat(info.normal_factor);
		stream.ReadFloat(info.onair_offset);
		stream.ReadFloat(info.onair_factor);
		stream.ReadFloat(info.move_offset);
		stream.ReadFloat(info.move_factor);
		
		//log_write(LOG_DEBUG5, "move_factor %f",info.move_factor);

		stream.ReadInt(info.sight_count);
		for (int i = 0; i < info.sight_count; i++)
		{
			stream.ReadFloat(info.sight_set[i].level);
			stream.ReadFloat(info.sight_set[i].sensitivity);
			stream.ReadFloat(info.sight_set[i].speed_offset);
			stream.ReadFloat(info.sight_set[i].fire_speed_factor);
			stream.ReadFloat(info.sight_set[i].kickbackscale);
		}
	}
}

static void WriteRifleInfo(BinaryStream & stream, const RifleInfo & info)
{
	WriteGunInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.normal_up_base);
		stream.WriteFloat(info.normal_lateral_base);
		stream.WriteFloat(info.normal_up_modifier);
		stream.WriteFloat(info.normal_lateral_modifier);
		stream.WriteFloat(info.normal_up_max);
		stream.WriteFloat(info.normal_lateral_max);
		stream.WriteFloat(info.normal_dir_change);

		stream.WriteFloat(info.move_up_base);
		stream.WriteFloat(info.move_lateral_base);
		stream.WriteFloat(info.move_up_modifier);
		stream.WriteFloat(info.move_lateral_modifier);
		stream.WriteFloat(info.move_up_max);
		stream.WriteFloat(info.move_lateral_max);
		stream.WriteFloat(info.move_dir_change);

		stream.WriteFloat(info.onair_up_base);
		stream.WriteFloat(info.onair_lateral_base);
		stream.WriteFloat(info.onair_up_modifier);
		stream.WriteFloat(info.onair_lateral_modifier);
		stream.WriteFloat(info.onair_up_max);
		stream.WriteFloat(info.onair_lateral_max);
		stream.WriteFloat(info.onair_dir_change);

		stream.WriteFloat(info.crouch_up_base);
		stream.WriteFloat(info.crouch_lateral_base);
		stream.WriteFloat(info.crouch_up_modifier);
		stream.WriteFloat(info.crouch_lateral_modifier);
		stream.WriteFloat(info.crouch_up_max);
		stream.WriteFloat(info.crouch_lateral_max);
		stream.WriteFloat(info.crouch_dir_change);
	}
}

static void ReadRifleInfo(BinaryStream & stream, RifleInfo & info)
{
	ReadGunInfo(stream, info);
	
	//log_write(LOG_DEBUG5, "info type : %d",info.type);
	if (info.type)
	{
		stream.ReadFloat(info.normal_up_base);
		//log_write(LOG_DEBUG5, "normal_up_base : %f",info.normal_up_base);
		stream.ReadFloat(info.normal_lateral_base);
		stream.ReadFloat(info.normal_up_modifier);
		stream.ReadFloat(info.normal_lateral_modifier);
		stream.ReadFloat(info.normal_up_max);
		stream.ReadFloat(info.normal_lateral_max);
		stream.ReadFloat(info.normal_dir_change);

		stream.ReadFloat(info.move_up_base);
		stream.ReadFloat(info.move_lateral_base);
		stream.ReadFloat(info.move_up_modifier);
		stream.ReadFloat(info.move_lateral_modifier);
		stream.ReadFloat(info.move_up_max);
		stream.ReadFloat(info.move_lateral_max);
		stream.ReadFloat(info.move_dir_change);

		stream.ReadFloat(info.onair_up_base);
		stream.ReadFloat(info.onair_lateral_base);
		stream.ReadFloat(info.onair_up_modifier);
		stream.ReadFloat(info.onair_lateral_modifier);
		stream.ReadFloat(info.onair_up_max);
		stream.ReadFloat(info.onair_lateral_max);
		stream.ReadFloat(info.onair_dir_change);

		stream.ReadFloat(info.crouch_up_base);
		stream.ReadFloat(info.crouch_lateral_base);
		stream.ReadFloat(info.crouch_up_modifier);
		stream.ReadFloat(info.crouch_lateral_modifier);
		stream.ReadFloat(info.crouch_up_max);
		stream.ReadFloat(info.crouch_lateral_max);
		stream.ReadFloat(info.crouch_dir_change);
	}
}

static void WritePistolInfo(BinaryStream & stream, const PistolInfo & info)
{
	WriteGunInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.up_modifier);

		stream.WriteFloat(info.accuracy_time);
		stream.WriteFloat(info.accuracy_time_modifier);
		stream.WriteFloat(info.max_accuracy);
		stream.WriteFloat(info.min_accuracy);

		stream.WriteFloat(info.normal_up_base);
		stream.WriteFloat(info.normal_lateral_base);
		stream.WriteFloat(info.normal_up_modifier);
		stream.WriteFloat(info.normal_lateral_modifier);
		stream.WriteFloat(info.normal_up_max);
		stream.WriteFloat(info.normal_lateral_max);
		stream.WriteFloat(info.normal_dir_change);

		stream.WriteFloat(info.move_up_base);
		stream.WriteFloat(info.move_lateral_base);
		stream.WriteFloat(info.move_up_modifier);
		stream.WriteFloat(info.move_lateral_modifier);
		stream.WriteFloat(info.move_up_max);
		stream.WriteFloat(info.move_lateral_max);
		stream.WriteFloat(info.move_dir_change);

		stream.WriteFloat(info.onair_up_base);
		stream.WriteFloat(info.onair_lateral_base);
		stream.WriteFloat(info.onair_up_modifier);
		stream.WriteFloat(info.onair_lateral_modifier);
		stream.WriteFloat(info.onair_up_max);
		stream.WriteFloat(info.onair_lateral_max);
		stream.WriteFloat(info.onair_dir_change);

		stream.WriteFloat(info.crouch_up_base);
		stream.WriteFloat(info.crouch_lateral_base);
		stream.WriteFloat(info.crouch_up_modifier);
		stream.WriteFloat(info.crouch_lateral_modifier);
		stream.WriteFloat(info.crouch_up_max);
		stream.WriteFloat(info.crouch_lateral_max);
		stream.WriteFloat(info.crouch_dir_change);
	}
}

static void ReadPistolInfo(BinaryStream & stream, PistolInfo & info)
{
	ReadGunInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.up_modifier);
		
		stream.ReadFloat(info.accuracy_time);
		stream.ReadFloat(info.accuracy_time_modifier);
		stream.ReadFloat(info.max_accuracy);
		stream.ReadFloat(info.min_accuracy);

		stream.ReadFloat(info.normal_up_base);
		//log_write(LOG_DEBUG5, "normal_up_base : %f",info.normal_up_base);
		stream.ReadFloat(info.normal_lateral_base);
		stream.ReadFloat(info.normal_up_modifier);
		stream.ReadFloat(info.normal_lateral_modifier);
		stream.ReadFloat(info.normal_up_max);
		stream.ReadFloat(info.normal_lateral_max);
		stream.ReadFloat(info.normal_dir_change);

		stream.ReadFloat(info.move_up_base);
		stream.ReadFloat(info.move_lateral_base);
		stream.ReadFloat(info.move_up_modifier);
		stream.ReadFloat(info.move_lateral_modifier);
		stream.ReadFloat(info.move_up_max);
		stream.ReadFloat(info.move_lateral_max);
		stream.ReadFloat(info.move_dir_change);

		stream.ReadFloat(info.onair_up_base);
		stream.ReadFloat(info.onair_lateral_base);
		stream.ReadFloat(info.onair_up_modifier);
		stream.ReadFloat(info.onair_lateral_modifier);
		stream.ReadFloat(info.onair_up_max);
		stream.ReadFloat(info.onair_lateral_max);
		stream.ReadFloat(info.onair_dir_change);

		stream.ReadFloat(info.crouch_up_base);
		stream.ReadFloat(info.crouch_lateral_base);
		stream.ReadFloat(info.crouch_up_modifier);
		stream.ReadFloat(info.crouch_lateral_modifier);
		stream.ReadFloat(info.crouch_up_max);
		stream.ReadFloat(info.crouch_lateral_max);
		stream.ReadFloat(info.crouch_dir_change);
	}
}

static void WriteDualPistol(BinaryStream & stream, const DualPistolInfo & info)
{
	WritePistolInfo(stream, info);
}

static void ReadDualPistol(BinaryStream & stream, DualPistolInfo & info)
{
	ReadPistolInfo(stream, info);
}

static void WriteSniperGunInfo(BinaryStream & stream, const SniperGunInfo & info)
{
	WriteGunInfo(stream, info);

	if (info.type)
	{
	/*	stream.WriteFloat(info.normal_up_base);
		stream.WriteFloat(info.normal_lateral_base);
		stream.WriteFloat(info.normal_up_modifier);
		stream.WriteFloat(info.normal_lateral_modifier);
		stream.WriteFloat(info.normal_up_max);
		stream.WriteFloat(info.normal_lateral_max);
		stream.WriteFloat(info.normal_dir_change);

		stream.WriteFloat(info.sight_normal_offset);
		stream.WriteFloat(info.sight_onair_offset);
		stream.WriteFloat(info.sight_move_offset);*/
		stream.WriteFloat(info.sight_normal_offset);
		stream.WriteFloat(info.sight_onair_offset);
		stream.WriteFloat(info.sight_move_offset);
		stream.WriteFloat(info.readytime);
	}
}

static void ReadSniperGunInfo(BinaryStream & stream, SniperGunInfo & info)
{
	ReadGunInfo(stream, info);

	if (info.type)
	{
		//stream.ReadFloat(info.normal_up_base);
		//stream.ReadFloat(info.normal_lateral_base);
		//stream.ReadFloat(info.normal_up_modifier);
		//stream.ReadFloat(info.normal_lateral_modifier);
		//stream.ReadFloat(info.normal_up_max);
		//stream.ReadFloat(info.normal_lateral_max);
		//stream.ReadFloat(info.normal_dir_change);

		//stream.ReadFloat(info.sight_normal_offset);
		//stream.ReadFloat(info.sight_onair_offset);
		//stream.ReadFloat(info.sight_move_offset);
		stream.ReadFloat(info.sight_normal_offset);
		stream.ReadFloat(info.sight_onair_offset);
		stream.ReadFloat(info.sight_move_offset);
		stream.ReadFloat(info.readytime);
	}
}

static void WriteShotGunInfo(BinaryStream & stream, const ShotGunInfo & info)
{
	WriteGunInfo(stream, info);

	if (info.type)
	{
		stream.WriteInt(info.shoot_bullet_count);
		stream.WriteFloat(info.spread);
		stream.WriteFloat(info.normal_up_base);
		stream.WriteFloat(info.normal_up_modifier);
		stream.WriteFloat(info.normal_up_max);
	}
}

static void ReadShotGunInfo(BinaryStream & stream, ShotGunInfo & info)
{
	ReadGunInfo(stream, info);

	if (info.type)
	{
		stream.ReadInt(info.shoot_bullet_count);
		stream.ReadFloat(info.spread);
		stream.ReadFloat(info.normal_up_base);
		stream.ReadFloat(info.normal_up_modifier);
		stream.ReadFloat(info.normal_up_max);
	}
}

static void WriteSubMachineGunInfo(BinaryStream & stream, const SubMachineGunInfo & info)
{
	WriteRifleInfo(stream, info);
}

static void ReadSubMachineGunInfo(BinaryStream & stream, SubMachineGunInfo & info)
{
	ReadRifleInfo(stream, info);
}

static void WriteMachineGunInfo(BinaryStream & stream, const MachineGunInfo & info)
{
	WriteRifleInfo(stream, info);
}

static void ReadMachineGunInfo(BinaryStream & stream, MachineGunInfo & info)
{
	ReadRifleInfo(stream, info);
}

static void WriteMiniGunInfo(BinaryStream & stream, const MiniGunInfo & info)
{
	WriteRifleInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.fire_max_speed);
		stream.WriteFloat(info.fire_start_speed);
		stream.WriteFloat(info.fire_accleration);
		stream.WriteFloat(info.fire_resistance);
	}
}

static void ReadMiniGunInfo(BinaryStream & stream, MiniGunInfo & info)
{
	ReadRifleInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.fire_max_speed);
		stream.ReadFloat(info.fire_start_speed);
		stream.ReadFloat(info.fire_accleration);
		stream.ReadFloat(info.fire_resistance);
	}
}

static void WriteKnifeInfo(BinaryStream & stream, const KnifeInfo & info)
{
	WriteWeaponInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.stab_time);
		stream.WriteFloat(info.stab_light_time);

		stream.WriteFloat(info.stab_distance);
		stream.WriteFloat(info.stab_light_distance);
		stream.WriteFloat(info.stab_width);
		stream.WriteFloat(info.back_factor);

		stream.WriteFloat(info.stab_hurt);
		stream.WriteFloat(info.stab_light_hurt);
		stream.WriteInt(info.back_boost_plus);
	}
}

static void ReadKnifeInfo(BinaryStream & stream, KnifeInfo & info)
{
	ReadWeaponInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.stab_time);
		stream.ReadFloat(info.stab_light_time);

		stream.ReadFloat(info.stab_distance);
		stream.ReadFloat(info.stab_light_distance);
		stream.ReadFloat(info.stab_width);
		stream.ReadFloat(info.back_factor);

		stream.ReadFloat(info.stab_hurt);
		stream.ReadFloat(info.stab_light_hurt);
		stream.ReadInt(info.back_boost_plus);
		
	}
}

static void WriteThrowableInfo(BinaryStream & stream, const ThrowableInfo & info)
{
	WriteWeaponInfo(stream, info);

	if (info.type)
	{
	 	stream.WriteFloat(info.explode_time);
		stream.WriteFloat(info.ready_time);
		stream.WriteFloat(info.throw_out_time);
	}
}

static void ReadThrowableInfo(BinaryStream & stream, ThrowableInfo & info)
{
	ReadWeaponInfo(stream, info);

	if (info.type)
	{
	 	stream.ReadFloat(info.explode_time);
		stream.ReadFloat(info.ready_time);
		stream.ReadFloat(info.throw_out_time);
	}
}

static void WriteGrenadeInfo(BinaryStream & stream, const GrenadeInfo & info)
{
	WriteThrowableInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.range);
		stream.WriteFloat(info.hurt);
	}
}

static void ReadGrenadeInfo(BinaryStream & stream, GrenadeInfo & info)
{
	ReadThrowableInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.range);
		stream.ReadFloat(info.hurt);
	}
}

static void WriteFlashInfo(BinaryStream & stream, const FlashInfo & info)
{
	WriteThrowableInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.range_start);
		stream.WriteFloat(info.range_end);
		stream.WriteFloat(info.time_max);
		stream.WriteFloat(info.time_fade);
		stream.WriteFloat(info.back_factor);
	} 
}

static void ReadFlashInfo(BinaryStream & stream, FlashInfo & info)
{
	ReadThrowableInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.range_start);
		stream.ReadFloat(info.range_end);
		stream.ReadFloat(info.time_max);
		stream.ReadFloat(info.time_fade);
		stream.ReadFloat(info.back_factor);
	}
}

static void WriteSmokeInfo(BinaryStream & stream, const SmokeInfo & info)
{
	WriteThrowableInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.time);
	}
}

static void ReadSmokeInfo(BinaryStream & stream, SmokeInfo & info)
{
	ReadThrowableInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.time);
	}
}


//////////////////////////////////////GL Add Start/////////////////////////////////////////

static void WriteFlameGunInfo(BinaryStream & stream, const FlameGunInfo & info)
{
	WriteGunInfo(stream, info);

	if(info.type)
	{
		stream.WriteFloat(info.special_distance);
		stream.WriteFloat(info.special_range);
		stream.WriteFloat(info.special_lasttime);
		stream.WriteFloat(info.particlenum);
		stream.WriteFloat(info.show_speed);
		stream.WriteFloat(info.hurtrange);
	}
}

static void ReadFlameGunInfo(BinaryStream & stream, FlameGunInfo & info)
{
	ReadGunInfo(stream, info);

	if(info.type)
	{

		stream.ReadFloat(info.special_distance);
		stream.ReadFloat(info.special_range);

		stream.ReadFloat(info.special_lasttime);
		stream.ReadFloat(info.particlenum);
		stream.ReadFloat(info.show_speed);
		stream.ReadFloat(info.hurtrange);
	}
}

static void WriteLuncherInfo(BinaryStream & stream, const LuncherInfo & info)
{
	WriteGunInfo(stream, info);

	if (info.type)
	{
		stream.WriteInt(info.ammo_type);
		stream.WriteFloat(info.fly_speed);
		stream.WriteFloat(info.spread);
		stream.WriteFloat(info.normal_up_base);
		stream.WriteFloat(info.normal_up_modifier);
		stream.WriteFloat(info.normal_up_max);
		stream.WriteFloat(info.maxalive_time);
		stream.WriteByte(info.gravity);
		stream.WriteFloat(info.hurt);
		stream.WriteString(info.ammopart_key);
		stream.WriteFloat(info.range);
		stream.WriteFloat(info.throwouttime);
		stream.WriteFloat(info.dmg_modify_timer_min);
		stream.WriteFloat(info.dmg_modify_timer_max);
		stream.WriteFloat(info.dmg_modify_min);
		stream.WriteFloat(info.dmg_modify_max);
		stream.WriteFloat(info.capsule_height);
		stream.WriteFloat(info.capsule_radius);


		// ammo hide -----------------------	
		stream.WriteFloat(info.ammo_hide_time);

		// charge shoot --------------------
		stream.WriteFloat(info.ammo_charge_time_max);
		stream.WriteFloat(info.ammo_charge_time_effective);
		stream.WriteFloat(info.ammo_charge_time_stable);

		stream.WriteFloat(info.ammo_spread_multiple);
		stream.WriteFloat(info.ammo_power_multiple);
		stream.WriteFloat(info.ammo_gravity_addon);

		stream.WriteFloat(info.ammo_blood_disk_interval);
		stream.WriteInt(info.ammo_blood_disk_hit_count);
	}
}

static void ReadLuncherInfo(BinaryStream & stream, LuncherInfo & info)
{
	ReadGunInfo(stream, info);

	if (info.type)
	{
		stream.ReadInt(info.ammo_type);
		log_write(LOG_DEBUG1, "Luncher ammo_type=%d fly_speed=%f maxalive=%f gravity=%d ammopart=%s range=%f capsule_h=%f capsule_r=%f",
			info.ammo_type, info.fly_speed, info.maxalive_time, (int)info.gravity, info.ammopart_key, info.range, info.capsule_height, info.capsule_radius);
		stream.ReadFloat(info.fly_speed);
		stream.ReadFloat(info.spread);
		stream.ReadFloat(info.normal_up_base);
		stream.ReadFloat(info.normal_up_modifier);
		stream.ReadFloat(info.normal_up_max);
		stream.ReadFloat(info.maxalive_time);
		stream.ReadByte(info.gravity);
		stream.ReadFloat(info.hurt);
		stream.ReadString(info.ammopart_key, sizeof(info.ammopart_key));
		stream.ReadFloat(info.range);
		stream.ReadFloat(info.throwouttime);
		stream.ReadFloat(info.dmg_modify_timer_min);
		stream.ReadFloat(info.dmg_modify_timer_max);
		stream.ReadFloat(info.dmg_modify_min);
		stream.ReadFloat(info.dmg_modify_max);
		stream.ReadFloat(info.capsule_height);
		stream.ReadFloat(info.capsule_radius);

		// ammo hide -----------------------	
		stream.ReadFloat(info.ammo_hide_time);

		// charge shoot --------------------
		stream.ReadFloat(info.ammo_charge_time_max);
		stream.ReadFloat(info.ammo_charge_time_effective);
		stream.ReadFloat(info.ammo_charge_time_stable);

		stream.ReadFloat(info.ammo_spread_multiple);
		stream.ReadFloat(info.ammo_power_multiple);
		stream.ReadFloat(info.ammo_gravity_addon);

		stream.ReadFloat(info.ammo_blood_disk_interval);
		stream.ReadInt(info.ammo_blood_disk_hit_count);
	}
}

static void WriteCureGunInfo(BinaryStream & stream, const CureGunInfo & info)
{
	WriteGunInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.max_distance);
		stream.WriteInt(info.add_blood);
	}
}

static void ReadCureGunInfo(BinaryStream & stream, CureGunInfo & info)
{
	ReadGunInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.max_distance);
		stream.ReadInt(info.add_blood);
	}
}

static void WriteMiniMachineGunInfo(BinaryStream & stream, const MiniMachineGunInfo & info)
{
	WriteShotGunInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.fire_max_speed);
		stream.WriteFloat(info.fire_start_speed);
		stream.WriteFloat(info.fire_aceleration);
		stream.WriteFloat(info.fire_resistance);
		stream.WriteFloat(info.readytime);
		
		stream.WriteInt(info.ammo_type);
		stream.WriteFloat(info.ammo_charge_time_max);
		for (int i = 0; i < 3; i++)
			stream.WriteFloat(info.fly_speed[i]);
		for (int i = 0; i < 3; i++)
			stream.WriteFloat(info.fly_speed_multiple[i]);
		
		stream.WriteFloat(info.maxalive_time);
		stream.WriteByte(info.gravity);
		stream.WriteFloat(info.hurt);
		stream.WriteString(info.ammopart_key);
		stream.WriteFloat(info.range);
		stream.WriteFloat(info.dmg_modify_timer_min);
		stream.WriteFloat(info.dmg_modify_timer_max);
		stream.WriteFloat(info.dmg_modify_min);
		stream.WriteFloat(info.dmg_modify_max);
		stream.WriteFloat(info.capsule_height);
		stream.WriteFloat(info.capsule_radius);
	}
}

static void ReadMiniMachineGunInfo(BinaryStream & stream, MiniMachineGunInfo & info)
{
	ReadShotGunInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.fire_max_speed);
		stream.ReadFloat(info.fire_start_speed);
		stream.ReadFloat(info.fire_aceleration);
		stream.ReadFloat(info.fire_resistance);
		stream.ReadFloat(info.readytime);
		
		stream.ReadInt(info.ammo_type);
		stream.ReadFloat(info.ammo_charge_time_max);
		for (int i = 0; i < 3; i++)
			stream.ReadFloat(info.fly_speed[i]);
		for (int i = 0; i < 3; i++)
			stream.ReadFloat(info.fly_speed_multiple[i]);
		
		stream.ReadFloat(info.maxalive_time);
		stream.ReadByte(info.gravity);
		stream.ReadFloat(info.hurt);
		stream.ReadString(info.ammopart_key, sizeof(info.ammopart_key));
		stream.ReadFloat(info.range);
		stream.ReadFloat(info.dmg_modify_timer_min);
		stream.ReadFloat(info.dmg_modify_timer_max);
		stream.ReadFloat(info.dmg_modify_min);
		stream.ReadFloat(info.dmg_modify_max);
		stream.ReadFloat(info.capsule_height);
		stream.ReadFloat(info.capsule_radius);
	}
}

static void WriteBowInfo(BinaryStream & stream, const BowInfo & info)
{
	WriteGunInfo(stream, info);
}
static void ReadBowInfo(BinaryStream & stream, BowInfo & info)
{
	ReadGunInfo(stream, info);
}


static void WriteAmmoInfo(BinaryStream & stream, const AmmoInfo & info)
{
	WriteWeaponInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.maxalive_time);
		stream.WriteByte(info.gravity);
		stream.WriteFloat(info.range);
		stream.WriteFloat(info.damage);
		stream.WriteFloat(info.dmg_modify_timer_min);
		stream.WriteFloat(info.dmg_modify_timer_max);
		stream.WriteFloat(info.dmg_modify_min);
		stream.WriteFloat(info.dmg_modify_max);
		stream.WriteFloat(info.capsule_height);
		stream.WriteFloat(info.capsule_radius);

	}
}

static void ReadAmmoInfo(BinaryStream & stream, AmmoInfo & info)
{
	ReadWeaponInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.maxalive_time);
		stream.ReadByte(info.gravity);
		stream.ReadFloat(info.range);
		stream.ReadFloat(info.damage);
		stream.ReadFloat(info.dmg_modify_timer_min);
		stream.ReadFloat(info.dmg_modify_timer_max);
		stream.ReadFloat(info.dmg_modify_min);
		stream.ReadFloat(info.dmg_modify_max);
		stream.ReadFloat(info.capsule_height);
		stream.ReadFloat(info.capsule_radius);
	}
}


static void WriteDrumInfo(BinaryStream & stream, const DrumInfo & info)
{
	WriteGunInfo(stream, info);
	if (info.type)
	{
		stream.WriteFloat(info.range);
	}
}

static void ReadDrumInfo(BinaryStream & stream, DrumInfo & info)
{
	ReadGunInfo(stream, info);
	if (info.type)
	{
		stream.ReadFloat(info.range);
	}
}
static void WriteMilkbottleInfo(BinaryStream & stream, const MilkbottleInfo & info)
{
	WriteGunInfo(stream, info);
	if (info.type)
	{
		stream.WriteFloat(info.range);
	}
}


static void ReadMilkbottleInfo(BinaryStream & stream, MilkbottleInfo & info)
{
	ReadGunInfo(stream, info);
	if (info.type)
	{
		stream.ReadFloat(info.range);
	}
}
static void WriteEquipmentInfo(BinaryStream & stream, const EquipmentInfo & info)
{
	WriteWeaponInfo(stream, info);
}

static void ReadEquipmentInfo(BinaryStream & stream, EquipmentInfo & info)
{
	ReadWeaponInfo(stream, info);
}

static void WriteBombInfo(BinaryStream & stream, const BombInfo & info)
{
	WriteWeaponInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.plant_time);
		stream.WriteFloat(info.defuse_time);
		stream.WriteFloat(info.defuse_with_item_time);
	}
}

static void ReadBombInfo(BinaryStream & stream, BombInfo & info)
{
	ReadWeaponInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.plant_time);
		stream.ReadFloat(info.defuse_time);
		stream.ReadFloat(info.defuse_with_item_time);
	}
}

static void WriteZombieGunInfo(BinaryStream & stream, const ZombieGunInfo & info)
{
	WriteWeaponInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.stab_time);
		stream.WriteFloat(info.stab_light_time);

		stream.WriteFloat(info.stab_distance);
		stream.WriteFloat(info.stab_light_distance);
		stream.WriteFloat(info.stab_width);
		stream.WriteFloat(info.back_factor);

		stream.WriteFloat(info.stab_hurt);
		stream.WriteFloat(info.stab_light_hurt);
		stream.WriteInt(info.back_boost_plus);

		stream.WriteFloat(info.skill_cooldown);
		stream.WriteFloat(info.skill_usetime);
	}
}

static void ReadZombieGunInfo(BinaryStream & stream, ZombieGunInfo & info)
{
	ReadWeaponInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.stab_time);
		stream.ReadFloat(info.stab_light_time);

		stream.ReadFloat(info.stab_distance);
		stream.ReadFloat(info.stab_light_distance);
		stream.ReadFloat(info.stab_width);
		stream.ReadFloat(info.back_factor);

		stream.ReadFloat(info.stab_hurt);
		stream.ReadFloat(info.stab_light_hurt);
		stream.ReadInt(info.back_boost_plus);

		stream.ReadFloat(info.skill_cooldown);
		stream.ReadFloat(info.skill_usetime);
	}
}

static void WriteZombieChargeInfo(BinaryStream & stream, const ZombieChargeInfo & info)
{
	WriteWeaponInfo(stream, info);

	if (info.type)
	{
		stream.WriteFloat(info.skill_cooldown);
		stream.WriteFloat(info.skill_usetime);
		stream.WriteFloat(info.skill_hurt);
	}
}

static void ReadZombieChargeInfo(BinaryStream & stream, ZombieChargeInfo & info)
{
	ReadWeaponInfo(stream, info);

	if (info.type)
	{
		stream.ReadFloat(info.skill_cooldown);
		stream.ReadFloat(info.skill_usetime);
		stream.ReadFloat(info.skill_hurt);
	}
}

static void WriteGunTowerBuilderInfo(BinaryStream & stream, const GunTowerBuilderInfo & info)
{
	WriteGunInfo(stream, info);
	if (info.type)
	{
		stream.WriteFloat(info.max_distance);
		stream.WriteFloat(info.max_lift_time);
		stream.WriteFloat(info.hurt_range);
		stream.WriteFloat(info.show_speed);
		stream.WriteFloat(info.move_speed);
		stream.WriteFloat(info.move_keep_time);
		stream.WriteFloat(info.recover_range);
		stream.WriteFloat(info.recover_check_interval);
		stream.WriteFloat(info.recover_per_count_life);
		stream.WriteFloat(info.recover_per_percent_ammo);
		stream.WriteFloat(info.recover_per_minus_ammo);
	}
}

static void ReadGunTowerBuilderInfo(BinaryStream & stream, GunTowerBuilderInfo & info)
{
	ReadGunInfo(stream, info);
	if (info.type)
	{
		stream.ReadFloat(info.max_distance);
		stream.ReadFloat(info.max_lift_time);
		stream.ReadFloat(info.hurt_range);
		stream.ReadFloat(info.show_speed);
		stream.ReadFloat(info.move_speed);
		stream.ReadFloat(info.move_keep_time);
		stream.ReadFloat(info.recover_range);
		stream.ReadFloat(info.recover_check_interval);
		stream.ReadFloat(info.recover_per_count_life);
		stream.ReadFloat(info.recover_per_percent_ammo);
		stream.ReadFloat(info.recover_per_minus_ammo);
	}
}

static void WriteGunTowerBuilderPlusInfo(BinaryStream & stream, const GunTowerBuilderPlusInfo & info)
{
	WriteGunInfo(stream, info);
	if (info.type)
	{
		stream.WriteFloat(info.max_distance);
		stream.WriteFloat(info.max_lift_time);
		stream.WriteFloat(info.hurt_range);
		stream.WriteFloat(info.show_speed);
		stream.WriteFloat(info.move_speed);
		stream.WriteFloat(info.move_keep_time);
		stream.WriteFloat(info.recover_range);
		stream.WriteFloat(info.recover_check_interval);
		stream.WriteFloat(info.recover_per_count_life);
		stream.WriteFloat(info.recover_per_percent_ammo);
		stream.WriteFloat(info.recover_per_minus_ammo);

		//plus info
		stream.WriteInt(info.m_iTowerGunCount);
		for(int i=0; i<info.m_iTowerGunCount; ++i)
		{	
			const GunTowerTypeInfo& oTypeInfo =info.m_aTowerTypeInfo[i];
			stream.WriteInt(oTypeInfo.DummyObjectType);
			stream.WriteInt(oTypeInfo.DummyObjectSubType);
			stream.WriteInt(oTypeInfo.SystemId);
			stream.WriteInt(oTypeInfo.MaxCount);
			stream.WriteFloat(oTypeInfo.Width);
			stream.WriteFloat(oTypeInfo.Height);
			stream.WriteString(oTypeInfo.ResKey);
		}
		stream.WriteInt(info.m_iMaxWallCount);
		stream.WriteInt(info.m_iMaxGuardCount);

	}
}

static void ReadGunTowerBuilderPlusInfo(BinaryStream & stream, GunTowerBuilderPlusInfo & info)
{
	ReadGunInfo(stream, info);
	if (info.type)
	{
		stream.ReadFloat(info.max_distance);
		stream.ReadFloat(info.max_lift_time);
		stream.ReadFloat(info.hurt_range);
		stream.ReadFloat(info.show_speed);
		stream.ReadFloat(info.move_speed);
		stream.ReadFloat(info.move_keep_time);
		stream.ReadFloat(info.recover_range);
		stream.ReadFloat(info.recover_check_interval);
		stream.ReadFloat(info.recover_per_count_life);
		stream.ReadFloat(info.recover_per_percent_ammo);
		stream.ReadFloat(info.recover_per_minus_ammo);

		//plus info
		stream.ReadInt(info.m_iTowerGunCount);
		if(info.m_iTowerGunCount > max_tower_gun_count){
			info.m_iTowerGunCount =max_tower_gun_count;
		}
		for(int i=0; i<info.m_iTowerGunCount; ++i)
		{	
			GunTowerTypeInfo& oTypeInfo =info.m_aTowerTypeInfo[i];
			stream.ReadInt(oTypeInfo.DummyObjectType);
			stream.ReadInt(oTypeInfo.DummyObjectSubType);
			stream.ReadInt(oTypeInfo.SystemId);
			stream.ReadInt(oTypeInfo.MaxCount);
			stream.ReadFloat(oTypeInfo.Width);
			stream.ReadFloat(oTypeInfo.Height);
			stream.ReadString(oTypeInfo.ResKey, sizeof(oTypeInfo.ResKey));
		}
		stream.ReadInt(info.m_iMaxWallCount);
		stream.ReadInt(info.m_iMaxGuardCount);
	}
}

void WriteWeapon(BinaryStream & stream, const Weapon & info)
{
	if (WriteBaseItemInfo(stream, info.base_info))
	{
		switch (info.weapon_data.weapon.type)
		{
		case kWeaponTypeNone:
			stream.WriteInt(0);
			break;
		
		case kWeaponTypePistol:
			WritePistolInfo(stream, info.weapon_data.pistol);
			break;

		case kWeaponTypeDualPistol:
			WriteDualPistol(stream, info.weapon_data.dual_pistol);
			break;

		case kWeaponTypeSubMachineGun:
			WriteSubMachineGunInfo(stream, info.weapon_data.sub_machine_gun);
			break;

		case kWeaponTypeRifle:
			WriteRifleInfo(stream, info.weapon_data.rifle);
			break;

		case kWeaponTypeSniperGun:
			WriteSniperGunInfo(stream, info.weapon_data.sniper);
			break;

		case kWeaponTypeShotGun:
			WriteShotGunInfo(stream, info.weapon_data.shot_gun);
			break;

		case kWeaponTypeMiniGun:
			WriteMiniGunInfo(stream, info.weapon_data.mini_gun);
			break;

		case kWeaponTypeKnife:
			WriteKnifeInfo(stream, info.weapon_data.knife);
			break;

		case kWeaponTypeGrenade:
		case kWeaponTypeSpecial:
			WriteGrenadeInfo(stream, info.weapon_data.grenade);
			break;

		case kWeaponTypeFlameGun:
			WriteFlameGunInfo(stream,info.weapon_data.flame_gun);
			break;
			
		case kWeaponTypeCureGun:
			WriteCureGunInfo(stream, info.weapon_data.cure_gun);
			break;

		case kWeaponTypeMiniMachineGun:
			WriteMiniMachineGunInfo(stream, info.weapon_data.minimachine_gun);
			break;

		case kWeaponTypeBow:
			WriteBowInfo(stream, info.weapon_data.bow);
			break;
			
		case kWeaponTypeMeditNeedleGun:
		case kWeaponTypeSignal:
		case kWeaponTypeLuncher:
			WriteLuncherInfo(stream, info.weapon_data.luncher);
			break;
			
		case kWeaponTypeAmmoRocket:
		case kWeaponTypeAmmoMeditNeedle:
		case kWeaponTypeAmmoGrenade:
		case kWeaponTypeAmmoStick:
		case kWeaponTypeAmmoArrow:
		case kWeaponTypeAmmoBloodDisk:
		case kWeaponTypeAmmoProd:
			WriteAmmoInfo(stream, info.weapon_data.ammo);
			break;

		case kWeaponTypeDrum:
			WriteDrumInfo(stream, info.weapon_data.drum);
			break;
			
		case kWeaponTypeMilkbottle:
			WriteMilkbottleInfo(stream, info.weapon_data.milkbottle);
			break;

		case kWeaponTypeFlash:
			WriteFlashInfo(stream, info.weapon_data.flash);
			break;

		case kWeaponTypeSmoke:
			WriteSmokeInfo(stream, info.weapon_data.smoke);
			break;

		case kWeaponTypeEquipment:
			WriteEquipmentInfo(stream, info.weapon_data.equipment);
			break;

		case kWeaponTypeBomb:
			WriteBombInfo(stream, info.weapon_data.bomb);
			break;

		case kWeaponTypeZombieGun:
			WriteZombieGunInfo(stream,info.weapon_data.zb_info);
			break;

		case kWeaponTypeZombieCharge:
			WriteZombieChargeInfo(stream,info.weapon_data.zbc_info);
			break;
		case kWeaponTypeGunTowerBuilder:
			WriteGunTowerBuilderInfo(stream, info.weapon_data.guntower_info);
			break;
		case kWeaponTypeGunTowerBuilderPlus:
			WriteGunTowerBuilderPlusInfo(stream, info.weapon_data.guntower_plus_info);
			break;

		default:
			stream.WriteInt(0);
			log_write(LOG_DEBUG1, "write weapon, unknow weapon.type : %d", info.weapon_data.weapon.type);
			break;
		}
	}
}

void ReadWeapon(BinaryStream & stream, Weapon & info)
{
	if (ReadBaseItemInfo(stream, info.base_info))
	{
		stream.ReadInt(info.weapon_data.weapon.type);
		log_write(LOG_DEBUG5, "weapon type : %d", info.weapon_data.weapon.type);
		
		switch (info.weapon_data.weapon.type)
		{
		case kWeaponTypeNone:
			break;
			
		case kWeaponTypePistol:
			ReadPistolInfo(stream, info.weapon_data.pistol);
			break;

		case kWeaponTypeDualPistol:
			ReadDualPistol(stream, info.weapon_data.dual_pistol);
			break;

		case kWeaponTypeSubMachineGun:
			ReadSubMachineGunInfo(stream, info.weapon_data.sub_machine_gun);
			break;

		case kWeaponTypeRifle:
			ReadRifleInfo(stream, info.weapon_data.rifle);
			break;

		case kWeaponTypeSniperGun:
			ReadSniperGunInfo(stream, info.weapon_data.sniper);
			break;

		case kWeaponTypeShotGun:
			ReadShotGunInfo(stream, info.weapon_data.shot_gun);
			break;

		case kWeaponTypeMiniGun:
			ReadMiniGunInfo(stream, info.weapon_data.mini_gun);
			break;

		case kWeaponTypeKnife:
			ReadKnifeInfo(stream, info.weapon_data.knife);
			break;

		case kWeaponTypeGrenade:
		case kWeaponTypeSpecial:
			ReadGrenadeInfo(stream, info.weapon_data.grenade);
			break;

		case kWeaponTypeBow:
			ReadBowInfo(stream, info.weapon_data.bow);
			break;

		case kWeaponTypeFlameGun:
			ReadFlameGunInfo(stream, info.weapon_data.flame_gun);
			break;

		case kWeaponTypeCureGun:
			ReadCureGunInfo(stream, info.weapon_data.cure_gun);
			break;

		case kWeaponTypeMiniMachineGun:
			ReadMiniMachineGunInfo(stream, info.weapon_data.minimachine_gun);
			break;
			
		case kWeaponTypeMeditNeedleGun:
		case kWeaponTypeSignal:
		case kWeaponTypeLuncher:
			ReadLuncherInfo(stream, info.weapon_data.luncher);
			break;
			
		case kWeaponTypeAmmoRocket:
		case kWeaponTypeAmmoMeditNeedle:
		case kWeaponTypeAmmoGrenade:
		case kWeaponTypeAmmoStick:
		case kWeaponTypeAmmoArrow:
		case kWeaponTypeAmmoBloodDisk:
		case kWeaponTypeAmmoProd:
			ReadAmmoInfo(stream, info.weapon_data.ammo);
			break;

		case kWeaponTypeDrum:
			ReadDrumInfo(stream, info.weapon_data.drum);
			break;
			
		case kWeaponTypeMilkbottle:
			ReadMilkbottleInfo(stream, info.weapon_data.milkbottle);
			break;

		case kWeaponTypeFlash:
			ReadFlashInfo(stream, info.weapon_data.flash);
			break;

		case kWeaponTypeSmoke:
			ReadSmokeInfo(stream, info.weapon_data.smoke);
			break;

		case kWeaponTypeEquipment:
			ReadEquipmentInfo(stream, info.weapon_data.equipment);
			break;
			
		case kWeaponTypeBomb:
			ReadBombInfo(stream, info.weapon_data.bomb);
			break;

		case kWeaponTypeZombieGun:
			ReadZombieGunInfo(stream,info.weapon_data.zb_info);
			break;

		case kWeaponTypeZombieCharge:
			ReadZombieChargeInfo(stream,info.weapon_data.zbc_info);
			break;
		case kWeaponTypeGunTowerBuilder:
			ReadGunTowerBuilderInfo(stream, info.weapon_data.guntower_info);
			break;
		case kWeaponTypeGunTowerBuilderPlus:
			ReadGunTowerBuilderPlusInfo(stream, info.weapon_data.guntower_plus_info);
			break;

		default:
			log_write(LOG_DEBUG1, "read weapon, unknow weapon.type : %d", info.weapon_data.weapon.type);
			break;
		}
	}
	else
	{
		info.base_info.sid = 0;
		info.weapon_data.weapon.type = kWeaponTypeNone;
	}
}

void WritePackInfo(BinaryStream & stream, const PackInfo & info)
{
	stream.WriteByte(info.id);

	stream.WriteInt((uint)info.weapon_set.size());
	for (std::map<byte, Weapon>::const_iterator itr = info.weapon_set.begin(); 
		itr != info.weapon_set.end(); itr++)
	{
		WriteWeapon(stream, itr->second);
	}
}

void WriteBaseCharacterInfo(BinaryStream & stream, const CharacterInfo & info)
{
	WriteCharacterHead(stream, info);
	for (SingleCharacterInfoMap::const_iterator itr = info.singlecharacter_set.begin(); 
		itr != info.singlecharacter_set.end(); itr++)
	{
		stream.WriteInt(itr->second.career_id);
		stream.WriteString(itr->second.careername);
		stream.WriteString(itr->second.career_key);
		stream.WriteString(itr->second.res_key);
		stream.WriteByte(itr->second.can_select);
	}
}

void WriteSingleCharacter(BinaryStream & stream, const SingleCharacterInfo & info)
{
	//log_write(LOG_DEBUG5, "player id %d, career id %d, career name %s, career key %s",info.player_id, info.career_id, info.careername, info.career_key);
	
	stream.WriteInt(info.player_id);
	stream.WriteInt(info.career_id);
	
	stream.WriteString(info.careername);
	stream.WriteString(info.career_key);
	stream.WriteString(info.res_key);
	
	stream.WriteInt(info.max_hp);
	stream.WriteInt(info.ex_hp);
	stream.WriteInt(info.resistance);

	stream.WriteFloat(info.run_speed);
	stream.WriteFloat(info.walk_speed);
	stream.WriteFloat(info.crouch_speed);
	stream.WriteFloat(info.acceleration);

	stream.WriteFloat(info.jump_velocity);
	stream.WriteFloat(info.throw_velocity);
	
	stream.WriteFloat(info.controller_height);
	stream.WriteFloat(info.controller_radius);
	stream.WriteFloat(info.controller_crouch_height);
	
	stream.WriteByte(info.can_select);
	stream.WriteByte(info.score_scale);
	stream.WriteInt(info.combat_power);

	stream.WriteInt((uint)info.costumes.size());
	for (uint i = 0; i < info.costumes.size(); ++i)
		WriteCostume(stream, info.costumes[i]);

	stream.WriteFloat(info.totalBloodAdd);

	stream.WriteInt((uint)info.packs.size());
	for (std::vector<PackInfo>::const_iterator i = info.packs.begin(); i != info.packs.end(); i++)
		WritePackInfo(stream, *i);
}

static void ReadPackInfo(BinaryStream & stream, PackInfo & info)
{
	int slot_count;
	
	stream.ReadByte(info.id);
	stream.ReadInt(slot_count);

	log_write(LOG_DEBUG5, "slot_count %d, id %d", slot_count, info.id);
	
	for (byte slot = 0; slot < slot_count; slot++)
	{
		Weapon weapon;
		
		ReadWeapon(stream, weapon);
		
		//if (weapon.base_info.sid > 0 && weapon.weapon_data.weapon.type != kWeaponTypeNone)
		{
			info.weapon_set.insert(std::pair<byte, Weapon>(slot, weapon));
		}
	}
}

void WriteCostume(BinaryStream & stream, const Costume & info)
{
	if (WriteBaseItemInfo(stream, info.base_info))
	{
		stream.WriteFloat(info.costume_data.resistance_flame);
		stream.WriteFloat(info.costume_data.resistance_explode);
		stream.WriteFloat(info.costume_data.resistance_bullet);
		stream.WriteFloat(info.costume_data.resistance_close);
		stream.WriteFloat(info.costume_data.health_infect);
	}
}

void ReadCostume(BinaryStream & stream, Costume & info)
{
	if (ReadBaseItemInfo(stream, info.base_info))
	{
		stream.ReadFloat(info.costume_data.resistance_flame);
		stream.ReadFloat(info.costume_data.resistance_explode);
		stream.ReadFloat(info.costume_data.resistance_bullet);
		stream.ReadFloat(info.costume_data.resistance_close);
		stream.ReadFloat(info.costume_data.health_infect);
	}
}

void WriteBuffItem(BinaryStream & stream, const BuffItem & item)
{
	stream.WriteByte(item.id);
	stream.WriteByte(item.type);
	stream.WriteFloat(item.value);
}

void ReadBuffItem(BinaryStream & stream, BuffItem & item)
{
	stream.ReadByte(item.id);
	stream.ReadByte(item.type);
	stream.ReadFloat(item.value);
	
	log_write(LOG_DEBUG4, "BuffItem : %d, %d, %f", item.id, item.type, item.value);
}

void WriteCharacterDropItem(BinaryStream & stream, const CharacterDropItem & info)
{
	stream.WriteInt(info.itemid);
	stream.WriteFloat(info.parameter1);
	stream.WriteFloat(info.parameter2);
	stream.WriteInt(info.count);
}

void ReadCharacterDropItem(BinaryStream & stream, CharacterDropItem & info)
{
	stream.ReadInt(info.itemid);
	stream.ReadFloat(info.parameter1);
	stream.ReadFloat(info.parameter2);
	stream.ReadInt(info.count);
}

void ReadSingleCharacter(BinaryStream & stream, SingleCharacterInfo & info)
{
	stream.ReadInt(info.player_id);
	stream.ReadInt(info.career_id);

	stream.ReadString(info.careername, sizeof(info.careername));
	stream.ReadString(info.career_key, sizeof(info.career_key));
	stream.ReadString(info.res_key, sizeof(info.res_key));

	stream.ReadInt(info.max_hp);
	stream.ReadInt(info.ex_hp);
	stream.ReadInt(info.resistance);

	stream.ReadFloat(info.run_speed);
	stream.ReadFloat(info.walk_speed);
	stream.ReadFloat(info.crouch_speed);
	stream.ReadFloat(info.acceleration);
	
	stream.ReadFloat(info.jump_velocity);
	stream.ReadFloat(info.throw_velocity);
	
	stream.ReadFloat(info.controller_height);
	stream.ReadFloat(info.controller_radius);
	stream.ReadFloat(info.controller_crouch_height);
	
	stream.ReadByte(info.can_select);
	stream.ReadByte(info.score_scale);
	stream.ReadInt(info.combat_power);

	int size = 0;
	stream.ReadInt(size);
	info.costumes.resize(size);
	for (std::vector<Costume>::iterator itr = info.costumes.begin(); itr != info.costumes.end(); itr++)
		ReadCostume(stream, *itr);

	stream.ReadFloat(info.totalBloodAdd);
	size = 0;
	stream.ReadInt(size);
	info.packs.resize(size);
	for (std::vector<PackInfo>::iterator itr = info.packs.begin(); itr != info.packs.end(); itr++)
		ReadPackInfo(stream, *itr);
	
	info.dropitems.clear();
	
	log_write(LOG_DEBUG5, "info.careername : %s", info.careername);	
}

void WriteCharacterHead(BinaryStream & stream, const CharacterInfo & info)
{
	stream.WriteString(info.character_name);
	stream.WriteByte(info.kick_count);
	stream.WriteInt(info.fu_huo_bi);
	stream.WriteInt(info.combat_power);

	stream.WriteInt(info.dan_grading);
	//stream.WriteString(info.team_name);
	stream.WriteInt(info.nMatchingLevel);
	
	stream.WriteByte(info.slot_count);
	
	stream.WriteInt((uint)info.item_set.size());
	for (std::vector<BuffItem>::const_iterator i = info.item_set.begin(); i != info.item_set.end(); i++)
		WriteBuffItem(stream, *i);
	
	if (info.character_count != info.singlecharacter_set.size())
	{
		log_write(LOG_DEBUG1, "singlecharacter_set count is incorrect %s, %d, %d", 
				info.character_name, info.character_count, info.singlecharacter_set.size());
		
		assert(0);
	}
	
	stream.WriteInt((uint)info.singlecharacter_set.size());
}

void ReadCharacterHead(BinaryStream & stream, CharacterInfo & info)
{
	stream.ReadString(info.character_name, sizeof(info.character_name));
	stream.ReadByte(info.kick_count);
	stream.ReadInt(info.fu_huo_bi);
	stream.ReadInt(info.combat_power);

	stream.ReadInt(info.dan_grading);
	//stream.ReadString(info.team_name, sizeof(info.team_name));
	stream.ReadInt(info.nMatchingLevel);
	
	//todo 暂时先这样 后面要加的
	uint dwMatch = 0;
	stream.ReadInt(dwMatch);
	
	stream.ReadByte(info.slot_count);
	
	int size = 0;
	stream.ReadInt(size);
	info.item_set.resize(size);
	for (std::vector<BuffItem>::iterator i = info.item_set.begin(); i < info.item_set.end(); i++)
		ReadBuffItem(stream, *i);
	
	stream.ReadInt(info.character_count);
}

void WriteCharacterInfo(BinaryStream & stream, const CharacterInfo & info)
{
	WriteCharacterHead(stream, info);
	
	for (SingleCharacterInfoMap::const_iterator itr = info.singlecharacter_set.begin(); 
		itr != info.singlecharacter_set.end(); itr++)
	{
		WriteSingleCharacter(stream, itr->second);
	}
}

void ReadCharacterInfo(BinaryStream & stream, CharacterInfo & info)
{
	ReadCharacterHead(stream, info);
	
	info.singlecharacter_set.clear();
	for (int i = 0; i < info.character_count; i++)
	{
		SingleCharacterInfo tmp_data;
		
		ReadSingleCharacter(stream, tmp_data);
		
		info.singlecharacter_set.insert(SingleCharacterInfoPair(tmp_data.career_id, tmp_data));
	}
}

// save
void CharacterBaseInfo::Write(BinaryStream & stream)
{
	stream.WriteInt(id);
	stream.WriteInt(level);
	stream.WriteString(name);
	stream.WriteString(group);

	stream.WriteInt(fcm_online_minutes);
	stream.WriteInt(fcm_offline_minutes);
}

// read
void CharacterBaseInfo::Read(BinaryStream & stream)
{
	stream.ReadInt(id);
	stream.ReadInt(level);
	stream.ReadString(name, sizeof(name));
	stream.ReadString(group, sizeof(group));

	stream.ReadInt(fcm_online_minutes);
	stream.ReadInt(fcm_offline_minutes);
}

void WriteBagInfo(BinaryStream & stream, const ItemBag & bag)
{
	ushort itemNum = bag.size();
	stream.WriteShort(itemNum);
	ItemBag::const_iterator it = bag.begin();
	for (; it != bag.end(); ++it)
	{
		stream.WriteInt(it->second.sid);
		stream.WriteInt(it->second.subType);
		stream.WriteInt(it->second.functionID);
		stream.WriteShort(it->second.count);
		stream.WriteFloat(it->second.CDTime);
		stream.Write(&it->second.param, sizeof(it->second.param) );
		stream.WriteString(it->second.icon);
	}
}

void ReadBagInfo(BinaryStream & stream, ItemBag & bag)
{
	bag.clear();
	ushort itemNum = 0;
	stream.ReadShort(itemNum);
	InGameItemInfo item_info;
	for (int i = 0; i < itemNum ; ++i)
	{
		stream.ReadShort(item_info.count);
		stream.ReadInt(item_info.sid);
		stream.ReadInt(item_info.subType);
		stream.ReadInt(item_info.functionID);
		stream.ReadFloat(item_info.CDTime);
		stream.Read(&item_info.param, sizeof(item_info.param) );
		stream.ReadString(item_info.icon, sizeof(item_info.icon) );
		item_info.init_count = item_info.count;
		bag[item_info.sid] = item_info;
	}
}