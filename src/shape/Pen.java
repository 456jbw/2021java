package shape;

import java.awt.*;
import java.util.ArrayList;

public class Pen extends Shape{
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int x2, y2;
    private ArrayList<Integer> dotXlist=new ArrayList<>();
    private ArrayList<Integer> dotYlist=new ArrayList<>();
    /**
     * 笔的构造类
     * @param color 笔的颜色
     * @param stroke 笔的宽度
     */
    public Pen(Color color, Stroke stroke){
        super(color, stroke);
    }

    /**
     * 当鼠标按下之后,将会设置按下去的位置为矩形的左上角坐标
     * 同时,图形将会进入画图阶段(Middle)
     * @param x 鼠标点击相对于界面的x坐标
     * @param y 鼠标点击相对于界面的y坐标
     */
    @Override
    public void pressStrategy(int x, int y) {
        System.out.println(this.state);
        if (this.state.isStart()){
            setX1(x);
            setY1(y);
            dotXlist.add(x);
            dotYlist.add(y);
            System.out.printf("Press: %d %d\n", x, y);
        }
        if (this.state.isMiddle()){

        }
        state.next();
    }
    /**
     * 拖动鼠标时，记录每次拖动的位置
     * 画笔的所有拖拽点都会被记录
     * @param x 鼠标点击相对于界面的x坐标
     * @param y 鼠标点击相对于界面的y坐标
     */
    @Override
    public void dragStrategy(int x,int y) {
        // TODO Auto-generated method stub
        if (this.state.isMiddle()){
            setX2(x);
            setY2(y);
            dotXlist.add(x);
            dotYlist.add(y);//将每次drag的点加进去
        }

    }
    /**
     * @return 画笔的名字
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "画笔";
    }
    /**
     * 图形将会进入画图阶段(Middle)，画出线条
     * @param g 画笔，用于在画布上实时画图
     */
    @Override
    public void draw(Graphics2D g){
        g.setColor(this.color);
        g.setStroke(getStroke());
        int arrX[]=dotXlist.stream().mapToInt(Integer::valueOf).toArray();
        int arrY[]=dotYlist.stream().mapToInt(Integer::valueOf).toArray();
        for(int i=0;i<dotYlist.size()-1;i++){
            g.drawLine(arrX[i],arrY[i],arrX[i+1],arrY[i+1]);
        }
    }
    /**
     * 释放鼠标的策略，用于显示本次画图的最终图形
     * @param x 鼠标点击相对于界面的x坐标
     * @param y 鼠标点击相对于界面的y坐标
     */
    @Override
    public void releaseStrategy(int x, int y) {
        if (getState().isMiddle()){
            getState().next();
        }
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

}

