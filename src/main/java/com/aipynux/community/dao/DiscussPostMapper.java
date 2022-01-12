package com.aipynux.community.dao;

import com.aipynux.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author aipynux
 * @create 2021-12-20 16:09
 */

@Mapper
public interface DiscussPostMapper {
  List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);
  int selectDiscussRows(@Param("userId") int userId);
}
