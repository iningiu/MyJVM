package com.saum.jvm.instructions.loads.aload;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.instructions.loads.Load;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 从局部变量表获取 引用类型 变量，然后推入操作数栈顶；索引隐含在操作码中
 */
public class ALOAD_1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Load.aload(frame, 1);
    }
}
