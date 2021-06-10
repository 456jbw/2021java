package views;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import listener.*;
// 调整线宽
public class LineWidth extends JPanel {
    private static final long serialVersionUID = 15100151;

    public LineWidth() {
        this.add(new JLabel("线条粗细"));
        JSlider slider = new JSlider(1, 20, 1);
        slider.setMajorTickSpacing(4);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        MyDrawListener listener = MyDrawListener.getInstance();
        slider.addChangeListener(listener);
        this.add(slider);
    }
}
