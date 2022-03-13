package com.saum.jvm.instructions.math.mul;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶两 float 类型数值相乘并将结果压入栈顶
 */
public class FMUL extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        float val1 = operandStack.popFloat();
        float val2 = operandStack.popFloat();
        float val = val1 * val2;
        operandStack.pushFloat(val);
    }
}
