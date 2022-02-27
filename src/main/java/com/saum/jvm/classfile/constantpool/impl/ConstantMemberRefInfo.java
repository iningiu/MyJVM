package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;
import com.saum.jvm.classfile.constantpool.ConstantPool;

import java.util.Map;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 * CONSTANT_Fieldref_info表示字段符号引用
 * CONSTANT_Methodref_info表示普通（非接口）方法符号引用
 * CONSTANT_InterfaceMethodref_info表示接口方法符号引用
 * 这三种类型结构一样,所以给出统一的类结构;
 * 然后定义三个类继承这个超类;
 * class_index和name_and_type_index都是常量池索引，分别指向CONSTANT_Class_info和CONSTANT_NameAndType_info常量。
 */
public class ConstantMemberRefInfo extends ConstantInfo {

    private ConstantPool constantPool;
    private int classIndex;
    private int nameAndTypeIndex;

    public ConstantMemberRefInfo(ConstantPool constantPool){
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        classIndex = reader.readU2();
        nameAndTypeIndex = reader.readU2();
    }

    @Override
    public int tag() {
        return 0;
    }

    public String getClassName(){
        return constantPool.getClassName(classIndex);
    }

    public Map<String, String> getNameAndDescriptor(){
        return constantPool.getNameAndType(nameAndTypeIndex);
    }
}
