package com.neusoft.neumooc.dao;

import java.util.Map;

import com.neusoft.core.common.exception.DAOException;

public interface UserDAO {


	/** 
	 * @Title: getUserByName 
	 * @Description: TODO 通过登录名获取用户
	 * @param userName
	 * @return Map<String,Object>
	 */
	public Map<String,Object> getUserByName(String userName) throws DAOException;
	
	/** 
	 * @Title: updatePass 
	 * @Description: TODO 修改用户密码
	 * @param userName 用户名
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @return boolean
	 */
	public boolean updatePass(String userName,String oldPass,String newPass) throws DAOException;
}
