package com.saum.jvm.instructions.math.div;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶两 double 类型数值相除并将结果压入栈顶
 */
public class DDIV extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        double val2 = operandStack.popDouble();
        double val1 = operandStack.popDouble();
        double val = val1 / val2;
        operandStack.pushDouble(val);
    }
}
