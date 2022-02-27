package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 * CONSTANT_Integer_info {
 *     u1 tag;
 *     u4 bytes;
 * }
 */
public class ConstantIntegerInfo extends ConstantInfo {

    private int val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readU4ToInteger();
    }

    @Override
    public int tag() {
        return CONSTANT_Integer;
    }

    public int getVal(){
        return val;
    }
}
