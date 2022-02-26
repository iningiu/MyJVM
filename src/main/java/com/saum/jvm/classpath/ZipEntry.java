package com.saum.jvm.classpath;

import java.io.IOException;
import java.nio.file.*;
import java.util.zip.ZipFile;

/**
 * @Author saum
 * @Description: zip/jar文件形式类路径
 */
public class ZipEntry extends Entry {

    private Path absolutePath;

    public ZipEntry(String path){
        // 获取绝对路径
        this.absolutePath = Paths.get(path).toAbsolutePath();
    }

    @Override
    byte[] readClass(String className) throws IOException {
        try(FileSystem fileSystem = FileSystems.newFileSystem(absolutePath, null)){
            return Files.readAllBytes(fileSystem.getPath(className));
        }
    }

    @Override
    String printClassName() {
        return absolutePath.toString();
    }
}
