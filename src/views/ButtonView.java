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
        for (String item : shapeArray) {
            JButton tmp = new JButton(item);
            tmp.addActionListener(listener);
            // tmp.setPreferredSize(new Dimension(80,20));
            northPanel.add(tmp);
        }

        // 添加颜色列表
        // 用于判断设置前景色还是设置背景色
        // 加入到同一个按钮组
        // 添加单选框
        northPanel.add(new Colorlist());
        // 添加线条粗细调整到toolbar的第二行
        southPanel.add(new LineWidth());
        
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
