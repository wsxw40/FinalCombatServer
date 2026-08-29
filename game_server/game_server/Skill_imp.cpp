
enum ESustainHurtType
{
	kSustainNone 				= 0,
	kSustainFlameHurt			= 1,
	kSustainHurtCutHurt			= 2,
	kSustainHurtPoison			= 3,
	kSustainHurtFrost			= 4,
	kSustainSurvivalMode		= 5,
	
	kSustainHurtcount ,
};

//////////////////////////////////////////////////////////////
static void Effect_ItervalHandle(EffectData &effectdata, Client &client)
{
	switch (effectdata.type)
	{
		case kEffect_Sustain_HurtBurn_Replace:
		case kEffect_Sustain_HurtBurn:
			client.TakeSustainHurt(server.GetClientByPId(effectdata.player_id), effectdata.value, kSustainFlameHurt);
			break;
		
		case kEffect_Sustain_HurtBloodshed_Replace:
		case kEffect_Sustain_HurtBloodshed:
			client.TakeSustainHurt(server.GetClientByPId(effectdata.player_id), effectdata.value, kSustainHurtCutHurt);
			break;
			
		case kEffect_Sustain_HurtPoison_Replace:
		case kEffect_Sustain_HurtPoison:
			client.TakeSustainHurt(server.GetClientByPId(effectdata.player_id), effectdata.value, kSustainHurtPoison);
			break;
		case kEffect_Sustain_Survival_Mode:
			{
				if (client.GetSkillEffect().HasEffect(kEffect_Survival_Debuff))
				{
					if (client.health <= 2 && client.CanBeGhost())
					{
						client.ghostflag = true;
					}
					else
						client.TakeSustainHurt(server.GetClientByPId(effectdata.player_id), (int)(((client.max_health * effectdata.value) + 1) * 2), kSustainSurvivalMode);
				}
				else
				{
					if (client.health <= 1 && client.CanBeGhost())
					{
						client.ghostflag = true;
					}
					else
						client.TakeSustainHurt(server.GetClientByPId(effectdata.player_id), (int)(client.max_health * effectdata.value) + 1, kSustainSurvivalMode);
				}
			}
			break;
		case kEffect_Sustain_HpRecover:
		case kEffect_Sustain_HpRecover2:
			client.Recover(effectdata.value, kRecoverSelf);
			break;
			
		case kEffect_Sustain_AmmoRecover:
		case kEffect_Sustain_AmmoRecover2:
			client.AmmoRecover(kSupplyDropWeapon, effectdata.value);
			break;
		
		case kEffect_Sustain_ArmorRecover:
		case kEffect_Sustain_ArmorRecover2:
			client.ArmorRecover(kRecoverSelf, effectdata.value);
			break;
		default:
			log_write(LOG_NOTICE, "Effect_ItervalHandle() Unknow effect type : %d", effectdata.type);
			break;
	}
}

static void Skill_UseHandle(SkillData &skilldata, Client &self)
{
	switch (skilldata.type)
	{
		case kSkill_Self:
			if (self.IsAlive())
				self.GetSkillEffect().ApplyEffect(skilldata.effect);
			break;
		case kSkill_SelfTeam:
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;
				if (c->IsAlive() && c->team == self.team)
				{
					Vector3 distance = c->position - self.position;
					if (Length(distance) <= skilldata.value)
						c->GetSkillEffect().ApplyEffect(skilldata.effect);
				}
			}
			break;
		case kSkill_EnemyTeam:
			for (uint i = 0; i < max_client_count; i++)
			{
				Client * c = server.clients + i;
				if (c->IsAlive() && c->team != self.team && c->team < 2)
				{
					Vector3 distance = c->position - self.position;
					if (Length(distance) <= skilldata.value)
						c->GetSkillEffect().ApplyEffect(skilldata.effect);
				}
			}
			break;
			
		default:
			log_write(LOG_NOTICE, "Skill_UseHandle() Unknow skill type : %d", skilldata.type);
			break;
	}
}

