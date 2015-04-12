package com.share.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import com.share.core.util.SortUtil.Order;

/**
 * 文件系统
 */
public final class FileSystem {
	private final static ClassLoader classLoader = FileSystem.class.getClassLoader();
	private final static String systemDir = classLoader.getResource("").toString() + StringUtil.getString(System.getProperty("project")) + "/";
	private final static Logger logger = LoggerFactory.getLogger(FileSystem.class);
	private final static String[] sizes = new String[] { "Byte", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };
	private final static DecimalFormat decimalFormat = new DecimalFormat("0.00");
	private final static boolean isWindows = System.getProperty("os.name").indexOf("Windows") != -1;
	private static Properties property = new Properties();
	static {
		loadProperties();
	}

	private FileSystem() {
	}

	/**
	 * 获取系统根目录
	 */
	public final static String getSystemDir() {
		return systemDir;
	}

	/**
	 * 判断是否为windows
	 * @return
	 */
	public final static boolean isWindows() {
		return isWindows;
	}

	/**
	 * 列出指定文件夹的内容
	 * @author ruan
	 * @param dir 目录路径
	 * @return
	 */
	public final static String[] ls(String dir) {
		File file = new File(dir);
		if (file.exists()) {
			return file.list();
		}
		return new String[0];
	}

	/**
	 * 列出指定文件夹内所有的jar文件
	 * @author ruan
	 * @param dir 目录路径
	 * @return jar文件全路径数组
	 */
	public final static List<String> lsJAR(String dir) {
		List<String> jarList = new ArrayList<String>();
		lsJAR(dir, jarList);
		return jarList;
	}

	/**
	 * 列出指定文件夹内所有的jar文件
	 * @author ruan
	 * @param dir 目录路径
	 * @param jar文件全路径数组
	 */
	private final static void lsJAR(String dir, List<String> jarList) {
		for (String file : ls(dir)) {
			File jar = new File(dir + "/" + file);
			if (jar.isDirectory()) {
				lsJAR(jar.getAbsolutePath(), jarList);
			} else {
				String absolutePath = jar.getAbsolutePath();
				if (!absolutePath.endsWith(".jar")) {
					continue;
				}
				jarList.add(absolutePath);
			}
		}
	}

