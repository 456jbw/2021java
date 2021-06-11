package views;

import java.awt.*;
import java.awt.Dialog.ModalityType;

import javax.swing.*;

import listener.MyEnterListener;

/**
 * 这个类用来实现开始时选择加入或者时新建协作的界面
 * 用户选择新建协作,将会在局域网内创建一个服务器,并创建自己的客户端,然后进入开始界面,当加入其他客户端加入之后由主机发起开始绘图
 * 用户选择加入,将会创建一个客户端。然后进入查找服务器的界面。
 */
public class ChooseView extends JFrame{
    private static ChooseView chooseView;
    private JTextField textField;
    private JButton confirmBtn;
    public static ChooseView getInstance(){
        if (chooseView == null){
            chooseView = new ChooseView();
        }
        return chooseView;
    }
    /**
     * 界面初始化,这个界面将会显示"画图",并且提供了输入了昵称框。如果没有输入自己的昵称将不会允许新建协作或者是加入
     * 同时将会显示新建协作按钮和加入的按钮
     */
    public void init(){
        this.setTitle("画图");        

        this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 设置退出时的行为
        this.setSize(new Dimension(1080,720));
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        var listener = MyEnterListener.getInstance();

        Font font = new Font("宋体", Font.BOLD, 50);
        JLabel jLabel = new JLabel("画图");
        jLabel.setFont(font);
        jLabel.setVisible(true);
        jLabel.setBounds(1080/2-70, 720/2-250, 150, 150);

        JButton jButton1 = new JButton("新建协作");
        
        jButton1.setBounds(1080/2-70, 720/2-70, 100, 50);
        jButton1.addActionListener(listener);
        
        confirmBtn = jButton1;

        JButton jButton2 = new JButton("加入");
        jButton2.setBounds(1080/2-70, 720/2+70, 100, 50);
        jButton2.addActionListener(listener);


        JLabel jname = new JLabel("昵称:");
    
        Font font1 = new Font("宋体", Font.BOLD, 20);
        jname.setFont(font1);
        jname.setVisible(true);
        jname.setBounds(1080/2 - 130, 720/2-120, 100, 25);

        JTextField textField = new JTextField();

        this.textField = textField;
        textField.setBounds(1080/2-70, 720/2-120, 100, 25);

        this.add(jname);
        this.add(jLabel);
        this.add(jButton1);
        this.add(jButton2);
        this.add(textField);    
        this.setVisible(true);
    }
    /**
     * 这个函数的调用在点击"新建协作"之后
     * 将"新建协作"切换为"开始",当点击开始则可以直接开始画图
     * 同时将会打开申请加入当前主机的界面()。
     */
    public void step1(){
        confirmBtn.setText("开始");
        ReceiveView.getInstance().init();
    }
    /**
     * 这个函数的调用发生在点击"加入之后"
     * 将会打开搜寻可用服务器主机的界面
     */
    public void step3(){
        SearchView.getInstance().init();
    }

    /**
     * 这个函数用来获取昵称框
     * @return 返回一个JTextField 实例,为该界面的昵称框
     */
    public JTextField getTextField() {
        return textField;
    }

    /**
     * 这个函数用来设置昵称框
     * @param textField 要添加到当前界面的文本框
     */
    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    /**
     * 这个函数用来获取确认这个按钮
     * @return 确认按钮的实例
     */
    public JButton getConfirmBtn() {
        return confirmBtn;
    }

    /**
     * 这个函数用来设置确认按钮
     * @param confirmBtn 要设置的按钮
     */
    public void setConfirmBtn(JButton confirmBtn) {
        this.confirmBtn = confirmBtn;
    }
    
}