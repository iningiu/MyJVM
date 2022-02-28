package com.saum.jvm.classfile.rtda;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 局部变量表，局部变量表是按索引访问的，所以很自然，可以把它想象成一个数组。
 * 根据Java虚拟机规范，这个数组的每个元素至少可以容纳一个int或引用值，两个连续的元素可以容纳一个long或double值。
 * 注:这里并没有真的对boolean、byte、short和char类型定义存取方法，因为这些类型的值都是转换成int值类来处理的(4K对齐)
 */
public class LocalVars {
    private Slot[] slots; // 局部变量表,JVM 规定其按照索引访问,所以将其设置为数组

    public LocalVars(int maxStack){
        if(maxStack > 0) {
            slots = new Slot[maxStack];
            for (int i = 0; i < maxStack; i++) {
                slots[i] = new Slot();
            }
        }
    }

    public void setInt(int index, int val){
        slots[index].num = val;
    }

    public int getInt(int index){
        return slots[index].num;
    }

    public void setFloat(int index, float val){
        slots[index].num = Float.valueOf(val).intValue();
    }

    public Float getFloat(int index){
        return (float)slots[index].num;
    }

    public void setRef(int index, Object ref){
        slots[index].ref = ref;
    }

    public Object getRef(int index){
        return slots[index].ref;
    }
}
