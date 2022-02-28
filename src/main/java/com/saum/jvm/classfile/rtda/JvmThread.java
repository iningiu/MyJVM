package com.saum.jvm.classfile.rtda;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 线程中私有的数据包括：PC 和虚拟机栈
 */
public class JvmThread {
    private int pc;
    private JvmStack stack;

    public JvmThread(){
        // 默认栈的大小是 1024,也就是说可以存放 1024 个栈帧
        stack = new JvmStack(1024);
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void pushFrame(Frame frame){
        stack.push(frame);
    }

    public Frame popFrame(){
        return stack.pop();
    }

    public Frame currentFrame(){
        return stack.top();
    }


}
