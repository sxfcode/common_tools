package algorithm;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 非递归方式获取指定目录下的所有文件名.
 * 本质实际上是去除了冗余的堆栈信息，只保留最基本的联系信息。
 *
 * @date 2013-6-20 11:33:09
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class ReadFileNames {
	
	/**
	 * 非递归方式获取某目录下的所有文件名称，包含子目录.
	 *
	 * @param dirName 目录名称
	 * @param includeDir 是否包含目录名称,若包含目录名称，则输出结果时，先输出目录名称，后输出文件名称.
	 * @return fileNames
	 */
	public static List<String> getFileNames(String dirName,boolean includeDir){
		// 存放文件名
		List<String> fileNames = new ArrayList<String>();
		// 存放目录名
		List<String> dirNames = new ArrayList<String>();
		Stack<String> temp = new Stack<String>();
		File file = new File(dirName);
		if (file.isFile()) {
			fileNames.add(file.getAbsolutePath());
			return fileNames;
		}
		temp.push(dirName);
		while (temp.size()>0) {
			file = new File(temp.pop());
			if (file.isFile()) {
				fileNames.add(file.getAbsolutePath());
			} else if( file.isDirectory()){
				if (includeDir) {
					dirNames.add(file.getAbsolutePath());
				}
				File[] files = file.listFiles();
				for (File file2 : files) {
					temp.push(file2.getAbsolutePath());
				}
			}
		}
		if (includeDir) {
			dirNames.addAll(fileNames);
			return dirNames;
		}else {
			return fileNames;
		}
	}
	
	/**
	 * Gets dirNames.
	 *
	 * @param dirName comments
	 * @param includeFile comments
	 * @return dirNames
	 */
	public static List<String> getDirNames(String dirName,boolean includeFile){
		// 存放目录名
		List<String> dirNames = new ArrayList<String>();
		// 存放文件名
		List<String> fileNames = new ArrayList<String>();
		Stack<String> temp = new Stack<String>();
		File file = new File(dirName);
		temp.push(dirName);
		while (temp.size()>0) {
			file = new File(temp.pop());
			if (file.isFile()) {
				if (includeFile) {
					fileNames.add(file.getAbsolutePath());
				}
			} else if( file.isDirectory()){
				dirNames.add(file.getAbsolutePath());
				File[] files = file.listFiles();
				for (File file2 : files) {
					temp.push(file2.getAbsolutePath());
				}
			}
		}
		if (includeFile) {
			dirNames.addAll(fileNames);
			return dirNames;
		}else {
			return dirNames;
		}
	}
	public static void main(String[] args) {
		//System.out.println( new File("D:/test/test_web").getParent());
		List<String> list = ReadFileNames.getFileNames("C:\\Users\\pc\\Desktop\\1", false);
		for (String fileName : list) {
			System.out.println(fileName);
		}
	}
}
