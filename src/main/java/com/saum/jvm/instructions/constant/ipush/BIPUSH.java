package com.saum.jvm.instructions.constant.ipush;

import com.saum.jvm.instructions.base.BytecodeReader;
import com.saum.jvm.instructions.base.Instruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: bipush指令从操作数中获取一个byte型整数，扩展成int型，然后推入栈顶
 */
public class BIPUSH implements Instruction {

    private byte val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        val = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        frame.operandStack().pushInt(val);
    }
}
