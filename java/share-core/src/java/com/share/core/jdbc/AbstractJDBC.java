package com.share.core.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.share.core.annotation.processor.PojoProcessor;
import com.share.core.util.StringUtil;

/**
 * jdbc抽象类
 */
public abstract class AbstractJDBC {
	/**
	 * logger
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * jdbc
	 */
	protected JdbcTemplate jdbc;
	/**
	 * 数据库pojo对象解析器
	 */
	@Autowired
	private PojoProcessor pojoProcessor;

	/**
	 * 构造函数
	 */
	public AbstractJDBC() {
		init();
	}

	/**
	 * 注入数据源
	 * @author ruan
	 * @param dataSource
	 */
	protected abstract void setDataSource(DataSource dataSource);

	/**
	 * 初始化测试连接
	 */
	protected void init() {
		queryInt("select 1");
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param clazz
	 * @param args
	 */
	public final <T> List<T> queryList(String sql, Class<T> clazz, Object... args) {
		List<T> list = new ArrayList<T>();
		Map<String, Method> methodMap = pojoProcessor.getSetMethodMapByClass(clazz);
		if (methodMap == null) {
			logger.error("class {} methodMap is null", clazz.getName());
			return list;
		}
		try {
			SqlRowSet rs = jdbc.queryForRowSet(sql, args);
			while (rs.next()) {
				T t = clazz.newInstance();
				for (Entry<String, Method> e : methodMap.entrySet()) {
					String column = fieldNameToColumnName(e.getKey());//程序字段->数据库字段(adminPhoneId->admin_phone_id)
					e.getValue().invoke(t, rs.getObject(column));
				}
				list.add(t);
			}
			return list;
		} catch (DataAccessException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("", e);
		}
		return list;
	}

	/**
	 * @author ruan
	 * @param <T>
	 * @param sql
	 * @param clazz
	 * @param args
	 */
	public final <T> T queryT(String sql, Class<T> clazz, Object... args) {
		Map<String, Method> methodMap = pojoProcessor.getSetMethodMapByClass(clazz);
		if (methodMap == null) {
			logger.error("class {} methodMap is null", clazz.getName());
			return null;
		}
		try {
			SqlRowSet rs = jdbc.queryForRowSet(sql, args);
			while (rs.next()) {
				T t = clazz.newInstance();
				for (Entry<String, Method> e : methodMap.entrySet()) {
					String column = fieldNameToColumnName(e.getKey());//程序字段->数据库字段(adminPhoneId->admin_phone_id)
					e.getValue().invoke(t, rs.getObject(column));
				}
				return t;
			}
			return null;
		} catch (DataAccessException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param args
	 * @return
	 */
	public final List<Map<String, Object>> queryList(String sql, Object... args) {
		try {
			return jdbc.queryForList(sql, args);
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param args
	 * @return
	 */
	public final byte queryByte(String sql, Object... args) {
		try {
			return StringUtil.getByte(jdbc.queryForObject(sql, args, Byte.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
			return 0;
		}
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param args
	 * @return
	 */
	public final short queryShort(String sql, Object... args) {
		try {
			return StringUtil.getShort(jdbc.queryForObject(sql, args, Short.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
			return 0;
		}
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param args
	 * @return
	 */
	public final int queryInt(String sql, Object... args) {
		try {
			return StringUtil.getInt(jdbc.queryForObject(sql, args, Integer.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
			return 0;
		}
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param args
	 * @return
	 */
	public final long queryLong(String sql, Object... args) {
		try {
			return StringUtil.getLong(jdbc.queryForObject(sql, args, Long.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
			return 0L;
		}
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param args
	 * @return
	 */
	public final float queryFloat(String sql, Object... args) {
		try {
			return StringUtil.getFloat(jdbc.queryForObject(sql, args, Float.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
			return 0L;
		}
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param args
	 * @return
	 */
	public final double queryDouble(String sql, Object... args) {
		try {
			return StringUtil.getDouble(jdbc.queryForObject(sql, args, Double.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
			return 0L;
		}
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param args
	 * @return
	 */
	public final String queryString(String sql, Object... args) {
		try {
			return StringUtil.getString(jdbc.queryForObject(sql, args, String.class));
		} catch (EmptyResultDataAccessException e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 执行update / insert 语句
	 * @param sql sql 语句
	 * @param args 参数数组
	 * @throws Exception
	 */
	public final boolean update(String sql, Object... args) {
		try {
			return jdbc.update(sql, args) > 0;
		} catch (DataAccessException dae) {
			logger.error("update-" + sql, dae);
		}
		return false;
	}

	/**
	 * 更新一条数据
	 * @param t pojo对象(pojo对象的名和表名是对应的)
	 * @param data 要修改的数据
	 */
	public final <T> boolean update(T t, Map<String, Object> data) {
		if (data == null || data.isEmpty()) {
			logger.error("update date map is empty");
			return false;
		}

		// 生成sql update头
		Class<?> clazz = t.getClass();
		StringBuilder sql = new StringBuilder();
		sql.append("update `");

		//类名转化成数据表名
		String className = clazz.getSimpleName().substring(1);
		StringBuilder tableName = new StringBuilder();
		int l = className.length();
		for (int i = 0; i < l; i++) {
			char c = className.charAt(i);
			if (i > 0 && Character.isUpperCase(c)) {
				tableName.append("_");
				tableName.append(c);
			} else {
				tableName.append(c);
			}
		}
		sql.append(tableName.toString().toLowerCase());

		sql.append("` set ");

		// 组成参数列表
		Object[] args = new Object[data.size() + 1];
		int count = 0;
		for (Entry<String, Object> e : data.entrySet()) {
			sql.append("`");
			sql.append(e.getKey());
			sql.append("`=?,");
			args[count] = e.getValue();
			count += 1;
		}
		int len = sql.length();
		sql.delete(len - 1, len);
		sql.append(" where id=?");

		// 最后取出id的值
		Map<String, Method> methodMap = pojoProcessor.getGetMethodMapByClass(clazz);
		try {
			args[args.length - 1] = methodMap.get("id").invoke(t);
		} catch (Exception e) {
			logger.error("", e);
		}
		return update(sql.toString(), args);
	}

	/**
	 * 插入数据,返回自增id
	 * @param sql  
	 * @param key 主键字段名
	 * @param args参数列表
	 * @return -1 异常 
	 */
	public final long insert(final String sql, final String key, final Object... args) {
		long result = -1L;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { key });
				if (args != null && args.length > 0) {
					for (int i = 0; i < args.length; i++) {
						ps.setObject(i + 1, args[i]);
					}
				}
				return ps;
			}
		};
		result = jdbc.update(psc, keyHolder);
		return result > 0 ? keyHolder.getKey().intValue() : -1;
	}

	/**
	 * 保存整个pojo对象
	 * @param t pojo对象
	 * @return -1 异常 
	 */
	public final <T> T save(T t) {
		// 生成sql insert头
		Class<?> clazz = t.getClass();
		StringBuilder sql = new StringBuilder();
		sql.append("insert into ");

		//类名转化成数据表名
		String className = clazz.getSimpleName().substring(1);
		StringBuilder tableName = new StringBuilder();
		int l = className.length();
		for (int i = 0; i < l; i++) {
			char c = className.charAt(i);
			if (i > 0 && Character.isUpperCase(c)) {
				tableName.append("_");
				tableName.append(c);
			} else {
				tableName.append(c);
			}
		}
		sql.append(tableName.toString().toLowerCase());

		sql.append(" (");

		try {
			// 统计有多少个字段
			int count = 0;

			// 列出字段
			Map<String, Method> methodMap = pojoProcessor.getGetMethodMapByClass(clazz);
			for (Entry<String, Method> e : methodMap.entrySet()) {
				String column = fieldNameToColumnName(e.getKey());//程序字段->数据库字段(adminPhoneId->admin_phone_id)
				if ("id".equals(column)) {
					continue;
				}
				sql.append("`");
				sql.append(column);
				sql.append("`,");

				count += 1;
			}
			int len = sql.length();
			sql.delete(len - 1, len);
			sql.append(") values (");

			// 统计出来的字段数是为了生成n个?，这样可以使用preperstament的防注入
			for (int i = 0; i < count; i++) {
				sql.append("?,");
			}
			len = sql.length();
			sql.delete(len - 1, len);
			sql.append(")");

			// 传入参数
			Object[] args = new Object[count];
			count = 0;
			for (Entry<String, Method> e : methodMap.entrySet()) {
				if ("id".equals(e.getKey())) {
					continue;
				}
				args[count] = e.getValue().invoke(t);

				count += 1;
			}
			long id = insert(sql.toString(), "id", args);
			if (id <= 0) {
				// 如果返回的id是非正数，证明插入错误，返回null对象
				return null;
			}
			methodMap = pojoProcessor.getSetMethodMapByClass(clazz);
			methodMap.get("id").invoke(t, id);
			return t;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 批量更新
	 * @param sql sql语句数组
	 * @return 每一条sql语句对应影响的行数
	 */
	public final int[] batchUpdate(String[] sql) {
		try {
			return jdbc.batchUpdate(sql);
		} catch (DataAccessException e) {
			logger.error("", e);
		}
		return null;
	}

	/**
	 * 程序字段->数据库字段(adminPhoneId->admin_phone_id)
	 * @param fieldName 程序字段 
	 * @return columnName 数据库字段
	 */
	private String fieldNameToColumnName(String fieldName) {
		StringBuilder columnName = new StringBuilder();
		int l = fieldName.length();
		for (int i = 0; i < l; i++) {
			char c = fieldName.charAt(i);
			if (i > 0 && Character.isUpperCase(c)) {
				columnName.append("_");
				columnName.append(c);
			} else {
				columnName.append(c);
			}
		}
		return columnName.toString().toLowerCase();
	}
}