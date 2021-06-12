package views;
import javax.swing.JPanel;
import javax.swing.JSlider;

import listener.*;
/**
     * 这个类实现了线宽选择器界面
     * 将会有一个可以拖动的Slider选择具体的线宽
 */
public class LineWidth extends JPanel {
    private static final long serialVersionUID = 15100151;
    /**
     * 构造函数 将会设置Silder的各种属性,这里设置为 1, 20, 1
     * 同时添加监听器监听滑动的动作
     */
    public LineWidth() {
        JSlider slider = new JSlider(1, 20, 1);
        slider.setMajorTickSpacing(4);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        MyDrawListener listener = MyDrawListener.getInstance();
        slider.addChangeListener(listener);
        this.add(slider);
    }
}
