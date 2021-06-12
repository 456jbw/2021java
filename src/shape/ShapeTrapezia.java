package shape;

import java.awt.*;
/**
 * 这个类是矩形类的具体实现,继承了等腰梯形类
 */
public class ShapeTrapezia extends Shape{
    private static final long serialVersionUID = 1L;
    private int x2, y2;
    private int []dotXlist=new int[4];
    private int []dotYlist=new int[4];
    /**
     * 等腰梯形的构造类
     * @param color 等腰梯形的颜色
     * @param stroke 等腰梯形的宽度
     */
    public ShapeTrapezia(Color color, Stroke stroke){
        super(color, stroke);
    }

    /**
     * 当鼠标按下之后,将会设置按下去的位置为等腰梯形的左上角坐标
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
     * 拖动鼠标时，记录每次拖动的位置，用于实时绘制等腰梯形
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
     * @return 等腰梯形的名字
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "等腰梯形";
    }
    /**
     * 图形将会进入画图阶段(Middle)，进行等腰梯形的绘制
     * @param g 画笔，用于在画布上实时画图
     */
    @Override
    public void draw(Graphics2D g){
        g.setColor(this.color);
        g.setStroke(getStroke());
        dotXlist[0]=x1;
        dotXlist[1]=x1*7/10+x2*3/10;
        dotXlist[2]=x2*7/10+x1*3/10;
        dotXlist[3]=x2;
        dotYlist[0]=y2;
        dotYlist[1]=y1;
        dotYlist[2]=y1;
        dotYlist[3]=y2;
        g.drawPolygon(dotXlist,dotYlist,4);
    }
    /**
     * 释放鼠标的策略，用于显示本次等腰梯形的最终图形
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
