#include "pch.h"

// 实现
#include "Skill_imp.cpp"

// debug flg
static const bool force_sync = false;
static const bool full_mode = true;

//////////////////////////////////////////////////////////////
static int SearchSkillData(const SkillDataList &skilldatas, 
							const EffectData &search_data, 
							SkillDataPtrList &result)
{
	int count = 0;
	
	result.clear();
	
	for (SkillDataList::const_iterator itr = skilldatas.begin(); itr != skilldatas.end(); itr++)
	{
		const SkillData &skilldata = *itr;
		const EffectData &effectdata = skilldata.effect;
		if (effectdata.player_id == search_data.player_id
			&& effectdata.player_item_id == search_data.player_item_id
			&& effectdata.attr_slotid == search_data.attr_slotid
			&& effectdata.attr_sub_slotid == search_data.attr_sub_slotid)
		{
			result.push_back((SkillData*)&skilldata);
			count++;
		}
	}
	
	return count;
}

static int SearchEffectDataWithType(const EffectDataList &effectdatas, 
									EEffect type, bool all, 
									EffectDataPtrList &result)
{
	int count = 0;
	
	result.clear();
	
	for (EffectDataList::const_iterator itr = effectdatas.begin(); itr != effectdatas.end(); itr++)
	{
		const EffectData &effectdata = *itr;
		if (effectdata.type == type && (effectdata.enable || all))
		{
			result.push_back((EffectData*)&effectdata);
			count++;
		}
	}
	
	return count;
}

static int SearchEffectData(const EffectDataList &effectdatas, 
							const EffectData &search_data, 
							EffectDataPtrList &result)
{
	int count = 0;
	
	result.clear();
	
	for (EffectDataList::const_iterator itr = effectdatas.begin(); itr != effectdatas.end(); itr++)
	{
		const EffectData &effectdata = *itr;
		if (effectdata.player_id == search_data.player_id
			&& effectdata.player_item_id == search_data.player_item_id
			&& effectdata.attr_slotid == search_data.attr_slotid
			&& effectdata.attr_sub_slotid == search_data.attr_sub_slotid)
		{
			result.push_back((EffectData*)&effectdata);
			count++;
		}
	}
	
	return count;
}

static int SearchEffectDataFull(const EffectDataList &effectdatas, 
							const EffectData &search_data, 
							EffectDataPtrList &result)
{
	int count = 0;
	
	result.clear();
	
	for (EffectDataList::const_iterator itr = effectdatas.begin(); itr != effectdatas.end(); itr++)
	{
		const EffectData &effectdata = *itr;
		if (effectdata.player_id == search_data.player_id
			&& effectdata.player_item_id == search_data.player_item_id
			&& effectdata.attr_slotid == search_data.attr_slotid
			&& effectdata.attr_sub_slotid == search_data.attr_sub_slotid
			&& effectdata.type == search_data.type)
		{
			result.push_back((EffectData*)&effectdata);
			count++;
		}
	}
	
	return count;
}

static int GetEffectDataCount(const EffectDataList &effectdatas, bool all)
{
	int count = 0;
	
	for (EffectDataList::const_iterator itr = effectdatas.begin(); itr != effectdatas.end(); itr++)
	{
		if (itr->enable || all)
		{
			count++;
		}
	}
	
	return count;
}

static void WriteEffectData(BinaryStream & stream, const EffectData & info, bool full)
{
	if (full)
	{
		// 全信息同步
		// 同步EffectData全部信息，未激活技能也会同步，一般用于调试。
		stream.WriteDouble(info.duration_timer);
		stream.WriteDouble(info.interval);
		stream.WriteDouble(info.interval_timer);
		stream.WriteInt(info.player_id);
		stream.WriteInt(info.player_item_id);
		stream.WriteByte(info.attr_slotid);
		stream.WriteByte(info.attr_sub_slotid);
		stream.WriteShort(info.attr_raw.type);
		stream.WriteShort(info.attr_raw.value1);
		stream.WriteShort(info.attr_raw.value2);
		stream.WriteShort(info.attr_raw.time);
		stream.WriteShort((short)info.type);
		stream.WriteFloat(info.value);
		stream.WriteByte((byte)info.enable);
	}
	else
	{
		// 部分信息同步
		// 仅发送EffectData的player_id、type、value和attr_raw的type字段，未激活技能不同步。
		if (info.enable)
		{
			stream.WriteInt(info.player_id);
			stream.WriteShort((short)info.type);
			stream.WriteFloat(info.value);
			stream.WriteShort(info.attr_raw.type);
		}
	}
}

