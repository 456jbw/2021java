package controller;

import java.rmi.registry.LocateRegistry;

import network.call.ServerInterface;

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
		try {
			if (server == null){
				var registry = LocateRegistry.getRegistry(null);
				server = (ServerInterface) registry.lookup("server");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        return server;
    }
}
