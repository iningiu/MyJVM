package com.saum.jvm.instructions.comparisons.ificmp;

import com.saum.jvm.instructions.base.BranchInstruction;
import com.saum.jvm.instructions.base.BranchLogic;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 比较栈顶两 int 型数值的大小
 */
public class IF_ICMPNE extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        int[] res = IfIcmp.icmpPop(frame);
        if(res[0] != res[1]){
            BranchLogic.branch(frame, offset);
        }
    }
}
