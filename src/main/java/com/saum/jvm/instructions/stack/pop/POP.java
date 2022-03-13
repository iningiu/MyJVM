package com.saum.jvm.instructions.stack.pop;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: pop指令把栈顶变量弹出，只能用于弹出int、float等占用一个操作数栈位置的变量
 * bottom -> top
 * [...][c][b][a]
 *             |
 *             V
 * [...][c][b]
 */
public class POP extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.operandStack().popSlot();
    }
}
