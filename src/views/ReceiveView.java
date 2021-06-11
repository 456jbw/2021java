package views;

import javax.swing.JDialog;
import javax.swing.plaf.DimensionUIResource;

import controller.Client;
import listener.MyEnterListener;

import java.awt.*;
import javax.swing.*;

public class ReceiveView extends JDialog{
    private static ReceiveView receiveView;
    private int cnt;
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
        setLayout(null);
        setVisible(true);
        JButton start = new JButton("开始");
        start.addActionListener(MyEnterListener.getInstance());
        start.setBounds(190, 400, 100, 30);
        add(start);
    }
    
    public void addClient(String a){
        JLabel tmp = new JLabel();
        cnt++;
        Font font = new Font("宋体", Font.BOLD, 15);
        tmp.setText("用户:"+a);
        tmp.setFont(font);
        tmp.setBounds(200, cnt*32, 100, 20);
        this.add(tmp);
    }
}
