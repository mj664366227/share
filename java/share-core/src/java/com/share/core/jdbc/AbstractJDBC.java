package com.share.core.jdbc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import com.share.core.util.SortUtil.Order;

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
	 * @author ruan
	 * @param sql
	 * @param clazz
	 * @param args
	 */
	public final <T> List<T> queryList(String sql, Class<T> clazz, Object... args) {
		List<T> list = new ArrayList<T>();
		Map<String, Method> methodMap = pojoProcessor.getMethodMapByClass(clazz);
		if (methodMap == null) {
			logger.error("class {} methodMap is null", clazz.getName());
			return list;
		}
		try {
			SqlRowSet rs = jdbc.queryForRowSet(sql, args);
			while (rs.next()) {
				T t = clazz.newInstance();
				for (Entry<String, Method> e : methodMap.entrySet()) {
					e.getValue().invoke(t, rs.getObject(e.getKey()));
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
		Map<String, Method> methodMap = pojoProcessor.getMethodMapByClass(clazz);
		if (methodMap == null) {
			logger.error("class {} methodMap is null", clazz.getName());
			return null;
		}
		try {
			SqlRowSet rs = jdbc.queryForRowSet(sql, args);
			while (rs.next()) {
				T t = clazz.newInstance();
				for (Entry<String, Method> e : methodMap.entrySet()) {
					e.getValue().invoke(t, rs.getObject(e.getKey()));
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
			return jdbc.queryForObject(sql, args, Byte.class);
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
			return jdbc.queryForObject(sql, args, Short.class);
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
			return jdbc.queryForObject(sql, args, Integer.class);
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
			return jdbc.queryForObject(sql, args, Long.class);
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
			return jdbc.queryForObject(sql, args, Float.class);
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
			return jdbc.queryForObject(sql, args, Double.class);
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
			return jdbc.queryForObject(sql, args, String.class);
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
	 * 插入数据,返回自增id
	 * @param sql  
	 * @param key 主键字段名
	 * @param args	参数列表
	 * @return -1-异常 
	 */
	public final long insert(final String sql, final String key, final Object... args) {
		long result = -1L;
		KeyHolder keyHolder = new GeneratedKeyHolder();
		PreparedStatementCreator psc = new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql, new String[] { key });
				if (args != null) {
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
			logger.error("batchUpdate sql:{}, error:{}", sql, e);
			return null;
		}
	}

	/**
	 * mysql查询类
	 * @author ruan
	 *
	 */
	public class Query {
		/**
		 * SQL语句
		 */
		private StringBuilder sql = new StringBuilder();

		/**
		 * select
		 * @author ruan
		 * @param table 表名
		 * @param column 要选择的字段(不填相当于select * from xx)
		 * @return
		 */
		public final Query select(String table, String... column) {
			if (column.length > 0) {
				sql.append("select ");
				for (String tmp : column) {
					sql.append(tmp);
					sql.append(",");
				}
				int sqlLen = sql.length();
				sql.delete(sqlLen - 1, sqlLen);
			} else {
				sql.append("select * ");
			}
			sql.append(" from ");
			sql.append(table);
			return this;
		}

		/**
		 * 去重select
		 * @author ruan
		 * @param table 表名
		 * @param column 要选择的字段(不填相当于select distinct * from xx)
		 * @return
		 */
		public final Query selectDistinct(String table, String... column) {
			if (column.length > 0) {
				sql.append("select distinct ");
				for (String tmp : column) {
					sql.append(tmp);
					sql.append(",");
				}
				int sqlLen = sql.length();
				sql.delete(sqlLen - 1, sqlLen);
			} else {
				sql.append("select distinct * ");
			}
			sql.append(" from ");
			sql.append(table);
			return this;
		}

		/**
		 * 插入
		 * 
		 * @author ruan
		 * @param table 表名
		 * @param data 要插入的数据
		 * @return
		 */
		public final Query insert(String table, HashMap<String, Object> data) {
			sql.append("insert into ");
			sql.append(table);
			sql.append(" (");
			sql.append(getKeys(data.keySet()));
			sql.append(") values (");
			sql.append(getValues(data.values()));
			sql.append(")");
			return this;
		}

		/**
		 * 批量插入
		 * 
		 * @author ruan
		 * @param table 表名
		 * @param list 要插入的数据列表
		 * @return
		 */
		public final Query insertBatch(String table, ArrayList<HashMap<String, Object>> list) {
			sql.append("insert into ");
			sql.append(table);
			sql.append(" (");
			sql.append(getKeys(list.get(0).keySet()));
			sql.append(") values ");
			for (HashMap<String, Object> _data : list) {
				sql.append("(");
				sql.append(getValues(_data.values()));
				sql.append("),");
			}
			int sqlLen = sql.length();
			sql.delete(sqlLen - 1, sqlLen);
			return this;
		}

		/**
		 * 替换
		 * 
		 * @author ruan
		 * @param table 表名
		 * @param data 要替换的数据
		 * @return
		 */
		public final Query replace(String table, HashMap<String, Object> data) {
			sql.append("replace into ");
			sql.append(table);
			sql.append(" (");
			sql.append(getKeys(data.keySet()));
			sql.append(") values (");
			sql.append(getValues(data.values()));
			sql.append(")");
			return this;
		}

		/**
		 * 批量替换
		 * 
		 * @author ruan
		 * @param table 表名
		 * @param list 要替换的数据列表
		 * @return
		 */
		public final Query replaceBatch(String table, ArrayList<HashMap<String, Object>> list) {
			sql.append("replace into ");
			sql.append(table);
			sql.append(" (");
			sql.append(getKeys(list.get(0).keySet()));
			sql.append(") values ");
			for (HashMap<String, Object> _data : list) {
				sql.append("(");
				sql.append(getValues(_data.values()));
				sql.append("),");
			}
			int sqlLen = sql.length();
			sql.delete(sqlLen - 1, sqlLen);
			return this;
		}

		/**
		 * 删除
		 * 
		 * @author ruan
		 * @param table 表名
		 * @return
		 */
		public final Query delete(String table) {
			sql.append("delete from ");
			sql.append(table);
			return this;
		}

		/**
		 * 更新数据
		 * 
		 * @author ruan 
		 * @param table 表名
		 * @param data 要更新的数据
		 * @return
		 */
		public final Query update(String table, HashMap<String, Object> data) {
			sql.append("update ");
			sql.append(table);
			sql.append(" set ");
			for (Entry<String, Object> e : data.entrySet()) {
				sql.append(e.getKey());
				sql.append("='");
				sql.append(e.getValue());
				sql.append("',");
			}
			int sqlLen = sql.length();
			sql.delete(sqlLen - 1, sqlLen);
			return this;
		}

		/**
		 * 获取所有的key
		 * 
		 * @author ruan
		 * @param keySet
		 * @return
		 */
		private final String getKeys(Set<String> keySet) {
			StringBuilder keys = new StringBuilder();
			for (String key : keySet) {
				keys.append(key);
				keys.append(",");
			}
			keys.trimToSize();
			return keys.substring(0, keys.length() - 1);

		}

		/**
		 * 获取所有的values
		 * 
		 * @author ruan
		 * @param values
		 * @return
		 */
		private final String getValues(Collection<Object> value) {
			StringBuilder values = new StringBuilder();
			values.append("'");
			for (Object key : value) {
				values.append(key + "','");
			}
			values.trimToSize();
			return values.substring(0, values.length() - 2);
		}

		/**
		 * where
		 * 
		 * @author ruan
		 * @param key 键
		 * @return this
		 */
		public final Query where(Object key) {
			sql.append(" where ");
			sql.append(key);
			sql.append(" ");
			return this;
		}

		/**
		 * and where
		 * 
		 * @author ruan
		 * @param key 键
		 * @return this
		 */
		public final Query andWhere(Object key) {
			sql.append(" and where ");
			sql.append(key);
			sql.append(" ");
			return this;
		}

		/**
		 * or where
		 * 
		 * @author ruan
		 * @param key 键
		 * @return this
		 */
		public final Query orWhere(Object key) {
			sql.append(" or where ");
			sql.append(key);
			sql.append(" ");
			return this;
		}

		/**
		 * 等于
		 * 
		 * @author ruan
		 * @param value 值
		 * @return this
		 */
		public final Query is(Object value) {
			sql.append(" = '");
			sql.append(value);
			sql.append("'");
			return this;
		}

		/**
		 * 不等于
		 * 
		 * @author ruan
		 * @param value 值
		 * @return this
		 */
		public final Query isNot(Object value) {
			sql.append(" != '");
			sql.append(value);
			sql.append("'");
			return this;
		}

		/**
		 * 小于
		 * 
		 * @author ruan
		 * @param value 值
		 * @return this
		 */
		public final Query lt(Object value) {
			sql.append(" < '");
			sql.append(value);
			sql.append("'");
			return this;
		}

		/**
		 * 小于等于
		 * 
		 * @author ruan
		 * @param value 值
		 * @return this
		 */
		public final Query lte(Object value) {
			sql.append(" <= '");
			sql.append(value);
			sql.append("'");
			return this;
		}

		/**
		 * 大于
		 * 
		 * @author ruan
		 * @param value 值
		 * @return this
		 */
		public final Query gt(Object value) {
			sql.append(" > '");
			sql.append(value);
			sql.append("'");
			return this;
		}

		/**
		 * 大于等于
		 * 
		 * @author ruan
		 * @param value 值
		 * @return this
		 */
		public final Query gte(Object value) {
			sql.append(" >= '");
			sql.append(value);
			sql.append("'");
			return this;
		}

		/**
		 * and
		 * 
		 * @author ruan
		 * @param key 键
		 * @return this
		 */
		public final Query and(String key) {
			sql.append(" and ");
			sql.append(key);
			return this;
		}

		/**
		 * or
		 * 
		 * @author ruan
		 * @param key 键
		 * @return this
		 */
		public final Query or(String key) {
			sql.append(" or ");
			sql.append(key);
			return this;
		}

		/**
		 * limit
		 * 
		 * @author ruan
		 * @param x
		 * @param y
		 * @return
		 */
		public final Query limit(int x, int y) {
			sql.append(" limit ");
			sql.append(x);
			sql.append(" , ");
			sql.append(y);
			return this;
		}

		/**
		 * limit
		 * 
		 * @author ruan
		 * @param x
		 * @return
		 */
		public final Query limit(int x) {
			sql.append(" limit ");
			sql.append(x);
			return this;
		}

		/**
		 * 给字段排序
		 * 
		 * @author vilinian
		 * @param columns
		 * @return
		 */
		public final Query orderBy(HashMap<String, Order> columns) {
			sql.append(" order by ");
			for (Entry<String, Order> elem : columns.entrySet()) {
				sql.append(elem.getKey());
				sql.append(" ");
				sql.append(elem.getValue().toString());
				sql.append(",");
			}
			int sqlLen = sql.length();
			sql.delete(sqlLen - 1, sqlLen);
			return this;
		}

		/**
		 * 分组
		 * 
		 * @author vilinian 
		 * @param column
		 * @return
		 */
		public final Query groupBy(String... column) {
			if (column.length <= 0) {
				return this;
			}
			sql.append(" group by ");
			for (String tmp : column) {
				sql.append(tmp);
				sql.append(",");
			}
			int sqlLen = sql.length();
			sql.delete(sqlLen - 1, sqlLen);

			return this;
		}

		/**
		 * 在集合内
		 * @author ruan
		 * @param column
		 * @return
		 */
		public final <T> Query in(T[] column) {
			sql.append(" in('");
			for (T s : column) {
				sql.append(s);
				sql.append("','");
			}
			if (column.length > 0) {
				int sqlLen = sql.length();
				sql.delete(sqlLen - 2, sqlLen);
			} else {
				sql.append("'");
			}
			sql.append(") ");
			return this;
		}

		/**
		 * 在集合内
		 * @author ruan
		 * @param column
		 * @return
		 */
		public final <T> Query in(Collection<T> column) {
			sql.append(" in('");
			for (T s : column) {
				sql.append(s);
				sql.append("','");
			}
			if (column.size() > 0) {
				int sqlLen = sql.length();
				sql.delete(sqlLen - 2, sqlLen);
			} else {
				sql.append("'");
			}
			sql.append(") ");
			return this;
		}

		/**
		 * 不在集合内
		 * @author ruan
		 * @param column
		 * @return
		 */
		public final <T> Query notIn(T[] column) {
			sql.append(" not in('");
			for (T s : column) {
				sql.append(s);
				sql.append("','");
			}
			if (column.length > 0) {
				int sqlLen = sql.length();
				sql.delete(sqlLen - 2, sqlLen);
			} else {
				sql.append("'");
			}
			sql.append(") ");
			return this;
		}

		/**
		 * 不在集合内
		 * @author ruan
		 * @param column
		 * @return
		 */
		public final <T> Query notIn(Collection<T> column) {
			sql.append(" not in('");
			for (T s : column) {
				sql.append(s);
				sql.append("','");
			}
			if (column.size() > 0) {
				int sqlLen = sql.length();
				sql.delete(sqlLen - 2, sqlLen);
			} else {
				sql.append("'");
			}
			sql.append(") ");
			return this;
		}

		/**
		 * 左连接
		 * @author ruan
		 * @param tableName 要连接的表(支持n个表)
		 * @return
		 */
		public final Query leftJoin(String... tableName) {
			sql.append(" left join (");
			for (String table : tableName) {
				sql.append(table);
				sql.append(",");
			}
			int sqlLen = sql.length();
			sql.delete(sqlLen - 1, sqlLen);
			sql.append(") ");
			return this;
		}

		/**
		 * 右连接
		 * @author ruan
		 * @param tableName 要连接的表(支持n个表)
		 * @return
		 */
		public final Query rightJoin(String... tableName) {
			sql.append(" right join (");
			for (String table : tableName) {
				sql.append(table);
				sql.append(",");
			}
			int sqlLen = sql.length();
			sql.delete(sqlLen - 1, sqlLen);
			sql.append(") ");
			return this;
		}

		/**
		 * 内连接
		 * @author ruan
		 * @param tableName 要连接的表(支持n个表)
		 * @return
		 */
		public final Query innerJoin(String... tableName) {
			sql.append(" inner join (");
			for (String table : tableName) {
				sql.append(table);
				sql.append(",");
			}
			int sqlLen = sql.length();
			sql.delete(sqlLen - 1, sqlLen);
			sql.append(") ");
			return this;
		}

		/**
		 * 联表后的条件
		 * @author ruan
		 * @param condition 条件
		 * @return
		 */
		public final Query on(String condition) {
			sql.append(" on ");
			sql.append(condition);
			return this;
		}

		/**
		 * toString方法
		 */
		public String toString() {
			return sql.toString();
		}

		/**
		 * clear
		 * @author ruan
		 */
		public final void clear() {
			sql.delete(0, sql.length());
		}
	}
}