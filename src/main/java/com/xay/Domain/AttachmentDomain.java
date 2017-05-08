package com.xay.Domain;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/6.
 */
public class AttachmentDomain {
    private Integer customerId;
    private byte[] file;
    private String fileType;

    public AttachmentDomain(Integer customerId, byte[] file, String fileType){
        this.customerId = customerId;
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
