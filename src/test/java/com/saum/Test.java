package com.saum;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author saum
 * @Description:
 */
public class Test {
    public static void main(String[] args) throws IOException {
        double a = 1;
        double b = 0;
//        double c = a / b;
        double d = Double.NaN;

        if(a > d){
            System.out.println(1);
        }else if(a == d){
            System.out.println(0);
        }else if(a < d){
            System.out.println(-1);
        }else{
            System.out.println(999);
        }

    }
}
