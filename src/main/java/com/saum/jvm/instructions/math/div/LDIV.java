package com.saum.jvm.instructions.math.div;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶两 long 类型数值相除并将结果压入栈顶
 */
public class LDIV extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        long val2 = operandStack.popLong();
        long val1 = operandStack.popLong();
        if (val2 == 0) {
            throw new ArithmeticException("java.lang.ArithmeticException: / by zero");
        }
        long val = val1 / val2;
        operandStack.pushLong(val);
    }
}
