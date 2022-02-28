package com.saum.jvm.classfile.attributes.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.AttributeInfo;
import com.saum.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: SourceFile是可选定长属性，只会出现在ClassFile结构中，用于指出源文件名 name
 *   这个属性也是可选的，使用 Javac -g：none 选项关闭该项信息。
 *   对于大多数情况，类名和文件名是一致的，但是只有在内部类中，如果抛出异常，并且没有生成该项，堆栈中将不会显示出错代码所属的文件名。
 *   SourceFile_attribute {
 *     u2 attribute_name_index; // 固定SourceFile
 *     u4 attribute_length; // 属性长度，固定为2
 *     u2 sourcefile_index; // 源代码文件名，指向常量池索引
 * }
 */
public class SourceFileAttribute extends AttributeInfo {

    private ConstantPool constantPool;
    private int sourceFileIndex;

    public SourceFileAttribute(ConstantPool constantPool){
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        sourceFileIndex = reader.readU2();
    }

    public String getFileName() {
        return constantPool.getUTF8(sourceFileIndex);
    }
}
