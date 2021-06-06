package shape;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import state.State;

/**
 * 这个类是矩形类的具体实现,继承了Shape类
 */
public class ShapeRect extends Shape{
    private int x2, y2;
    public ShapeRect(Color color, Stroke stroke){
        super(color, stroke);
    }

    @Override
    public void clickStrategy(int x,int y) {
        // TODO Auto-generated method stub
        System.out.println(this.state);
        if (this.state.isStart()){
            setX1(x);
            setY1(y);
            System.out.printf("Click: %d %d\n", x, y);
        }
        if (this.state.isMiddle()){

        }
        state.next();
        
    }
    @Override
    public void moveStrategy(int x,int y) {
        // TODO Auto-generated method stub
        if (this.state.isMiddle()){
            setX2(x);
            setY2(y);
        }
        
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
        return "矩形";
    }

    @Override
    public void draw(Graphics2D g){
        g.setColor(this.color);
        g.setStroke(this.stroke);
        int minx = Math.min(x1, x2), miny = Math.min(y1, y2);
        int maxx = Math.max(x1, x2), maxy = Math.max(y1, y2);
        g.drawRect(minx, miny, maxx-minx, maxy-miny);
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