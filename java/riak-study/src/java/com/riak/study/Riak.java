package com.riak.study;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.cap.Quorum;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.api.commands.kv.StoreValue.Option;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;

public class Riak {
	public static void main(String[] a) throws Exception {
		RiakClient client = RiakClient.newClient("192.168.190.129");

		Namespace ns = new Namespace("default", "my_bucket");
		Location location = new Location(ns, "my_key");
		
		// Getting Data In
		RiakObject riakObject = new RiakObject();
		riakObject.setValue(BinaryValue.create("_value"));
		StoreValue store = new StoreValue.Builder(riakObject).withLocation(location).withOption(Option.W, new Quorum(3)).build();
		client.execute(store);
		
		// Getting Data Out
		FetchValue fv = new FetchValue.Builder(location).build();
		FetchValue.Response response = client.execute(fv);
		RiakObject obj = response.getValue(RiakObject.class);
		System.err.println(obj.getValue());
		System.exit(0);
	}
}
