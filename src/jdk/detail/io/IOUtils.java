package jdk.detail.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import core.algorithm.ReadFileNames;

/**
 * The Class IOUtils.
 * 
 * @date 2013-4-19 16:15:08
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
@SuppressWarnings("unused")
public class IOUtils {

	/**
	 * 以字节方式读取文件，常用于读取二进制文件，例如图片，声音等.也可用读取纯英文的文本文件。
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void readFileByBytes(String fileName) {
		File file = new File(fileName);
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] temp = new byte[10];
			while (true) {
				// 实际读取的字节数
				int count = in.read(temp);
				// 这里输出各字节对应的字符
				if (count != -1) {
					System.out.write(temp, 0, count);
				} else {
					break;
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 以字符方式读取文件,主要用于读取文本，数字类型的文件。例如xx.text,xx.log等文件 需要注意换行符的处理。
	 * 
	 * @param fileName
	 *            文件名
	 */
	public static void readFileByChar(String fileName) {
		File file = new File(fileName);
		Reader in = null;
		try {
			// InputStreamReader可以指定字符编码
			in = new InputStreamReader(new FileInputStream(file));
			char[] temp = new char[10];
			while (true) {
				// 实际读取的字符数目
				int count = in.read(temp);
				// 这里输出各字节对应的字符
				if (count != -1) {
					for (int i = 0; i < count; i++) {
						char c = temp[i];
						System.out.print(c);
					}
				} else {
					break;
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 以行的形式读取文件.适合日志文件
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void readFileByLine(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String temp = "";
			while ((temp = reader.readLine()) != null) {
				System.out.println(temp);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 以随机访问模式读取文件,此方式可以跳跃读取数据.
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void readFileByRandomAccess(String fileName) {
		RandomAccessFile randomFile = null;
		// 只读方式访问
		try {
			randomFile = new RandomAccessFile(fileName, "rw");
			long filelength = randomFile.length();
			int beginIndex = 0;
			randomFile.seek(beginIndex);
			byte[] bytes = new byte[10];
			int byteread = 0;
			while ((byteread = randomFile.read(bytes)) != -1) {
				System.out.write(bytes, 0, byteread);
			}
			randomFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 字节形式写文件.
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void writeFileByBytes(String fileName) {
		File file = new File(fileName);
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			out.write("haha123123你好".getBytes());
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 字符形式写文件：单个字符.
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void writeFileByChars(String fileName) {
		File file = new File(fileName);
		Writer writer = null;
		try {
			writer = new OutputStreamWriter(new FileOutputStream(file));
			writer.write("haha你好");
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 字符形式写文件：行.
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void writeFileByLine(String fileName) {
		File file = new File(fileName);
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write("第一行哈哈");
			writer.newLine();
			writer.write("第二行哈哈");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 随机方式写文件.
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void writeFileByRandom(String fileName) {
		RandomAccessFile randomFile = null;
		// 只读方式访问
		try {
			randomFile = new RandomAccessFile(fileName, "rw");
			int beginIndex = 0;
			randomFile.seek(beginIndex);
			randomFile.writeUTF("123");
			// randomFile.writeChars("123");
			randomFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 追加文件内容.
	 * 
	 * @param fileName
	 *            comments
	 * @param content
	 *            comments
	 */
	public static void appendFile(String fileName, String content) {
		FileWriter fw;
		try {
			fw = new FileWriter(fileName, true);
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从控制台读取输入，并写入指定文件.
	 * 
	 * @param fileName
	 *            comments
	 */
	public static void readFromStdIn(String fileName) {
		File file = new File(fileName);
		PrintWriter writer = null;
		BufferedReader reader = null;
		try {
			writer = new PrintWriter(file);
			reader = new BufferedReader(new InputStreamReader(System.in));
			String inputLine = "";
			while ((inputLine = reader.readLine()) != null
					&& (!inputLine.equals("quit"))) {
				writer.println(inputLine);
			}
			writer.flush();
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 将指定文件或目录复制到指定目录下.
	 * 若执行copy("d:/test/dir1",d:/test/target),则执行结果为,将dir复制到target下
	 * 即d:/test/target/dir1/..
	 *
	 * @param source 来源目录或文件
	 * @param dest 目标目录
	 * @param iscover 是否覆盖已有文件
	 */
	public static boolean copy(String source, String dest,
			boolean iscover) {
		// 检测来源和目标 是否存在
		if (!(new File(source).exists())) {
			return false;
		}
		if (!(new File(dest).exists())) {
			return false;
		}
		// 目标目录
		if (!dest.endsWith(File.separator)) {
			dest = dest+File.separator+new File(source).getName()+File.separator;
		}
		List<String> fromFiles = ReadFileNames.getFileNames(source, true);
		FileInputStream fi;
		FileOutputStream fo;
		// 复制文件
		for (String fromFileName : fromFiles) {
			String targetFileName = dest + fromFileName.substring(source.length());
			File from = new File(fromFileName);
			File to = new File(targetFileName);
			// step 1:不允许覆盖
			if (!iscover) {
				if (to.exists()) {
					continue;
				}
			}
			// step 2:创建目录
			if (from.isDirectory()) {
				to.mkdirs();
				continue;
			}
			// step 3:创建文件
			try {
				fi = new FileInputStream(from);
				fo = new FileOutputStream(to);
				byte[] buffer = new byte[1024];
				while (true) {
					int count = fi.read(buffer);
					if (count!=-1) {
						fo.write(buffer, 0, count);
					}else {
						break;
					}
				}
				fo.close();
				fi.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	/**
	 * 先复制到目标目录，再删除原目录.
	 * 
	 * renameto方法受文件系统影响可能失败。
	 *
	 * @param source comments
	 * @param dest comments
	 * @param iscover comments
	 */
	public static boolean move(String source,String dest,boolean iscover){
		boolean copyResult = copy(source, dest, iscover);
		List<String> failedFiles = new ArrayList<String>();
		if (copyResult) {
			// step 1:删除文件
			List<String> files = ReadFileNames.getFileNames(source, false);
			for (String fileName : files) {
				if (!new File(fileName).delete()) {
					failedFiles.add("文件-"+fileName);
				}
			}
			// step 2:删除目录
			List<String> dirs = ReadFileNames.getDirNames(source, false);
			if (dirs.size()>0) {
				// sort默认从小到的顺序排序，这里调整为按字符串的长度从大到小.
				Collections.sort(dirs, new Comparator<String>() {
					@Override
					public int compare(String s1, String s2) {
						return s2.length()-s1.length();
					}
				});
			}
			for (String fileName : dirs) {
				if (!new File(fileName).delete()) {
					failedFiles.add("目录-"+fileName);
				}else {
					System.out.println("目录-"+fileName+"删除成功!");
				}
			}
			if (failedFiles.size()>0) {
				for (String failedFile : failedFiles) {
					System.out.println("删除:"+failedFile+"失败!");
				}
				return false;
			}else {
				return true;
			}
		}
		return false;
	}

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		String fileName = "D:/test/myzip01.zip";
		String fileName2 = "D:/test/myzip02.zip";
		// readFileByBytes(fileName);
		// readFileByChar(fileName);
		// readFileByLine(fileName);
		// readFileByRandomAccess(fileName);
		// writeFileByBytes(fileName);
		// writeFileByChars(fileName);
		// writeFileByLine(fileName);
		// writeFileByRandom(fileName);
		// appendFile(fileName, "test \n");
		// readFromStdIn(fileName);
		// zipFile(fileName,fileName2);
		//copy("D:\\test1", "D:\\test2", true);
		move("D:\\test1", "D:\\test2", true);
	}
}
