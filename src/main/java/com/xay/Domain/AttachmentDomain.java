package com.xay.Domain;

import com.xay.MySQL.DO.AttachmentDO;

import java.sql.Date;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/6.
 */
public class AttachmentDomain {
    private Integer attachId;
    private Date createTime;
    private Date updateTime;
    private byte[] file;
    private String attachName;
    private Integer ownerType;
    private Integer ownerId;
    private Integer fileType;

    public AttachmentDomain(byte[] file, String attachName, Integer ownerType, Integer ownerId, Integer fileType) {
        this.file = file;
        this.attachName = attachName;
        this.ownerType = ownerType;
        this.ownerId = ownerId;
        this.fileType = fileType;
    }

    public AttachmentDomain(Integer attachId, byte[] file, String attachName, Integer ownerType, Integer ownerId, Integer fileType) {
        this.attachId = attachId;
        this.file = file;
        this.attachName = attachName;
        this.ownerType = ownerType;
        this.ownerId = ownerId;
        this.fileType = fileType;
    }

    public AttachmentDomain(AttachmentDO attachmentDO){
        this.attachId = attachmentDO.getAttachment_id();
        this.file = attachmentDO.getFile();
        this.attachName = attachmentDO.getAttachment_name();
        this.ownerType = attachmentDO.getOwner_type();
        this.ownerId = attachmentDO.getOwner_id();
        this.fileType = attachmentDO.getFile_type();
    }

    public AttachmentDomain() {

    }

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public Integer getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(Integer ownerType) {
        this.ownerType = ownerType;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }
}
