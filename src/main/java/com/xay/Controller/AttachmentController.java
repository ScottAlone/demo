package com.xay.Controller;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/9.
 */
import com.xay.Domain.BaseResult;
import com.xay.Service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class AttachmentController {
    private static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);

    @Autowired
    private AttachmentService attachmentService;

    //文件上传相关代码
    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("test") MultipartFile file) {
        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = "C:\\Users\\Administrator\\Desktop\\files\\";
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    //文件下载相关代码
    @RequestMapping("/download")
    public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response) {
        String fileName = "2.jpg";
        if (fileName != null) {
            String realPath = "C:\\Users\\Administrator\\Desktop\\files";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 所有附件获取
     * @param ownerType
     * @param ownerId
     * @return
     */
    @RequestMapping(value = "/attachments", method = RequestMethod.GET)
    public BaseResult<Object> getAttachmentAll(@RequestParam("ownerType")Integer ownerType, @RequestParam("ownerId")Integer ownerId){
        return attachmentService.getAttachmentAll(ownerType, ownerId);
    }

    /**
     * 单个附件获取
     * @param attachmentId
     * @return
     */
    @RequestMapping(value = "/attachments/id", method = RequestMethod.GET)
    public BaseResult<Object> getAttachmentByAttachmentId(@RequestParam("attachmentId")Integer attachmentId){
        return attachmentService.getAttachmentByAttachmentId(attachmentId);
    }

    /**
     * 附件删除
     * @param attachmentId
     * @return
     */
    @RequestMapping(value = "/attachments/id", method = RequestMethod.DELETE)
    public BaseResult<Object> deleteAttachmentByAttachmentId(@RequestParam("attachmentId")Integer attachmentId){
        return attachmentService.deleteAttachmentByAttachmentId(attachmentId);
    }

    //多文件上传
//    @RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
//    @ResponseBody
//    public String handleFileUpload(HttpServletRequest request) {
//        List<MultipartFile> files = ((MultipartHttpServletRequest) request)
//                .getFiles("file");
//        MultipartFile file = null;
//        BufferedOutputStream stream = null;
//        for (int i = 0; i < files.size(); ++i) {
//            file = files.get(i);
//            if (!file.isEmpty()) {
//                try {
//                    byte[] bytes = file.getBytes();
//                    stream = new BufferedOutputStream(new FileOutputStream(
//                            new File(file.getOriginalFilename())));
//                    stream.write(bytes);
//                    stream.close();
//
//                } catch (Exception e) {
//                    stream = null;
//                    return "You failed to upload " + i + " => "
//                            + e.getMessage();
//                }
//            } else {
//                return "You failed to upload " + i
//                        + " because the file was empty.";
//            }
//        }
//        return "upload successful";
//    }
}

