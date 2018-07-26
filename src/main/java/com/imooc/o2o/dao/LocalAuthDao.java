package com.imooc.o2o.dao;

import com.imooc.o2o.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


/**
 * @Author:REX
 * @Date: Create in 16:17 2018/5/19
 */
public interface LocalAuthDao {


	/**
	 * 通过帐号密码查询登录
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByUserNameAndPwd(@Param("username") String username, @Param("password") String password);

	/**
	 * 通过用户ID查询localauth
	 * @param userId
	 * @return
	 */
	LocalAuth queryLocalByUserId(@Param("userId") Long userId);

	/**
	 * 添加平台帐号
	 * @param localAuth
	 * @return
	 */
	int insertLocalAuth(LocalAuth localAuth);

	/**
	 * 通过userid username password更改密码
	 * @param userId
	 * @param username
	 * @param password
	 * @param newPassword
	 * @param editTime
	 * @return
	 */
	int updateLocalAuth(@Param("userId") Long userId, @Param("username") String username, @Param("password") String password, @Param("newPassword") String newPassword, @Param("editTime") Date editTime);
}
