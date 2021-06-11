package listener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import views.Drawboard;

/**
 * 这个类用来监听点击颜色选择,将会设置画板的颜色
 */
public class MyColorListener implements ActionListener {
    private static MyColorListener myColorListener;
    public static MyColorListener getInstance(){
        if (myColorListener == null){
            myColorListener = new MyColorListener();
        }
        return myColorListener;
    }
    /**
     * 点击按钮将会画板设置颜色
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton instance = (JButton) e.getSource();
        Color color = instance.getBackground();
        MyDrawListener.getInstance().setColor(color);
    }
}