package com.imp.model;

import java.util.Date;

/**
 * Created by i_it on 2017/6/27.
 */
public class TokenModel {

    private long lastTime = 0;
    private String corpid;
    private String accessToken;
    private String ssosecret;
    private String corpsecret;
    private String serialno;
    private String virtualIp;
    private Date updateTime;

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSsosecret() {
        return ssosecret;
    }

    public void setSsosecret(String ssosecret) {
        this.ssosecret = ssosecret;
    }

    public String getCorpsecret() {
        return corpsecret;
    }

    public void setCorpsecret(String corpsecret) {
        this.corpsecret = corpsecret;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getVirtualIp() {
        return virtualIp;
    }

    public void setVirtualIp(String virtualIp) {
        this.virtualIp = virtualIp;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
