package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.Character;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysCharacter;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.BiochemicalConstants;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetCharacterList extends BaseClientServlet {
	private static final long serialVersionUID = -7782733693520784343L;
	private static Logger log = LoggerFactory.getLogger(GetCharacterList.class.getName());
	private static final String[] paramNames = {"pid"};
	
	protected String innerService(String... args) {
		String result;
		try{
			int playerId=StringUtil.toInt(args[0]);
			List<Character> enableList=new ArrayList<Character>();
			List<SysCharacter> characterList=getService.getDefaultSysCharacterList();
			Player player=getService.getPlayerById(playerId);
			updateService.checkAndUpdateCharacters(player);//保证角色解锁
			String[] characterIds=player.getCharactersIds();
			int openSize=0;
			for(SysCharacter sc:characterList){
				Character ch=new Character();
			
				ch.setSysCharacter(sc);
				String resource="";
				List<PlayerItem> list=getService.getCostumePackList(playerId, 1, sc.getId());
				for(PlayerItem pi:list){
					SysItem si=getService.getSysItemByItemId(pi.getItemId());
					if(si.getType()==Constants.DEFAULT_COSTUME_TYPE){
						resource=si.getName();
						break;
					}
				}
				ch.setHeadResource(resource);
				if(Constants.BOOLEAN_YES.equals(sc.getIsDefault())){
					for(String str:characterIds){
						if(StringUtil.toInt(str)==sc.getId()){
							ch.setIsOpen(1);
							openSize++;
							break;
						}
					}
					enableList.add(ch);
				}
				if(sc.getId()==2){ch.setDescription("完成“等级达到lv6”成就可解锁职业");}
				if(sc.getId()==5){ch.setDescription("完成“等级达到lv3”成就可解锁职业");}
			}
			//检测玩家所选参战名单
			getService.checkSelectCharacterIds(player);
			
			//生化人参战名单:获取已买到的生化人buff item
			List<PlayerItem> allItemList=getService.getPlayerItemByItemIid(playerId, Constants.DEFAULT_ITEM_TYPE, Constants.NUM_ONE);
			
			List<PlayerItem> allbioBuffList=new ArrayList<PlayerItem>();
			HashSet<Integer> bioSet=new HashSet<Integer>();
			for(PlayerItem pi: allItemList){
				
				if(pi.getSysItem().getIBuffId()==BiochemicalConstants.ordinaryBuffId&&
						pi.getTimeLeft()>0){
				
					bioSet.add(pi.getSysItem().getId());
					allbioBuffList.add(pi);
				}
			}
			
		
			//得到当前起到效果的生化人buff (每次只能有一个生化buff起作用)
			List<PlayerItem> allBuffList=player.getBuffList();
		    int selectedBioBuff=0;
			for(PlayerItem pi: allBuffList){
				if(pi.getSysItem().getIBuffId()==BiochemicalConstants.ordinaryBuffId){
					selectedBioBuff=pi.getSysItem().getId();
					break;
				}
			}
			//根据配置文件得到所有的生化buff 的itemID
			String[] bioList=StringUtil.getStringArrayParam(ConfigurationUtil.BIO_LIST, new String[]{"5302"});
			int[] bioIntegerList=new int[bioList.length];
			for(int i=0;i<bioList.length;i++){
				if(bioList[i].matches("\\d+$")){
					bioIntegerList[i]=Integer.parseInt(bioList[i]);
				}
			}
			
			//找到每种生化角色的buff中剩余时间最多的
			int[] bioLeftSeconds=new int[bioIntegerList.length];
			for(PlayerItem pi: allbioBuffList){
				for(int i=0;i<bioIntegerList.length;i++){
					if(pi.getSysItem().getId()==bioIntegerList[i]){
						if(pi.getTimeLeft()*60>bioLeftSeconds[i]){
							bioLeftSeconds[i]=pi.getTimeLeft()*60;
							break;
						}
					}
				}
				
			}
			
			List<Character> bioListAndState=new ArrayList<Character>();
			List<SysItem> bioBuffItemList=new ArrayList<SysItem>();
			for(int i=0;i<bioIntegerList.length;i++){
				int flag=0;
				if(bioSet.contains(bioIntegerList[i])){
					flag=1;
				}
				if(selectedBioBuff==bioIntegerList[i]){
					flag=2;
				}
				SysItem bioItem=getService.getSysItemByItemId(bioIntegerList[i]);
				
				getService.joinPayment(bioItem);
				bioBuffItemList.add(bioItem);
				
				Character bioCharacter=new Character();
				bioCharacter.setSysCharacter(getService.getSysCharacter(Integer.parseInt(bioItem.getIValue())));
				bioCharacter.setStateInCharList(flag);
				bioCharacter.setLeftSeconds(bioLeftSeconds[i]);
				bioListAndState.add(bioCharacter);
			}
			
			
			
			result=Converter.playerCharacterList(enableList,player.getSelectedCharacters(),player.getCharacterSize(),openSize,bioListAndState,bioBuffItemList);
			return result;
		}
		catch (Exception e) {
			log.warn("Exception happen in GetCharacterList",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
