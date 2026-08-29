/**
 * 
 */
package com.pde.uweb.ma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pde.infor.common.utils.KeyUtil;
import com.pde.uweb.database.ma.security.QuestionDao;
import com.pde.uweb.database.ma.security.QuestionPojo;
import com.pde.uweb.database.ma.user.UserDao;
import com.pde.uweb.database.ma.user.UserPojo;
import com.pde.uweb.ma.dto.LoginRequestDto;
import com.pde.uweb.ma.dto.LoginResultDto;
import com.pde.uweb.ma.dto.RegisterRequestDto;
import com.pde.uweb.ma.dto.RegisterResultDto;
import com.pde.uweb.ma.service.MemberService;

/**
 * @author Huanggang
 * 
 */
public class MemberServiceImpl implements MemberService {

	private UserDao userDao;
	
	private QuestionDao questionDao;

	/**
	 * 创建用户
	 */
	public RegisterResultDto doCreateMemberRnTx(RegisterRequestDto requestDto)
			throws Exception {
		
		RegisterResultDto result = new RegisterResultDto();
		try {
			UserPojo user = new UserPojo();
			user.setUserId(KeyUtil.generateDBKey());
			user.setIdName(requestDto.getIdName());
			user.setIdNumber(requestDto.getIdNumber());
			user.setPassword(requestDto.getPassword());
			user.setUserName(requestDto.getUserName());
			user.setEmail(requestDto.getEm());
			user.setMobile(requestDto.getMobile());
			user.setStatus(UserPojo.STATUS_INACTIVE);
			userDao.add(user);

			// 创建成功
			result.setRegisterSuccess(true);
			result.setUser(user);
		} catch (Exception e) {
			// // 创建失败
			result.setRegisterSuccess(false);
			result.setRegisterError(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 用户登录
	 */
	public LoginResultDto doLogin(LoginRequestDto requestDto)
			throws Exception {
		
		LoginResultDto result = new LoginResultDto();
		try {

			// 校验有无此用户名
			UserPojo user = userDao.getUserByLoginName(requestDto.getUserName());
			if (user==null) {
				result.setLoginSuccess(false);
				result.setLoginError("用户名或密码不正确");
				return result;
			}
			
			// 校验密码是否正确
			if (user.getPassword().equals(requestDto.getPassword())) {
				result.setLoginSuccess(true);
				result.setUser(user);
			} else {
				result.setLoginSuccess(false);
				result.setLoginError("用户名或密码不正确");
			}
		} catch (Exception e) {
			result.setLoginSuccess(false);
			result.setLoginError(e.getMessage());
		}
		return result;
	}
	
	/**
	 *  校验用户名是否存在
	 */
	public boolean isLoginNameExist(String loginName) throws Exception {
		long userId = userDao.getUidByLoginName(loginName);
		return userId>Long.valueOf(0);
	}
	
	/**
	 *  根据 email 获得 userId
	 */
	public long getUserIdByEmail(String email) throws Exception {
		return userDao.getUidByEmail(email);
	}
	
	/**
	 *  修改用户密码
	 *  
	 *  @param userId 待修改的user
	 *  @param newPwd 新密码
	 *  @return 返回修改结果
	 */
	public boolean modifyUserPassword(long userId, String newPwd) {
		
		UserPojo user = this.getUser(userId);
		user.setPassword(newPwd);
		return userDao.updateForField(user);
	}

	/** 
	 * 根据 user_id 获得 user 对象
	 */
	public UserPojo getUser(long userId) {
		return userDao.select(userId);
	}

	/** 
	 *  绑定手机号
	 */
	public boolean bindMobile(long userId, String mobile) {
		
		UserPojo user = this.getUser(userId);
		user.setMobile(mobile);
		user.setMobileStatus(UserPojo.STATUS_MOBILE_BIND_YES);		
		return userDao.updateForField(user);
	}
	
	/** 
	 *  绑定邮箱
	 */
	public boolean bindEmail(long userId, String email) {
		
		UserPojo user = this.getUser(userId);
		user.setEmail(email);
		user.setEmailStatus(UserPojo.STATUS_EMAIL_BIND_YES);
		return userDao.updateForField(user);
	}
	
	/** 
	 *  防沉迷认证
	 *  
	 *  @param userId 用户id
	 *  @param idName 真实姓名
	 *  @param idNumber 身份证
	 *  @return 返回操作结果
	 */
	public boolean addicateAudit(long userId, String idName, String idNumber) {
		
		UserPojo user = this.getUser(userId);
		user.setIdName(idName);
		user.setIdNumber(idNumber);
		user.setStatus(UserPojo.STATUS_ACTIVE);
		return userDao.updateForField(user);
	}
	
	/** 
	 *  设置安全问题
	 *  
	 *  @param userId 用户id
	 *  @param questionId 问题id
	 *  @param answer 答案
	 *  @return 返回操作结果
	 */
	public boolean questionAudit(long userId, long questionId, String answer) {
		
		UserPojo user = this.getUser(userId);
		user.setQuestionId(questionId);
		user.setAnswer(answer);
		return userDao.updateForField(user);
	}
	
	/**
	 * 获得所有安全问题记录
	 * 
	 * @return 返回 Question Map< question_id, question_info >
	 */
	public Map<String, QuestionPojo> getAllQuestionsMap() {
		
		List<QuestionPojo> questionList = questionDao.getAllQuestions();
		Map<String, QuestionPojo> result = new HashMap<>();
		for (QuestionPojo question : questionList) {
			result.put(String.valueOf(question.getQuestionId()), question);
		}
		return result;
	}
	
	/**
	 * 获得指定的安全问题详述
	 */
	public String getQuestionInfo(long questionId) {
		
		return questionDao.getQuestionInfo(questionId);
	}
	
	/**
	 * 快速注册
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @return 注册结果对象
	 */
	public RegisterResultDto doQuickCreateMemberRnTx(String userName, String password)  
			throws Exception {

		RegisterResultDto result = new RegisterResultDto();
		try {
			UserPojo user = new UserPojo();
			user.setUserId(KeyUtil.generateDBKey());
			user.setUserName(userName);
			user.setPassword(password);
			user.setStatus(UserPojo.STATUS_INACTIVE);

			userDao.add(user);
			// 创建成功
			result.setRegisterSuccess(true);
			result.setUser(user);
		} catch (Exception e) {
			// 创建失败
			result.setRegisterSuccess(false);
			result.setRegisterError(e.getMessage());
		}
		return result;
	}
	
	/**
	 *  快速登录
	 *   
	 *  @param userName 用户名
	 *  @param password 密码
	 *  @return 登录结果对象
	 */
	public LoginResultDto doQuickLogin(String userName, String password) {
		
		LoginResultDto result = new LoginResultDto();
		try {

			// 校验有无此用户名
			UserPojo user = userDao.getUserByLoginName(userName);
			if (user==null) {
				result.setLoginSuccess(false);
				result.setLoginError("用户名或密码不正确");
				return result;
			}
			
			// 校验密码是否正确
			if (user.getPassword().equals(password)) {
				result.setLoginSuccess(true);
				result.setUser(user);
			} else {
				result.setLoginSuccess(false);
				result.setLoginError("用户名或密码不正确");
			}
		} catch (Exception e) {
			result.setLoginSuccess(false);
			result.setLoginError(e.getMessage());
		}
		return result;
	}
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

}
