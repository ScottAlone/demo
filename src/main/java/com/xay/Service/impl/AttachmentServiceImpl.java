package com.xay.Service.impl;

import com.xay.Domain.BaseResult;
import com.xay.MySQL.DO.AttachmentDO;
import com.xay.MySQL.Mapper.AttachmentMapper;
import com.xay.Service.AttachmentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Autowired
    AttachmentMapper attachmentMapper;

    @Override
    public BaseResult upload(Integer i) throws IOException{
        Integer customerId = 12;
        File file = new File("C:\\Users\\Administrator\\Desktop\\1.jpg");
        String fileType = file.getName().substring(file.getName().lastIndexOf("."));
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentNameByCustomerId(customerId);
        String attachName = RandomStringUtils.randomAlphanumeric(50);
        InputStream in = new BufferedInputStream(new FileInputStream(file));
        String finalPath = attachName + fileType;
        if (attachmentDO == null){
            attachmentMapper.insertAttachment(new AttachmentDO(attachName, 1, "wa"));
//            jdbcTemplate.execute(finalSql, (PreparedStatementCallback<Object>) ps -> {
//                ps.setString(1, finalPath);
//                ps.setInt(2, 11);
//                ps.setString(3, "ww");
//                ps.setBinaryStream(4, in);
//                ps.execute();
//                return null;
//            });
        }else {
            attachName = attachmentDO.getAttachmentName();
            attachmentMapper.updateAttachment(new AttachmentDO(attachName, 1, "wa"));
//            jdbcTemplate.execute(finalSql, (PreparedStatementCallback<Object>) ps -> {
//                ps.setString(1, finalPath);
//                ps.setInt(2, 11);
//                ps.setString(3, "ww");
//                ps.setBinaryStream(4, in);
//                ps.setString(5, map.get("attachment_name").toString());
//                ps.execute();
//                return null;
//            });
        }
        in.close();
        attachmentMapper.updateAttachmentInCustomer(attachName, customerId);
        return new BaseResult();
    }

    @Override
    public BaseResult download(Integer customerId, String path) throws IOException{
        AttachmentDO attachmentDO = attachmentMapper.getAttachmentNameByCustomerId(customerId);
        if (attachmentDO == null){
            return new BaseResult(500, "没有附件");
        }else {
            attachmentDO = attachmentMapper.getAttachmentByAttachmentName(attachmentDO.getAttachmentName());
//            String filename = map.get("attachment_name").toString();
//            sql = "SELECT file FROM attachment WHERE attachment_name=?";
//            map = jdbcTemplate.queryForList(sql, map.get("attachment_name")).get(0);
//            byte[] b = (byte[])map.get("file");
//            File f = new File(path + "\\" + filename);
//            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f));
//            bos.write(b);
//            bos.flush();
//            bos.close();
        }
        return new BaseResult();
    }
}
