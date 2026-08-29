package com.pearl.fcw.lobby.pojo.columnDescriptor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.lobby.pojo.ParamObject;
import com.pearl.fcw.lobby.pojo.WPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWCharacterData;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.proto.enums.ECharacterDataNumberParam;
import com.pearl.fcw.proto.enums.EPlayerNumberParam;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.utils.GrowthMissionConstants;

/**
 * Player表的特殊字段描述
 */
public class WPlayerColumnDescriptor extends ColumnDescriptor<WPlayer> {
    @Override
    public WPlayer get(WPlayer m) {
        if (null == m) {
            return m;
        }
        //tutorial
        if (StringUtil.isEmpty(m.getTutorial())) {
            m.setTutorial(GrowthMissionConstants.DEFAULTTUTORIAL);
        }
        List<ParamObject<Integer>> tutorialList = Stream.of(m.getTutorial().split("")).map(Integer::parseInt).map(p -> new ParamObject<Integer>(p)).collect(Collectors.toList());
        while (tutorialList.size() > GrowthMissionConstants.DEFAULTTUTORIAL.length()) {
            tutorialList.remove(tutorialList.size() - 1);
        }
        while (tutorialList.size() < GrowthMissionConstants.DEFAULTTUTORIAL.length()) {
            tutorialList.add(new ParamObject<Integer>(0));
        }
        for (int i = 0; i < tutorialList.size(); i++) {
            m.getTutorialMap().put(i + "", tutorialList.get(i));
        }


//        if (!StringUtil.isEmpty(m.getProfile())) {
//            m.getProfileMap().putAll(Stream.of(m.getProfile().split(",")).map(p -> p.split("\\|")).collect(Collectors.toMap(p -> p[0], p -> new ParamObject(p[1]))));
//        }
        return m;
    }

    @Override
    public void set(WPlayer m) {
        //tutorial
        if (m.getTutorialMap().isEmpty()) {
            m.setTutorial(GrowthMissionConstants.DEFAULTTUTORIAL);
        } else {
            while (m.getTutorialMap().size() > GrowthMissionConstants.DEFAULTTUTORIAL.length()) {
                m.getTutorialMap().remove((m.getTutorialMap().size() - 1) + "");
            }
            while (m.getTutorialMap().size() < GrowthMissionConstants.DEFAULTTUTORIAL.length()) {
                m.getTutorialMap().put(m.getTutorialMap().size() + "", new ParamObject<Integer>(0));
            }
            m.setTutorial(m.getTutorialMap().values().stream().map(p -> p.getValue().toString()).collect(Collectors.joining()));
        }
//        m.setProfile(String.join(",", m.getProfileMap().keySet().stream().map(k -> k + "|" + m.getProfileMap().get(k).getValue()).collect(Collectors.toList())));
    }

	/**
	 * 玩家角色更换装备时会导致一些不记录在数据库但是保留在缓存的中间数值变化。该方法用于刷新缓存的中间值
	 * @param pwPlayer
	 * @param pwCharacterDatas
	 */
	public static void refreshNumberParamMap(ProxyWPlayer pwPlayer, Collection<ProxyWCharacterData> pwCharacterDatas) {
		//战斗力FightNum(所有可用的玩家角色的战斗力叠加)
		int fightNum = 0;
		for (ProxyWCharacterData pwCharacterData : pwCharacterDatas) {
			fightNum += pwCharacterData.numberParamMap().get(ECharacterDataNumberParam.characterFightNum.toString()).get().getValue().intValue();
		}
		pwPlayer.numberParamMap().get(EPlayerNumberParam.playerFightNum.toString()).set(new ParamObject<>(fightNum));
	}
}
