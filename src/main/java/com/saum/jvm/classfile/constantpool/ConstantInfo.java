package com.saum.jvm.classfile.constantpool;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.impl.*;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc: 常量的抽象类，这里实现了常量池中所有常量的类型。
 *   由于常量池中存放的信息各不相同，所以每种常量的格式也不同。常量数据的第一字节是tag，用来区分常量类型。
 *  根据在常量池的字节码的每个tag字节，可以判断下一个常量类型是什么，每个常量占用多少个字节都是可以确定的，接着再读取下一个tag，确定下一个常量类型；
 */
public abstract class ConstantInfo {
    public static final int CONSTANT_Utf8 = 1;
    public static final int CONSTANT_Integer = 3;
    public static final int CONSTANT_Float = 4;
    public static final int CONSTANT_Long = 5;
    public static final int CONSTANT_Double = 6;
    public static final int CONSTANT_Class = 7;
    public static final int CONSTANT_String = 8;
    public static final int CONSTANT_Fieldref = 9;
    public static final int CONSTANT_Methodref = 10;
    public static final int CONSTANT_InterfaceMethodref = 11;
    public static final int CONSTANT_NameAndType = 12;
    public static final int CONSTANT_MethodHandle = 15;
    public static final int CONSTANT_MethodType = 16;
    public static final int CONSTANT_InvokeDynamic = 18;

    public abstract void readInfo(ClassReader reader);
    public abstract int tag();

    public static ConstantInfo readConstantInfo(ClassReader reader, ConstantPool constantPool){
        int tag = reader.readU1(); // 标志位
        ConstantInfo constantInfo = create(tag, constantPool);
        constantInfo.readInfo(reader);
        return constantInfo;
    }

    private static ConstantInfo create(int tag, ConstantPool constantPool){
        switch (tag){
            case CONSTANT_Utf8:
                return new ConstantUtf8Info();
            case CONSTANT_Integer:
                return new ConstantIntegerInfo();
            case CONSTANT_Float:
                return new ConstantFloatInfo();
            case CONSTANT_Long:
                return new ConstantLongInfo();
            case CONSTANT_Double:
                return new ConstantDoubleInfo();
            case CONSTANT_Class:
                return new ConstantClassInfo(constantPool);
            case CONSTANT_String:
                return new ConstantStringInfo(constantPool);
            case CONSTANT_Fieldref:
                return new ConstantFieldRefInfo(constantPool);
            case CONSTANT_Methodref:
                return new ConstantMethodRefInfo(constantPool);
            case CONSTANT_InterfaceMethodref:
                return new ConstantInterfaceMethodRefInfo(constantPool);
            case CONSTANT_NameAndType:
                return new ConstantNameAndTypeInfo();
            case CONSTANT_MethodHandle:
                return new ConstantMethodHandleInfo();
            case CONSTANT_MethodType:
                return new ConstantMethodTypeInfo();
            case CONSTANT_InvokeDynamic:
                return null;
            default:
                throw new ClassFormatError("constant pool tag is invalid!");
        }
    }
}
