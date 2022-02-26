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
        Path path = Paths.get("C:\\Program Files\\Java\\jdk1.8.0_241\\jre\\lib");

        String str = Files.walk(path)
                .filter(Files::isRegularFile)
                .map(Path::toString)
                .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))
                .collect(Collectors.joining(File.pathSeparator));


        File file = new File("");
        try {
            String path2 = file.getCanonicalPath();
            System.out.println(file.getAbsolutePath());
            System.out.println(path2);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
