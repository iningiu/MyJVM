package com.saum.jvm.classfile.attributes.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.AttributeInfo;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 用来描述外部类和内部类之间的关系
 * InnerClasses_attribute {
 *     u2 attribute_name_index; // 固定InnerClasses
 *     u4 attribute_length; // 属性长度
 *     u2 number_of_classes; // 内部类格式
 *     {   u2 inner_class_info_index; // 内部类类型
 *         u2 outer_class_info_index; // 外部类类型
 *         u2 inner_name_index; // 内部类名称
 *         u2 inner_class_access_flags; // 内部类访问标识符
 *     } classes[number_of_classes]; // 内部类内容
 * }
 */
public class InnerClassesAttribute extends AttributeInfo {

    private InnerClassEntry[] classes;

    @Override
    public void readInfo(ClassReader reader) {
        int numberOfClasses = reader.readU2();
        classes = new InnerClassEntry[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            classes[i] = new InnerClassEntry(reader.readU2(), reader.readU2(), reader.readU2(), reader.readU2());
        }
    }

    public static class InnerClassEntry{
        private int innerClassInfoIndex;
        private int outerClassInfoIndex;
        private int innerNameIndex;
        private int innerClassAccessFlags;

        public InnerClassEntry(int innerClassInfoIndex, int outerClassInfoIndex, int innerNameIndex, int innerClassAccessFlags) {
            this.innerClassInfoIndex = innerClassInfoIndex;
            this.outerClassInfoIndex = outerClassInfoIndex;
            this.innerNameIndex = innerNameIndex;
            this.innerClassAccessFlags = innerClassAccessFlags;
        }
    }
}
