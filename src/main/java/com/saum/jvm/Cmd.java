package com.saum.jvm;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

/**
 * @Author saum
 * @Description: 参数解析器
 */
public class Cmd {

    @Parameter(names = {"-?", "-help"}, description = "print help message", order = 3, help = true)
    boolean helpFlag = false;

    @Parameter(names = "-version", description = "print the version", order = 1)
    boolean versionFlag;

    @Parameter(names = {"-cp", "-classpath"}, description = "classpath", order = 1)
    String classpath;

    @Parameter(description = "main class and args")
    List<String> mainClassAndArgs;

    boolean ok;

    String getMainClass(){
        return mainClassAndArgs != null && !mainClassAndArgs.isEmpty() ? mainClassAndArgs.get(0) : null;
    }

    List<String> getArgs(){
        return mainClassAndArgs != null && mainClassAndArgs.size() > 1 ? mainClassAndArgs.subList(1, mainClassAndArgs.size()) : null;
    }

    static Cmd parse(String[] argv){
        Cmd args = new Cmd();
        JCommander cmd = JCommander.newBuilder().addObject(args).build();
        cmd.parse(argv);
        args.ok = true;
        return args;
    }

}
