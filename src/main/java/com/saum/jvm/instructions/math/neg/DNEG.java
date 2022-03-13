package com.saum.jvm.instructions.math.neg;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶 double 类型的数值取负并将结果压入栈顶
 */
public class DNEG extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        double val = operandStack.popDouble();
        operandStack.pushDouble(-val);
    }
}
