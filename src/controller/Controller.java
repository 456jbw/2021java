package controller;

import shape.Shape;
import views.Drawboard;
import views.ReceiveView;

import java.io.IOException;
import java.rmi.RemoteException;
import javax.swing.JTextField;

import network.call.ClientController;
import network.call.ServerController;
import network.discovery.DiscoveryProber;
import network.discovery.RespondServer;

/**
 * 这个类实现了客户端的一些操作的控制,将会向服务器发送相对应的指令
 */
public class Controller {
	private static Controller controller;
	private Thread serverThread;
	private Thread searchThread;
	/**
	 * 向服务器发送绘制好的图像
	 * @param shape 绘制的图像
	 */
	public void addShape(Shape shape) {
		try {
			Server.getInstance().sendShape(shape);
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	public static Controller getInstance() {
		if (controller == null){
			controller = new Controller();
		}
		return controller;
	}
	
	/**
	 * 向服务器发起添加对话的历史
	 * @param content 对话内容
	 */
	public void addContent(String content) {
		String name = Client.getInstance().getName() + ":";
		try {
			Server.getInstance().sendContent(name+content);
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		((JTextField)Drawboard.getInstance().getComponent(1)).setText("");
	}
	/**
	 * 向服务器发送重绘请求
	 */
	void repaint() {
		try {
			Server.getInstance().sendRepaint();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 向服务器发送加入请求,并控制客户端进入等待状态
	 * @param name
	 * @throws IOException
	 */
	public void Wait(String name) throws IOException {
		serverThread = new Thread(new RespondServer(name, (short)1099));
		serverThread.start();
		ServerController.start();
		// assert server has already started
		ServerController.mayGetController()
			.setCallback(ReceiveView.getInstance()::addClient);

		Client.getInstance().setName(name);
		Client.getInstance().setState(1);
		var clientwrapper = new ClientController(Client.getInstance());
		Server.getInstance().registerClient(clientwrapper, name);
	}
	
	/**
	 * 结束搜索服务器进程,并向服务器发送开始请求
	 */
	public void Start(){
		if (serverThread != null){
			serverThread.interrupt();
		}
		try {
			Server.getInstance().sendStart();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 创建一个搜索线程,将会搜索可用服务器
	 * @throws IOException
	 */
	public void Find() throws IOException {
		searchThread = new Thread(
				new DiscoveryProber(
					null,
					Client.getInstance()::addServer));
		searchThread.start();
		Client.getInstance().setState(3);
	}

}
