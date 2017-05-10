package com.xay.MySQL.DO;


import com.xay.Domain.AttachmentDomain;

import java.sql.Timestamp;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class AttachmentDO {
    private Integer attachment_id;
    private String attachment_name;
    private Timestamp create_time;
    private Timestamp update_time;
    private String g_username;

    public AttachmentDO(Integer attachment_id, String attachment_name, Timestamp create_time, Timestamp update_time, String g_username) {
        this.attachment_id = attachment_id;
        this.attachment_name = attachment_name;
        this.create_time = create_time;
        this.update_time = update_time;
        this.g_username = g_username;
    }

    public AttachmentDO(AttachmentDomain attachmentDomain){
        this.attachment_id = attachmentDomain.getAttachId();
        this.attachment_name = attachmentDomain.getAttachName();
        this.g_username = attachmentDomain.getgUsername();
    }

    public AttachmentDO(String attachment_name, String g_username) {
        this.attachment_name = attachment_name;
        this.g_username = g_username;
    }

    public AttachmentDO(){

    }

    public void setAttachment_id(Integer attachment_id) {
        this.attachment_id = attachment_id;
    }

    public Integer getAttachment_id() {
        return attachment_id;
    }

    public String getAttachment_name() {
        return attachment_name;
    }

    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }

    public String getgUsername() {
        return g_username;
    }

    public void setgUsername(String g_username) {
        this.g_username = g_username;
    }
}
