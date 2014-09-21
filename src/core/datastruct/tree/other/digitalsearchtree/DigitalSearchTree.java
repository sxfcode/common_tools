package core.datastruct.tree.other.digitalsearchtree;

/**
 * The Class DigitalSearchTree.
 *
 * @date 2014-9-21 17:06:38
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class DigitalSearchTree {
	
	/** root. */
	private DigitalSearchNode root;
	
	public void init(String[] sourceArray){
		for (String key : sourceArray) {
			addKey(key);
		}
		
	}
	public void addKey(String key){
		char[] chars = key.toCharArray();
		
	}
	
	public void deleteKey(String key){
		
	}
	public boolean search(String key){
		return false;
	}

}
