package jdk.basic;

/**
 * The Class CommandArgs.
 *
 * @date 2013-6-14 11:15:29
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0
 */
public class CommandArgs {
	
	public static void error(String msg){
		System.err.println(msg);
		System.exit(1);
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		String usageMsg="Usage: CommandArgs [options]\n"
			+ "Where [options] are :\n"
			+ " -help      显示帮助信息\n"
			+ " -n <name>  设置参数的名字\n"
			+ " -v <value> 设置参数的值";
		String name = null;
		String value = null;
		for (int i = 0; i < args.length; i++) {
			if(args[i].equals("-n")){
				if((i+1)==args.length){
					error("Error: -n  requires an arguments");
				}
				name = args[++i];
			}else if(args[i].equals("-v")){
				if((i+1)==args.length){
					error("Error: -v  requires an arguments");
				}
				value = args[++i];
			}else if (args[i].equalsIgnoreCase("-help")) {
				System.out.println(usageMsg);
				System.exit(0);
			} else {
				error("Error: argument not recognized: " + args[i]);
			}
			
		}
		System.out.println("[命令行解析结果] name: " + name + "; value: " + value);
	}

}
