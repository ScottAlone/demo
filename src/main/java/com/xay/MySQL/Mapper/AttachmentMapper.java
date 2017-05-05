package com.xay.MySQL.Mapper;

import com.xay.MySQL.DO.AttachmentDO;
import org.apache.ibatis.annotations.*;

/**
 * @author ZhangTianren
 * @version v0.1 2017/5/5.
 */
@Mapper
public interface AttachmentMapper {
    @Select("SELECT attachment_name FROM customers WHERE customer_id=#{customer_id}")
    AttachmentDO getAttachmentNameByCustomerId(@Param("customer_id")int customer_id);

    @Insert("INSERT INTO attachment(attachment_name, city_id, tags, file) VALUES(#{attachment_name}, #{city_id}, #{tags}, #{file})")
    int insertAttachment(AttachmentDO attachmentDO);

    @Update("UPDATE attachment SET attachment_name=#{attachment_name}, city_id=#{city_id}, tags=#{tags}, file=#{file} WHERE attachment_name=#{attachment_name}")
    int updateAttachment(AttachmentDO attachmentDO);

    @Update("UPDATE customers SET attachment_name=#{attachment_name} WHERE customer_id=#{customer_id}")
    int updateAttachmentInCustomer(@Param("attachment_name")String attachment_name, @Param("customer_id")Integer customer_id);

    @Select("SELECT file FROM attachment WHERE attachment_name=#{attachment_name}")
    AttachmentDO getAttachmentByAttachmentName(@Param("attachment_name")String attachment_name);
}
