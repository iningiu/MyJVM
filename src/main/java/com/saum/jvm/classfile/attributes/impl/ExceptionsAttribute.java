package com.saum.jvm.classfile.attributes.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.AttributeInfo;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: Exceptions是变长属性，记录方法抛出的异常表
    Exceptions_attribute {
        u2 attribute_name_index;
        u4 attribute_length;
        u2 number_of_exceptions;
        u2 exception_index_table[number_of_exceptions];
    }
 */
public class ExceptionsAttribute extends AttributeInfo {

    int[] exceptionIndexTable;

    @Override
    public void readInfo(ClassReader reader) {
        exceptionIndexTable = reader.readU2s();
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }
}
