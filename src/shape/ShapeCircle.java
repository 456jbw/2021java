package shape;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Stroke;


import shape.Shape;

/**
 * 这个类是圆形的具体实现类,继承了基类Shape用于绘制图形
 */
public class ShapeCircle extends Shape{
    int radius; // 半径
    int x2,y2; // 另一个点的坐标
    /**
     * 构造函数
     * @param color 用于设置图形的颜色
     * @param stroke 用于设置图形线条的大小
     */
    public ShapeCircle(Color color, Stroke stroke){
        super(color, stroke);
    }

    /**
     * 该方法重写了Shape类的pressStrategy方法。用于设置当鼠标按下画图时相对应图形的属性设置
     * @param x 这次点击时鼠标x轴的位置
     * @param y 这次点击时鼠标y轴的位置
     */
    @Override
    public void pressStrategy(int x, int y) {
        if (this.state.isStart()){
            setX1(x);
            setY1(y);
        }
        state.next();
    }
    @Override
    public void dragStrategy(int x, int y) {
        // TODO Auto-generated method stub
        super.dragStrategy(x, y);
        if (this.state.isMiddle()){
            setX2(x);
            setY2(y);
        }
    }
    
    @Override
    public void draw(Graphics2D g) {
        g.setColor(this.color);
        g.setStroke(this.stroke);

        if (x2 == 0 && y2 == 0){
            return;
        }
        setRadius((int)Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)));
        System.out.printf("X: %d  Y: %d   ", x1, y1);
        System.out.printf("Radius: %d\n",radius);
        g.drawOval(x1-radius, y1-radius, 2*radius, 2*radius);
    }

    @Override
    public String toString() {
        return "圆形";
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
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