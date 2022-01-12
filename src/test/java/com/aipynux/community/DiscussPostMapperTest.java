package com.aipynux.community;

import com.aipynux.community.dao.DiscussPostMapper;
import com.aipynux.community.entity.DiscussPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author aipynux
 * @create 2021-12-20 17:17
 */
@SpringBootTest
public class DiscussPostMapperTest {
  @Autowired
  DiscussPostMapper discussPostMapper;

  @Test
  public void testSelectDiscussPosts(){
    List<DiscussPost>  listResult = discussPostMapper.selectDiscussPosts(0,0,10);
    for(DiscussPost discussPost : listResult){
      System.out.println(discussPost.toString());
    }
  }

  @Test
  public void testSelectDiscussRows(){
    System.out.println(discussPostMapper.selectDiscussRows(0));
  }
}
