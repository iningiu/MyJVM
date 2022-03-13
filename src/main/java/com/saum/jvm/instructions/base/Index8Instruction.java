package com.saum.jvm.instructions.base;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 存储和加载类指令需要根据索引存取局部变量表，索引由单字节操作数给出
 *   部分存储和加载指令是自带操作数的,所以不需要index;
 *   然后其余部分的指令,只有加载/存储的语义,并不知道将数据存储/加载到局部变量表的哪一位,所以需要index
 */
public abstract class Index8Instruction implements Instruction {

    public int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readByte();
    }
}
