package com.xay.Service;

import com.xay.Domain.AttachmentDomain;
import com.xay.Domain.BaseResult;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
public interface AttachmentService {

    /**
     * 插入附件
     * @param attachmentDomain
     * @return
     */
    BaseResult<Object> insertAttachment(AttachmentDomain attachmentDomain);

    /**
     * 所有附件获取
     * @param gUsername
     * @return
     */
    BaseResult<Object> getAttachmentAll(String gUsername);

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
