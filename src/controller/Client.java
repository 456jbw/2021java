package controller;

import java.net.InetSocketAddress;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;

import main.DrawDemo;
import shape.Shape;
import views.ChooseView;
import views.ReceiveView;
import views.SearchView;

public class Client {
    private final DrawDemo demo;
    private String name;
    private int State;
    private List<Shape> shapesList;
    private Deque<String> contentsList;
    private static Client client;
    private HashMap<String, InetSocketAddress> serversList;

    private Client() {
        shapesList = new ArrayList<Shape>();
        contentsList = new ArrayDeque<String>();
        serversList = new HashMap<String, InetSocketAddress>();
        demo = new DrawDemo();
    }

    public void addServer(String a, InetSocketAddress b){
        serversList.put(a, b);
    
        SearchView.getInstance().addButton(a, b);
        SearchView.getInstance().repaint();
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

    public void show(){
        var c = ChooseView.getInstance();
        c.init();
    }

    public void start() {
        ChooseView.getInstance().dispose();
        SearchView.getInstance().dispose();
        ReceiveView.getInstance().dispose();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public HashMap<String, InetSocketAddress> getServersList() {
        return serversList;
    }

    public void setServersList(HashMap<String, InetSocketAddress> serversList) {
        this.serversList = serversList;
    }


}