package com.xay.MySQL.DO;


/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class AttachmentDO {
    private String attachment_name;
    private Integer city_id;
    private String tags;
    private byte[] file;

    public AttachmentDO(String attachmentName, Integer city_id, String tags, byte[] file){
        this.attachment_name = attachmentName;
        this.city_id = city_id;
        this.tags = tags;
        this.file = file;
    }

    public AttachmentDO(String attachmentName){
        this.attachment_name = attachment_name;
    }

    public AttachmentDO(String attachmentName, byte[] file){
        this.attachment_name = attachmentName;
        this.file = file;
    }

    public String getAttachment_name() {
        return attachment_name;
    }

    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
