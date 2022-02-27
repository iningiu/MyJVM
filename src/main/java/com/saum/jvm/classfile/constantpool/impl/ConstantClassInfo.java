package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;
import com.saum.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 * CONSTANT_Class_info {
 *     u1 tag;
 *     u2 name_index; // 常量池索引，指向 CONSTANT_Utf8_info 常量，代表该类/接口的全限定名
 * }
 */
public class ConstantClassInfo extends ConstantInfo {

    private ConstantPool constantPool;
    public int nameIndex;

    public ConstantClassInfo(ConstantPool constantPool){
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_Class;
    }

    public String getVal(){
        return constantPool.getUTF8(nameIndex);
    }
}
