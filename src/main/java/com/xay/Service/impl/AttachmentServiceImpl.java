package com.xay.Service.impl;

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
    public BaseResult upload(Integer i) throws IOException{
        Integer customerId = 19;
        File file = new File("C:\\Users\\Administrator\\Desktop\\1.jpg");
        String fileType = file.getName().substring(file.getName().lastIndexOf("."));
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentNameByCustomerId(customerId);
        String attachName = RandomStringUtils.randomAlphanumeric(50);
        String finalPath = attachName + fileType;
        if (attachmentDO == null){
            attachmentMapper.insertAttachment(new AttachmentDO(finalPath, 1, "wa", SerializableUtil.fileToBytes(file)));
        }else {
            attachmentDO = attachmentMapper.getAttachmentByAttachmentName(attachmentDO.getAttachment_name());
            if (attachmentDO == null){
                attachmentMapper.insertAttachment(new AttachmentDO(finalPath, 1, "wa", SerializableUtil.fileToBytes(file)));
            }else {
                attachName = attachmentDO.getAttachment_name();
                finalPath = attachName;
                attachmentMapper.updateAttachment(new AttachmentDO(finalPath, 1, "wa", SerializableUtil.fileToBytes(file)));
            }
        }
        attachmentMapper.updateAttachmentInCustomer(finalPath, customerId);
        return new BaseResult();
    }

    @Override
    public BaseResult download(Integer customerId, String path) throws Exception{
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentNameByCustomerId(customerId);
        if (attachmentDO == null){
            return new BaseResult(500, "没有附件");
        }else {
            attachmentDO = attachmentMapper.getAttachmentByAttachmentName(attachmentDO.getAttachment_name());
            File f = new File(path + "\\" + attachmentDO.getAttachment_name());
            if (!f.exists()){
                f.createNewFile();
            }
            byte[] files = attachmentDO.getFile();
            FileOutputStream outStream = new FileOutputStream(f);
            outStream.write(files);
            outStream.close();
        }
        return new BaseResult();
    }
}
