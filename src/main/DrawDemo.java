package main;

import java.awt.*;
import java.awt.Color;
import shape.Shape;

import javax.swing.*;
import javax.swing.border.Border;

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
        String [] tb= {"矩形","圆形","直线","椭圆","圆角矩形"};
        JRadioButton t = new JRadioButton("加粗");
        ButtonGroup group = new ButtonGroup();
        
        MyDrawListener listner = new MyDrawListener(this);
        for (int i = 0; i < tb.length; i++){
            JRadioButton button = new JRadioButton(tb[i]);
            button.setSelected(true);
            group.add(button);
            button.addActionListener(listner);
            this.add(button, BorderLayout.NORTH);
        }

        JPanel drawboard = new Drawboard(listner);

        // this.add(t, BorderLayout.NORTH);
        this.setTitle("画图");
        // this.setLayout(new FlowLayout()); // 设置布局
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置大小
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置退出时的行为
        this.setVisible(true); // 设置可见性
        
        this.add(drawboard, BorderLayout.CENTER);
        drawboard.setBackground(Color.CYAN);
        drawboard.setVisible(true);
        drawboard.setSize(200, 400);
    }
}