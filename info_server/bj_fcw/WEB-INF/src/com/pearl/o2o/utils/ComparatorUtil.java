package com.pearl.o2o.utils;

import java.util.Comparator;

import com.pearl.o2o.pojo.BaseMappingBean;
import com.pearl.o2o.pojo.OnlineAward;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;

public class ComparatorUtil {
	public static class IdComparatorClass implements Comparator<Object> {
		@Override
		public int compare(Object arg0, Object arg1) {
			Integer ch1 = ((BaseMappingBean<?>)arg0).getId();
			Integer ch2 = ((BaseMappingBean<?>)arg1).getId();
			return ch1.compareTo(ch2);
		}
	}
	public static class SysItemPayTypeComparatorClass implements Comparator<Object> {
		@Override
		public int compare(Object arg0, Object arg1) {
			Integer ch1 = ((Payment)arg0).getPayType();
			Integer ch2 = ((Payment)arg1).getPayType();;
			return ch1.compareTo(ch2);
		}
	}
	public static class SysItemUnitTypeComparatorClass implements Comparator<Object> {
		@Override
		public int compare(Object arg0, Object arg1) {
			Integer ch1 = ((Payment)arg0).getUnitType();
			Integer ch2 = ((Payment)arg1).getUnitType();;
			return ch1.compareTo(ch2);
		}
	}
	public static class SysItemWeaponSeqComparatorClass implements Comparator<Object> {
		@Override
		public int compare(Object arg0, Object arg1) {
			Integer ch1 = CommonUtil.getWeaponSeq(((SysItem)arg0).getWId());
			Integer ch2 = CommonUtil.getWeaponSeq(((SysItem)arg1).getWId());
			return ch1.compareTo(ch2);
		}
	}
	public static class PlayerItemIndexComparatorClass implements Comparator<PlayerItem> {
		@Override
		public int compare(PlayerItem arg0, PlayerItem arg1) {
			try{
				Integer ch1 = ServiceLocator.getService.getSysItemByItemId((arg0).getItemId()).getItemIndex();
				Integer ch2 = ServiceLocator.getService.getSysItemByItemId((arg1).getItemId()).getItemIndex();
				return ch1 - ch2;
			}catch (Exception e) {
				ServiceLocator.exceptionLog.warn("happen in PlayerItemIndexComparatorClass",e);
				return 1;
			}
		}
	}
	public static class PlayerStorgeComparator implements Comparator<PlayerItem> {
		@Override
		public int compare(PlayerItem arg0, PlayerItem arg1) {
			try{
				int lv0 = arg0.getLevel();
				int lv1 = arg1.getLevel();
				if(lv0==lv1){
					return ServiceLocator.getService.getSysItemByItemId((arg0).getItemId()).getItemIndex() - ServiceLocator.getService.getSysItemByItemId((arg1).getItemId()).getItemIndex();
				}
				return lv1-lv0;
			}catch (Exception e) {
				ServiceLocator.exceptionLog.warn("happen in PlayerStorgeComparatorClass",e);
				return 1;
			}
		}
	}
	
	public static class SysItemIndexComparatorClass implements Comparator<SysItem> {
		@Override
		public int compare(SysItem arg0, SysItem arg1) {
			try{
				Integer ch1 = arg0.getItemIndex();
				Integer ch2 = arg1.getItemIndex();
				return ch1.compareTo(ch2);
			}catch (Exception e) {
				ServiceLocator.exceptionLog.warn("happen in SysItemIndexComparatorClass",e);
				return 1;
			}
		}
	}
	
	public static class OnlineAwardComparatorClass implements Comparator<OnlineAward> {
		@Override
		public int compare(OnlineAward arg0, OnlineAward arg1) {
			SysItem s0 = arg0.getSysItem();
			SysItem s1 = arg1.getSysItem();
			if(s0==null||s1==null){
				return 0;
			}
			int typeCom= s1.getRareLevel() - s0.getRareLevel();
			if(typeCom==0){
				typeCom = s0.getType() - s1.getType();
			}
			if(typeCom==0){
				typeCom = s0.getSubType() - s1.getSubType();
			}
			if(typeCom==0){
				typeCom = s0.getMType() - s1.getMType();
			}
			if(typeCom==0){
				typeCom = Integer.parseInt(s0.getCharacterId()) - Integer.parseInt(s1.getCharacterId());
			}
			if(typeCom==0){
				typeCom = s0.getId() - s1.getId();
			}
			if(typeCom==0){
				typeCom = arg1.getUnit() - arg0.getUnit();
			}
			return typeCom;
		}
	}
	
}
