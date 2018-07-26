package com.imooc.o2o.service;

import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.exception.LocalAuthOperationException;


/**
 * @Author:REX
 * @Date: Create in 21:35 2018/5/19
 */
public interface LocalAuthService {
	/**
	 * 通过帐号密码获取平台帐号信息
	 * @param userName
	 * @param passWord
	 * @return
	 */
	LocalAuth getLocalAuthByUsernameAndPwd(String userName, String passWord);

	/**
	 * 通过userid获取平台帐号信息
	 * @param userId
	 * @return
	 */
	LocalAuth getLocalAuthByUserId(Long userId);

	/**
	 * 绑定本地帐号
	 * @param localAuth
	 * @return
	 * @throws LocalAuthOperationException
	 */
	LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

	/**
	 * 修改本地帐号密码
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @return
	 * @throws LocalAuthOperationException
	 */
	LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException;
}
