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
		// 插入第一个结点
		if (root==null) {
			root = new DigitalSearchNode("");
			DigitalSearchNode current = root;
			for (char c : chars) {
				DigitalSearchNode node = new DigitalSearchNode(c);
				current.setFirstChild(node);
				node.setParent(current);
				current = node;
			}
		}else{
			DigitalSearchNode parent = root;
			DigitalSearchNode current = root.getFirstChild();
			for (int i = 0;i<chars.length;i++) {
				char c = chars[i];
				while (true) {
					// 当前元素位置为空,直接将后续元素添加到树
					if (current==null) {
						for (int j = i; j < chars.length; j++) {
							DigitalSearchNode newNode = new DigitalSearchNode(c);
							parent.setFirstChild(newNode);
							newNode.setParent(parent);
							parent = newNode;
						}
						return;
					}else{
						int n = c - (Character)current.getData();
						// 当前层次，新插入的结点排序更小
						if (n<0) {
							
							for (int j = i; j < chars.length; j++) {
								DigitalSearchNode newNode = new DigitalSearchNode(c);
								parent.setFirstChild(newNode);
								newNode.setParent(parent);
								parent = newNode;
							}
							return;
						// 当前层次，新插入的结点排序更大
						}else if(n>0){
							for (int j = i; j < chars.length; j++) {
								DigitalSearchNode newNode = new DigitalSearchNode(c);
								parent.setFirstChild(newNode);
								newNode.setParent(parent);
								parent = newNode;
							}
							return;
						}else{
							
						}
					}
					
					
					
				}
				
				
			}
			int i = 0;
			
		}
		
		
		
	}
	
	public void deleteKey(String key){
		
	}
	public boolean search(String key){
		return false;
	}

}
