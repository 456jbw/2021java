package shape;

import java.awt.*;

public class ShapeTextBoard extends Shape{
    private int x2, y2;
    private int []dotXlist=new int[12];
    private int []dotYlist=new int[12];
    public ShapeTextBoard(Color color, Stroke stroke){
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
        return "对话框";
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(this.color);
        g.setStroke(this.stroke);
        int minx = Math.min(x1, x2), miny = Math.min(y1, y2);
        int maxx = Math.max(x1, x2), maxy = Math.max(y1, y2);
        if (minx == 0 && miny == 0){
            return;
        }
        int minedge=Math.min(maxx-minx,maxy-miny);
        g.drawRoundRect(minx, miny, maxx-minx, (maxy-miny)*9/10,minedge/6,minedge/6);
        dotXlist[0]=x1*4/5+x2/5;
        dotXlist[2]=x1*13/20+x2*7/20;
        dotXlist[1]=x1*29/40+x2*11/40;
        dotYlist[0]=y2*9/10+y1/10;
        dotYlist[1]=y2;
        dotYlist[2]=y2*9/10+y1/10;
        g.drawPolygon(dotXlist,dotYlist,3);
        g.setColor(Color.BLUE);//如何获得背景颜色？
        g.drawLine(x1*4/5+x2/5,y2*9/10+y1/10,x1*13/20+x2*7/20,y2*9/10+y1/10);
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