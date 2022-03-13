package com.saum.jvm.instructions.comparisons.fcmp;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc:
 */
public class FCMPG extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        FCMP.fcmp(frame, true);
    }
}
