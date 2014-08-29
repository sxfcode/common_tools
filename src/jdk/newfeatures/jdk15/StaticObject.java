package jdk.newfeatures.jdk15;

/**
 * The Class StaticObject.
 *
 * @date 2014-7-17 14:16:03
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class StaticObject {
	
	/** privateField. */
	private static int privateField = 100;
	
	/** publicField. */
	public  static int publicField = 100;
	
	/**
	 * printName.
	 */
	public static void printName(){
		System.out.println("my name is StaticObject");
	}
	public static void printName(String msg){
		System.out.println("my name is StaticObject msg:"+msg);
	}
	
	/**
	 * Gets privateField.
	 *
	 * @return privateField
	 */
	public static int getPrivateField() {
		return privateField;
	}
	
	/**
	 * Sets privateField.
	 *
	 * @param privateField comments
	 */
	public static void setPrivateField(int privateField) {
		StaticObject.privateField = privateField;
	}
	
	/**
	 * Gets publicField.
	 *
	 * @return publicField
	 */
	public static int getPublicField() {
		return publicField;
	}
	
	/**
	 * Sets publicField.
	 *
	 * @param publicField comments
	 */
	public static void setPublicField(int publicField) {
		StaticObject.publicField = publicField;
	}

}
