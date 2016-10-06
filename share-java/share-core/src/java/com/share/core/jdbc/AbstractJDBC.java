package com.share.core.jdbc;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.google.common.base.Joiner;
import com.share.core.annotation.processor.PojoProcessor;
import com.share.core.interfaces.DSuper;
import com.share.core.util.StringUtil;
import com.share.core.util.SystemUtil;

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
	public JdbcTemplate jdbc;
	/**
	 * 数据库pojo对象解析器
	 */
	@Autowired
	private PojoProcessor pojoProcessor;

	/**
	 * 注入数据源
	 * @author ruan
	 * @param dataSource
	 */
	public abstract void setDataSource(DataSource dataSource);

	/**
	 * 检查连接
	 */
	protected final void check() {
		byte one = queryByte("select 1");
		if (one != 1) {
			logger.error("can not connect to mysql!");
			System.exit(0);
		}
		update("set names utf8mb4");
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
			//throw new MethodMapNotFoundException("class " + clazz.getName() + " methodMap is null");
		}
		try {
			SqlRowSet rs = jdbc.queryForRowSet(sql, args);
			while (rs.next()) {
				T t = clazz.newInstance();
				for (Entry<String, Method> e : methodMap.entrySet()) {
					String column = fieldNameToColumnName(e.getKey());//程序字段->数据库字段(adminPhoneId->admin_phone_id)
					Class<?> columnClazz = e.getValue().getParameterTypes()[0];
					if (columnClazz.equals(byte.class)) {
						e.getValue().invoke(t, StringUtil.getByte(rs.getByte(column)));
					} else if (columnClazz.equals(Byte.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getByte(value));
					} else if (columnClazz.equals(short.class)) {
						e.getValue().invoke(t, StringUtil.getShort(rs.getShort(column)));
					} else if (columnClazz.equals(Short.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getShort(value));
					} else if (columnClazz.equals(int.class)) {
						e.getValue().invoke(t, StringUtil.getInt(rs.getInt(column)));
					} else if (columnClazz.equals(Integer.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getInt(value));
					} else if (columnClazz.equals(long.class)) {
						e.getValue().invoke(t, StringUtil.getLong(rs.getLong(column)));
					} else if (columnClazz.equals(Long.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getLong(value));
					} else if (columnClazz.equals(float.class)) {
						e.getValue().invoke(t, StringUtil.getFloat(rs.getFloat(column)));
					} else if (columnClazz.equals(Float.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getFloat(value));
					} else if (columnClazz.equals(double.class)) {
						e.getValue().invoke(t, StringUtil.getDouble(rs.getDouble(column)));
					} else if (columnClazz.equals(Double.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getDouble(value));
					} else if (columnClazz.equals(byte[].class)) {
						e.getValue().invoke(t, (byte[]) rs.getObject(column));
					} else {
						e.getValue().invoke(t, StringUtil.getString(rs.getString(column)));
					}
				}
				list.add(t);
			}
			return list;
		} catch (Exception e) {
			logger.error("", e);
		}
		return list;
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param clazz
	 * @param args
	 */
	public final List<Long> queryLongList(String sql, Object... args) {
		List<Long> list = new ArrayList<Long>();
		try {
			SqlRowSet rs = jdbc.queryForRowSet(sql, args);
			while (rs.next()) {
				list.add(StringUtil.getLong(rs.getLong(1)));
			}
			return list;
		} catch (Exception e) {
			logger.error("", e);
		}
		return list;
	}

	/**
	 * @author ruan
	 * @param sql
	 * @param clazz
	 * @param args
	 */
	public final List<String> queryStringList(String sql, Object... args) {
		List<String> list = new ArrayList<String>();
		try {
			SqlRowSet rs = jdbc.queryForRowSet(sql, args);
			while (rs.next()) {
				list.add(StringUtil.getString(rs.getString(1)));
			}
			return list;
		} catch (Exception e) {
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
					Class<?> columnClazz = e.getValue().getParameterTypes()[0];
					if (columnClazz.equals(byte.class)) {
						e.getValue().invoke(t, StringUtil.getByte(rs.getByte(column)));
					} else if (columnClazz.equals(Byte.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getByte(value));
					} else if (columnClazz.equals(short.class)) {
						e.getValue().invoke(t, StringUtil.getShort(rs.getShort(column)));
					} else if (columnClazz.equals(Short.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getShort(value));
					} else if (columnClazz.equals(int.class)) {
						e.getValue().invoke(t, StringUtil.getInt(rs.getInt(column)));
					} else if (columnClazz.equals(Integer.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getInt(value));
					} else if (columnClazz.equals(long.class)) {
						e.getValue().invoke(t, StringUtil.getLong(rs.getLong(column)));
					} else if (columnClazz.equals(Long.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getLong(value));
					} else if (columnClazz.equals(float.class)) {
						e.getValue().invoke(t, StringUtil.getFloat(rs.getFloat(column)));
					} else if (columnClazz.equals(Float.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getFloat(value));
					} else if (columnClazz.equals(double.class)) {
						e.getValue().invoke(t, StringUtil.getDouble(rs.getDouble(column)));
					} else if (columnClazz.equals(Double.class)) {
						String value = rs.getString(column);
						e.getValue().invoke(t, value == null ? null : StringUtil.getDouble(value));
					} else if (columnClazz.equals(byte[].class)) {
						e.getValue().invoke(t, (byte[]) rs.getObject(column));
					} else {
						e.getValue().invoke(t, StringUtil.getString(rs.getString(column)));
					}
				}
				return t;
			}
			return null;
		} catch (Exception e) {
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
		} catch (Exception e) {
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
		} catch (Exception e) {
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
		} catch (Exception e) {
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
		} catch (Exception e) {
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
		} catch (Exception e) {
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
		} catch (Exception e) {
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
	public final double queryDouble(String sql, Object... args) {
		try {
			return StringUtil.getDouble(jdbc.queryForObject(sql, args, Double.class));
		} catch (Exception e) {
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
		} catch (Exception e) {
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
		} catch (Exception e) {
			logger.error("", e);
		}
		return false;
	}

	/**
	 * 更新一条数据
	 * @param t pojo对象(pojo对象的名和表名是对应的)
	 */
	public final <T> boolean update(DSuper t) {
		// 生成sql update头
		Class<?> clazz = t.getClass();
		String table = classNameToTableName(clazz);
		StringBuilder sql = new StringBuilder();
		sql.append("update `");
		sql.append(table);
		sql.append("` set ");

		try {
			// 统计有多少个字段
			int count = 1;

			// 组成参数列表
			Map<String, Method> methodMap = pojoProcessor.getGetMethodMapByClass(clazz);
			for (Entry<String, Method> e : methodMap.entrySet()) {
				String column = fieldNameToColumnName(e.getKey());//程序字段->数据库字段(adminPhoneId->admin_phone_id)
				if ("id".equals(column)) {
					continue;
				}

				sql.append("`");
				sql.append(column);
				sql.append("`=?,");

				count += 1;
			}

			int len = sql.length();
			sql.delete(len - 1, len);
			sql.append(" where `id`=?");

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

			// 把id也set进去
			args[args.length - 1] = methodMap.get("id").invoke(t);
			return update(sql.toString(), args);
		} catch (Exception e) {
			logger.error("", e);
		}

		return true;
	}

	/**
	 * 批量获取T
	 * @param idSet id集合
	 * @param clazz 实体类
	 */
	public final <T, F> List<T> multiGetT(Set<F> idSet, Class<T> clazz) {
		return multiGetT(idSet, clazz, "id");
	}

	/**
	 * 批量获取T(兼容"id"不是主键)
	 * @param idSet id集合
	 * @param clazz 实体类
	 * @param sqlKey 数据库主键(程序字段格式)
	 */
	public final <T, F> List<T> multiGetT(Set<F> idSet, Class<T> clazz, String sqlKey) {
		String column = sqlKey;
		if (!sqlKey.equals("id")) {
			column = fieldNameToColumnName(sqlKey);
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select * from `");
		sql.append(classNameToTableName(clazz));
		sql.append("` where `");
		sql.append(column);
		sql.append("` in (");
		sql.append(Joiner.on(",").join(idSet));
		sql.append(") order by `");
		sql.append(column);
		sql.append("` desc");
		return queryList(sql.toString(), clazz);
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
		return result > 0 ? keyHolder.getKey().longValue() : -1;
	}

	/**
	 * 删除一条数据
	 * @param t pojo对象(pojo对象的名和表名是对应的)
	 */
	public final <T> boolean delete(T t) {
		// 生成sql delete头
		Class<?> clazz = t.getClass();
		StringBuilder sql = new StringBuilder();
		sql.append("delete from `");
		sql.append(classNameToTableName(clazz));
		sql.append("` where id=?");

		Map<String, Method> methodMap = pojoProcessor.getGetMethodMapByClass(clazz);
		Method method = methodMap.get("id");
		try {
			return update(sql.toString(), method.invoke(t));
		} catch (Exception e) {
			logger.error("", e);
		}
		return false;
	}

	/**
	 * 批量保存pojo对象
	 * @param tArray pojo对象
	 */
	@SuppressWarnings("unchecked")
	public final <T> void batchSave(T... tArray) {
		int count = 0;
		List<Object[]> batchArgs = new ArrayList<Object[]>(Math.max(tArray.length, 100));
		Map<String, Method> methodMap = null;
		StringBuilder sql = null;

		// 主键是否是id
		try {
			for (int j = 0; j < tArray.length; j++) {
				T t = tArray[j];
				Class<?> clazz = t.getClass();
				if (j == 0) {
					// 生成sql insert头
					sql = new StringBuilder();
					sql.append("replace into `");
					sql.append(classNameToTableName(clazz));
					sql.append("` (");
					methodMap = pojoProcessor.getGetMethodMapByClass(clazz);

					// 列出字段
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
				}

				// 传入参数
				Object[] args = new Object[count];
				int tmpCount = 0;
				for (Entry<String, Method> e : methodMap.entrySet()) {
					if ("id".equals(e.getKey())) {
						continue;
					}
					args[tmpCount] = e.getValue().invoke(t);
					tmpCount += 1;
				}

				batchArgs.add(args);
				if ((j + 1) % 100 == 0 && !batchArgs.isEmpty()) {
					batchUpdate(sql.toString(), batchArgs);
					batchArgs.clear();
				}
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		if (!batchArgs.isEmpty()) {
			batchUpdate(sql.toString(), batchArgs);
		}
	}

	/**
	 * 保存整个pojo对象
	 * @param t pojo对象
	 * @return -1 异常 
	 */
	public final <T> T save(T t) {
		// 主键是否是id
		boolean idIsPrimary = false;
		// 生成sql insert头
		Class<?> clazz = t.getClass();
		StringBuilder sql = new StringBuilder();
		sql.append("replace into `");
		sql.append(classNameToTableName(clazz));
		sql.append("` (");

		try {
			// 统计有多少个字段
			int count = 0;

			// 如果id有值，组sql的时候也要有id字段
			Map<String, Method> methodMap = pojoProcessor.getGetMethodMapByClass(clazz);
			long id = 0;
			Method method = methodMap.get("id");
			if (method != null) {
				id = StringUtil.getLong(method.invoke(t));
			}

			// 列出字段
			for (Entry<String, Method> e : methodMap.entrySet()) {
				String column = fieldNameToColumnName(e.getKey());//程序字段->数据库字段(adminPhoneId->admin_phone_id)
				if ("id".equals(column) && id <= 0) {
					idIsPrimary = true;
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
				if ("id".equals(e.getKey()) && id <= 0) {
					continue;
				}
				args[count] = e.getValue().invoke(t);
				count += 1;
			}

			// 如果id有值或者主键不是id，调用update
			if (!idIsPrimary || id > 0) {
				boolean success = update(sql.toString(), args);
				if (!success) {
					return null;
				}
				return t;
			}
			// 主键是id
			id = insert(sql.toString(), "id", args);
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
	 * 批量执行sql语句
	 * @param sql sql语句
	 * @return 每一条sql语句对应影响的行数
	 */
	public final int[] batchUpdate(String sql, List<Object[]> batchArgs) {
		try {
			return jdbc.batchUpdate(sql, batchArgs);
		} catch (DataAccessException e) {
			logger.error("batchUpdate sql:{}, error:{}", sql, SystemUtil.stackTrace2String(e));
			return null;
		}
	}

	/**
	 * 程序字段->数据库字段(adminPhoneId->admin_phone_id)
	 * @param fieldName 程序字段 
	 * @return columnName 数据库字段
	 */
	public final String fieldNameToColumnName(String fieldName) {
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

	/**
	 * pojo类型转化成表名
	 * @param clazz
	 */
	private final <T> String classNameToTableName(Class<T> clazz) {
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
		return tableName.toString().toLowerCase();
	}
}