package com.pearl.fcw.lobby.pojo.columnDescriptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.lobby.pojo.WPlayerInfo;
import com.pearl.fcw.lobby.pojo.json.JsonPlayerInfoCaches;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.fcw.utils.ZipUtil;

/**
 * PlayerInfo表的特殊字段描述
 */
public class WPlayerInfoColumnDescriptor extends ColumnDescriptor<WPlayerInfo> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public WPlayerInfo get(WPlayerInfo m) {
        if (null == m) {
            return m;
        }
        try {
			if (!StringUtil.isEmpty(m.getCaches())) {
				m.setCachesEntity(ZipUtil.unzipJson(m.getCaches(), JsonPlayerInfoCaches.class));
			}
		} catch (Exception e) {
            logger.warn("playerInfoProto has error : ", e);
        }
        return m;
    }

    @Override
    public void set(WPlayerInfo m) {
		m.setCaches(ZipUtil.zipJson(m.getCachesEntity()));
    }
}
