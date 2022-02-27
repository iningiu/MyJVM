package com.saum.jvm.classfile.constantpool.impl;

import com.saum.jvm.classfile.ClassReader;
import com.saum.jvm.classfile.constantpool.ConstantInfo;

/**
 * @Author: saum
 * @Date: 2022/2/27
 * @Desc: 字段或方法的名称和描述符
 * CONSTANT_NameAndType_info {
 *   u1 tag;
 *   u2 name_index; // 常量池索引，字段或方法名称
 *   u2 descriptor_index; // 常量池索引，字段或方法描述符
 * }
 *
 * 描述符:描述字段的类型，描述方法的参数类型；
 * 1.类型描述符
 *   a:基本类型byte、short、char、int、long、float和double的描述符是单个字母，分别对应B、S、C、I、J、F和D。注意，long的描述符是J而不是L。
 *   b:引用类型的描述符是 L ＋ 类的完全限定名 ＋ 分号 eg: Ljava.lang.String;
 *   c:数组类型的描述符是[＋数组元素类型描述符。eg: [I  代表int[]
 * 2.字段描述符就是字段的类型描述符。
 * 3.方法描述符格式是：“（分号分隔的参数类型描述符）+返回值类型描述符”，其中 void 返回值由单个字母 V 表示。eg:(Ljava.lang.String;I)Ljava.lang.String
 *   代表的就是 String (String int),方法名由name_index给出;
 */
public class ConstantNameAndTypeInfo extends ConstantInfo {

    public int nameIndex;
    public int descIndex;

    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readU2();
        descIndex = reader.readU2();
    }

    @Override
    public int tag() {
        return CONSTANT_NameAndType;
    }
}
