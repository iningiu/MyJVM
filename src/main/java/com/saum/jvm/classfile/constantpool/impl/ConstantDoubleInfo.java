package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 * CONSTANT_Double_info {
 *     u1 tag;
 *     u8 bytes;
 * }
 */
public class ConstantDoubleInfo extends ConstantInfo {
    private double val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readU8ToDouble();
    }

    @Override
    public int tag() {
        return CONSTANT_Double;
    }

    public double getVal(){
        return val;
    }
}
