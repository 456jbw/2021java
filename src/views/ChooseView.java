package views;

import java.awt.*;
import javax.swing.*;

import listener.MyEnterListener;

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
    void init(){
        this.setTitle("画图");        

        this.setBackground(Color.PINK);  
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
    public void step(){
        confirmBtn.setText("开始");
        
    }
    public static void main(String[] args) {
        var c = getInstance();
        c.init();
        // System.out.println(111);
    }

    public JTextField getTextField() {
        return textField;
    }

    public void setTextField(JTextField textField) {
        this.textField = textField;
    }

    public JButton getConfirmBtn() {
        return confirmBtn;
    }

    public void setConfirmBtn(JButton confirmBtn) {
        this.confirmBtn = confirmBtn;
    }
}