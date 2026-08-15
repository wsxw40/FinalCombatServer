package com.pearl.fcw.lobby.servlet.server.system;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.proto.server.RequestResetSysData;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryReader;

/**
 * 重置游戏系统数据
 */
@Service("fcw_s_reset_sys_data")
public class SResetSysData extends BaseServerServlet implements Servletable {

    private static final long serialVersionUID = 4789677919300084353L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private SResetSysData fcw_s_reset_sys_data;

    @Override
    protected byte[] innerService(BinaryReader in) throws IOException, Exception {
        try {
            return fcw_s_reset_sys_data.server(in);
        } catch (Exception e) {
			logger.error("fcw_s_reset_sys_data has error : ", e);
        }
        return Servletable.super.server(in);
    }

    @Override
    public byte[] server(BinaryReader reader) throws Exception {
        byte[] bytes = new byte[reader.readInt()];
        ((BinaryChannelBuffer) reader).readBytes(bytes);
        RequestResetSysData request = RequestResetSysData.parseFrom(bytes);
        request.getClassNameList().forEach(System.out::println);
        return new byte[0];
    }
}