static void WriteEffectDataList(BinaryStream & stream, const EffectDataList &effectdatas, bool fullmode)
{
	int count = GetEffectDataCount(effectdatas, fullmode);
	
	stream.WriteInt(count);
	
	for (EffectDataList::const_iterator itr = effectdatas.begin(); itr != effectdatas.end(); itr++)
	{
		WriteEffectData(stream, *itr, fullmode);
	}
}

static int GetSkillDataCount(const SkillDataList &skilldatas, bool all)
{
	int count = 0;
	
	for (SkillDataList::const_iterator itr = skilldatas.begin(); itr != skilldatas.end(); itr++)
	{
		if (itr->enable || all)
		{
			count++;
		}
	}
	
	return count;
}

static void WriteSkillData(BinaryStream & stream, const SkillData & info, bool full)
{
	if (full)
	{
		// 全信息同步
		// 同步SkillData全部信息，未激活技能也会同步，一般用于调试。
		stream.WriteDouble(info.duration_timer);
		WriteEffectData(stream, info.effect, true);
		stream.WriteShort((short)info.type);
		stream.WriteFloat(info.value);
		stream.WriteShort((short)info.slot);
		stream.WriteByte((byte)info.enable);
	}
	else
	{
		// 部分信息同步
		// 仅发送SkillData的type和effect.attr_raw的type字段，未激活技能不同步。
		if (info.enable)
		{
			stream.WriteShort((short)info.type);
			stream.WriteShort(info.effect.attr_raw.type);
			stream.WriteShort((short)info.slot);
		}
	}
}

static void WriteSkillDataList(BinaryStream & stream, const SkillDataList &skilldatas, bool fullmode)
{
	int count = GetSkillDataCount(skilldatas, fullmode);
	
	stream.WriteInt(count);
	
	for (SkillDataList::const_iterator itr = skilldatas.begin(); itr != skilldatas.end(); itr++)
	{
		WriteSkillData(stream, *itr, fullmode);
	}
}

//////////////////////////////////////////////////////////////
SkillEffect::SkillEffect(Client *pClient)
	: m_DirtFlgSkill(false)
	, m_DirtFlgNatural(false)
	, m_DirtFlgAcquired(false)
	, m_ApplyEffectLock(false)
	, m_pClient(pClient)
{
}

SkillEffect::~SkillEffect()
{
	ClearUp();
}

void SkillEffect::Initialize()
{
	ClearUp();

	const SingleCharacterInfo &singlecharacter = m_pClient->GetCurCharinfo();
	// 道具
	for (std::vector<Costume>::const_iterator itr = singlecharacter.costumes.begin(); 
		itr != singlecharacter.costumes.end(); itr++)
	{
		ChangeBaseItemInfo_Inside(itr->base_info, true, true);
	}
	
	// 背包
	for (std::vector<PackInfo>::const_iterator itr = singlecharacter.packs.begin(); 
		itr != singlecharacter.packs.end(); itr++)
	{
		const PackInfo &pack = *itr;
		for (std::map<byte, Weapon>::const_iterator itr = pack.weapon_set.begin(); 
			itr != pack.weapon_set.end(); itr++)
		{
			ChangeBaseItemInfo_Inside(itr->second.base_info, true, false);
		}
	}
	
	m_pClient->newDamage = 0;
	m_pClient->newResistance = 0;
	
	const CharacterInfo& character_info = m_pClient->GetCharacterInfo();
	// BuffItem
	//if (singlecharacter.can_select)
	{
		for (std::vector<BuffItem>::const_iterator itr = character_info.item_set.begin(); 
			itr != character_info.item_set.end(); itr++)
		{
			if (BuffItemToSkill(*itr, m_Skill))
			{
				m_DirtFlgSkill = true;
			}
			
			if (BuffItemToNaturalEffect(*itr, m_NaturalEffect, singlecharacter.career_id))
			{
				m_DirtFlgNatural = true;
			}
			
			if (itr->id == kBuffIdRoom)
			{
				switch (itr->type)
				{
				case kBuffTypeDamage:
					m_pClient->newDamage = itr->value / 100.f;
					break;
				case kBuffTypeResistance:
					m_pClient->newResistance = itr->value / 100.f;
					break;
				}
			}
		}
	}

	//AddSkillFromCharacter("206,30,10,-70,10,20,0");
	
	// debug
	Debug_TestWeaponAttr(character_info);
	
	if (Debug_AddEffect(m_NaturalEffect, character_info))
	{
		m_DirtFlgNatural = true;
	}
	
	// OnSkillEffectChanged
	OnSkillEffectChanged();
}

