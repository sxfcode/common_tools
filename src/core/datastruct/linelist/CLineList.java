package core.datastruct.linelist;

import core.datastruct.DSBase;

/**
 * Created by didi on 16/2/7.
 * 线性表定义
 * 1.存在唯一一个第一个元素
 * 2.存在唯一一个最后一个元素
 * 3.除第一个,每个元素只有一个前驱
 * 4.除最后一个,每个元素只有一个后继
 */
public abstract class CLineList extends DSBase {
    public abstract Object prior(int i);
    public abstract Object next(int i);
    public abstract void insert(int i,Object o);
    public abstract void delete(int i);
    public abstract void traverse();
}
