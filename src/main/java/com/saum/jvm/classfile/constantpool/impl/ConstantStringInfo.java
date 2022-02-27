package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;
import com.saum.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc: 本身并不存放字符串数据, 只存了常量池索引，这个索引指向一个CONSTANT_Utf8_info常量
 *   所以在readInfo中首先读出索引，然后在去对应的CONSTANT_Utf8_info常量中读取具体的字符串
 */
public class ConstantStringInfo extends ConstantInfo {
    private ConstantPool constantPool;
    private int strIndex;

    public ConstantStringInfo(ConstantPool constantPool){
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        strIndex = reader.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_String;
    }

    public String getString(){
        return constantPool.getUTF8(strIndex);
    }
}
