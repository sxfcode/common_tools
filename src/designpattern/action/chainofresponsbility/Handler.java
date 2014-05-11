package designpattern.action.chainofresponsbility;

/**
 * The Class Handler.
 * 处理器父类。
 *
 * @date 2014-5-7 23:01:25
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools_trunk 1.0
 */
public class Handler {
	
	/** request. */
	private Request request;
	
	/** response. */
	private Response response;

	/** 是否处理请求. */
	private boolean isDone;

	/**
	 * process.
	 *
	 * @param request comments
	 * @param response comments
	 */
	public void process(Request request, Response response) {
		this.request = request;
		this.response = response;
	}

	/**
	 * Gets request.
	 *
	 * @return request
	 */
	public Request getRequest() {
		return request;
	}

	/**
	 * Sets request.
	 *
	 * @param request comments
	 */
	public void setRequest(Request request) {
		this.request = request;
	}

	/**
	 * Gets response.
	 *
	 * @return response
	 */
	public Response getResponse() {
		return response;
	}

	/**
	 * Sets response.
	 *
	 * @param response comments
	 */
	public void setResponse(Response response) {
		this.response = response;
	}

	/**
	 * Checks if is done.
	 *
	 * @return true, if is done
	 */
	public boolean isDone() {
		return isDone;
	}

	/**
	 * Sets done.
	 *
	 * @param isDone comments
	 */
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
}
