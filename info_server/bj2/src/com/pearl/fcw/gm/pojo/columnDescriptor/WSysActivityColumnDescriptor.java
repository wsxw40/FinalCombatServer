package com.pearl.fcw.gm.pojo.columnDescriptor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.pojo.WSysActivity;
import com.pearl.fcw.lobby.pojo.ParamObject;
import com.pearl.fcw.proto.enums.EActivityActionType;
import com.pearl.fcw.proto.enums.EActivityNumberParam;
import com.pearl.fcw.utils.DateUtil;
import com.pearl.fcw.utils.StringUtil;

/**
 * SysActivity表的特殊字段描述
 */
public class WSysActivityColumnDescriptor extends ColumnDescriptor<WSysActivity> {
    @Override
    public WSysActivity get(WSysActivity m) {
        if (null == m) {
            return m;
        }
        //活动未逻辑删除，正在激活中，并且活动类型不是TOP_PLAYER_ACTIVITY，name字段内容变更
        if ("Y".equals(m.getTheSwitch()) && "N".equals(m.getIsDeleted()) && m.getAction() != EActivityActionType.TOP_PLAYER_ACTIVITY.getNumber()) {
            Object[] endTimeValue = { DateUtil.formatToFlag(m.getEndTime(), "yyyy-MM-dd HH"), m.getValue() };
            Object[] startEndTime = { DateUtil.formatToFlag(m.getStartTime(), "yyyy-MM-dd HH"), DateUtil.formatToFlag(m.getEndTime(), "yyyy-MM-dd HH") };
            Object[] startEndValue = { DateUtil.formatToFlag(m.getStartTime(), "yyyy-MM-dd HH"), DateUtil.formatToFlag(m.getEndTime(), "yyyy-MM-dd HH"), m.getValue() };
            Object[] startEndTargetNum = { DateUtil.formatToFlag(m.getStartTime(), "yyyy-MM-dd HH"), DateUtil.formatToFlag(m.getEndTime(), "yyyy-MM-dd HH"), m.getTargetNum() };
            Object[] startEndValueTargetNum = { DateUtil.formatToFlag(m.getStartTime(), "yyyy-MM-dd HH"), DateUtil.formatToFlag(m.getEndTime(), "yyyy-MM-dd HH"), m.getValue(), m.getTargetNum() };
            String name = m.getName();

            switch (EActivityActionType.forNumber(m.getAction())) {
            case LEVEL_ACTIVITY:
                name = messageFormatI18N(m.getAction().toString(), "<AC", 2, endTimeValue);
                break;
            case LOGIN_ACTIVITY:
            case RANDOM_ACTIVITY:
            case DISCOUNT_ACTIVITY:
            case IGNORE_DEAD:
            case IGNORE_LOSE:
            case OPEN_LUCKYPACKAGE:
                name = messageFormatI18N(m.getAction().toString(), "<AC", 2, startEndTime);
                break;
            case ONLINE_ACTIVITY:
            case TASK_ACTIVITY:
            case LEVEL_FIRST_LOGIN:
                name = messageFormatI18N(m.getAction().toString(), "<AC", 3, startEndValue);
                break;
            case ACHIEVEMENT_ACTIVITY:
                name = m.getValue().toString();
                break;
            case KILL_ACTIVITY:
            case BOSS_KILL_ACTIVITY:
            case KILL_BOSS_ACTIVITY:
            case KILL_BIOCHEMICAL:
            case PAY_FC:
            case STRENGTH_TO_RANK:
            case ENHANCE_MAX_STRENGTH_LEVEL:
                name = messageFormatI18N(m.getAction().toString(), "<AC", 3, startEndTargetNum);
                break;
            case HOUR2HOUR_ACTIVITY:
            case STRENGTH_NON_LOSE:
            case STRENGTH_ADD:
            case TEAM_BATTLE_ADD:
                if (m.getValue() == 0 && m.getTargetNum() == 24) {
                    name = messageFormatI18N("" + m.getAction() * 10, "<AC", 2, startEndTime);
                } else {
                    name = messageFormatI18N(m.getAction().toString(), "<AC", 4, startEndValueTargetNum);
                }
                break;
            case TOP_PLAYER_ACTIVITY:
                name = messageFormatI18N(m.getAction().toString(), "<AC", 4, startEndValueTargetNum);
                break;
            case CHARGE_FC:
                //（配置开始时间-配置结束时间）每天第1次登陆游戏 至 当日23:59，充值“配置钱数”可获得以下奖励！
                if ("Y".equals(m.getBackUp())) {
                    name = messageFormatI18N("" + m.getAction() * 10, "<AC", 3, startEndTargetNum);
                } else {
                    name = messageFormatI18N(m.getAction().toString(), "<AC", 3, startEndTargetNum);
                }
                break;
            case STRENGTH_MUST:
                name = messageFormatI18N(m.getAction().toString(), "<AC", 3, startEndTime);
                break;
            case KILL_BOSS2_ACTIVITY:
                name = messageFormatI18N(m.getAction().toString(), "<AC", 4, startEndTargetNum);
                break;
            case TEAM_COMBAT_FINISH: //28 需要占用，任务内 每日与非每日需要有两段文字
                name = messageFormatI18N((m.getAction().toString() + ("Y".equals(m.getBackUp()) ? 1 : 0)), "<AC", 4, DateUtil.formatToFlag(m.getStartTime(), "yyyy-MM-dd HH"),
                        DateUtil.formatToFlag(m.getEndTime(), "yyyy-MM-dd HH"), m.getValue(), "SN" + m.getItems() + "^0>");
                break;
            default:
                break;
            }
            m.setName(name);
        }
        //items
        if (!StringUtil.isEmpty(m.getItems())) {
            m.getItemsList().addAll(Stream.of(m.getItems().split(",")).map(Integer::parseInt).collect(Collectors.toList()));
        }
        //numberParam//needAward有报酬
        switch (EActivityActionType.forNumber(m.getAction())) {
        case LEVEL_ACTIVITY:
        case LOGIN_ACTIVITY:
        case RANDOM_ACTIVITY:
        case ONLINE_ACTIVITY:
        case DISCOUNT_ACTIVITY:
        case HOUR2HOUR_ACTIVITY:
        case TOP_PLAYER_ACTIVITY:
        case IGNORE_DEAD:
        case IGNORE_LOSE:
        case LEVEL_FIRST_LOGIN:
        case STRENGTH_NON_LOSE:
        case STRENGTH_ADD:
        case OPEN_LUCKYPACKAGE:
        case STRENGTH_MUST:
        case TEAM_BATTLE_ADD:
        case ENHANCE_MAX_STRENGTH_LEVEL:
            m.getNumberParamMap().put(EActivityNumberParam.needAward.toString(), new ParamObject<Number>(0));
            break;
        default:
            m.getNumberParamMap().put(EActivityNumberParam.needAward.toString(), new ParamObject<Number>(1));
            break;
        }
        return m;
    }

    @Override
    public void set(WSysActivity m) {
    }

    /**
     * 转Lua格式字符串
     * @param regx
     * @param pref
     * @param num
     * @param args
     * @return
     */
    private String messageFormatI18N(String regx, String pref, int num, Object... args) {
        regx = pref + regx + "^" + num + "^";
        for (Object obj : args) {
            regx += "\\\"" + obj + "\\\"^";
        }
        return regx + ">";
    }
}
