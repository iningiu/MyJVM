package com.saum.jvm.classfile.rtda;

import java.util.EmptyStackException;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 虚拟机栈
 */
public class JvmStack {
    private int maxSize; //虚拟机栈中所包含栈帧的最大容量
    private int size; //当前虚拟机栈中包含帧的实际数量
    private Frame top; //栈顶的帧

    public JvmStack(int maxSize){
        this.maxSize = maxSize;
    }

    public void push(Frame frame){
        if(size > maxSize){
            //如果栈已经满了，按照 Java 虚拟机规范，应该抛出 StackOverflowError 异常
            throw new StackOverflowError();
        }
        if(top != null){
            frame.lower = top;
        }
        top = frame;
        size++;
    }

    public Frame pop(){
        if(top == null){
            throw new EmptyStackException();
        }
        Frame tmp = top;
        top = tmp.lower;
        tmp.lower = null;
        size--;
        return tmp;
    }

    public Frame top(){
        if(top == null){
            throw new EmptyStackException();
        }
        return top;
    }
}
