package controller;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import main.DrawDemo;
import shape.Shape;

public class Client {
    private final DrawDemo demo;
    private List<Shape> shapesList;
    private Deque<String> contentsList;
    private static Client client;

    private Client() {
        shapesList = new ArrayList<Shape>();
        contentsList = new ArrayDeque<String>();
        demo = new DrawDemo();
    }

    public void addShape(final Shape shape) {
        shapesList.add(shape);
    }

    public void addContent(final String content) {
        if (contentsList.size() == 5){
            contentsList.removeFirst();
        }
        contentsList.add(content);
    }

    public void repaint() {
        demo.repaint();
    }

    public void start() {
        demo.init();
    }

    public static Client getInstance() {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    public List<Shape> getShapesList() {
        return shapesList;
    }

    public void setShapesList(final List<Shape> shapesList) {
        this.shapesList = shapesList;
    }

    public Deque<String> getContentsList() {
        return contentsList;
    }

    public void setContentsList(Deque<String> contentsList) {
        this.contentsList = contentsList;
    }


}