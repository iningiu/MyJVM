package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 * CONSTANT_Float_info {
 *     u1 tag;
 *     u4 bytes;
 * }
 */
public class ConstantFloatInfo extends ConstantInfo {
    private float val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readU4ToFloat();
    }

    @Override
    public int tag() {
        return CONSTANT_Float;
    }

    public float getVal(){
        return val;
    }
}
