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
    @Insert("INSERT INTO attachment(attachment_name, owner_type, owner_id, file, file_type) " +
            "VALUES(#{attachment_name}, #{owner_type}, #{owner_id}, #{file,jdbcType=BLOB}, 1)")
    int insertAttachment(AttachmentDO attachmentDO);

    /**
     * 所有附件获取
     * @param owner_id
     * @param owner_type
     * @return
     */
    @Select("SELECT * FROM attachment WHERE owner_type=#{owner_type} AND owner_id=#{owner_id} AND file_type=1")
    AttachmentDO[] getAttachmentAll(@Param("owner_type")Integer owner_type, @Param("owner_id")Integer owner_id);

    /**
     * 单个附件获取
     * @param attachment_id
     * @return
     */
    @Select("SELECT * FROM attachment WHERE attachment_id=#{attachment_id} AND file_type=1")
    AttachmentDO getAttachmentByAttachmentId(@Param("attachment_id")Integer attachment_id);

    /**
     * 附件删除
     * @param attachment_id
     * @return
     */
    @Delete("DELETE FROM attachment WHERE attachment_id=#{attachment_id} AND file_type=1")
    int deleteAttachmentByAttachmentId(@Param("attachment_id")Integer attachment_id);
}
