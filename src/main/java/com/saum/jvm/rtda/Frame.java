package com.saum.jvm.rtda;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 栈帧,执行方法所需的局部变量表大小和操作数栈深度是由编译器预先计算好的，存储在class文件method_info结构的Code属性中
 */
public class Frame {
    Frame lower; //当前帧的前一帧的引用;相当于单向链表的前一个指针
    private LocalVars localVars; //局部变量表的引用;
    private OperandStack operandStack; //操作数栈的引用;
    private JvmThread thread; //当前栈帧所在的线程;
    int nextPc;  //frame中并不改变PC的值,其PC值是由ByteReader读取字节码不断改变的

    public Frame(JvmThread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public LocalVars localVars(){
        return localVars;
    }

    public OperandStack operandStack(){
        return operandStack;
    }

    public JvmThread getThread() {
        return thread;
    }

    public int getNextPc() {
        return nextPc;
    }

    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }
}
