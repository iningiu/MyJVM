package com.saum.jvm.rtda;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 线程中私有的数据包括：PC 和虚拟机栈
 * Java虚拟机规范对Java虚拟机栈的约束也相当宽松,
 *  虚拟机栈可以是连续的空间，也可以不连续;可以是固定大小，也可以在运行时动态扩展
 *  如果Java虚拟机栈有大小限制，且执行线程所需的栈空间超出了这个限制，会导致StackOverflowError异常抛出。
 *  如果Java虚拟机栈可以动态扩展，但是内存已经耗尽，会导致OutOfMemoryError异常抛出。
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

    public Frame topFrame(){
        return stack.top();
    }

    public Frame createFrame(int maxLocals, int maxStack){
        return new Frame(this, maxLocals, maxStack);
    }
}