	/**
	 * 写入文件(完全自定义文件格式和写入内容)
	 * 
	 * @param filename 文件的完整路径
	 * @param data 要写入的数据
	 * @param flag 是否追加(true追加，false不追加)
	 * @return boolean
	 */
	public final static boolean write(String filename, String data, boolean flag) {
		int i = filename.lastIndexOf("/");
		File file = new File(filename.substring(0, i));
		if (!file.exists()) {
			file.mkdirs();
		}
		try {
			FileWriter fw = new FileWriter(filename, flag);
			fw.write(data);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 删除某个文件或目录
	 * 
	 * @param filename 文件名或目录
	 * @return 0：删除成功，-1：指定文件夹不存在，-2：指定文件不存在
	 */
	public final static int delete(String filename) {
		File file = new File(filename);
		if (file.isDirectory()) {
			String[] dir = ls(filename);
			for (int i = 0; i < dir.length; i++) {
				new File(filename + "/" + dir[i]).delete();
				if (file.isDirectory()) {
					delete(filename + "/" + dir[i]);
				}
			}
			if (file.delete()) {
				return 0;
			} else {
				return -1;
			}
		} else if (file.isFile()) {
			if (file.delete()) {
				return 0;
			} else {
				return -2;
			}
		} else {
			return -1;
		}
	}

	/**
	 * 读取文件类容
	 * @author ruan
	 * @param filename 文件的完整路径
	 * @param isCreateNewFile 如果文件不存在，是否新建一个？如果否，则报错
	 * @return
	 */
	public final static String read(String filename, boolean isCreateNewFile) {
		try {
			String text = null;
			StringBuilder sb = new StringBuilder();
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			while ((text = input.readLine()) != null) {
				sb.append(text);
				sb.append("\n");
			}
			input.close();
			return sb.toString().trim();
		} catch (IOException e) {
			if (isCreateNewFile) {
				write(filename, "", false);
			} else {
				logger.error("", e);
			}
		}
		return "";
	}

	/**
	 * 读取文件类容
	 * @author ruan
	 * @param filename 文件的完整路径
	 * @return
	 */
	public final static String read(String filename) {
		return read(filename, false);
	}

	/**
	 * 读取jar包内指定类型的文件
	 * @param jarFileName jar文件路径
	 * @param readFile jar包内文件的路径
	 */
	public final static List<String> readFileTypeInJAR(String jarFileName, String fileType) {
		List<String> fileList = new ArrayList<String>();
		try {
			JarFile jarFile = new JarFile(jarFileName);// 读入jar文件
			Enumeration<JarEntry> entries = jarFile.entries();
			while (entries.hasMoreElements()) {
				// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
				JarEntry entry = entries.nextElement();
				String name = entry.getName().trim();
				if (name.indexOf("/") > -1 || name.indexOf("core") > -1 || !name.endsWith(fileType)) {
					continue;
				}
				fileList.add(name);
			}
			jarFile.close();

		} catch (IOException e) {
			logger.error("", e);
		}
		return fileList;
	}

	/**
	 * 计算文件大小
	 * 
	 * @author ruan
	 * @param filename 文件的完整路径
	 * @return
	 */
	public final static String getSize(String filename) {
		long size = new File(filename).length();
		if (size == 0) {
			return "0 " + sizes[0];
		}
		return getSize(size);
	}

	/**
	 * 根据传进来的大小选择最适合的单位
	 * @param size
	 * @return
	 */
	public final static String getSize(double size) {
		int i = (int) Math.floor(Math.log(size) / Math.log(1024));
		return decimalFormat.format(size / Math.pow(1024, i)) + " " + sizes[i];
	}

	/**
	 * 加载一个property文件
	 * @author ruan
	 * @param file property文件的完整路径
	 * @return
	 */
	public final static synchronized Properties loadProperties(String file) {
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStream in = new BufferedInputStream(fileInputStream);
			Properties properties = new Properties();
			properties.load(in);
			fileInputStream.close();
			in.close();
			return properties;
		} catch (IOException e) {
			logger.error("", e);
			return null;
		} finally {
			logger.info("load properties: {}", file);
		}
	}

	/**
	 * 自动发现指定路径内property文件并自动加载
	 * @param path
	 */
	private final static synchronized void loadProperties0(String path) {
		String[] fileList = ls(path);
		if (fileList.length <= 0) {
			return;
		}
		for (String file : fileList) {
			if (file.lastIndexOf(".properties") <= -1) {
				continue;
			}
			if ("config.properties".equals(file) || "log4j.properties".equals(file)) {
				// 这两个文件一定加载，所以不用再加载了
				continue;
			}
			property.putAll(loadProperties(path + file));
		}
	}

	/**
	 * 自动发现property文件并自动加载
	 */
	private final static synchronized void loadProperties() {
		try {
			property.putAll(loadProperties(classLoader.getResource("config.properties").toString().replace("file:", "").trim()));
		} catch (Exception e) {
			logger.error("can not find config.properties", e);
			System.exit(0);
		}
		if (isWindows) {
			String path = classLoader.getResource("config.properties").toString().replace("file:", "").replace("config.properties", "").trim();
			loadProperties0(path);
		}
		String path = classLoader.getResource("").toString().replace("file:", "");
		loadProperties0(path);

	}

	/**
	 * Property转成有序的list
	 * @author ruan
	 * @param order 排序方式
	 * @return
	 */
	public final static List<Object> property2List(Order order) {
		return property2List(property, order);
	}

	/**
	 * Property转成有序的list
	 * @author ruan
	 * @param property property文件
	 * @param order 排序方式
	 * @return
	 */
	public final static List<Object> property2List(Properties Property, Order order) {
		HashMap<Object, Object> configMap = new HashMap<Object, Object>();
		for (Entry<Object, Object> e : Property.entrySet()) {
			configMap.put(e.getKey(), e.getValue());
		}
		return SortUtil.sortMap(configMap, order);
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static String getPropertyString(String key) {
		String value = property.getProperty(key);
		if (value == null) {
			logger.error("can not found property: " + key);
			return "";
		}
		return value.trim();
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static byte getPropertyByte(String key) {
		return StringUtil.getByte(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static short getPropertyShort(String key) {
		return StringUtil.getShort(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static int getPropertyInt(String key) {
		return StringUtil.getInt(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static float getPropertyFloat(String key) {
		return StringUtil.getFloat(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static double getPropertyDouble(String key) {
		return StringUtil.getDouble(getPropertyString(key));
	}

	/**
	 * 获取property文件配置
	 * @param key 键
	 */
	public final static boolean getPropertyBoolean(String key) {
		return StringUtil.getBoolean(getPropertyString(key));
	}

	/**
	 * 是否有此配置
	 * @param key 键
	 * @return
	 */
	public final static boolean propertyHas(String key) {
		Object o = property.get(key);
		if (o == null) {
			return false;
		}
		return !o.toString().trim().isEmpty();
	}

	/**
	 * 自动发现spring配置文件并自动加载
	 */
	public final static synchronized void loadSpringConfig() {
		String[] fileList = ls(getSystemDir() + "bin/");
		List<String> jarList = lsJAR(getSystemDir() + "lib/");
		for (String file : jarList) {
			List<String> list = readFileTypeInJAR(file, ".xml");
			if (list == null || list.isEmpty()) {
				continue;
			}
			int size = list.size();
			fileList = Arrays.copyOf(fileList, fileList.length + size);
			for (int i = 0; i < size; i++) {
				fileList[fileList.length - size - i] = list.get(i);
			}
		}
		if (fileList.length <= 0) {
			return;
		}
		for (String file : fileList) {
			if (file.lastIndexOf(".xml") <= -1) {
				continue;
			}
			new ClassPathXmlApplicationContext("classpath:" + file).registerShutdownHook();
			logger.info("load spring config: {}", file);
		}
	}

	/**
	 * 下载文件
	 * @author ruan
	 * @param path 文件网络路径
	 * @param file 文件存放路径
	 * @param threadnum 下载线程数
	 * @throws Exception
	 */
	public final static void downFile(String path, File file, int threadnum) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(10 * 1000);
		conn.setRequestMethod("GET");
		// 获得网络文件的长度
		int length = conn.getContentLength();
		// 每个线程负责下载的文件大小
		int block = (length % threadnum) == 0 ? length / threadnum : length / threadnum + 1;
		// 从http相应消息获取的状态码，200:OK;401:Unauthorized
		if (conn.getResponseCode() == 200) {
			for (int i = 0; i < threadnum; i++) {
				// 开启线程下载
				new DownThread(i, file, block, url).start();
			}
		}
	}

	/**
	 * 获取一个包下所有类的数量和方法数量
	 * @author ruan
	 * @param packageName
	 */
	public final static Map<String, Object> getPackageClassAndMethodNum(String packageName) {
		Set<Class<?>> classSet = SystemUtil.getClasses(packageName);

		String classNum = "class num";
		String methodNum = "method total num";
		String methodDistribute = "method distribute";

		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> methodDistributeMap = new HashMap<String, Object>();
		result.put("package name", packageName);
		result.put(classNum, classSet.size());

		try {
			for (Class<?> clazz : classSet) {
				int classMethodNum = clazz.getMethods().length;
				methodDistributeMap.put(clazz.getName(), classMethodNum);
				result.put(methodNum, StringUtil.getInt(result.get(methodNum)) + classMethodNum);
			}

			result.put(methodDistribute, methodDistributeMap);
		} catch (Exception e) {
			logger.error("", e);
			result.clear();
		}
		return result;
	}

	/**
	 * 读取csv文件
	 * @param path 文件路径
	 * @return
	 */
	public final static List<List<String>> readCSV(String path) {
		List<List<String>> content = null;
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(new File(path));
			content = readCSV(fileReader);
		} catch (FileNotFoundException e) {
			logger.error("", e);
		} finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return content;
	}

	/**
	 * 读取csv文件
	 * @param reader 文件流
	 * @return
	 */
	public final static List<List<String>> readCSV(Reader reader) {
		List<List<String>> content = new ArrayList<>();
		CsvListReader csvListReader = null;
		try {
			csvListReader = new CsvListReader(reader, CsvPreference.EXCEL_PREFERENCE);
			List<String> line = new ArrayList<String>();
			while ((line = csvListReader.read()) != null) {
				content.add(line);
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (csvListReader != null) {
					csvListReader.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
		return content;
	}

	/**
	  * 写入csv文件
	  * @param path 文件路径
	  * @param header 头部
	  * @param content 内容
	  */
	public final static void writeCsv(String path, String[] header, List<String[]> content) {
		FileWriter fileWriter = null;
		CsvListWriter writer = null;
		try {
			fileWriter = new FileWriter(new File(path));
			writer = new CsvListWriter(fileWriter, CsvPreference.EXCEL_PREFERENCE);
			writer.writeHeader(header);
			for (String[] str : content) {
				writer.write(str);
			}
		} catch (IOException e) {
			logger.error("", e);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			}
		}
	}
}

final class DownThread extends Thread {
	private final static Logger logger = LoggerFactory.getLogger(DownThread.class);
	private int id; // 线程id
	private File file;// 目标文件
	private int block;// 每个线程下载文件的大小
	private URL url;

	public DownThread(int id, File file, int block, URL url) {
		this.id = id;
		this.file = file;
		this.block = block;
		this.url = url;
	}

	@Override
	public void run() {
		int start = (id * block);// 当前线程开始下载处
		int end = (id + 1) * block - 1;// 当前线程结束下载处
		// 建立随机操作文件对象
		try {
			RandomAccessFile accessFile = new RandomAccessFile(file, "rwd");
			accessFile.seek(start);// 设置操作文件的入点
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestMethod("GET");
			// 指定网络位置从什么位置开始下载,到什么位置结束
			conn.setRequestProperty("Range", "bytes=" + start + "-" + end + "");
			InputStream in = conn.getInputStream();// 获得输入流
			byte[] data = new byte[1024];
			int len = 0;
			while ((len = in.read(data)) != -1) {
				accessFile.write(data, 0, len);
			}
			accessFile.close();
			in.close();
			logger.info("线程:" + (id + 1) + "下载完成!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}