package com.pde.uweb.database.partner.webcafelist;

import java.util.HashMap;
import java.util.Map;

import com.pde.infor.common.dao.AbstractEntityDao;

public class WebcafeIpListDao extends
		AbstractEntityDao<WebcafeIpListPojo> {

	@Override
	public Object add(WebcafeIpListPojo arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(WebcafeIpListPojo arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(long arg0, long arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getVersion(long arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WebcafeIpListPojo select(long id) {
		if (id <= Long.valueOf(0))
			return null;
		Object result = this.getSqlMapClientTemplate().queryForObject("WebcafeIpList.select", id);
		return (result != null) ? (WebcafeIpListPojo)result : null;
	}
	public static  Map<String, WebcafeIpListPojo> webcafeMap=new HashMap<String, WebcafeIpListPojo>();
	public  Map<String, WebcafeIpListPojo> selectAll() {
		if(webcafeMap.isEmpty()){
			 Map<String, WebcafeIpListPojo> result = this.getSqlMapClientTemplate().queryForMap("WebcafeIpList.select", null, "cafeIp");;
			 webcafeMap=result;
		}
		return webcafeMap;
	}
	@Override
	public boolean update(WebcafeIpListPojo arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateForField(WebcafeIpListPojo arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
