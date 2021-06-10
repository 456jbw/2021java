package listener;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import views.Drawboard;

public class MyColorListener implements ActionListener {
    private static MyColorListener myColorListener;
    public static MyColorListener getInstance(){
        if (myColorListener == null){
            myColorListener = new MyColorListener();
        }
        return myColorListener;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton instance = (JButton) e.getSource();
        Color color = instance.getBackground();
        MyDrawListener.getInstance().setColor(color);
    }
}