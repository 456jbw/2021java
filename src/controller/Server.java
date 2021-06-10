package controller;

import java.util.ArrayList;
import java.util.List;

import shape.Shape;

public class Server {
    /**
     * 这个函数用于监控和服务器连接的客户端
     */

    private List<Client> clientsList;
    private static Server server;
    private Server(){
        clientsList = new ArrayList<Client>();
    }
    
    /**
     * 单例模式获得一个Server实例
     * @return 一个Server实例
     */
    public static Server getInstance() {
        if (server == null){
            server = new Server();
        }
        return server;
    }
    public void addClient(Client client){
        clientsList.add(client);
    }
    void WatchConnect(){
        // while(true){
        //     Client client = new Client();
        //     clientList.add(client);
        // }
    }
    /**
     * 发送消息
     */
    public void sendShape(Shape shape){
        for (Client client: clientsList){
            client.addShape(shape);
        }
    }
    public void sendRepaint(){
        for (Client client: clientsList){
            client.repaint();
        }
    }

    public void sendContent(String content){
        for (Client client: clientsList){
            client.addContent(content);
            client.repaint();
        }
    }

    public void sendUpdata(Shape shape){

    }
}