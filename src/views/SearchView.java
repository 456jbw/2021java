package views;

import javax.swing.JDialog;

import controller.Client;

import java.awt.*;
import java.net.InetSocketAddress;
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
        this.add(tmp);
    }
}