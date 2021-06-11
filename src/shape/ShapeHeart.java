package shape;

import java.awt.*;

public class ShapeHeart extends Shape{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int x2, y2;
    private int []dotXlist=new int[370];
    private int []dotYlist=new int[370];
    /**
     * 笔的构造类
     * @param color 心形的颜色
     * @param stroke 心形的宽度
     */
    public ShapeHeart(Color color, Stroke stroke){
        super(color, stroke);
    }

    /**
     * 当鼠标按下之后,将会设置按下去的位置为爱心的左上角坐标
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
     * 拖动鼠标时，记录每次拖动的位置，用于实时绘制心形
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
     * @return 爱心的名字
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "爱心";
    }
    /**
     * 图形将会进入画图阶段(Middle)，进行心形的绘制
     * @param g 画笔，用于在画布上实时画图
     */
    @Override
    public void draw(Graphics2D g){
        g.setColor(this.color);
        g.setStroke(getStroke());
        double x,y;
        int k=0;
        for(double t = 1;t<=360;t++){
            x= Math.cos(Math.toRadians(t));
            y= -(Math.sin(Math.toRadians(t)) + Math.cbrt(Math.pow(Math.cos(Math.toRadians(t)), 2.0)));
            dotXlist[k] = (int)(x*100)*(x2-x1)/200+x1;
            dotYlist[k] = (int)(y*100)*(y2-y1)/200+y1;
            k++;
        }
        g.drawPolygon(dotXlist,dotYlist,360);
    }
    /**
     * 释放鼠标的策略，用于显示本次画心形的最终图形
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
