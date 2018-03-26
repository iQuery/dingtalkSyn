package com.imp.httpclient;

/**
 * Created by i_it on 2017/6/27.
 */

import com.alibaba.fastjson.JSONObject;
import com.imp.util.DatetimeUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;


public class HttpHelper {

    static final Logger logger = Logger.getLogger(HttpHelper.class);

    public static String httpGet(String url){
        HttpGet httpGet = new HttpGet(url);
        //此处区别PC终端类型
        httpGet.addHeader("typeFlg", "9");
        httpGet.setConfig(RequestConfig.DEFAULT);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //HttpHost httpProxy = new HttpHost("192.168.100.2", 8666, "http");
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        try {
            response = httpClient.execute(httpGet, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode() + ", url=" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                //JSONObject result = JSON.parseObject(resultStr);
                return resultStr;
//                if (result.getInteger("errcode") == 0) {
////                	result.remove("errcode");
////                	result.remove("errmsg");
//                    return result;
//                } else {
//                    System.out.println("request url=" + url);
//                    System.out.println("return value=" + resultStr);
//                    int errCode = result.getInteger("errcode");
//                    String errMsg = result.getString("errmsg");
//                    logger.error(DatetimeUtil.getFormatTime(new Date()) + "; url = " + url);
//                    logger.error(DatetimeUtil.getFormatTime(new Date()) + "; msg = " + errMsg);
//                    throw new OApiException(errCode, errMsg);
//                }
            }
        } catch (IOException e) {
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            logger.error(DatetimeUtil.getFormatTime(new Date()) + "; url = " + url);
            logger.error(DatetimeUtil.getFormatTime(new Date()) + "; msg = " + e.getMessage());

        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }


    public static String httpPost(String url, String jsondata){
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //HttpHost httpProxy = new HttpHost("192.168.100.2", 8666, "http");
        RequestConfig requestConfig = RequestConfig.custom().
                setSocketTimeout(5000).setConnectTimeout(5000).build();
        httpPost.setConfig(requestConfig);
        httpPost.addHeader("Content-Type", "application/json");
        //此处区别PC终端类型
        httpPost.addHeader("typeFlg", "9");
        try {
            if (jsondata != null){
                StringEntity requestEntity = new StringEntity(jsondata, "utf-8");
                httpPost.setEntity(requestEntity);
            }
            response = httpClient.execute(httpPost, new BasicHttpContext());
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("request url failed, http code=" + response.getStatusLine().getStatusCode()+ ", url=" + url);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String resultStr = EntityUtils.toString(entity, "utf-8");
                return resultStr;
            }
        } catch (ConnectTimeoutException e) {
            logger.error(DatetimeUtil.getFormatTime(new Date()) + "; url = " + url);
            logger.error(DatetimeUtil.getFormatTime(new Date()) + "; msg = " + e.getMessage());
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errcode","500");
            jsonObject.put("errmsg","请求超时");
            return jsonObject.toJSONString();
        }catch (IOException e) {
            logger.error(DatetimeUtil.getFormatTime(new Date()) + "; url = " + url);
            logger.error(DatetimeUtil.getFormatTime(new Date()) + "; msg = " + e.getMessage());
            System.out.println("request url=" + url + ", exception, msg=" + e.getMessage());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("errcode","99");
            jsonObject.put("errmsg","POST IO异常");
            return jsonObject.toJSONString();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }






}
