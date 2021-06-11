package views;

import javax.swing.JDialog;

import controller.Client;
import listener.MyServerListener;

import java.awt.*;
import java.net.InetSocketAddress;

import javax.swing.*;

/**
 * 这个类实现了搜索可用服务器的界面,将会显示所有的搜寻到的服务器名称。
 * 点击相对应的按钮之后将会显示等待主机开始
 * @author costwen
 */
public class SearchView extends JDialog{
    private static SearchView searchView;
    public static SearchView getInstance(){
        if (searchView == null){
            searchView = new SearchView();
        }
        return searchView;
    }
    /**
     * 界面初始化,设置了标题和界面布局。
     */
    public void init(){
        setTitle("查找到的服务器");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);// 设置退出时的行为
        setSize(new Dimension(500,500));
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        setVisible(true);
        setFocusable(true);
    }

    /**
     * 添加搜索到的服务器到界面上
     * @param a 服务器名称
     * @param b 服务器地址
     */
    public void addButton(String a, InetSocketAddress b){
        JButton tmp = new JButton();
        tmp.setText(a + b);
        tmp.addActionListener(MyServerListener.getInstance());
        this.add(tmp);

    }
    /**
     * 选择服务器之后,除主机外,其他客户端将显示等待主机开始。
     */
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
