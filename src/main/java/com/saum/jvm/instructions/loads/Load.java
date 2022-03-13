package com.saum.jvm.instructions.loads;

import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 工具类,为不同的数据类型提供不同的加载机制;
 * 总体原则是:先从本地变量表中取出变量,然后将该变量压入到操作数栈中;
 */
public class Load {

    public static void aload(Frame frame, int index){
        Object ref = frame.localVars().getRef(index);
        frame.operandStack().pushRef(ref);
    }

    public static void dload(Frame frame, int index){
        double val = frame.localVars().getDouble(index);
        frame.operandStack().pushDouble(val);
    }

    public static void fload(Frame frame, int index){
        float val = frame.localVars().getFloat(index);
        frame.operandStack().pushFloat(val);
    }

    public static void iload(Frame frame, int index){
        int val = frame.localVars().getInt(index);
        frame.operandStack().pushInt(val);
    }

    public static void lload(Frame frame, int index){
        long val = frame.localVars().getLong(index);
        frame.operandStack().pushLong(val);
    }
}
