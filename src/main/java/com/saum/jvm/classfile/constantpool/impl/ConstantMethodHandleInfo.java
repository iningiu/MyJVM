package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 * CONSTANT_Method_Handle_Info {
 *     u1 tag;
 *     u1 reference_kind;
 *     u2 reference_index; //常量池索引
 * }
 */
public class ConstantMethodHandleInfo extends ConstantInfo {

    private byte referenceKind;
    private int referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        referenceKind = reader.readU1();
        referenceIndex = reader.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_MethodHandle;
    }
}
