package com.saum.jvm.instructions.stack.pop;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: double和long变量在操作数栈中占据两个位置，需要使用pop2指令弹出
 * bottom -> top
 * [...][c][b][a]
 *          |  |
 *          V  V
 * [...][c]
 */
public class POP2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.operandStack().popSlot();
        frame.operandStack().popSlot();
    }
}
