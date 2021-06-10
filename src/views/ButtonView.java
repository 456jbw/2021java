package views;
import javax.swing.*;
import listener.MyDrawListener;
import java.awt.*;

public class ButtonView extends JPanel{
    MyDrawListener listener;
    public ButtonView(MyDrawListener listener){
        // this.setLayout();
        // this.setBackground(Color.RED);
        this.listener = listener;
    }
    public void init(){
        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        this.setLayout(new BorderLayout());

        String[] shapeArray = { "铅笔", "直线", "矩形", "圆", "文本", "橡皮擦", "帮助" };
        // 添加所有的按钮并添加按钮点击事件监听
        for (String item : shapeArray) {
            JButton tmp = new JButton(item);
            tmp.addActionListener(listener);
            northPanel.add(tmp);
        }

        // 添加颜色列表
        // 用于判断设置前景色还是设置背景色
        ButtonGroup bg = new ButtonGroup();
        JRadioButton back = new JRadioButton("背景色");
        // 加入到同一个按钮组
        bg.add(back);
        // 添加单选框
        northPanel.add(back);
        northPanel.add(new Colorlist());
        // 添加线条粗细调整到toolbar的第二行
        southPanel.add(new LineWidth());
        
        this.add(northPanel, BorderLayout.WEST);
        this.add(southPanel, BorderLayout.EAST);
    }
}