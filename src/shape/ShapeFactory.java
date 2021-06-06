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
        if (type.equals("矩形")){
            shape = new ShapeRect(color, stroke);
            return shape;
        }
        return null;
    }
}