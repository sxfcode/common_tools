package designpattern.action.chainofresponsbility;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class ControllCenter.
 * 责任链控制中心，控制请求和响应的传递.
 *
 * @date 2014-5-7 22:50:09
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class ControllCenter {
	public static List<HandlerChild> hanlderChain = new ArrayList<HandlerChild>();
	private static ControllCenter instance = null;
	private ControllCenter(){
	}
	// 获取示例。
	public static ControllCenter getInstance(){
		if(instance ==null){
			instance = new ControllCenter();
		}
		return instance;
	}
	
	// 在处理链上注册处理器
	public void registerHandler(HandlerChild handler){
		hanlderChain.add(handler);
	}
	
	public void process(Request request, Response response) {
		Request request_local=request;
		Response response_local =response;
		for (HandlerChild handler : hanlderChain) {
			handler.process(request_local, response_local);
			// 传递处理结果
			request_local = handler.getRequest();
			response_local = handler.getResponse();
			// 若当前处理器处理完，则跳出责任链，直接返回，否则继续责任链向下走.
			if(handler.isDone()){
				break;
			}
		}
	}

}
