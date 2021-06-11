package main;

import java.awt.*;
import javax.swing.*;
import views.ButtonView;
import views.Drawboard;
import listener.*;

/**
 * 这个类用于设置画图的主窗口和绘制图像
 * @author Costwen
 */
public class DrawDemo extends JFrame{

    /**
     * 这个方法用于加载和显示主窗口的各种按钮
     */
    public void init(){

        this.setLayout(new BorderLayout());

        MyDrawListener listener = MyDrawListener.getInstance();

        JPanel drawboard = Drawboard.getInstance();

        //设置画板
        this.setTitle("画图");
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置大小

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置退出时的行为
        this.setSize(new Dimension(1080,720));
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        drawboard.addMouseListener(listener);
        drawboard.addMouseMotionListener(listener);
        drawboard.setBackground(Color.WHITE);
        drawboard.setPreferredSize(new Dimension(1080,600));
        drawboard.setVisible(true);

        this.getContentPane().add(drawboard);

        // 设置按钮布局
        ButtonView buttonView = ButtonView.getInstance();
        buttonView.setListener(listener);
        buttonView.init();
        buttonView.setVisible(true);
        buttonView.setPreferredSize(new Dimension(1,120));


        // 设置对话框布局
        drawboard.setLayout(null);

        JButton b = new JButton("发送");
        b.setBounds(250, 550, 70, 30);

        JTextField text = new JTextField();
        text.setBounds(30, 550, 220, 30);

        MyTextListener textListener = MyTextListener.getInstance();
        
        drawboard.add(b);
        drawboard.add(text);

        b.addActionListener(textListener);
        text.addActionListener(textListener);

        // 添加布局
        this.add(drawboard, BorderLayout.SOUTH);  
        this.add(buttonView, BorderLayout.NORTH);
        this.setVisible(true);
        repaint();
    }
}