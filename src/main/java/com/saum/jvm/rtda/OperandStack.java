package com.saum.jvm.rtda;

/**
 * @Author: saum
 * @Date: 2022/2/28
 * @Desc: 操作数栈，底层其实还是用数组来实现的，但是对外要暴露的接口是栈的特性，操作数栈的大小是编译器确定的
 * 包含的操作和局部变量表类似
 * 操作数栈的大小是编译期已经确定的，保存在code属性中，所以可以用Slot数组实现
 * 但要和 LocalVars 区分开，本地变量表按索引访问，操作数栈是用数组模拟的栈；方法栈是用单向链表模拟的栈
 */
public class OperandStack {
    private int size; // 初始值为 0,在运行中,代表当前栈顶的 index,还未使用,可以直接用,用完记得 size++;
    private Slot[] slots; // 栈顶在右边

    public OperandStack(int maxStack){
        if(maxStack > 0) {
            slots = new Slot[maxStack];
            for (int i = 0; i < maxStack; i++) {
                slots[i] = new Slot();
            }
        }
    }

    public void pushInt(int val){
        slots[size++].num = val;
    }

    public int popInt(){
        return slots[--size].num;
    }

    public void pushFloat(float val){
        slots[size++].num = (int)val;
    }

    public float popFloat(){
        return slots[--size].num;
    }

    public void pushLong(long val){
        slots[size].num = (int)val;
        slots[size+1].num = (int)(val >> 32);
        size += 2;
    }

    public long popLong(){
        size -= 2;
        int low = slots[size].num;
        int high = slots[size + 1].num;
        return (long)(high << 32) | (long)low;
    }

    public void pushDouble(double val) {
        pushLong((long) val);
    }

    public double popDouble() {
        return popLong();
    }

    public void pushRef(Object ref){
        slots[size++].ref = ref;
    }

    public Object popRef(){
        /* 错误写法，pop后将数组中当前位置设置为 null
          但是存在的问题是：操作数栈中的如果有多个的引用，都指向相同的一个实例对象，
          将其中的一个引用设置为 null，相当于把对象在堆中的空间设置为 null 了，
         这会导致操作数栈中所有的引用都会变成 null，后续会产生 NullPointerException
        所以这里在弹栈之后，不在设置为 null；
        操作数栈本身是不断复用的，故不考虑 GC 问题
     */
//        --size;
//        Object ref = slots[size].ref;
//        slots[size].ref = null;
//        return ref;

        return slots[--size].ref;
    }

    public void pushSlot(Slot slot){
        slots[size++] = slot;
    }

    public Slot popSlot(){
        return slots[--size];
    }

    // 清空操作数栈，直接将 size 设置为0，而不是将所有的 slot 都设为 null；因为这样可能会引起其它问题
    public void clear(){
        size = 0;
    }

    public Slot[] getSlots() {
        return slots;
    }

}
