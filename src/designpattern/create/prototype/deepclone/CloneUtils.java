package designpattern.create.prototype.deepclone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * The Class CloneUtils.
 * 该深度克隆的方式有可能存在性能问题，未测试性能问题,请注意。
 *
 * @date 2014-4-24 20:09:58
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CloneUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> T cloneObj(T o) throws IOException, ClassNotFoundException{
		T cloneObj = null;
		ByteArrayInputStream bin = null;
		ByteArrayOutputStream bout = null;
		
		bout = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bout);
		oos.writeObject(o);
		oos.close();
		
		bin  = new ByteArrayInputStream(bout.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bin);
		cloneObj = (T)ois.readObject();
		ois.close();
		return cloneObj;
	}

}
