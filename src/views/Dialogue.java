package views;

import javax.swing.*;

public class Dialogue extends JPanel {
    private static Dialogue dialogue;
    public Dialogue(){};
    public static Dialogue getInstance(){
        if(dialogue==null){
            dialogue = new Dialogue();
        }
        return dialogue;
    }
}
