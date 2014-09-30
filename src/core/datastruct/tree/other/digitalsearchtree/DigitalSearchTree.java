package core.datastruct.tree.other.digitalsearchtree;

import java.util.LinkedList;
import java.util.Queue;

import core.datastruct.tree.other.digitalsearchtree.trie.DigitalSearchTrieNode;

/**
 * The Class DigitalSearchTree.
 * 键树
 * @date 2014-9-21 17:06:38
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class DigitalSearchTree {

	/** 基于孩子兄弟结构的实现方式，查找相对较慢，插入相对较快. */
	public final static int STRUCT_TYPE_SIBLING = 1;

	/** 基于树的多重链表，每个结点保存所有子结点的指针的实现方式，查找相对较快，插入相对较慢。. */
	public final static int STRUCT_TYPE_TRIE = 2;

	/** 根节点. */
	private DigitalSearchNode root;

	/** 存储结构. */
	private int structType = STRUCT_TYPE_TRIE;

	/**
	 * 构造一棵键树.
	 *
	 * @param sourceArray
	 *            comments
	 * @param structType
	 *            comments
	 */
	public void init(String[] sourceArray, int structType) {
		for (String key : sourceArray) {
			addKey(key, structType);
		}
	}

	/**
	 * 添加关键字.
	 * 例如将字典中的单词加入到树中。
	 *
	 * @param key
	 *            comments
	 * @param structType
	 *            comments
	 */
	public void addKey(String key, int structType) {
		char[] chars = key.toCharArray();
		if (STRUCT_TYPE_TRIE == structType) {
			// 根结点为空，初始化，插入第一个关键字
			if (root == null) {
				root = new DigitalSearchTrieNode(null);
				// 直接将关键字构成的度为1的子树
				addRemainderNode((DigitalSearchTrieNode) root, chars, 0);
			} else {
				DigitalSearchTrieNode parent = (DigitalSearchTrieNode) root;
				DigitalSearchTrieNode leafNode = null;
				int flag = 0;
				while (flag < chars.length) {
					// 在树的当前层次(children所在层次)中查找char
					char insertChar = chars[flag];
					DigitalSearchTrieNode[] oldChildren = parent.getChildren();
					if (oldChildren != null) {
						// 插入前的子结点数组长度
						int oldChildrenLength = oldChildren.length;
						DigitalSearchTrieNode insertNode = intsertNode(
								insertChar, parent);
						// 插入后的子结点数组长度
						int newChildrenLength = parent.getChildren().length;
						// 树未增加新结点(即找到了insertChar在树中对应的结点 ),在下一层次查找下一个char
						if (newChildrenLength == oldChildrenLength) {
							parent = insertNode;
							flag++;
							continue;
							// 树增加新结点，直接将剩余字符追加到新结点上
						} else {
							leafNode = insertNode;
							flag++;
							break;
						}
						// 该子树已到达叶子结点,直接将剩余字符追加到该叶子结点上
						// 当前字符未加入到树中，所以flag不++
					} else {
						leafNode = parent;
						break;
					}
				}
				// 若之前的while循环退出时，尚有字符未处理完，
				// 则直接将剩余字符构成的子树（树的度为1）直接添加到上一个已处理的字符结点上。
				if (flag < chars.length) {
					addRemainderNode(leafNode, chars, flag);
				}
			}
		} else {
			// 暂未实现
		}
	}

	/**
	 * 向目标结点插入子节点.,
	 * 该子节点在目标结点的子节点中的位置应该是有序的。
	 * 假设目标结点A有2个子节点，其键值分别为3,6，要插入子节点的键值为4，则插入后A的子节点从左到右的顺序应为3,4,6
	 *
	 *
	 * @param insertChar
	 *            需要插入的字符
	 * @param node
	 *            目标结点
	 * @return DigitalSearchTrieNode 被插入的结点(其data域为插入的字符insertChar)
	 */
	public DigitalSearchTrieNode intsertNode(char insertChar,
			DigitalSearchTrieNode node) {
		int insertIndex = -1;
		DigitalSearchTrieNode[] children = node.getChildren();
		DigitalSearchTrieNode insertNode = new DigitalSearchTrieNode(insertChar);
		// 查找需要插入的位置
		for (int i = 0; i < children.length; i++) {
			// insertChar大于当前子结点，比较下一个子结点
			if (insertChar - (Character) children[i].getData() > 0) {
				continue;
				// insertChar等于于当前子结点，说明已存在相应结点
			} else if (insertChar - (Character) children[i].getData() == 0) {
				return children[i];
				// insertChar小于当前子结点，说明应该在该位置插入char
			} else {
				insertIndex = i;
			}
		}
		// insertIndex = children.length
		if (insertIndex == -1) {
			insertIndex = children.length;
		}
		// 新的孩子指针数组
		DigitalSearchTrieNode[] newChildren = new DigitalSearchTrieNode[children.length + 1];
		// 插入到孩子数组的最前边
		if (insertIndex == 0) {
			System.arraycopy(children, 0, newChildren, 1, children.length);
			newChildren[0] = insertNode;
			// 插入到孩子数组的最后边
		} else if (insertIndex == children.length) {
			System.arraycopy(children, 0, newChildren, 0, children.length);
			newChildren[children.length] = insertNode;
		} else {
			// 出入到孩子数组的中间的某个位置
			System.arraycopy(children, 0, newChildren, 0, insertIndex);
			System.arraycopy(children, insertIndex, newChildren,
					insertIndex + 1, children.length - insertIndex);
			newChildren[insertIndex] = insertNode;
		}
		// 设置父子关系
		node.setChildren(newChildren);
		insertNode.setParent(node);
		return insertNode;
	}

	/**
	 * 向目标结点插入一棵度为1的子树（以字符数组中的字符为关键字）.
	 * 从chars的beginIndex字符开始，依次作为前一个字符的子节点插入到targetNode结点下。
	 * 即targetNode下添加一棵具有(chars
	 * .length-beginIndex+1)的子树,该子树的度为1,索引越靠前的元素，距离根节点越近,索引越靠后，距离根节点越远。
	 *
	 * @param targetNode
	 *            目标结点
	 * @param chars
	 *            字符数组
	 * @param beginIndex
	 *            开始插入的字符索引
	 */
	public void addRemainderNode(DigitalSearchTrieNode targetNode,
			char[] chars, int beginIndex) {
		DigitalSearchTrieNode preNode = targetNode;
		for (int i = beginIndex; i < chars.length; i++) {
			DigitalSearchTrieNode insertNode = new DigitalSearchTrieNode(
					chars[i]);
			DigitalSearchTrieNode[] children = new DigitalSearchTrieNode[] { insertNode };
			preNode.setChildren(children);
			insertNode.setParent(preNode);
			preNode = insertNode;
			// 最后一个结点，设置为单词的结尾
			if (i == chars.length - 1) {
				insertNode.setWordEnd(true);
			}
		}
	}

	/**
	 * visit.
	 *
	 * @param node comments
	 */
	public void visit(DigitalSearchNode node) {
		System.out.print(node.getData());
		node.setVisited(true);
	}

	/**
	 * 深度优先遍历.
	 *
	 * @param node comments
	 */
	public void dfsTree(DigitalSearchNode node) {
		if (!node.isVisited()) {
			if (STRUCT_TYPE_SIBLING == structType) {
				// todo
			} else if (STRUCT_TYPE_TRIE == structType) {
				DigitalSearchTrieNode dstNode = (DigitalSearchTrieNode) node;
				visit(node);
				if (dstNode.getChildren()!=null) {
					for (DigitalSearchNode child : dstNode.getChildren()) {
						dfsTree(child);
					}
				}
			}
		}

	}

	
	/**
	 * 广度优先遍历.
	 *
	 * @param node comments
	 */
	public void bfsTree(DigitalSearchNode node) {
		if (STRUCT_TYPE_SIBLING == structType) {
			// todo
		} else if (STRUCT_TYPE_TRIE == structType) {
			if (!node.isVisited()) {
				// 访问初始结点
				visit(node);
				DigitalSearchTrieNode dstNode = (DigitalSearchTrieNode) node;
				// 辅助队列
				Queue<DigitalSearchTrieNode> queue = new LinkedList<DigitalSearchTrieNode>();
				queue.add(dstNode);
				// 整个队列如果保存历史记录的话，可以发现，队列，先加入了第一层的结点，再加入了，第二层的结点，再加入了n层的结点。
				while (queue.size()>0) {
					DigitalSearchTrieNode head = queue.remove();
					// 取出头结点，将头结点的孩子加入到队列尾部。
					if (head.getChildren()!=null) {
						for (DigitalSearchTrieNode child : head.getChildren()) {
							if (!child.isVisited()) {
								visit(child);
								queue.add(child);
							}
						}
					}

				}
			}
		}
	}

	/**
	 * deleteKey.
	 *
	 * @param key
	 *            comments
	 */
	public void deleteKey(String key) {
		// todo
	}

	/**
	 * search.
	 *
	 * @param key
	 *            comments
	 * @return true, if successful
	 */
	public boolean search(String key) {
		char[] chars = key.toCharArray();
		int flag = 0;
		DigitalSearchTrieNode pre = (DigitalSearchTrieNode) root;
		while (flag < chars.length) {
			char compareChar = chars[flag];
			DigitalSearchTrieNode[] children = pre.getChildren();
			boolean findChar = false;
			// 在children中查找chars中的第n个字符,children不能为空
			if (children == null) {
				return false;
			} else {
				for (DigitalSearchTrieNode digitalSearchTrieNode : children) {
					if (compareChar == (Character) digitalSearchTrieNode
							.getData()) {
						findChar = true;
						pre = digitalSearchTrieNode;
						break;
					}
				}
				if (!findChar) {
					return false;
				}
			}
			flag++;
		}
		// 此时pre是对应chars中的最后一个字符
		// 检查pre是否是单词结尾
		if (pre.isWordEnd()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将结点初始化成未访问的.
	 *
	 * @param node comments
	 */
	public void initUnVisited(DigitalSearchNode node) {
		if (STRUCT_TYPE_SIBLING == structType) {
			// todo
		} else if (STRUCT_TYPE_TRIE == structType) {
			DigitalSearchTrieNode dstn = (DigitalSearchTrieNode) node;
			dstn.setVisited(false);
			DigitalSearchTrieNode[] children = dstn.getChildren();
			if (children != null) {
				for (DigitalSearchTrieNode digitalSearchTrieNode : children) {
					initUnVisited(digitalSearchTrieNode);
				}
			}
		}
	}

	/**
	 * Gets root.
	 *
	 * @return root
	 */
	public DigitalSearchNode getRoot() {
		return root;
	}

	/**
	 * Sets root.
	 *
	 * @param root comments
	 */
	public void setRoot(DigitalSearchNode root) {
		this.root = root;
	}

	/**
	 * Gets structType.
	 *
	 * @return structType
	 */
	public int getStructType() {
		return structType;
	}

	/**
	 * Sets structType.
	 *
	 * @param structType comments
	 */
	public void setStructType(int structType) {
		this.structType = structType;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//String[] sourceArray = new String[] { "hello","helloworld"};
		String[] sourceArray = new String[] { "hello","helloworld", "xiaowang","xiaozhang"};
		DigitalSearchTree dst = new DigitalSearchTree();
		dst.init(sourceArray, DigitalSearchTree.STRUCT_TYPE_TRIE);
		dst.initUnVisited(dst.getRoot());
		dst.dfsTree(dst.getRoot());
		dst.initUnVisited(dst.getRoot());
		System.out.println("查找单词hello:"+dst.search("hello"));
	    //dst.bfsTree(dst.getRoot());

	}

}
