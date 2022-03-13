package com.saum.jvm.instructions.base;

import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 真正的跳转逻辑,因为这个函数在很多指令中都会用到，所以把它定义base中
 */
public class BranchLogic {
    public static void branch(Frame frame, int offset){
        int pc = frame.getThread().getPc();
        int nextPc = pc + offset;
        frame.setNextPc(nextPc);
    }
}
