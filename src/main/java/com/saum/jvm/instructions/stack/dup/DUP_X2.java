package com.saum.jvm.instructions.stack.dup;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;
import com.saum.jvm.rtda.Slot;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc:
 * bottom -> top
 * [...][c][b][a]
 *       |
 *       V
 * [...][a][c][b][a]
 */
public class DUP_X2 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        Slot slot3 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot3);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }
}
