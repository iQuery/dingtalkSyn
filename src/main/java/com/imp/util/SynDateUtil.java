package com.imp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imp.httpclient.AuthHelper;
import com.imp.httpclient.HttpHelper;
import com.imp.model.DataType;
import com.imp.model.DingtalkSynModel;
import com.imp.model.MessageModel;

import java.util.List;

public class SynDateUtil {

    public static final String GET_SYN_DATA = "/dingtalk/getSynData.do";
    public static final String CALL_BACK = "/dingtalk/callBack.do";

    public static void getData(String serverUrl){
        String jsonStr = HttpHelper.httpPost(serverUrl + GET_SYN_DATA, null);
        List<DingtalkSynModel> list = JSONObject.parseArray(jsonStr,DingtalkSynModel.class);
        MessageModel message = new MessageModel();
        if(list != null){
            for (DingtalkSynModel dingtalkSynModel: list){
                message.setUid(dingtalkSynModel.getUid());
                message.setDataType(DataType.valueOf(dingtalkSynModel.getDateType()));
                message.setUpdateTime(dingtalkSynModel.getUpdateTime());
                if (dingtalkSynModel.getDateType().equals(DataType.USER_ADD.toString()) || dingtalkSynModel.getDateType().equals(DataType.USER_UPDATE.toString()) ){
                    synUser(message, serverUrl, dingtalkSynModel);
                }else if (dingtalkSynModel.getDateType().equals(DataType.USER_DELETE.toString())){
                    delUser(message, serverUrl, dingtalkSynModel);
                }else if (dingtalkSynModel.getDateType().equals(DataType.GET_DEPT_USER.toString())){
                    getDeptUser(message, serverUrl, dingtalkSynModel);
                }
                HttpHelper.httpPost(serverUrl + CALL_BACK, JSON.toJSONString(message));
            }
        }

    }
    private static void synUser(MessageModel message, String serverUrl, DingtalkSynModel dingtalkSynModel){
        try {
            String accessToken = AuthHelper.getAccessToken(message,dingtalkSynModel.getCorpId(), dingtalkSynModel.getCorpSecret());
            String result;
            if (accessToken == null){
                return;
            }else{
                result = HttpHelper.httpPost(dingtalkSynModel.getUrl() + accessToken, dingtalkSynModel.getData());
            }
            if (result == null){
                message.setErrorCode("-1");
                message.setErrmsg("访问钉钉时出错了！");
                return;
            }else{
                JSONObject resultObject = JSON.parseObject(result);
                message.setErrorCode(resultObject.getString("errcode"));
                message.setErrmsg(resultObject.getString("errmsg"));
            }
        }catch (Exception e){
            message.setErrorCode("-2");
            message.setErrmsg("数据解析异常");
        }
    }
    private static void delUser(MessageModel message, String serverUrl,  DingtalkSynModel dingtalkSynModel){
        try {
            String accessToken = AuthHelper.getAccessToken(message,dingtalkSynModel.getCorpId(), dingtalkSynModel.getCorpSecret());
            String result = null;
            if (accessToken == null){
                return;
            }else{
                JSONObject jsonObject = JSON.parseObject(dingtalkSynModel.getData());
                String userId = jsonObject.getString("userId");
                if (userId == null || userId.trim().equals("")){
                    message.setErrorCode("-5");
                    message.setErrmsg("删除userId不能为空！");
                    return;
                }else{
                    result = HttpHelper.httpGet(dingtalkSynModel.getUrl() + accessToken + "&userid=" + userId);
                }
            }
            if (result == null){
                message.setErrorCode("-1");
                message.setErrmsg("访问钉钉时出错了！");
                return;
            }else{
                JSONObject resultObject = JSON.parseObject(result);
                message.setErrorCode(resultObject.getString("errcode"));
                message.setErrmsg(resultObject.getString("errmsg"));
            }
        }catch (Exception e){
            message.setErrorCode("-2");
            message.setErrmsg("数据解析异常");
        }
    }
    private static void getRecord(MessageModel message, String serverUrl, DingtalkSynModel dingtalkSynModel){
        try {
            String accessToken = AuthHelper.getAccessToken(message,dingtalkSynModel.getCorpId(), dingtalkSynModel.getCorpSecret());
            String result;
            if (accessToken == null){
                return;
            }else{
                result = HttpHelper.httpPost(dingtalkSynModel.getUrl() + accessToken, dingtalkSynModel.getData());
            }
            if (result == null){
                message.setErrorCode("-3");
                message.setErrmsg("访问钉钉时出错了！");
                return;
            }else{
                JSONObject resultObject = JSON.parseObject(result);
                message.setErrorCode(resultObject.getString("errcode"));
                message.setErrmsg(resultObject.getString("errmsg"));
            }
        }catch (Exception e){
            message.setErrorCode("-4");
            message.setErrmsg("数据解析异常");
        }
    }


    /**
     * {
     "errcode": 0,
     "errmsg": "ok",
     "hasMore": false,
     "userlist": [
     {
     "userid": "zhangsan",
     "name": "张三"
     }
     ]
     }
     * @param message
     * @param serverUrl
     * @param dingtalkSynModel
     */

    private static void getDeptUser(MessageModel message, String serverUrl,  DingtalkSynModel dingtalkSynModel){
        try {
            String accessToken = AuthHelper.getAccessToken(message,dingtalkSynModel.getCorpId(), dingtalkSynModel.getCorpSecret());
            String result = null;
            if (accessToken == null){
                return;
            }else{
                JSONObject jsonObject = JSON.parseObject(dingtalkSynModel.getData());
                String deptId = jsonObject.getString("department_id");
                if (deptId == null || deptId.trim().equals("")){
                    deptId = "1";
                }
                result = HttpHelper.httpGet(dingtalkSynModel.getUrl() + accessToken + "&department_id=" + deptId);
            }
            if (result == null){
                message.setErrorCode("-1");
                message.setErrmsg("访问钉钉时出错了！");
                return;
            }else{
                JSONObject resultObject = JSON.parseObject(result);
                message.setErrorCode(resultObject.getString("errcode"));
                message.setErrmsg(resultObject.getString("errmsg"));
                message.setData(resultObject.getString("userlist"));
            }
        }catch (Exception e){
            message.setErrorCode("-2");
            message.setErrmsg("数据解析异常");
        }
    }
}
