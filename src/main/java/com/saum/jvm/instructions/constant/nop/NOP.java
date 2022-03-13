package com.saum.jvm.instructions.constant.nop;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: nop指令是最简单的一条指令，因为它什么也不做
 */
public class NOP extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {

    }
}
