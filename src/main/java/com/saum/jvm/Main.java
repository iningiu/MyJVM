package com.saum.jvm;

import com.saum.jvm.classfile.ClassFile;
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
        ClassPath classPath = new ClassPath(cmd.jre, cmd.classpath);
        System.out.printf("classpath:%s class:%s args:%s\n", classPath, cmd.getMainClass(), cmd.getArgs());
        // 获取className
        String className = cmd.getMainClass();

        // 打印class文件内容
//        try {
//            byte[] classData = cp.readClass(className);
//            System.out.println("classData:");
//            for(byte b : classData){
//                //16进制输出
//                System.out.print(String.format("%02x", b & 0xff) + " ");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        loadClass(className, classPath);

    }

    private static ClassFile loadClass(String className, ClassPath classPath){
        try {
            byte[] classData = classPath.readClass(className);
            return new ClassFile(classData);
        } catch (IOException e) {
            System.out.println("Could not find or load main class " + className);
            return null;
        }

    }
}
