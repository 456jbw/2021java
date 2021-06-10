package state;

import java.io.Serializable;

enum StateType{
    Start, Middle, End;
}
/**
 * 这个类实现了一个描述目前绘制的图形状态的类
 * 当图形处于Start态时,将会设置图形的起始位置(X1, Y1)
 * 当图形处于Middle态时,将会持续的监听鼠标移动或点击的位置,从而实现可视化。
 * 当图形处于End态时,将会结束监听位置,并且确定完成图形的最终形状。
 * @author Costwen
 */

public class State implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private StateType type = StateType.Start;
    public State(){
    }
    /**
     * 这个方法用于将图形的状态切换到下一个状态
     */
    public void next(){
        switch(type){
            case Start:
                System.out.println("Start to Middle");
                type = StateType.Middle;
                break;
            case Middle:        
                System.out.println("Middle to End");
                type = StateType.End;
                break;
            case End:
                System.out.println("End ");
                // type = StateType.Start;
                break;
            default:
                break;
        }
    }
    /**
     * 用来判断是否为Start状态
     * @return true 表示结果为真, false 表示结果为假
     */
    public boolean isStart(){
        if (this.getType() == StateType.Start){
            return true;
        }
        return false;
    }
    
    public boolean isMiddle(){
        if (this.getType() == StateType.Middle){
            return true;
        }
        return false;
    }
    
    public boolean isEnd(){
        if (this.getType() == StateType.End){
            return true;
        }
        return false;
    }
    public StateType getType() {
        return type;
    }

    public void setType(StateType type) {
        this.type = type;
    }
}