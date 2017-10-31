package com.minsx.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.minsx.common.constant.Constant;


/**
 * Properties 读写工具
 * 注：默读写为自动加解密，保证文件中是加密后的键值对
 */
public class PropertiesUtil {
	
	private Properties property = new Properties();
	private File file = null;
	public Map<String, String> toSaveMap = new HashMap<String, String>();
	
	/**
	 * 构造
	 */
	public PropertiesUtil(String propertiesName) {
		final String filePath =  Constant.SOFT_CONFIG_PATH + propertiesName;
		file = new File(filePath);
		try {
			if (!file.exists()) {
				FileUtil.createFile(filePath);
			}
			FileInputStream fis = new FileInputStream(file);
			property.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取所有键值对
	 */
	public Map<String, String> getAllValues() throws Exception {
		FileInputStream fis = new FileInputStream(file);
		property.load(fis);
		Set<Object> keys = property.keySet();
		for (Iterator<Object> itr = keys.iterator(); itr.hasNext();) {
			String key = (String) itr.next();
			Object value = property.get(key);
			toSaveMap.put(key, String.valueOf(value));
		}
		fis.close();
		return toSaveMap;
	}

	/**
	 * 判断是否存在指定文件
	 */
	public void isExists(File properties) throws Exception {
		if (!properties.exists()) {
			properties.createNewFile();
		}
		property.setProperty("key", "values");
		FileOutputStream fis = new FileOutputStream(properties);
		property.store(fis, null);
		fis.close();
	}

	/**
	 *  读取值
	 */
	public String get(String key) {
		String values = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			property.load(fis);
			values = property.getProperty(key);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return values;
	}

	/**
	 *  写入值
	 */
	public synchronized void set(String key, String value) {
		try {
			getAllValues();
			toSaveMap.put(key, value);
			property.putAll(toSaveMap);
			FileOutputStream fis = new FileOutputStream(file);
			property.store(fis, null);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
