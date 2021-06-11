package shape;

import java.awt.*;

public class ShapeOval extends Shape{
    private static final long serialVersionUID = 1L;
    private int x2, y2;
    /**
     * 椭圆的构造类
     * @param color 椭圆的颜色
     * @param stroke 椭圆的宽度
     */
    public ShapeOval(Color color, Stroke stroke){
        super(color, stroke);
    }

    /**
     * 当鼠标按下之后,将会设置按下去的位置为椭圆的左上角坐标
     * 同时,图形将会进入画图阶段(Middle)
     * @param x 鼠标点击相对于界面的x坐标
     * @param y 鼠标点击相对于界面的y坐标
     */
    @Override
    public void pressStrategy(int x, int y) {
        System.out.println(this.state);
        if (this.state.isStart()){
            setX1(x);
            setY1(y);
            System.out.printf("Press: %d %d\n", x, y);
        }
        if (this.state.isMiddle()){

        }
        state.next();
    }
    /**
     * 拖动鼠标时，记录每次拖动的位置，用于实时绘制椭圆
     * @param x 鼠标点击相对于界面的x坐标
     * @param y 鼠标点击相对于界面的y坐标
     */
    @Override
    public void dragStrategy(int x,int y) {
        // TODO Auto-generated method stub
        if (this.state.isMiddle()){
            setX2(x);
            setY2(y);
        }

    }
    /**
     * @return 椭圆的名字
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "椭圆";
    }
    /**
     * 图形将会进入画图阶段(Middle)，进行椭圆的绘制
     * @param g 画笔，用于在画布上实时画图
     */
    @Override
    public void draw(Graphics2D g){
        g.setColor(this.color);
        g.setStroke(getStroke());
        int minx = Math.min(x1, x2), miny = Math.min(y1, y2);
        int maxx = Math.max(x1, x2), maxy = Math.max(y1, y2);
        if (minx == 0 && miny == 0){
            return;
        }
        g.drawOval(minx, miny, maxx-minx, maxy-miny);
    }
    /**
     * 释放鼠标的策略，用于显示本次椭圆的最终图形
     * @param x 鼠标点击相对于界面的x坐标
     * @param y 鼠标点击相对于界面的y坐标
     */
    @Override
    public void releaseStrategy(int x, int y) {
        if (getState().isMiddle()){
            getState().next();
        }
    }
    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

}
