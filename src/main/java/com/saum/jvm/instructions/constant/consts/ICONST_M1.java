package com.saum.jvm.instructions.constant.consts;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 把int型-1推入操作数栈顶
 */
public class ICONST_M1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushInt(-1);
    }
}