void SkillEffect::Update(float delta)
{
	m_ApplyEffectLock = true;
	
	// m_Skill
	for (SkillDataList::iterator itr = m_Skill.begin(); itr != m_Skill.end(); )
	{
		SkillData &skilldata = *itr;

		if (skilldata.loop)
		{
			if (skilldata.enable)
			{
				skilldata.cd_time -= delta;
				
				if (skilldata.cd_time <= 0 && skilldata.duration_timer <= 0)
				{
					skilldata.enable = false;
				}
				if (skilldata.duration_timer>=0)
				{
					skilldata.duration_timer -= delta;
					if (skilldata.duration_timer < 0)
					{
						OnSkillEffectEnd(skilldata);
					}
				}
			}
		}
		else
		{
			skilldata.duration_timer -= delta;
			if (skilldata.duration_timer <= 0)
			{
				itr = m_Skill.erase(itr);

				m_DirtFlgSkill = true;

				continue;
			}
		}
		itr++;
	}
	
	// m_NaturalEffect
	for (EffectDataList::iterator itr = m_NaturalEffect.begin(); itr != m_NaturalEffect.end(); )
	{
		EffectData &effectdata = *itr;
		
		if (effectdata.enable)
		{
			if (effectdata.iterval_callback)
			{
				effectdata.interval_timer -= delta;
				if (effectdata.interval_timer <= 0)
				{
					effectdata.iterval_callback(effectdata, *m_pClient);
					
					effectdata.interval_timer += effectdata.interval;
				}
			}
		}
		
		effectdata.duration_timer -= delta;
		if (effectdata.duration_timer <= 0)
		{
			itr = m_NaturalEffect.erase(itr);
			
			m_DirtFlgNatural = true;
		}
		else
			itr++;
	}
	
	// m_AcquiredEffect
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); )
	{
		EffectData &effectdata = *itr;
		
		if (effectdata.enable)
		{
			if (effectdata.iterval_callback)
			{
				effectdata.interval_timer -= delta;
				if (effectdata.interval_timer <= 0)
				{
					effectdata.iterval_callback(effectdata, *m_pClient);
					
					effectdata.interval_timer += effectdata.interval;
				}
			}
		}
		
		effectdata.duration_timer -= delta;
		if (effectdata.duration_timer <= 0)
		{
			itr = m_AcquiredEffect.erase(itr);
			
			m_DirtFlgAcquired = true;
		}
		else
			itr++;
	}
	
	m_ApplyEffectLock = false;
	
	if (force_sync)
	{
		m_DirtFlgSkill = true;
		m_DirtFlgNatural = true;
		m_DirtFlgAcquired = true;
	}
	
	// OnSkillEffectChanged
	OnSkillEffectChanged();
}

