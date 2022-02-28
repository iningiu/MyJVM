package com.saum.jvm.classfile;

import java.math.BigInteger;

/**
 * @Author saum
 * @Description: Class文件字节码读取辅助类
 * java虚拟机定义了u1、u2、u4三种数据类型来表示；1字节、2字节、4字节，无符号整数。
 * 在如下实现中，用增位方式表示无符号类型：
 *  u1、u2可以用int类型存储，因为int类型是4字节
 *  u4 需要用long类型存储，因为long类型是8字节
 */
public class ClassReader {
    byte[] data;
    int index;

    public ClassReader(byte[] data){
        this.data = data;
    }

    // u1
    public byte readU1(){
        byte[] res = readBytes(1);
        return res[0];
    }

    // u2 这里是读取一个无符号的 16 位整数,java 中没有,只能用 int 来代替吧;
    public int readU2(){
        byte[] res = readBytes(2);
        return byte2int(res);
    }

    public int[] readU2s(){
        int n = readU2();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = readU2(); // 指向常量池中的索引
        }
        return arr;
    }

    // u4
    public int readU4ToInteger(){
        byte[] res = readBytes(4);
        return new BigInteger(1, res).intValue();
    }

    public float readU4ToFloat(){
        byte[] res = readBytes(4);
        return new BigInteger(1, res).floatValue();
    }

    public String readU4ToHexStr(){
        byte[] res = readBytes(4);
        String str_hex = new BigInteger(1, res).toString(16);
        return str_hex;
    }

    // u8
    public long readU8ToLong(){
        byte[] res = readBytes(8);
        return new BigInteger(1, res).longValue();
    }

    public double readU8ToDouble(){
        byte[] res = readBytes(8);
        return new BigInteger(1, res).doubleValue();
    }


    public byte[] readBytes(int n){
        if(index + n > data.length){
            throw new IndexOutOfBoundsException();
        }
        byte[] copy = new byte[n];
        System.arraycopy(data, index, copy, 0, n);
        index += n;
        return copy;
    }

    private int byte2int(byte[] val){
        String str_hex = new BigInteger(1, val).toString(16);
        return Integer.parseInt(str_hex, 16);
    }



}
