package com.pearl.fcw.lobby.servlet.server.system;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.pojo.WUnspeaker;
import com.pearl.fcw.gm.service.WUnspeakerService;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

/**
 * 获取禁言玩家
 */
@Service("fcw_s_get_unspeaklist")
public class SGetUnspeaklist extends BaseServerServlet implements Servletable {

	private static final long serialVersionUID = -5864638924910120574L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private SGetUnspeaklist fcw_s_get_unspeaklist;
    @Resource
	private WUnspeakerService wUnspeakerService;

    @Override
    protected byte[] innerService(BinaryReader in) throws IOException, Exception {
        try {
			return fcw_s_get_unspeaklist.server(in);
        } catch (Exception e) {
			logger.error("fcw_s_get_unspeaklist has error : ", e);
        }
        return Servletable.super.server(in);
    }

    @Override
    public byte[] server(BinaryReader reader) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

		List<WUnspeaker> wUnspeakerList = wUnspeakerService.findList(null);
		out.write(BinaryUtil.toByta(wUnspeakerList.size()));
		for (WUnspeaker wUnspeaker : wUnspeakerList) {
			out.write(BinaryUtil.toByta(wUnspeaker.getPlayerId()));
		}

        return out.toByteArray();
    }
}
