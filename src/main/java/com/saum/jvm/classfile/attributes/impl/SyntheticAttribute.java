package com.saum.jvm.classfile.attributes.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.AttributeInfo;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 仅起标记作用，不包含任何数据。是JDK1.1引入的，可以出现在 ClassFile、field_info和method_info结构中
 *   属于布尔属性，只有存在和不存在的区别。
 */
public class SyntheticAttribute extends AttributeInfo {

    private int attributeNameIndex;
    private int attributeLength;

    @Override
    public void readInfo(ClassReader reader) {
        // 由于没有数据,所以是空的
    }
}