void SkillEffect::SetEnable(const BaseItemInfo &info, bool is_enable)
{
	EffectData search_data;
	EffectDataPtrList result_e;
	SkillDataPtrList result_s;
	
	search_data.player_id = m_pClient->GetCharacterInfo().character_id;
	search_data.player_item_id = info.player_item_id;
	
	for (uint slotid = 0; slotid < info.attrs.size(); slotid++)
	{
		const AttributeList &attributelist = info.attrs[slotid];
		for (uint sub_slotid = 0; sub_slotid < attributelist.size(); sub_slotid++)
		{
			search_data.attr_slotid = slotid;
			search_data.attr_sub_slotid = sub_slotid;
			
			// m_Skill
			SearchSkillData(m_Skill, search_data, result_s);
			for (SkillDataPtrList::iterator itr = result_s.begin(); itr != result_s.end(); itr++)
			{
				SkillData *pSkillData = *itr;
				if (pSkillData->enable != is_enable)
					pSkillData->enable = is_enable;
					
				m_DirtFlgSkill = true;
			}
			
			// m_NaturalEffect
			SearchEffectData(m_NaturalEffect, search_data, result_e);
			for (EffectDataPtrList::iterator itr = result_e.begin(); itr != result_e.end(); itr++)
			{
				EffectData *pEffectdata = *itr;
				if (pEffectdata->enable != is_enable)
					pEffectdata->enable = is_enable;
					
				m_DirtFlgNatural = true;
			}
			
			// 后天Effect不能关闭
			// m_AcquiredEffect
			// SearchEffectData(m_AcquiredEffect, search_data, result_e);
			// for (EffectDataPtrList::iterator itr = result_e.begin(); itr != result_e.end(); itr++)
			// {
				// EffectData *pEffectdata = *itr;
				// if (pEffectdata->enable != is_enable)
					// pEffectdata->enable = is_enable;
					
				// m_DirtFlgAcquired = true;
			// }
		}
	}
	
	// OnSkillEffectChanged
	OnSkillEffectChanged();
}

void SkillEffect::ChangeBaseItemInfo(const BaseItemInfo &info, bool add, bool is_enable)
{
	ChangeBaseItemInfo_Inside(info, add, is_enable);
	
	// OnSkillEffectChanged
	OnSkillEffectChanged();
}

bool SkillEffect::HasEffect(EEffect type)
{
	for (EffectDataList::iterator itr = m_NaturalEffect.begin(); itr != m_NaturalEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.enable
			&& effectdata.type == type)
		{
			return true;
		}
	}
	
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.enable
			&& effectdata.type == type)
		{
			return true;
		}
	}
	
	return false;
}

bool SkillEffect::SumEffect(EEffect type, float& value)
{
	bool is_find = false;

	for (EffectDataList::iterator itr = m_NaturalEffect.begin(); itr != m_NaturalEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.enable
			&& effectdata.type == type)
		{
			value += effectdata.value;
			
			is_find = true;
		}
	}
	
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.enable
			&& effectdata.type == type)
		{
			value += effectdata.value;
			
			is_find = true;
		}
	}
	
	DEBUGLOG_WRITE("SumEffect(%d, %f), is_find : %d, name : %s", 
				type, value, is_find, m_pClient ? m_pClient->GetCharacterInfo().character_name : "");
	
	return is_find;
}

void SkillEffect::ApplyEffect(const EffectData &effectdata)
{
	if (m_ApplyEffectLock)
	{
		DEBUGLOG_WRITE("SkillEffect::ApplyEffect(), ApplyEffectLock is On(%d)!!!", m_ApplyEffectLock);
		
		log_write(LOG_ERROR, "SkillEffect::ApplyEffect(), ApplyEffectLock is On!!!");
		
		//assert(0);
		
		return;
	}

	EffectDataPtrList result;
	
	bool is_effect = false;
	bool has_invalideffect = false;
	
	// 检查kEffect_Invalid_Sustain_HurtBurn
	is_effect = (effectdata.type == kEffect_Sustain_HurtBurn_Replace || 
				effectdata.type == kEffect_Sustain_HurtBurn);
	has_invalideffect = HasEffect(kEffect_Invalid_Sustain_HurtBurn);
	if (is_effect && has_invalideffect)
	{
		return;
	}
	// 检查kEffect_Invalid_Sustain_HurtBloodshed
	is_effect = (effectdata.type == kEffect_Sustain_HurtBloodshed_Replace || 
				effectdata.type == kEffect_Sustain_HurtBloodshed);
	has_invalideffect = HasEffect(kEffect_Invalid_Sustain_HurtBloodshed);
	if (is_effect && has_invalideffect)
	{
		return;
	}
	// 检查kEffect_Invalid_Sustain_HurtPoison
	is_effect = (effectdata.type == kEffect_Sustain_HurtPoison_Replace || 
				effectdata.type == kEffect_Sustain_HurtPoison);
	has_invalideffect = HasEffect(kEffect_Invalid_Sustain_HurtPoison);
	if (is_effect && has_invalideffect)
	{
		return;
	}
	// 检查kEffect_Invalid_MoveSpeed
	is_effect = (effectdata.type == kEffect_Infect_MoveSpeed);
	has_invalideffect = HasEffect(kEffect_Invalid_MoveSpeed);
	if (is_effect && has_invalideffect)
	{
		return;
	}
	
	if (effectdata.type == kEffect_Sustain_HurtBurn_Replace || 
		effectdata.type == kEffect_Sustain_HurtBloodshed_Replace || 
		effectdata.type == kEffect_Sustain_HurtPoison_Replace)
	{
		// 强制替换型
		if (SearchEffectDataWithType(m_AcquiredEffect, effectdata.type, false, result) > 0)
		{
			assert(result.size() == 1);
			
			EffectData *pEffectData = *result.begin();
			*pEffectData = effectdata;
		}
		else
		{
			m_AcquiredEffect.push_back(effectdata);
		}
	}
	else
	{
		// 普通
		if (SearchEffectDataFull(m_AcquiredEffect, effectdata, result) > 0)
		{
			assert(result.size() == 1);
			
			EffectData *pEffectData = *result.begin();
			*pEffectData = effectdata;
		}
		else
		{
			m_AcquiredEffect.push_back(effectdata);
		}
	}
	
	m_DirtFlgAcquired = true;
	
	OnSkillEffectChanged();
}

