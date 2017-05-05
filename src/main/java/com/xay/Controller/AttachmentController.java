package com.xay.Controller;

import com.xay.Domain.BaseResult;
import com.xay.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@RestController
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;

    /**
     * 上传附件
     * @param f
     * @return BaseResult
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
//    public BaseResult upload(@RequestParam("customerId")Integer customerId,
//                             @RequestParam("cityId")Integer cityId,
//                             @RequestParam("tags")String tags,
//                             @RequestParam("file")File file) throws IOException{
    public BaseResult upload(@RequestParam("file")Integer f) throws IOException {
        return attachmentService.upload(f);
    }

    /**
     * 下载附件
     * @param customerId
     * @return BaseResult
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public BaseResult download(@RequestParam("customerId")Integer customerId,
                               @RequestParam("path")String path) throws IOException {
        return attachmentService.download(customerId, path);
    }

}
