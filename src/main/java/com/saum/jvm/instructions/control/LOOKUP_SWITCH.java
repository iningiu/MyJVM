package com.saum.jvm.instructions.control;

import com.saum.jvm.instructions.base.BranchLogic;
import com.saum.jvm.instructions.base.BytecodeReader;
import com.saum.jvm.instructions.base.Instruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 如果case值不可以编码成一个索引表(case中的数值不是连续的)，则实现成lookupswitch指令
 */
public class LOOKUP_SWITCH implements Instruction {
    private int defaultOffset;
    private int npairs;
    //matchOffsets有点像Map，它的key是case值，value是跳转偏移,但是并没有实现成map,而是用数组代替,两个连续的数位key-value;
    private int[] matchOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt();
        npairs = reader.readInt();
        matchOffsets = reader.readInts(npairs * 2);
    }

    /**
    * @Desc 从操作数栈中弹出一个int变量，然后用它查找matchOffsets，看是否能找到匹配的key。
     * 如果能，则按照value给出的偏移量跳转，否则按照defaultOffset跳转。
    * @param
    * @param
    * @return
    */
    @Override
    public void execute(Frame frame) {
        int key = frame.operandStack().popInt();
        for (int i = 0; i < npairs * 2; i++) {
            if(matchOffsets[i] == key){
                int offset = matchOffsets[i+1];
                BranchLogic.branch(frame, offset);
                return;
            }
        }
        BranchLogic.branch(frame, defaultOffset);
    }
}
