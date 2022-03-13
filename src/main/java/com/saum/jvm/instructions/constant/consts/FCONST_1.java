package com.saum.jvm.instructions.constant.consts;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 把float型1推入操作数栈顶
 */
public class FCONST_1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushFloat(1.0f);
    }
}
