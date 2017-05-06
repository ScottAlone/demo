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
     * 附件下载
     * @param customerId
     * @return
     */
    BaseResult download(Integer customerId) throws Exception;

    /**
     * 附件上传
     * @param attachmentDomain
     * @return
     * @throws IOException
     */
    BaseResult upload(AttachmentDomain attachmentDomain) throws IOException;
}
