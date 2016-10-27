package com.neusoft.core.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.myframework.jdbc.sqltemplate.impl.VelocitySqlTemplate;
import org.myframework.util.StringUtil;
import org.myframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.neusoft.core.common.exception.DAOException;

/**
 * 基础DAO类
 * 
 * 2014-10-24
 * 
 */
public class BaseDAO extends BaseObject {
	/**
	 * SpringMVC框架注入的参数JdbcTemplate 对象
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * SpringMVC框架注入的参数NamedParameterJdbcTemplate 对象
	 */
	@Autowired
	private NamedParameterJdbcTemplate npjt;

	@Autowired
	private VelocitySqlTemplate sqlTemplate;

	/**
	 * 执行单条sql语句，如create table或者drop table
	 * 
	 * @param sql
	 *            要执行sql语句
	 */
	protected void executeSQL(String sql) throws DAOException {
		try {
			this.jdbcTemplate.execute(sql);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行单条insert、update或者delete语句
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @return 执行语句影响数据的条数
	 */
	protected int update(String sql) throws DAOException {
		try {
			return this.jdbcTemplate.update(sql);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行单条insert、update或者delete语句
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param args
	 *            语句中的参数
	 * @return 执行语句影响数据的条数
	 */
	protected int update(String sql, Object... args) throws DAOException {
		try {
			return this.jdbcTemplate.update(sql, args);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行批量的insert、update或者delete语句
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @return 执行语句影响数据的条数
	 */
	protected int[] updateBatch(String[] sql) throws DAOException {
		try {
			return this.jdbcTemplate.batchUpdate(sql);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取一个记录
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param requiredType
	 *            返回对象类型
	 * @return 单个数据对象
	 */
	protected <T> T queryForObject(String sql, Class<T> requiredType)
			throws DAOException {
		try {
			return this.jdbcTemplate.queryForObject(sql, requiredType);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取一个记录
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param requiredType
	 *            返回对象类型
	 * @param args
	 *            参数
	 * @return 单个数据对象
	 * @throws DAOException
	 */
	protected <T> T queryForObject(String sql, Class<T> requiredType,
			Object... args) throws DAOException {
		try {
			return this.jdbcTemplate.queryForObject(sql, requiredType, args);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 获取一个字符串
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param args
	 *            参数
	 * @return 字符串
	 * @throws DAOException
	 */
	protected String queryString(String sql, Object... args)
			throws DAOException {
		try {
			return this.jdbcTemplate.queryForObject(sql, String.class, args);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 获取一个整形数据
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param args
	 *            参数
	 * @return 数字
	 * @throws DAOException
	 */
	protected int queryInteger(String sql, Object... args) throws DAOException {
		try {
			return this.jdbcTemplate.queryForObject(sql, Integer.class, args);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * @Title: queryInteger
	 * @Description: 获取一个整形数据
	 * @param sql
	 *            要执行的语句
	 * @param params
	 *            参数
	 * @return 整型
	 * @throws DAOException
	 * 
	 */
	protected int queryInteger(String sql, Map<String, ?> params)
			throws DAOException {
		try {
			return this.npjt.queryForObject(sql, params, Integer.class);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取一个字符串
	 * 
	 * @param sql
	 *            要执行的语句
	 * @return 字符串
	 */
	protected String queryForString(String sql) throws DAOException {
		try {
			return this.jdbcTemplate.queryForObject(sql, String.class);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取一个整形数字
	 * 
	 * @param sql
	 *            要执行的语句
	 * @return 数字
	 */
	protected int queryForInteger(String sql) throws DAOException {
		try {
			return this.jdbcTemplate.queryForObject(sql, Integer.class);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 没有返回类型的方法 用于多表联查的需要，非公共属性的数据
	 * 
	 * @param sql
	 *            sql语句
	 * 
	 * @return
	 */
	protected List<Map<String, Object>> queryForList(String sql) {
		try {
			return this.jdbcTemplate.queryForList(sql);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 没有返回类型的方法 用于多表联查的需要，非公共属性的数据
	 * 
	 * @param sql
	 *            sql语句
	 * @param args
	 *            参数
	 * @return
	 */
	protected List<Map<String, Object>> queryForList(String sql, Object... args) {
		try {
			return this.jdbcTemplate.queryForList(sql, args);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * @Title queryForList
	 * @Description 没有返回类型的方法 用于多表联查的需要，非公共属性的数据
	 * @param sql
	 * @param bean
	 *            bean类型参数
	 * @return List<Map<String,Object>>
	 */
	protected List<Map<String, Object>> queryForListByBean(String sql,
			Object bean) {
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
					bean);
			return this.npjt.queryForList(sql, namedParameters);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * @Title queryForListByBeanMap
	 * @Description 没有返回类型的方法 用于多表联查的需要，非公共属性的数据
	 * @param sql
	 * @param paramMap
	 *            map类型参数
	 * @return List<Map<String,Object>>
	 */
	protected List<Map<String, Object>> queryForListByBeanMap(String sql,
			Map<String, ?> paramMap) {
		try {
			return this.npjt.queryForList(sql, paramMap);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取列表数据
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param elementType
	 *            对象类型
	 * @return 数据列表
	 */
	protected <T> List<T> queryForList(String sql, Class<T> elementType)
			throws DAOException {
		try {
			return this.jdbcTemplate.queryForList(sql, elementType);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取列表数据
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param elementType
	 *            对象类型
	 * @param args
	 *            语句中的参数
	 * @return 数据列表
	 */
	protected <T> List<T> queryForList(String sql, Class<T> elementType,
			Object... args) throws DAOException {
		try {
			return this.jdbcTemplate.queryForList(sql, elementType, args);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取获取一行数据
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @return map对象，key就是是列的名称
	 */
	protected Map<String, Object> queryForMap(String sql) throws DAOException {
		try {
			return this.jdbcTemplate.queryForMap(sql);
		} catch (EmptyResultDataAccessException ede) {
			log.debug(ede.getMessage());
			return null;
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * @Title queryForMap
	 * @Description 执行select语句，获取获取一行数据
	 * @param sql
	 * @param bean
	 *            bean对象
	 * @return
	 * @throws DAOException
	 *             Map<String,Object>
	 */
	protected Map<String, Object> queryForMapByBean(String sql, Object bean)
			throws DAOException {
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
					bean);
			return this.npjt.queryForMap(sql, namedParameters);
		} catch (EmptyResultDataAccessException ede) {
			log.debug(ede.getMessage());
			return null;
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * @Title queryForMapByBeanMap
	 * @Description 执行select语句，获取获取一行数据
	 * @param sql
	 * @param paramMap
	 *            map类型参数
	 * @return
	 * @throws DAOException
	 *             Map<String,Object>
	 */
	protected Map<String, Object> queryForMapByBeanMap(String sql,
			Map<String, ?> paramMap) throws DAOException {
		try {
			return this.npjt.queryForMap(sql, paramMap);
		} catch (EmptyResultDataAccessException ede) {
			log.debug(ede.getMessage());
			return null;
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取获取一行数据
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param args
	 *            sql语句中的参数
	 * @return map对象，key就是是列的名称
	 */
	protected Map<String, Object> queryForMap(String sql, Object... args)
			throws DAOException {
		try {
			return this.jdbcTemplate.queryForMap(sql, args);
		} catch (EmptyResultDataAccessException ede) {
			log.debug(ede.getMessage());
			return null;
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取一个记录,并转换为传入类型Bean
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param requiredType
	 *            返回对象类型
	 * @return Bean数据对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T> T queryForBean(String sql, Class<T> requiredType)
			throws DAOException {
		try {
			Map m = this.jdbcTemplate.queryForMap(sql);
			Class<?> cls = Class.forName(requiredType.getName());// 取得Class对象
			Object obj = cls.newInstance();
			T t = (T) obj;
			BeanUtils.copyProperties(t, m);
			return t;
		} catch (EmptyResultDataAccessException ede) {
			log.debug(ede.getMessage());
			return null;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * @Title queryForBean
	 * @Description 执行select语句，获取一个记录,并转换为传入类型Bean
	 * @param sql
	 * @param requiredType
	 * @param bean
	 *            bean类型参数
	 * @return
	 * @throws DAOException
	 *             T
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T> T queryForBeanByBean(String sql, Class<T> requiredType,
			Object bean) throws DAOException {
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
					bean);
			Map m = this.npjt.queryForMap(sql, namedParameters);
			Class<?> cls = Class.forName(requiredType.getName());// 取得Class对象
			Object obj = cls.newInstance();
			T t = (T) obj;
			BeanUtils.copyProperties(t, m);
			return t;
		} catch (EmptyResultDataAccessException ede) {
			log.debug(ede.getMessage());
			return null;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * @Title queryForBeanByBeanMap
	 * @Description 执行select语句，获取一个记录,并转换为传入类型Bean
	 * @param sql
	 * @param requiredType
	 * @param paramMap
	 *            map类型参数
	 * @return
	 * @throws DAOException
	 *             T
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T> T queryForBeanByBeanMap(String sql, Class<T> requiredType,
			Map<String, ?> paramMap) throws DAOException {
		try {
			Map m = this.npjt.queryForMap(sql, paramMap);
			Class<?> cls = Class.forName(requiredType.getName());// 取得Class对象
			Object obj = cls.newInstance();
			T t = (T) obj;
			BeanUtils.copyProperties(t, m);
			return t;
		} catch (EmptyResultDataAccessException ede) {
			log.debug(ede.getMessage());
			return null;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取一个记录,并转换为传入类型Bean
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param requiredType
	 *            返回对象类型
	 * @param args
	 *            参数
	 * @return Bean数据对象
	 * @throws DAOException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected <T> T queryForBean(String sql, Class<T> requiredType,
			Object... args) throws DAOException {
		try {
			Map m = this.jdbcTemplate.queryForMap(sql, args);
			Class<?> cls = Class.forName(requiredType.getName());// 取得Class对象
			Object obj = cls.newInstance();
			T t = (T) obj;
			BeanUtils.copyProperties(t, m);
			return t;

		} catch (EmptyResultDataAccessException ede) {
			log.debug(ede.getMessage());
			return null;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取列表数据,并指定列表中Bean的类型
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param requiredType
	 *            对象类型
	 * @return List<Bean>数据列表
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> queryForBeanList(String sql, Class<T> requiredType)
			throws DAOException {
		try {
			List<T> resultList = new ArrayList<T>();
			Class<?> cls = Class.forName(requiredType.getName());// 取得Class对象

			List<Map<String, Object>> list = this.jdbcTemplate
					.queryForList(sql);
			for (Map<String, Object> m : list) {
				T t = (T) cls.newInstance();
				BeanUtils.copyProperties(t, m);
				resultList.add(t);
			}
			return resultList;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取列表数据
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param requiredType
	 *            对象类型
	 * @param args
	 *            语句中的参数
	 * @return 数据列表
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> queryForBeanList(String sql, Class<T> requiredType,
			Object... args) throws DAOException {
		try {
			List<T> resultList = new ArrayList<T>();
			Class<?> cls = Class.forName(requiredType.getName());// 取得Class对象

			List<Map<String, Object>> list = this.jdbcTemplate.queryForList(
					sql, args);
			for (Map<String, Object> m : list) {
				T t = (T) cls.newInstance();
				BeanUtils.copyProperties(t, m);
				resultList.add(t);
			}
			return resultList;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行select语句，获取列表数据（使用npjt方式）
	 * 
	 * @param sql
	 *            要执行的语句
	 * @param requiredType
	 *            对象类型
	 * @param bean
	 *            语句中的参数,从Map中获取
	 * @return 数据列表
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> queryForBeanListByBeanMap(String sql,
			Class<T> requiredType, Map<String, ?> paramMap) {
		try {
			List<T> resultList = new ArrayList<T>();
			Class<?> cls = Class.forName(requiredType.getName());// 取得Class对象
			List<Map<String, Object>> list = this.npjt.queryForList(sql,
					paramMap);
			for (Map<String, Object> m : list) {
				T t = (T) cls.newInstance();
				BeanUtils.copyProperties(t, m);
				resultList.add(t);
			}
			return resultList;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * @Title queryForBeanListByBean
	 * @Description 执行select语句，获取列表数据（使用npjt方式）
	 * @param sql
	 * @param requiredType
	 * @param bean
	 *            语句中的参数,从Bean中获取
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> queryForBeanListByBean(String sql,
			Class<T> requiredType, Object bean) {
		try {
			List<T> resultList = new ArrayList<T>();
			Class<?> cls = Class.forName(requiredType.getName());// 取得Class对象
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
					bean);
			List<Map<String, Object>> list = this.npjt.queryForList(sql,
					namedParameters);
			for (Map<String, Object> m : list) {
				T t = (T) cls.newInstance();
				BeanUtils.copyProperties(t, m);
				resultList.add(t);
			}
			return resultList;
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 执行单条insert、update或者delete语句（使用npjt方式）
	 * 
	 * @param sql
	 *            要执行的sql语句
	 * @param bean
	 *            语句中的参数,从Bean中获取
	 * @return 执行语句影响数据的条数
	 */
	protected int update(String sql, Object bean) throws DAOException {
		try {
			SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(
					bean);
			return this.npjt.update(sql, namedParameters);
		} catch (DataAccessException e) {
			throw new DAOException(e.getMessage(), e);
		}
	}

	/**
	 * 获取LIMIT语句串，主要用于Mysql分页查询
	 * 
	 * @param index
	 *            开始行索引
	 * @param count
	 *            结果记录的条数
	 * @return LIMIT语句串
	 */
	protected String getLimitSQL(int index, int count) {
		return new StringBuffer().append(" LIMIT ").append(index).append(",")
				.append(count).toString();
	}

	/**
	 * @Title: 获取count语句串，主要用于Mysql分页查询
	 * @Description: TODO
	 * @param sql
	 *            执行翻页sql
	 * @return String
	 */
	protected String getCountSQL(String sql) {
		return new StringBuffer().append(" select count(1) from (").append(sql)
				.append(") _auto_count ").toString();
	}

	/**
	 * @Title: getCount
	 * @Description: 获得数据行数
	 * @param srcSql
	 * @param args
	 * @return int
	 */
	protected int getCount(String srcSql, Object... args) {
		return this.queryInteger(getCountSQL(srcSql), args);
	}

	/**
	 * @Title: getCount
	 * @Description: 获得数据行数
	 * @param srcSql
	 * @param paramMap
	 * @return int
	 */
	protected int getCount(String srcSql, Map<String, ?> paramMap) {
		return this.queryInteger(getCountSQL(srcSql), paramMap);
	}

	/**
	 * 分页查询封装子方法
	 * 
	 * @param srcSql
	 * @param pageIndex
	 * @param pageSize
	 * @param requiredType
	 * @param args
	 * @return
	 * @throws DAOException
	 */
	protected <T> List<T> getPageData(String srcSql, Class<T> requiredType,
			int pageIndex, int pageSize, Object... args) throws DAOException {
		StringBuffer pagingSql = new StringBuffer();
		pagingSql.append(srcSql);
		pagingSql.append(this.getLimitSQL(pageIndex, pageSize));
		return this.queryForBeanList(pagingSql.toString(), requiredType, args);
	}

	/**
	 * @Title: getPageData
	 * @Description: 分页查询封装子方法
	 * @param srcSql
	 *            执行的sql语句
	 * @param requiredType
	 *            返回List中的元素类型
	 * @param pageIndex
	 * @param pageSize
	 * @param paramMap
	 *            参数Map
	 * @return
	 * @throws DAOException
	 *             List<T>
	 */
	protected <T> List<T> getPageData(String srcSql, Class<T> requiredType,
			int pageIndex, int pageSize, Map<String, ?> paramMap)
			throws DAOException {
		StringBuffer pagingSql = new StringBuffer();
		pagingSql.append(srcSql);
		pagingSql.append(this.getLimitSQL(pageIndex, pageSize));
		return this.queryForBeanListByBeanMap(pagingSql.toString(),
				requiredType, paramMap);
	}

	/**
	 * 获得解析后的sql
	 * 
	 * @param sqlKey
	 * @param params
	 * @return
	 */
	protected String getSql(String sqlKey, Map<String, Object> params) {
		String sql = sqlTemplate.getSql(sqlKey, params);
		List<Object> paramList = new ArrayList<Object>();
		return this.sqlConvert(sql, params, paramList);
	}

	protected String getSql(String sqlKey) {
		return this.getSql(sqlKey, new HashMap<String, Object>());
	}

	/**
	 * sql ( select * from table where user_id = '#userId' ) Convert to standard
	 * jdbc sql ( select * from table where user_id = '112313123') ;
	 *
	 * @param sql
	 * @param map
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private String sqlConvert(String sql, Map qryMap, List<Object> list) {
		// replace :param to bindValue
		// if (qryMap != null && !qryMap.isEmpty()) {
		// List<String> params = getParameterList(sql);
		// for (String param : params) {
		// list.add(qryMap.get(param));
		// sql = StringUtils.replaceOnce(sql, ":" + param, "?");
		// }
		// }
		// replace #param to value
		if (qryMap != null && !qryMap.isEmpty()) {
			List<String> params = getReplaceParameterList(sql);
			for (String param : params) {
				sql = StringUtils.replaceOnce(sql, "#" + param,
						StringUtil.asString(qryMap.get(param)));
			}
		}
		return sql;
	}

	/**
	 * sql ( select * from table where user_id = :userId ) :userId will be added
	 * into list as param
	 *
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<String> getParameterList(String sql) {
		String regex = "\\:(\\w+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sql);
		List<String> params = new ArrayList<String>();
		while (matcher.find())
			params.add(matcher.group(1));
		return params;
	}

	private List<String> getReplaceParameterList(String sql) {
		String regex = "\\#(\\w+)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(sql);
		List<String> params = new ArrayList<String>();
		while (matcher.find())
			params.add(matcher.group(1));
		return params;
	}

	/**
	 * @Title batchUpdate
	 * @Description 批量执行insert、update、delete操作
	 * @param sql
	 * @param args
	 * @return int[]
	 */
	protected int[] batchUpdate(String sql, Map<String, Object>[] args) {
		int[] r = npjt.batchUpdate(sql, args);
		return r;
	}

	/**
	 * @Title batchUpdate
	 * @Description 批量执行insert、update、delete操作
	 * @param sql
	 * @param args
	 * @return int[]
	 */
	protected int[] batchUpdate(String sql, List<Object[]> args) {
		int[] r = jdbcTemplate.batchUpdate(sql, args);
		return r;
	}
}
