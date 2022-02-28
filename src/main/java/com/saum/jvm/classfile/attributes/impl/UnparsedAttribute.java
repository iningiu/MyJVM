package com.saum.jvm.classfile.attributes.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.AttributeInfo;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc:
 */
public class UnparsedAttribute extends AttributeInfo {

    private String name;
    private int length;
    private byte[] info;

    public UnparsedAttribute(String attrName, int attrLen){
        this.name = attrName;
        this.length = attrLen;
    }

    @Override
    public void readInfo(ClassReader reader) {
        info = reader.readBytes(length);
    }

    public byte[] info(){
        return this.info;
    }
}
