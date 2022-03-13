package com.saum.jvm.instructions.stores;

import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 工具类, 为不同的数据类型提供不同的存储机制;
 *  总体原则是:把变量从操作数栈顶弹出，然后存入局部变量表
 */
public class Store {

    public static void astore(Frame frame, int index){
        Object ref = frame.operandStack().popRef();
        frame.localVars().setRef(index, ref);
    }

    public static void istore(Frame frame, int index){
        int val = frame.operandStack().popInt();
        frame.localVars().setInt(index, val);
    }

    public static void lstore(Frame frame, int index){
        long val = frame.operandStack().popLong();
        frame.localVars().setLong(index, val);
    }

    public static void fstore(Frame frame, int index){
        float val = frame.operandStack().popFloat();
        frame.localVars().setFloat(index, val);
    }

    public static void dstore(Frame frame, int index){
        double val = frame.operandStack().popDouble();
        frame.localVars().setDouble(index, val);
    }

}
