package listener;

import javax.swing.*;

import controller.Client;
import controller.Controller;
import network.discovery.RespondServer;
import views.ChooseView;
import views.Drawboard;
import views.SearchView;

import java.awt.event.*;
import java.io.IOException;

public class MyServerListener implements ActionListener {
    private static MyServerListener myServerListener;

    private MyServerListener() {

    }

    public static MyServerListener getInstance() {
        if (myServerListener == null) {
            myServerListener = new MyServerListener();
        }
        return myServerListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = e.getActionCommand();
        var addr = Client.getInstance().getServersList().get(name);
        Client.getInstance().setServer(addr, Client.getInstance().getName());
        SearchView.getInstance().showWait();
        System.out.println(e.getActionCommand());
    }
}
