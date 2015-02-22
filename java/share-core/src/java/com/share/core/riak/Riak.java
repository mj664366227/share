package com.share.core.riak;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.Quorum;
import com.basho.riak.client.api.commands.buckets.ListBuckets;
import com.basho.riak.client.api.commands.datatypes.FetchMap;
import com.basho.riak.client.api.commands.datatypes.MapUpdate;
import com.basho.riak.client.api.commands.datatypes.RegisterUpdate;
import com.basho.riak.client.api.commands.datatypes.UpdateMap;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.ListKeys;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.StoreValue.Option;
import com.basho.riak.client.api.commands.kv.UpdateValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.util.BinaryValue;
import com.share.core.util.StringUtil;

/**
 * riak
 */
public class Riak {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(Riak.class);
	/**
	 * riak集群
	 */
	@Value("${riak.cluster}")
	private String cluster;
	/**
	 * riak客户端
	 */
	private RiakClient client;
	/**
	 * 集群数
	 */
	private int clusterNum;
	/**
	* kv
	*/
	public KV KV = new KV();
	/**
	 * buckets
	 */
	public Buckets BUCKETS = new Buckets();
	/**
	 * mapReduce
	 */
	public MapReduce MAPREDUCE = new MapReduce();

	/**
	 * 构造函数
	 */
	private Riak() {
	}

	/**
	 * 获取集群的地址 
	 */
	private InetSocketAddress[] getAddress() {
		String[] arr = cluster.split("\\|");
		clusterNum = arr.length;
		InetSocketAddress[] inetSocketAddress = new InetSocketAddress[clusterNum];
		for (int i = 0; i < clusterNum; i++) {
			String[] a = arr[i].split(":");
			InetSocketAddress socketAddress = new InetSocketAddress(StringUtil.getString(a[0]), StringUtil.getInt(a[1]));
			inetSocketAddress[i] = socketAddress;
		}
		return inetSocketAddress;
	}

	/**
	 * 初始化方法 
	 */
	public void init() {
		try {
			client = RiakClient.newClient(getAddress());
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	public class KV {
		private KV() {
		}

		/**
		* 保存数据
		* @param bucketName
		* @param key
		* @param value
		*/
		public void store(String bucketName, String key, Object value) {
			Namespace ns = new Namespace(bucketName, bucketName);
			Location location = new Location(ns, key);
			StoreValue.Builder store = new StoreValue.Builder(value);
			store.withLocation(location);
			store.withOption(Option.W, new Quorum(clusterNum));
			try {
				client.execute(store.build());
			} catch (Exception e) {
				logger.error("", e);
			}
		}

		/**
		 * 获取数据
		 * @param bucketName
		 * @param key
		 * @param clazz
		 */
		public <T> T fetch(String bucketName, String key, Class<T> clazz) {
			Namespace ns = new Namespace(bucketName);
			Location location = new Location(ns, key);
			FetchValue fv = new FetchValue.Builder(location).build();
			try {
				FetchValue.Response response = client.execute(fv);
				return response.getValue(clazz);
			} catch (Exception e) {
				logger.error("", e);
			}
			return null;
		}

		/**
		 * 删除数据
		 * @param bucketName
		 * @param key
		 */
		public void delete(String bucketName, String key) {
			Namespace ns = new Namespace(bucketName);
			Location location = new Location(ns, key);
			DeleteValue.Builder deleteValue = new DeleteValue.Builder(location);
			try {
				client.execute(deleteValue.build());
			} catch (Exception e) {
				logger.error("", e);
			}
		}

		/**
		 * 修改数据
		 * @param bucketName
		 * @param key
		 */
		public void update(String bucketName, String key) {
			Namespace ns = new Namespace(bucketName);
			Location location = new Location(ns, key);
			UpdateValue.Builder updateValue = new UpdateValue.Builder(location);
			try {
				client.execute(updateValue.build());
			} catch (Exception e) {
				logger.error("", e);
			}
		}

		/**
		 * 获取某个bucket下的所有key
		 * @param bucketName
		 */
		public List<Location> ListKeys(String bucketName) {
			try {
				List<Location> list = new ArrayList<Location>();
				Namespace namespace = new Namespace(bucketName);
				ListKeys.Builder listKeys = new ListKeys.Builder(namespace);
				ListKeys.Response response = client.execute(listKeys.build());
				Iterator<Location> it = response.iterator();
				while (it.hasNext()) {
					list.add(it.next());
				}
				return list;
			} catch (Exception e) {
				logger.error("", e);
			}
			return null;
		}
	}

	public class Buckets {
		private Buckets() {
		}

		/**
		 * 列出所有bucket
		 */
		public List<Namespace> listBuckets() {
			try {
				List<Namespace> list = new ArrayList<Namespace>();
				ListBuckets listBuckets = new ListBuckets.Builder(Namespace.DEFAULT_BUCKET_TYPE).build();
				ListBuckets.Response response = client.execute(listBuckets);
				Iterator<Namespace> it = response.iterator();
				while (it.hasNext()) {
					list.add(it.next());
				}
				return list;
			} catch (Exception e) {
				logger.error("", e);
			}
			return null;
		}
	}

	public class MapReduce {
		/**
		 * 更新map	
		 * @param bucketName
		 * @param key
		 * @param map
		 */
		public void updateMap(String bucketName, String key, Map<String, String> map) {
			Namespace ns = new Namespace(bucketName, bucketName);
			Location location = new Location(ns, key);

			MapUpdate mapUpdate = new MapUpdate();
			for (Entry<String, String> e : map.entrySet()) {
				RegisterUpdate registerUpdate = new RegisterUpdate(BinaryValue.create(e.getValue()));
				mapUpdate.update(e.getKey(), registerUpdate);
			}

			UpdateMap.Builder update = new UpdateMap.Builder(location, mapUpdate);
			try {
				client.execute(update.build());
			} catch (Exception e) {
				logger.error("", e);
			}
		}

		/**
		 * 查询map
		 * @param bucketName
		 * @param key
		 */
		public void FetchMap(String bucketName, String key) {
			Namespace ns = new Namespace(bucketName, bucketName);
			Location location = new Location(ns, key);
			FetchMap.Builder fetchMap = new FetchMap.Builder(location);
			try {
				FetchMap.Response response = client.execute(fetchMap.build());
				System.err.println(	response.getDatatype());
			} catch (Exception e) {
				logger.error("", e);
			}
		}
	}

	/**
	 * 关闭方法
	 */
	public void close() {
		client.shutdown();
		logger.info("riak cluster closed");
	}
}