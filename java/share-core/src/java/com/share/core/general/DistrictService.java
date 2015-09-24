package com.share.core.general;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.share.core.util.FileSystem;
import com.share.core.util.JSONObject;
import com.share.core.util.StringUtil;

/**
 * 地区配置服务
 * @author ruan
 */
@Component
public class DistrictService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(DistrictService.class);
	/**
	 * 省份map
	 */
	private Map<Integer, String> provinceMap = new HashMap<>();
	/**
	 * 城市map
	 */
	private Map<String, String> cityMap = new HashMap<>();
	/**
	 * 区县map
	 */
	private Map<String, String> areaMap = new HashMap<>();

	/**
	 * 私有构造函数()只有spring才可以实例化
	 */
	private DistrictService() {
	}

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		// 初始化省份
		initProvince();

		// 初始化城市
		initCity();

		// 初始化区县
		initArea();
	}

	/**
	 * 初始化省份
	 */
	private void initProvince() {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("province.json");
		if (inputStream == null) {
			return;
		}
		ArrayList<HashMap<String, Object>> provinceList = JSONObject.decode(FileSystem.read(inputStream), new TypeToken<ArrayList<HashMap<String, Object>>>() {
		}.getType());
		if (provinceList == null || provinceList.isEmpty()) {
			return;
		}
		logger.warn("load province config: province.json");
		for (HashMap<String, Object> data : provinceList) {
			provinceMap.put(StringUtil.getInt(data.get("ProID")), StringUtil.getString(data.get("name")));
		}
	}

	/**
	 * 初始化城市
	 */
	private void initCity() {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("city.json");
		if (inputStream == null) {
			return;
		}
		ArrayList<HashMap<String, Object>> cityList = JSONObject.decode(FileSystem.read(inputStream), new TypeToken<ArrayList<HashMap<String, Object>>>() {
		}.getType());
		if (cityList == null || cityList.isEmpty()) {
			return;
		}
		logger.warn("load city config: city.json");
		for (HashMap<String, Object> data : cityList) {
			cityMap.put(StringUtil.getInt(data.get("ProID")) + "," + StringUtil.getInt(data.get("CityID")), StringUtil.getString(data.get("name")));
		}
	}

	/**
	 * 初始化区县
	 */
	private void initArea() {
		InputStream inputStream = ClassLoader.getSystemResourceAsStream("area.json");
		if (inputStream == null) {
			return;
		}
		ArrayList<HashMap<String, Object>> areaList = JSONObject.decode(FileSystem.read(inputStream), new TypeToken<ArrayList<HashMap<String, Object>>>() {
		}.getType());
		if (areaList == null || areaList.isEmpty()) {
			return;
		}
		logger.warn("load area config: area.json");
		for (HashMap<String, Object> data : areaList) {
			areaMap.put(StringUtil.getInt(data.get("CityID")) + "," + StringUtil.getInt(data.get("Id")), StringUtil.getString(data.get("DisName")));
		}
	}

	/**
	 * 获取省份
	 * @param provinceId 省份id
	 * @return 成功可以获取省份，否则返回null
	 */
	public String getProvince(int provinceId) {
		return provinceMap.get(provinceId);
	}

	/**
	 * 获取城市
	 * @param provinceId 省份id
	 * @param cityId 城市id
	 * @return 成功可以获取城市，否则返回null
	 */
	public String getCity(int provinceId, int cityId) {
		return cityMap.get(provinceId + "," + cityId);
	}

	/**
	 * 获取区县
	 * @param cityId 城市id
	 * @param areaId 区县id
	 * @return 成功可以获取区县，否则返回null
	 */
	public String getArea(int cityId, int areaId) {
		return areaMap.get(cityId + "," + areaId);
	}

	/**
	 * 获取省份map
	 */
	public Map<Integer, String> getProvinceMap() {
		return provinceMap;
	}

	/**
	 * 获取城市map
	 */
	public Map<String, String> getCityMap() {
		return cityMap;
	}

	/**
	 * 获取区县map
	 */
	public Map<String, String> getAreaMap() {
		return areaMap;
	}
}