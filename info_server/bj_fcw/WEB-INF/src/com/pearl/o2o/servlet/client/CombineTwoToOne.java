package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 合成系统--合成（精英系统）
 * 
 * @author OuYangGuang
 * 
 */
public class CombineTwoToOne extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory
			.getLogger(CombineTwoToOne.class.getName());
	private String[] paramNames = { "pid", "mainItemId", "toItemId", "isFree" };

	public CombineTwoToOne() {
		super();
	}

	/* 
	 * (non-Javadoc)
	 * 
	 * @see com.pearl.o2o.servlet.client.BaseClientServlet#getLockKey(java.lang.String[])
	 */
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}

	@Override
	protected String innerService(String... args) {
		int playerId = Integer.parseInt(args[0]);
		int mainItemId = Integer.parseInt(args[1]);
		String toItemId = args[2];
		int isFree = Integer.parseInt(args[3]); // 是否勾选每日一次
		
		try {
			PlayerItem pItem1 = getService.getPlayerItemById(playerId,
					mainItemId);
			PlayerItem pItem2 = null;
			
			// 检测是否输过二级密码
			if (!checkEnterSPW(playerId)) {
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			
			if (pItem1 == null || pItem1.getSysItem() == null)
				throw new BaseException(ExceptionMessage.ITEM_NOT_EXIST);

			if (pItem1.getGst_level() >= Constants.MAX_GST_LEVEL)
				throw new BaseException(ExceptionMessage.ITEM_START_MAX_LEVEL);

			String[] pItems = toItemId.split(":");
			HashMap<Integer, Integer> repeat = new HashMap<Integer, Integer>();	//同品质武器加成
			repeat.put(pItem1.getSysItem().getId(), 1);
			List<PlayerItem> items = new ArrayList<PlayerItem>();
			HashMap<Integer,Integer> h = new HashMap<Integer, Integer>();
			for(String p : pItems){
				h.put(Integer.parseInt(p), 0);
			}
			h.put(mainItemId, 0);
			if(h.size()!=(pItems.length+1))
			{
				throw new BaseException(ExceptionMessage.ITEM_NOT_EXIST);
			}
			
			for (String itemIdStr : pItems) {
				int itemId = Integer.parseInt(itemIdStr);
				pItem2 = getService.getPlayerItemById(playerId, itemId);
				if (pItem2 == null || pItem2.getSysItem() == null)
					throw new BaseException(ExceptionMessage.ITEM_NOT_EXIST);

				// 主武器,服饰,配饰限定
				if ((pItem2.getSysItem().getType() == Constants.DEFAULT_WEAPON_TYPE
						&& pItem2.getSysItem().getSubType() == Constants.NUM_ONE)||
						(pItem2.getSysItem().getType() == Constants.DEFAULT_COSTUME_TYPE
								&& pItem2.getSysItem().getSubType() == Constants.NUM_ONE)||
								(pItem2.getSysItem().getType() == Constants.DEFAULT_PART_TYPE
										&& (pItem2.getSysItem().getSubType() == Constants.NUM_ONE || pItem2.getSysItem().getSubType() == Constants.NUM_TWO))) {
					
						//类型不能混合升星
						if(((int)pItem1.getSysItem().getType())!=((int)pItem2.getSysItem().getType()))
							throw new BaseException(ExceptionMessage.ERROR_MESSAGE_ALL);
						
					items.add(pItem2);
					if (repeat.containsKey(pItem2.getSysItem().getId())) {
						repeat.put(pItem1.getSysItem().getId(),repeat.get(pItem1.getSysItem().getId())+ 1);
					}
				}
				
			}
			
			if(items!=null && items.size()>0)
				updateService.combinePlayerItem(playerId, pItem1, items, repeat.get(pItem1.getSysItem().getId()),
					isFree == 1 ? true : false);

			PlayerItem item = getService
					.getPlayerItemById(playerId, mainItemId);

			//System.out.println("Final GST_Level:" + item.getGst_level());
			return Converter.combineTwoToOne(item);
		} catch (Exception e) {
			log.warn("Exception in Convert: " + e.getMessage());
			return Converter.error(e.getMessage());
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	/**
	 * 精英（武器星级）成就
	 * 
	 * 
	 */
//	private void updateCombineTowToOneAchievement() {
//
//	}
}
