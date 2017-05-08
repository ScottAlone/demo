package com.xay.Service.impl;

import com.xay.Domain.AttachmentDomain;
import com.xay.Domain.BaseResult;
import com.xay.MySQL.DO.AttachmentDO;
import com.xay.MySQL.Mapper.AttachmentMapper;
import com.xay.Service.AttachmentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public BaseResult upload(AttachmentDomain attachmentDomain) throws IOException{
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentNameByCustomerId(attachmentDomain.getCustomerId());
        String attachName = RandomStringUtils.randomAlphanumeric(50);
        String finalPath = attachName + "." + attachmentDomain.getFileType();
        byte[] files = attachmentDomain.getFile();
        if (attachmentDO == null){
            attachmentMapper.insertAttachment(new AttachmentDO(finalPath, files));
        }else {
            attachmentDO = attachmentMapper.getAttachmentByAttachmentName(attachmentDO.getAttachment_name());
            if (attachmentDO == null){
                attachmentMapper.insertAttachment(new AttachmentDO(finalPath, files));
            }else {
                attachName = attachmentDO.getAttachment_name();
                finalPath = attachName;
                attachmentMapper.updateAttachment(new AttachmentDO(finalPath, files));
            }
        }
        attachmentMapper.updateAttachmentInCustomer(finalPath, attachmentDomain.getCustomerId());
        return new BaseResult();
    }

    @Override
    public BaseResult download(Integer customerId) throws Exception{
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentNameByCustomerId(customerId);
        if (attachmentDO == null){
            return new BaseResult(500, "没有附件");
        }else {
            attachmentDO = attachmentMapper.getAttachmentByAttachmentName(attachmentDO.getAttachment_name());
            if (attachmentDO == null){
                return new BaseResult(500, "没有附件");
            }else {
                attachmentDO = attachmentMapper.getAttachmentByAttachmentName(attachmentDO.getAttachment_name());
                byte[] files = attachmentDO.getFile();
                String fileName = attachmentDO.getAttachment_name();
                return new BaseResult(200, fileName, files);
            }
        }
    }
}
