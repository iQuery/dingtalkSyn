package com.imp.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imp.model.MessageModel;
import com.imp.model.TokenModel;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by i_it on 2017/6/27.
 */
public class AuthHelper {
    public  static Hashtable<String ,TokenModel> tokenMap = new Hashtable<>();

    /*
     * 在此方法中，为了避免频繁获取access_token，
     * 在距离上一次获取access_token时间在两个小时之内的情况，
     * 将直接从持久化存储中读取access_token
     *
     * 因为access_token和jsapi_ticket的过期时间都是7200秒
     * 所以在获取access_token的同时也去获取了jsapi_ticket
     * 注：jsapi_ticket是在前端页面JSAPI做权限验证配置的时候需要使用的
     * 具体信息请查看开发者文档--权限验证配置
     */
    public static String getAccessToken(MessageModel messageModel, String corpId, String corpSecret){
        if (tokenMap.containsKey(corpId) && !invalidToken(tokenMap.get(corpId).getUpdateTime())){
            return tokenMap.get(corpId).getAccessToken();
        }
        String accessToken = null;
        try {
            String url = Env.OAPI_HOST + "/gettoken?corpid=" + corpId + "&corpsecret=" + corpSecret;
            JSONObject response = JSON.parseObject(HttpHelper.httpGet(url));
            if (response.getInteger("errcode") == 0 && response.containsKey("access_token")){
                accessToken = response.getString("access_token");
                TokenModel tokenModel = new TokenModel();
                tokenModel.setAccessToken(accessToken);
                tokenModel.setUpdateTime(new Date());
                tokenMap.put(corpId, tokenModel);
                return accessToken;
            }else{
                messageModel.setErrorCode(response.getString("errcode"));
                messageModel.setErrmsg(response.getString("errmsg"));
            }
        }catch (Exception e){
            messageModel.setErrorCode("1000");
            messageModel.setErrmsg("获取accessToken异常！");
            return accessToken;
        }
        return accessToken;
    }

    //检查token是否超过1小时40分钟，
    private static boolean invalidToken(Date date){
        return (System.currentTimeMillis() - date.getTime()) > (1000 * 60 * 100);
    }

}
