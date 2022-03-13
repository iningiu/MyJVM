package com.saum.jvm.instructions.comparisons.lcmp;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 把栈顶的两个long变量弹出，进行比较，然后把比较结果（int型0、1或-1）推入栈顶
 */
public class LCMP extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        long val1 = operandStack.popLong();
        long val2 = operandStack.popLong();
        if(val1 > val2){
            operandStack.pushInt(1);
        }else if(val1 == val2){
            operandStack.pushInt(0);
        }else{
            operandStack.pushInt(-1);
        }
    }
}
