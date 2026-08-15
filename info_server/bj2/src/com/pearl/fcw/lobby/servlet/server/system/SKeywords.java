package com.pearl.fcw.lobby.servlet.server.system;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.pojo.WSysBannedWord;
import com.pearl.fcw.gm.service.WSysBannedWordService;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

/**
 * 获取禁言数据
 */
@Service("fcw_s_keywords")
public class SKeywords extends BaseServerServlet implements Servletable {

	private static final long serialVersionUID = -368074654490944083L;
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
	private SKeywords fcw_s_keywords;
    @Resource
	private WSysBannedWordService wSysBannedWordService;

    @Override
    protected byte[] innerService(BinaryReader in) throws IOException, Exception {
        try {
			return fcw_s_keywords.server(in);
        } catch (Exception e) {
			logger.error("fcw_s_keywords has error : ", e);
        }
        return Servletable.super.server(in);
    }

    @Override
    public byte[] server(BinaryReader reader) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
		for (WSysBannedWord wSysBannedWord : wSysBannedWordService.findList(null)) {
			out.write(BinaryUtil.toByta(wSysBannedWord.getBannedWord()));
        }
        return out.toByteArray();
    }
}
