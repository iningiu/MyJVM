package com.saum.jvm;

import com.saum.jvm.classfile.ClassFile;
import com.saum.jvm.classfile.MemberInfo;
import com.saum.jvm.classfile.rtda.Frame;
import com.saum.jvm.classfile.rtda.LocalVars;
import com.saum.jvm.classfile.rtda.OperandStack;
import com.saum.jvm.classpath.ClassPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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

        ClassFile classFile = loadClass(className, classPath);
//        printClassInfo(classFile);

        Frame frame = new Frame(100, 100);
        test_localVars(frame.localVars());
        test_operandStack(frame.operandStack());
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

    private static void printClassInfo(ClassFile cf){
        System.out.println("version: " + cf.getMajorVersion() + "." + cf.getMinorVersion());
        System.out.println("constants count：" + cf.getConstantPool().getConstantPoolCount());
        System.out.format("access flags：0x%x\n", cf.getAccessFlags());
        System.out.println("this class：" + cf.getClassName());
        System.out.println("super class：" + cf.getSuperClassName());
        System.out.println("interfaces：" + Arrays.toString(cf.getInterfaceNames()));
        System.out.println("fields count：" + cf.getFields().length);
        for (MemberInfo memberInfo : cf.getFields()) {
            System.out.format("%s \t\t %s\n", memberInfo.getName(), memberInfo.getDesc());
        }

        System.out.println("methods count: " + cf.getMethods().length);
        for (MemberInfo memberInfo : cf.getMethods()) {
            System.out.format("%s \t\t %s\n", memberInfo.getName(), memberInfo.getDesc());
        }
    }

    private static void test_localVars(LocalVars vars){
        vars.setInt(0,100);
        vars.setInt(1,-100);
        System.out.println(vars.getInt(0));
        System.out.println(vars.getInt(1));
    }

    private static void test_operandStack(OperandStack ops){
        ops.pushInt(100);
        ops.pushInt(-100);
        ops.pushRef(null);
        System.out.println(ops.popRef());
        System.out.println(ops.popInt());
    }
}
