package com.xay.MySQL.Mapper;

import com.xay.MySQL.DO.AttachmentDO;
import org.apache.ibatis.annotations.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Mapper
public interface AttachmentMapper {
    /**
     * 附件上传
     * @param attachmentDO
     * @return
     */
    @Insert("INSERT INTO attachment(attachment_name, g_username, token) " +
            "VALUES(#{attachment_name}, #{g_username}, #{token})")
    int insertAttachment(AttachmentDO attachmentDO);

    /**
     * 所有附件获取
     * @param g_username
     * @return
     */
    @Select("SELECT * FROM attachment WHERE binary g_username=#{g_username}")
    AttachmentDO[] getAttachmentAll(@Param("g_username")String g_username);

    /**
     * 单个附件获取
     * @param attachment_id
     * @return
     */
    @Select("SELECT * FROM attachment WHERE attachment_id=#{attachment_id}")
    AttachmentDO getAttachmentByAttachmentId(@Param("attachment_id")Integer attachment_id);

    /**
     * 附件删除
     * @param attachment_id
     * @return
     */
    @Delete("DELETE FROM attachment WHERE attachment_id=#{attachment_id}")
    int deleteAttachmentByAttachmentId(@Param("attachment_id")Integer attachment_id);
}
