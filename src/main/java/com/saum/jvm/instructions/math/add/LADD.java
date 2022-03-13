package com.saum.jvm.instructions.math.add;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶两 long 类型数值相加并将结果压入栈顶
 */
public class LADD extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        long val1 = operandStack.popLong();
        long val2 = operandStack.popLong();
        long val = val1 + val2;
        operandStack.pushLong(val);
    }
}
