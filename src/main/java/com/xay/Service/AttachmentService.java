package com.xay.Service;

import com.xay.Domain.AttachmentDomain;
import com.xay.Domain.BaseResult;

import java.io.IOException;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public interface AttachmentService {


    /**
     * 所有附件获取
     * @param ownerType
     * @param ownerId
     * @return
     */
    BaseResult<Object> getAttachmentAll(Integer ownerType, Integer ownerId);

    /**
     * 单个附件获取
     * @param attachmentId
     * @return
     */
    BaseResult<Object> getAttachmentByAttachmentId(Integer attachmentId);

    /**
     * 删除附件
     * @param attachmentId
     * @return
     */
    BaseResult<Object> deleteAttachmentByAttachmentId(Integer attachmentId);
}
