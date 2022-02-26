package com.saum.jvm.classpath;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author saum
 * @Description: 目录形式的类路径
 */
public class DirEntry extends Entry {
    private Path absDir;

    public DirEntry(String path){
        // 获取绝对路径
        absDir = Paths.get(path).toAbsolutePath();
    }

    @Override
    byte[] readClass(String className) throws IOException {
        Path path = absDir.resolve(className);
        return Files.readAllBytes(path);
    }

    @Override
    String printClassName() {
        return absDir.toString();
    }
}
