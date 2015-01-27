package com.share.test.http.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.share.core.redis.Redis;
import com.share.core.util.HttpServerUtil;
import com.share.core.util.JSONObject;
import com.share.core.util.Time;
import com.share.test.db.AdminDbService;
import com.share.test.protocol.ReqDemo;

@Controller
public class DemoController {
	@Autowired
	private AdminDbService jdbc;

	@Autowired
	private Redis redis;

	@RequestMapping(value = "/demo/test", method = RequestMethod.GET)
	public void action(PrintWriter printWriter, JSONObject json) {
		printWriter.write("/demo/test");
		printWriter.flush();
		printWriter.close();
		//System.err.println(redis.SERVER.info());
	}

	@RequestMapping(value = "/demo/test2", method = RequestMethod.GET)
	public void test2(PrintWriter printWriter) {
		long t = System.nanoTime();
		printWriter.write("/demo/test2");
		printWriter.flush();
		printWriter.close();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("/demo/test2");
		System.err.println(Time.showTime(System.nanoTime() - t));
	}

	@RequestMapping(value = "/demo/fuck", method = RequestMethod.GET)
	public ModelAndView fuck() {
		ModelAndView model = new ModelAndView("demo/fuck");
		model.addObject("a", "我在人民广场吃炸鸡");
		model.addObject("test", true);
		return model;
	}

	@RequestMapping(value = "/demo/post", method = RequestMethod.GET)
	@ResponseBody
	public byte[] post(HttpServletRequest request) throws IOException {
		ReqDemo reqDemo = new ReqDemo();
		reqDemo.setEmail("ruan@j.com");
		reqDemo.setName("阮志军");
		ByteBuf buffer = Unpooled.buffer();
		reqDemo.convert2Buffer(buffer);
		byte[] dst = new byte[buffer.readableBytes()];
		buffer.readBytes(dst);
		return dst;
	}

	@RequestMapping(value = "/demo/download", method = RequestMethod.GET)
	private void downloadFile(HttpServletResponse response) {
		HttpServerUtil.downloadFile(new File("F:\\^m^p^3^\\阿肆\\阿肆-缺乏.mp3"), response);
	}

	@RequestMapping(value = "/ajax", method = RequestMethod.GET)
	public byte[] response() {
		JSONObject json = new JSONObject();
		json.put("a", 1);
		json.put("b", "中文测试");
		json.put("thread", Thread.currentThread().getId());
		return json.toString().getBytes();
	}

	@RequestMapping(value = "/demo/data", method = RequestMethod.POST)
	@ResponseBody
	public String data(ReqDemo reqDemo) throws IOException {
		return reqDemo.toString();
	}
}
