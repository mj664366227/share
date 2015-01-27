package com.share.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用排序类
 */
public final class SortUtil {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(SortUtil.class);
	/**
	 * 装载已经用过的规则 实现类似单例模式
	 */
	private static Map<String, SortUtil> sortMap = new HashMap<String, SortUtil>();
	/**
	 * 方法名数组
	 */
	private Method[] methodArr = null;
	/**
	 * 正序、反序
	 */
	private int[] typeArr = null;
	/**
	 * 排序方式
	 */
	private Order order = null;

	/**
	 * 构造函数 并保存该规则
	 * 
	 * @param clazz
	 * @param args
	 */
	private <T> SortUtil(Class<T> clazz, String... args) {
		methodArr = new Method[args.length];
		typeArr = new int[args.length];
		try {
			for (int i = 0; i < args.length; i++) {
				String key = args[i].split("#")[0];
				methodArr[i] = clazz.getMethod(key, new Class[] {});
				typeArr[i] = Integer.valueOf(args[i].split("#")[1]);
			}
		} catch (IllegalArgumentException | NoSuchMethodException | SecurityException e) {
			logger.error("", e);
		}
	}

	/**
	 * 构造函数 ，设置排序规则
	 * @param order
	 */
	private <T> SortUtil(Order order) {
		this.order = order;
	}

