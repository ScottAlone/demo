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

    @Override
    public BaseResult<Object> insertAttachment(AttachmentDomain attachmentDomain) {
        AccountDO accountDO = accountMapper.getGuideByUsername(attachmentDomain.getgUsername());
        if (accountDO != null){
            attachmentMapper.insertAttachment(new AttachmentDO(attachmentDomain));
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "账户不存在");
    }

    @Override
    public BaseResult<Object> getAttachmentAll(String gUsername){
        AttachmentDO[] attachmentDOS = attachmentMapper.getAttachmentAll(gUsername);
        AttachmentDomain[] attachmentDomain = new AttachmentDomain[attachmentDOS.length];
        if (attachmentDomain.length == 0){
            return new BaseResult<>(500, "没有附件");
        }
        for (int i = 0; i < attachmentDOS.length; i++){
            attachmentDomain[i] = new AttachmentDomain(attachmentDOS[i]);
        }
        return new BaseResult<>(attachmentDomain);
    }

    @Override
    public BaseResult<Object> getAttachmentByAttachmentId(Integer attachmentId) {
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentByAttachmentId(attachmentId);
        if (attachmentDO != null){
            return new BaseResult<>(new AttachmentDomain(attachmentDO));
        }
        return new BaseResult<>(500, "未找到该附件");
    }

    @Override
    public BaseResult<Object> deleteAttachmentByAttachmentId(Integer attachmentId) {
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentByAttachmentId(attachmentId);
        if (attachmentDO != null){
            attachmentMapper.deleteAttachmentByAttachmentId(attachmentId);
            return new BaseResult<>();
        }
        return new BaseResult<>(500, "未找到该附件");
    }
}
