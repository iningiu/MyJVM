package com.saum.jvm.instructions.base;

import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 每个指令都会实现该接口，所有的指令逻辑都是先从字节码数组中取数据，然后执行各自的逻辑
 */
public interface Instruction {

    //从字节码中提取操作数
    void fetchOperands(BytecodeReader reader);

    // 执行指令逻辑
    void execute(Frame frame);
}
