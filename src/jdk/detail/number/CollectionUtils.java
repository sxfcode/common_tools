package jdk.detail.number;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * The Class CollectionUtils.
 *
 * @date 2013-11-13 18:40:24
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CollectionUtils {
	
	/**
	 * 数组转换成List集合.
	 *
	 * @param <T> the generic type
	 * @param source comments
	 * @return List
	 */
	public static <T> List<T> arrayToList(T[] source){
		return Arrays.asList(source);
	}
	
	/**
	 * List集合转换成数组.
	 *
	 * @param <T> the generic type
	 * @param list comments
	 * @return T[]
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] listToArray(List<T> list){
		return (T[])list.toArray();
	}
	
	
	/**
	 * 扩展对象数组大小.
	 *
	 * @param src 对象数组
	 * @param size 增加的容量
	 * @return Object[]
	 */
	public static Object[] increase(Object[] src,int size){
		if(src==null){
			return null;
		}
		Object[] target = new Object[src.length+size];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
	}
	
	/**
	 * 对map按key进行排序.
	 * map的key应该为int
	 *
	 * @param src 需要排序的map
	 * @return TreeMap
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static TreeMap<Integer,Object> sortMap(Map<Integer,Object> src){
		TreeMap<Integer,Object> tm = new TreeMap<Integer,Object>(new Comparator(){
			@SuppressWarnings("unused")
			public int compare(Map.Entry<Integer,Object> o1, Map.Entry<Integer,Object> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}

			@Override
			public int compare(Object o1, Object o2) {
				return 0;
			}
		});
		tm.putAll(src);
		return tm;
	}
	
	/**
	 * 对map按指定比较器排序.
	 *
	 * @param src 需要排序的map
	 * @param comp 比较器
	 * @return TreeMap
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static TreeMap sortMap(Map src,Comparator comp){
		TreeMap tm = new TreeMap(comp);
		tm.putAll(src);
		return tm;
	}
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
//		String[] s = new String[]{"a","b","c"};
//		for (String st : CollectionUtils.arrayToList(s) ){
//			System.out.println(st);
//		}
		Map<Integer,String> map  = new HashMap<Integer,String>();
		map.put(1, "a");
		map.put(3, "c");
		map.put(4, "d");
		map.put(5, "b");
		Iterator i = map.entrySet().iterator();
		while (i.hasNext()) {
			Entry<Integer,String> en = (Entry<Integer,String>)i.next();
			System.out.println(en.getValue());
		}
	}

}
