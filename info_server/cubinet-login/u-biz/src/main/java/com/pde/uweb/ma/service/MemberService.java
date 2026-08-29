/**
 * 
 */
package com.pde.uweb.ma.service;

import java.util.Map;

import com.pde.uweb.database.ma.security.QuestionPojo;
import com.pde.uweb.database.ma.user.UserPojo;
import com.pde.uweb.ma.dto.LoginRequestDto;
import com.pde.uweb.ma.dto.LoginResultDto;
import com.pde.uweb.ma.dto.RegisterRequestDto;
import com.pde.uweb.ma.dto.RegisterResultDto;

/**
 * @author Huanggang
 * 
 */
public interface MemberService {

	/**
	 * 创建会员
	 */
	public RegisterResultDto doCreateMemberRnTx(RegisterRequestDto requestDto) throws Exception;

	/**
	 *  用户登录
	 */
	public LoginResultDto doLogin(LoginRequestDto requestDto) throws Exception;
	
	/**
	 *  校验用户名是否存在
	 */
	public boolean isLoginNameExist(String loginName) throws Exception;
	
	/**
	 *  根据 email 获得 userId
	 */
	public long getUserIdByEmail(String email) throws Exception;
	
	/**
	 *  修改用户密码
	 *  
	 *  @param userId 待修改的user
	 *  @param newPwd 新密码
	 *  @return 返回修改结果
	 */
	public boolean modifyUserPassword(long userId, String newPwd);

	/** 
	 * 根据 user_id 获得 user 对象 
	 */
	public UserPojo getUser(long userId);
	
	/** 
	 *  绑定手机号
	 */
	public boolean bindMobile(long userId, String mobile);
	
	/** 
	 *  绑定邮箱
	 */
	public boolean bindEmail(long userId, String email);
	
	/** 
	 *  防沉迷认证
	 *  
	 *  @param userId 用户id
	 *  @param idName 真实姓名
	 *  @param idNumber 身份证
	 *  @return 返回操作结果
	 */
	public boolean addicateAudit(long userId, String idName, String idNumber);
	
	/** 
	 *  设置安全问题
	 *  
	 *  @param userId 用户id
	 *  @param questionId 问题id
	 *  @param answer 答案
	 *  @return 返回操作结果
	 */
	public boolean questionAudit(long userId, long questionId, String answer);
	
	/**
	 * 获得所有安全问题记录
	 * 
	 * @return 返回 Question Map< question_id, question_info >
	 */
	public Map<String, QuestionPojo> getAllQuestionsMap();
	
	/**
	 * 获得指定的安全问题详述
	 */
	public String getQuestionInfo(long questionId);
	
	/**
	 * 快速注册
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @return 注册结果对象
	 */
	public RegisterResultDto doQuickCreateMemberRnTx(String userName, String password) throws Exception;

	/**
	 *  快速登录
	 *   
	 *  @param userName 用户名
	 *  @param password 密码
	 *  @return 登录结果对象
	 */
	public LoginResultDto doQuickLogin(String userName, String password);

}