void SkillEffect::ApplySystemEffect(EffectData effectdata)
{
	ApplySystemItemEffect(effectdata, 0);
}

void SkillEffect::ApplySystemItemEffect(EffectData effectdata, uint item_id)
{
	effectdata.player_id = 0;
	effectdata.player_item_id = item_id;
	effectdata.attr_slotid = -1;
	effectdata.attr_sub_slotid = -1;
	
	if (effectdata.type > kEffect_IntervalBase_Start && 
		effectdata.type < kEffect_IntervalBase_End)
	{
		effectdata.iterval_callback = &Effect_ItervalHandle;
	}
	
	ApplyEffect(effectdata);
}

void SkillEffect::ClearAcquiredEffect(EEffect type)
{
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.type == type)
		{
			effectdata.duration_timer = 0.001f;
		}
	}
}

void SkillEffect::CancelSustainHurt()
{
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.enable && 
			(effectdata.type == kEffect_Sustain_HurtBurn_Replace || 
			effectdata.type == kEffect_Sustain_HurtBloodshed_Replace || 
			effectdata.type == kEffect_Sustain_HurtPoison_Replace || 
			effectdata.type == kEffect_Sustain_HurtBurn || 
			effectdata.type == kEffect_Sustain_HurtBloodshed || 
			effectdata.type == kEffect_Sustain_HurtPoison))
		{
			effectdata.duration_timer = 0.001f;
		}
	}
}

void SkillEffect::CancelInvisible()
{
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.enable && effectdata.type == kEffect_Special_Invisible)
		{
			effectdata.duration_timer = 0.001f;
		}
	}
}

void SkillEffect::OnPlayDead()
{
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.enable && effectdata.dead_disable)
		{
			effectdata.enable = false;
		}
	}
}

SkillData* SkillEffect::GetSkillData(uint skill_id)
{
	for (SkillDataList::iterator itr = m_Skill.begin(); itr != m_Skill.end(); itr++)
	{
		SkillData &effectdata = *itr;
		if (effectdata.skill_id == skill_id)
		{
			return &effectdata;
		}
	}

	return NULL;
}

SkillData* SkillEffect::GetSkillData(uint slot, uint career_id)
{
	for (SkillDataList::iterator itr = m_Skill.begin(); itr != m_Skill.end(); itr++)
	{
		SkillData &effectdata = *itr;
		if (effectdata.slot == slot && effectdata.carrer_id == career_id)
		{
			return &effectdata;
		}
	}

	return NULL;
}

const SkillDataList& SkillEffect::GetSkill()
{
	return m_Skill;
}

const EffectDataList& SkillEffect::GetNaturalEffect()
{
	return m_NaturalEffect;
}

const EffectDataList& SkillEffect::GetAcquiredEffect()
{
	return m_AcquiredEffect;
}

