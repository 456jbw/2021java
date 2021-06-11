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

public class Drawboard extends JPanel{
    /**
     *
     */
    private BufferedImage bfimg;
    private Graphics2D bfpen;
    private static Drawboard drawboard;
    private static List<Component> componentList;

    private Drawboard(){
        
    }

    @Override
    public Component add(Component comp) {
        Component c = super.add(comp);
        if (componentList == null) {
            componentList = new ArrayList<Component>();
        }
        componentList.add(comp);
        return c;
    }

    public static Drawboard getInstance() {
        if (drawboard == null) {
            drawboard = new Drawboard();
        }
        return drawboard;
    }

    /**
     * 这个方法重写了JFrame的画图方法,将会把所有绘制的图形全部绘制一次。 同时使用了缓冲bfimg 预先加载好了背景
     * 
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

    public BufferedImage getBfimg() {
        return bfimg;
    }

    public void setBfimg(BufferedImage bfimg) {
        this.bfimg = bfimg;
    }
}