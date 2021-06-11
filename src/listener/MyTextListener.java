package listener;

import javax.swing.*;

import controller.Controller;
import views.Dialogue;
import views.Drawboard;
import java.awt.event.*;

/**
 * 这个类用来监听发送按钮,从而实现信息的发送
 */
public class MyTextListener implements ActionListener{
    private static MyTextListener listener;
    /**
     * 重写的监听方法 当点击发送之后将会向所有人发送输入到对话框的内容
     * @param e 事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("发送")){
            JTextField jft = (JTextField) Dialogue.getInstance().getComponent(1);
            if (jft.getText().length() != 0){
                Controller.getInstance().addContent(jft.getText());
            }
        }
    }
    /**
     * 单例模式构造方法
     */
    private MyTextListener(){}
    /**
     * 单例模式的实例获得方法
     * @return 监听器
     */
    public static MyTextListener getInstance(){
        if (listener == null){
            listener = new MyTextListener();
        }
        return listener;
    }
}