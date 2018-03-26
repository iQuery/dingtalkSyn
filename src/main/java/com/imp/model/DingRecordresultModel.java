package com.imp.model;

/**
 * Created by i_it on 2017/6/26.
 */
public class DingRecordresultModel {

/*    errcode	返回码
    errmsg	对返回码的文本描述内容
    id	唯一标示ID
    groupId	考勤组ID
    planId	排班ID
    recordId	打卡记录ID
    workDate	工作日
    userId	用户ID
    checkType	考勤类型（OnDuty：上班，OffDuty：下班）
    timeResult	时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；NotSigned:未打卡）
    locationResult	位置结果（Normal:范围内；Outside:范围外）
    approveId	关联的审批id
    baseCheckTime	计算迟到和早退，基准时间
    userCheckTime	实际打卡时间*/

    private String baseCheckTime;
    private String checkType;
    private String corpId;
    private Long groupId;
    private Long id;
    private Long planId;
    private Long recordId;
    private String locationResult;
    //时间结果（Normal:正常;Early:早退; Late:迟到;SeriousLate:严重迟到；NotSigned:未打卡）
    private String timeResult;
    private String userCheckTime;
    private String userId;
    private String workDate;

    public String getBaseCheckTime() {
        return baseCheckTime;
    }

    public void setBaseCheckTime(String baseCheckTime) {
        this.baseCheckTime = baseCheckTime;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationResult() {
        return locationResult;
    }

    public void setLocationResult(String locationResult) {
        this.locationResult = locationResult;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public String getTimeResult() {
        return timeResult;
    }

    public void setTimeResult(String timeResult) {
        this.timeResult = timeResult;
    }

    public String getUserCheckTime() {
        return userCheckTime;
    }

    public void setUserCheckTime(String userCheckTime) {
        this.userCheckTime = userCheckTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }
}
