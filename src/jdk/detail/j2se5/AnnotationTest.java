package jdk.detail.j2se5;

/**
 * The Class AnnotationTest.
 * 测试注解的使用
 * @date 2013-8-2 18:03:30
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class AnnotationTest {
	
	public AnnotationTest(){
		
	}
	@CounterAnnotation
	public void test(){
		System.out.println("123");
	}
  
	public static void main(String[] args) {
		AnnotationTest a = new AnnotationTest();
		a.test();
	}
}
