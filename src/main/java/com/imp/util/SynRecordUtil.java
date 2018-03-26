package com.imp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imp.httpclient.AuthHelper;
import com.imp.httpclient.HttpHelper;
import com.imp.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SynRecordUtil {
    public static final String OAPI_HOST = "https://oapi.dingtalk.com";
    public static final String GET_SYN_DATA = "/dingtalk/getMachineList.do";
    public static final String CALL_BACK = "/dingtalk/callBackRecord.do";
    /**
     * 获取部门人员信息
     * Https请求方式: GET
     */
    public static final String GET_DEPT_USER = "/user/simplelist?access_token=ACCESS_TOKEN&department_id=1";

    public static void getData(String serverUrl) {
        String jsonStr = HttpHelper.httpPost(serverUrl + GET_SYN_DATA, null);
        List<MessageModel> list = JSONObject.parseArray(jsonStr, MessageModel.class);
        List<DingRecordModel> dingRecordModelList = new ArrayList<>();
        if (list != null) {
            for (MessageModel inMessage : list) {
                DingRecordModel dingRecordModel = new DingRecordModel();
                dingRecordModel.setCorpId(inMessage.getCorpId());
                dingRecordModel.setRecordresult(new ArrayList<DingRecordresultModel>());
                getRecord(inMessage, dingRecordModel);
                if (dingRecordModel.getRecordresult().size() > 0){
                    dingRecordModelList.add(dingRecordModel);
                }
            }
            HttpHelper.httpPost(serverUrl + CALL_BACK, JSON.toJSONString(dingRecordModelList));
        }
    }

    private static void getRecord(MessageModel inMessage, DingRecordModel dingRecordModel) {
        try {
            MessageModel backMessage = new MessageModel();
            if(inMessage.getCorpId().equals("ding2de2a71870809c4f35c2f4657eb6378f")){
                System.out.println("JL4");
            }
            String accessToken = AuthHelper.getAccessToken(backMessage, inMessage.getCorpId(), inMessage.getCorpsecret());
            if (accessToken == null) {
                return;
            } else {
                String dateNow = DatetimeUtil.toDefaultDateString(new Date());
                String workDateFrom = DatetimeUtil.toDefaultDateString(inMessage.getUpdateTime());
                String workDateTo = DatetimeUtil.toDefaultDateString(new Date());
                if (workDateFrom != null) {
                    while (DatetimeUtil.getDistanceDays(workDateFrom, dateNow) > 7) {
                        workDateTo = DatetimeUtil.plusTimeMillis(workDateFrom, (long) 1000 * 3600 * 24 * 7);
                        if (workDateFrom.compareTo(workDateTo) > 0) {
                            System.out.println("workDateFrom = " + workDateFrom + "; workDateTo = " + workDateTo);
                        }
                        try {
                            post(dingRecordModel,backMessage, accessToken , workDateFrom, workDateTo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        workDateFrom = workDateTo;
                    }
                    try {
                        post(dingRecordModel,backMessage, accessToken , workDateFrom, workDateTo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            dingRecordModel.setErrcode(-2);
            dingRecordModel.setErrmsg("数据解析异常");
        }
    }

    private static void post(DingRecordModel dingRecordModel,MessageModel outMessage, String accessToken , String workDateFrom, String workDateTo){
        String url = OAPI_HOST + "/attendance/list?" +"access_token=" + accessToken;
        JSONObject args = new JSONObject();
        args.put("userId", null);
        args.put("workDateFrom", workDateFrom);
        args.put("workDateTo", workDateTo);
        String result =  HttpHelper.httpPost(url, args.toJSONString());
        DingRecordModel resultModel = JSON.parseObject(result,DingRecordModel.class);
        if(resultModel == null){
            return;
        }
        if (resultModel.getErrcode() == 0 ){
            dingRecordModel.setErrcode(resultModel.getErrcode());
            dingRecordModel.setErrmsg(resultModel.getErrmsg());
            dingRecordModel.setUpdateTime(DatetimeUtil.string2Date(workDateTo));
            dingRecordModel.getRecordresult().addAll(resultModel.getRecordresult());
        }
    }
}


