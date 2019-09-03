package cn.zs.learn.java;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class LearnCmdOnLunix {
  public static void main(String[] args) {
    try {
         Runtime runtime = Runtime.getRuntime();
      
         Process process = runtime
             .exec("mvn clean package -DskipTests",null,new File("/home/zhang/Downloads/jenkins/jenkins-jenkins-2.105"));
        
    
         
         //param1:命令 param2:环境变量字符串数组，格式为name=value param3:子进程的工作目录
//         File file = new File("/home/zhang/Desktop/dockerSDKforjava/docker-client-master");
//         Process process = runtime.exec("mvn checkstyle:check", null, file);
         
 
        
         //导致当前进程等待。一直等到该process表示的进程终止
         process.waitFor();
        
        //进程的输入流
//        OutputStream os = process.getOutputStream();
        
        //进程的错误输出流
//        InputStream eir = process.getErrorStream();
//         System.out.println(process.isAlive());
        //获得进程的输出流
        InputStream is = process.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String read = null;
        while((read=br.readLine()) != null) {
            System.out.println(read); 
        }
        
//        Thread.sleep(10000);
        //判断进程是否活着
        Boolean isAlive = process.isAlive();
        System.out.println(isAlive);
        if(isAlive) {
          System.out.println("success");
        }else {
          System.out.println("failed");
        }
        
        System.out.println(process.isAlive());
        System.out.println(process.isAlive());
        System.out.println(process.isAlive());
        System.out.println(process.isAlive());
        /*
         * exitValue，进程退出码，若调用时进程未结束，则会出异常
         */    
        int exitValue = process.exitValue();
        System.out.println(exitValue);
        if(exitValue != 0) {
          
        }else {
          
        }
       
      
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
      
      
      
      
}
