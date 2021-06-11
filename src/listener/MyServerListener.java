package listener;

import javax.swing.*;

import controller.Client;
import controller.Controller;
import network.discovery.RespondServer;
import views.ChooseView;
import views.Drawboard;
import views.SearchView;

import java.awt.event.*;
import java.io.IOException;

/**
 * 这个类用来确认添加哪一个服务器并发送连接请求
 */
public class MyServerListener implements ActionListener {
    private static MyServerListener myServerListener;

    private MyServerListener() {

    }

    /**
     * 获取一个实例
     * @return 一个MyserverListener实例
     */
    public static MyServerListener getInstance() {
        if (myServerListener == null) {
            myServerListener = new MyServerListener();
        }
        return myServerListener;
    }

    /**
     * 重写的监听方法
     * 将会设置客户端的服务器主机
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        var addr = Client.getInstance().getServersList().get(name);
        Client.getInstance().setServer(addr, Client.getInstance().getName());
        SearchView.getInstance().showWait();
        System.out.println(e.getActionCommand());
    }
}
