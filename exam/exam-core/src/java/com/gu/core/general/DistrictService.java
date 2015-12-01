package com.gu.core.general;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;
import com.gu.core.util.FileSystem;
import com.gu.core.util.JSONObject;
import com.gu.core.util.StringUtil;

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
	private Map<Integer, Map<Integer, String>> cityMap = new HashMap<>();

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
			int provinceId = StringUtil.getInt(data.get("ProID"));
			Map<Integer, String> cityMap = this.cityMap.get(provinceId);
			if (cityMap == null) {
				cityMap = new HashMap<>();
				this.cityMap.put(provinceId, cityMap);
			}
			cityMap.put(StringUtil.getInt(data.get("CityID")), StringUtil.getString(data.get("name")));
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
		Map<Integer, String> cityMap = getCityMap(provinceId);
		if (cityMap == null) {
			return null;
		}
		return cityMap.get(cityId);
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
	public Map<Integer, Map<Integer, String>> getCityMap() {
		return cityMap;
	}

	/**
	 * 获取城市map
	 * @param provinceId 省份id
	 */
	public Map<Integer, String> getCityMap(int provinceId) {
		return cityMap.get(provinceId);
	}
}