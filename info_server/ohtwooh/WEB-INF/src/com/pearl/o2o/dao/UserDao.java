package com.pearl.o2o.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.pearl.o2o.pojo.User;



public class UserDao extends SqlMapClientDaoSupport {

	//==============================
	//Get methods
	//==============================		
	@SuppressWarnings("unchecked")
	public List<User> getUser(String userName) throws DataAccessException {
		return this.getSqlMapClientTemplate().queryForList("User.select", userName);
	}	
	
	
	public User getUserById(int id) {
		return (User) this.getSqlMapClientTemplate().queryForObject("User.selectById", id);
	}
	
	
	public User create(User user) throws DataAccessException {
		user.setId((Integer) this.getSqlMapClientTemplate().insert("User.insert",user));
		return user;
	}		
}
