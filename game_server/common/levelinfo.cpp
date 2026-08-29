#include "levelinfo.h"
#include "log.h"

static void WriteTransform(BinaryStream & stream, const Transform & transform)
{
	stream.WriteVector3(transform.position);
	stream.WriteFloat(transform.rotation);
}

static void ReadTransform(BinaryStream & stream, Transform & transform)
{
	stream.ReadVector3(transform.position);
	stream.ReadFloat(transform.rotation);
}

static void WritePointInfo(BinaryStream & stream, const PointInfo & info)
{
	stream.WriteByte(info.id);
	WriteTransform(stream, info.transform);
}

static void ReadPointInfo(BinaryStream & stream, PointInfo & info)
{
	stream.ReadByte(info.id);
	ReadTransform(stream, info.transform);
}

void WriteSupply(BinaryStream & stream, const Supply & supply)
{
	stream.WriteVector3(supply.position);
	stream.WriteByte(supply.type);
	stream.WriteString(supply.name.c_str());
	stream.WriteInt(supply.value);
	stream.WriteFloat(supply.random);
	stream.WriteFloat(supply.skilltime);
}

static void ReadSupply(BinaryStream & stream, Supply & supply)
{
	stream.ReadVector3(supply.position);
	stream.ReadByte(supply.type);
	char str[1024] = {0};
	stream.ReadString(str, sizeof(str));
	supply.name = str;
	stream.ReadInt(supply.value);
	stream.ReadFloat(supply.random);
	stream.ReadFloat(supply.skilltime);
}

void WriteKickInfo(BinaryStream & stream, const KickInfo & info)
{
	stream.WriteFloat(info.kick_time_interval);
	stream.WriteFloat(info.kick_factor);
	stream.WriteQuaternion(info.dir);
	stream.WriteFloat(info.on_static_kick_y_offset);
}

void ReadKickInfo(BinaryStream & stream, KickInfo & info)
{
	stream.ReadFloat(info.kick_time_interval);
	stream.ReadFloat(info.kick_factor);
	stream.ReadQuaternion(info.dir);
	stream.ReadFloat(info.on_static_kick_y_offset);
}

static void WriteRoomItem(BinaryStream & stream, const RoomItem & info)
{
	stream.WriteByte(info.type);

	if (info.type)
	{
		stream.WriteInt(info.value);
		stream.WriteString(info.comment.c_str());
		stream.WriteString(info.comment2.c_str());
	}
}

static void ReadRoomItem(BinaryStream & stream, RoomItem & info)
{
	stream.ReadByte(info.type);

	if (info.type)
	{
		stream.ReadInt(info.value);
		char str[1024] = {0};
		stream.ReadString(str, sizeof(str));
		info.comment = str;
		memset(str, 0, sizeof(str));
		stream.ReadString(str, sizeof(str));
		info.comment2 = str;
	}
}


static void WriteStepFatherInfo(BinaryStream & stream, const ServerDummyCreateWithStepFatherInfo & info)
{
	stream.WriteByte(info.type);
	stream.WriteByte(info.sub_type);

	stream.WriteInt(info.create_info.level);
	stream.WriteVector3(info.create_info.position);
	stream.WriteQuaternion(info.create_info.rotation);
	stream.WriteString(info.create_info.key);
	stream.WriteString(info.create_info.tower_key);
	stream.WriteInt(info.create_info.max_hp);

	stream.WriteInt(info.create_info.damage_modifier);
	stream.WriteFloat(info.create_info.range_start);
	stream.WriteFloat(info.create_info.range_end);
	stream.WriteFloat(info.create_info.range_modifier);
	stream.WriteInt(info.create_info.base_damage);

	stream.WriteFloat(info.create_info.check_angle);
	stream.WriteFloat(info.create_info.check_range);
	stream.WriteFloat(info.create_info.angle_speed);
	stream.WriteFloat(info.create_info.fire_interval);
	stream.WriteFloat(info.create_info.life_time);
	stream.WriteFloat(info.create_info.distance);

	stream.WriteInt(info.create_info.max_ammo_count);
	stream.WriteInt(info.create_info.current_ammo_count);

	stream.WriteFloat(info.create_info.move_speed);
	stream.WriteFloat(info.create_info.move_keep_time);

	stream.WriteFloat(info.create_info.recover_range);
	stream.WriteFloat(info.create_info.recover_check_interval);
	stream.WriteInt(info.create_info.recover_per_count_life);
	stream.WriteInt(info.create_info.recover_per_percent_ammo);
	stream.WriteInt(info.create_info.recover_per_minus_ammo);

	stream.WriteShort(info.create_info.freeze_count);
	stream.WriteFloat(info.create_info.respawn_time);
}

