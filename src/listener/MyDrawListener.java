package listener;

import java.awt.event.*;
import javax.swing.event.*;

import controller.*;

import javax.swing.*;
import java.awt.*;
import shape.Shape;
import main.DrawDemo;
import shape.ShapeFactory;
import state.State;
import views.Drawboard;

/**
 * 这个类是一个观察者,继承了MouseAdapter用于监听主界面中鼠标的各种操作
 * 同时实现了ActionListener接口用于监听点击各种按钮的事件。
 */
public class MyDrawListener extends MouseAdapter implements ActionListener, ChangeListener{
    // private ArrayList list; 
    private Color color = Color.black;
    private Stroke stroke = new BasicStroke(1);
    private Shape shape;
    private ShapeFactory shapeFactory = new ShapeFactory();
    private String type = "矩形";
    private static MyDrawListener myDrawListener;

    private MyDrawListener() {
        super();
    }

    public static MyDrawListener getInstance() {
        if (myDrawListener == null) {
            myDrawListener = new MyDrawListener();
        }
        return myDrawListener;
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider jslider = (JSlider) e.getSource();
        // 将焦点还给绘图区域（没有焦点没有办法响应键盘事件）
        stroke = new BasicStroke(jslider.getValue());
        Drawboard.getInstance().requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stu
        if (e.getSource() instanceof JButton){
                type = e.getActionCommand();
            }
    }
   
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    
    /**
     * 这个方法重写了mousePressed 方法,当鼠标按下之后,如果当前没有设置画图的类型或者是画图的图形状态处于结束状态,将会创建一个新的图形。
     */
    @Override
    public void mousePressed(MouseEvent e){
        int x = (int)e.getX(), y=(int)e.getY();
        if (shape == null || shape.getState().isEnd()){
            System.out.print("create:");
            shape = shapeFactory.createShape(type, color, stroke);
        }
        shape.pressStrategy(x, y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        int x = (int)e.getX(), y=(int)e.getY();
    }
    /**
     * 这个方法重写了mouseDragged 方法,用于监听鼠标拖拽操作
     * 如果当前绘制的图形处于Middle态(未确定),将会根据鼠标的移动位置持续绘制。
     * @param e 用于获取现在鼠标的一些信息参数
     * 
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        int x = (int)e.getX(), y=(int)e.getY();
        if (shape == null){
            return;
        }
        // System.out.printf("Move: %d %d\n", x, y);
        shape.dragStrategy(x, y);
        if (shape.getState().isMiddle()) {
            Drawboard.getInstance().repaint();
        }
    }

    @Override 
    public void mouseReleased(MouseEvent e) {
        int x = (int)e.getX(), y=(int)e.getY();
        shape.releaseStrategy(x, y);
        if (shape.getState().isEnd()){
            Controller.getInstance().addShape(shape);
            // Controller.getInstance().repaint();
        }
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
}