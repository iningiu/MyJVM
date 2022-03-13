package com.saum.jvm.instructions.stack.dup;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;
import com.saum.jvm.rtda.Slot;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 复制栈顶一个（对于long或double类型）或两个（对于非long或double的其它类型）变量并将复制值压入栈顶
 * b a => b a b a
 * bottom -> top
 * [...][c][b][a]
 *       |
 *       V
 * [...][c][b][a][b][a]
 */
public class DUP2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }
}
