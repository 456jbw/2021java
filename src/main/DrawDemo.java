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

        drawboard.addMouseListener(listener);
        drawboard.addMouseMotionListener(listener);
        drawboard.setBackground(Color.CYAN);
        drawboard.setVisible(true);
        drawboard.setPreferredSize(new Dimension(0,1000));
        
        // this.add(t, BorderLayout.NORTH);
        this.setTitle("画图");
        // this.setLayout(new FlowLayout()); // 设置布局
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置大小
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置退出时的行为
      
        // 设置按钮布局
        ButtonView buttonView = ButtonView.getInstance();
        buttonView.setListener(listener);
        buttonView.init();
        buttonView.setVisible(true);
        buttonView.setPreferredSize(new Dimension(0,40));
        // 设置对话框布局
        drawboard.setLayout(null);
        
        JButton b = new JButton("发送");
        b.setBounds(250, 940, 70, 30);

        JTextField text = new JTextField();
        text.setBounds(30, 940, 220, 30);

        MyTextListener textListener = MyTextListener.getInstance();
        
        drawboard.add(b);
        drawboard.add(text);

        b.addActionListener(textListener);
        text.addActionListener(textListener);

        // 添加布局
        this.add(drawboard, BorderLayout.SOUTH);  
        this.add(buttonView, BorderLayout.NORTH);
        this.setVisible(true);
    }
}