	/**
	 * 对象排序规则
	 */
	private final Comparator<Object> comparatorObject = new Comparator<Object>() {
		@Override
		public int compare(Object o1, Object o2) {
			int result = 0;
			try {
				for (int i = 0; i < methodArr.length; i++) {
					Object value1 = methodArr[i].invoke(o1);
					Object value2 = methodArr[i].invoke(o2);
					if (value1 instanceof Integer) {
						int int1 = Integer.parseInt(value1.toString());
						int int2 = Integer.parseInt(value2.toString());
						if (int1 > int2) {
							result = 1;
						} else if (int1 < int2) {
							result = -1;
						}
					} else if (value1 instanceof Boolean) {
						boolean boolean1 = Boolean.parseBoolean(value1.toString());
						boolean boolean2 = Boolean.parseBoolean(value2.toString());
						if (boolean1 && !boolean2) {
							result = 1;
						} else if (!boolean1 && boolean2) {
							result = -1;
						}
					} else if (value1 instanceof Double) {
						double double1 = Double.parseDouble(value1.toString());
						double double2 = Double.parseDouble(value2.toString());
						if (double1 > double2) {
							result = 1;
						} else if (double1 < double2) {
							result = -1;
						}
					} else if (value1 instanceof Float) {
						float float1 = Float.parseFloat(value1.toString());
						float float2 = Float.parseFloat(value2.toString());
						if (float1 > float2) {
							result = 1;
						} else if (float1 < float2) {
							result = -1;
						}
					} else if (value1 instanceof Long) {
						long long1 = Long.parseLong(value1.toString());
						long long2 = Long.parseLong(value2.toString());
						if (long1 > long2) {
							result = 1;
						} else if (long1 < long2) {
							result = -1;
						}
					} else if (value1 instanceof Short) {
						short short1 = Short.parseShort(value1.toString());
						short short2 = Short.parseShort(value2.toString());
						if (short1 > short2) {
							result = 1;
						} else if (short1 < short2) {
							result = -1;
						}
					} else if (value1 instanceof Byte) {
						byte byte1 = Byte.parseByte(value1.toString());
						byte byte2 = Byte.parseByte(value2.toString());
						if (byte1 > byte2) {
							result = 1;
						} else if (byte1 < byte2) {
							result = -1;
						}
					} else {
						int rs = value1.toString().compareToIgnoreCase(value2.toString());
						if (rs > 0) {
							result = 1;
						} else if (rs < 0) {
							result = -1;
						}
					}
					if (result == 0) {
						continue;
					}
					return typeArr[i] == 1 ? result : -result;
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				logger.error("", e);
			}
			return result;
		}
	};

	/**
	 * 非对象排序规则
	 */
	private final Comparator<Object> comparatorValue = new Comparator<Object>() {
		@Override
		public int compare(Object o1, Object o2) {
			int result = 0;
			if (o1 instanceof Integer) {
				result = Integer.parseInt(o1.toString()) >= Integer.parseInt(o2.toString()) ? 1 : -1;
			} else if (o1 instanceof Boolean) {
				boolean boolean1 = Boolean.parseBoolean(o1.toString());
				boolean boolean2 = Boolean.parseBoolean(o2.toString());
				if (boolean1 && !boolean2) {
					result = 1;
				} else if (!boolean1 && boolean2) {
					result = -1;
				}
			} else if (o1 instanceof Double) {
				result = Double.parseDouble(o1.toString()) >= Double.parseDouble(o2.toString()) ? 1 : -1;
			} else if (o1 instanceof Float) {
				result = Float.parseFloat(o1.toString()) >= Float.parseFloat(o2.toString()) ? 1 : -1;
			} else if (o1 instanceof Long) {
				result = Long.parseLong(o1.toString()) >= Long.parseLong(o2.toString()) ? 1 : -1;
			} else if (o1 instanceof Short) {
				result = Short.parseShort(o1.toString()) >= Short.parseShort(o2.toString()) ? 1 : -1;
			} else if (o1 instanceof Byte) {
				result = Byte.parseByte(o1.toString()) >= Byte.parseByte(o2.toString()) ? 1 : -1;
			} else {
				result = o1.toString().compareToIgnoreCase(o2.toString()) >= 0 ? 1 : -1;
			}
			return order.equals(Order.ASC) ? result : -result;
		}
	};

	/**
	 * 获取排序规则
	 * 
	 * @return SortUtil
	 */
	private final static <T> SortUtil getSort(Class<T> clazz, String... args) {
		String key = clazz.getName() + Arrays.toString(args);
		SortUtil sort = sortMap.get(key);
		if (sort == null) {
			sort = new SortUtil(clazz, args);
			sortMap.put(key, sort);
		}
		return sort;
	}

	/**
	 * 获取排序规则
	 * @author ruan
	 * @param clazz
	 * @param order
	 * @return
	 */
	private final static <T> SortUtil getSort(Class<T> clazz, Order order) {
		String key = clazz.getName() + order;
		SortUtil sort = sortMap.get(key);
		if (sort == null) {
			sort = new SortUtil(order);
			sortMap.put(key, sort);
		}
		return sort;
	}

	/**
	 * 对对象数组进行排序
	 * <pre>
	 * 首先会在容器中，根据class+规则去找。如果没有见则new 
	 * 调用方式 SortUtil.sort(list,"方法名#升序(1)/降序(-1)","..","..")
	 * 后面字符串参数：比如："getMark#1","getAge#-1"
	 * 表示先按照getMark的值按照升序排，如果相等再按照getAge的降序排
	 * 如果返回值是true类型，若按照true先排："isOnline#1" ,若按照false先排："isOnline#-1"
	 * </pre>
	 * 
	 * @param list
	 * @param args
	 */
	public final static <T> void sort(List<T> list, String... args) {
		SortUtil sort = getSort(list.get(0).getClass(), args);
		Collections.sort(list, sort.comparatorObject);
	}

	/**
	 * 对非对象数组进行排序(多用于数值型)
	 * @author ruan
	 * @param list 数组
	 * @param order 排序方式
	 */
	public final static <T> void sort(List<T> list, Order order) {
		SortUtil sort = getSort(list.get(0).getClass(), order);
		Collections.sort(list, sort.comparatorValue);
	}

	/**
	 * 给Map进行排序 对map的value进行排序(对象)
	 * 
	 * @param map 被排序的map
	 * @param args 排序方法条件：方法名x#1升序-1倒序, 方法名y#-1倒序
	 * @return List<T>
	 */
	public final static <K, V> List<V> sortMap(Map<K, V> map, String... args) {
		List<V> list = new ArrayList<V>();
		if (map == null || map.isEmpty()) {
			return list;
		}
		list.addAll(map.values());
		sort(list, args);
		return list;
	}

	/**
	 * 给Map进行排序 对map的value进行排序(非对象)
	 * 
	 * @param map 被排序的map
	 * @param args 排序方法条件：方法名x#1升序-1倒序, 方法名y#-1倒序
	 * @return List<T>
	 */
	public final static <K, V> List<V> sortMap(Map<K, V> map, Order order) {
		List<V> list = new ArrayList<V>();
		if (map == null || map.isEmpty()) {
			return list;
		}
		list.addAll(map.values());
		sort(list, order);
		return list;
	}

	/**
	 * 给map的key排序
	 * @author ruan
	 * @param map 被排序的map
	 * @param order 排序方式
	 * @return
	 */
	public final static <K, V> List<K> sortMapKey(Map<K, V> map, Order order) {
		ArrayList<K> list = new ArrayList<K>();
		for (K key : map.keySet()) {
			list.add(key);
		}
		sort(list, order);
		return list;
	}

	/**
	 * 排序方式
	 * 
	 * @author ruan
	 * 
	 */
	public enum Order {
		/**
		 * 升序
		 */
		ASC,
		/**
		 * 反序
		 */
		DESC;
	}
}