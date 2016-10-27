package com.neusoft.neumooc.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neusoft.core.common.exception.ServiceException;
import com.neusoft.neumooc.dao.UserDAO;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired UserDAO userDAO;
	/** 
	 * @Title: getUserByName 
	 * @Description: TODO 通过登录名获取用户
	 * @param userName 登录用户名
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getUserByName(String userName)  throws ServiceException{
		// TODO Auto-generated method stub
		return userDAO.getUserByName(userName);
	}

	/** 
	 * @Title: updatePass 
	 * @Description: TODO 修改用户密码
	 * @param userName 登录用户名
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @return boolean
	 */
	@Override
	public boolean updatePass(String userName, String oldPass, String newPass)  throws ServiceException{
		// TODO Auto-generated method stub
		return userDAO.updatePass(userName, oldPass, newPass);
	}

}
