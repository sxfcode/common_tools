package jdk.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class DivideUtils.
 *
 * @date 2013-6-21 11:42:49
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
@SuppressWarnings("unused")
public class DivideUtils {
	
	/**
	 * 分割文件.
	 *
	 * @param fileName 被分割的文件
	 * @param size 每个分割文件的大小。
	 * @return String[]
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public  static String[] divideBySize(String fileName,long size) throws IOException{
		File source = new File(fileName);
		if (!source.exists()) {
			return null;
		}
		if(size<2){
			size=2;
		}
		File parent = source.getParentFile();
		// 将拆分的文件放入当前文件所在目录的divid目录
		parent = new File(parent,"divide");
		parent.mkdir();
		FileInputStream in = new FileInputStream(source);
		long length = source.length();
		System.out.println("目标文件大小:"+length);
		int num = (int)((length % size==0)?length/size:(length/size+1));
		String[] outFiles = new String[num];
		for(int i=0;i<num;i++){
			File outFile = new File(parent,"divide_"+i+"."+"dd");
			FileOutputStream out = new FileOutputStream(outFile);
			// 确定最后一个文件的长度
			if(i==num-1){
				if(num*size>length){
					size = length -(num-1)*size;
				}
			}
			for(int j=0;j<size;j++){
				out.write(in.read());
			}
			out.close();
			outFiles[i]= outFile.getAbsolutePath();
			System.out.println("写入拆分文件"+outFiles[i]);
		}
		in.close();
		return outFiles;
	}
	
	/**
	 * 合并文件.
	 * 按照提供的文件名依次顺序合并文件。
	 *
	 * @param fileNames 被合并的文件列表。
	 * @param targetFile 合并后的文件名。
	 * @return String
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String union(String[] fileNames,String targetFile) throws IOException{
		if(fileNames==null||fileNames.length<2||targetFile==null){
			return null;
		}
		File outFile  = new File(targetFile);
		FileOutputStream out = new FileOutputStream(outFile);
		File inFile = null;
		FileInputStream in = null;
		for (int i = 0; i < fileNames.length; i++) {
			inFile = new File(fileNames[i]);
			in = new FileInputStream(new File(fileNames[i]));
			int c; 
			while ((c=in.read())!=-1) {
				out.write(c);
			}
			in.close();
		}
		out.close();
		return outFile.getAbsolutePath();
		
	}
	
	/**
	 * divideByLine.
	 * 按行分割文件,可以考虑多线程。
	 *
	 * @param fileName comments
	 * @param size comments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public  static void divideByLine(String fileName,long size) throws IOException{
		System.out.println("divideByLine start...");
		File source = new File(fileName);
		if (!source.exists()) {
			return;
		}
		if(size<2){
			size=2;
		}
		long totalLength = source.length();
		// size的长度应该大于最大单条日志的长度。
		if(size>=totalLength){
			return;
		}
		RandomAccessFile raf = new RandomAccessFile(source,"r");
		// 分割边界。
		List<Long> fs = new ArrayList<Long>();
		fs.add(0L);
		// 初始化
		long current = size;
		long flag = 0;
		// 方法1，自己判断行的结束。由于查找和判断行结束的算法还存在问题，暂时不采用在这种方式。可以尝试学习jdk中的readLine方法。
//		while(current<totalLength){
//			flag = current -200;
//			raf.seek(flag);
//			System.out.println("flag :"+flag);
//			boolean findIt = false;
//			// 限制遍历区间在size之内。
//			while(flag>current-size){
//				//System.out.println("c2");
//				// 在200的区间内查找换行符
//				for(long i= flag;i<flag+200;i++){
//					//System.out.println("c3");
//					System.out.println(raf.readUTF());
//					if('\n'==raf.readChar()){
//						System.out.println("发现匹配的分割边界");
//						fs.add(flag+1);
//						findIt = true;
//						current = current+size;
//						break;
//					}
//				}
//				if(findIt){
//					break;
//				}
//				// 若200区间内未找到，则前移200个数字，直到整个size区间检查完毕。
//				flag = flag -200;
//			}
//		}
		// 方法2，调用系统方法来判断行的结束。readLine^_^， 通过readLine达到行的结尾。通过该方式获取的文件片段总是大于给定大小
		while(current<totalLength){
			flag = current -200;
			raf.seek(flag);
			System.out.println("flag :"+flag);
			raf.readLine();
			fs.add(raf.getFilePointer());
			current = current+size;
			
		}
		System.out.println("开始创建分割文件");
		File parent = source.getParentFile();
		// 将拆分的文件放入当前文件所在目录的divid目录
		parent = new File(parent,"divide");
		parent.mkdir();
		for (int i = 0; i < fs.size(); i++) {
			File outFile = new File(parent,"divide_"+i+"."+"dd");
			raf.seek(fs.get(i));
			FileOutputStream out = new FileOutputStream(outFile);
			long begin = fs.get(i);
			long end = 0;
			if(i==(fs.size()-1)){
				end = totalLength;
			}else{
				end = fs.get(i+1);
			}
			for(long j=begin;j<end;j++){
				out.write(raf.read());
			}
			out.close();
			System.out.println("写入拆分文件"+outFile.getAbsolutePath());
		}
		raf.close();
		System.out.println("divideByLine end...");
	}
    /**
 * The main method.
 *
 * @param args the arguments
 * @throws IOException Signals that an I/O exception has occurred.
 */
public static void main(String[] args) throws IOException {
//    	System.out.println("拆分文件开始..");
//		String[] fileNames = DivideUtils.divideBySize("D:/test/system_log.log", 10240);
//		System.out.println("拆分文件结束..");
//		String target = DivideUtils.union(fileNames, "D:/test/system_log2.log");
//		System.out.println("合并后的文件:"+target);
		//DivideUtils.divideByLine("D:/test/system_log2.log", 10240);
	    System.out.println(1024*1024*1024);
	}
}
