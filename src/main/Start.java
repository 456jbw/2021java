package main;

import java.net.Inet4Address;

import java.net.InetAddress;

import java.net.NetworkInterface;

import controller.*;
import network.call.ServerController;
import network.call.ClientController;


/**
 * 这个类用来启动整个程序
 */
public class Start {
    /**
     * 启动方法
     * @param args 启动参数
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        var allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            //循环遍历所有网络接口
        String host = null;
        while (allNetInterfaces.hasMoreElements()) {
            //顺序拿取某个网络接口
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            //获得与该网络接口绑定的 IP 地址，一般只有一个
            var addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress ip = addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address 
                && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {
                    if (netInterface.getName().contains("wlan")){
                        host = ip.getHostAddress();
                    }
                    System.out.println("网卡接口名称：" + netInterface.getName());
                    System.out.println("网卡接口地址：" + ip.getHostAddress());
                    System.out.println();
                }
            }
        }
        if (host != null){

//            System.setProperty("java.rmi.server.hostname", host);
            System.setProperty("java.rmi.server.useLocalHostname", "true");
        }

        System.out.println(System.getProperties());
        Client client = Client.getInstance();
        client.show();
    }
}
