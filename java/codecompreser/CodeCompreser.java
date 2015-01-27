import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class CodeCompreser {
	public static void main(String[] args) throws IOException {
		if (args.length <= 0) {
			System.err.println("用法：");
			System.err.println("-source: 源文件夹");
			System.err.println("-target: 目标文件夹，如不指定则以source为目标文件夹");
			System.exit(0);
		}
		HashMap<String, String> data = new HashMap<String, String>();
		String key = null, value = null;
		for (int i = 0; i < args.length; i++) {
			if (i % 2 == 0) {
				key = args[i].trim();
			}
			if (i % 2 == 1) {
				value = args[i].trim();
			}
			if (key == null || value == null || key.isEmpty() || value.isEmpty()) {
				continue;
			}
			data.put(key.replaceAll("[^a-zA-Z]+", ""), value);
			key = value = null;
		}

		String source = getString(data.get("source")) + "/";
		String target = (getString(data.get("target")).isEmpty() ? source : getString(data.get("target"))) + "/";

		mkdir(source);
		mkdir(target);

		for (String f : ls(source)) {
			String content = read(source + f);
			content = content.replaceAll("\\>\\s+\\<", "><");
			content = content.replaceAll(",\\s+'", ",'");
			content = content.replaceAll(",\\s+", ",");
			content = content.replaceAll("'\\s+:", "':");
			content = content.replaceAll("\\s+}", "}");
			content = content.replaceAll("}\\s+", "}");
			content = content.replaceAll("\\{\\s+'", "{'");
			content = content.replaceAll("\\{\\s+var", "{var");
			content = content.replaceAll(";\\s+var", ";var");
			content = content.replaceAll(";\\s+", ";");
			content = content.replaceAll("\\{\\s+", "{");
			content = content.replaceAll(":\\s+}", ":");
			content = content.replaceAll("php}", "php }");
			write(target + f, new String(content.trim().getBytes("GBK"), "UTF-8"), false);
		}
	}

	private final static void mkdir(String path) {
		File f = new File(path);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	private final static String getString(String str) {
		return str == null ? "" : str.trim();
	}

	private final static String[] ls(String dir) {
		File file = new File(dir);
		if (file.exists()) {
			return file.list();
		}
		return new String[0];
	}

	/**
	 * 读取文件类容
	 * 
	 * @author ruan 2012-3-21
	 * @param filename 文件名(可包含路径)
	 * @return 文件类容
	 */
	private static String read(String filename) {
		try {
			String text = null;
			StringBuilder sb = new StringBuilder();
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while ((text = input.readLine()) != null) {
				sb.append(text.trim());
			}
			input.close();
			sb.trimToSize();
			return sb.toString().trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 写入文件(完全自定义文件格式和写入内容)
	 * 
	 * @param filename 文件名(可包含路径)
	 * @param data 要写入的数据
	 * @param flag 是否追加(true追加，false不追加)
	 * @return boolean
	 */
	private static boolean write(String filename, String data, boolean flag) {
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
}