package com.saum.jvm.instructions.math.mul;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶两 int 类型数值相乘并将结果压入栈顶
 */
public class IMUL extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        int val1 = operandStack.popInt();
        int val2 = operandStack.popInt();
        int val = val1 * val2;
        operandStack.pushInt(val);
    }
}