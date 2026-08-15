package com.pearl.fcw.lobby.pojo.columnDescriptor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.lobby.pojo.ParamObject;
import com.pearl.fcw.lobby.pojo.WPlayerAchievement;
import com.pearl.fcw.utils.StringUtil;

/**
 * PlayerAchievement表的特殊字段描述
 */
public class WPlayerAchievementColumnDescriptor extends ColumnDescriptor<WPlayerAchievement> {
    @Override
    public WPlayerAchievement get(WPlayerAchievement m) {
        if (null == m) {
            return m;
        }
        if (!StringUtil.isEmpty(m.getSysAchievementIds())) {
            m.getSysAchievementIdsList().addAll(Stream.of(m.getSysAchievementIds().split(",")).map(Integer::parseInt).map(p -> new ParamObject<Integer>(p)).collect(Collectors.toList()));
        }
        return m;
    }

    @Override
    public void set(WPlayerAchievement m) {
        m.setSysAchievementIds(m.getSysAchievementIdsList().stream().map(p -> p.getValue().toString()).collect(Collectors.joining(",")));
    }
}
