package core.datastruct;

/**
 * Created by didi on 16/2/6.
 */
public abstract class DSBase {
    protected static int DEFAULT_CAPACITY = 100;
    public abstract void init();
    public abstract void destroy();
    public abstract void clear();
    public abstract void isEmpty();
    public abstract int size();
}
