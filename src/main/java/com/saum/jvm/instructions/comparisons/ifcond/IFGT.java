package com.saum.jvm.instructions.comparisons.ifcond;

import com.saum.jvm.instructions.base.BranchInstruction;
import com.saum.jvm.instructions.base.BranchLogic;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 把操作数栈顶的int变量弹出，然后跟0进行比较（>），满足条件则跳转
 */
public class IFGT extends BranchInstruction {
    @Override
    public void execute(Frame frame) {
        int val = frame.operandStack().popInt();
        if(val > 0){
            BranchLogic.branch(frame, offset);
        }
    }
}
