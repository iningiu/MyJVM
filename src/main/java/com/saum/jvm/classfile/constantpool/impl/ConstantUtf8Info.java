package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 * CONSTANT_Utf8_info {
 *   u1 tag;
 *   u2 length;
 *   u1 bytes[length];
 * }
 */
public class ConstantUtf8Info extends ConstantInfo {

    private String val;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readU2();
        byte[] bytes = reader.readBytes(length);
        val = new String(bytes);
    }

    @Override
    public int tag() {
        return CONSTANT_Utf8;
    }

    public String getVal(){
        return val;
    }
}
