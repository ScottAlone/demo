package com.xay.Service.impl;

import com.xay.Domain.AttachmentDomain;
import com.xay.Domain.BaseResult;
import com.xay.MySQL.DO.AccountDO;
import com.xay.MySQL.DO.AttachmentDO;
import com.xay.MySQL.Mapper.AccountMapper;
import com.xay.MySQL.Mapper.AttachmentMapper;
import com.xay.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public BaseResult<Object> insertAttachment(AttachmentDomain attachmentDomain) {
        AccountDO accountDO = accountMapper.getGuideByUsername(attachmentDomain.getgUsername());
        if (accountDO != null){
            attachmentMapper.insertAttachment(new AttachmentDO(attachmentDomain));
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No user found");
    }

    @Override
    public BaseResult<Object> getAttachmentAll(String gUsername){
        AttachmentDO[] attachmentDOS = attachmentMapper.getAttachmentAll(gUsername);
        AttachmentDomain[] attachmentDomain = new AttachmentDomain[attachmentDOS.length];
        if (attachmentDomain.length == 0){
            return new BaseResult<>(500, "No attachment found");
        }
        for (int i = 0; i < attachmentDOS.length; i++){
            attachmentDomain[i] = new AttachmentDomain(attachmentDOS[i], simpleDateFormat.format(attachmentDOS[i].getCreate_time()), simpleDateFormat.format(attachmentDOS[i].getUpdate_time()));
        }
        return new BaseResult<>(attachmentDomain);
    }

    @Override
    public BaseResult<Object> getAttachmentByAttachmentId(Integer attachmentId) {
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentByAttachmentId(attachmentId);
        if (attachmentDO != null){
            return new BaseResult<>(new AttachmentDomain(attachmentDO, simpleDateFormat.format(attachmentDO.getCreate_time()), simpleDateFormat.format(attachmentDO.getUpdate_time())));
        }
        return new BaseResult<>(500, "No attachment found");
    }

    @Override
    public BaseResult<Object> deleteAttachmentByAttachmentId(Integer attachmentId) {
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentByAttachmentId(attachmentId);
        if (attachmentDO != null){
            attachmentMapper.deleteAttachmentByAttachmentId(attachmentId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "No attachment found");
    }
}