//////////////////////////////////////////////////////////////
void SkillEffect::OnClientKill(Client &client, const BaseItemInfo &info)
{
	// 忽略系统造成的伤害
	if (!m_pClient || info.player_item_id == 0)
	{
		return;
	}
	
	uint player_id = m_pClient->GetCharacterInfo().character_id;
	
	// m_Skill
	for (SkillDataList::iterator itr = m_Skill.begin(); itr != m_Skill.end(); itr++)
	{
		SkillData &skilldata = *itr;
		
		if (skilldata.effect.player_id == player_id && 
			skilldata.effect.player_item_id == info.player_item_id && 
			skilldata.kill_callback)
		{
			skilldata.kill_callback(skilldata, *m_pClient, client);
		}
	}
}

void SkillEffect::OnClientHit(Client &client, const BaseItemInfo &info)
{
	// 忽略系统造成的伤害
	if (!m_pClient || info.player_item_id == 0)
	{
		return;
	}
	
	uint player_id = m_pClient->GetCharacterInfo().character_id;
	
	// m_Skill
	for (SkillDataList::iterator itr = m_Skill.begin(); itr != m_Skill.end(); itr++)
	{
		SkillData &skilldata = *itr;
		
		if (skilldata.effect.player_id == player_id && 
			skilldata.effect.player_item_id == info.player_item_id && 
			skilldata.hit_callback)
		{
			skilldata.hit_callback(skilldata, *m_pClient, client);
		}
	}
	
	// m_NaturalEffect
	for (EffectDataList::iterator itr = m_NaturalEffect.begin(); itr != m_NaturalEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.enable)
		{
			switch (effectdata.type)
			{
				case kEffect_Special_HitAddScore:
					m_pClient->data.ScoreDataAdd(effectdata.value);
					break;
				
				default:
					break;
			}
		}
	}
	
	// m_AcquiredEffect
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		if (effectdata.enable)
		{
			switch (effectdata.type)
			{
				case kEffect_Special_HitAddScore:
					m_pClient->data.ScoreDataAdd(effectdata.value);
					break;
				
				default:
					break;
			}
		}
	}
}

void SkillEffect::OnTakeDamage(Client &client)
{
	// m_NaturalEffect
	for (EffectDataList::iterator itr = m_NaturalEffect.begin(); itr != m_NaturalEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		
		if (effectdata.enable)
		{
			switch (effectdata.type)
			{
				case kEffect_Sustain_HpRecover:
				case kEffect_Sustain_HpRecover2:
				case kEffect_Sustain_AmmoRecover:
				case kEffect_Sustain_AmmoRecover2:
				case kEffect_Sustain_ArmorRecover:
				case kEffect_Sustain_ArmorRecover2:
					{
						double v = effectdata.interval * 2.f;
						// 略微延长timer，以错开持续伤害之类的
						if (effectdata.interval_timer < v)
							effectdata.interval_timer = v;
					}
					break;
					
				default:
					break;
			}
		}
	}
	
	// m_AcquiredEffect
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		
		if (effectdata.enable)
		{
			switch (effectdata.type)
			{
				case kEffect_Sustain_HpRecover:
				case kEffect_Sustain_HpRecover2:
				case kEffect_Sustain_AmmoRecover:
				case kEffect_Sustain_AmmoRecover2:
				case kEffect_Sustain_ArmorRecover:
				case kEffect_Sustain_ArmorRecover2:
					{
						double v = effectdata.interval * 2.f;
						// 略微延长timer，以错开持续伤害之类的
						if (effectdata.interval_timer < v)
							effectdata.interval_timer = v;
					}
					break;
					
				default:
					break;
			}
		}
	}
}

void SkillEffect::OnAction()
{
	// m_NaturalEffect
	for (EffectDataList::iterator itr = m_NaturalEffect.begin(); itr != m_NaturalEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		
		if (effectdata.enable)
		{
			switch (effectdata.type)
			{
				case kEffect_Sustain_HpRecover2:
				case kEffect_Sustain_AmmoRecover2:
				case kEffect_Sustain_ArmorRecover2:
					{
						double v = effectdata.interval * 5.f;
						// 略微延长timer
						if (effectdata.interval_timer < v)
							effectdata.interval_timer = v;
					}
					break;
					
				default:
					break;
			}
		}
	}
	
	// m_AcquiredEffect
	for (EffectDataList::iterator itr = m_AcquiredEffect.begin(); itr != m_AcquiredEffect.end(); itr++)
	{
		EffectData &effectdata = *itr;
		
		if (effectdata.enable)
		{
			switch (effectdata.type)
			{
				case kEffect_Sustain_HpRecover2:
				case kEffect_Sustain_AmmoRecover2:
				case kEffect_Sustain_ArmorRecover2:
					{
						double v = effectdata.interval * 5.f;
						// 略微延长timer
						if (effectdata.interval_timer < v)
							effectdata.interval_timer = v;
					}
					break;
					
				default:
					break;
			}
		}
	}
}

