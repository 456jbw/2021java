package main;
/**
 * 这个类继承了Thread类,用于实现绘画线程
 * @author Costwen
 */
public class DrawMoveThread extends Thread{
    private String name;
    private DrawDemo demo;
    public DrawMoveThread(String name,DrawDemo demo){
        this.name = name;
        this.demo = demo;
    }
    /**
     * 这个方法重写了Thread的run方法,调用这个方法将会把主窗口的重新绘制一次。
     */
    @Override
    public void run() {
        this.demo.repaint();
    }
}