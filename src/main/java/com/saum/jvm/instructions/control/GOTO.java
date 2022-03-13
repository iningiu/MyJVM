package com.saum.jvm.instructions.control;

import com.saum.jvm.instructions.base.BranchInstruction;
import com.saum.jvm.instructions.base.BranchLogic;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc:
 */
public class GOTO extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        BranchLogic.branch(frame, offset);
    }
}
