package com.xay.MySQL.DO;


/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class AttachmentDO {
    private String attachment_name;
    private byte[] file;

    public AttachmentDO(String attachmentName, byte[] file){
        this.attachment_name = attachmentName;
        this.file = file;
    }

    public AttachmentDO(String attachmentName){
        this.attachment_name = attachment_name;
    }

    public String getAttachment_name() {
        return attachment_name;
    }

    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
