package com.share.test;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.IRiakObject;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.bucket.Bucket;

public class Test {
	public static void main(String[] args) throws Exception {
		// create a client (see Configuration below in this README for more details)
		IRiakClient riakClient = RiakFactory.pbcClient("192.168.23.128",8098); //or RiakFactory.httpClient();
		 
		// create a new bucket
		Bucket myBucket = riakClient.createBucket("myBucket").execute();
		 
		// add data to the bucket
		myBucket.store("key1", "value1").execute();
		 
		//fetch it back
		IRiakObject myData = myBucket.fetch("key1").execute();
		 
		// you can specify extra parameters to the store operation using the
		// fluent builder style API
		myData = myBucket.store("key1", "value2").returnBody(true).execute();
		 
		// delete
		myBucket.delete("key1").rw(3).execute();
	}
}