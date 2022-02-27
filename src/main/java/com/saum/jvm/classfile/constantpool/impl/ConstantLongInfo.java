package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 * CONSTANT_Long_info {
 *     u1 tag;
 *     u8 bytes;
 * }
 */
public class ConstantLongInfo extends ConstantInfo {
    private long val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readU8ToLong();
    }

    @Override
    public int tag() {
        return CONSTANT_Long;
    }

    public long getVal(){
        return val;
    }
}
