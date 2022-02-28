package com.saum.jvm.classfile.attributes.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.AttributeInfo;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 用于支持invokeDynamic指令，它是描述和保存引导方法。
 * BootstrapMethods_attribute {
 *     u2 attribute_name_index; // 固定BootstrapMethods
 *     u4 attribute_length; // 属性总长度（不包含前6个字节）
 *     u2 num_bootstrap_methods; // 这个类中抱哈的引导方法的个数
 *     {   u2 bootstrap_method_ref; // 指明函数
 *         u2 num_bootstrap_arguments; // 指明引导方法的参数个数
 *         u2 bootstrap_arguments[num_bootstrap_arguments]; // 引导方法的参数
 *     } bootstrap_methods[num_bootstrap_methods];
 * }
 */
public class BootstrapMethodsAttribute extends AttributeInfo {

    BootstrapMethod[] bootstrapMethods;

    @Override
    public void readInfo(ClassReader reader) {
        int bootstrapMethodNum = reader.readU2();
        bootstrapMethods = new BootstrapMethod[bootstrapMethodNum];
        for (int i = 0; i < bootstrapMethodNum; i++) {
            bootstrapMethods[i] = new BootstrapMethod(reader.readU2(), reader.readU2s());
        }
    }

    public static class BootstrapMethod {
        int bootstrapMethodRef;
        int[] bootstrapArguments;

        BootstrapMethod(int bootstrapMethodRef, int[] bootstrapArguments) {
            this.bootstrapMethodRef = bootstrapMethodRef;
            this.bootstrapArguments = bootstrapArguments;
        }
    }
}
