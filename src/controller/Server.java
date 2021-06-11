package controller;

import java.rmi.registry.LocateRegistry;

import network.call.ServerInterface;

/**
 * 这个类用于启动服务器
 */
public class Server {
    /**
     * 这个函数用于监控和服务器连接的客户端
     */

    private static ServerInterface server;
    
    /**
     * 单例模式获得一个Server实例
     * @return 一个Server实例
     */
    public static ServerInterface getInstance() {
		return getInstance(null);
	}

    /**
     * 服务器注册
     * @param host 服务器的地址
     * @return 一个服务器实例
     */
    public static ServerInterface getInstance(String host) {
		try {
			if (server == null){
				var registry = LocateRegistry.getRegistry(host);
				server = (ServerInterface) registry.lookup("server");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return server;
    }
}
