package controller;

import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.rmi.RemoteException;

import main.DrawDemo;
import shape.Shape;
import views.ChooseView;
import views.ReceiveView;
import views.SearchView;
import network.call.ClientController;

/**
 * 这个类实现了本地的客户端,记录了该客户端的绘制历史,搜寻到的服务器,连接的服务器,当前设置的昵称。还有发言历史。
 * @author costwen
 */
public class Client {
    private final DrawDemo demo;
    private String name;
    private int State;
    private List<Shape> shapesList;
    private Deque<String> contentsList;
    private static Client client;
    private HashMap<String, InetSocketAddress> serversList;

    /**
     * 构造函数
     */
    private Client() {
        shapesList = new ArrayList<Shape>();
        contentsList = new ArrayDeque<String>();
        serversList = new HashMap<String, InetSocketAddress>();
        demo = new DrawDemo();
    }

    /**
     * 用于记录扫描到的服务器。
     * @param a 服务器名字
     * @param b 服务器地址
     */
    public void addServer(String a, InetSocketAddress b){
        serversList.put(a+b, b);
        SearchView.getInstance().addButton(a, b);
        SearchView.getInstance().repaint();
    }
    /**
     * 添加一个绘制记录到历史
     * @param shape
     */
    public void addShape(final Shape shape) {
        shapesList.add(shape);
    }   
    /**
     * 添加一个发言到历史记录,并且将会只保存5条
     * @param content
     */
    public void addContent(final String content) {
        if (contentsList.size() == 5){
            contentsList.removeFirst();
        }
        contentsList.add(content);
    }
    /**
     * 重新绘制屏幕
     */
    public void repaint() {
        demo.repaint();
    }
    /**
     * 显示最开始的选择界面
     */
    public void show(){
        var c = ChooseView.getInstance();
        c.init();
    }
    /**
     * 向服务器发送开始请求
     */
	public void sendStart() {
		(new Thread(new Runnable() {
			public void run() {
				Client.getInstance().start();
			}
		})).start();
	}
    /**
     * 自己开始画图进程
     */
    public void start() {
        ChooseView.getInstance().dispose();
        SearchView.getInstance().dispose();
        ReceiveView.getInstance().dispose();
        demo.init();
    }

    /**
     * 单例模式
     * @return 返回一个Client实例
     */
    public static Client getInstance() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public List<Shape> getShapesList() {
        return shapesList;
    }

    public void setShapesList(final List<Shape> shapesList) {
        this.shapesList = shapesList;
    }

    public Deque<String> getContentsList() {
        return contentsList;
    }

    public void setContentsList(Deque<String> contentsList) {
        this.contentsList = contentsList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public HashMap<String, InetSocketAddress> getServersList() {
        return serversList;
    }

    public void setServersList(HashMap<String, InetSocketAddress> serversList) {
        this.serversList = serversList;
    }
    /**
     * 设置与自己相连接的主机
     */
	public void setServer(InetSocketAddress addr, String name){
		// 根据传入的服务器地址设置主机
		try {
			var clientwrapper = new ClientController(Client.getInstance());
			Server.getInstance(addr.getHostName())
				.registerClient(clientwrapper, name);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
