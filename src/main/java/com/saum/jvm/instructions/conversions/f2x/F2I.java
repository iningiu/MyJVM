package com.saum.jvm.instructions.conversions.f2x;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶 float 型数值强制转换成 int 型数值并将结果压入栈顶
 */
public class F2I extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        float val1 = operandStack.popFloat();
        int val2 = (int)val1;
        operandStack.pushInt(val2);
    }
}
