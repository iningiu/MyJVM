package com.saum.test;

import com.alibaba.fastjson.JSON;
import com.saum.jvm.Cmd;
import com.saum.jvm.classfile.ClassFile;
import com.saum.jvm.classfile.MemberInfo;
import com.saum.jvm.classfile.attributes.impl.CodeAttribute;
import com.saum.jvm.classpath.ClassPath;
import com.saum.jvm.instructions.InstructionFactory;
import com.saum.jvm.instructions.base.BytecodeReader;
import com.saum.jvm.instructions.base.Instruction;
import com.saum.jvm.rtda.Frame;
import com.saum.jvm.rtda.JvmThread;

import java.io.IOException;

/**
 * @Author: saum
 * @Date: 2022/3/1
 * @Desc:
 */
public class TestInterpreter {
    public static void main(String[] args) throws IOException {
        Cmd cmd = Cmd.parse(args);

//        System.out.println(toHexString(-78));
// 0x03 0x3b 0x03 0x3c 0x1b 0x10 0x64 0xa2 0x00 0x0d 0x1a 0x1b 0x60 0x3b 0x84 0x01 0x01 0xa7 0xff 0xf3 0x1a 0xac
        ClassPath classPath = new ClassPath(cmd.jre, cmd.classpath);
        byte[] classData = classPath.readClass(cmd.getMainClass());
        ClassFile classFile = new ClassFile(classData);
        MemberInfo[] methods = classFile.getMethods();
        MemberInfo targetMethod = null;
        for(MemberInfo method : methods){
//            if("test".equals(method.getName()) && "()I".equals(method.getDesc())){
            if("main".equals(method.getName()) && "([Ljava/lang/String;)V".equals(method.getDesc())){
                targetMethod = method;
                break;
            }
        }

        if(targetMethod != null){
            //拿到Code属性,主要是拿到其中的字节码用来测试创建的指令是否有效
            CodeAttribute codeAttribute = targetMethod.getCodeAttribute();
            //获得执行方法所需的局部变量表和操作数栈空间
            int maxLocals = codeAttribute.getMaxLocals();
            int maxStack = codeAttribute.getMaxStack();

            //拿到code的字节码
            byte[] byteCode = codeAttribute.getCode();

//            for(byte b : byteCode){
//                System.out.print(toHexString(b) + " ");
//            }
//            if(true) return;

            JvmThread thread = new JvmThread();
            //该线程中创建一帧
            Frame frame = thread.createFrame(maxLocals, maxStack);
            thread.pushFrame(frame);
            BytecodeReader reader = new BytecodeReader();
            //这里循环的条件是true，因为在调用下面的 test 方法时，会遇到return,而现在还没有实现return
            // 目前遇到为能解析的指令，会抛出异常，那么循环也就终止了，此时查看其操作数栈，观察结果
            while(true){
                int pc = frame.getNextPc();
                thread.setPc(pc);
                reader.reset(byteCode, pc);
                int opCode = reader.readUint8();
                //解析指令,创建指令,然后根据不同的指令执行不同的操作
                Instruction instruction = InstructionFactory.createInstruction(opCode);
                if(instruction == null){
                    System.out.println("指令尚未实现 " + toHexString(opCode));
                    break;
                }
                instruction.fetchOperands(reader);
                frame.setNextPc(reader.getPc());
//                System.out.println("指令：" + toHexString(opCode) + " -> " + instruction.getClass().getSimpleName() + " => 局部变量表：" + JSON.toJSONString(frame.localVars().getSlots()) + " 操作数栈：" + JSON.toJSONString(frame.operandStack().getSlots()));            //exec
                System.out.println("局部变量表：" + JSON.toJSONString(frame.localVars().getSlots()) + "，操作数栈：" + JSON.toJSONString(frame.operandStack().getSlots()) + " => 指令：" + toHexString(opCode) + "(" + instruction.getClass().getSimpleName() + ")");            //exec
                instruction.execute(frame);
            }
        }else{
            System.out.println("can't load test method");
        }
    }

    private static String toHexString(int code) {
        String str = String.format("%02x", code & 0xff);
        return "0x" + str;

//        StringBuilder sb = new StringBuilder();
//        sb.append("0x");
//        String strHex = Integer.toHexString(code);
//        if (strHex.length() < 2) {
//            strHex = "0" + strHex;
//        }
//        sb.append(strHex);
//        return sb.toString();
    }
}
