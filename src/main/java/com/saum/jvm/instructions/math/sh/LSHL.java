package com.saum.jvm.instructions.math.sh;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: long 算术左移
 */
public class LSHL extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        int val2 = operandStack.popInt(); // 要移位多少bit
        long val1 = operandStack.popLong(); // 要进行位移操作的变量
        int s = val2 & 0x3f; // long变量有64位，所以只取val2的后6个比特就足够表示位移位数了
        // 但是Java中对于大数左移超出后,也会变成负数,所以这里不做额外处理了
        long val = val1 << s;
        operandStack.pushLong(val);
    }

}
