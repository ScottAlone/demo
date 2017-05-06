package com.xay.Controller;

import com.xay.Domain.AttachmentDomain;
import com.xay.Domain.BaseResult;
import com.xay.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@RestController
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 上传附件
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public BaseResult upload(HttpServletRequest request) throws IOException{
        request.setCharacterEncoding("UTF-8");
        String customerId = request.getParameter("customerId");
        String cityId = request.getParameter("cityId");
        String tags = request.getParameter("tags");
        String f = request.getParameter("file");
        String fileType = request.getParameter("fileType");
        String[] t = f.split(",");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] file = decoder.decodeBuffer(t[1]);
        AttachmentDomain attachmentDomain = new AttachmentDomain(Integer.valueOf(customerId), Integer.valueOf(cityId), tags, file, fileType);
        return attachmentService.upload(attachmentDomain);
    }

    /**
     * 下载附件
     * @param customerId
     * @return BaseResult
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public BaseResult download(@RequestParam("customerId")Integer customerId) throws Exception {
        return attachmentService.download(customerId);
    }
}
