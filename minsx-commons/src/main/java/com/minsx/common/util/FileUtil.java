package com.minsx.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * 文件操作工具
 *
 * @Author Joker
 * @Date 2017年2月22日
 */
public class FileUtil extends FileUtils {

	/**
	 * 替换文本文件制定字符串
	 *
	 * @param file
	 *            需要被操作的文件
	 * @param oldString
	 *            久字符串
	 * @param newString
	 *            新字符串
	 * @throws IOException
	 */
	public static void replace(File file, String oldString, String newString) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
		File outfile = new File(file + ".tmp");
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outfile), "utf-8")));
		String reading;
		while ((reading = in.readLine()) != null) {
			out.println(reading.replaceAll(oldString, newString));
		}
		out.close();
		in.close();
		file.delete();
		outfile.renameTo(file);
	}

	/**
	 * 读取文件内容
	 *
	 * @param file
	 *            需要被操作的文件
	 * @return 读取的文件内容
	 */
	public static String getFileContent(File file) {
		StringBuilder result = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}

	/**
	 * 创建文件 ：如果不存在指定路径，将自动创建文件夹和文件
	 *
	 * @param filePath
	 *            文件夹路径
	 * @return 是否上传成功
	 */
	public static boolean createFile(String filePath) {
		boolean isSuccess = false;
		try {
			String path = filePath.substring(0, filePath.lastIndexOf("/"));
			File f = new File(path);
			if (!f.exists())
				f.mkdirs();
			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.length());
			File file = new File(f, fileName);
			if (!file.exists())
				file.createNewFile();
			isSuccess = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}

	/**
	 * 拷贝一个目录下的所有文件及文件夹到另一个目录 注：此方法只适用于Windows系统，采用cmd的方式拷贝
	 *
	 * @param pathA
	 *            原目录
	 * @param pathB
	 *            目标目录
	 */
	public static void copyAllFilesAndContentToAnother(String pathA, String pathB) {
		try {
			String lastStrA = pathA.substring(pathA.length() - 1, pathA.length());
			if (lastStrA.equalsIgnoreCase("\\") || lastStrA.equalsIgnoreCase("/")) {
				pathA = pathA.substring(0, pathA.length() - 1);
			}
			String lastStrB = pathB.substring(pathB.length() - 1, pathB.length());
			if (lastStrB.equalsIgnoreCase("\\") || lastStrB.equalsIgnoreCase("/")) {
				pathB = pathB.substring(0, pathB.length() - 1);
			}
			String cmd = "cmd /c echo d|xcopy /s /e \"" + pathA + "\"  \"" + pathB + "\" /y ";
			System.out.println(cmd);
			Process getSyncInfoProcess = Runtime.getRuntime().exec(cmd);
			BufferedReader in = new BufferedReader(new InputStreamReader(getSyncInfoProcess.getInputStream()));
			while ((in.readLine()) != null) {
			} // 阻塞
			in.close();
			getSyncInfoProcess.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 指定文件后缀取文件
	 *
	 * @param fileDir
	 *            文件路径
	 * @param fileType
	 *            文件类型
	 * @return
	 */
	public static List<File> getFiles(File fileDir, String fileType) {
		List<File> lfile = new ArrayList<File>();
		if (!fileDir.exists()) {
			return lfile;
		}
		File[] fs = fileDir.listFiles();
		for (File f : fs) {
			if (f.isFile()) {
				if (fileType.equals("*")) {
					lfile.add(f);
				} else if (fileType.equalsIgnoreCase(
						f.getName().substring(f.getName().lastIndexOf(".") + 1, f.getName().length()))) {
					lfile.add(f);
				}
			} else {
				List<File> ftemps = getFiles(f, fileType);
				lfile.addAll(ftemps);
			}
		}
		return lfile;
	}

	/**
	 * 向指定文件写入指定内容
	 *
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            要写入的内容
	 * @throws IOException
	 */
	public static void writeToFile(String filePath, String content) throws IOException {
		BufferedWriter bufferWritter = null;
		FileWriter fileWritter = null;
		File file = new File(filePath);
		// if folder doesn't exists, then create it
		if (!file.exists()) {
			String path = file.getAbsolutePath();
			File folder = new File(path.substring(0, path.lastIndexOf("\\")));
			if (!folder.exists()) {
				folder.mkdirs();
			}
		}
		// if file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		// true = append file
		fileWritter = new FileWriter(filePath, false);
		bufferWritter = new BufferedWriter(fileWritter);
		bufferWritter.write(content);
		bufferWritter.close();
		fileWritter.close();
	}

	/**
	 * 取指定目录下所有目录名
	 *
	 * @param fileDir
	 *            指定目录
	 * @return 所有目录名
	 */
	public static List<String> getDirectoryNames(File fileDir) {
		List<String> directoryNames = new ArrayList<>();
		File[] fs = fileDir.listFiles();
		if (fs == null) {
			return directoryNames;
		}
		for (File f : fs) {
			if (f.isDirectory()) {
				directoryNames.add(f.getName());
			}
		}
		return directoryNames;
	}

	/**
	 * 删除指定文件
	 *
	 * @param path
	 *            文件路径
	 * @return 是否删除成功
	 */
	public static boolean deleteFileByPath(String path) {
		File file = new File(path);
		// 判断目录或文件是否存在
		if (!file.exists()) { // 不存在返回 false
			return false;
		} else {
			return file.delete();
		}
	}

	/**
	 * 取指定目录下所有文件(除目录外)
	 *
	 * @param dir
	 *            指定目录
	 * @return 所有文件
	 */
	public static List<File> getFileList(File dir) {
		List<File> filelist = new ArrayList<>();
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isDirectory()) {
					filelist.add(files[i]);
				}
			}
		}
		return filelist;
	}

	public static boolean isEmptyDirectory(File directory) {
		boolean isEmpty = false;
		if (directory != null && directory.exists() && directory.isDirectory()) {
			File[] files = directory.listFiles();
			if (files != null && files.length == 0) {
				isEmpty = true;
			}
		}
		return isEmpty;
	}

}
