package com.pearl.o2o.dao.impl.nonjoin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.pearl.o2o.pojo.GmReport;

public class GmReportDao extends BaseMappingDao {
	
	@SuppressWarnings("unchecked")
	public List<GmReport> getGmReports(Date start, Date end, int reportPlayerId, int targetPlayerId, int type, String isHandle) throws DataAccessException {
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("reportPlayerId", reportPlayerId);
		map.put("targetPlayerId", targetPlayerId);
		map.put("type", type);
		map.put("isHandle", isHandle);
		return this.getSqlMapClientTemplate().queryForList("GmReport.select", map);
	}
	
	public void updateGmReport(GmReport gmReport) throws DataAccessException {
		this.getSqlMapClientTemplate().update("GmReport.update", gmReport);
	}

}
