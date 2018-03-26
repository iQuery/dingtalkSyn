package com.imp.model;

import java.util.Date;
import java.util.List;

/**
 * Created by i_it on 2017/6/26.
 */
public class DingRecordModel {

    private String corpId;
    private Integer errcode;
    private String errmsg;
    private Date updateTime;

    private List<DingRecordresultModel> recordresult;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<DingRecordresultModel> getRecordresult() {
        return recordresult;
    }

    public void setRecordresult(List<DingRecordresultModel> recordresult) {
        this.recordresult = recordresult;
    }
}
