package views;

import javax.swing.*;
import java.awt.*;

/**
 * 这个类用来加载图标,将会加载pencil, eraser, color, save的相对应的图标到内存之中。
 */
public class MyImage {
    public static MyImage image = new MyImage();

    private static ImageIcon pencilIcon = new ImageIcon(image.getClass().getResource(  "/image/pencil.png"));
    private static ImageIcon eraserIcon = new ImageIcon(image.getClass().getResource( "/image/eraser.png"));
    private static ImageIcon colorIcon = new ImageIcon(image.getClass().getResource( "/image/color.png"));
    private static ImageIcon saveIcon = new ImageIcon(image.getClass().getResource( "/image/save.png"));
    public static ImageIcon getPencilIcon(int width,int height) {
        pencilIcon.setImage(pencilIcon.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        return pencilIcon;
    }
    /**
     * 获取橡皮擦图标
     * @param width 图标的宽度
     * @param height 图标的长度
     * @return 图标
     */
    public static ImageIcon getEraserIcon(int width,int height) {
        eraserIcon.setImage(eraserIcon.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        return eraserIcon;
    }
    /**
     * 获得颜色的图标
     * @return 返回颜色的选择图标
     */
    public static ImageIcon getColorIcon() {
        return colorIcon;
    }

    /**
     * 获得保存的图标
     * @return 返回保存的图标
     */
    public static ImageIcon getSaveIcon() {
        saveIcon.setImage(saveIcon.getImage().getScaledInstance(35, 35,Image.SCALE_DEFAULT ));
        return saveIcon;
    }
}
