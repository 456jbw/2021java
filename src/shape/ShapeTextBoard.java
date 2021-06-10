package shape;

import java.awt.*;

import views.Drawboard;

public class ShapeTextBoard extends Shape{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
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
        g.setStroke(getStroke());
        int minx = Math.min(x1, x2), miny = Math.min(y1, y2);
        int maxx = Math.max(x1, x2), maxy = Math.max(y1, y2);
        if (minx == 0 && miny == 0){
            return;
        }
        int minedge=Math.min(maxx-minx,maxy-miny);
        g.drawRoundRect(minx, miny, maxx-minx, maxy-miny,minedge/6,minedge/6);

        dotXlist[0]=minx*4/5+maxx/5;
        dotXlist[2]=minx*13/20+maxx*7/20;
        dotXlist[1]=minx*29/40+maxx*11/40;
        dotYlist[0]=maxy;
        dotYlist[1]=maxy+(maxy-miny)/10;
        dotYlist[2]=maxy;
        g.drawPolygon(dotXlist,dotYlist,3);

        g.setColor(Drawboard.getInstance().getBackground());
        g.drawLine(minx*4/5+maxx/5,maxy,minx*13/20+maxx*7/20,maxy);
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