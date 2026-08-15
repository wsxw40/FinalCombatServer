package com.pearl.fcw.lobby.servlet.server.system;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.pojo.WSysNotice;
import com.pearl.fcw.gm.service.WSysNoticeService;
import com.pearl.fcw.proto.enums.ENoticeType;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

/**
 * 获取sysNotice
 */
@Service("fcw_s_notice_list")
public class SNoticeList extends BaseServerServlet implements Servletable {

	private static final long serialVersionUID = -7659978774008791818L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private SNoticeList fcw_s_notice_list;
    @Resource
	private WSysNoticeService wSysNoticeService;

    @Override
    protected byte[] innerService(BinaryReader in) throws IOException, Exception {
        try {
			return fcw_s_notice_list.server(in);
        } catch (Exception e) {
			logger.error("fcw_s_notice_list has error : ", e);
        }
        return Servletable.super.server(in);
    }

    @Override
    public byte[] server(BinaryReader reader) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

		List<WSysNotice> wSysNoticeList = wSysNoticeService.findList(null);
		out.write(BinaryUtil.toByta(wSysNoticeList.size()));
		for (WSysNotice wSysNotice : wSysNoticeList) {
			out.write(BinaryUtil.toByta(wSysNotice.getId()));
			out.write(BinaryUtil.toByta(wSysNotice.getType().byteValue()));
			out.write(BinaryUtil.toByta(wSysNotice.getContent()));
			if (wSysNotice.getType() == ENoticeType.NOTICE_LOOP.getNumber()) {
				out.write(BinaryUtil.toByta(wSysNotice.getStartTime().getTime() / 1000));
				out.write(BinaryUtil.toByta(wSysNotice.getEndTime().getTime() / 1000));
				out.write(BinaryUtil.toByta(wSysNotice.getNoticeTime()));
			} else if (wSysNotice.getType() == 1) {
				out.write(BinaryUtil.toByta(wSysNotice.getStartTime().getTime() / 1000));
			}
		}

        return out.toByteArray();
    }
}
