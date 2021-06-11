package listener;

import javax.swing.*;

import controller.Controller;
import views.Dialogue;
import views.Drawboard;
import java.awt.event.*;

public class MyTextListener implements ActionListener{
    private static MyTextListener listener;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("发送")){
            JTextField jft = (JTextField) Dialogue.getInstance().getComponent(1);
            if (jft.getText().length() != 0){
                Controller.getInstance().addContent(jft.getText());
            }
        }
    }
    private MyTextListener(){}
    public static MyTextListener getInstance(){
        if (listener == null){
            listener = new MyTextListener();
        }
        return listener;
    }
}