package com.saum.jvm.classpath;

import java.io.File;
import java.io.IOException;

/**
 * @Author saum
 * @Description:
 */
public abstract class Entry {

    /**
    * @Description 寻找和加载class文件
    * @param
    * @return
    */
    abstract byte[] readClass(String className) throws IOException;

    /**
    * @return 返回className的字符串表示形式
    */
    abstract String printClassName();

    /**
    * @Description
    * @param
    * @param
    * @return 创建具体的Entry
    */
    static Entry createEntry(String path){
        if(path != null){
            if(path.contains(File.pathSeparator)){
                return new CompositeEntry(path);
            }
            if(path.endsWith("*")){
                return new WildcardEntry(path);
            }
            if(path.endsWith(".jar") || path.endsWith("JAR") || path.endsWith(".zip") || path.endsWith(".ZIP")){
                return new ZipEntry(path);
            }
            return new DirEntry(path);
        }else{
            // 如果命令行中没有显式的指定-cp选项,那么默认要找的class就在当前路径下
            File file = new File("");
            try {
                path = file.getCanonicalPath();
                return new DirEntry(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("illegal classpath format,or you should point out the classpath explicitly");
    }
}
