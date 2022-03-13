package com.saum.jvm.instructions.math.iinc;

import com.saum.jvm.instructions.base.BytecodeReader;
import com.saum.jvm.instructions.base.Instruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.LocalVars;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: iinc指令给局部变量表中的int变量增加常量值，局部变量表索引和常量值都由指令的操作数提供。
 */
public class IINC implements Instruction {

    public int index;
    public int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readByte();
        offset = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        LocalVars localVars = frame.localVars();
        int val = localVars.getInt(index);
        val += offset;
        localVars.setInt(index, val);
    }
}
