package com.saum.jvm;

import com.saum.jvm.classpath.ClassPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author saum
 * @Description:
 */
public class Main {
    public static void main(String[] args) {
        Cmd cmd = Cmd.parse(args);
        if(!cmd.ok || cmd.helpFlag){
            System.out.println("Usage: <main class> [-options] class [args...]");
            return;
        }
        if(cmd.versionFlag){
            System.out.println("java version \"1.8.0\"");
            return;
        }
        startJVM(cmd);
    }

    private static void startJVM(Cmd cmd){
        ClassPath cp = new ClassPath(cmd.jre, cmd.classpath);
        System.out.printf("classpath:%s class:%s args:%s\n", cp, cmd.getMainClass(), cmd.getArgs());
        // 获取className
        String className = cmd.getMainClass();
        try {
            byte[] classData = cp.readClass(className);
            System.out.println("classData:");
            for(byte b : classData){
                //16进制输出
                System.out.print(String.format("%02x", b & 0xff) + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
