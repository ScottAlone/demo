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
     * 查询附件名
     * @param customer_id
     * @return
     */
    @Select("SELECT attachment_name FROM customers WHERE customer_id=#{customer_id}")
    AttachmentDO getAttachmentNameByCustomerId(@Param("customer_id")int customer_id);

    /**
     * 插入附件
     * @param attachmentDO
     * @return
     */
    @Insert("INSERT INTO attachment(attachment_name, city_id, tags, file) VALUES(#{attachment_name}, #{city_id}, #{tags}, #{file,jdbcType=BLOB})")
    int insertAttachment(AttachmentDO attachmentDO);

    /**
     * 更新附件
     * @param attachmentDO
     * @return
     */
    @Update("UPDATE attachment SET attachment_name=#{attachment_name}, city_id=#{city_id}, tags=#{tags}, file=#{file,jdbcType=BLOB} WHERE attachment_name=#{attachment_name}")
    int updateAttachment(AttachmentDO attachmentDO);

    /**
     * 更新附件记录
     * @param attachment_name
     * @param customer_id
     * @return
     */
    @Update("UPDATE customers SET attachment_name=#{attachment_name} WHERE customer_id=#{customer_id}")
    int updateAttachmentInCustomer(@Param("attachment_name")String attachment_name, @Param("customer_id")Integer customer_id);

    /**
     * 查询附件
     * @param attachment_name
     * @return
     */
    @Select("SELECT attachment_name, file FROM attachment WHERE attachment_name=#{attachment_name}")
    AttachmentDO getAttachmentByAttachmentName(@Param("attachment_name")String attachment_name);
}
