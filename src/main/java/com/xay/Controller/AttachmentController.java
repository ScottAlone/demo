package com.xay.Controller;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/9.
 */
import com.xay.Domain.AttachmentDomain;
import com.xay.Domain.BaseResult;
import com.xay.Service.AttachmentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    //文件上传相关代码
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String  upload(@RequestParam("file") MultipartFile file, @RequestParam("gUsername") String username) {
        if (file.isEmpty()) {
            return "Empty file";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        if (fileName.split(".").length == 1){
            return "Unsupported file type";
        }
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
        String finalName = fileName.substring(0, fileName.lastIndexOf(".")) + "(" + LocalDateTime.now().format(dateTimeFormatter) + ")" + suffixName;
        // 文件上传后的路径
        String filePath = "C:\\Users\\Administrator\\Desktop\\files\\";
        File dest = new File(filePath + fileName);
        String token = RandomStringUtils.randomAlphabetic(36);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            File finalFile = new File(filePath + finalName);
            dest.renameTo(finalFile);
            return attachmentService.insertAttachment(new AttachmentDomain(finalName, username, token)).getMessage();
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    //文件下载相关代码
    @RequestMapping("/download")
    public String downloadFile(org.apache.catalina.servlet4preview.http.HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
        String attachId = request.getParameter("attachmentId");
        String token = request.getParameter("token");
        AttachmentDomain attachmentDomain = (AttachmentDomain)getAttachmentByAttachmentId(Integer.valueOf(attachId)).getData();
        if (attachmentDomain == null){
            return "No attachments found";
        }
        if (token == null || !token.equals(attachmentDomain.getToken())){
            return "Bad token";
        }
        String fileName = attachmentDomain.getAttachName();
        if (fileName != null) {
            String realPath = "C:\\Users\\Administrator\\Desktop\\files\\";
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));// 设置文件名
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
     * @param gUsername
     * @return
     */
    @RequestMapping(value = "/attachments", method = RequestMethod.GET)
    public BaseResult<Object> getAttachmentAll(@RequestParam("gUsername")String gUsername){
        return attachmentService.getAttachmentAll(gUsername);
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

