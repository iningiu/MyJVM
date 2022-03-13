package com.saum.jvm.instructions.extended;

import com.saum.jvm.instructions.base.BranchInstruction;
import com.saum.jvm.instructions.base.BranchLogic;
import com.saum.jvm.instructions.base.BytecodeReader;
import com.saum.jvm.instructions.base.Instruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: goto_w指令和goto指令的唯一区别就是索引从2字节变成了4字节
 */
public class GOTO_W implements Instruction {

    private int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt();
    }

    @Override
    public void execute(Frame frame) {
        BranchLogic.branch(frame, offset);
    }
}
