package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetSysItemListZYZDZ extends BaseClientServlet {
	private static final long serialVersionUID = -3717927346602527483L;
	private static Logger log = LoggerFactory
			.getLogger(GetSysItemListZYZDZ.class.getName());
	private static final String[] paramNames = { "uid", "pid", "cid", "p",
			"st", "pt", "belong" };

	protected String innerService(String... args) {
		try {
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int type = Constants.DEFAULT_ZYZDZ_TYPE;// 指定是资源争夺战的物品
			int characterId = 0;
			int page = StringUtil.toInt(args[3]);
			int subType = 0;// 子类型，与客户端的下拉框对应
			int payType = 0;// 付款方式：0，全部 1，fc点 2，c币 ，5黑曜石
			int belong = 0;// 页面上的tag 0代表资源争夺战个人物品，1代表资源争夺战团队物品

			/** 验证初始化参数 */
			{
				subType = StringUtil.toInt(args[4]);
				payType = StringUtil.toInt(args[5]);
				belong = StringUtil.toInt(args[6]);
				if (subType < 0) {
					subType = 0;
				}
				if (payType < 0) {
					payType = 0;
				}
				if (belong < 0 || belong > 1) {
					belong = 1;
				}
			}

			Player player = getService.getSimplePlayerById(playerId);
			List<SysItem> sysItemList = new ArrayList<SysItem>();
			String result = "";
			int pages = 0;

			/** 获得返回列表 */
			{
				int[] subTypes = getSubTypesBySubTypeAndSubTag(subType, belong);

				if (subTypes == null)
					subTypes = Constants.TeamSpaceConstants.SHOP_TEAM_SUPTYPES;

				sysItemList = getItemBySubtypes(characterId, type, payType,
						subTypes);
			}

			
			/**截取返回列表长度*/
			{
				int pageSize = Constants.DEFAULT_A_PAGE_SIZE;
				pages = CommonUtil.getListPages(sysItemList, pageSize);
				if (pages == 0) {
					pages = 1;
				}
	
				if (page < 1 || page > pages) {
					log.warn(String.format("GetSysItem page out range!uid:%s,pid:%s,t:%s,cid:%s,p:%s,st:%s,pt:%s,pages:%s",userId, playerId, type, characterId,page, subType, payType, pages));
					page = pages;
				}
	
				int endIndex = page * pageSize;
				int startIndex = (page - 1) * pageSize;
	
				sysItemList = sysItemList.subList(startIndex,
						endIndex > sysItemList.size() ? sysItemList.size()
								: endIndex);
			}
			result = Converter.sysItemList(page, pages, sysItemList, player
					.getRank());
			return result;
		} catch (Exception e) {
			log.warn("Error in GetSysItem: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	/**
	 * 按照subtypes取得资源争夺战的物品物品
	 * 
	 * @param characterId
	 * @param type
	 * @param subType
	 * @param payType
	 * @param subTypes
	 * @return
	 * @throws Exception
	 */
	private List<SysItem> getItemBySubtypes(int characterId, int type,
			int payType, int[] subTypes) throws Exception {

		List<SysItem> resultList = new ArrayList<SysItem>();

		for (int subType : subTypes) {
			String key = SysItemDao.getClassifyKey(characterId, type, subType,
					payType);
			List<SysItem> sysItemList = getService.getClassifySysItemMap().get(
					key);
			resultList.addAll(sysItemList);
		}

		return resultList;

	}

	private int[] getSubTypesBySubTypeAndSubTag(int subtype, int belong) {
		if (subtype != 0) {// 客户端指定了subtype
			return new int[] { subtype };
		} else {
			if (belong == 0) {
				return Constants.TeamSpaceConstants.SHOP_PERSONAL_SUPTYPES;
			} else if (belong == 1) {
				return Constants.TeamSpaceConstants.SHOP_TEAM_SUPTYPES;
			} else {
				return null;
			}
		}

	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
