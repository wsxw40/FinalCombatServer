package com.pearl.fcw.info.lobby.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.pearl.fcw.info.core.persistence.BaseEntity;
import com.pearl.fcw.info.core.persistence.config.ClassMetadataWrapper;
import com.pearl.fcw.info.core.persistence.dao.CacheSingleGenericDao;
import com.pearl.fcw.info.lobby.pojo.SysConfig;

@Repository
public class SysConfigDao extends CacheSingleGenericDao<SysConfig> {

	public SysConfigDao() {
		super(SysConfig.class);
	}

	public <T extends BaseEntity> List<Map<String, Object>> queryAllSysTableColumns(
			ClassMetadataWrapper<T> wrapper) {
		return singleSourceRouter.namedParameterJdbcTemplate()
				.getJdbcOperations().queryForList(wrapper.getQueryAllSql());
	}
}
