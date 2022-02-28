package com.saum;

/**
 * @Author saum
 * @Description: -Xjre "C:\Program Files\Java\jdk1.8.0_241\jre" H:\IDEAProject\MyJVM\src\test\java\com\saum\HelloWorld
 */
public class HelloWorld {
    private String str = "hello,world!";

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
