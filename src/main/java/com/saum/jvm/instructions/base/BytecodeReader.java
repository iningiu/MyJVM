package com.saum.jvm.instructions.base;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc:
 */
public class BytecodeReader {
    private byte[] codes; // 存放字节码， byte的范围：-128~127
    private int pc; // 记录读取到了哪个字节

    /**
    * @Desc 重置，避免每次解码指令都新创建一个BytecodeReader实例
    * @param
    * @param
    * @return
    */
    public void reset(byte[] codes, int pc){
        this.codes = codes;
        this.pc = pc;
    }

    public int getPc(){
        return pc;
    }

    public byte readByte(){
        byte code = codes[pc++];
        return code;
    }

    public int readUint8(){
        int code = codes[pc++];
        code = (code + 256) % 256;
        return code;
    }

    public short readShort(){
        byte byte1 = readByte();
        byte byte2 = readByte();
        int ub1 = byte1 & 0xff;
        int ub2 = byte2 & 0xff;
        return (short) ((ub1 << 8) | ub2);
    }

    public int readInt(){
        int byte1 = readByte();
        int byte2 = readByte();
        int byte3 = readByte();
        int byte4 = readByte();
        return (byte1 << 24) | (byte2 << 16) | (byte3 << 8) | byte4;
    }

    public int[] readInts(int n){
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[n] = readInt();
        }
        return arr;
    }

    //4k对齐,没有对齐的会有填充数据,这些数据要忽略掉; 用在lookupswitch and tableswitcch中
    public void skipPadding(){
        while(pc % 4 != 0){
            readByte();
        }
    }



}
