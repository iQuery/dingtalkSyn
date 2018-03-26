package com.imp;

import com.imp.task.MyTimerTask;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class Main {
    public static void main(String[] args0){
        BasicConfigurator.configure();
        //PropertyConfigurator.configure( "log4j.properties");



        PropertyConfigurator.configure(System.getProperty("user.dir")+ "/log4j.properties");



        MyTimerTask.getInstance();
    }
}