static void ReadStepFatherInfo(BinaryStream & stream, ServerDummyCreateWithStepFatherInfo & info)
{
	stream.ReadByte(info.type);
	stream.ReadByte(info.sub_type);

	stream.ReadInt(info.create_info.level);
	stream.ReadVector3(info.create_info.position);
	stream.ReadQuaternion(info.create_info.rotation);
	stream.ReadString(info.create_info.key, elementsof(info.create_info.key));
	stream.ReadString(info.create_info.tower_key, elementsof(info.create_info.tower_key));
	stream.ReadInt(info.create_info.max_hp);

	stream.ReadInt(info.create_info.damage_modifier);
	stream.ReadFloat(info.create_info.range_start);
	stream.ReadFloat(info.create_info.range_end);
	stream.ReadFloat(info.create_info.range_modifier);
	stream.ReadInt(info.create_info.base_damage);

	stream.ReadFloat(info.create_info.check_angle);
	stream.ReadFloat(info.create_info.check_range);
	stream.ReadFloat(info.create_info.angle_speed);
	stream.ReadFloat(info.create_info.fire_interval);
	stream.ReadFloat(info.create_info.life_time);
	stream.ReadFloat(info.create_info.distance);

	stream.ReadInt(info.create_info.max_ammo_count);
	stream.ReadInt(info.create_info.current_ammo_count);

	stream.ReadFloat(info.create_info.move_speed);
	stream.ReadFloat(info.create_info.move_keep_time);

	stream.ReadFloat(info.create_info.recover_range);
	stream.ReadFloat(info.create_info.recover_check_interval);
	stream.ReadInt(info.create_info.recover_per_count_life);
	stream.ReadInt(info.create_info.recover_per_percent_ammo);
	stream.ReadInt(info.create_info.recover_per_minus_ammo);

	stream.ReadShort(info.create_info.freeze_count);
	stream.ReadFloat(info.create_info.respawn_time);
	
}

static void WriteDieBuffInfo(BinaryStream & stream, const DieBuffData & info)
{
	stream.WriteFloat(info.duration_timer);
	stream.WriteFloat(info.interval);
	stream.WriteInt(info.type);
	stream.WriteFloat(info.value);
	stream.WriteString(info.res_key);
	stream.WriteString(info.res_desc);
	stream.WriteInt(info.rate);
}

static void ReadDieBuffInfo(BinaryStream & stream, DieBuffData & info)
{
	stream.ReadFloat(info.duration_timer);
	stream.ReadFloat(info.interval);
	stream.ReadInt(info.type);
	stream.ReadFloat(info.value);
	stream.ReadString(info.res_key, res_key_length);
	stream.ReadString(info.res_desc, res_key_length);
	stream.ReadInt(info.rate);
}

