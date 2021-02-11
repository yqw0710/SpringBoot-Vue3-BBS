package com.yuan.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuan.bbs.common.pojo.ChatRecordDto;
import com.yuan.bbs.entity.Chat;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 聊天记录表 Mapper 接口
 * </p>
 *
 * @author yuan
 * @since 2021-02-08
 */
public interface ChatMapper extends BaseMapper<Chat> {

    @Select("select  c.id,c.sid,c.rid,c.content,c.read,c.created,u.avatar,u.nickname\n" +
            "from y_chat c,y_user u\n" +
            "where c.rid=#{rid} and c.rid=u.id and c.read =0\n" +
            "order by c.created desc")
    @ResultType(Chat.class)
    List<Chat> unreadChatRecordList( Integer rid);



    @Select("SELECT count(readed = 0 OR NULL) AS unread,content,created,from_id,nickname,avatar \n" +
            " FROM (\n" +
            "  SELECT rid AS from_id,content, readed,created \n" +
            "   FROM y_chat WHERE (sid=#{uid}) AND (rid<>#{uid}) \n" +
            "  UNION\n" +
            "  SELECT sid AS from_id,content,readed,created \n" +
            "   FROM y_chat WHERE (sid<>#{uid}) AND (rid=#{uid}) \n" +
            "  ORDER BY created DESC\n" +
            ") AS uni\n" +
            "INNER JOIN y_user AS u ON from_id = u.id GROUP BY from_id ORDER BY created DESC")
    @ResultType(ChatRecordDto.class)
    List<ChatRecordDto> chatRecordList(Integer uid);
}
