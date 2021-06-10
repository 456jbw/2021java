package controller;

import shape.Shape;;

public class Controller{
    private static Controller controller;
    public void addShape(Shape shape){
        Server.getInstance().sendShape(shape);
    }
    public void addContent(String content){
        Server.getInstance().sendContent(content);
    }
    void repaint(){
        Server.getInstance().sendRepaint();
    }

    public static Controller getInstance() {
        if (controller == null){
            controller = new Controller();
        }
        return controller;
    }
}