void WriteLevelInfo(BinaryStream & stream, const LevelInfo & info)
{
	stream.WriteInt(info.level_id);

	if (info.level_id > 0)
	{
		stream.WriteByte(info.type);
		stream.WriteString(info.name);
		stream.WriteByte(info.is_use_normal_weapon);

		stream.WriteFloat(info.dead_height);
		stream.WriteFloat(info.snatch_speed_base);
		stream.WriteInt(info.vehicle_recover);
		if(info.vehicle_recover != 0)
			stream.WriteVector3(info.vehicle_dim);

		int size = info.rebirth_point.size();
		stream.WriteInt(size);
		for (std::vector<PointInfo>::const_iterator point = info.rebirth_point.begin(); point < info.rebirth_point.end(); point++)
			WritePointInfo(stream, *point);

		size = info.hold_point.size();
		stream.WriteInt(size);
		for (std::vector<AABBPointInfo>::const_iterator point = info.hold_point.begin(); point < info.hold_point.end(); point++)
		{
			WriteTransform(stream, point->transform);
			stream.WriteVector3(point->dimension);
		}

		if (info.type == kPushVehicle || info.type == kNovice)
		{
			size = info.push_vehicle_point[0].size();
			stream.WriteInt(size);
			for (std::vector<PushVehicleInfo>::const_iterator point = info.push_vehicle_point[0].begin(); point < info.push_vehicle_point[0].end(); point++)
			{
				stream.WriteVector3(point->position);
				stream.WriteVector3(point->dir);
				stream.WriteInt(point->sliding);
			}

			size = info.push_vehicle_point[1].size();
			stream.WriteInt(size);
			for (std::vector<PushVehicleInfo>::const_iterator point = info.push_vehicle_point[1].begin(); point < info.push_vehicle_point[1].end(); point++)
			{
				stream.WriteVector3(point->position);
				stream.WriteVector3(point->dir);
				stream.WriteInt(point->sliding);
			}
		}
		else if (info.type == kBossPVE)
		{
			size = info.push_boss_point.size();
			stream.WriteInt(size);
			for (std::vector<BossActionInfo>::const_iterator point = info.push_boss_point.begin(); point < info.push_boss_point.end(); point++)
			{
				stream.WriteInt(point->lineid);
				stream.WriteInt(point->move_timer);
				stream.WriteVector3(point->position);
				stream.WriteFloat(point->career_id);
				stream.WriteFloat(point->action_timer);
				stream.WriteFloat(point->rotangle);
				stream.WriteInt(point->action_id);
			}
		}
		else
		{
			stream.WriteInt(0);
		}

		size = info.banner_point.size();
		stream.WriteInt(size);
		for (std::vector<BannerPointInfo>::const_iterator point = info.banner_point.begin(); point < info.banner_point.end(); point++)
		{
			stream.WriteByte(point->id);
			WriteTransform(stream, point->aabb.transform);
			stream.WriteVector3(point->aabb.dimension);
		}

		size = info.weapon_point.size();
		stream.WriteInt(size);
		for (std::vector<PointInfo>::const_iterator point = info.weapon_point.begin(); point < info.weapon_point.end(); point++)
			WritePointInfo(stream, *point);

		size = info.weapon_set.size();
		stream.WriteInt(size);
		for (std::vector<Weapon>::const_iterator weapon = info.weapon_set.begin(); weapon < info.weapon_set.end(); weapon++)
			WriteWeapon(stream, *weapon);

		size = info.supply_set.size();
		stream.WriteInt(size);
		for (std::vector<Supply>::const_iterator supply = info.supply_set.begin(); supply < info.supply_set.end(); supply++)
			WriteSupply(stream, *supply);

		log_write(LOG_DEBUG4, "game mode %d", info.type);
		
		stream.WriteByte(info.health_scale);
		
		stream.WriteByte(info.is_rushgold);
		
		size = info.rushgold_set.size();
		stream.WriteInt(size);
		for (std::vector<Vector3>::const_iterator rushgold = info.rushgold_set.begin(); rushgold < info.rushgold_set.end(); rushgold++)
			stream.WriteVector3(*rushgold);
			
		stream.WriteByte(info.is_moneyreward);
		
		// boss mode
		if (info.type == kBossMode)
			WriteSingleCharacter(stream, info.boss_info);
		else if(info.type == kCommonZombieMode)
		{
			WriteSingleCharacter(stream, info.Zombie_info);
			WriteSingleCharacter(stream, info.KingZombie_info);
			WriteSingleCharacter(stream, info.SuperZombie_info);
			stream.WriteInt((int)info.buff_zombie_info.size());
			for (std::vector<SingleCharacterInfo>::const_iterator zombie = info.buff_zombie_info.begin(); 
				zombie != info.buff_zombie_info.end(); zombie++)
			{
				WriteSingleCharacter(stream, *zombie);
			}
		}
		else if(info.type == kBossMode2)
		{
			WriteSingleCharacter(stream, info.boss_info);
			
			for (int i = 0; i < 4; ++i)
			{
				WriteSingleCharacter(stream, info.bosspve_info[i]);
			}
		}
		// bosspve mode
		else if (info.type == kBossPVE)
		{
			stream.WriteByte(info.bosspve_count);
			for (int i = 0; i < info.bosspve_count; ++i)
			{
				WriteSingleCharacter(stream, info.bosspve_info[i]);
				
				stream.WriteInt((uint)info.bosspve_dropitems[i].size());
				for (std::vector<CharacterDropItem>::const_iterator itr = info.bosspve_dropitems[i].begin(); 
					itr != info.bosspve_dropitems[i].end(); itr++)
				{
					WriteCharacterDropItem(stream, *itr);
				}
				
				log_write(LOG_DEBUG4, "w bosspve dropitem_count : %d", info.bosspve_dropitems[i].size());
			}
		}
		else if(info.type == kItemMode || info.type == KMoonMode)
		{
			WriteSingleCharacter(stream, info.boss_info);
		}
		
		WriteRoomItem(stream, info.room_item);
		
		stream.WriteInt((uint)info.stepfather_dummy_info.size());
		for (std::vector<ServerDummyCreateWithStepFatherInfo>::const_iterator point = info.stepfather_dummy_info.begin(); point != info.stepfather_dummy_info.end(); point++)
			WriteStepFatherInfo(stream, *point);

		stream.WriteInt((uint)info.die_buff_data.size());
		for (std::vector<DieBuffData>::const_iterator point = info.die_buff_data.begin(); point != info.die_buff_data.end(); point++)
			WriteDieBuffInfo(stream, *point);
	}
}

