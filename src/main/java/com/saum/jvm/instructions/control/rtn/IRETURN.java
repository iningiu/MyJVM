package com.saum.jvm.instructions.control.rtn;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.JvmThread;

/**
 * @Author: saum
 * @Date: 2022/3/13
 * @Desc: 从当前方法返回int
 * 执行方法在执行结束后，如果有返回值，其返回值会放在该方法的操作数栈
 * 执行方法的外部——调用方法，需要将执行方法的返回值，压入调用方法的操作数栈
 */
public class IRETURN extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        JvmThread thread = frame.getThread();
        Frame currentFrame = thread.popFrame();
        Frame invokerFrame  = thread.topFrame();
        int val = currentFrame.operandStack().popInt();
        invokerFrame.operandStack().pushInt(val);
    }
}