static void Skill_UseHandleSustain(SkillData &skilldata, Client &self)
{
	switch (skilldata.type)
	{
		case kSkill_Self:
			if (self.IsAlive())
			{
				self.GetSkillEffect().ApplyEffect(skilldata.effect);
				
				EffectData effect;
				effect.duration_timer = skilldata.effect.duration_timer;
				//effect.player_id = self.GetCharacterInfo().character_id;
				effect.player_id = skilldata.effect.player_id;
				effect.player_item_id = skilldata.effect.player_item_id;
				effect.type = kEffect_Special_UsingSkill;
				effect.enable = true;
				
				self.GetSkillEffect().ApplyEffect(effect);
			}
			break;
			
		default:
			log_write(LOG_NOTICE, "Skill_UseHandleSustain() Unknow skill type : %d", skilldata.type);
			break;
	}
}

static void Skill_HitHandle(SkillData &skilldata, Client &self, Client &target)
{
	switch (skilldata.type)
	{
		case kSkill_Self:
			if (self.IsAlive())
				self.GetSkillEffect().ApplyEffect(skilldata.effect);
			break;
		case kSkill_Target:
			if (target.IsAlive())
				target.GetSkillEffect().ApplyEffect(skilldata.effect);
			break;
		case kSkill_SuckBlood:
			if (self.uid != target.uid)
				self.Recover(skilldata.value, kRecoverSelf);
			break;
		case kSkill_CureEnergy:
			if (self.uid != target.uid)
				self.ModifyCurePower(skilldata.value);
			break;
		
		default:
			log_write(LOG_NOTICE, "Skill_HitHandle() Unknow skill type : %d", skilldata.type);
			break;
	}
}

static void Skill_KillHandle(SkillData &skilldata, Client &self, Client &target)
{
	switch (skilldata.type)
	{
		case kSkill_Self:
			if (self.IsAlive())
				self.GetSkillEffect().ApplyEffect(skilldata.effect);
			break;
		case kSkill_Target:
			if (target.IsAlive())
				target.GetSkillEffect().ApplyEffect(skilldata.effect);
			break;
		case kSkill_SuckBlood:
			if (self.uid != target.uid)
				self.Recover(skilldata.value, kRecoverSelf);
			break;
		case kSkill_CureEnergy:
			if (self.uid != target.uid)
				self.ModifyCurePower(skilldata.value);
			break;
		
		default:
			log_write(LOG_NOTICE, "Skill_KillHandle() Unknow skill type : %d", skilldata.type);
			break;
	}
}

//////////////////////////////////////////////////////////////
static bool BuffItemToSkill(const BuffItem &buffitem, SkillDataList &skilldatalist)
{
	return false;
}

static bool BuffItemToNaturalEffect(const BuffItem &buffitem, EffectDataList &effectdatalist, uint career_id)
{
	EffectData effectdata;
	EffectData *pEffectData = NULL;
	
	if (buffitem.id == kBuffIdRoom)
	{
		switch (buffitem.type)
		{
			case kBuffTypeHealthMax:
				pEffectData = &effectdata;
				pEffectData->type = kEffect_Infect_MaxHp;
				pEffectData->value = buffitem.value / 100.f;
				break;
			case kBuffTypeHealth:
				pEffectData = &effectdata;
				pEffectData->type = kEffect_Infect_SupplyHpRecover;
				pEffectData->value = buffitem.value / 100.f;
				break;
			case kBuffTypeAmmo:
				pEffectData = &effectdata;
				pEffectData->type = kEffect_Infect_SupplyAmmo;
				pEffectData->value = buffitem.value / 100.f;
				break;
			case kBuffTypeRebirthSpeed:
				pEffectData = &effectdata;
				pEffectData->type = kEffect_Infect_Rebirth;
				pEffectData->value = -buffitem.value;
				break;
			case kBuffTypeMainWeaponAmmo:
				pEffectData = &effectdata;
				pEffectData->type = kEffect_Infect_AmmoCount;
				pEffectData->value = buffitem.value / 100.f;
				break;
			
			case kBuffTypeTeamDamage:
				pEffectData = &effectdata;
				pEffectData->type = kEffect_Infect_Damage;
				pEffectData->value = buffitem.value / 100.f;
				break;
			case kBuffTypeTeamHealthMax:
				pEffectData = &effectdata;
				pEffectData->type = kEffect_Infect_MaxHp;
				pEffectData->value = buffitem.value / 100.f;
				break;
			case kBuffTypeTeamSupplyAmmo:
				pEffectData = &effectdata;
				pEffectData->type = kEffect_Infect_SupplyAmmo;
				pEffectData->value = buffitem.value / 100.f;
				break;
			case kBuffTypeTeamSupplyHp:
				pEffectData = &effectdata;
				pEffectData->type = kEffect_Infect_SupplyHpRecover;
				pEffectData->value = buffitem.value / 100.f;
				break;
			case kBuffTypeTeamCareerId1SelfResistance:
				if (career_id == 1)
				{
					pEffectData = &effectdata;
					pEffectData->type = kEffect_Infect_SelfResistanceExplode;
					pEffectData->value = buffitem.value / 100.f;
				}
				break;
			case kBuffTypeTeamCareerId3GunEnergy:
				if (career_id == 3)
				{
					pEffectData = &effectdata;
					pEffectData->type = kEffect_Infect_GunEnergy;
					pEffectData->value = buffitem.value / 100.f;
				}
				break;
			case kBuffTypeTeamCareerId4ReloadTime:
				if (career_id == 4)
				{
					pEffectData = &effectdata;
					pEffectData->type = kEffect_Infect_ReloadTime;
					pEffectData->value = -buffitem.value / 100.f;
				}
				break;
			case kBuffTypeTeamCareerId5AmmoCount:
				if (career_id == 5)
				{
					pEffectData = &effectdata;
					pEffectData->type = kEffect_Infect_AmmoOneClip;
					pEffectData->value = buffitem.value / 100.f;
				}
				break;
			case kBuffTypeTeamCareerId6CureEnergy:
				if (career_id == 6)
				{
					pEffectData = &effectdata;
					pEffectData->type = kEffect_Infect_CureEnergy;
					pEffectData->value = buffitem.value / 100.f;
				}
				break;
				
			default:
				break;
		}
	}
	
	if (pEffectData)
	{
		pEffectData->duration_timer = DURATION_INFINITY;
		pEffectData->player_id = 0;
		pEffectData->player_item_id = 0;
		pEffectData->attr_slotid = -1;
		pEffectData->attr_sub_slotid = -1;
		pEffectData->enable = true;
		
		effectdatalist.push_back(*pEffectData);
		
		return true;
	}
	
	return false;
}

