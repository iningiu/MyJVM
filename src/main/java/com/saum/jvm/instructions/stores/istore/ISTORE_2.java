package com.saum.jvm.instructions.stores.istore;

import com.saum.jvm.instructions.base.NoOperandsInstruction;
import com.saum.jvm.instructions.stores.Store;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 把 指定的int类型 变量从操作数栈顶弹出，然后存入局部变量表；索引隐含在操作码中
 */
public class ISTORE_2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Store.istore(frame, 2);
    }
}
