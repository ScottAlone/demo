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

import java.time.LocalDateTime;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public BaseResult<Object> getAttachmentAll(Integer ownerType, Integer ownerId){
        AttachmentDO[] attachmentDOS = attachmentMapper.getAttachmentAll(ownerType, ownerId);
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
