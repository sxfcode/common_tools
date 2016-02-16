package core.datastruct;

/**
 * Created by didi on 16/2/7.
 */
public interface SeqInterface {
    public void ensureCapacity(int currentCapacity);
    public int nextCapacity(int currentCapacity);
}
