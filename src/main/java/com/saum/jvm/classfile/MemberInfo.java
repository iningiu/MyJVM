package com.saum.jvm.classfile;

import com.saum.jvm.classfile.attributes.AttributeInfo;
import com.saum.jvm.classfile.constantpool.ConstantPool;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc: 字段表和方法表结构完全一样
 * field_info {
 *   u2 access_flags;		//字段的访问修饰符
 *   u2 name_index;		//常量池索引，代表字段的简单名称
 *   u2 descriptor_index;	//常量池索引，代表字段描述符
 *   u2 attributes_count;	//字段的额外附加属性数量
 *   attribute_info attributes[attributes_count];	//字段的额外的附加属性
 * }
 */
public class MemberInfo {

    private ConstantPool constantPool;
    private int accessFlags;
    private int nameIndex;
    private int descIndex;
    private AttributeInfo[] attributeInfos;

    public MemberInfo(ClassReader reader, ConstantPool constantPool){
        this.constantPool = constantPool;
        this.accessFlags = reader.readU2();
        this.nameIndex = reader.readU2();
        this.descIndex = reader.readU2();
        this.attributeInfos = AttributeInfo.readAttributes(reader, constantPool);
    }

    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool constantPool){
        int count = reader.readU2();
        MemberInfo[] fields = new MemberInfo[count];
        for(int i = 0; i < count; i++){
            fields[i] = new MemberInfo(reader, constantPool);
        }
        return fields;
    }

    public String getName(){
        return constantPool.getUTF8(nameIndex);
    }

    public String getDesc(){
        return constantPool.getUTF8(descIndex);
    }

}
