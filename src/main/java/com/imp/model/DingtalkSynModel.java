package com.imp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by i_it on 2017/12/01.
 * 钉钉同步信息表 Model
 */
public class DingtalkSynModel implements Serializable {
    /**
     * 版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 唯一键UUID
     */
    private String uid;

    /**
     * corpId
     */
    private String corpId;

    /**
     * channelSecret
     */
    private String channelSecret;

    /**
     * corpSecret
     */
    private String corpSecret;

    /**
     * 访问URL
     */
    private String url;

    /**
     * 数据类型
     */
    private String dateType;

    /**
     * json数据
     */
    private String data;

    /**
     * 错误代码
     */
    private Integer errCode;

    /**
     * 错误消息
     */
    private String errMsg;

    /**
     * 结果json
     */
    private String result;

    private Integer flag;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId == null ? null : corpId.trim();
    }

    public String getChannelSecret() {
        return channelSecret;
    }

    public void setChannelSecret(String channelSecret) {
        this.channelSecret = channelSecret == null ? null : channelSecret.trim();
    }

    public String getCorpSecret() {
        return corpSecret;
    }

    public void setCorpSecret(String corpSecret) {
        this.corpSecret = corpSecret == null ? null : corpSecret.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType == null ? null : dateType.trim();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg == null ? null : errMsg.trim();
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", uid=").append(uid);
        sb.append(", corpId=").append(corpId);
        sb.append(", channelSecret=").append(channelSecret);
        sb.append(", corpSecret=").append(corpSecret);
        sb.append(", url=").append(url);
        sb.append(", dateType=").append(dateType);
        sb.append(", data=").append(data);
        sb.append(", errCode=").append(errCode);
        sb.append(", errMsg=").append(errMsg);
        sb.append(", result=").append(result);
        sb.append(", flag=").append(flag);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DingtalkSynModel other = (DingtalkSynModel) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
                && (this.getCorpId() == null ? other.getCorpId() == null : this.getCorpId().equals(other.getCorpId()))
                && (this.getChannelSecret() == null ? other.getChannelSecret() == null : this.getChannelSecret().equals(other.getChannelSecret()))
                && (this.getCorpSecret() == null ? other.getCorpSecret() == null : this.getCorpSecret().equals(other.getCorpSecret()))
                && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
                && (this.getDateType() == null ? other.getDateType() == null : this.getDateType().equals(other.getDateType()))
                && (this.getData() == null ? other.getData() == null : this.getData().equals(other.getData()))
                && (this.getErrCode() == null ? other.getErrCode() == null : this.getErrCode().equals(other.getErrCode()))
                && (this.getErrMsg() == null ? other.getErrMsg() == null : this.getErrMsg().equals(other.getErrMsg()))
                && (this.getResult() == null ? other.getResult() == null : this.getResult().equals(other.getResult()))
                && (this.getFlag() == null ? other.getFlag() == null : this.getFlag().equals(other.getFlag()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getCorpId() == null) ? 0 : getCorpId().hashCode());
        result = prime * result + ((getChannelSecret() == null) ? 0 : getChannelSecret().hashCode());
        result = prime * result + ((getCorpSecret() == null) ? 0 : getCorpSecret().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getDateType() == null) ? 0 : getDateType().hashCode());
        result = prime * result + ((getData() == null) ? 0 : getData().hashCode());
        result = prime * result + ((getErrCode() == null) ? 0 : getErrCode().hashCode());
        result = prime * result + ((getErrMsg() == null) ? 0 : getErrMsg().hashCode());
        result = prime * result + ((getResult() == null) ? 0 : getResult().hashCode());
        result = prime * result + ((getFlag() == null) ? 0 : getFlag().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }
}