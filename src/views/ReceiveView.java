package views;

import javax.swing.JDialog;

import controller.Client;
import listener.MyEnterListener;

import java.awt.*;
import javax.swing.*;

/**
 * 这个类实现了显示加入协作的客户端名称界面
 * 将会显示所有添加到当前服务器的客户端的名称
 * 并且每添加一个新的客户端,都将会重新刷新一次
 */
public class ReceiveView extends JDialog{
    private static ReceiveView receiveView;
    private int cnt;
    public static ReceiveView getInstance(){
        if (receiveView == null){
            receiveView = new ReceiveView();
        }
        return receiveView;
    }
    /**
     * 界面初始化, 将会显示加入的成员和一个开始按钮
     * 点击开始之后将会开始画图程序
     */
    public void init(){
        setTitle("加入的成员");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置退出时的行为
        setSize(new Dimension(500,500));
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        JButton start = new JButton("开始");
        start.addActionListener(MyEnterListener.getInstance());
        start.setBounds(190, 400, 100, 30);
        add(start);
    }
    /**
     * 用来显示添加到当前服务器的客户端名称,并且重新刷新一遍屏幕
     * @param a 客户端名称
     */
    public void addClient(String a){
        System.out.println("xxxx");
        JLabel tmp = new JLabel();
        cnt++;
        Font font = new Font("宋体", Font.BOLD, 15);
        tmp.setText("用户:"+a);
        tmp.setFont(font);
        tmp.setBounds(200, cnt*32, 100, 20);
        this.add(tmp);
        this.repaint();
    }
}
