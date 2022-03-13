package com.saum.jvm.instructions.extended;

import com.saum.jvm.instructions.base.BranchInstruction;
import com.saum.jvm.instructions.base.BranchLogic;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 根据栈顶的引用是否不为null进行跳转
 */
public class IFNONNULL extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        Object ref = frame.operandStack().popRef();
        if(ref != null){
            BranchLogic.branch(frame, offset);
        }
    }
}
