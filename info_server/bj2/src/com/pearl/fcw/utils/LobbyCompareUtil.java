package com.pearl.fcw.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerItem;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;

/**
 * 具体业务的比较器
 */
public class LobbyCompareUtil {

	private Map<Integer, WSysItem> wSysItemMap = new HashMap<>();

	public LobbyCompareUtil(Map<Integer, WSysItem> wSysItemMap) {
		this.wSysItemMap = wSysItemMap;
	}

	/**
	 * 系统道具SysItem在商城的排序
	 */
	public static final Comparator<SysItem> SYS_ITEM_COMPARATOR = new Comparator<SysItem>() {
		@Override
		public int compare(SysItem sysItem1, SysItem sysItem2) {
			int result = 0;
			//排序依据1：特殊标记-限时购买
			result = sysItem2.getShoppingType() - sysItem1.getShoppingType();
			if (result != 0) {
				return result;
			}
			//排序依据1：特殊标记-新品
			result = sysItem2.getIsNew() - sysItem1.getIsNew();
			if (result != 0) {
				return result;
			}
			//排序依据1：特殊标记-热卖
			result = sysItem2.getIsPopular() - sysItem1.getIsPopular();
			if (result != 0) {
				return result;
			}
			//排序依据2：稀有度
			result = sysItem2.getRareLevel() - sysItem1.getRareLevel();
			if (result != 0) {
				return result;
			}
			//排序依据3：父子类型
			result = sysItem1.getType() - sysItem2.getType();
			if (result != 0) {
				return result;
			}
			result = sysItem1.getSubType() - sysItem2.getSubType();
			if (result != 0) {
				return result;
			}
			//排序依据4：ID
			result = sysItem2.getId() - sysItem1.getId();
			return result;
		}
	};

	/**
	 * 玩家道具PlayerItem在背包以及合成界面的排序
	 */
	public static final Comparator<PlayerItem> PLAYER_ITEM_COMPARATOR = new Comparator<PlayerItem>() {
		@Override
		public int compare(PlayerItem playerItem1, PlayerItem playerItem2) {
			int result = 0;
			//排序依据1：系统道具在商城的排序规则（排除特殊标签）
			SysItem sysItem1 = playerItem1.getSysItem();
			SysItem sysItem2 = playerItem2.getSysItem();
			if (null != sysItem1 && null != sysItem2) {
				//排序依据1.2：稀有度
				result = sysItem2.getRareLevel() - sysItem1.getRareLevel();
				if (result != 0) {
					return result;
				}
				//排序依据1.3：父子类型
				result = sysItem1.getType() - sysItem2.getType();
				if (result != 0) {
					return result;
				}
				result = sysItem1.getSubType() - sysItem2.getSubType();
				if (result != 0) {
					return result;
				}
				//排序依据1.4：ID
				result = sysItem2.getId() - sysItem1.getId();
			}
			//排序依据2：强化等级
			result = playerItem2.getLevel() - playerItem1.getLevel();
			if (result != 0) {
				return result;
			}
			//排序依据3：ID
			result = playerItem2.getId() - playerItem1.getId();
			return result;
		}
	};

	/**
	 * 系统道具WSysItem在商城的排序
	 */
	public static final Comparator<WSysItem> W_SYS_ITEM_COMPARATOR = new Comparator<WSysItem>() {
		@Override
		public int compare(WSysItem wSysItem1, WSysItem wSysItem2) {
			int result = 0;
			//排序依据1：特殊标记-限时购买
			result = wSysItem2.getShoppingType() - wSysItem1.getShoppingType();
			if (result != 0) {
				return result;
			}
			//排序依据1：特殊标记-新品
			result = wSysItem2.getIsNew() - wSysItem1.getIsNew();
			if (result != 0) {
				return result;
			}
			//排序依据1：特殊标记-热卖
			result = wSysItem2.getIsPopular() - wSysItem1.getIsPopular();
			if (result != 0) {
				return result;
			}
			//排序依据2：稀有度
			result = wSysItem2.getRareLevel() - wSysItem1.getRareLevel();
			if (result != 0) {
				return result;
			}
			//排序依据3：父子类型
			result = wSysItem1.getType() - wSysItem2.getType();
			if (result != 0) {
				return result;
			}
			result = wSysItem1.getSubType() - wSysItem2.getSubType();
			if (result != 0) {
				return result;
			}
			//排序依据4：ID
			result = wSysItem2.getId() - wSysItem1.getId();
			return result;
		}
	};

	/**
	 * 玩家道具代理ProxyWPlayerItem在背包以及合成界面的排序
	 */
	public final Comparator<ProxyWPlayerItem> PROXY_W_PLAYER_ITEM_COMPARATOR = new Comparator<ProxyWPlayerItem>() {
		@Override
		public int compare(ProxyWPlayerItem pwPlayerItem1, ProxyWPlayerItem pwPlayerItem2) {
			int result = 0;
			//排序依据1：系统道具在商城的排序规则（排除特殊标签）
			WSysItem wSysItem1 = null;
			WSysItem wSysItem2 = null;
			try {
				wSysItem1 = wSysItemMap.get(pwPlayerItem1.itemId());
				wSysItem2 = wSysItemMap.get(pwPlayerItem2.itemId());
			} catch (Exception e) {
			}
			if (null != wSysItem1 && null != wSysItem2) {
				//排序依据1.2：稀有度
				result = wSysItem2.getRareLevel() - wSysItem1.getRareLevel();
				if (result != 0) {
					return result;
				}
				//排序依据1.3：父子类型
				result = wSysItem1.getType() - wSysItem2.getType();
				if (result != 0) {
					return result;
				}
				result = wSysItem1.getSubType() - wSysItem2.getSubType();
				if (result != 0) {
					return result;
				}
				//排序依据1.4：ID
				result = wSysItem2.getId() - wSysItem1.getId();
			}
			//排序依据2：强化等级
			result = pwPlayerItem2.level().get() - pwPlayerItem1.level().get();
			if (result != 0) {
				return result;
			}
			//排序依据3：ID
			result = pwPlayerItem2.id().get() - pwPlayerItem1.id().get();
			return result;
		}
	};
}
