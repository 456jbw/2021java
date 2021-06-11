package views;
import javax.swing.JPanel;
import javax.swing.JSlider;

import listener.*;
/**
 * 这个类实现了线宽选择器界面
 */
public class LineWidth extends JPanel {
    private static final long serialVersionUID = 15100151;
    /**
     * 构造函数 将会初始化界面
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
