package com.saum.jvm.instructions.extended;

import com.saum.jvm.instructions.base.BytecodeReader;
import com.saum.jvm.instructions.base.Instruction;
import com.saum.jvm.instructions.loads.aload.ALOAD;
import com.saum.jvm.instructions.loads.dload.DLOAD;
import com.saum.jvm.instructions.loads.fload.FLOAD;
import com.saum.jvm.instructions.loads.iload.ILOAD;
import com.saum.jvm.instructions.loads.lload.LLOAD;
import com.saum.jvm.instructions.math.iinc.IINC;
import com.saum.jvm.instructions.stores.astore.ASTORE;
import com.saum.jvm.instructions.stores.dstore.DSTORE;
import com.saum.jvm.instructions.stores.fstore.FSTORE;
import com.saum.jvm.instructions.stores.istore.ISTORE;
import com.saum.jvm.instructions.stores.lstore.LSTORE;
import com.saum.jvm.rtda.Frame;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc: 加载类指令、存储类指令、ret 指令和 iinc 指令需要按索引访问局部变量表，索引以 uint8 的形式存在字节码中。
 * 对于大部分方法来说，局部变量表大小一般都不会超过 256，所以用一字节来表示索引就够了。
 * 但是如果有方法的局部变量表超过这限制时,Java 虚拟机规范定义了 wide 指令来扩展前述指令。
 * wide 指令改变其他指令的行为，modifiedInstruction 字段存放被改变的指令。
 */
public class WIDE implements Instruction {

    private Instruction modifiedInstruction;


    /**
    * @Desc 先从字节码中读取一字节的操作码，然后创建子指令实例，最后读取子指令的操作数。因为没有实现ret指令，所以暂时调用 RuntimeException 函数终止程序执行
    * @param
    * @return
    */
    @Override
    public void fetchOperands(BytecodeReader reader) {
        int opCode = reader.readByte();
        switch (opCode){
            case 0x15:
                ILOAD iload = new ILOAD();
                iload.index = reader.readShort();
                modifiedInstruction = iload;
                break;
            case 0x16:
                LLOAD lload = new LLOAD();
                lload.index = reader.readShort();
                modifiedInstruction = lload;
                break;
            case 0x17:
                FLOAD fload = new FLOAD();
                fload.index = reader.readShort();
                modifiedInstruction = fload;
                break;
            case 0x18:
                DLOAD dload = new DLOAD();
                dload.index = reader.readShort();
                modifiedInstruction = dload;
                break;
            case 0x19:
                ALOAD aload = new ALOAD();
                aload.index = reader.readShort();
                modifiedInstruction = aload;
                break;
            case 0x36:
                ISTORE istore = new ISTORE();
                istore.index = reader.readShort();
                modifiedInstruction = istore;
                break;
            case 0x37:
                LSTORE lstore = new LSTORE();
                lstore.index = reader.readShort();
                modifiedInstruction = lstore;
                break;
            case 0x38:
                FSTORE fstore = new FSTORE();
                fstore.index = reader.readShort();
                modifiedInstruction = fstore;
                break;
            case 0x39:
                DSTORE dstore = new DSTORE();
                dstore.index = reader.readShort();
                modifiedInstruction = dstore;
                break;
            case 0x3a:
                ASTORE astore = new ASTORE();
                astore.index = reader.readShort();
                modifiedInstruction = astore;
                break;
            case 0x84:
                IINC iinc = new IINC();
                iinc.index = reader.readShort();
                iinc.offset = reader.readShort();
                modifiedInstruction = iinc;
                break;
            case 0xa9: // ret
                throw new RuntimeException("Unsupported opcode: 0xa9!");
        }
    }

    //wide指令只是增加了索引宽度，并不改变子指令操作，所以其Execute() 方法只要调用子指令的Execute()方法即可
    @Override
    public void execute(Frame frame) {
        modifiedInstruction.execute(frame);
    }
}
