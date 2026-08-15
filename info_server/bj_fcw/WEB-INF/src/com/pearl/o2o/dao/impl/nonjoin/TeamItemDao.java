package com.pearl.o2o.dao.impl.nonjoin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.TeamItem;
import com.pearl.o2o.utils.Constants;

public class TeamItemDao extends BaseMappingDao {
	// SUB_TYPE 1 防御，2攻击，3消耗
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TeamItem> getAllTeamItem() {
		return new ArrayList(getSqlMapClientTemplate().queryForMap(
				"TeamItem.selectAll", null, "id").values());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TeamItem> getTeamItemList(int teamId) throws Exception {
		List<TeamItem> result = new ArrayList<TeamItem>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("teamId", teamId);
		List<TeamItem> tempList = new ArrayList(getSqlMapClientTemplate()
				.queryForMap("TeamItem.selectByTeamId", map,
						"id").values());
		if (tempList != null) {
			for (TeamItem teamItem : tempList) {//数据库拿到的是正确的ID的teamItem
				teamItem=getTeamItemById(teamItem.getId());// 缓存中的才是正确的
				result.add(getTeamItemById(teamItem.getId()));
			}
		}
		return result;
	}

	/**
	 * 仅用于升级树
	 * 
	 * @param teamId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<TeamItem> getTeamItemListByTeamId(int teamId) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("teamId", teamId);
		return new ArrayList(getSqlMapClientTemplate().queryForMap(
				"TeamItem.selectAllItem", map, "id").values());
	}

	public TeamItem getTeamItemById(int teamItemId) {
		TeamItem result = queryMappingBeanById(TeamItem.class, teamItemId);
		if (null == result) {
			return null;
		}
		return result;
	}

	// 修改
	public void updateTeamItem(TeamItem teamItem) throws Exception {
		updateMappingBeanInCache(teamItem,teamItem.getId());
	}

	// 删除
	public void deleteTeamItem(TeamItem teamItem) throws Exception {
		deleteObjFromDBAndCache(teamItem,teamItem.getId());
	}

	/**
	 * 
	 * @param teamId
	 * @param sysItem
	 * @param unit
	 * @param unitType
	 * @param isGift
	 * @param isBind
	 * @param isDefault
	 * @return newId
	 * @throws Exception
	 */
	public int createOrUpdateTeamItem(int teamId, SysItem sysItem,
			int unit, int unitType, String isGift, String isBind,
			String isDefault,int usedCount) throws Exception {
		int teamItemId=-1;
		TeamItem ti = null;
		ti = getTeamItemByTeamIdAndItemId(teamId, sysItem.getId());
		if (ti != null) {//
			ti.setQuantity(ti.getQuantity() + unit);
			if (sysItem.getTimeForCreate() != 0) {
				ti.setLastBuildTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(System.currentTimeMillis()));
			}
			updateTeamItem(ti);
			teamItemId=ti.getId();
		} else {
			ti = new TeamItem();
			ti.setTeamId(teamId);
			ti.setItemId(sysItem.getId());
			ti.setModifiedDesc(sysItem.getModifiedDesc());
			if (unitType == 2) {// 时间
				ti.setValidTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(System.currentTimeMillis()));
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DAY_OF_YEAR, unit);
				ti.setExpireTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(c.getTime()));
				ti.setTimeLeft(24 * 60 * unit);
				// 时间计算的物品记录剩余秒数
				int seconds = unit * 24 * 60 * 60;
				ti.setLeftSeconds(seconds);
			} else if (unitType == 1) {// 数量
				ti.setQuantity(unit);
				ti.setValidTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(System.currentTimeMillis()));
				ti.setExpireTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(System.currentTimeMillis()));
			} else {
				ti.setValidTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(System.currentTimeMillis()));
				ti.setExpireTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
						.format(System.currentTimeMillis()));
			}
			ti.setCurrency(Constants.RES_PAY);
			ti.setUnitType(unitType);
			ti.setDurable(100);
			ti.setIsBind(isBind);
			ti.setIsDefault(isDefault);
			ti.setIsGift(isGift);
			ti.setIsDeleted(Constants.BOOLEAN_NO);
			ti.setGunProperty1("0,0,1");
			ti.setGunProperty2("0,0,1");
			ti.setGunProperty3("0,0,1");
			ti.setGunProperty4("0,0,1");
			ti.setGunProperty5("0,0,1");
			ti.setGunProperty6(sysItem.getGunProperty6());
			ti.setGunProperty7(sysItem.getGunProperty7());
			ti.setGunProperty8("");
			ti.setLevel(sysItem.getLevel());
			ti.setUsedCount(0);
			ti.setBullet(100);
			ti.setUsedCount(usedCount);
			teamItemId=insertObjInDB(ti);
		}
		return teamItemId;
	}

	public TeamItem getTeamItemByTeamIdAndItemId(int teamId, int itemId)
			throws Exception {
		List<TeamItem> list = getTeamItemList(teamId);
		for (TeamItem item : list) {
			if (item.getItemId() == itemId) {
				return item;
			}
		}
		return null;
	}
}
