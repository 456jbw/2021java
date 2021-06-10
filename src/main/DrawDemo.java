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
    public static void main(String[] args) {
        DrawDemo s = new DrawDemo();
        s.init();
    }
    /**
     * 这个方法用于加载和显示主窗口的各种按钮
     */
    public void init(){
        // String [] tb= {"直线","椭圆","矩形","多边形","画笔","圆","圆角矩形","填充3D矩形",
		// "填充弧","填充圆","刷子","橡皮","喷子"};

        this.setLayout(new BorderLayout());

        MyDrawListener listener = MyDrawListener.getInstance();
        listener.setDemo(this);

        JPanel drawboard = new Drawboard();
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
      
        ButtonView buttonView = new ButtonView(listener); // 按钮布局
        buttonView.init();
        buttonView.setVisible(true);
        buttonView.setPreferredSize(new Dimension(0,40));
        
        drawboard.setLayout(null);
        
        JButton b = new JButton("发送");
        b.setBounds(250, 940, 70, 30);

        JTextField text = new JTextField();
        text.setBounds(30, 940, 220, 30);

        drawboard.add(b);
        drawboard.add(text);

        this.add(drawboard, BorderLayout.SOUTH);  
        this.add(buttonView, BorderLayout.NORTH);
        
        this.setVisible(true);
    }
}