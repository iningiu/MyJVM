package com.saum.jvm.instructions.math.sh;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.OperandStack;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: int 算术左移
 */
public class ISHL extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack operandStack = frame.operandStack();
        int val2 = operandStack.popInt(); // 要移位多少bit
        int val1 = operandStack.popInt(); // 要进行位移操作的变量
        int s = val2 & 0x1f; // int变量只有32位，所以只取val2的后5个比特就足够表示位移位数了
        // 但是Java中对于大数左移超出后,也会变成负数,所以这里不做额外处理了
        int val = val1 << s;
        operandStack.pushInt(val);
    }

}
