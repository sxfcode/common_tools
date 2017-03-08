package core.algorithm.sort.select;

import core.algorithm.sort.Sort;
import core.algorithm.sort.SortUtils;


/**
 * 堆排序,完全二叉树.
 * 大根堆,小根堆
 * 对一棵完全二叉树,从根节点开始编号,根节点编号为1.
 * 则编号为i(i>=1)的节点
 * 父母节点为i/2
 * 左孩子2i，右孩子为2i+1
 *
 */

public class HeapSort implements Sort {


    /**
     * 堆排序
     * 大根堆
     * @param s
     * @return
     */
    public static int[] heapSort(int[] s){
        // flag表示正在查找的节点
        int flag =s.length;
        while(flag>1){
            // n为节点编号,使用n是为了便于理解
            int n = flag;
            // 最底层,最右边的叶子节点是左孩子,缺少兄弟节点(当前节点的父节点只有一个左孩子)特殊处理下.
            if(flag%2==0){
                //n/2-2,n-1为节点的索引值
                makeMax(s,n/2-1,n-1,-1);
                n=n-1;
            }

            // 编号最小为1,代表根节点,执行完毕后根节点最大
            // 当前节点的父节点都有两个孩子.
            for(int i = n;i>=1 &&(i-1)>1;i=i-2){
                makeMax(s,(i-1)/2-1,i-2,i-1);
            }
            // 交换根节点和大根堆的最后一个节点flag
            SortUtils.swap(s,0,flag-1);
            flag--;
        }
        return s;

    }

    public static void makeMax(int[] s,int root,int left,int right){
        if(right!=-1){
            if(s[right]>s[root]){
                SortUtils.swap(s,root,right);
            }
        }
        if(s[left]>s[root]){
            SortUtils.swap(s,root,left);
        }
    }

    @Override
    public int[] doSort(int[] sourceArray) {
        return heapSort(sourceArray);
    }
}
