package shape;

import java.awt.*;

public class ShapeRhombus extends Shape{
    private int x2, y2;
    private int []dotXlist=new int[4];
    private int []dotYlist=new int[4];
    public ShapeRhombus(Color color, Stroke stroke){
        super(color, stroke);
    }

    /**
     * 当鼠标按下之后,将会设置按下去的位置为矩形的左上角坐标
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
    @Override
    public void dragStrategy(int x,int y) {
        // TODO Auto-generated method stub
        if (this.state.isMiddle()){
            setX2(x);
            setY2(y);
        }

    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "菱形";
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(this.color);
        g.setStroke(this.stroke);
        dotXlist[0]=x1;
        dotXlist[1]=(x1+x2)/2;
        dotXlist[2]=x2;
        dotXlist[3]=(x1+x2)/2;
        dotYlist[0]=(y2+y1)/2;
        dotYlist[1]=y1;
        dotYlist[2]=(y2+y1)/2;
        dotYlist[3]=y2;
        g.drawPolygon(dotXlist,dotYlist,4);
    }

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