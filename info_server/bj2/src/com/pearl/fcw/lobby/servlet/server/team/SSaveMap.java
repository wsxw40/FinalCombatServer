package com.pearl.fcw.lobby.servlet.server.team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.pojo.TeamLevelConfigPoints;
import com.pearl.fcw.lobby.service.WTeamService;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;

@Service("fcw_s_save_map")
public class SSaveMap extends BaseServerServlet implements Servletable {

    private static final long serialVersionUID = -6334116841824653874L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private SSaveMap fcw_s_save_map;
    @Resource
    private WTeamService wTeamService;

    @Override
    protected byte[] innerService(BinaryReader in) throws IOException, Exception {
        try {
            return fcw_s_save_map.server(in);
        } catch (Exception e) {
            logger.warn("s_save_map has error : ", e);
        }
        return com.pearl.o2o.utils.Constants.EMPTY_BYTE_ARRAY;
    }

    @Override
    public byte[] server(BinaryReader reader) throws Exception {
        try {
            int sysLevelId = reader.readInt();
            int playerId = reader.readInt();
            List<TeamLevelConfigPoints> cpList = new ArrayList<>();
            //解析地图数据
            int size = reader.readInt();
            for (int i = 0; i < size; i++) {
                TeamLevelConfigPoints cp = new TeamLevelConfigPoints();
                cp.setSysItemId(reader.readInt());
                cp.setX(reader.readFloat());
                cp.setY(reader.readFloat());
                cp.setZ(reader.readFloat());
                cp.setR1(reader.readFloat());
                cp.setR2(reader.readFloat());
                cp.setR3(reader.readFloat());
                cp.setR4(reader.readFloat());
                cpList.add(cp);
            }

            wTeamService.saveTeamMap(playerId, sysLevelId, cpList);
        } catch (Exception e) {
            logger.error("Error happend when processing the map save. Exception is ", e);
        }
        return new byte[0];
    }

    @Override
    public String getLockedKey(BinaryReader reader) {
        try {
            reader.readInt();
            int playerId = reader.readInt();
            return playerId + "";
        } catch (IOException e) {
            logger.error("getLockedKey has error ", e);
        }
        return null;
    }
}
