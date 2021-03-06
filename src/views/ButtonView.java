package views;

import javax.swing.*;

import listener.MyDrawListener;
import listener.MySaveListener;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * 这个类实现了最上层按钮的布局, 能够显示图形、橡皮擦、颜色选择器、画笔宽度选项并选择
 */
public class ButtonView extends JPanel{

	private MyDrawListener listener;
    private static ButtonView buttonView;
    /**
     * 构造方法 
     */
    private ButtonView(){
    }

    /**
     * 单例模式 获得一个ButtonView的实例
     * @return ButtonView 的实例
     */
    public static ButtonView getInstance(){
        if (buttonView == null){
            buttonView = new ButtonView();
        }
        return buttonView;
    }


    /**
     * 图像界面初始化
     * 将会显示所有可以选择绘制的图形
     * 可以绘制的图形有"矩形", "圆形", "直线", "椭圆", "爱心", "菱形","五边形", "六边形",  
     * "四角星", "五角星", "六角星", "对话框","上下箭头", "左右箭头","圆角矩形","等腰梯形",
     * "平行四边形", "直角三角形", "等腰三角形"
     * 点击相对应的按钮之后,将能够绘制对应的图像
     * 同时加载了颜色选择器和线条宽度选择器
     */
    public void init(){
        this.setLayout(new BorderLayout());

        // System.out.println(MyImage.getPencilIcon(30,30).getDescription());
            
        String[] shapeArray = { "矩形", "圆形", "直线", "椭圆", "爱心", "菱形",
                "五边形", "六边形",  "四角星", "五角星", "六角星", "对话框",
                "上下箭头", "左右箭头","圆角矩形","等腰梯形",
                "平行四边形", "直角三角形", "等腰三角形" };

        // 添加所有的按钮并添加按钮点击事件监听
        int i=0;
        GroupLayout thisLayout = new GroupLayout(this);
        this.setLayout(thisLayout);

        JPanel shapebutton=new JPanel();
        for (String item:shapeArray) {
            JButton tmp = new JButton(item);
            tmp.setContentAreaFilled(false);
            tmp.setFocusPainted(false);
            tmp.addActionListener(listener);
            // tmp.setPreferredSize(new Dimension(80,20));
            if(i<8){
                shapebutton.setLayout(null);
                tmp.setBounds(8+75*i,4,70,20);
                i++;
                shapebutton.add(tmp);
            }
            else if (i<15){
                shapebutton.setLayout(null);
                tmp.setBounds(8+86*(i-8),27,82,20);
                i++;
                shapebutton.add(tmp);
            }
            else{
                shapebutton.setLayout(null);
                tmp.setBounds(8+105*(i-15),53,100,20);
                i++;
                shapebutton.add(tmp);
            }
        }
        shapebutton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        shapebutton.setVisible(true);
        this.setLayout(null);
        shapebutton.setBounds(70,3,610,78);
        this.add(shapebutton);

        //画笔按钮
        JButton buttonP = new JButton("画笔",MyImage.getPencilIcon(30,30));
        buttonP.setFont(new Font("宋体",1,0));
        buttonP.addActionListener(listener);
        buttonP.setContentAreaFilled(false);
        buttonP.setFocusPainted(false);
        buttonP.setBounds(20,5,32,32);
        //橡皮按钮
        JButton buttonE = new JButton("橡皮",MyImage.getEraserIcon(30,30));
        buttonE.setFont(new Font("宋体",1,0));
        buttonE.addActionListener(listener);
        buttonE.setContentAreaFilled(false);
        buttonE.setFocusPainted(false);
        buttonE.setBounds(20,45,32,32);


        //保存按钮
        JButton buttonC = new JButton("保存",MyImage.getSaveIcon());
        buttonC.setFont(new Font("宋体",1,0));
        buttonC.addActionListener(MySaveListener.getInstance());
        buttonC.setContentAreaFilled(false);
        buttonC.setFocusPainted(false);
        //buttonC.setBorderPainted(false);
        buttonC.setBounds(1000,15,40,40);

        this.add(buttonP);
        this.add(buttonE);
        this.add(buttonC);
        // 添加颜色列表
        // 用于判断设置前景色还是设置背景色
        ButtonGroup bg = new ButtonGroup();
        JLabel back = new JLabel("颜色");
        back.setBounds(710,5,80,20);
        //bg.add(back);
        // 添加单选框
        this.add(back);
        Colorlist cl=new Colorlist();
        cl.setBounds(750,10,120,30);
        this.add(cl);
        // 添加线条粗细调整到toolbar的第二行
        JLabel back1 = new JLabel("线条宽度");

        back1.setBounds(700,45,60,20);

        this.add(back1);

        LineWidth lw=new LineWidth();

        lw.setBounds(720,45,280,60);

        this.add(lw);
    }

    /**
     * 获得监听ButtonView的MyDrawListener 类型的listener
     * @return 返回一个MyDrawListener 实例
     * @see MyDrawListener
     */
    public MyDrawListener getListener() {
        return listener;
    }

    /**
     * 设置ButtonView 的动作监听器
     * @param listener MyDrawListener类型的一个实例
     */
    public void setListener(MyDrawListener listener) {
        this.listener = listener;
    }
}
