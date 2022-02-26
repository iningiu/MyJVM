package com.saum.jvm.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author saum
 * @Description: 通配符类路径: aa/bb/*，表明我们的class文件在aa/bb/路径下的jar包中,
 * 所以我们只要遍历该路径下的所有以.jar结尾的文件,然后调用ZipJarEntry的实现方法,即可以获得字节码
 */
public class WildcardEntry extends CompositeEntry {

    public WildcardEntry(String pathList) {
        super(toPathList(pathList));
    }

    // 首先把路径末尾的星号去掉，得到baseDir，然后遍历该baseDir路径下的文件,只取以 .jar 结尾的文件
    private static String toPathList(String wildCardPath){
        String baseDir = wildCardPath.replace("*", "");
        StringBuilder sb = new StringBuilder();
        try (DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get(baseDir), "*.jar")) {
            paths.forEach(p->{
                sb.append(p.toString());
                sb.append(File.pathSeparator);
            });
            return sb.deleteCharAt(sb.length() - 1).toString();
        } catch (IOException e) {
            return "";
        }
    }
}
