package com.aipynux.community;

import com.aipynux.community.dao.UserMapper;
import com.aipynux.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author aipynux
 * @create 2021-12-13 18:12
 */
@SpringBootTest
public class MyBatisTests {
  @Autowired
  UserMapper userMapper;

  @Test
  public void testSelectById(){
    User user = userMapper.selectById(102);
    System.out.println(user.toString()  );
  }

  @Test
  public void testSelectByName(){
    User user = userMapper.selectByName("crane");
    System.out.println(user.toString()  );
  }

  @Test
  public void testSelectByEmail(){
    User user = userMapper.selectByEmail("nowcoder103@sina.com");
    System.out.println(user.toString()  );
  }

  @Test
  public void testInsertUser(){
    User user = new User();
    user.setStatus(1);
    user.setType(1);
    user.setUsername("crane233");
    user.setPassword("1234");
    user.setEmail("crane@gmail.com");
    user.setActivationCode("a@sasdfsdlfsjadflkfasfsda");
    user.setCreateTime(new Date());
    int rows = userMapper.insertUser(user);
    System.out.println(rows);
    System.out.println(user.getId());
  }

  @Test
  public void testUpdateStatus(){
    int rows = userMapper.updateStatus(102,0);
    System.out.println(rows);
  }

  @Test
  public void testUpdateHeader(){
    int rows = userMapper.updateHeader(102,"http://images.nowcoder.com/head/233t.png");
    System.out.println(rows);
  }

  @Test
  public void testUpdatePassword(){
    int rows = userMapper.updatePassword(102,"666");
    System.out.println(rows);
  }

  @Test
  public void testDeleteById(){
    userMapper.deleteById(152);
    userMapper.deleteById(153);
  }
}
