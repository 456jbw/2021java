package views;

import javax.swing.JDialog;
import javax.swing.plaf.DimensionUIResource;

import controller.Client;
import listener.MyEnterListener;

import java.awt.*;
import java.net.InetSocketAddress;
import javax.swing.*;

public class ReceiveView extends JDialog{
    private static ReceiveView receiveView;
    public static ReceiveView getInstance(){
        if (receiveView == null){
            receiveView = new ReceiveView();
        }
        return receiveView;
    }
    public void init(){
        setTitle("加入的成员");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置退出时的行为
        setSize(new Dimension(500,500));
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setVisible(true);
        setFocusable(true);
        JButton start = new JButton("开始");
        start.addActionListener(MyEnterListener.getInstance());
        start.setPreferredSize(new Dimension(100, 40));
        add(start);
    }
    
    public void addClient(String a, InetSocketAddress b){
        JLabel tmp = new JLabel();
        tmp.setText(a + b);
        this.add(tmp);
    }
}