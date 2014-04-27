package algorithm.find;

/**
 * KMP算法实现。 
 * 一、在主串中查找模式串，返回在主串中第一次出现模式串的位置。
 * 二、kmp算法是一种改进的字符串匹配算法，由D.E.Knuth与V.R.Pratt和J
 * .H.Morris同时发现，因此人们称它为克努特——莫里斯——普拉特操作（简称KMP算法）.
 * KMP算法的核心思想是利用已经得到的部分匹配信息来进行后面的匹配过程。
 * 在进行匹配的过程中，利用已经完成的部分匹配信息(主串和模式串相同的部分)，找到进行下一次匹配时需要做的位移量。
 * 由于该部分信息在模式串中存在，所以可以提前对模式串的所有字符求next函数，即下图中的求n或求m.
 * 三、求有效m,n的前提是在已经完成的部分匹配中，模式串的前n个字符和后n个字符一一相同。
 * 四、在匹配的过程中，主串的前m个字符和模式串的前m个字符一对一相等 ，第m+1个字符不相等，假设在m个字符串中存在另一个和模式串开头相同的部分n，
 * 并且这个n是不可分解的， 主串的前m-n个字符也是不可分解的。则下一次比较应该发生在模式串的第n+1个字符和主串第m+1个字符之间。求解n.类似于最小分解的过程.
 * 这里的分解指的是得到有意义的m或n的过程.
 * (常规的比较则是发生在模式串的第一个字符和主串的第2个字符之间,
 * 而kmp算法则是相当于模式串跨过了前m-n个字符，从第m-n+1个字符开始匹配与主串的当前字符进行比较，主串不比进行回溯)。
 * 也有人称前m-n个字符为前缀串。 
 *     |-----------m个字符 -----------------| |----------n个字符------| i
 *    main[i+0]                  main[i+m-1]main[m]      main[i+m+n-1] main[i+m+n] 
 * 主串 |___________________________________| |_______________________| |
 *                  pattern[n-1] pattern[n] pattern[m+n]               j 
 * 模串 |_______________________| |________|  |_______________________| |
 * 
 * 使用kmp算法后，主串的main[i+m+n]和模串的pattern[n]进行比较。
 * 
 * 主串和模式串前m+n个字符分别相同,main[i+m+n]!=patter[m+n], m>n,求n
 * (下一次字符匹配发生在main[i+m+n]和pattern[n]之间,实际上这取决于外部具体的匹配算法,求的值和使用的值，请区分清楚)
 * 实际上在进行循环比较的时候，m+n是已知的,只要求的m和n其中的一个就可以得到另一个。
 * 在进行比较时，只有当匹配在模串中的匹配下标大于m+1时，效率才会有提升。
 * 
 * 
 * @date 2013-3-11 11:50:36
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class KMP {

	/**
	 * indexOf.
	 * 
	 * @param main
	 *            主串
	 * @param pattern
	 *            模式串
	 * @return int
	 */
	public static int indexOf(char[] main, char[] pattern) {
		int index = -1;
		// 主串的长度，必须大于子串的长度
		if (main.length < pattern.length) {
			return index;
		}
		// 计算模式串的next值
		int[] pattern_next = getNext(pattern);
		// 模式串当前需要比较的元素的索引
		int pattern_index=0;
		// 查找是否包含匹配字符串，不包含则返回默认的-1;
		for (int i = 0; i <= (main.length-pattern.length); i++) {
			System.out.println("外循环:i:"+i+",pattern_start:"+pattern_index);
			for (int j = pattern_index; j < pattern.length; j++) {
				System.out.println("i+j:"+(i+j)+",i:"+i+",j:"+j);
				if (main[i + j] == pattern[j]) {
					// 模式串匹配到结尾
					if (j == (pattern.length - 1)) {
						index = i;
						return index;
					}else {
						continue;
					}
				} else {
					// 实际上当j>1时，模式串的值才有意义。
					if (j>1) {
						// 调整主串和模式串的起始匹配索引位置。m=pattern_next[j]
						// i=i+m
						i = i+(j-pattern_next[j])-1;
						// j=n
						pattern_index = pattern_next[j];
						System.out.println("跳转前：i:"+i+"跳转前：j:"+j+",next[j]:"+pattern_next[j]);
					}
					
					break;
				}
			}
		}
		return index;
	}

	/**
	 * Gets next. 这里的next指的是类说明中的m值,或者说前缀长度,同样也是模式中下一次用来进行比较的元素下标序号。
	 * 
	 * @param pattern
	 *            comments
	 * @return next
	 */
	public static int[] getNext(char[] pattern) {
		int[] result = new int[pattern.length];
		// 求每个字符的next函数值
		result[0] = 0;
		result[1] = 0;
		for (int i = 2; i < pattern.length; i++) {
			result[i] = getNext(i,pattern);
		}
		return result;
	}

	
	/**
	 * Gets next.
	 * 对于有效的targetIndex来说，其前边的targetIndex个字符特点：首尾字符串相同.
	 * 所以求next,其实就是求模式串中首串的长度或尾串的长度，也相当于模式串匹配时的偏移量。
	 * 即
	 *
	 * @param targetIndex 模式字符串中需要计算next值的字符的索引
	 * @param pattern 模式字符串
	 * @return next
	 */
	private static int getNext(int targetIndex, char[] pattern) {
		int next = 0;
		char first_char = pattern[0];
		// 对于有效的targetIndex来说，其前边的targetIndex个字符特点：首尾字符串相同
		for (int j = targetIndex % 2 == 1 ? targetIndex / 2 + 1
				: targetIndex / 2; j < targetIndex; j++) {
			// 从targetIndex的二分之一处开始查找,查找第一个可能的尾串开始值,开始匹配
			if (pattern[j] == first_char) {
				next=1;
				// 从第2个字符开始比较时因为pattern[j]==first_char已经进行了比较。
				for (int index1 = 1, index2 = j + 1; index1 < index2
						&& index2 < targetIndex; index1++, index2++) {
					// 发现不匹配的值，从下一个j开始重新找
					if (pattern[index1] != pattern[index2]) {
						break;
					}
					// 匹配完成,index1为首串的结束索引,则index1+1为next值
					if (index2==(targetIndex-1)) {
						next =index1+1;
					}
				}
			}
		}
		return next;
	}
	public static void main(String[] args) {
		String main = "0123bcdebcdebcdfeabcd";
		String pattern = "bcdebcdf";
		char[] next_arry = pattern.toCharArray();
		int[] result = KMP.getNext(next_arry);
		System.out.println(next_arry);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i]+",");
		}
		System.out.println(KMP.indexOf(main.toCharArray(), pattern.toCharArray()));
	}
	
}
