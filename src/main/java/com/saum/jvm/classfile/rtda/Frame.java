package com.saum.jvm.classfile.rtda;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 栈帧
 */
public class Frame {
    Frame lower; //当前帧的前一帧的引用;相当于单向链表的前一个指针
    private LocalVars localVars; //局部变量表的引用;
    private OperandStack operandStack; //操作数栈的引用;

    public Frame(int maxLocals, int maxStack) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public LocalVars localVars(){
        return localVars;
    }

    public OperandStack operandStack(){
        return operandStack;
    }

}