void SkillEffect::OnClientUseSkill(ESkillSlot slot)
{
	// m_Skill
	for (SkillDataList::iterator itr = m_Skill.begin(); itr != m_Skill.end(); itr++)
	{
		SkillData &skilldata = *itr;
		
		if (skilldata.enable && 
			skilldata.slot == slot &&
			skilldata.use_callback &&
			skilldata.cd_time <= 0)
		{
			skilldata.use_callback(skilldata, *m_pClient);
		}
	}
}

//////////////////////////////////////////////////////////////
void SkillEffect::ForceSendToClient(Client &client)
{
	bool DirtFlgSkillBk = m_DirtFlgSkill;
	bool DirtFlgNaturalBk = m_DirtFlgNatural;
	bool DirtFlgAcquiredBk = m_DirtFlgAcquired;
	
	m_DirtFlgSkill = true;
	m_DirtFlgNatural = true;
	m_DirtFlgAcquired = true;
	
	Send_SyncSkillEffect(client, this);
	
	m_DirtFlgSkill = DirtFlgSkillBk;
	m_DirtFlgNatural = DirtFlgNaturalBk;
	m_DirtFlgAcquired = DirtFlgAcquiredBk;
}

//////////////////////////////////////////////////////////////
void SkillEffect::OnSkillEffectChanged()
{
	if (m_DirtFlgSkill || 
		m_DirtFlgNatural || 
		m_DirtFlgAcquired)
	{
		m_pClient->OnSkillEffectChanged();
		
		// Sync
		server.BroadcastClients(&SkillEffect::Send_SyncSkillEffect, this, NULL);
		
		m_DirtFlgSkill = false;
		m_DirtFlgNatural = false;
		m_DirtFlgAcquired = false;
	}
}

void SkillEffect::ChangeBaseItemInfo_Inside(const BaseItemInfo &info, bool add, bool is_enable)
{
	uint player_id = m_pClient->GetCharacterInfo().character_id;
	uint player_item_id = info.player_item_id;
	
	if (add)
	{
		for (uint slotid = 0; slotid < info.attrs.size(); slotid++)
		{
			const AttributeList &attributelist = info.attrs[slotid];
			for (uint sub_slotid = 0; sub_slotid < attributelist.size(); sub_slotid++)
			{
				const Attribute &attribute = attributelist[sub_slotid];
				
				if (WeaponAttrToSkill(player_id, player_item_id, slotid, sub_slotid, is_enable, attribute, m_Skill))
				{
					m_DirtFlgSkill = true;
				}
				
				if (WeaponAttrToNaturalEffect(player_id, player_item_id, slotid, sub_slotid, is_enable, attribute, m_NaturalEffect))
				{
					m_DirtFlgNatural = true;
				}
			}
		}
	}
	else
	{
		EffectData search_data;
		EffectDataPtrList result_e;
		SkillDataPtrList result_s;
		
		search_data.player_id = player_id;
		search_data.player_item_id = player_item_id;
		for (uint slotid = 0; slotid < info.attrs.size(); slotid++)
		{
			const AttributeList &attributelist = info.attrs[slotid];
			for (uint sub_slotid = 0; sub_slotid < attributelist.size(); sub_slotid++)
			{
				search_data.attr_slotid = slotid;
				search_data.attr_sub_slotid = sub_slotid;
				
				// m_Skill
				SearchSkillData(m_Skill, search_data, result_s);
				for (SkillDataPtrList::iterator itr = result_s.begin(); itr != result_s.end(); itr++)
				{
					SkillData *pSkillData = *itr;
					for (SkillDataList::iterator itr = m_Skill.begin(); itr != m_Skill.end(); )
					{
						SkillData &skilldata = *itr;
						if (&skilldata == pSkillData)
						{
							itr = m_Skill.erase(itr);
							
							m_DirtFlgSkill = true;
							
							continue;
						}
						
						itr++;
					}
				}
				
				// m_NaturalEffect
				SearchEffectData(m_NaturalEffect, search_data, result_e);
				for (EffectDataPtrList::iterator itr = result_e.begin(); itr != result_e.end(); itr++)
				{
					EffectData *pEffectdata = *itr;
					for (EffectDataList::iterator itr = m_NaturalEffect.begin(); itr != m_NaturalEffect.end(); )
					{
						EffectData &effectdata = *itr;
						if (&effectdata == pEffectdata)
						{
							itr = m_NaturalEffect.erase(itr);
							
							m_DirtFlgNatural = true;
							
							continue;
						}
						
						itr++;
					}
				}
			}
		}
	}
}

