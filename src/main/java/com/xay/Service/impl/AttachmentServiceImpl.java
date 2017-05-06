package com.xay.Service.impl;

import com.xay.Domain.AttachmentDomain;
import com.xay.Domain.BaseResult;
import com.xay.MySQL.DO.AttachmentDO;
import com.xay.MySQL.Mapper.AttachmentMapper;
import com.xay.Service.AttachmentService;
import com.xay.Util.SerializableUtil;
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
        File file = new File("C:\\Users\\Administrator\\Desktop\\demo\\src\\main\\resources\\static\\" + finalPath);
        byte[] files = attachmentDomain.getFile();
        FileOutputStream outStream = new FileOutputStream(file);
        outStream.write(files);
        outStream.close();
        if (attachmentDO == null){
            attachmentMapper.insertAttachment(new AttachmentDO(finalPath, attachmentDomain.getCityId(), attachmentDomain.getTags(), SerializableUtil.fileToBytes(file)));
        }else {
            attachmentDO = attachmentMapper.getAttachmentByAttachmentName(attachmentDO.getAttachment_name());
            if (attachmentDO == null){
                attachmentMapper.insertAttachment(new AttachmentDO(finalPath, attachmentDomain.getCityId(), attachmentDomain.getTags(), SerializableUtil.fileToBytes(file)));
            }else {
                attachName = attachmentDO.getAttachment_name();
                finalPath = attachName;
                attachmentMapper.updateAttachment(new AttachmentDO(finalPath, attachmentDomain.getCityId(), attachmentDomain.getTags(), SerializableUtil.fileToBytes(file)));
                file.renameTo(new File("C:\\Users\\Administrator\\Desktop\\demo\\src\\main\\resources\\static\\" + finalPath));
            }
        }
        attachmentMapper.updateAttachmentInCustomer(finalPath, attachmentDomain.getCityId());
        return new BaseResult();
    }

    @Override
    public BaseResult download(Integer customerId) throws Exception{
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentNameByCustomerId(customerId);
        if (attachmentDO == null){
            return new BaseResult(500, "没有附件");
        }else {
            attachmentDO = attachmentMapper.getAttachmentByAttachmentName(attachmentDO.getAttachment_name());
            File f = new File("C:\\Users\\Administrator\\Desktop\\demo\\src\\main\\resources\\static\\" + attachmentDO.getAttachment_name());
            if (!f.exists()){
                f.createNewFile();
            }
            byte[] files = attachmentDO.getFile();
            FileOutputStream outStream = new FileOutputStream(f);
            outStream.write(files);
            outStream.close();
            String fileName = attachmentDO.getAttachment_name();
            return new BaseResult(200, fileName);
        }
    }
}
