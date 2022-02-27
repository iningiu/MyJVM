package com.saum.jvm.classfile.constantpool;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.impl.ConstantClassInfo;
import com.saum.jvm.classfile.constantpool.impl.ConstantDoubleInfo;
import com.saum.jvm.classfile.constantpool.impl.ConstantNameAndTypeInfo;
import com.saum.jvm.classfile.constantpool.impl.ConstantUtf8Info;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author saum
 * @Description: 常量池格式(一共14种常量类型)：
 *  ClassFile {
 *   ...
 *   u2 constant_pool_count;	// 常量池大小
 *   cp_info constant_pool[constant_pool_count-1]; // 常量池
 *   ...
 * }
 */
public class ConstantPool {
    private int constantPoolCount; // 常量池容量
    private ConstantInfo[] constantInfos;

    public ConstantPool(ClassReader reader){
        constantPoolCount = reader.readU2();
        constantInfos = new ConstantInfo[constantPoolCount];
        for(int i = 1; i < constantPoolCount; i++){
            constantInfos[i] = ConstantInfo.readConstantInfo(reader, this);
            // 因为double和long占8个字节
            if(constantInfos[i] instanceof ConstantDoubleInfo){
                i++;
            }
        }
    }

    /**
    * @Desc 按索引查找常量,如果没有的话,直接抛异常；需要先检查index是否合法，index范围：[1,constantPoolCount-1]
    * @param index 索引
    * @return
    */
    private ConstantInfo getConstantInfo(int index){
        if(index > 0 && index < constantPoolCount){
            ConstantInfo info = constantInfos[index];
            if(info != null){
                return info;
            }
        }
        throw new NullPointerException("Invalid constant pool index!");
    }

    public String getUTF8(int index) {
        return ((ConstantUtf8Info)getConstantInfo(index)).getVal();
    }

    public String getClassName(int index) {
        ConstantClassInfo constantClassInfo = (ConstantClassInfo) getConstantInfo(index);
        return getUTF8(constantClassInfo.nameIndex);
    }

    public Map<String, String> getNameAndType(int index) {
        ConstantNameAndTypeInfo constantNameAndTypeInfo = (ConstantNameAndTypeInfo) getConstantInfo(index);
        Map<String, String> map = new HashMap<>();
        map.put("name", getUTF8(constantNameAndTypeInfo.nameIndex));
        map.put("type", getUTF8(constantNameAndTypeInfo.descIndex));
        return map;
    }
}
