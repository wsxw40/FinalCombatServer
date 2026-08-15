package com.pearl.fcw.lobby.pojo.columnDescriptor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.lobby.pojo.TeamLevelConfigPoints;
import com.pearl.fcw.lobby.pojo.WTeamLevel;
import com.pearl.fcw.utils.StringUtil;

/**
 * TeamLevel表的特殊字段描述
 */
public class WTeamLevelColumnDescriptor extends ColumnDescriptor<WTeamLevel> {
    @Override
    public WTeamLevel get(WTeamLevel m) {
        if (null == m) {
            return m;
        }
        if (!StringUtil.isEmpty(m.getConfigPoints())) {
            m.getConfigPointsList().addAll(Stream.of(m.getConfigPoints().split(";")).map(p -> {
                String[] arr = p.split(",");
                TeamLevelConfigPoints cp = new TeamLevelConfigPoints();
                cp.setSysItemId(Integer.parseInt(arr[0]));
                cp.setR1(Float.parseFloat(arr[1]));
                cp.setR2(Float.parseFloat(arr[2]));
                cp.setR3(Float.parseFloat(arr[3]));
                cp.setR4(Float.parseFloat(arr[4]));
                cp.setX(Float.parseFloat(arr[5]));
                cp.setY(Float.parseFloat(arr[6]));
                cp.setZ(Float.parseFloat(arr[7]));
                return cp;
            }).collect(Collectors.toList()));
        }
        return m;
    }

    @Override
    public void set(WTeamLevel m) {
        m.setConfigPoints(m
                .getConfigPointsList()
                .stream()
                .map(cp -> {
                    return String.join(",", cp.getSysItemId().toString(), cp.getR1().toString(), cp.getR2().toString(), cp.getR3().toString(), cp.getR4().toString(), cp.getX().toString(), cp.getY()
                            .toString(), cp.getZ().toString());
                }).collect(Collectors.joining(";")));
    }
}
