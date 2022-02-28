package com.saum.jvm.classfile.attributes.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.AttributeInfo;
import com.saum.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: Code 出现在方法表的属性集合中
 * Code_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 max_stack;
 *     u2 max_locals;
 *     u4 code_length;
 *     u1 code[code_length];
 *     u2 exception_table_length;
 *     exception_info {
 *        u2 start_pc;
 *       u2 end_pc;
 *       u2 handler_pc;
 *       u2 catch_type;
 *     } exception_table[exception_table_length];
 *     u2 attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 */
public class CodeAttribute extends AttributeInfo {
    private ConstantPool constantPool;
    private int maxStack;
    private int maxLocals;
    private byte[] code; // 字节码
    ExceptionTableEntry[] exceptionTables;
    private AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool constantPool){
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        maxStack = reader.readU2();
        maxLocals = reader.readU2();
        int codeLength = reader.readU4ToInteger();
        code = reader.readBytes(codeLength);
        exceptionTables = readExceptionTable(reader);
        attributes = AttributeInfo.readAttributes(reader, constantPool);
    }

    private ExceptionTableEntry[] readExceptionTable(ClassReader reader){
        int exceptionTableLength = reader.readU2();
        ExceptionTableEntry[] exceptionTables = new ExceptionTableEntry[exceptionTableLength];
        for(int i = 0; i < exceptionTableLength; i++){
            exceptionTables[i] = new ExceptionTableEntry(reader);
        }
        return exceptionTables;
    }

    //异常表，包含四个指针，分别为
    public static class ExceptionTableEntry{
        private int startPc; //可能出现异常的代码块的起始字节码（包括）
        private int endPc; //可能出现异常的代码块的终止字节码（不包括）
        private int handlerPc; //负责处理异常的 catch 块的起始位置
        private int catchType; //指向运行时常量池的一个索引，解析后可以得到一个异常类

        public ExceptionTableEntry(ClassReader reader){
            startPc = reader.readU2();
            endPc = reader.readU2();
            handlerPc = reader.readU2();
            catchType = reader.readU2();
        }

        public int getStartPc() {
            return startPc;
        }

        public int getEndPc() {
            return endPc;
        }

        public int getHandlerPc() {
            return handlerPc;
        }

        public int getCatchType() {
            return catchType;
        }
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }

    public ExceptionTableEntry[] getExceptionTables() {
        return exceptionTables;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }
}
