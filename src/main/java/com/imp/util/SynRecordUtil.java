package com.imp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.imp.httpclient.AuthHelper;
import com.imp.httpclient.HttpHelper;
import com.imp.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SynRecordUtil {
    public static final String OAPI_HOST = "https://oapi.dingtalk.com";
    public static final String GET_SYN_DATA = "/dingtalk/getMachineList.do";
    public static final String CALL_BACK = "/dingtalk/callBackRecord.do";
    public static final int limitNum = 50;
    /**
     * 获取部门人员信息
     * Https请求方式: GET
     */
    public static final String GET_DEPT_USER = "/user/simplelist?access_token=ACCESS_TOKEN&department_id=1";

    public static void getData(String serverUrl) {
        String jsonStr = HttpHelper.httpPost(serverUrl + GET_SYN_DATA, null);
        List<MessageModel> list = JSONObject.parseArray(jsonStr, MessageModel.class);

        if (list != null) {
            for (MessageModel inMessage : list) {
                List<DingRecordModel> dingRecordModelList = new ArrayList<>();
                userListPaging(inMessage, dingRecordModelList);
                HttpHelper.httpPost(serverUrl + CALL_BACK, JSON.toJSONString(dingRecordModelList));
            }

        }
    }

    public static void userListPaging(MessageModel inMessage, List<DingRecordModel> dingRecordModelList) {
        String[] userList = inMessage.getUserIdList();
        if (userList == null || userList.length == 0){
            return;
        }
        for(int i = 0; i < userList.length; i += limitNum){
            String[] userListTemp = Arrays.copyOfRange(userList, i, Math.min(userList.length, i + limitNum));
            DingRecordModel dingRecordModel = new DingRecordModel();
            dingRecordModel.setCorpId(inMessage.getCorpId());
            dingRecordModel.setRecordresult(new ArrayList<DingRecordresultModel>());
            dingRecordModel.setUserIdList(userListTemp);
            getRecord(inMessage, dingRecordModel);
            if (dingRecordModel.getRecordresult().size() > 0){
                dingRecordModelList.add(dingRecordModel);
            }
        }
    }

    private static void getRecord(MessageModel inMessage, DingRecordModel dingRecordModel) {
        int offset = 0;
        try {
            MessageModel backMessage = new MessageModel();
//            if(!"ding2d5fc986802e338e35c2f4657eb6378f".equals(inMessage.getCorpId())){
//                return;
//            }
            String accessToken = AuthHelper.getAccessToken(backMessage, inMessage.getCorpId(), inMessage.getCorpsecret());
            if (accessToken == null) {
                return;
            } else {
                String dateNow = DatetimeUtil.getDateString(new Date());
                String workDateFrom = DatetimeUtil.getDateString(inMessage.getUpdateTime());
                String workDateTo = DatetimeUtil.getDateString(new Date());
                if (workDateFrom != null) {
                    while (DatetimeUtil.getDistanceDays(workDateFrom, dateNow) > 7) {
                        workDateTo = DatetimeUtil.plusTimeMillis(workDateFrom, (long) 1000 * 3600 * 24 * 7);
                        if (workDateFrom.compareTo(workDateTo) > 0) {
                            System.out.println("workDateFrom = " + workDateFrom + "; workDateTo = " + workDateTo);
                        }
                        try {
                            post(offset, dingRecordModel,backMessage, accessToken , workDateFrom, workDateTo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        workDateFrom = workDateTo;
                    }
                    try {
                        post(offset, dingRecordModel,backMessage, accessToken , workDateFrom, workDateTo);
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

    private static void post(int offset, DingRecordModel dingRecordModel,MessageModel outMessage, String accessToken , String workDateFrom, String workDateTo){
        String url = OAPI_HOST + "/attendance/list?" +"access_token=" + accessToken;
        JSONObject args = new JSONObject();
        args.put("userIdList", dingRecordModel.getUserIdList());
        args.put("workDateFrom", workDateFrom);
        args.put("workDateTo", workDateTo);
        args.put("offset", offset);
        args.put("limit", limitNum);
        String result =  HttpHelper.httpPost(url, args.toJSONString());
        System.out.println(DatetimeUtil.toDefaultDateString(new Date()) + "   " + result);
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
        //递归查询所有记录
        if("ok".equals(resultModel.getErrmsg()) && resultModel.isHasMore()){
            post(offset + limitNum, dingRecordModel, outMessage, accessToken, workDateFrom, workDateTo);
        }
    }
}


