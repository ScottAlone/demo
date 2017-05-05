package com.xay.MySQL.DO;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public class AttachmentDO {
    private String attachmentName;
    private Integer cityId;
    private String tags;

    public AttachmentDO(String attachmentName, Integer cityId, String tags){
        this.attachmentName = attachmentName;
        this.cityId = cityId;
        this.tags = tags;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
