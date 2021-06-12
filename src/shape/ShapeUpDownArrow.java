package shape;


import java.awt.*;
/**
 * 这个类是矩形类的具体实现,继承了上下箭头类
 */
public class ShapeUpDownArrow extends Shape {
    private static final long serialVersionUID = 1L;
    private int x2, y2;
    private int []dotXlist=new int[7];
    private int []dotYlist=new int[7];
    /**
     * 上下箭头的构造类
     * @param color 上下箭头的颜色
     * @param stroke 上下箭头的宽度
     */
    public ShapeUpDownArrow(Color color, Stroke stroke){
        super(color, stroke);
    }

    /**
     * 当鼠标按下之后,将会设置按下去的位置为上下箭头的左上角坐标
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
     * 拖动鼠标时，记录每次拖动的位置，用于实时绘制上下箭头
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
     * @return 上下箭头的名字
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "上下箭头";
    }
    /**
     * 图形将会进入画图阶段(Middle)，进行上下箭头的绘制
     * @param g 画笔，用于在画布上实时画图
     */
    @Override
    public void draw(Graphics2D g){
        g.setColor(this.color);
        g.setStroke(getStroke());
        dotXlist[0]=x1;
        dotXlist[1]=x1*3/4+x2/4;
        dotXlist[2]=x1*3/4+x2/4;
        dotXlist[3]=x1/4+x2*3/4;
        dotXlist[4]=x1/4+x2*3/4;
        dotXlist[5]=x2;
        dotXlist[6]=(x1+x2)/2;
        dotYlist[0]=y1/2+y2/2;
        dotYlist[1]=y1/2+y2/2;
        dotYlist[2]=y1;
        dotYlist[3]=y1;
        dotYlist[4]=y2/2+y1/2;
        dotYlist[5]=y1/2+y2/2;
        dotYlist[6]=y2;
        g.drawPolygon(dotXlist,dotYlist,7);
    }
    /**
     * 释放鼠标的策略，用于显示本次上下箭头的最终图形
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
