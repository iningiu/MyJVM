package com.saum.jvm.classfile;

import com.saum.jvm.classfile.attributes.AttributeInfo;
import com.saum.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author saum
 * @Description:
 */
public class ClassFile {
    private int minorVersion; // 次版本号
    private int majorVersion;
    private ConstantPool constantPool;
    private int accessFlags; // 访问标志
    /*
    thisClass和superClass索引值指向常量池中一个类型为 CONSTANT_Class_info 的类描述符常量,
    再通过 CONSTANT_Class_info类型的常量中的索引值,可以找到定义在CONSTANT_Utf8_info类型的常量中的全限定名字符串。
     */
    private int thisClass;
    private int superClass;
    private int[] interfaces; // 存放所实现的接口在常量池中的索引
    private MemberInfo[] fields;
    private MemberInfo[] methods;
    private AttributeInfo[] attributes;

    /**
    * @Description
    * @param classData class文件，即二进制字节流
    */
    public ClassFile(byte[] classData){
        ClassReader classReader = new ClassReader(classData);
        read(classReader);
    }

    private void read(ClassReader reader){
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        constantPool = readConstantPool(reader);
        accessFlags = reader.readU2();
        thisClass = reader.readU2();
        superClass = reader.readU2();
        interfaces = reader.readU2s();
        fields = MemberInfo.readMembers(reader, constantPool);
        methods = MemberInfo.readMembers(reader, constantPool);
        attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    /**
    * @Description 检查魔数
    * @param
    * @return
    */
    private void readAndCheckMagic(ClassReader reader){
        String magic = reader.readU4ToHexStr();
        if(!"cafebabe".equals(magic)){
            throw new ClassFormatError("magic is wrong!");
        }
    }

    /**
    * @Description 版本号检查，完整的版本号可以表示成“M.m”的形式。次版本号只在 J2SE 1.2 之前用过，从 1.2 开始基本上就没什么用了（都是 0）。
     * 主版本号在 J2SE 1.2 之前是 45，从 1.2 开始，每次有大的 Java 版本发布，都会加 1。
    * @param
    * @return
    */
    private void readAndCheckVersion(ClassReader reader){
        minorVersion = reader.readU2();
        majorVersion = reader.readU2();
        if(majorVersion == 45) return; // 1.2
        if(minorVersion == 0 && majorVersion >= 46 && majorVersion <= 52) return; // 1.3~1.8
        throw new UnsupportedClassVersionError();
    }

    private ConstantPool readConstantPool(ClassReader reader){
        return new ConstantPool(reader);
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public String getClassName() {
        return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName() {
        // 除java.lang.Object外，所有Java类的父类索引都不为0
        if (superClass < 0) {
            return "";
        }
        return constantPool.getClassName(superClass);
    }

    public String[] getInterfaceNames(){
        String[] interfaceNames = new String[interfaces.length];
        for(int i = 0; i < interfaces.length; i++){
            interfaceNames[i] = constantPool.getClassName(interfaces[i]);
        }
        return interfaceNames;
    }
}
