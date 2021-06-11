package views;

import java.awt.Component;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class Dialogue extends JPanel {
    private static Dialogue dialogue;
    private List<Component> componentsList;
    public Dialogue(){
        componentsList = new ArrayList<Component>();
    };
    @Override
    public Component add(Component comp) {
        var c = super.add(comp);
        componentsList.add(c);
        return c;
    }
    public static Dialogue getInstance(){
        if(dialogue==null){
            dialogue = new Dialogue();
        }
        return dialogue;
    }
}
