package com.xay.Domain;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/6.
 */
public class AttachmentDomain {
    private Integer customerId;
    private Integer cityId;
    private String tags;
    private byte[] file;
    private String fileType;

    public AttachmentDomain(Integer customerId, Integer cityId, String tags, byte[] file, String fileType){
        this.customerId = customerId;
        this.cityId = cityId;
        this.tags = tags;
        this.file = file;
        this.fileType = fileType;
    }

    public AttachmentDomain(){

    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}
