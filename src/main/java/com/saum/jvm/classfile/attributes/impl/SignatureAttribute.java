package com.saum.jvm.classfile.attributes.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.attributes.AttributeInfo;
import com.saum.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 出现于类、属性表和方法表结构的属性表中。任何类、接口、初始化方法或成员的泛型签名如果包含了类型变量（Type Variables）或参数化类型（Parameterized Types），
 * 则Signature属性会为他记录泛型签名信息
 * Signature_attribute {
 *     u2 attribute_name_index; // 固定Signature
 *     u4 attribute_length; // 固定2
 *     u2 signature_index; // 常量池有效索引。
 *     如果该签名属性是类文件结构的属性，则该索引处的常量池项必须是表示类签名的常量信息结构）；
 *     如果该签名属性是方法信息结构的属性，则必须是方法签名；否则，必须是字段签名。
 */
public class SignatureAttribute extends AttributeInfo {

    private ConstantPool constantPool;
    private int signatureIdx;

    public SignatureAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.signatureIdx = reader.readU2();
    }

    public String signature(){
        return this.constantPool.getUTF8(this.signatureIdx);
    }

}
