package com.share.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.reflect.TypeToken;

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
	 * 省份文件路径
	 */
	private final String provincePath = StringUtil.getString(getClass().getClassLoader().getResource("province.json")).replace("file:/", "");
	/**
	 * 城市文件路径
	 */
	private final String cityPath = StringUtil.getString(getClass().getClassLoader().getResource("city.json")).replace("file:/", "");
	/**
	 * 区县文件路径
	 */
	private final String areaPath = StringUtil.getString(getClass().getClassLoader().getResource("area.json")).replace("file:/", "");
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
		ArrayList<HashMap<String, Object>> provinceList = JSONObject.decode(FileSystem.read(provincePath), new TypeToken<ArrayList<HashMap<String, Object>>>() {
		}.getType());
		if (provinceList == null || provinceList.isEmpty()) {
			return;
		}
		logger.warn("load province config: {}", provincePath);
		for (HashMap<String, Object> data : provinceList) {
			provinceMap.put(StringUtil.getInt(data.get("ProID")), StringUtil.getString(data.get("name")));
		}
	}

	/**
	 * 初始化城市
	 */
	private void initCity() {
		ArrayList<HashMap<String, Object>> cityList = JSONObject.decode(FileSystem.read(cityPath), new TypeToken<ArrayList<HashMap<String, Object>>>() {
		}.getType());
		if (cityList == null || cityList.isEmpty()) {
			return;
		}
		logger.warn("load city config: {}", cityPath);
		for (HashMap<String, Object> data : cityList) {
			cityMap.put(StringUtil.getInt(data.get("ProID")) + "," + StringUtil.getInt(data.get("CityID")), StringUtil.getString(data.get("name")));
		}
	}

	/**
	 * 初始化区县
	 */
	private void initArea() {
		ArrayList<HashMap<String, Object>> areaList = JSONObject.decode(FileSystem.read(areaPath), new TypeToken<ArrayList<HashMap<String, Object>>>() {
		}.getType());
		if (areaList == null || areaList.isEmpty()) {
			return;
		}
		logger.warn("load area config: {}", areaPath);
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
}