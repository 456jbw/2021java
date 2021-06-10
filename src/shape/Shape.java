package shape;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

import state.State;

/**
 * 这个类是所有图形类的基类,如果需要绘制图像,需要继承这个基类。
 * @author Costwen
 */
public abstract class Shape implements Serializable{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	protected int x1, y1; // 
    protected Color color; // color
    protected int stroke;
    protected State state;
    /**
     * 构造函数
     * 
     * @param color 用于设置图形的颜色
     * @param stroke 用于设置图形线条的大小
     */
    public Shape(Color color,Stroke stroke){
        this.color = color;
        this.stroke = (int)((BasicStroke)stroke).getLineWidth();
        this.state = new State();
    }
/**
 * 这个方法重写了Shape类的pressStrategy方法,用于实现鼠标点击屏幕时,相对应的图形的行为。
 * 
 * @param x 这次点击时鼠标x轴的位置
 * @param y 这次点击时鼠标y轴的位置
 */
    public void clickStrategy(int x, int y){

    };

    public void moveStrategy(int x,int y){

    };
    
    public void dragStrategy(int x, int y){

    };

    public void releaseStrategy(int x, int y){

    };

    public void pressStrategy(int x,int y){
    };

    public abstract String toString();
    public abstract void draw(Graphics2D g);

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Stroke getStroke() {
        return new BasicStroke(stroke);
    }

    public void setStroke(Stroke stroke) {
        this.stroke = (int)((BasicStroke)stroke).getLineWidth();
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
}
