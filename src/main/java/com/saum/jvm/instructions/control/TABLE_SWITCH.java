package com.saum.jvm.instructions.control;

import com.saum.jvm.instructions.base.BranchLogic;
import com.saum.jvm.instructions.base.BytecodeReader;
import com.saum.jvm.instructions.base.Instruction;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 如果case值可以编码成一个索引表(case中的数值是连续的)，则实现成tableswitch指令
 */
public class TABLE_SWITCH implements Instruction {
    private int defaultOffset;
    // low和high记录case的取值范围
    private int low;
    private int high;
    //jumpOffsets是一个索引表，里面存放high-low+1个int值,，对应各种case情况下，执行跳转所需的字节码偏移量
    private int[] jumpOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        //tableswitch指令操作码的后面有0~3字节的padding，以保证 defaultOffset在字节码中的地址是4的倍数
        reader.skipPadding();
        defaultOffset = reader.readInt();
        low = reader.readInt();
        high = reader.readInt();
        int jumpOffsetCount = high - low + 1;
        jumpOffsets = reader.readInts(jumpOffsetCount);
    }

    /**
    * @Desc 先判断 index 在不在 low 和 high 之间，如果不在，其 offset 就是 defaultOffset，否则就用从数组中找 arr[index-low]，拿到对应的 offset。
    * @param
    * @return
    */
    @Override
    public void execute(Frame frame) {
        int index = frame.operandStack().popInt();
        int offSet;
        if((index >= low) && (index <= high)){
            offSet = jumpOffsets[index - low];
        }else{
            offSet = defaultOffset;
        }

        BranchLogic.branch(frame, offSet);
    }
}
