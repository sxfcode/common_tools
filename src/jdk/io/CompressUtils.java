package jdk.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import algorithm.ReadFileNames;

/**
 * The Class CompressUtils.
 * 
 * @date 2013-6-21 10:30:42
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CompressUtils {
	
	/**
	 * 压缩zip文件.
	 *
	 * @param sourceFileName 准备打包的目录或文件
	 * @param targetFileName 压缩后的压缩包名称
	 */
	public static void zipFile(String sourceFileName, String targetFileName) {
		InputStream input;
		ZipOutputStream zipOut;
		ZipEntry zipEntry;
		File tempFile;
		String entryName;
		String root;
		List<String> sourceFileNames = ReadFileNames.getFileNames(
				sourceFileName, false);
		try {
			zipOut = new ZipOutputStream(new FileOutputStream(targetFileName));
			root = new File(sourceFileName).getParent() + File.separator;
			for (String tempFileName : sourceFileNames) {
				entryName = tempFileName.substring(root.length());
				zipEntry = new ZipEntry(entryName);
				zipOut.putNextEntry(zipEntry);
				tempFile = new File(tempFileName);
				if (tempFile.isFile()) {
					input = new FileInputStream(tempFile);
					byte[] temp = new byte[10];
					while (true) {
						int count = input.read(temp);
						if (count != -1) {
							zipOut.write(temp);
						} else {
							break;
						}
					}
					input.close();
				}
				zipOut.closeEntry();
			}
			zipOut.finish();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解压缩zip文件.
	 *
	 * @param sourceFileName 压缩包完成路径名称
	 * @param targetFilePath 解压缩路径名称
	 * @param iscover 是否覆盖现有文件
	 */
	@SuppressWarnings("rawtypes")
	public static void upzipFile(String sourceFileName, String targetFilePath,boolean iscover) {
		if (!targetFilePath.endsWith(File.separator)){
			targetFilePath += File.separator;
		}
		File targetFile = new File(targetFilePath);
		// 若不存在解压目录，则创建解压目录
		if (!(targetFile.exists()&&targetFile.isDirectory())) {
			targetFile.mkdir();
		}
		ZipFile zipFile;
		FileOutputStream fos;
		InputStream in;
		try {
			zipFile = new ZipFile(sourceFileName);
			Enumeration entrys = zipFile.entries();
			while (entrys.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entrys.nextElement();
				String name = entry.getName();
				String targetFileName = targetFilePath+name;
				if (entry.isDirectory()) {
					// entry是目录，则创建目录
					new File(targetFileName).mkdirs();
					continue;
				}else {
					// entry是文件，则创建父目录。
					new File(targetFileName).getParentFile().mkdirs();
				}
				// 检查是否已存在同名文件 
				if (!iscover) {
					File old = new File(targetFileName);
					if (old.exists()&&old.isFile()) {
						continue;
					}
				}
				// 创建文件
				fos = new FileOutputStream(new File(targetFileName));
				System.out.println("创建文件:"+targetFileName);
				in = zipFile.getInputStream(entry);
				byte[] buffer = new byte[10];
				while(true){
					int bytes_read = in.read(buffer);
					if (bytes_read!=-1) {
						fos.write(buffer,0,bytes_read);
					}else {
						break;
					}
				}
				fos.close();
				in.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//zipFile("D:\\test\\test_web", "D:\\test\\test_web_zip.zip");
		//zipFile("D:\\test\\test1.txt", "D:\\test\\test_web_zip1.zip");
		upzipFile("D:\\test\\test_web_zip.zip", "D:\\test2\\",false);
	}
}
