package views;

import java.awt.Component;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 这个类用来提供输入对话文字的文本框和发送按钮的界面,其将出现在画图界面的最下方。
 */
public class Dialogue extends JPanel {
    private static Dialogue dialogue;
    private List<Component> componentsList;
    /**
     * 构造函数 
     */
    public Dialogue(){
        componentsList = new ArrayList<Component>();
    };

    /**
     * 这个函数用来添加要加入到当前界面的组件
     * @param comp 要添加在当前界面的组件,将会传入文本框和发送按钮
     */
    @Override
    public Component add(Component comp) {
        var c = super.add(comp);
        componentsList.add(c);
        return c;
    }
    /**
     * 单例模式获取一个对话框对象
     * @return 一个Dialogue的实例
     */
    public static Dialogue getInstance(){
        if(dialogue==null){
            dialogue = new Dialogue();
        }
        return dialogue;
    }
}