static bool WeaponAttrToSkill(uint player_id, uint player_item_id, 
							byte attr_slotid, byte attr_sub_slotid, 
							bool enable, const Attribute &attribute, 
							SkillDataList &skilldatalist)
{
	SkillData skilldata;
	SkillData *pSkillData = NULL;
	
	SkillData skilldata2;
	SkillData *pSkillData2 = NULL;
	
	bool ret = false;
	
	switch (attribute.type)
	{
		case kWeaponAttr_Target_SlowDown:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_MoveSpeed;
			pSkillData->effect.value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Target_AlwaysBeSubBoost:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_ResistanceAll;
			pSkillData->effect.value = -0.15f;
			break;
		case kWeaponAttr_Target_SustainHurt:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.iterval_callback = &Effect_ItervalHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.interval = pSkillData->effect.interval_timer = 1;
			if (attribute.value2 == 1)
				pSkillData->effect.type = kEffect_Sustain_HurtBurn_Replace;
			else if (attribute.value2 == 2)
				pSkillData->effect.type = kEffect_Sustain_HurtBloodshed_Replace;
			else
				pSkillData->effect.type = kEffect_Sustain_HurtPoison_Replace;
			pSkillData->effect.value = attribute.value1;
			break;
		case kWeaponAttr_Target_BulletResistanceReduce:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_ResistanceBullet;
			pSkillData->effect.value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Target_ExplodeResistanceReduce:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_ResistanceExplode;
			pSkillData->effect.value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Target_CloseResistanceReduce:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_ResistanceClose;
			pSkillData->effect.value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Target_FlameResistanceReduce:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_ResistanceFlame;
			pSkillData->effect.value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Target_RecoverReduce:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_SupplyHpRecover;
			pSkillData->effect.value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Target_CureReduce:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_CureHpRecover;
			pSkillData->effect.value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_From_SubBoostAdd:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->kill_callback = &Skill_KillHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_Damage;
			pSkillData->effect.value = 0.15f;
			break;
		case kWeaponAttr_From_SuckBloodInAttack:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_SuckBlood;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->value = attribute.value1;
			break;
		case kWeaponAttr_From_SuckBloodAfterKill:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_SuckBlood;
			pSkillData->kill_callback = &Skill_KillHandle;
			pSkillData->value = attribute.value1;
			break;
		case kWeaponAttr_From_AddCureEnergyInAttack:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_CureEnergy;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->value = attribute.value1;
			break;
		case kWeaponAttr_From_AddCureEnergyAfterKill:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_CureEnergy;
			pSkillData->kill_callback = &Skill_KillHandle;
			pSkillData->value = attribute.value1;
			break;
		case kWeaponAttr_Target_CannotHide:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Special_CannotHide;
			break;
		case kWeaponAttr_Target_ReversalMouse:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Special_ReversalMouse;
			break;
		case kWeaponAttr_Target_ReversalMouse2:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Special_ReversalMouse2;
			break;
		case kWeaponAttr_Target_ViewLost:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Special_ViewLost;
			break;
		case kWeaponAttr_Target_ViewLost2:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Target;
			pSkillData->hit_callback = &Skill_HitHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Special_ViewLost2;
			break;
		case kWeaponAttr_Benefit_DamageAdd:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_SelfTeam;
			pSkillData->value = attribute.value2 / 10.f;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_Damage;
			pSkillData->effect.value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Benefit_DefenceAdd:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_SelfTeam;
			pSkillData->value = attribute.value2 / 10.f;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_ResistanceAll;
			pSkillData->effect.value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Skill_AddBlood:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandleSustain;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_SelfHpRecover;
			pSkillData->effect.value = attribute.value1;
			
			pSkillData2 = &skilldata2;
			pSkillData2->type = kSkill_Self;
			pSkillData2->use_callback = &Skill_UseHandleSustain;
			pSkillData2->effect.duration_timer = attribute.time;
			pSkillData2->effect.type = kEffect_Infect_Cure;
			pSkillData2->effect.value = attribute.value1;
			break;
		case kWeaponAttr_Skill_Invincible:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandleSustain;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Invincible;
			break;
		case kWeaponAttr_Skill_Boost:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandleSustain;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_Damage;
			pSkillData->effect.value = attribute.value1 / 100.f;
			break;
			
		case kWeaponAttr_ZombieSkill_AllResistance:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_ResistanceAll;
			pSkillData->effect.value = attribute.value1 / 100.f;	
			break;

		case kWeaponAttr_ZombieSkill_MoveMent:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_MoveSpeed;
			pSkillData->effect.value = attribute.value1 / 100.f;	
			break;
		
		case kWeaponAttr_ZombieSkill_JumpHeight:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_JumpHeight;
			pSkillData->effect.value = attribute.value1 / 100.f;	
			break;
		
		case kWeaponAttr_ZombieSkill_Charge:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_MoveSpeed;
			pSkillData->effect.value = attribute.value1 / 100.f;
			break;
			
		case kWeaponAttr_ZombieSkill_FOV:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_FOV;
			pSkillData->effect.value = attribute.value1;
			break;

		case kWeaponAttr_ZombieSkill_HitBack:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Infect_HitBack;
			pSkillData->effect.value = attribute.value1/100.0f;
			break;

		case kWeaponAttr_CommonZombieSkill_Invisible:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Special_Invisible;
			pSkillData->effect.value = attribute.value1;
			break;

		case kWeaponAttr_CommonZombieSkill_Smog:
			pSkillData = &skilldata;
			pSkillData->type = kSkill_Self;
			pSkillData->use_callback = &Skill_UseHandle;
			pSkillData->effect.duration_timer = attribute.time;
			pSkillData->effect.type = kEffect_Special_Smog;
			pSkillData->effect.value = attribute.value1;
			break;

		default:
			//log_write(LOG_NOTICE, "WeaponAttrToSkill() Unknow attribute type : %d", attribute.type);
			break;
	}
	
	if (pSkillData)
	{
		// 特性型跟随装备，永久有效
		pSkillData->duration_timer = DURATION_INFINITY;
		pSkillData->enable = enable;
		pSkillData->slot = kSkillSlot_LeftButton;
		pSkillData->skill_id = attribute.type;
		
		pSkillData->effect.player_id = player_id;
		pSkillData->effect.player_item_id = player_item_id;
		pSkillData->effect.attr_slotid = attr_slotid;
		pSkillData->effect.attr_sub_slotid = attr_sub_slotid;
		pSkillData->effect.attr_raw = attribute;
		pSkillData->effect.enable = true;
		
		skilldatalist.push_back(*pSkillData);
		
		ret = true;;
	}
	if (pSkillData2)
	{
		// 特性型跟随装备，永久有效
		pSkillData2->duration_timer = DURATION_INFINITY;
		pSkillData2->enable = enable;
		pSkillData->skill_id = attribute.type;
		
		pSkillData2->effect.player_id = player_id;
		pSkillData2->effect.player_item_id = player_item_id;
		pSkillData2->effect.attr_slotid = attr_slotid;
		pSkillData2->effect.attr_sub_slotid = attr_sub_slotid;
		pSkillData2->effect.attr_raw = attribute;
		pSkillData2->effect.enable = true;
		
		skilldatalist.push_back(*pSkillData2);
		
		ret = true;;
	}
	
	return ret;
}

