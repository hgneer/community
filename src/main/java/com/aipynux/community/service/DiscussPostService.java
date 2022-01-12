package com.aipynux.community.service;

import com.aipynux.community.dao.DiscussPostMapper;
import com.aipynux.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aipynux
 * @create 2022-01-04 20:55
 */
@Service
public class DiscussPostService {
  @Autowired
  private DiscussPostMapper discussPostMapper;

  public List<DiscussPost> findDiscussPosts(int userId,int offset,int limit){
    return discussPostMapper.selectDiscussPosts(userId, offset, limit);
  }
  public int findDiscussRows(int userId){
    return discussPostMapper.selectDiscussRows(userId);
  }
}
