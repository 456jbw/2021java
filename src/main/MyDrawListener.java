package main;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Stroke;

import java.awt.BasicStroke;

import shape.Shape;
import main.DrawDemo;
import main.DrawMoveThread;
import shape.ShapeFactory;
import state.State;

/**
 * 这个类是一个观察者,继承了MouseAdapter用于监听主界面中鼠标的各种操作
 * 同时实现了ActionListener接口用于监听点击各种按钮的事件。
 */
public class MyDrawListener extends MouseAdapter implements ActionListener{
    // private ArrayList list; 
    private List<Shape> shapesList;
    private Color color = Color.black;
    private JColorChooser colorChooser;
    private Stroke stroke = new BasicStroke(10);
    private Shape shape;
    private ShapeFactory shapeFactory = new ShapeFactory();
    private DrawDemo demo;
    private String type = "矩形";

    public MyDrawListener(DrawDemo demo){
        super();
        this.demo = demo;
        shapesList = new ArrayList<Shape>();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stu
        if (e.getSource() instanceof JRadioButton){
                type = e.getActionCommand();
            }
    }
    
    /**
     * 这个方法重写了mouseClicked 方法,当鼠标点击之后,如果当前没有设置画图的类型或者是画图的图形状态处于结束状态,将会创建一个新的图形。
     */
    @Override
    public void mouseClicked(MouseEvent e) {
       
        int x = (int)e.getX(), y=(int)e.getY();
        if (shape == null || shape.getState().isEnd()){
            shape = shapeFactory.createShape(type, color, stroke);
            shapesList.add(shape);
        }
        shape.clickStrategy(x, y);
    }
    @Override
    public void mousePressed(MouseEvent e){
        double x = e.getX(), y = e.getY();
    }
    /**
     * 这个方法重写了mouseMoved 方法,用于监听鼠标移动
     * 如果当前绘制的图形处于Middle态(未确定),将会根据鼠标的移动位置持续绘制。
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        int x = (int)e.getX(), y=(int)e.getY();
        if (shape == null){
            return;
        }
        // System.out.printf("Move: %d %d\n", x, y);
        shape.moveStrategy(x, y);
        if (shape.getState().isMiddle()) {
            // DrawMoveThread thread = new DrawMoveThread("draw", this.demo);
            // thread.start();
            // thread.run();
            this.demo.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        int x = (int)e.getX(), y=(int)e.getY();
        System.out.printf("Drag: %d %d\n", x, y);
        // shape.dragStrategy(x, y);
    }


    @Override 
    public void mouseReleased(MouseEvent e) {
        int x = (int)e.getX(), y=(int)e.getY();
        System.out.printf("Release: %d %d\n", x, y);
    }

    public List<Shape> getShapesList() {
        return shapesList;
    }

    public void setShapesList(List<Shape> shapesList) {
        this.shapesList = shapesList;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }


}