static bool WeaponAttrToNaturalEffect(uint player_id, uint player_item_id, 
							byte attr_slotid, byte attr_sub_slotid, 
							bool enable, const Attribute &attribute, 
							EffectDataList &effectdatalist)
{
	EffectData effectdata;
	EffectData *pEffectData = NULL;
	
	// 特性型
	switch (attribute.type)
	{
		case kWeaponAttr_From_AddWeaponDamage:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_Damage;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Target_AmmoJump:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Special_HitTargetJump;
			pEffectData->value = attribute.value1 / 100.f;
			break;
	
		default:
			break;
	}
	
	if (pEffectData)
	{
		// 特性型跟随装备，永久有效
		pEffectData->duration_timer = DURATION_INFINITY;
		pEffectData->player_id = player_id;
		pEffectData->player_item_id = player_item_id;
		pEffectData->attr_slotid = attr_slotid;
		pEffectData->attr_sub_slotid = attr_sub_slotid;
		pEffectData->attr_raw = attribute;
		pEffectData->enable = enable;
		
		effectdatalist.push_back(*pEffectData);
		
		return true;
	}
	
	// 装备型
	switch (attribute.type)
	{
		case kWeaponAttr_From_LowBloodHighDamage:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_HpInfectDamage;
			pEffectData->value = ((float)attribute.value2 / (float)attribute.value1) / 100.f;
			break;
		case kWeaponAttr_Self_CannotSlowDown:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Invalid_MoveSpeed;
			break;
		case kWeaponAttr_Self_SpeedInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_MoveSpeed;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_CannotBeBurn:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Invalid_Sustain_HurtBurn;
			break;
		case kWeaponAttr_Self_CannotBeCut:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Invalid_Sustain_HurtBloodshed;
			break;
		case kWeaponAttr_Self_AlwaysSubBoost:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_ResistanceAll;
			pEffectData->value = -0.35f;
			break;
		case kWeaponAttr_Self_RecoverInfectMore:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_SupplyHpRecover;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_BulletResistance:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_ResistanceBullet;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ExplodeResistance:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_ResistanceExplode;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_CloseResistance:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_ResistanceClose;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_FlameResistance:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_ResistanceFlame;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_RecoverInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_SupplyHpRecover;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_CureInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CureHpRecover;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_MaxBloodInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_MaxHp;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_AmmoOneClipInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_AmmoOneClip;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_AmmoCountInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_AmmoCount;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_FireTimeInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_FireTime;
			pEffectData->value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ReloadTimeInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_ReloadTime;
			pEffectData->value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ChangeInTimeInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_ChangeInTime;
			pEffectData->value = -attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_DamageInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_Damage;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_AmmoFlySpeedInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_AmmoFlySpeed;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_HurtRangeInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_AmmoExplodeRange;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_SelfHurtInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_SelfResistanceExplode;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_SniperEnergyInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_GunEnergy;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_CureEnergyInfect:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CureEnergy;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_AutoRecoverInfect:
			pEffectData = &effectdata;
			pEffectData->iterval_callback = &Effect_ItervalHandle;
			pEffectData->interval = pEffectData->interval_timer = 1;
			pEffectData->type = kEffect_Sustain_HpRecover;
			pEffectData->value = attribute.value1;
			break;
		case kWeaponAttr_Self_LowBloodHighSpeed:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_HpInfectMoveSpeed;
			pEffectData->value = ((float)attribute.value2 / (float)attribute.value1) / 100.f;
			break;
		case kWeaponAttr_Self_CannotOutFlame:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Special_CannotOutFlame;
			break;
		case kWeaponAttr_Self_AmmoJump:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Special_HittedSelfJump;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_AutoAmmoRecoverInfect:
			pEffectData = &effectdata;
			pEffectData->iterval_callback = &Effect_ItervalHandle;
			pEffectData->interval = pEffectData->interval_timer = attribute.value2;
			pEffectData->type = kEffect_Sustain_AmmoRecover;
			pEffectData->value = attribute.value1;
			break;
		case kWeaponAttr_Self_DamageFireman:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerDamage_Id1;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_DamageButcher:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerDamage_Id2;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_DamageOfficelady:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerDamage_Id3;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_DamageLeader:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerDamage_Id4;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_DamageFirebat:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerDamage_Id5;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_DamageMedic:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerDamage_Id6;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ResistanceFireman:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerResistance_Id1;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ResistanceButcher:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerResistance_Id2;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ResistanceOfficelady:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerResistance_Id3;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ResistanceLeader:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerResistance_Id4;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ResistanceFirebat:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerResistance_Id5;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ResistanceMedic:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerResistance_Id6;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_AddScore:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_Score;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_DamageEngineer:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerDamage_Id7;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		case kWeaponAttr_Self_ResistanceEngineer:
			pEffectData = &effectdata;
			pEffectData->type = kEffect_Infect_CareerResistance_Id7;
			pEffectData->value = attribute.value1 / 100.f;
			break;
		
		default:
			break;
	}
	
	if (pEffectData)
	{
		// 装备型为系统施展，永久有效，永久开启
		pEffectData->duration_timer = DURATION_INFINITY;
		pEffectData->player_id = 0;
		pEffectData->player_item_id = 0;
		pEffectData->attr_slotid = -1;
		pEffectData->attr_sub_slotid = -1;
		pEffectData->attr_raw = attribute;
		pEffectData->enable = true;
		
		effectdatalist.push_back(*pEffectData);
		
		return true;
	}
	
	//log_write(LOG_NOTICE, "WeaponAttrToNaturalEffect() Unknow attribute type : %d", attribute.type);
	
	return false;
}

