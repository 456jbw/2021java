package shape;
import java.awt.Color;
import java.awt.Stroke;
/**
 * 这个类使用了工厂模式,是Shape类的工厂类,用于构建不同形状的图形。
 * @author Costwen 
 */
public class ShapeFactory {
    public ShapeFactory(){
        
    }
    /**
     * 这个方法实现了工厂类的创建方法,将会根据不同的输入创建好返回的类型。
     * @param type 图形的形状
     * @param color 图形的颜色
     * @param stroke 图形的形状
     * @return 返回创建好的图形类,如果创建的类型不存在返回null
     */
    public Shape createShape(String type, Color color, Stroke stroke){
        Shape shape;
        switch(type){
            case "矩形":
                shape = new ShapeRect(color, stroke);
                return shape;
            case "圆形":
                shape = new ShapeCircle(color, stroke);
                return shape;
            case "直线":
                shape =new ShapeLine(color,stroke);
                return shape;
            case "椭圆":
                shape =new ShapeOval(color,stroke);
                return shape;
            case "圆角矩形":
                shape=new ShapeRoundRect(color,stroke);
                return shape;
            case "画笔":
                shape=new Pen(color,stroke);
                return shape;
            case "直角三角形":
                shape=new ShapeRightTri(color,stroke);
                return shape;
            case "等腰三角形":
                shape=new ShapeEqualTri(color,stroke);
                return shape;
            case "菱形":
                shape=new ShapeRhombus(color,stroke);
                return shape;
            case "五边形":
                shape=new ShapePentagon(color,stroke);
                return shape;
            case "六边形":
                shape=new ShapeHexagon(color,stroke);
                return shape;
            case "上下箭头":
                shape=new ShapeUpDownArrow(color,stroke);
                return shape;
            case "左右箭头":
                shape=new ShapeLeftRightArrow(color,stroke);
                return shape;
            case "四角星":
                shape=new ShapeFourStar(color,stroke);
                return shape;
            case "六角星":
                shape=new ShapeSixStar(color,stroke);
                return shape;
            case "平行四边形":
                shape=new ShapeParallelogram(color,stroke);
                return shape;
            case "等腰梯形":
                shape=new ShapeTrapezia(color,stroke);
                return shape;
            case "五角星":
                shape=new ShapeFiveStar(color,stroke);
                return shape;
            case "对话框":
                shape=new ShapeTextBoard(color,stroke);
                return shape;
            case "爱心":
                shape=new ShapeHeart(color,stroke);
                return shape;
            default:
                return null;
            }
    }
}