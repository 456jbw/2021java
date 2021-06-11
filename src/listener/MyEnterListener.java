package listener;

import javax.swing.*;

import controller.Controller;
import controller.Client;

import network.discovery.RespondServer;
import views.ChooseView;
import views.Drawboard;
import java.awt.event.*;
import java.io.IOException;

/**
 * 这个类实现了新建协作,加入,名称输入的监听器
 */
public class MyEnterListener implements ActionListener {
    private static MyEnterListener myEnterListener;

    private MyEnterListener() {

    }

    public static MyEnterListener getInstance() {
        if (myEnterListener == null) {
            myEnterListener = new MyEnterListener();
        }
        return myEnterListener;
    }

    /**
     * 用来监听点击的是新建协作还是加入
     * 新建协作将会创建一个服务器,等待其他人加入, 并切换为开始,再次点击将会开始画图
     * 加入将会搜寻所有的服务器
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (ChooseView.getInstance().getTextField().getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "请输入昵称", null, JOptionPane.PLAIN_MESSAGE);
            return;
        }
        if (e.getActionCommand().equals("新建协作")) {
            try {
                Controller.getInstance().Wait(ChooseView.getInstance().getTextField().getText());
                ChooseView.getInstance().step1();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else if (e.getActionCommand().equals("加入")){
			Client.getInstance().setName(
					ChooseView.getInstance().getTextField().getText()
					);
            try {
                Controller.getInstance().Find();
                ChooseView.getInstance().step3();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        else if (e.getActionCommand().equals("开始")){
            Controller.getInstance().Start();
        }
    }
    
}
