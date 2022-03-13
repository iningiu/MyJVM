package com.saum.jvm.instructions.stores.dstore;

import com.saum.jvm.instructions.base.Index8Instruction;
import com.saum.jvm.instructions.stores.Store;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 把 double类型 变量从操作数栈顶弹出，然后存入局部变量表
 */
public class DSTORE extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        Store.dstore(frame, index);
    }
}
