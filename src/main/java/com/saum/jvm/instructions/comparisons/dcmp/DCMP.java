package com.saum.jvm.instructions.comparisons.dcmp;

import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 由于浮点数计算有可能产生NaN（Not a Number）值，所以比较两个浮点数时，除了大于、等于、小于之外， 还有第4种结果：无法比较
 *   dcmpg和dcmpl指令的区别就在于对第4种结果的定义;
 *   当两个double变量中至少有一个是NaN时，用dcmpg指令比较的结果是1，而用dcmpl指令比较的结果是-1。
 */
public class DCMP {

    public static void dcmp(Frame frame, boolean flag){
        OperandStack operandStack = frame.operandStack();
        double val2 = operandStack.popDouble();
        double val1 = operandStack.popDouble();

        if(val1 > val2){
            operandStack.pushInt(1);
        }else if(val1 == val2){
            operandStack.pushInt(0);
        }else if(val1 < val2){
            operandStack.pushInt(-1);
        }else if(flag){ // 两个double变量中至少有一个是NaN
            operandStack.pushInt(1);
        }else{
            operandStack.pushInt(-1);
        }
    }
}
