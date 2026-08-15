package com.pearl.o2o.dao.impl.usejoin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.User;



public class UserDao extends SqlMapClientDaoSupport {

	//==============================
	//Get methods
	//==============================		
	@SuppressWarnings("unchecked")
	public List<User> getUser(String userName) {
		return this.getSqlMapClientTemplate().queryForList("User.select", userName);
	}	
	
	
	public User getUserById(int id) {
		return (User) this.getSqlMapClientTemplate().queryForObject("User.selectById", id);
	}
	
	
	public User create(User user) {
		user.setId((Integer) this.getSqlMapClientTemplate().insert("User.insert",user));
		return user;
	}		
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateUserProfile(int uid, String profile){
		Map map = new HashMap();
		map.put("id", uid);
		map.put("profile", profile);
		this.getSqlMapClientTemplate().update("User.updateUserProfile",map);
	}
	
}
