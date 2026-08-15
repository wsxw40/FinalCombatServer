package com.pearl.fcw.lobby.servlet.server.system;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.gm.pojo.WSysLevel;
import com.pearl.fcw.gm.service.WSysLevelService;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

/**
 * 获取地图列表
 */
@Service("fcw_s_get_level_list")
public class SGetLevelList extends BaseServerServlet implements Servletable {

	private static final long serialVersionUID = 1454743815326661946L;
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource
	private SGetLevelList fcw_s_get_level_list;
	@Resource
	private WSysLevelService WSysLevelService;

	@Override
	protected byte[] innerService(BinaryReader in) throws IOException, Exception {
		try {
			return fcw_s_get_level_list.server(in);
		} catch (Exception e) {
			logger.error("fcw_s_get_level_list has error : ", e);
		}
		return Servletable.super.server(in);
	}

	@Override
	public byte[] server(BinaryReader in) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		List<WSysLevel> list = WSysLevelService.findList(null);
		out.write(BinaryUtil.toByta(list.size()));
		for (WSysLevel wSysLevel : list) {
			out.write(BinaryUtil.toBytas(wSysLevel.getId()));
			out.write(BinaryUtil.toByta(wSysLevel.getName()));
			out.write(BinaryUtil.toByta(wSysLevel.getType().byteValue()));
			out.write(BinaryUtil.toByta("<LD" + wSysLevel.getId() + "^0>"));
			out.write(BinaryUtil.toByta(wSysLevel.getDescription()));
			out.write(BinaryUtil.toByta(wSysLevel.getIsVip().byteValue()));
			out.write(BinaryUtil.toByta(wSysLevel.getIsnew().byteValue()));
			out.write(BinaryUtil.toByta(wSysLevel.getNumForTeam()));
			out.write(BinaryUtil.toByta(wSysLevel.getIsGm().byteValue()));
		}
		return out.toByteArray();
	}
}
