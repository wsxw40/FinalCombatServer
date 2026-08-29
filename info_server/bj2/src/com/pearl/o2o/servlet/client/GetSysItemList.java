package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.gm.pojo.WPayment;
import com.pearl.fcw.gm.pojo.WSysItem;
import com.pearl.fcw.gm.service.WPaymentService;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.proto.enums.EItemShoppingType;
import com.pearl.fcw.proto.enums.EItemSubType;
import com.pearl.fcw.proto.enums.EItemType;
import com.pearl.fcw.proto.enums.EPayType;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.LobbyCompareUtil;
import com.pearl.o2o.dao.impl.nonjoin.SysItemDao;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetSysItemList extends BaseClientServlet {
	private static final long serialVersionUID = -4205634889709446795L;
	private static Logger log = LoggerFactory.getLogger(GetSysItemList.class.getName());
	private static final String[] paramNames = { "uid", "pid", "t", "cid", "p", "st", "pt" };
	@Resource
	private WPaymentService wPaymentService;
	@Resource
	private WSysItemService wSysItemService;

	@Override
	protected String innerService(String... args) {
		try {
			int userId = StringUtil.toInt(args[0]);
			int playerId = StringUtil.toInt(args[1]);
			int type = StringUtil.toInt(args[2]);
			int characterId = StringUtil.toInt(args[3]);
			int page = StringUtil.toInt(args[4]);
			int subType = 0;//子类型，与客户端的下拉框对应
			int payType = 0;//付款方式：0，全部  1，fc点 2，c币
			if (null != args[5]) {
				subType = StringUtil.toInt(args[5]);
			}
			if (null != args[6]) {
				payType = StringUtil.toInt(args[6]);
			}
			if (subType < 0) {
				subType = 0;
			}
			if (payType < 0) {
				payType = 0;
			}
			if (type < Constants.NUM_ONE || type > Constants.TYPE_NUM || page < Constants.NUM_ONE || type == Constants.DEFAULT_ZYZDZ_TYPE) {//老商城中，不允许显示资源争夺战的物品
				log.warn("type or subtype or page not correct.");
			} else if (type != Constants.DEFAULT_WEAPON_TYPE && type != Constants.DEFAULT_COSTUME_TYPE && type != Constants.DEFAULT_PART_TYPE) {
				characterId = 0;
			}
			Player player = getService.getSimplePlayerById(playerId);

			List<SysItem> sysItemList = new ArrayList<SysItem>();
			String result = "";
			int pages = 0;
			/*String objKey=CacheUtil.oShop(type, characterId);
			ArrayList<SysItem>[] array = mcc.get(objKey,Constants.CACHE_TIMEOUT);
			if (array == null) {
				array = getService.getSysItem(type, characterId);
			}
			if (array != null) {
				pages = array.length;
			}
			if (pages >= page&&page!=0) {
				sysItemList = array[page - 1];
			}*/
			String key = SysItemDao.getClassifyKey(characterId, type, subType, payType);
			sysItemList = getService.getClassifySysItemMap().get(key);
			sysItemList = sysItemList.stream().filter(sysItem -> {
				switch (EItemShoppingType.forNumber(sysItem.getShoppingType())) {
				case NORAML_SALE:
					return true;
				case FLASH_SALE:
					Date now = new Date();
					if (DateUtil.after(now, sysItem.getShoppingStartTime(), Calendar.MILLISECOND, 1) && DateUtil.before(now, sysItem.getShoppingEndTime(), Calendar.MILLISECOND, 1)) {
						return true;
					} else {
						return false;
					}
				default:
					return false;
				}
			}).collect(Collectors.toList());
			int pageSize = Constants.DEFAULT_C_PAGE_SIZE;
			if (type == Constants.DEFAULT_PART_TYPE) {
				pageSize = Constants.DEFAULT_B_PAGE_SIZE;
			} else if (type > 3) {
				pageSize = Constants.DEFAULT_A_PAGE_SIZE;
			}

			pages = CommonUtil.getListPages(sysItemList, pageSize);

			if (pages == 0) {
				pages = 1;
			}

			//page [1,pages]
			if (page < 1 && page > pages) {
				log.warn(String.format("GetSysItem page out range!uid:%s,pid:%s,t:%s,cid:%s,p:%s,st:%s,pt:%s,pages:%s", userId, playerId, type, characterId, page, subType, payType, pages));
				page = pages;
			}

			int endIndex = page * pageSize;
			int startIndex = (page - 1) * pageSize;

			sysItemList = sysItemList.subList(startIndex, endIndex > sysItemList.size() ? sysItemList.size() : endIndex);
			//FCW 道具排序新规则
			sysItemList.sort(LobbyCompareUtil.SYS_ITEM_COMPARATOR);
			System.out.println(sysItemList.stream().map(p -> p.getId()).collect(Collectors.toList()));

			//FCW
			int itemType = type;
			if (itemType != EItemType.WEAPON.getNumber() && itemType != EItemType.COSTUME.getNumber() && itemType != EItemType.PART.getNumber()) {
				characterId = 0;
			}
			int itemSubType = subType;
			int itemPayType = payType;
			int sysCharacterId = characterId;
			Date now = new Date();
			List<WPayment> wPaymentList = wPaymentService.findList(null);
			List<WSysItem> wSysItemList = wSysItemService
					.findList(null)
					.stream()
					.filter(wSysItem -> {
						//区分普通销售和限时销售
						switch (EItemShoppingType.forNumber(wSysItem.getShoppingType())) {
						case NORAML_SALE:
							break;
						case FLASH_SALE:
							if (DateUtil.after(now, wSysItem.getShoppingStartTime(), Calendar.MILLISECOND, 1) && DateUtil.before(now, wSysItem.getShoppingEndTime(), Calendar.MILLISECOND, 1)) {
								break;
							} else {
								return false;
							}
						default:
							return false;
						}
						//支付类型
						if (itemPayType != EPayType.PAY_NONE.getNumber()) {
							if (wPaymentList.stream().filter(wPayment -> wPayment.getPayType() == itemPayType && wPayment.getItemId() == wSysItem.getId()).count() == 0) {
								return false;
							}
						}
						//角色类型
						if (sysCharacterId != 0 && !wSysItem.getCharacterIdList().isEmpty()) {
							if (!wSysItem.getCharacterIdList().contains(sysCharacterId)) {
								return false;
							}
						}
						//类型
						if (itemType != EItemType.ITEMTYPE_NONE.getNumber()) {
							if (itemType == EItemType.POPULAR.getNumber()) {//热卖商品,该类型并非真实类型
								if (wSysItem.getType() == EItemType.WEAPON.getNumber() || wSysItem.getType() == EItemType.COSTUME.getNumber() || wSysItem.getType() == EItemType.PART.getNumber()
										|| wSysItem.getIsPopular() == 0) {
									return false;
								}
							} else if (itemType != wSysItem.getType()) {
								return false;
							}
							if (itemSubType != EItemSubType.ITEMSUBTYPE_NONE.getNumber() && itemSubType != wSysItem.getSubType()) {//子类型
								return false;
							}
						}
						return true;
					}).sorted(LobbyCompareUtil.W_SYS_ITEM_COMPARATOR).collect(Collectors.toList());
			int count = wSysItemList.size();
			pages = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			Map<Integer, SysItem> sysItemMap = getService.getSysItemDao().getAllSysItemMap();
			sysItemList = wSysItemList.stream().skip((page - 1) * pageSize).limit(pageSize).map(p -> {
				return sysItemMap.get(p.getId());
			}).collect(Collectors.toList());

			result = Converter.sysItemList(page, pages, sysItemList, player.getRank());
			return result;
		} catch (Exception e) {
			log.warn("Error in GetSysItem: ", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