static bool Debug_AddEffect(EffectDataList &effectdatalist, const CharacterInfo& character_info)
{
	if (appcfg.add_debugeffect && strcmp(appcfg.debug_username, character_info.character_name) == 0)
	{
		EffectData effect;
		
		effect.duration_timer = appcfg.debugeffect_duration;
		effect.interval = appcfg.debugeffect_interval;
		effect.interval_timer = effect.interval;
		if (effect.interval > 0)
		{
			effect.iterval_callback = &Effect_ItervalHandle;
		}
		effect.type = (EEffect)appcfg.debugeffect_type;
		effect.value = appcfg.debugeffect_value;
		effect.enable = true;
		
		effectdatalist.push_back(effect);
		
		DEBUGLOG_WRITE("Debug_AddEffect(), name : %s, type : %d, value : %f, has_iterval : %d", 
						character_info.character_name, effect.type, effect.value, effect.iterval_callback != NULL);
		
		return true;
	}

	return false;
}

static void Debug_TestWeaponAttr(const CharacterInfo& character_info)
{
	if (appcfg.test_weaponattr && strcmp(appcfg.debug_username, character_info.character_name) == 0)
	{
		SkillDataList skills;
		EffectDataList effects;
		
		Attribute attribute;
		
		attribute.value1 = 10;
		attribute.value2 = 10;
		attribute.time = 10;
		
		for (short i = 0; i < 256; i++)
		{
			attribute.type = i;
			
			skills.clear();
			effects.clear();
			
			WeaponAttrToSkill(0, 0, 0, 0, false, attribute, skills);
			WeaponAttrToNaturalEffect(0, 0, 0, 0, false, attribute, effects);
			
			DEBUGLOG_WRITE("Attribute : %s", attribute.ToString().c_str());
			
			DEBUGLOG_WRITE("skills.size() : %d", skills.size());
			for (SkillDataList::iterator itr = skills.begin(); itr != skills.end(); itr++)
			{
				DEBUGLOG_WRITE("skill : %s", itr->ToString().c_str());
			}
			
			DEBUGLOG_WRITE("effects.size() : %d", effects.size());
			for (EffectDataList::iterator itr = effects.begin(); itr != effects.end(); itr++)
			{
				DEBUGLOG_WRITE("effect : %s", itr->ToString().c_str());
			}
			
			DEBUGLOG_WRITE("%s", "\n");
		}
	}
}
