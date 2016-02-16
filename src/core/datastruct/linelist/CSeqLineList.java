package core.datastruct.linelist;

import core.datastruct.SeqInterface;

import java.util.Stack;

/**
 * Created by didi on 16/2/7.
 */
public class CSeqLineList extends CLineList implements SeqInterface {
    private Object[] elements;
    private int size = 0;
    private int capacity = DEFAULT_CAPACITY;


    @Override
    public Object prior(int i) {
        if(i!=0){
            return elements[i-1];
        }
        return null;
    }

    @Override
    public Object next(int i) {
        if(i!=size){
            return elements[i-1];
        }
        return null;
    }

    @Override
    public void insert(int i, Object o) {


    }

    @Override
    public void delete(int i) {

    }

    @Override
    public void traverse() {

    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void clear() {

    }

    @Override
    public void isEmpty() {

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void ensureCapacity(int currentCapacity) {

    }

    @Override
    public int nextCapacity(int currentCapacity) {
        return 0;
    }
}
