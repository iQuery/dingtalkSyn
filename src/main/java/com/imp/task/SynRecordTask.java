package com.imp.task;

import com.imp.util.SynRecordUtil;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.TimerTask;

public class SynRecordTask extends TimerTask{
    static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SynRecordTask.class);
    Properties prop = new Properties();
    InputStreamReader in = null;
    String[] urls = null;
    @Override
    public void run() {
        try {
            in = new InputStreamReader(new FileInputStream(System.getProperty("user.dir") + "/config.properties"), "UTF-8");
            prop.load(in);
            urls = prop.getProperty("service.urls").trim().split(";");
            logger.info("SynRecordTask: " + Arrays.toString(urls));
            for (String serverUrl: urls){
                if (serverUrl != null && serverUrl.trim().length() > 0){
                    SynRecordUtil.getData(serverUrl);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }



    }
}
