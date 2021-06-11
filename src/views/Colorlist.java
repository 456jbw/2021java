package views;
import javax.swing.*;

import listener.MyColorListener;

import java.awt.*;

/**
 * 这个类实现了颜色选取的界面, 能够在前7个选项之中选择
 * Color.BLACK, Color.BLUE, Color.YELLOW, Color.GREEN, Color.PINK, Color.RED, Color.CYAN 7中固定好的颜色
 * 并且能够在最后一个按钮之中自定义自己的颜色选项
 */
public class Colorlist extends JPanel {
    static final long serialVersionUID = 1471001741;
    
    /**
     * 构造函数初始化了基本界面,一共加载了8个颜色选择的按钮,其中前面的7个用来选择预设好的
     * Color.BLACK, Color.BLUE, Color.YELLOW, Color.GREEN, Color.PINK, Color.RED, Color.CYAN7中颜色
     * 最后一个按钮用来自定义,点击之后将会弹出一个可以供自定义选择颜色的界面框
     */

    public Colorlist() {
        // 得到监听器实例
        MyColorListener listener = MyColorListener.getInstance();
        // 为列表使用二行四列的栅格布局
        this.setLayout(new GridLayout(2, 4, 4, 4));
        // 通过颜色数组快速构建前七个按钮
        Color[] colorArray = { Color.BLACK, Color.BLUE, Color.YELLOW, Color.GREEN, Color.PINK, Color.RED, Color.CYAN };
        for (Color item : colorArray) {
            JButton tmp = new JButton();
            tmp.setBackground(item);
            // 为该按钮添加点击监听，详见EventListener->actionPerformed
            tmp.addActionListener(listener);
            this.add(tmp);
        }
        // 最后一个按钮是自定义颜色
        JButton customColor = new JButton(MyImage.getColorIcon());
        customColor.setBackground(Color.WHITE);
        // 为该按钮加入与其它按钮相同的监听
        customColor.addActionListener(listener);
        // 点击弹出颜色对话框，将选中颜色设置为背景色
        // 注意：经过测试，多个ActionListener的情况下会优先执行后添加的
        customColor.addActionListener(e -> {
            Color selectedColor = JColorChooser.showDialog(null, "自定义颜色", Color.BLACK);
            if (selectedColor == null) {
                selectedColor = Color.WHITE;
            }
            customColor.setBackground(selectedColor);
        });
        this.add(customColor);
    }
}
