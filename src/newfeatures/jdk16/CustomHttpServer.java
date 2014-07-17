package newfeatures.jdk16;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


/**
 * The Class CustomHttpServer.
 * 简易的httpServer
 * 这里需要sun的jar包 lib\http-20070405.jar
 *
 * @date 2014-7-17 23:10:30
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,trunk 1.0
 */
public class CustomHttpServer {
	private HttpServer hs = null;
	
	public CustomHttpServer() throws IOException{
		hs = HttpServer.create(new InetSocketAddress(8888), 0);
		hs.createContext("/",new  CutomHandler());
		hs.setExecutor(null);
	}
	
	public void start(){
		hs.start();
	}
	public void stop(){
		hs.stop(0);
	}
	
	class CutomHandler implements HttpHandler{

		@Override
		public void handle(HttpExchange he) throws IOException {
			System.out.println("remote host is:"+he.getRemoteAddress().getAddress().getHostAddress());
			OutputStream os = he.getResponseBody();
			String response = "<font color='#ff0000'>come on baby</font>";
			he.sendResponseHeaders(200, response.length());
			os.write(response.getBytes());
			os.close();
		}
		
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		CustomHttpServer chs = new CustomHttpServer();
		chs.start();

	}
	
}
