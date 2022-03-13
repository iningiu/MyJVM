package com.saum.jvm.instructions.conversions.i2x;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶 int 型数值强制转换成 char 型数值并将结果压入栈顶
 */
public class I2C extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        int val1 = operandStack.popInt();
        char val2 = (char)val1;
        operandStack.pushInt(val2);
    }
}
