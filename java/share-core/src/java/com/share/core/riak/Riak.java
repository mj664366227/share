package com.share.core.riak;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.Quorum;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.StoreValue.Option;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
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
	 * 构造函数
	 */
	private Riak() {
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

		Namespace ns = new Namespace("defaultaaaaa", "user");
		Location location = new Location(ns, "1");
		StoreValue store = new StoreValue.Builder("d").withLocation(location).withOption(Option.W, new Quorum(2)).build();
		try {
			client.execute(store);
		} catch (ExecutionException | InterruptedException e) {
			logger.error("", e);
		}
	}

	/**
	 * 获取集群的地址 
	 */
	private InetSocketAddress[] getAddress() {
		String[] arr = cluster.split("\\|");
		InetSocketAddress[] inetSocketAddress = new InetSocketAddress[arr.length];
		for (int i = 0; i < arr.length; i++) {
			String[] a = arr[i].split(":");
			InetSocketAddress socketAddress = new InetSocketAddress(StringUtil.getString(a[0]), StringUtil.getInt(a[1]));
			inetSocketAddress[i] = socketAddress;
		}
		return inetSocketAddress;
	}

	/**
	 * 关闭方法
	 */
	public void close() {
		client.shutdown();
		logger.info("riak cluster closed");
	}
}
/*
 * public static void main(String[] a) throws Exception { RiakClient client =
 * RiakClient.newClient("192.168.190.129");
 * 
 * Namespace ns = new Namespace("default", "user"); UserInfo userInfo = new
 * UserInfo(); userInfo.setName("ruanzhijun"); userInfo.setCity("guangzou");
 * userInfo.setUid("15152"); userInfo.setNickName("jun jun");
 * 
 * Location location = new Location(ns, "1");
 * 
 * // Getting Data In StoreValue store = new
 * StoreValue.Builder(userInfo).withLocation(location).withOption(Option.W, new
 * Quorum(2)).build(); client.execute(store);
 * 
 * // Getting Data Out FetchValue fv = new FetchValue.Builder(location).build();
 * FetchValue.Response response = client.execute(fv); UserInfo u =
 * response.getValue(UserInfo.class); System.err.println(u);
 * 
 * ListBuckets listBuckets = new ListBuckets.Builder("a").build();
 * ListBuckets.Response listBucketsresponse = client.execute(listBuckets);
 * Iterator<Namespace> it = listBucketsresponse.iterator(); while (it.hasNext())
 * { Namespace namespace = it.next();
 * System.err.println(namespace.getBucketNameAsString()); }
 * 
 * System.exit(0); }
 */