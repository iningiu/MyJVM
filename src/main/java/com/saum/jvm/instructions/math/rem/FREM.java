package com.saum.jvm.instructions.math.rem;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 将栈顶两 float 类型数值做 取模运算 并将结果压入栈顶
 */
public class FREM extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        float val2 = operandStack.popFloat();
        float val1 = operandStack.popFloat();
        float val = val1 % val2;
        operandStack.pushFloat(val);
    }
}
