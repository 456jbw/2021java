package views;
import javax.swing.*;

import listener.MyDrawListener;
import java.awt.*;

public class ButtonView extends JPanel{
    private MyDrawListener listener;
    private static ButtonView buttonView;
    private ButtonView(){
    }

    public static ButtonView getInstance(){
        if (buttonView == null){
            buttonView = new ButtonView();
        }
        return buttonView;
    }

    public void init(){
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        this.setLayout(new BorderLayout());

        String[] shapeArray = { "矩形", "圆形", "直线", "椭圆", 
        "圆角矩形", "画笔", "直角三角形", "等腰三角形", 
        "菱形", "五边形", "六边形", "上下箭头", 
        "左右箭头", "四角星", "五角星", "六角星", 
        "平行四边形", "等腰梯形", "对话框", "爱心" };

        // 添加所有的按钮并添加按钮点击事件监听
        int i=0;
        GroupLayout thisLayout=new GroupLayout(this);
        this.setLayout(thisLayout);

        JPanel shapebutton=new JPanel();
        for (String item : shapeArray) {
            JButton tmp = new JButton(item);
            tmp.addActionListener(listener);
            // tmp.setPreferredSize(new Dimension(80,20));
            if(i<7){
                shapebutton.setLayout(null);
                tmp.setBounds(1+85*i,1,80,20);
                i++;
                shapebutton.add(tmp);
            }
            else if (i<14){
                shapebutton.setLayout(null);
                tmp.setBounds(1+85*(i-7),25,80,20);
                i++;
                shapebutton.add(tmp);
            }
            else{
                shapebutton.setLayout(null);
                tmp.setBounds(1+85*(i-14),49,80,20);
                i++;
                shapebutton.add(tmp);
            }
        }
        shapebutton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        shapebutton.setVisible(true);
        this.setLayout(null);
        shapebutton.setBounds(1,1,610,69);
        this.add(shapebutton);

        // 添加颜色列表
        // 用于判断设置前景色还是设置背景色
        ButtonGroup bg = new ButtonGroup();
        JLabel back = new JLabel("背景色");
        back.setBounds(850,1,60,20);
        //bg.add(back);
        // 添加单选框
        this.add(back);
        Colorlist cl=new Colorlist();
        cl.setBounds(910,1,80,20);
        this.add(cl);
        // 添加线条粗细调整到toolbar的第二行
        JLabel back1 = new JLabel("线条宽度");
        back1.setBounds(850,25,60,20);
        this.add(back1);
        LineWidth lw=new LineWidth();
        lw.setBounds(910,25,150,60);
        this.add(lw);
        
        this.add(northPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.EAST);
    }

    public MyDrawListener getListener() {
        return listener;
    }

    public void setListener(MyDrawListener listener) {
        this.listener = listener;
    }
}