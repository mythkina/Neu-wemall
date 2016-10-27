package com.neusoft.neumooc.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;
import com.neusoft.core.common.BaseDAO;
import com.neusoft.core.common.exception.DAOException;

@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO{

	private final static String QUERY_USER = "select * from wemall_admin where username=?";
	/** 
	 * @Title: getUserByName 
	 * @Description: TODO 通过登录名获取用户
	 * @param userName
	 * @return Map<String, Object>
	 * @throws DAOException
	 */
	@Override
	public Map<String, Object> getUserByName(String userName) throws DAOException{
		// TODO Auto-generated method stub
		Object[] obj = {userName};
		return this.queryForMap(QUERY_USER,obj);
	}

	private final static String UPDATE_USER_PASSWORD = "update training_users set password = ? where username = ? and password = ?";
	/** 
	 * @Title: updatePass 
	 * @Description: TODO 修改用户密码
	 * @param userName 用户名
	 * @param oldPass 旧密码
	 * @param newPass 新密码
	 * @return boolean
	 * @throws DAOException
	 */
	@Override
	public boolean updatePass(String userName, String oldPass, String newPass) throws DAOException{
		// TODO Auto-generated method stub
		Object[] obj = {newPass,userName,oldPass };
		int t = this.update(UPDATE_USER_PASSWORD,obj);
		if(t==1){
			return true;
		}
		return false;
	}

	
}
