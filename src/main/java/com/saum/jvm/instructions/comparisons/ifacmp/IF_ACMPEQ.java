package com.saum.jvm.instructions.comparisons.ifacmp;

import com.saum.jvm.instructions.base.BranchInstruction;
import com.saum.jvm.instructions.base.BranchLogic;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 比较栈顶两引用型数值，当结果相等时跳转
 */
public class IF_ACMPEQ extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        Object ref1 = operandStack.popRef();
        Object ref2 = operandStack.popRef();
        if(ref1 == ref2){
            BranchLogic.branch(frame, offset);
        }
    }
}