void ReadLevelInfo(BinaryStream & stream, LevelInfo & info)
{
	stream.ReadInt(info.level_id);
	log_write(LOG_DEBUG1, "read level info level_id : %d", info.level_id);

	if (info.level_id > 0)
	{
		stream.ReadByte(info.type);
		stream.ReadString(info.name, sizeof(info.name));

		stream.ReadByte(info.is_use_normal_weapon);

		stream.ReadFloat(info.dead_height);
		stream.ReadFloat(info.snatch_speed_base);
		//log_write(LOG_DEBUG5, "snatch_speed_base : %f", info.snatch_speed_base);
		stream.ReadInt(info.vehicle_recover);
		//log_write(LOG_DEBUG5, "vehicle_recover %d", info.vehicle_recover);
		if(info.vehicle_recover != 0)
		{
			stream.ReadVector3(info.vehicle_dim);

			//log_write(LOG_DEBUG5, "vehicle_dim %f, %f, %f", info.vehicle_dim.x, info.vehicle_dim.y, info.vehicle_dim.z);
		}

		int size = 0;
		stream.ReadInt(size);
		info.rebirth_point.clear();
		info.rebirth_point.resize(size);
		for (std::vector<PointInfo>::iterator point = info.rebirth_point.begin(); point < info.rebirth_point.end(); point++)
			ReadPointInfo(stream, *point);

		stream.ReadInt(size);
		info.hold_point.clear();
		info.hold_point.resize(size);
		for (std::vector<AABBPointInfo>::iterator point = info.hold_point.begin(); point < info.hold_point.end(); point++)
		{
			ReadTransform(stream, point->transform);
			stream.ReadVector3(point->dimension);
		}

		
		if (info.type == kPushVehicle || info.type == kNovice)
		{
			stream.ReadInt(size);
			info.push_vehicle_total_length[0] = 0.f;
			log_write(LOG_DEBUG5, "vehicle[0] size %d", size);

			info.push_vehicle_point[0].clear();
			info.push_vehicle_point[0].resize(size);
			std::vector<PushVehicleInfo>::iterator prev_point;
			prev_point = info.push_vehicle_point[0].begin();
			for (std::vector<PushVehicleInfo>::iterator point = info.push_vehicle_point[0].begin(); point < info.push_vehicle_point[0].end(); point++)
			{
				stream.ReadVector3(point->position);
				stream.ReadVector3(point->dir);
				stream.ReadInt(point->sliding);

				point->total_length = 0.f;
				if(point != info.push_vehicle_point[0].begin())
				{
					Vector3 delta = point->position - prev_point->position;
					float delta_length = Length(delta);
					info.push_vehicle_total_length[0] += delta_length;
					point->total_length = prev_point->total_length + delta_length; 
					prev_point = point;
				}
			}

			stream.ReadInt(size);
			info.push_vehicle_total_length[1] = 0.f;
			log_write(LOG_DEBUG5, "vehicle[1] size %d",size );

			info.push_vehicle_point[1].clear();
			info.push_vehicle_point[1].resize(size);
			prev_point = info.push_vehicle_point[1].begin();
			for (std::vector<PushVehicleInfo>::iterator point = info.push_vehicle_point[1].begin(); point < info.push_vehicle_point[1].end(); point++)
			{
				stream.ReadVector3(point->position);
				stream.ReadVector3(point->dir);
				stream.ReadInt(point->sliding);

				if(point != info.push_vehicle_point[1].begin())
				{
					Vector3 delta = point->position - prev_point->position;
					float delta_length = Length(delta);
					info.push_vehicle_total_length[1] += delta_length;
					point->total_length = prev_point->total_length + delta_length; 
					prev_point = point;
				}
			}
		}
		else if (info.type == kBossPVE)
		{
			stream.ReadInt(size);
			info.push_boss_point.clear();
			info.push_boss_point.resize(size);
			for (std::vector<BossActionInfo>::iterator point = info.push_boss_point.begin(); point < info.push_boss_point.end(); point++)
			{
				stream.ReadInt(point->lineid);
				stream.ReadInt(point->move_timer);
				stream.ReadVector3(point->position);
				stream.ReadFloat(point->career_id);
				stream.ReadFloat(point->action_timer);
				stream.ReadFloat(point->rotangle);
				stream.ReadInt(point->action_id);
			}
			for (int i = 0; i < 4; i++)
			{
				info.push_boss_points[i].clear();
			}
			for (int i = 0; i < info.push_boss_point.size(); i++)
			{
				if (info.push_boss_point[i].lineid > 0 && info.push_boss_point[i].lineid <= 4)
				{
					info.push_boss_points[info.push_boss_point[i].lineid-1].push_back(info.push_boss_point[i]);
				}
			}
		}
		else
		{
			stream.ReadInt(size);
		}

		size = info.banner_point.size();
		stream.ReadInt(size);
		info.banner_point.clear();
		info.banner_point.resize(size);
		for (std::vector<BannerPointInfo>::iterator point = info.banner_point.begin(); point < info.banner_point.end(); point++)
		{
			stream.ReadByte(point->id);
			ReadTransform(stream, point->aabb.transform);
			stream.ReadVector3(point->aabb.dimension);
		}

		size = info.weapon_point.size();
		stream.ReadInt(size);
		info.weapon_point.clear();
		info.weapon_point.resize(size);
		for (std::vector<PointInfo>::iterator point = info.weapon_point.begin(); point < info.weapon_point.end(); point++)
			ReadPointInfo(stream, *point);

		size = info.weapon_set.size();
		stream.ReadInt(size);
		info.weapon_set.clear();
		info.weapon_set.resize(size);
		for (std::vector<Weapon>::iterator weapon = info.weapon_set.begin(); weapon < info.weapon_set.end(); weapon++)
			ReadWeapon(stream, *weapon);

		size = info.supply_set.size();
		stream.ReadInt(size);
		info.supply_set.clear();
		info.supply_set.resize(size);
		for (std::vector<Supply>::iterator supply = info.supply_set.begin(); supply < info.supply_set.end(); supply++)
			ReadSupply(stream, *supply);
			
		stream.ReadByte(info.health_scale);
		
		stream.ReadByte(info.is_rushgold);

		log_write(LOG_DEBUG4, "rush gold is %d", info.is_rushgold);
		
		size = info.rushgold_set.size();
		stream.ReadInt(size);
		info.rushgold_set.clear();
		info.rushgold_set.resize(size);
		for (std::vector<Vector3>::iterator rushgold = info.rushgold_set.begin(); rushgold < info.rushgold_set.end(); rushgold++)
			stream.ReadVector3(*rushgold);
			
		stream.ReadByte(info.is_moneyreward);
		
		// boss mode
		if (info.type == kBossMode)
		{
			log_write(LOG_DEBUG4, "Boss Mode");
			ReadSingleCharacter(stream, info.boss_info);
		}
		else if(info.type == kCommonZombieMode)
		{
			ReadSingleCharacter(stream, info.Zombie_info);
			ReadSingleCharacter(stream, info.KingZombie_info);
			ReadSingleCharacter(stream, info.SuperZombie_info);
			int size = 0;
			stream.ReadInt(size);
			info.buff_zombie_info.clear();
			info.buff_zombie_info.resize(size);
			for (std::vector<SingleCharacterInfo>::iterator zombie = info.buff_zombie_info.begin(); zombie < info.buff_zombie_info.end(); zombie++)
				ReadSingleCharacter(stream, *zombie);
		}
		else if (info.type == kBossMode2)
		{
			log_write(LOG_DEBUG4, "Boss Mode2");
			
			ReadSingleCharacter(stream, info.boss_info);
			
			for (int i = 0; i < 4; ++i)
			{
				ReadSingleCharacter(stream, info.bosspve_info[i]);
			}
		}
		else if (info.type == kBossPVE)
		{
			stream.ReadByte(info.bosspve_count);
			
			log_write(LOG_DEBUG4, "bosspve_count : %d", info.bosspve_count);
			
			for (int i = 0; i < info.bosspve_count; ++i)
			{
				ReadSingleCharacter(stream, info.bosspve_info[i]);
				
				info.bosspve_dropitems[i].clear();
				
				int dropitem_count = 0;
				stream.ReadInt(dropitem_count);
				
				log_write(LOG_DEBUG4, "r bosspve dropitem_count : %d", dropitem_count);
				
				for (int j = 0; j < dropitem_count; j++)
				{
					CharacterDropItem tmp_info;
					
					ReadCharacterDropItem(stream, tmp_info);
					
					info.bosspve_dropitems[i].push_back(tmp_info);
				}
			}
		}
		else if (info.type == kItemMode || info.type == KMoonMode)
		{
			log_write(LOG_DEBUG4, "Item Mode");
			ReadSingleCharacter(stream, info.boss_info);
		}
		
		ReadRoomItem(stream, info.room_item);
		
		info.stepfather_dummy_info.clear();
		
		stream.ReadInt(size);
		info.stepfather_dummy_info.resize(size);
		for (std::vector<ServerDummyCreateWithStepFatherInfo>::iterator point = info.stepfather_dummy_info.begin(); point != info.stepfather_dummy_info.end(); point++)
			ReadStepFatherInfo(stream, *point);

		stream.ReadInt(size);
		info.die_buff_data.resize(size);
		for (std::vector<DieBuffData>::iterator point = info.die_buff_data.begin(); point != info.die_buff_data.end(); point++)
			ReadDieBuffInfo(stream, *point);
	}
}
