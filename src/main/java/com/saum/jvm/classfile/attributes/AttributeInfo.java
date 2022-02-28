package com.saum.jvm.classfile.attributes;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.impl.*;
import com.saum.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc:
 * attribute_info {
 *   u2 attribute_name_index; // 常量池的索引
 *   u4 attribute_length; // 属性长度
 *   u1 info[attribute_length];
 * }
 */
public abstract class AttributeInfo {

    public abstract void readInfo(ClassReader reader);

    // 读取单个属性
    private static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool){
        int attrNameIndex = reader.readU2();
        String attrName = constantPool.getUTF8(attrNameIndex);
        int attrLen = reader.readU4ToInteger();
        AttributeInfo attributeInfo = create(attrName, attrLen, constantPool);
        attributeInfo.readInfo(reader);
        return attributeInfo;
    }

    /**
     * 读取属性表;
     * 和ConstantPool中的方法类似,一般都是一下子全部读取出来,不会只读一个
     * 整个 JVM 中有三个地方用到了读取属性表
     * 1. 由 class 文件转为 ClassFile 对象时，读取 Class 的属性
     * 2. 为 class 中定义的 Field 和 Method 读取属性
     * 3. 为 Method 中的字节码读取属性(本地变量表大小，操作数大小，字节码，异常表)
     */
    public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool){
        int attributesCount = reader.readU2();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for(int i = 0; i < attributesCount; i++){
            attributes[i] = readAttribute(reader, constantPool);
        }
        return attributes;
    }

    /**
    * @Desc Java虚拟机规范预定义了23种属性，先解析其中的8种。23种预定义属性可以分为三组。
     *     第一组属性是实现Java虚拟机所必需的，共有5种；
     *     第二组属性是Java类库所必需的，共有12种；
     *     第三组属性主要提供给工具使用，共有6种。第三组属性是可选的，也就是说可以不出现在class文件中。
     *     (如果class文件中存在第三组属性，Java虚拟机实现或者Java类库也是可以利用它们的，比如使用LineNumberTable属性在异常堆栈中显示行号。)
    * @param
    * @param
    * @return
    */
    private static AttributeInfo create(String attrName, int attrLen, ConstantPool constantPool){
        switch (attrName){
            case "Code":
                return new CodeAttribute(constantPool);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "Exceptions":
                return new ExceptionsAttribute();
            case "InnerClasses":
                return new InnerClassesAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "Signature":
                return new SignatureAttribute(constantPool);
            case "SourceFile":
                return new SourceFileAttribute(constantPool);
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                return new UnparsedAttribute(attrName, attrLen);
        }
    }
}
