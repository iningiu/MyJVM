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
    public boolean helpFlag = false;

    @Parameter(names = "-version", description = "print the version", order = 2)
    public boolean versionFlag;

    @Parameter(names = {"-cp", "-classpath"}, description = "classpath", order = 1)
    public String classpath;

    @Parameter(names = "-Xjre", description = "path of jre", order = 4)
    public String jre;

    @Parameter(description = "main class and args")
    public List<String> mainClassAndArgs;

    public boolean ok;

    public String getMainClass(){
        return mainClassAndArgs != null && !mainClassAndArgs.isEmpty() ? mainClassAndArgs.get(0) : null;
    }

    public List<String> getArgs(){
        return mainClassAndArgs != null && mainClassAndArgs.size() > 1 ? mainClassAndArgs.subList(1, mainClassAndArgs.size()) : null;
    }

    public static Cmd parse(String[] argv){
        Cmd args = new Cmd();
        JCommander cmd = JCommander.newBuilder().addObject(args).build();
        cmd.parse(argv);
        args.ok = true;
        return args;
    }

}
