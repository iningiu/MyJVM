package com.saum.jvm.instructions.stack.dup;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;
import com.saum.jvm.rtda.Slot;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: DUP指令复制栈顶的单个变量
 * bottom -> top
 * [...][c][b][a]
 *       |
 *       V
 * [...][c][b][a][a]
 */
public class DUP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        Slot slot = operandStack.popSlot();
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);
    }
}
