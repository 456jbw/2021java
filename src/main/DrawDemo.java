package main;

import java.awt.*;
import javax.swing.*;
import views.ButtonView;
import views.Dialogue;
import views.Drawboard;
import listener.*;

/**
 * 这个类用于设置画图的主窗口和绘制图像
 * 将会显示画板,颜色选择器,线条选择器,对话框
 * @author Costwen
 */
public class DrawDemo extends JFrame{

    /**
     * 这个方法用于加载和显示主窗口的各种按钮
     * 将会显示画板,颜色选择器,线条选择器,对话框
     */
    public void init(){

        this.setLayout(new BorderLayout());

        MyDrawListener listener = MyDrawListener.getInstance();

        JPanel drawboard = Drawboard.getInstance();

        //设置画板
        this.setTitle("画图");
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置大小

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置退出时的行为
        this.setSize(new Dimension(1080,800));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        drawboard.addMouseListener(listener);
        drawboard.addMouseMotionListener(listener);
        drawboard.setBackground(Color.WHITE);
        drawboard.setBounds(0,90,1080,630);
        drawboard.setVisible(true);

        this.getContentPane().add(drawboard);

        // 设置按钮布局
        ButtonView buttonView = ButtonView.getInstance();
        buttonView.setListener(listener);
        buttonView.init();
        buttonView.setVisible(true);
        buttonView.setBounds(0,0,1080,90);


        // 设置对话框布局
        Dialogue dialog =  Dialogue.getInstance();
        dialog.setBounds(0,720,1080,80);
        dialog.setVisible(true);
        dialog.setLayout(null);
        drawboard.setLayout(null);

        JButton b = new JButton("发送");
        b.setBounds(330, 8, 70, 30);

        JTextField text = new JTextField();
        text.setBounds(10, 8, 300, 30);

        MyTextListener textListener = MyTextListener.getInstance();

        dialog.add(b);
        dialog.add(text);

        b.addActionListener(textListener);
        text.addActionListener(textListener);

        // 添加布局

        this.setLayout(null);
        //this.add(drawboard, BorderLayout.SOUTH);
        //this.add(buttonView, BorderLayout.NORTH);
        this.add(dialog);
        this.add(drawboard);
        this.add(buttonView);
        this.setVisible(true);
        repaint();
    }
}