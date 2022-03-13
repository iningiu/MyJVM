package com.saum.jvm.instructions.comparisons.ificmp;

import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc:
 */
public class IfIcmp {
    public static int[] icmpPop(Frame frame){
        OperandStack operandStack = frame.operandStack();
        int[] res = new int[2];
        res[1] = operandStack.popInt();
        res[0] = operandStack.popInt();
        return res;
    }
}
