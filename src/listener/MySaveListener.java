package listener;

import javax.imageio.ImageIO;
import javax.swing.*;

import controller.Controller;
import views.Drawboard;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedOutputStream;


/**
 * 这个类实现了绘制保存图片的界面
 */
public class MySaveListener implements ActionListener {
    private static MySaveListener listener;

    /**
     * 重写的actionPerformed方法
     * 点击保存之后,将会显示保存地址的选择,选择完成之后,将会保存绘制图片并提示
     * @param e 事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("保存")) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            jfc.showDialog(new JLabel(), "选择");
            File file = jfc.getSelectedFile();
            FileOutputStream out;
            try {
                out = new FileOutputStream(file);
                var im = Drawboard.getInstance().getBfimg();
                ImageIO.write(im, "png", out);
                JOptionPane.showMessageDialog(null, "保存成功", null, JOptionPane.PLAIN_MESSAGE);
                
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }
    /**
     * 单例模式构造方法
     */
    private MySaveListener(){}
    /**
     * 单例模式获得实例的方法
     * @return 返回监听器
     */
    public static MySaveListener getInstance(){
        if (listener == null){
            listener = new MySaveListener();
        }
        return listener;
    }
}