void SkillEffect::ClearUp()
{
	if (m_ApplyEffectLock)
	{
		DEBUGLOG_WRITE("SkillEffect::ClearUp(), ApplyEffectLock is On(%d)!!!", m_ApplyEffectLock);
		
		log_write(LOG_ERROR, "SkillEffect::ClearUp(), ApplyEffectLock is On!!!");
		
		assert(0);
	}

	m_Skill.clear();
	m_DirtFlgSkill = true;
	
	m_NaturalEffect.clear();
	m_DirtFlgNatural = true;
	
	m_AcquiredEffect.clear();
	m_DirtFlgAcquired = true;
}

void SkillEffect::Send_SyncSkillEffect(Client &client, const void *userdata)
{
	SkillEffect *pSkillEffect = (SkillEffect*)userdata;
	if (pSkillEffect && client.IsConnected())
	{	
		byte SyncFlg = 0;
		SyncFlg |= full_mode << 0;
		SyncFlg |= pSkillEffect->m_DirtFlgSkill << 1;
		SyncFlg |= pSkillEffect->m_DirtFlgNatural << 2;
		SyncFlg |= pSkillEffect->m_DirtFlgAcquired << 3;
		
		client.BeginWrite();
		client.WriteByte(SM_SyncSkillEffect);
		client.WriteByte(pSkillEffect->m_pClient->uid);
		client.WriteByte(SyncFlg);
		
		if (pSkillEffect->m_DirtFlgSkill)
		{
			WriteSkillDataList(client, pSkillEffect->m_Skill, full_mode);
		}
		
		if (pSkillEffect->m_DirtFlgNatural)
		{
			WriteEffectDataList(client, pSkillEffect->m_NaturalEffect, full_mode);
		}
		
		if (pSkillEffect->m_DirtFlgAcquired)
		{
			WriteEffectDataList(client, pSkillEffect->m_AcquiredEffect, full_mode);
		}
		
		client.EndWrite();
	}
}

void SkillEffect::AddSkillFromCharacter(std::string skill_str)
{
	SkillData skillData;
	sscanf(skill_str.c_str(), "%d,%f,%f,%f,%f,%f,%f", &skillData.skill_id, &skillData.param[0], &skillData.param[1],
		&skillData.param[2], &skillData.param[3], &skillData.param[4], &skillData.param[5]);

	switch(skillData.skill_id)
	{
		// param参数解释 1.CD时间 2.持续时间 3.吸收伤害值 4.护盾开启，玩家自身受到伤害值 5.护盾爆发范围 6.保留
	case kCharacterSkill_EnergyShield:
		skillData.slot = kSkillSlot_KC_6;
		break;
	default: 
		log_write(LOG_DEBUG1, "unkown skill type %d !", skillData.skill_id);
		return;
	}

	skillData.carrer_id = m_pClient->GetCurCharinfo().career_id;
	if (GetSkillData(skillData.skill_id) )
	{
		log_write(LOG_ERROR, "the skill is repeat %d !!", skillData.skill_id);
		return;
	}
	m_Skill.push_back(skillData);
}

void SkillEffect::OnSkillEffectEnd(SkillData& skillData)
{
	switch(skillData.skill_id)
	{
	case kEffect_Infect_HurtAbsorb:
		{
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;

				if (c->IsReady() && m_pClient != c)
				{
					Vector3 distance = c->position - m_pClient->position;
					if (Length(distance) <= skillData.param[4])
					{
						c->SkillHurt(m_pClient, skillData.data[0]);
					}
				}
			}
		}
		break;
	default:
		assert(0);
		break;
	}
}
