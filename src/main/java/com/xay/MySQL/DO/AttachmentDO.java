package com.xay.MySQL.DO;


import com.xay.Domain.AttachmentDomain;

import java.sql.Date;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class AttachmentDO {
    private Integer attachment_id;
    private String attachment_name;
    private Date create_time;
    private Date update_time;
    private byte[] file;
    private Integer owner_type;
    private Integer owner_id;
    private Integer file_type;

    public AttachmentDO(Integer attachment_id, String attachment_name, Date create_time, Date update_time, byte[] file, Integer owner_type, Integer owner_id, Integer file_type) {
        this.attachment_id = attachment_id;
        this.attachment_name = attachment_name;
        this.create_time = create_time;
        this.update_time = update_time;
        this.file = file;
        this.owner_type = owner_type;
        this.owner_id = owner_id;
        this.file_type = file_type;
    }

    public AttachmentDO(AttachmentDomain attachmentDomain){
        this.attachment_id = attachmentDomain.getAttachId();
        this.attachment_name = attachmentDomain.getAttachName();
        this.create_time = attachmentDomain.getCreateTime();
        this.update_time = attachmentDomain.getUpdateTime();
        this.file = attachmentDomain.getFile();
        this.owner_type = attachmentDomain.getOwnerType();
        this.owner_id = attachmentDomain.getOwnerId();
        this.file_type = attachmentDomain.getFileType();
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

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Integer getOwner_type() {
        return owner_type;
    }

    public void setOwner_type(Integer owner_type) {
        this.owner_type = owner_type;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Integer getFile_type() {
        return file_type;
    }

    public void setFile_type(Integer file_type) {
        this.file_type = file_type;
    }
}
