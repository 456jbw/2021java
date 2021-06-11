package views;

import javax.swing.JDialog;
import javax.swing.plaf.DimensionUIResource;

import controller.Client;
import listener.MyServerListener;

import java.awt.*;
import java.net.InetSocketAddress;

import javax.lang.model.type.NullType;
import javax.swing.*;

public class SearchView extends JDialog{
    private static SearchView searchView;
    public static SearchView getInstance(){
        if (searchView == null){
            searchView = new SearchView();
        }
        return searchView;
    }
    public void init(){
        setTitle("查找到的服务器");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置退出时的行为
        setSize(new Dimension(500,500));
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setVisible(true);
        setFocusable(true);
    }

    public void addButton(String a, InetSocketAddress b){
        JButton tmp = new JButton();
        tmp.setText(a + b);
        tmp.addActionListener(MyServerListener.getInstance());
        this.add(tmp);

    }
    public void showWait(){
        this.getContentPane().removeAll();
        Font font = new Font("宋体", Font.BOLD, 30);
        JLabel jLabel = new JLabel("等待主机开始");
        setLayout(null);
        jLabel.setFont(font);
        jLabel.setVisible(true);
        jLabel.setBounds(150, 100, 250, 100);

        add(jLabel);
        setVisible(true);
        repaint();
    }
}
