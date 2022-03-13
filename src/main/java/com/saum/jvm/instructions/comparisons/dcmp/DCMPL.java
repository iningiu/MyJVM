package com.saum.jvm.instructions.comparisons.dcmp;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc:
 */
public class DCMPL extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        DCMP.dcmp(frame, false);
    }
}
