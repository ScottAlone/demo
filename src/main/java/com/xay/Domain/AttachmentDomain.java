package com.xay.Domain;

import com.xay.MySQL.DO.AttachmentDO;

import java.text.SimpleDateFormat;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/6.
 */
public class AttachmentDomain {
    private Integer attachId;
    private String createTime;
    private String updateTime;
    private String attachName;
    private String gUsername;
    private String token;

    public AttachmentDomain(String attachName, String gUsername, String token) {
        this.attachName = attachName;
        this.gUsername = gUsername;
        this.token = token;
    }

    public AttachmentDomain(AttachmentDO attachmentDO, String createTime, String updateTime){
        this.attachId = attachmentDO.getAttachment_id();
        this.attachName = attachmentDO.getAttachment_name();
        this.gUsername = attachmentDO.getG_username();
        this.token = attachmentDO.getToken();
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public AttachmentDomain() {

    }

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getgUsername() {
        return gUsername;
    }

    public void setgUsername(String gUsername) {
        this.gUsername = gUsername;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
