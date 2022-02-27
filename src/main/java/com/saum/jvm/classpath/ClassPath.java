package com.saum.jvm.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author saum
 * @Description:
 */
public class ClassPath {
    private Entry boostrapClassPath; // 启动类路径
    private Entry extensionClassPath; // 扩展类路径
    private Entry userClassPath; // 用户类路径

    public ClassPath(String jreOption, String cpOption){
        //启动类&扩展类 "C:\Program Files\Java\jdk1.8.0_161\jre"
        bootstrapAndExtensionClasspath(jreOption);
        //用户类 E:\..\org\itstack\demo\test\HelloWorld
        parseUserClassPath(cpOption);
    }

    private void bootstrapAndExtensionClasspath(String jreOption){
        // 获取jre路径
        String jreDir = getJreDir(jreOption);

        // ../jre/lib/*
        String jreLibPath = Paths.get(jreDir, "lib") + File.separator + "*";
        boostrapClassPath = new WildcardEntry(jreLibPath);

        // ../jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir, "lib", "ext") + File.separator + "*";
        extensionClassPath = new WildcardEntry(jreExtPath);
    }

    private static String getJreDir(String jreOption){
        // jre全路径
        if(jreOption != null && Files.exists(Paths.get(jreOption))){
            return jreOption;
        }
        // jre放在当前的工程目录下
        if(Files.exists(Paths.get("./jre"))){
            return "./jre";
        }
        String jh = System.getenv("JAVA_HOME");
        if(jh != null){
            return Paths.get(jh, "jre").toString();
        }
        throw new RuntimeException("Can not find JRE folder!");
    }

    private void parseUserClassPath(String cpOption) {
        // 如果用户没有指定classpath，则默认为当前路径
        if (cpOption == null) {
//            cpOption = ".";
        }
        userClassPath = Entry.createEntry(cpOption);
    }

    /**
    * @Description ClassPath 对外的统一接口,实例化ClassPath时传入 userPath 路径和类名就可以读取字节码文件
     *       读取className 对应的字节码,注意顺序,我们的查找次序是:
     *       bootClasspath => extClasspath => userClasspath;
    * @param
    * @return
    */
    public byte[] readClass(String className) throws IOException {
        //注意，用命令行加载java文件时，只写文件名，所有这里统一为文件名后补上“.class”的后缀；
        if(className.endsWith(".class")){
            throw new RuntimeException("can't find or can't load the class: " + className);
        }
        className = className.replace(".", "/") + ".class";

        try {
            return boostrapClassPath.readClass(className);
        } catch (IOException e) {
        }

        try {
            return extensionClassPath.readClass(className);
        } catch (IOException e) {
        }

        return userClassPath.readClass(className);
    }
}
