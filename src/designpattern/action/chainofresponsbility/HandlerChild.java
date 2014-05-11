package designpattern.action.chainofresponsbility;

/**
 * The Class HandlerChild.
 * 具体的处理器.
 *
 * @date 2014-5-7 22:49:26
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class HandlerChild extends Handler {

	@Override
	public void process(Request request, Response response) {
		super.process(request, response);
		// 添加实际的业务处理类
		
	}
}
