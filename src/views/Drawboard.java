package views;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import shape.Shape;
import listener.*;

public class Drawboard extends JPanel{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private BufferedImage bfimg;
    private Graphics bfpen;
    private static Drawboard drawboard;
    private static List<Component> componentList;

    @Override
    public Component add(Component comp) {
        Component c = super.add(comp);
        if (componentList == null){
            componentList = new ArrayList<Component>();
        }
        componentList.add(comp);
        return c;
    }

    public static Drawboard getInstance(){
        if (drawboard == null){
            drawboard=new Drawboard();
        }
        return drawboard;
    }
    
    
    /**
     * 这个方法重写了JFrame的画图方法,将会把所有绘制的图形全部绘制一次。
     * 同时使用了缓冲bfimg 预先加载好了背景
     * @param g 用于显示图像的画笔工具。
     */
    @Override
    public void paint(Graphics g){
        
        if (bfimg == null){
            System.out.println("bfimg");
            bfimg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
            bfpen = bfimg.getGraphics();
        }
        
        List <Shape> shapesList = ((MyDrawListener)this.getMouseListeners()[0]).getShapesList();
        bfpen.setColor(getBackground());//设置背景颜色
        bfpen.fillRect(0, 0, getWidth(), getHeight());

        for (Shape shape: shapesList){
            shape.draw((Graphics2D)bfpen);
        }
        g.drawImage(bfimg, 0, 0, this);
        for (Component c: componentList){
            c.repaint();
        }
    }
}