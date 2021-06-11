package views;

import java.awt.*;
import javax.swing.*;

import controller.Client;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import shape.Shape;
import listener.*;

/**
 * 这个类实现了画板界面,画板界面提供了一个画板可以让客户端在上面绘制预设好的图形
 */
public class Drawboard extends JPanel{
    /**
     *
     */
    private BufferedImage bfimg;
    private Graphics2D bfpen;
    private static Drawboard drawboard;
    private static List<Component> componentList;

    /**
     * 构造函数
     */
    private Drawboard(){
        
    }

    /**
     * 重写了添加组件的方法,从而能够实现用自定义的列表记录添加的按钮
     */
    @Override
    public Component add(Component comp) {
        Component c = super.add(comp);
        if (componentList == null) {
            componentList = new ArrayList<Component>();
        }
        componentList.add(comp);
        return c;
    }
    /**
     * 单例模式 
     * @return 返回一个Drawboard的实例
     */
    public static Drawboard getInstance() {
        if (drawboard == null) {
            drawboard = new Drawboard();
        }
        return drawboard;
    }

    /**
     * 这个方法重写了JFrame的画图方法,将会把记录在历史记录之中所有已经绘制的图形全部绘制一次。 
     * 使用了一个缓冲图片,首先将背景重置和绘制等操作绘制在缓冲图片上。
     * 然后再一次性将缓冲图片加载到当前的画布之上,避免了屏幕的闪烁
     * 同时根据聊天记录也会在画板的相对应区域渲染聊天记录
     * 同时为了实现客户端自身能够实时看到绘制的图形的预览效果,单独绘制了仍处于绘图Middle态的图形
     * @param g 用于显示图像的画笔工具。
     */
    @Override
    public void paint(Graphics g) {

        if (bfimg == null) {
            System.out.println("bfimg");
            bfimg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_4BYTE_ABGR_PRE);
            bfpen = (Graphics2D) bfimg.getGraphics();
            
            Font font = new Font("宋体", Font.PLAIN, 18);
            bfpen.setFont(font);

            bfpen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        List<Shape> shapesList = Client.getInstance().getShapesList();
        bfpen.setColor(getBackground()); //设置背景颜色
        bfpen.fillRect(0, 0, getWidth(), getHeight());

        Shape curShape = MyDrawListener.getInstance().getShape();

        if (curShape != null && curShape.getState().isMiddle()){
            curShape.draw(bfpen);
        }

        for (Shape shape : shapesList) {
            shape.draw(bfpen);
        }
        
        //绘制聊天内容
        int i = 0;
        Iterator<String> iterator = Client.getInstance().getContentsList().iterator();
        int size = Client.getInstance().getContentsList().size();

        bfpen.setColor(Color.BLACK); //设置颜色
        while(iterator.hasNext()){
            String s = (String)iterator.next();
            bfpen.setColor(Drawboard.getInstance().getBackground());
            bfpen.fillRect(30,510 + 21*(5 - size+i),300,50);
            bfpen.setColor(Color.BLACK); //设置文字颜色
            bfpen.drawString(s, 30, 530 + 21*(5 - size+i));

            i++;
        }

        g.drawImage(bfimg, 0, 0, this);
        // 绘制按钮
//        for (Component c : componentList) {
//           c.repaint();
//       }
    }
    /**
     * 获取处于缓冲区的图片,也就是绘制的图像
     * @return 返回一个BufferedImage表已经绘制的图像
     */
    public BufferedImage getBfimg() {
        return bfimg;
    }
    /**
     * 设置缓冲区绘制图像
     * @param bfimg 要设置的图像
     */
    public void setBfimg(BufferedImage bfimg) {
        this.bfimg = bfimg;
    }
}