package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.LocalAuthDao;
import com.imooc.o2o.dto.LocalAuthExecution;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.enums.LocalAuthStateEnum;
import com.imooc.o2o.exception.LocalAuthOperationException;
import com.imooc.o2o.service.LocalAuthService;
import com.imooc.o2o.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author:REX
 * @Date: Create in 21:48 2018/5/19
 */
@Service
public class LocalAuthServiceImpl implements LocalAuthService {

	@Autowired
	private LocalAuthDao localAuthDao;

	@Override
	public LocalAuth getLocalAuthByUsernameAndPwd(String userName, String passWord) {
		return localAuthDao.queryLocalByUserNameAndPwd(userName, MD5.getMd5(passWord));
	}

	@Override
	public LocalAuth getLocalAuthByUserId(Long userId) {
		return localAuthDao.queryLocalByUserId(userId);
	}

	@Override
	@Transactional
	public LocalAuthExecution bindLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		// 空值判断
		if(localAuth == null || localAuth.getPassword() == null || localAuth.getUsername() == null
				|| localAuth.getPersonInfo() == null || localAuth.getPersonInfo().getUserId() == null){
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		// 查询用户是否已绑定
		LocalAuth tempAuth = localAuthDao.queryLocalByUserId(localAuth.getPersonInfo().getUserId());
		if(tempAuth != null){
			// 已绑定则退出
			return new LocalAuthExecution(LocalAuthStateEnum.ONLY_ONE_ACCOUNT);
		}
		try{
			// 没有绑定
			localAuth.setCreateTime(new Date());
			localAuth.setEditTime(new Date());
			// 密码加密
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if(effectedNum <= 0){
				throw new LocalAuthOperationException("帐号绑定失败");
			}else{
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS,localAuth);
			}
		}catch (Exception e){
			throw new LocalAuthOperationException("INSERT LOCALAUTH ERROR : " + e.getMessage());
		}
	}

	@Override
	@Transactional
	public LocalAuthExecution modifyLocalAuth(Long userId, String username, String password, String newPassword) throws LocalAuthOperationException {
		// 空值判断
		if(userId != null && username != null && password != null && newPassword != null && !password.equals(newPassword)){
			try {
				// 更新密码
				int effectedNum = localAuthDao.updateLocalAuth(userId,username,MD5.getMd5(password),MD5.getMd5(newPassword),new Date());
				if(effectedNum <= 0){
					throw new LocalAuthOperationException("修改密码失败");
				}
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
			}catch (Exception e){
				throw new LocalAuthOperationException("修改密码失败 : " + e.toString());
			}
		}else{
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
}
