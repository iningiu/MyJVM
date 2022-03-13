package com.saum.jvm.instructions.math.or;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶两 int 型数值做 按位或 并将结果压入栈顶
 */
public class IOR extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        int val1 = operandStack.popInt();
        int val2 = operandStack.popInt();
        int val = val1 | val2;
        operandStack.pushInt(val);
    }
}
