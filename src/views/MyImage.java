package views;

import javax.swing.*;
import java.awt.*;

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

    public static ImageIcon getEraserIcon(int width,int height) {
        eraserIcon.setImage(eraserIcon.getImage().getScaledInstance(width, height,Image.SCALE_DEFAULT ));
        return eraserIcon;
    }

    public static ImageIcon getColorIcon() {
        return colorIcon;
    }

    public static ImageIcon getSaveIcon() {
        saveIcon.setImage(saveIcon.getImage().getScaledInstance(35, 35,Image.SCALE_DEFAULT ));
        return saveIcon;
    }
}
