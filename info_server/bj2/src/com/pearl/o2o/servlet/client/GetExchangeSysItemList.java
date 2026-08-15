package com.pearl.o2o.servlet.client;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.service.WSysItemPriceService;
import com.pearl.fcw.gm.service.WSysItemService;
import com.pearl.fcw.lobby.service.ShopService;
import com.pearl.fcw.proto.ProtoConverter;
import com.pearl.fcw.utils.Smarty4jConverter;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.ExceptionMessage;

public class GetExchangeSysItemList extends BaseClientServlet implements Servletable {

	private static final long serialVersionUID = -8847865216405794375L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final String[] paramNames = { "pid", "page", "isBlueprint", "itemType", "itemSubType" };

	@Resource
	private WSysItemService wSysItemService;
	@Resource
	private WSysItemPriceService wSysItemPriceService;
	@Resource
	private ProtoConverter protoConverter;
	@Resource
	private ShopService shopService;
	@Resource
	private GetExchangeSysItemList c_shop_exchange_list;

	@Override
	protected String innerService(String... args) {
		try {
			return c_shop_exchange_list.rpc(args);
		} catch (BaseException e1) {
			logger.error("c_shop_exchange_list has error : ", e1);
			return Smarty4jConverter.error(e1.getMessage());
		} catch (Exception e) {
			logger.error("c_shop_exchange_list has error : ", e);
		}
		return Smarty4jConverter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		//		int playerId = StringUtil.toInt(args[0]);
		//		int page = StringUtil.toInt(args[1]);
		//		List<Integer> itemTypeList = new ArrayList<>();
		//		if (!com.pearl.fcw.utils.StringUtil.isEmpty(args[2])) {
		//			itemTypeList.addAll(Stream.of(args[2].split(",")).map(Integer::parseInt).collect(Collectors.toList()));
		//		}
		//		try {
		//			Player player = getService.getSimplePlayerById(playerId);
		//			//start加上对玩家的判断，判断是否完成任务跟达到等级
		//			//			if (player.getRank()<11||player.getTutorial().charAt(6)!='2')
		//			//				return Converter.error(CommonMsg.FUNC_SIGN_ERROR);
		//			//end
		//			List<SysItem> list = getService.getExchangeSysItemList().stream().filter(p -> {
		//				return !itemTypeList.isEmpty() && itemTypeList.contains(p.getType());
		//			}).sorted(LobbyCompareUtil.SYS_ITEM_COMPARATOR).collect(Collectors.toList());
		//			//			Collections.sort(list,new SysItemComparator());
		//			int chipNum = getService.getMedolNumByPlayerId(playerId);
		//			int reviceCoinNum = getService.getPlayerItemsTotalQuantityByIid(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.SPECIAL_ITEM_IIDS.RELIVE_COIN.getValue());
		//			//20160401zlm start
		//			//因为碎片要换，所以不能直接用id，这里应该是iid跟type来确定
		//			int aChipNum = getService.getPlayerItemsTotalQuantityByIid(playerId, Constants.DEFAULT_MATERIAL_TYPE, Constants.MATERIAL_IIDS.A_Chip.getValue());
		//			int bChipNum = getService.getPlayerItemsTotalQuantityByIid(playerId, Constants.DEFAULT_MATERIAL_TYPE, Constants.MATERIAL_IIDS.B_Chip.getValue());
		//			int cChipNum = getService.getPlayerItemsTotalQuantityByIid(playerId, Constants.DEFAULT_MATERIAL_TYPE, Constants.MATERIAL_IIDS.C_Chip.getValue());
		//			//20160401zlm end
		//			int pageSize = Constants.DEFAULT_A_PAGE_SIZE;
		//			int pages = CommonUtil.getListPages(list, pageSize);
		//			if (pages != 0 && page > pages) {
		//				logger.warn("Error in page bigger than pages");
		//				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		//			}
		//			int endIndex = page * pageSize;
		//			int startIndex = (page - 1) * pageSize;
		//			endIndex = endIndex > list.size() ? list.size() : endIndex;
		//			list = list.subList(startIndex, endIndex);
		//			if (pages == 0) {
		//				pages = 1;
		//			}
		//			return Converter.exchangeSysItemList(page, pages, list, player.getRank(), chipNum, reviceCoinNum, aChipNum, bChipNum, cChipNum);
		//		} catch (Exception e) {
		//			logger.warn("Error in GetExchangeSysItem: ", e);
		//			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		//		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	public String rpc(String... args) throws Exception {
		int playerId = Integer.parseInt(args[0]);
		int page = Integer.parseInt(args[1]);
		boolean isBlueprint = false;
		int itemType = 0;
		int itemSubType = 0;
		try {
			isBlueprint = args[2].equals("1") ? true : false;
			itemType = Integer.parseInt(args[3]);
			itemSubType = Integer.parseInt(args[4]);
		} catch (Exception e) {
		}
		String str = shopService.getExchangeList(playerId, page, isBlueprint, itemType, itemSubType);
		return str;
	}

	@Override
	public String getLockedKey(String... args) {
		return args[0];
	}
}
