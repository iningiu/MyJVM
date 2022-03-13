package com.saum.jvm.instructions.loads.iload;

import com.saum.jvm.instructions.base.Index8Instruction;
import com.saum.jvm.instructions.loads.Load;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 从局部变量表获取 指定的int类型 变量，然后推入操作数栈顶
 */
public class ILOAD extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        Load.iload(frame, index);
    }
}