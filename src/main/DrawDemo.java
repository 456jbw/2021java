package main;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;

import shape.Shape;
/**
 * 这个类用于设置画图的主窗口和绘制图像
 * @author Costwen
 */
public class DrawDemo extends JFrame{
    private BufferedImage bfimg;
    private Graphics bfpen;
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
        String [] tb= {"矩形", "圆形"};
        JRadioButton t = new JRadioButton("加粗");
        ButtonGroup group = new ButtonGroup();
        
        MyDrawListener listner = new MyDrawListener(this);
        for (int i = 0; i < tb.length; i++){
            JRadioButton button = new JRadioButton(tb[i]);
            button.setSelected(true);
            group.add(button);
            this.add(button);
            button.addActionListener(listner);
        }

        // JColorChooser c = new JColorChooser(Color.blue);

        // this.add(c);
        this.add(t);
        this.setTitle("画图");
        this.setLayout(new FlowLayout()); // 设置布局
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // 设置大小
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置退出时的行为
        this.setVisible(true); // 设置可见性
        this.addMouseListener(listner); 
        this.addMouseMotionListener(listner);

    }
    /**
     * 这个方法重写了JFrame的画图方法,将会把所有绘制的图形全部绘制一次。
     * 同时使用了缓冲bfimg 预先加载好了背景
     * @param g 用于显示图像的画笔工具。
     */
    @Override
    public void paint(Graphics g){
        
        if (bfimg == null){
            System.out.println("bfimg");
            bfimg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
            bfpen = bfimg.getGraphics();
        }
        
        List <Shape> shapesList = ((MyDrawListener)this.getMouseListeners()[0]).getShapesList();
        bfpen.setColor(getBackground());//设置背景颜色
        bfpen.fillRect(0, 0, getWidth(), getHeight());

        for (Shape shape: shapesList){
            shape.draw((Graphics2D)bfpen);
        }
        g.drawImage(bfimg, 0, 0, this);
    }
}