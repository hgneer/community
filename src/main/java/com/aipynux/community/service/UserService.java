package com.aipynux.community.service;

import com.aipynux.community.dao.UserMapper;
import com.aipynux.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author aipynux
 * @create 2022-01-04 21:19
 */
@Service
public class UserService {
  @Autowired
  private UserMapper userMapper;

  public User findUserById(int id){
    return userMapper.selectById(id);
  }
}
