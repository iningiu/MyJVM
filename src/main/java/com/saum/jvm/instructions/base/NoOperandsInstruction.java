package com.saum.jvm.instructions.base;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 表示没有操作数的指令，所以没有定义任何字段。FetchOperands方法也是空实现，什么也不用读
 */
public abstract class NoOperandsInstruction implements Instruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

}
