package com.saum.jvm.classfile.attributes.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.AttributeInfo;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: LineNumberTable属性表存放方法的行号信息, 和前面介绍的SourceFile属性都属于调试信息，都不是运行时必需
 * 在使用javac编译器编译Java程序时，默认会在class文件中生成这些信息。可以使用javac提供的-g：none选项来关闭这些信息的生成
 * 描述Java源码行号与字节码行号之间的对应关系。
 * LineNumberTable_attribute {
 *     u2 attribute_name_index; // 固定为LineNumberTable
 *     u4 attribute_length; // 属性长度
 *     u2 line_number_table_length; // 表项长度
 *     {   u2 start_pc; // 字节码偏移量
 *         u2 line_number;	 // 行号
 *     } line_number_table[line_number_table_length]; // 表项内容
 * }
 */
public class LineNumberTableAttribute extends AttributeInfo {

    private LineNumberTableEntry[] lineNumberTable;

    @Override
    public void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readU2();
        lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for(int i = 0; i < lineNumberTableLength; i++){
            lineNumberTable[i] = new LineNumberTableEntry(reader.readU2(), reader.readU2());
        }
    }

    public static class LineNumberTableEntry{
        int startPc;  //字节码行号
        int lineNumber; //Java源码行号

        public LineNumberTableEntry(int startPc, int lineNumber){
            this.startPc = startPc;
            this.lineNumber = lineNumber;
        }
    }
}
