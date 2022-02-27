package com.saum.jvm.classfile;

import com.saum.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author saum
 * @Description:
 */
public class ClassFile {
    private int minorVersion; // 次版本号
    private int majorVersion;
    private ConstantPool constantPool;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int[] interfaces;

    /**
    * @Description
    * @param classData class文件，即二进制字节流
    */
    public ClassFile(byte[] classData){
        ClassReader classReader = new ClassReader(classData);
        read(classReader);
    }

    private void read(ClassReader classReader){
        readAndCheckMagic(classReader);
        readAndCheckVersion(classReader);
        constantPool = readConstantPool(classReader);
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

}
