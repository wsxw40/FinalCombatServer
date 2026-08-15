#include "roomoption.h"
#include "log.h"

// read room option
void ReadRoomOption(BinaryStream & stream, RoomOption & option)
{
	stream.ReadString(option.name, sizeof(option.name));
	stream.ReadString(option.map_name, sizeof(option.map_name));
	stream.ReadByte((byte&)option.use_random_map);

	stream.ReadInt(option.level_id);
	stream.ReadInt(option.map_level);
	stream.ReadString(option.rand_key, sizeof(option.rand_key));
	stream.ReadByte((byte&)option.character_id);
	stream.ReadByte((byte&)option.is_knife);
	stream.ReadByte((byte&)option.is_gm);

	stream.ReadByte((byte&)option.client_count);
	stream.ReadByte((byte&)option.viewer_count);

	stream.ReadByte(option.game_type);
	stream.ReadInt(option.rule_value);
	stream.ReadByte(option.special_mode);

	stream.ReadShort(option.round_rebirth_time_max);
	stream.ReadByte(option.dead_view_mode);
	stream.ReadByte((byte&)option.can_join_on_playing);
	stream.ReadByte((byte&)option.check_game_balance);
	stream.ReadByte((byte&)option.team_hurt);
	stream.ReadByte((byte&)option.group_match);
	stream.ReadByte((byte&)option.auto_start);

	stream.ReadByte((byte&)option.use_password);
	stream.ReadString(option.password, sizeof(option.password));

	stream.ReadInt(option.item_id);
	stream.ReadString(option.item_resource, sizeof(option.item_resource));
	stream.ReadString(option.item_name, sizeof(option.item_name));

	stream.ReadByte(option.m_bIsMatchingRoom);

	log_write(LOG_DEBUG3, 
		"option.name : %s, option.map_name : %s, option.use_random_map : %d, option.level_id : %d, option.rule_value : %d, option.item_resource : %s, option.item_name : %s, is matching : %d", 
		option.name, option.map_name, option.use_random_map, option.level_id, option.rule_value, option.item_resource, option.item_name, option.m_bIsMatchingRoom);
}

// write room option
void WriteRoomOption(BinaryStream & stream, const RoomOption & option)
{
	stream.WriteString(option.name);
	stream.WriteString(option.map_name);
	stream.WriteByte(option.use_random_map);

	stream.WriteInt(option.level_id);
	stream.WriteInt(option.map_level);
	stream.WriteString(option.rand_key);
	stream.WriteByte((byte&)option.character_id);
	stream.WriteByte((byte&)option.is_knife);
	stream.WriteByte((byte&)option.is_gm);

	stream.WriteByte(option.client_count);
	stream.WriteByte(option.viewer_count);

	stream.WriteByte(option.game_type);
	stream.WriteInt(option.rule_value);
	stream.WriteByte(option.special_mode);

	stream.WriteShort(option.round_rebirth_time_max);
	stream.WriteByte(option.dead_view_mode);
	stream.WriteByte(option.can_join_on_playing);
	stream.WriteByte(option.check_game_balance);
	stream.WriteByte(option.team_hurt);
	stream.WriteByte(option.group_match);
	stream.WriteByte(option.auto_start);

	stream.WriteByte(option.use_password);
	stream.WriteString(option.password);

	stream.WriteInt(option.item_id);
	stream.WriteString(option.item_resource);
	stream.WriteString(option.item_name);

	stream.WriteByte(option.m_bIsMatchingRoom);
}
