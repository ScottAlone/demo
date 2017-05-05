package com.xay.Service;

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
     * @param path
     * @return
     */
    BaseResult download(Integer customerId, String path) throws Exception;

    /**
     * 附件上传
     * @param i
     * @return
     */
    BaseResult upload(Integer i) throws IOException;
}
