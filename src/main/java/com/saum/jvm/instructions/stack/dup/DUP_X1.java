package com.saum.jvm.instructions.stack.dup;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;
import com.saum.jvm.rtda.Slot;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: DUP_X1 指令 先将栈顶的两个变量交换,然后再将原栈顶元素添加到栈顶 ab => bab;
 * bottom -> top
 * [...][c][b][a]
 *       |
 *       V
 * [...][c][a][b][a]
 */
public class DUP_X1 extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }
}
