package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 */
public class ConstantMethodTypeInfo extends ConstantInfo {

    private int descIndex;


    @Override
    public void readInfo(ClassReader reader) {
        descIndex = reader.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_MethodType;
    }
}
